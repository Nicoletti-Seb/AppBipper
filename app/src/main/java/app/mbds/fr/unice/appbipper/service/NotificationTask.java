package app.mbds.fr.unice.appbipper.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.adapter.MenuItemAdapter;
import app.mbds.fr.unice.appbipper.adapter.NotificationItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Menu;
import app.mbds.fr.unice.appbipper.entity.Notification;

/**
 * Created by MBDS on 27/11/2016.
 */

public class NotificationTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "NotificationTask";

    private Context context;
    private NotificationItemAdapter notificationItemAdapter;

    public NotificationTask(NotificationItemAdapter notificationItemAdapter, Context context) {
        this.notificationItemAdapter = notificationItemAdapter;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();
        try {
            String server = context.getResources().getString(R.string.url_server);
            String service = context.getString(R.string.url_service_notification);

            URL url = new URL( server + service);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setAllowUserInteraction(false);
            connection.connect();

            int codeResponse = connection.getResponseCode();
            Log.i(TAG, "codeResponse :" + codeResponse);
            if( 200 <= codeResponse && codeResponse < 300 ){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                while((line = br.readLine()) != null){
                    result.append(line);
                }
                br.close();
            }

            //Close
            connection.disconnect();

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected void onPostExecute(String result) {
        if(result == null || result.isEmpty()) {
            Log.i(TAG, "Result menu is empty...");
            return;
        }

        try{
            //https://static.javadoc.io/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
            Type listType = new TypeToken<ArrayList<Notification>>() {}.getType();
            List<Notification> list = new Gson().fromJson(result, listType);
            notificationItemAdapter.getNotifications().addAll(list);
            notificationItemAdapter.notifyDataSetChanged();
        }catch(Exception e){
            Toast.makeText(context, R.string.error_parse_json, Toast.LENGTH_SHORT).show();
        }
    }

}
