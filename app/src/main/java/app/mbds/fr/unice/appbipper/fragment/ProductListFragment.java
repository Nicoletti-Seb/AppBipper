package app.mbds.fr.unice.appbipper.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.adapter.ProductItemAdapter;
import app.mbds.fr.unice.appbipper.entity.Product;
import app.mbds.fr.unice.appbipper.service.ProductTask;

public class ProductListFragment extends Fragment implements AdapterView.OnItemClickListener{

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
        listView = (ListView)view.findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "Selected position " + position);
        if(adapter.getChoice() == position){
            adapter.setChoice(-1);
        }else{
            adapter.setChoice(position);
            view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    public Product getProductSelected(){
        if(adapter.getChoice() < 0 ){
            return null;
        }
        return products.get(adapter.getChoice());
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
