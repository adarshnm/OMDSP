package com.govt.omdsp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.govt.omdsp.Container.DonationsDBHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<HashMap<String, String>> list;
    private Context context;
    DonationsDBHelper dbHelper;

    public CustomAdapter(ArrayList<HashMap<String, String>> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_update_donors, null);
        }
        final HashMap<String, String> donor = list.get(position);
        dbHelper = new DonationsDBHelper(context);
        //Handle TextView and display string from your list
        TextView nameET = view.findViewById(R.id.donorName);
        nameET.setText("Name: " + donor.get("name"));
        TextView usernameET = view.findViewById(R.id.donorUsername);
        usernameET.setText("Username: " + donor.get("username"));
        TextView ngoET = view.findViewById(R.id.donorNGO);
        ngoET.setText("NGO: " + donor.get("ngo"));
        TextView dateET = view.findViewById(R.id.donorDate);
        dateET.setText("Date: " + donor.get("date"));
        TextView tabET = view.findViewById(R.id.donorTablet);
        tabET.setText("Tablets: " + donor.get("tablets"));
        TextView syrET = view.findViewById(R.id.donorSyrup);
        syrET.setText("Syrup: " + donor.get("syrups"));
        TextView sanET = view.findViewById(R.id.donorSanitary);
        sanET.setText("Sanitary: " + donor.get("sanitary"));
        TextView othET = view.findViewById(R.id.donorOther);
        othET.setText("Others: " + donor.get("other"));
        final TextView statusET = view.findViewById(R.id.donorStatus);
        statusET.setText("Status: " + donor.get("status"));


        //Handle buttons and add onClickListeners
        Button callbtn = view.findViewById(R.id.updateDonationBtn);

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean res = dbHelper.updateDonorStatus(donor.get("id"));
                if (res) {
                    Snackbar.make(v, "Successful", Snackbar.LENGTH_SHORT).show();
                    statusET.setText("Status: Picked");
                } else Snackbar.make(v, "Failed", Snackbar.LENGTH_SHORT).show();

            }
        });

        return view;
    }
}
