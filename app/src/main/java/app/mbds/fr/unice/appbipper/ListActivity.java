package app.mbds.fr.unice.appbipper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.PersonItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Login;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.ListingTask;

public class ListActivity extends Activity implements View.OnClickListener {

    //View
    private ListView listView;

    //Model
    private ListingTask mListingTask;
    private List<Person> person = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //List
        PersonItemAdapter adapter = new PersonItemAdapter(this, person);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        mListingTask = new ListingTask(adapter, this);
        mListingTask.execute();

        // Listener button
        Button addServer = (Button)findViewById(R.id.add_server);
        addServer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_server:
                startActivity(new Intent(ListActivity.this, RegisterActivity.class));
                break;
        }
    }
}
