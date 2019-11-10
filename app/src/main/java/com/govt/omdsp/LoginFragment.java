package com.govt.omdsp;


import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
public class LoginFragment extends Fragment {
    EditText usernameET, passwordET;


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final UserDatabaseHelper db = new UserDatabaseHelper(getContext());
        final NavController navController = Navigation.findNavController(view);
        TextView registerButton = view.findViewById(R.id.textViewRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.toRegistration_From_Login);
            }
        });
        usernameET = view.findViewById(R.id.editTextLoginUsername);
        passwordET = view.findViewById(R.id.editTextLoginPass);
        Button loginButton = view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameET.getText().toString();
                String password = passwordET.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Snackbar.make(v, "Invalid fields/Fields empty", Snackbar.LENGTH_SHORT).show();
                } else if (username.equals("admin") && password.equals("qwerty"))
                    navController.navigate(R.id.toAdminPage);
                else {
                    Cursor res = db.getUser(username, password);
                    if (res.getCount() == 0)
                        Snackbar.make(v, "Username and Password doesn't match", Snackbar.LENGTH_SHORT).show();
                    else {

                        String name = "User";
                        while (res.moveToNext()) {
                            name = res.getString(0);
                        }
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("username", username);
                        navController.navigate(R.id.toUserPage, bundle);
                    }
                }

            }
        });

    }

}
