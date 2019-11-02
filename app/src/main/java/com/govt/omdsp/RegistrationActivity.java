package com.govt.omdsp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class RegistrationActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_STATE = "state";


    EditText name, email, phone, password, confPassword, address;
    Button submitButton;
    Spinner stateSpinner;
    RadioGroup genderGroup;
    ImageView backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        name = findViewById(R.id.editTextName);
        email = findViewById(R.id.editTextEmail);
        phone = findViewById(R.id.editTextPhone);
        password = findViewById(R.id.editTextPassword);
        confPassword = findViewById(R.id.editTextConfirmPassword);
        address = findViewById(R.id.editTextAddress);
        stateSpinner = findViewById(R.id.states);
        genderGroup = findViewById(R.id.gender);
        submitButton = findViewById(R.id.submit);
        backbutton = findViewById(R.id.backbuttonReg);
        backButton_Click();

    }

    public void backButton_Click() {
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void registerUser(View view) {
    }
}
