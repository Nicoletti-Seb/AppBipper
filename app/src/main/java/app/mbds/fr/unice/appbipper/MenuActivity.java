package app.mbds.fr.unice.appbipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.mbds.fr.unice.appbipper.entity.Person;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PARAM_USER = "PARAM_USER";

    private Person user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        user = (Person)getIntent().getSerializableExtra(PARAM_USER);

        Button gotolist = (Button)findViewById(R.id.button_list);
        gotolist.setOnClickListener(this);

        Button btnCreateMenu = (Button)findViewById(R.id.button_create_menu);
        btnCreateMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_list:
                startActivity(new Intent(this, ServerActivity.class));
                break;
            case R.id.button_create_menu:
                Intent i = new Intent(this, MenuListActivity.class);
                i.putExtra(MenuListActivity.PARAM_USER, user);
                startActivity(i);
                break;
        }
    }
}
