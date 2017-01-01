package app.mbds.fr.unice.appbipper;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.MenuItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Menu;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.MenuTask;

public class MenuListActivity extends ListActivity implements View.OnClickListener{

    private static final String TAG = "MenuListActivity";

    public static final String PARAM_USER = "PARAM_USER";


    //Model
    private Person user;
    private List<Menu> menus = new ArrayList<>();

    //View
    private ImageButton btnAddProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (Person)getIntent().getSerializableExtra(PARAM_USER);

        setContentView(R.layout.activity_menu_list);
        setTitle(R.string.list_menu_title);

        //Init list
        MenuItemAdapter adapter = new MenuItemAdapter(this, menus);
        setListAdapter(adapter);
        new MenuTask(adapter, this).execute();

        //init listener button
        btnAddProducts = (ImageButton) findViewById(R.id.menu_List_add);
        btnAddProducts.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.menu_List_add){
            Intent intent = new Intent(this, CreateMenuActivity.class);
            intent.putExtra(CreateMenuActivity.PARAM_USER, user);
            startActivity(intent);
        }
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        //TODO describe menu
        Log.i(TAG, "Click " + position);
    }
}
