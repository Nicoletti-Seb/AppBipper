package app.mbds.fr.unice.appbipper.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.adapter.PersonItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Person;

/**
 * Created by MBDS on 27/11/2016.
 */

public class BuzzTask extends AsyncTask<Person, Void, Boolean> {

    private static final String TAG = "BuzzTask";

    private Context context;

    public BuzzTask(Context context) {
        this.context = context;
    }


    /**
     * @param params: [receiver, sender]
     * @return
     */
    @Override
    protected Boolean doInBackground(Person... params) {

        if(params.length < 2){
            return null;
        }

        Buzz buzz = new Buzz();
        buzz.receiver = params[0];
        buzz.sender = params[1];
        String stringJson = new Gson().toJson(buzz);
        boolean result = false;
        try {
            String server = context.getResources().getString(R.string.url_server);
            String service = context.getString(R.string.url_service_buzz);

            URL url = new URL(server + service);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("POST");
            connection.setAllowUserInteraction(false);
            connection.connect();

            //Write data
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            osw.write(stringJson);
            osw.flush();
            osw.close();

            int codeResponse = connection.getResponseCode();
            if( 200 <= codeResponse && codeResponse < 300){
                result = true;

                StringBuilder r = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while((line = br.readLine()) != null){
                    r.append(line);
                }
                br.close();
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            Toast.makeText(context, R.string.buzz_send, Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context, R.string.error_toast, Toast.LENGTH_LONG).show();
        }
    }


    private class Buzz implements Serializable{
        Person receiver;
        Person sender;
    }
}