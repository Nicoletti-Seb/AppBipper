package app.mbds.fr.unice.appbipper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.NotificationItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Notification;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.MyNotificationTask;

public class MyNotificationListActivity extends AppCompatActivity {

    private static final String TAG = "NotificationListActivity";

    //Model
    private Person user;
    private List<Notification> notifications = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((BipperApplication)getApplication()).getUser();

        setContentView(R.layout.activity_notification_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Init list
        NotificationItemAdapter adapter = new NotificationItemAdapter(this, notifications);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);


        new MyNotificationTask(adapter, this).execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
