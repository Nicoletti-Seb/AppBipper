package app.mbds.fr.unice.appbipper;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import app.mbds.fr.unice.appbipper.adapter.DescMenuItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Menu;
import app.mbds.fr.unice.appbipper.entity.Product;

public class MenuDescActivity extends ListActivity {

    public static final String PARAM_MENU = "PARAM_MENU";

    //Model
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        menu = (Menu) getIntent().getSerializableExtra(PARAM_MENU);

        setContentView(R.layout.activity_desc_menu);

        //init list
        DescMenuItemAdapter adapter = new DescMenuItemAdapter(this, menu.getItems());
        setListAdapter(adapter);


        //Init view
        //Server or cooker can be null
        String nameServer = (menu.getServer() != null)? menu.getServer().getFullName(): " --";
        String nameCooker = (menu.getCooker() != null)? menu.getCooker().getFullName(): " --";

        TextView number = (TextView) findViewById(R.id.menu_desc_number);
        number.setText(menu.getId());

        TextView server = (TextView) findViewById(R.id.menu_desc_server);
        server.setText(nameServer);

        TextView cooker = (TextView) findViewById(R.id.menu_desc_cooker);
        cooker.setText(nameCooker);

        TextView discount = (TextView) findViewById(R.id.menu_desc_total_discount);
        discount.setText(String.valueOf(menu.getDiscount()));

        TextView price = (TextView) findViewById(R.id.menu_desc_total_price);
        price.setText(String.valueOf(menu.getPrice()));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Product product = menu.getItems().get(position);

        Intent i = new Intent(this, DescProduct.class);
        i.putExtra(DescProduct.PARAM_PRODUCT, product);
        startActivity(i);
    }
}
