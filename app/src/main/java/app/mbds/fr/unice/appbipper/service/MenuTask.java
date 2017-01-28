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
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.adapter.MenuItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Menu;
import app.mbds.fr.unice.appbipper.entity.Product;

/**
 * Created by MBDS on 27/11/2016.
 */

public class MenuTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "MenuTask";

    private final Context context;

    private MenuItemAdapter menuItemAdapter;

    public MenuTask(MenuItemAdapter menuItemAdapter, Context context) {
        this.menuItemAdapter = menuItemAdapter;
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();
        try {
            String server = context.getResources().getString(R.string.url_server);
            String service = context.getString(R.string.url_service_menu);

            URL url = new URL( server + service);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setAllowUserInteraction(false);
            connection.connect();

            int codeResponse = connection.getResponseCode();
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

        //https://static.javadoc.io/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
        Type listType = new TypeToken<ArrayList<Menu>>() {}.getType();
        List<Menu> list = new Gson().fromJson(result, listType);
        menuItemAdapter.getMenus().addAll(list);
        menuItemAdapter.notifyDataSetChanged();
    }

}
