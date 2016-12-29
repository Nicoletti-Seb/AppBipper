package app.mbds.fr.unice.appbipper.service;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.adapter.PersonItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Person;

/**
 * Created by MBDS on 27/11/2016.
 */

public class ListingTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private PersonItemAdapter personItemAdapter;

    public ListingTask(PersonItemAdapter personItemAdapter, Context context) {
        this.personItemAdapter = personItemAdapter;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        //Effectuer la requete vers les WS ici
        String url = context.getResources().getString(R.string.url_server);
        String service = context.getResources().getString(R.string.url_service_person);
        String urlList = url + service;

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

        System.out.println("Reponse " + theResponse);

        //https://static.javadoc.io/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
        Type listType = new TypeToken<ArrayList<Person>>() {}.getType();
        List<Person> list = new Gson().fromJson(theResponse, listType);
        personItemAdapter.getPerson().addAll(list);
        personItemAdapter.notifyDataSetChanged();

    }
}