package app.mbds.fr.unice.appbipper;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.PersonItemAdapter;
import app.mbds.fr.unice.appbipper.adapter.ProductItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.PersonTask;

public class PersonListActivity extends ListActivity {

    public static final String RESULT_PERSON = "RESULT_PERSON";

    //Model
    private List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person_list);

        //Adapter
        PersonItemAdapter adapter = new PersonItemAdapter(this, persons, R.layout.person_item_list);
        setListAdapter(adapter);

        //Get persons
        new PersonTask(adapter, this).execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent();
        intent.putExtra(RESULT_PERSON, persons.get(position));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
