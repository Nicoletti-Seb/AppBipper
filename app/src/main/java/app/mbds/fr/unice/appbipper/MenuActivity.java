package app.mbds.fr.unice.appbipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button gotolist = (Button)findViewById(R.id.button_list);
        gotolist.setOnClickListener(this);

        Button btnCreateMenu = (Button)findViewById(R.id.button_create_menu);
        btnCreateMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_list:
                startActivity(new Intent(this, ListActivity.class));
                break;
            case R.id.button_create_menu:
                startActivity(new Intent(this, OrderActivity.class));
                break;
        }
    }
}
