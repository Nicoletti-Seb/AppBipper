package app.mbds.fr.unice.appbipper.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.entity.Product;

public class DescProduct extends AppCompatActivity {

    public static final String ID_PRODUCT = "BUNDLE_PARAMS_ID";
    private GetProductInfoTask mGetProductInfoTask = null;

    private TextView txt_title_product;
    private ImageView img_image_product;
    private TextView txt_price_value;
    private TextView txt_calories_value;
    private TextView txt_type_value;
    private TextView txt_discount_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc_product);

        String id = savedInstanceState.getString(ID_PRODUCT);

        txt_title_product = (TextView) findViewById(R.id.txt_title_product);
        img_image_product = (ImageView) findViewById(R.id.img_image_product);
        txt_price_value = (TextView) findViewById(R.id.txt_price_value);
        txt_calories_value = (TextView) findViewById(R.id.txt_calories_value);
        txt_type_value = (TextView) findViewById(R.id.txt_type_value);
        txt_discount_value = (TextView) findViewById(R.id.txt_discount_value);

        mGetProductInfoTask = new GetProductInfoTask(id);
        mGetProductInfoTask.execute((Void) null);
    }

    public class GetProductInfoTask extends AsyncTask<Object, Object, String> {

        private final String mId;

        public GetProductInfoTask(String id) {
            mId = id;
        }

        @Override
        protected String doInBackground(Object... params) {

            String ulrProduct = " http://95.142.161.35:1337/product/" + mId;

            try {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(ulrProduct);

                HttpResponse response = client.execute(get);
                System.out.println("\nSending 'GET' request to URL : " + ulrProduct);
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
        protected void onPostExecute(String theResponse) {
            super.onPostExecute(theResponse);

            Gson gson = new Gson();
            Product product = gson.fromJson(theResponse, Product.class);

            //txt_title_product =
        }
    }
}
