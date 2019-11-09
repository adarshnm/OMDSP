package com.govt.omdsp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DonateFragment extends Fragment {
    private static Integer editTextNo = 2;
    private static int aboveitemId;


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
        Button addItembtn = view.findViewById(R.id.addItemBtn);
        final EditText itemname = view.findViewById(R.id.itemname1);
        final RelativeLayout itemLayout = view.findViewById(R.id.itemsLayout);
        aboveitemId = R.id.itemname1;
        addItembtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200,40);
                params.addRule(RelativeLayout.BELOW,aboveitemId);
                params.setMargins(10,0,0,15);
                EditText text = new EditText(getContext());
                text.setLayoutParams(params);
                text.setHint(""+editTextNo);
                text.setId(editTextNo);
                text.setWidth(itemname.getWidth());
                text.setHeight(itemname.getHeight());
                aboveitemId = text.getId();
                text.setBackground(getResources().getDrawable(R.drawable.inputboxgrey,null));
                itemLayout.addView(text);
                editTextNo++;

            }
        });
    }
}
