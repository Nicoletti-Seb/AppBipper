package app.mbds.fr.unice.appbipper.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.entity.Menu;

/**
 * Created by MBDS on 27/11/2016.
 */

public class CreateMenuTask extends AsyncTask<Menu, Void, Boolean> {

    private static final String TAG = "CreateMenuTask";

    private final Context context;

    public CreateMenuTask(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Menu... params) {
        //Verify params
        if( params.length <= 0 ){
            return null;
        }
        Menu menu  = params[0];

        //Post request
        Gson g = new Gson();
        String stringJson = g.toJson(menu, Menu.class);
        boolean result = false;
        try {
            URL url = new URL(context.getString(R.string.url_server) + context.getString(R.string.url_service_menu));
            Log.i(TAG, "URL: " + url);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", "UTF-8");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setChunkedStreamingMode(0);
            connection.connect();

            //Write data
            OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            osw.write(stringJson);
            osw.flush();
            osw.close();

            int codeResponse = connection.getResponseCode();
            if( 200 <= codeResponse && codeResponse < 300 ){
                result = true;
            }

            //Close
            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return result;
    }


    @Override
    protected void onPostExecute(Boolean bool) {
        //Server execute request but send nothing....
        //if(bool){
            Toast.makeText(context, R.string.menu_create, Toast.LENGTH_LONG).show();
        /*}else{
            Toast.makeText(context, R.string.error_toast, Toast.LENGTH_LONG).show();
        }*/
    }

}
