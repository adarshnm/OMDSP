package com.govt.omdsp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewDonationsFragment extends Fragment {


    public ViewDonationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_donations, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<HashMap<String,String>> userList = new ArrayList<>();
        HashMap<String,String> user = new HashMap<>();
        user.put("name","Name1");
        user.put("designation","Designation1");
        user.put("location","Location 1");
        userList.add(user);
        HashMap<String,String> user2 = new HashMap<>();
        user2.put("name","Name2");
        user2.put("designation","Designation2");
        user2.put("location","Location 2");
        userList.add(user2);
        ListView listView = view.findViewById(R.id.viewDonationsUserList);
        ListAdapter adapter = new SimpleAdapter(getContext(),userList,R.layout.list_user,new String[]{"name","designation","location"},new int[]{R.id.name,R.id.designation,R.id.location});
        listView.setAdapter(adapter);

    }
}
