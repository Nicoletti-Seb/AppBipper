package app.mbds.fr.unice.appbipper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;
import java.util.Map;

import app.mbds.fr.unice.appbipper.entity.Product;

/**
 * Created by 53js-Seb on 21/11/2016.
 */

public class ProductFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private List<String> titles;

    public ProductFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles;){
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }
}
