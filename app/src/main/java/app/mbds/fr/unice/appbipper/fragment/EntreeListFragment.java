package app.mbds.fr.unice.appbipper.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.mbds.fr.unice.appbipper.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntreeListFragment extends Fragment {

    private String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //name = getResources().getString(R.string.title_fragment_entrees);
        this.name = "Entrées"; // TODO

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entree_list, container, false);
    }


    @Override
    public String toString() {
        return name;
    }

}
