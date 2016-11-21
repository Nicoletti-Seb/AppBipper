package app.mbds.fr.unice.appbipper;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.adapter.ProductListAdapter;
import app.mbds.fr.unice.appbipper.fragment.AppetizerListFragment;
import app.mbds.fr.unice.appbipper.fragment.DessertsListFragment;
import app.mbds.fr.unice.appbipper.fragment.DishListFragment;
import app.mbds.fr.unice.appbipper.fragment.EntreeListFragment;

public class ProductListActivity extends FragmentActivity {

    private ProductListAdapter productListAdapter;

    private ViewPager viewPager;

    /* Fragments */
    private AppetizerListFragment appetizerListFragment;
    private DessertsListFragment dessertsListFragment;
    private DishListFragment dishListFragment;
    private EntreeListFragment entreeListFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);


        //init fragments
        initFragments();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(appetizerListFragment);
        fragments.add(dessertsListFragment);
        fragments.add(dishListFragment);
        fragments.add(entreeListFragment);

        //init list
        productListAdapter = new ProductListAdapter(getSupportFragmentManager(), fragments);
        viewPager = (ViewPager) findViewById(R.id.product_list_layout);
        viewPager.setAdapter(productListAdapter);

    }


    private void initFragments(){
        appetizerListFragment = new AppetizerListFragment();
        dessertsListFragment = new DessertsListFragment();
        dishListFragment = new DishListFragment();
        entreeListFragment = new EntreeListFragment();
    }

    private void initActionBar(List<Fragment> fragments){


    }
}
