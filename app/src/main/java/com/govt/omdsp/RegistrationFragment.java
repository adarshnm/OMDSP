package com.govt.omdsp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;
import com.govt.omdsp.Container.UserDatabaseHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {
    UserDatabaseHelper db;
    EditText nameEditText, usernameEditText, phoneEditText, passwordEditText, confPasswordEditText, addressEditText;
    Button submitButton;
    Spinner stateSpinner;
    RadioGroup genderGroup;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registration, container, false);

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        db = new UserDatabaseHelper(getContext());
        final NavController navController = Navigation.findNavController(view);
        nameEditText = view.findViewById(R.id.editTextName);
        usernameEditText = view.findViewById(R.id.editTextUserName);
        phoneEditText = view.findViewById(R.id.editTextPhone);
        passwordEditText = view.findViewById(R.id.editTextPassword);
        confPasswordEditText = view.findViewById(R.id.editTextConfirmPassword);
        addressEditText = view.findViewById(R.id.editTextAddress);
        stateSpinner = view.findViewById(R.id.states);
        genderGroup = view.findViewById(R.id.gender);
        submitButton = view.findViewById(R.id.submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confPass = confPasswordEditText.getText().toString();
                String address = addressEditText.getText().toString();
                String state = stateSpinner.getSelectedItem().toString();
                RadioButton genderRadio = view.findViewById(genderGroup.getCheckedRadioButtonId());
                String gender = genderRadio.getText().toString();
                if (name.isEmpty() || username.isEmpty() || phone.isEmpty() || password.isEmpty() || confPass.isEmpty() || address.isEmpty()) {
                    Snackbar.make(view, "Invalid fields/Fields empty", Snackbar.LENGTH_SHORT).show();
                } else if (username.contains(" ")) {
                    Snackbar.make(view, "Username should not contain space", Snackbar.LENGTH_SHORT).show();
                } else if (db.checkUserExist(username)) {
                    Snackbar.make(view, "Username already taken", Snackbar.LENGTH_SHORT).show();
                } else if (!confPass.equals(password)) {
                    Snackbar.make(view, "Passwords doesn't match", Snackbar.LENGTH_SHORT).show();
                } else {
                    boolean res = db.insertUser(username, name, phone, password, gender, address, state);
                    if (res) {
                        Snackbar.make(view, "Registration Successful", Snackbar.LENGTH_SHORT).show();
                    } else
                        Snackbar.make(view, "Registration failed", Snackbar.LENGTH_SHORT).show();
                }

            }
        });


    }

}
