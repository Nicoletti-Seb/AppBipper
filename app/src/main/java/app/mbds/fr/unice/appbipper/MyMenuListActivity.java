package app.mbds.fr.unice.appbipper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.MenuItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Menu;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.service.MenuTask;
import app.mbds.fr.unice.appbipper.service.MyMenuTask;

public class MyMenuListActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener{

    private static final String TAG = "MyMenuListActivity";

    //Model
    private Person user;
    private List<Menu> menus = new ArrayList<>();

    //View
    private ImageButton btnAddProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((BipperApplication)getApplication()).getUser();

        setContentView(R.layout.error_api);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /* API NOT WORKING */

        //Init list
        /*MenuItemAdapter adapter = new MenuItemAdapter(this, menus);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);


        new MyMenuTask(adapter, this).execute(user.getId());

        //init listener button
        btnAddProducts = (ImageButton) findViewById(R.id.menu_List_add);
        btnAddProducts.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View v) {
        if( v.getId() == R.id.menu_List_add){
            Intent intent = new Intent(this, CreateMenuActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Menu menu = menus.get(position);

        Intent i = new Intent(this, MenuDescActivity.class);
        i.putExtra(MenuDescActivity.PARAM_MENU, menu);
        startActivity(i);
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
