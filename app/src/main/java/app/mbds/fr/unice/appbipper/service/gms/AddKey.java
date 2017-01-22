package app.mbds.fr.unice.appbipper.service.gms;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import app.mbds.fr.unice.appbipper.R;

/**
 * Created by MBDS on 27/11/2016.
 */

public class AddKey extends AsyncTask<String, Void, Boolean> {

    private static final String TAG = "AddKey";

    private Context context;

    public AddKey(Context context) {
        this.context = context;
    }


    /**
     * @param params: [idPerson, token, apiKey]
     * @return
     */
    @Override
    protected Boolean doInBackground(String... params) {

        if(params.length < 3){
            return null;
        }

        boolean result = false;

        String idPerson = params[0];
        String token = params[1];
        String apiKey = params[2];

        try {
            String server = context.getResources().getString(R.string.url_server);
            String service = String.format(context.getString(R.string.url_service_add_key),
                    idPerson, token, apiKey);

            URL url = new URL(server + service);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            connection.setAllowUserInteraction(false);
            connection.connect();


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

                Log.i(TAG, "Result : " + r.toString());
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
        if(!result){
            Toast.makeText(context, R.string.error_toast, Toast.LENGTH_LONG).show();
        }
    }
}