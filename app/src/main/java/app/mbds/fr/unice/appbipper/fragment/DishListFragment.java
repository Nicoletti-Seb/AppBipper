 package app.mbds.fr.unice.appbipper.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.mbds.fr.unice.appbipper.R;

public class DishListFragment extends Fragment {

    private String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //name = getResources().getString(R.string.title_fragment_dishes);
        this.name = "Plats"; // TODO

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dish_list, container, false);
    }

    @Override
    public String toString() {
        return name;
    }

}
