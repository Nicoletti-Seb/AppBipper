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


    public EntreeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entree_list, container, false);
    }

}
