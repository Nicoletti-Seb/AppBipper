package app.mbds.fr.unice.appbipper.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
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
import app.mbds.fr.unice.appbipper.adapter.PersonItemAdapter;
import app.mbds.fr.unice.appbipper.adapter.ProductItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.entity.Product;

/**
 * Created by MBDS on 27/11/2016.
 */

public class ProductTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = "ProductTask";

    private Context context;
    private ProductItemAdapter productItemAdapter;
    private String type;

    public ProductTask(String type, ProductItemAdapter productItemAdapter, Context context) {
        this.type = type;
        this.context = context;
        this.productItemAdapter = productItemAdapter;
    }
    @Override
    protected String doInBackground(Void... params) {
        StringBuilder result = new StringBuilder();
        try {
            String server = context.getResources().getString(R.string.url_server);
            String service = String.format(context.getString(R.string.url_service_product_type),
                    URLEncoder.encode(type, "UTF-8"));

            URL url = new URL( server + service);

            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
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
            Log.d(TAG, "Result " + type + " is empty...");
            return;
        }

        try{
            //https://static.javadoc.io/com.google.code.gson/gson/2.6.2/com/google/gson/reflect/TypeToken.html
            Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
            List<Product> list = new Gson().fromJson(result, listType);
            productItemAdapter.getProducts().addAll(list);
            productItemAdapter.notifyDataSetChanged();
        }catch(Exception e){
            Toast.makeText(context, R.string.error_parse_json, Toast.LENGTH_SHORT).show();
        }
    }
}
