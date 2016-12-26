package app.mbds.fr.unice.appbipper.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.entity.Product;

public class ProductListFragment extends Fragment {

    private static final String TAG = "ProductListFragment";
    private static final String KEY_URL = "url";

    private List<Product> products;

    private String name;

    private String url;

    public static ProductListFragment newInstance(String name, String url) {
        ProductListFragment myFragment = new ProductListFragment();
        myFragment.setName(name);

        Bundle args = new Bundle();
        args.putString(KEY_URL, url);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.url = getArguments().getString(KEY_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false);
    }


    public List<Product> getProducts(){
        return products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
