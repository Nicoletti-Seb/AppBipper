package app.mbds.fr.unice.appbipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.mbds.fr.unice.appbipper.entity.Person;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {


    private Person user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        user = ((BipperApplication)getApplication()).getUser();

        Button servers = (Button)findViewById(R.id.button_list);
        servers.setOnClickListener(this);

        Button btnCreateMenu = (Button)findViewById(R.id.button_create_menu);
        btnCreateMenu.setOnClickListener(this);

        Button allMenus = (Button)findViewById(R.id.button_all_menus);
        allMenus.setOnClickListener(this);

        Button myMenus = (Button)findViewById(R.id.button_my_menu);
        myMenus.setOnClickListener(this);

        Button allNotification = (Button)findViewById(R.id.button_all_notifications);
        allNotification.setOnClickListener(this);

        Button myNotifications = (Button)findViewById(R.id.button_my_notifications);
        myNotifications.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.button_list:
                startActivity(new Intent(this, ServerActivity.class));
                break;
            case R.id.button_create_menu:
                startActivity(new Intent(this, CreateMenuActivity.class));
                break;
            case R.id.button_all_menus:
                i = new Intent(this, MenuListActivity.class);
                startActivity(i);
                break;
            case R.id.button_my_menu:
                i = new Intent(this, MyMenuListActivity.class);
                startActivity(i);
                break;
            case R.id.button_all_notifications:
                i = new Intent(this, NotificationListActivity.class);
                startActivity(i);
                break;
            case R.id.button_my_notifications:
                i = new Intent(this, MyNotificationListActivity.class);
                startActivity(i);
                break;
        }
    }
}
