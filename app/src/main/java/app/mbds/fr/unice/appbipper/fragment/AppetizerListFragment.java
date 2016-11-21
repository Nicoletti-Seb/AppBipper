package app.mbds.fr.unice.appbipper.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.entity.Product;

public class AppetizerListFragment extends Fragment {

    private List<Product> products;

    private String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //this.name = getResources().getString(R.string.title_fragment_appetizer);
        this.name = "Appétitifs"; // TODO

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appetizer_list, container, false);
    }


    public List<Product> getProducts(){
        return products;
    }

    @Override
    public String toString() {
        return name;
    }
}
