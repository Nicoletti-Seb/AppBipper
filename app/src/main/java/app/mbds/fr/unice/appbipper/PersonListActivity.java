package app.mbds.fr.unice.appbipper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.PersonItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.PersonTask;

public class PersonListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String RESULT_PERSON = "RESULT_PERSON";

    //Model
    private PersonItemAdapter adapter;
    private List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Adapter
        adapter = new PersonItemAdapter(this, persons, R.layout.person_item_list);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        //Get persons
        new PersonTask(adapter, this).execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra(RESULT_PERSON, persons.get(position));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_refresh:
                //empty the list
                persons.clear();
                adapter.notifyDataSetChanged();

                //update
                new PersonTask(adapter, this).execute();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
