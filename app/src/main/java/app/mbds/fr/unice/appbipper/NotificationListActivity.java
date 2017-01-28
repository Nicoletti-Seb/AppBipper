package app.mbds.fr.unice.appbipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.NotificationItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Notification;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.NotificationTask;

public class NotificationListActivity extends AppCompatActivity {

    private static final String TAG = "NotificationListActivity";

    //Model
    private NotificationItemAdapter adapter;
    private Person user;
    private List<Notification> notifications = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((BipperApplication)getApplication()).getUser();

        setContentView(R.layout.activity_notification_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Init list
        adapter = new NotificationItemAdapter(this, notifications);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);

        new NotificationTask(adapter, this).execute();
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
                notifications.clear();
                adapter.notifyDataSetChanged();

                //update
                new NotificationTask(adapter, this).execute();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
