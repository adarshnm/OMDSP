package com.govt.omdsp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.govt.omdsp.Container.DonationsDBHelper;
import com.govt.omdsp.Container.NgoDatabaseHelper;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonateFragment extends Fragment {
    private static String username, name;
    NgoDatabaseHelper ngoDatabaseHelper;
    DonationsDBHelper dbHelper;
    Spinner spinner;
    Button requestPickup;
    EditText tabletET, syrupET, sanitET, othET;



    public DonateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_donate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ngoDatabaseHelper = new NgoDatabaseHelper(getContext());
        dbHelper = new DonationsDBHelper(getContext());
        spinner = view.findViewById(R.id.ngoSpinner);
        loadNGO();
        name = getArguments().getString("name", "User");
        username = getArguments().getString("username", "User");
        tabletET = view.findViewById(R.id.editTxtTablet);
        syrupET = view.findViewById(R.id.editTxtSyrup);
        sanitET = view.findViewById(R.id.editTxtSanitary);
        othET = view.findViewById(R.id.editTxtOthers);
        requestPickup = view.findViewById(R.id.requestPickupBtn);
        requestPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tablet = tabletET.getText().toString();
                String syrup = syrupET.getText().toString();
                String sanitary = sanitET.getText().toString();
                String other = othET.getText().toString();
                String ngo = spinner.getSelectedItem().toString();
                if (tablet.isEmpty() || syrup.isEmpty() || sanitary.isEmpty() || other.isEmpty())
                    Snackbar.make(v, "Fields are empty", Snackbar.LENGTH_SHORT).show();
                else {
                    boolean res = dbHelper.insertDonor(name, username, ngo, tablet, syrup, sanitary, other);
                    if (res) {
                        Snackbar.make(v, "Your Package has initiated for pickup", Snackbar.LENGTH_SHORT).show();
                    } else
                        Snackbar.make(v, "Pickup Initiation failed", Snackbar.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void loadNGO() {
        List<String> ngoList = ngoDatabaseHelper.getNGO();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(Objects.requireNonNull(getContext()), android.R.layout.simple_spinner_item, ngoList);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }



}
