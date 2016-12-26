package app.mbds.fr.unice.appbipper;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.ProductListAdapter;
import app.mbds.fr.unice.appbipper.fragment.ProductListFragment;

public class ProductListActivity extends FragmentActivity {

    private ProductListAdapter productListAdapter;

    private ViewPager viewPager;

    /* Fragments */
    private List<ProductListFragment> productFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        //init fragments
        initFragments();

        //init list
        productListAdapter = new ProductListAdapter(getSupportFragmentManager(), productFragments);
        viewPager = (ViewPager) findViewById(R.id.product_list_layout);
        viewPager.setAdapter(productListAdapter);
    }


    private void initFragments(){
        productFragments = new ArrayList<>();
        String[] productTypes = getResources().getStringArray(R.array.name_type_products);
        String url = getResources().getString(R.string.url_server);
        String service = getResources().getString(R.string.url_service_product_type);

        for(String type : productTypes){
            String productUrl = url + String.format(service, type);
            ProductListFragment f = ProductListFragment.newInstance(type, productUrl);
            productFragments.add(f);
        }
    }

}
