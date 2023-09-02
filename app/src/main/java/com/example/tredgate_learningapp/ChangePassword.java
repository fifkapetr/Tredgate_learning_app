package com.example.tredgate_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePassword extends AppCompatActivity {

    private TextView backButton;
    private Button changePinButton;
    private EditText oldPin;
    private EditText newPin;
    private EditText confirmedNewPin;
    private TextView validationErrorTextView;
    private String validationMsg = "ERROR";
    private String tredgate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        Log.d(tredgate, "Change password layout displayed");
        changePinButton = findViewById(R.id.change_password_submit);
        oldPin = findViewById(R.id.current_password_input);
        newPin = findViewById(R.id.new_password_input);
        confirmedNewPin = findViewById(R.id.confirm_password_input);
        validationErrorTextView = findViewById(R.id.val_error_text);
        backButton = findViewById(R.id.back_button);

        changePinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePin(oldPin.getText().toString(), newPin.getText().toString(), confirmedNewPin.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "PIN changed", Toast.LENGTH_LONG).show();
                    Log.d(tredgate, "PIN changed");
                    Intent intent = new Intent(getApplicationContext(), LoggedUser.class);
                    startActivity(intent);
                } else {
                    validationErrorTextView.setVisibility(View.VISIBLE);
                    validationErrorTextView.setText(validationMsg);
                    Log.d(tredgate, "Validation error message displayed");
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                throw new RuntimeException("Error: Layout state sync failed");
            }
        });
    }

    private boolean validatePin(String oldPin, String newPin, String confirmedPin) {
        boolean validated = false;
        if (!oldPin.equals("123456")) {
            validationMsg = "Stávající PIN není správný";
            Log.w(tredgate, "Current PIN validation failure: PIN is not correct");
        } else if (oldPin.equals(newPin)) {
            validationMsg = "Stávající PIN je shodný s novým PIN";
            Log.w(tredgate, "New PIN validation failure: New PIN equals old PIN");
        } else if (!confirmedPin.equals(newPin)) {
            validationMsg = "Nový PIN není shodný s potvrzeným PIN";
            Log.w(tredgate, "New PIN validation failure: New PIN is not equals with Confirm New PIN");
        } else if (!newPin.matches("\\d{6,6}")) {
            validationMsg = "Nový PIN není číslo nebo nemá 6 čísel";
            Log.w(tredgate, "New PIN validation failure: PIN is not a number or length is less than 6 numbers");
        } else {
            validated = true;
            Log.d(tredgate, "PIN validation: ok");
        }
        return validated;
    }
    //regex: "\\d{6,6}"
}
