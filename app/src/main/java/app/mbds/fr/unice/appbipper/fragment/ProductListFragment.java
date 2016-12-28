package app.mbds.fr.unice.appbipper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.adapter.ProductItemAdapter;
import app.mbds.fr.unice.appbipper.adapter.ProductListAdapter;
import app.mbds.fr.unice.appbipper.entity.Product;
import app.mbds.fr.unice.appbipper.service.ProductTask;

public class ProductListFragment extends Fragment {

    private static final String TAG = "ProductListFragment";

    //Model
    private List<Product> products;
    private ProductItemAdapter adapter;
    private String name;

    //View
    private ListView listView;

    public static ProductListFragment newInstance(String name) {
        ProductListFragment myFragment = new ProductListFragment();
        myFragment.setName(name);
        return myFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        products = new ArrayList<>();
        adapter = new ProductItemAdapter(getContext(), products);
        new ProductTask(name, adapter, getContext()).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        listView = (ListView)view.findViewById(R.id.product_list);
        listView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
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
