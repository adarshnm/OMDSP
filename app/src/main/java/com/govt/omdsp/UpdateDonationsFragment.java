package com.govt.omdsp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.govt.omdsp.Container.DonationsDBHelper;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateDonationsFragment extends Fragment {
    DonationsDBHelper dbHelper;


    public UpdateDonationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_donations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dbHelper = new DonationsDBHelper(getContext());
        ArrayList<HashMap<String, String>> userList = dbHelper.GetDonations();
        ListView listView = view.findViewById(R.id.updateDonationsUserList);
        listView.setAdapter(new CustomAdapter(userList, getContext()));
    }
}
