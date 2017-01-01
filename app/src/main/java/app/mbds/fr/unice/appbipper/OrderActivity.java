package app.mbds.fr.unice.appbipper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import app.mbds.fr.unice.appbipper.entity.Person;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String PARAM_USER = "PARAM_USER";

    //Model
    private Person user;

    //View
    private ListView orderList;
    private ImageButton btnAddProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (Person)getIntent().getSerializableExtra(PARAM_USER);

        setContentView(R.layout.activity_order);
        setTitle(R.string.order_title);

        //init list
        orderList = (ListView)findViewById(R.id.menu_order_list);

        //init listener button
        btnAddProducts = (ImageButton) findViewById(R.id.menu_order_add);
        btnAddProducts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.menu_order_add){
            Intent intent = new Intent(this, CreateMenuActivity.class);
            intent.putExtra(CreateMenuActivity.PARAM_USER, user);
            startActivity(intent);
        }
    }
}
