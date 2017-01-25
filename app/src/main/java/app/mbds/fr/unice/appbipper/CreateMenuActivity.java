package app.mbds.fr.unice.appbipper;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.ProductListAdapter;
import app.mbds.fr.unice.appbipper.entity.Menu;
import app.mbds.fr.unice.appbipper.entity.Person;
import app.mbds.fr.unice.appbipper.entity.Product;
import app.mbds.fr.unice.appbipper.fragment.ProductListFragment;
import app.mbds.fr.unice.appbipper.service.CreateMenuTask;

public class CreateMenuActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int REQUEST_CODE_ASK_PERSON = 0;

    private Person user;

    private ProductListAdapter productListAdapter;

    private ViewPager viewPager;

    /* Fragments */
    private List<ProductListFragment> productFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = ((BipperApplication)getApplication()).getUser();

        setContentView(R.layout.activity_product_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //init fragments
        initFragments();

        //init list
        productListAdapter = new ProductListAdapter(getSupportFragmentManager(), productFragments);
        viewPager = (ViewPager) findViewById(R.id.product_list_layout);
        viewPager.setAdapter(productListAdapter);
        viewPager.setOffscreenPageLimit(productFragments.size()); //Keep all fragment

        //init listener
        Button button = (Button) findViewById(R.id.btn_validate_products);
        button.setOnClickListener(this);
    }


    private void initFragments(){
        productFragments = new ArrayList<>();
        String[] productTypes = getResources().getStringArray(R.array.name_type_products);
        for(String type : productTypes){
            ProductListFragment f = ProductListFragment.newInstance(type);
            productFragments.add(f);
        }
    }

    @Override
    public void onClick(View v) {
        if(R.id.btn_validate_products == v.getId()){
            Intent i = new Intent(this, PersonListActivity.class);
            startActivityForResult(i, REQUEST_CODE_ASK_PERSON);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == REQUEST_CODE_ASK_PERSON && resultCode == Activity.RESULT_OK ){
            Person chef = (Person)data.getSerializableExtra(PersonListActivity.RESULT_PERSON);
            float price = 0f;
            float discount = 0f;
            List<Product> products = new ArrayList<>();
            for(ProductListFragment f : productFragments){
                Product p = f.getProductSelected();
                if(p == null){
                    continue;
                }

                products.add(p);
                price += p.getPrice();
                discount += p.getDiscount();
            }

            Menu menu = new Menu();
            menu.setCooker(chef);
            menu.setServer(user);
            menu.setItems(products);
            menu.setPrice(price);
            menu.setDiscount(discount);

            new CreateMenuTask(this).execute(menu);
            finish();
        }
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
