package com.example.note_cryptv2;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private BiometricPrompt biometricPrompt;
    private Executor executor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton login_btn = (MaterialButton) findViewById(R.id.login_btn);

        executor = Executors.newSingleThreadExecutor();
        biometricPrompt = new BiometricPrompt((FragmentActivity) this,
                executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Intent i = new Intent(MainActivity.this, TextPad.class);
                        startActivity(i);
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(MainActivity.this, "Authentication failed, please try again", Toast.LENGTH_SHORT).show();
                    }
                });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                        .setTitle("ID Authentication")
                        .setSubtitle("Please use your Device's Fingerprint, Pin or Pattern to access the App.")
                        .setDeviceCredentialAllowed(true)
                        //.setNegativeButtonText("Cancel")
                        .setConfirmationRequired(false)
                        .build();
                biometricPrompt.authenticate(promptInfo);
            }
        });
        MaterialButton login_btn_2 = (MaterialButton) findViewById(R.id.login_btn_2);
        login_btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TextPad_Basic.class);
                startActivity(i);
            }
        });
    }
}