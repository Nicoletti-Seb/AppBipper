package app.mbds.fr.unice.appbipper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.List;

import app.mbds.fr.unice.appbipper.fragment.ProductListFragment;

/**
 * Created by 53js-Seb on 21/11/2016.
 */

public class ProductListAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "ProductListAdapter";

    private List<ProductListFragment> fragments;

    public ProductListAdapter(FragmentManager fm, List<ProductListFragment> fragments){
        super(fm);
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getName();
    }
}
