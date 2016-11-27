package app.mbds.fr.unice.appbipper.service;

import android.os.AsyncTask;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.ListActivity;
import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.adapter.PersonItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Person;

/**
 * Created by MBDS on 27/11/2016.
 */

public class ListingTask extends AsyncTask<Void, Void, String> {

    private final ListActivity mListActivity;

    public ListingTask(ListActivity listActivity) {
        mListActivity = listActivity;
    }

    @Override
    protected String doInBackground(Void... params) {
        //Effectuer la requete vers les WS ici
        String urlList = "http://95.142.161.35:1337/person";

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(urlList);

            //addHeader
            get.setHeader("Content-Type", "application/json");

            HttpResponse response = client.execute(get);
            System.out.println("\nSending 'GET' request to URL : " + urlList);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            System.out.println(result.toString());
            return result.toString();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Afficher un loading "Patientez, inscription en cours..."
    }

    @Override
    protected void onPostExecute(String theResponse) {
        super.onPostExecute(theResponse);

        //showProgressDialog(false);
        ListView lst = (ListView) mListActivity.findViewById(R.id.listView);
        List<Person> person = new ArrayList<>();

        Gson gson = new Gson();
        ArrayList array = gson.fromJson(theResponse, ArrayList.class);
        for (int i = 0; i < array.size(); i++) {
            LinkedTreeMap ob = (LinkedTreeMap) array.get(i);
            System.out.println("o"+i+" :: "+ob.toString());

            Person p = new Person();
            p.setId((String) ob.get("id"));
            p.setNom((String) ob.get("nom"));
            p.setPrenom((String) ob.get("prenom"));
            p.setEmail((String) ob.get("email"));
            p.setPassword((String) ob.get("password"));
            p.setSexe((String) ob.get("sexe"));
            p.setTelephone((String) ob.get("telephone"));
            p.setCreatedBy((String) ob.get("createdBy"));
            p.setConnected((boolean) ob.get("connected"));

            person.add(p);
        }

        PersonItemAdapter adapter = new PersonItemAdapter(mListActivity, person);
        lst.setAdapter(adapter);
    }
}