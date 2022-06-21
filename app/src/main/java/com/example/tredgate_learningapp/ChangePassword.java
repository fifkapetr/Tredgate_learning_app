package com.example.tredgate_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePassword extends AppCompatActivity {

    private Button changePinButton;
    private EditText oldPin;
    private EditText newPin;
    private EditText confirmedNewPin;
    private TextView validationErrorTextView;
    private String validationMsg = "ERROR";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);

        changePinButton = findViewById(R.id.change_password_submit);
        oldPin = findViewById(R.id.current_password_input);
        newPin = findViewById(R.id.new_password_input);
        confirmedNewPin = findViewById(R.id.confirm_password_input);
        validationErrorTextView = findViewById(R.id.val_error_text);

        changePinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validatePin(oldPin.getText().toString(), newPin.getText().toString(), confirmedNewPin.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "PIN changed", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoggedUser.class);
                    startActivity(intent);
                } else {
                    validationErrorTextView.setVisibility(View.VISIBLE);
                    validationErrorTextView.setText(validationMsg);
                }
            }
        });
    }

    private boolean validatePin(String oldPin, String newPin, String confirmedPin) {
        boolean validated = false;
        if (!oldPin.equals("123456")) {
            validationMsg = "The current PIN is incorrect";
        } else if (oldPin.equals(newPin)) {
            validationMsg = "Stávající PIN je shodný s novým PIN";
        } else if (!confirmedPin.equals(newPin)) {
            validationMsg = "The current PIN is the same as the new PIN";
        } else if (!newPin.matches("\\d{6,6}")) {
            validationMsg = "The new PIN is not a number or has 6 numbers";
        } else {
            validated = true;
        }
        return validated;
    }
    //regex: "\\d{6,6}"
}
