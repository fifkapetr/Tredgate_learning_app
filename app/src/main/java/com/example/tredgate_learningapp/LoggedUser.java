package com.example.tredgate_learningapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedUser extends AppCompatActivity {

    private TextView logout;
    private String tredgate;
    private Button changePin;
    private Button toastButton;
    private Button nothingButton;
    private Button logdButton;
    public static int nothingClicked = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        nothingClicked = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_user);
        logout = findViewById(R.id.logout);
        changePin = findViewById(R.id.change_pin_button);
        tredgate = "Tredgate";
        toastButton = findViewById(R.id.toast_msg_button);
        nothingButton = findViewById(R.id.nothing_button);
        logdButton = findViewById(R.id.logd_button);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tredgate, "Logout successful");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tredgate, "Logout successful");
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        changePin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tredgate, "Change pin button click()");
//                throw new RuntimeException("Error, function not implemented");
                Intent intent = new Intent(getApplicationContext(), ChangePassword.class);
                startActivity(intent);
            }
        });

        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tredgate, "Toast message debug displayed");
                Toast.makeText(getApplicationContext(), "DEBUG: toto je toast", Toast.LENGTH_LONG).show();
            }
        });

        logdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(tredgate, "DEBUG: logd zpráva");
            }
        });

        nothingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nothingClicked++;
                if(nothingClicked == 1) {
                    nothingButton.setText("NIC NEDĚLÁM...");
                }
                else if(nothingClicked == 3)
                    Toast.makeText(getApplicationContext(), "DEBUG: marná snaha, nic nedělám, neklikej na mě!", Toast.LENGTH_LONG).show();
                else if(nothingClicked == 6)
                {
                    nothingButton.setBackgroundColor(getResources().getColor(R.color.red));
                    nothingButton.setText("!!!NIC NEDĚLÁM!!!");
                }
                else if (nothingClicked == 9){
                    nothingButton.setVisibility(View.INVISIBLE);
                    Log.d(tredgate, "easter egg :)");
                }
            }
        });
    }
}