package app.mbds.fr.unice.appbipper.service;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.BaseAdapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.adapter.PersonItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Person;

/**
 * Created by MBDS on 27/11/2016.
 */

public class DeleteUserTask extends AsyncTask<Object, Object, String> {

    private final Context context;
    private final PersonItemAdapter personItemAdapter;
    private Person personne;

    public DeleteUserTask(Person personne, PersonItemAdapter personItemAdapter, Context context) {
        this.personne = personne;
        this.context = context;
        this.personItemAdapter = personItemAdapter;
    }
    @Override
    protected String doInBackground(Object... params) {
        String server = context.getResources().getString(R.string.url_server);
        String service = String.format(context.getString(R.string.url_service_person_delete), personne.getId());

        //Delete request
        try {
            URL url = new URL(server + service);
            System.out.println("url delete: " + url);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("DELETE");
            connection.connect();

            String result = "" + connection.getResponseCode();
            System.out.println("Response :"+result);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(final String success) {
        System.out.println("success :"+success);

        if(success.equals("200")) {
            personItemAdapter.person.remove(personne);
            personItemAdapter.notifyDataSetChanged();
        }

    }
}
