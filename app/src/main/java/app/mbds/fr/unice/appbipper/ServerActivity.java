package app.mbds.fr.unice.appbipper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.PersonItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.PersonTask;

public class ServerActivity extends Activity implements View.OnClickListener {

    //View
    private ListView listView;

    //Model
    private PersonTask mListingTask;
    private List<Person> person = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_list);

        //List
        PersonItemAdapter adapter = new PersonItemAdapter(this, person, R.layout.person_remove_item_list);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);

        mListingTask = new PersonTask(adapter, this);
        mListingTask.execute();

        // Listener button
        Button addServer = (Button)findViewById(R.id.add_server);
        addServer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_server:
                startActivity(new Intent(ServerActivity.this, RegisterActivity.class));
                break;
        }
    }
}
