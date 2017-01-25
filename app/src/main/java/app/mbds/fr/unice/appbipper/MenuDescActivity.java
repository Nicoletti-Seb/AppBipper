package app.mbds.fr.unice.appbipper;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import app.mbds.fr.unice.appbipper.adapter.DescMenuItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Menu;
import app.mbds.fr.unice.appbipper.entity.Product;

public class MenuDescActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    public static final String PARAM_MENU = "PARAM_MENU";

    //Model
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menu = (Menu) getIntent().getSerializableExtra(PARAM_MENU);

        setContentView(R.layout.activity_desc_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init list
        DescMenuItemAdapter adapter = new DescMenuItemAdapter(this, menu.getItems());
        ListView listView = (ListView)findViewById(android.R.id.list);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);


        //Init view
        //Server or cooker can be null
        String nameServer = (menu.getServer() != null)? menu.getServer().getFullName(): " --";
        String nameCooker = (menu.getCooker() != null)? menu.getCooker().getFullName(): " --";

        TextView number = (TextView) findViewById(R.id.menu_number);
        number.setText(menu.getId());

        TextView server = (TextView) findViewById(R.id.menu_server);
        server.setText(nameServer);

        TextView cooker = (TextView) findViewById(R.id.menu_cooker);
        cooker.setText(nameCooker);

        TextView discount = (TextView) findViewById(R.id.menu_desc_total_discount);
        discount.setText(String.valueOf(menu.getDiscount()));

        TextView price = (TextView) findViewById(R.id.menu_desc_total_price);
        price.setText(String.valueOf(menu.getPrice()));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product product = menu.getItems().get(position);

        Intent i = new Intent(this, DescProduct.class);
        i.putExtra(DescProduct.PARAM_PRODUCT, product);
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
