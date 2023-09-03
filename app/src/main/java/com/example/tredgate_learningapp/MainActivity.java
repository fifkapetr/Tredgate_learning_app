package com.example.tredgate_learningapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText username;
    private EditText pin;
    private TextView err_msg;
    private String usernameValue;
    private String pinValue;
    private String tredgate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseApp.initializeApp(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.submit_button);
        username = findViewById(R.id.username_field);
        pin = findViewById(R.id.pin_field);
        err_msg = findViewById(R.id.error_msg_text);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernameValue = username.getText().toString();
                pinValue = pin.getText().toString();
                tredgate = "Tredgate";
                Log.d(tredgate, "Username is: " + usernameValue);
                Log.d(tredgate, "Pin is: " + pinValue);
                if(usernameValue.equals("success_user") && pinValue.equals("123456")) {
                    err_msg.setVisibility(View.INVISIBLE);
                    Log.d(tredgate, "Login successful");
                    Intent intent = new Intent(getApplicationContext(), LoggedUser.class);
                    startActivity(intent);
                }
                else {
                    Log.d(tredgate, "Login failed, wrong username or pin!" + pinValue);
                    err_msg.setVisibility(View.VISIBLE);
                }

            }
        });
    }
}