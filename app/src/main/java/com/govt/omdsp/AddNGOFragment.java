package com.govt.omdsp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.govt.omdsp.Container.NgoDatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddNGOFragment extends Fragment {
    NgoDatabaseHelper ngoDatabaseHelper;


    public AddNGOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ngo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ngoDatabaseHelper = new NgoDatabaseHelper(getContext());
        final EditText nameet = view.findViewById(R.id.addNGOname);
        final EditText addresset = view.findViewById(R.id.addNGOAddress);
        updateNGOList(view);
        Button addNGO = view.findViewById(R.id.addNGOButton);
        addNGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameet.getText().toString();
                String address = addresset.getText().toString();
                boolean res = ngoDatabaseHelper.insertData(name, address);
                if (res) Snackbar.make(v, "Successful", Snackbar.LENGTH_SHORT).show();
                else Snackbar.make(v, "Failed", Snackbar.LENGTH_SHORT).show();
                updateNGOList(view);
            }
        });

    }

    public void updateNGOList(View v) {
        ArrayList<HashMap<String, String>> ngoList = ngoDatabaseHelper.getAllData();
        ListView lv = v.findViewById(R.id.addNewNGOList);
        ListAdapter adapter = new SimpleAdapter(getContext(), ngoList, R.layout.list_ngo_admin, new String[]{"name", "address"}, new int[]{R.id.ngoName, R.id.ngoAddress});
        lv.setAdapter(adapter);
    }

}
