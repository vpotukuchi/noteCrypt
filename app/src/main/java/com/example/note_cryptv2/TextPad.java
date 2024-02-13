package com.example.note_cryptv2;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import android.util.Base64;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.google.android.material.button.MaterialButton;

public class TextPad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_pad);

        final MaterialButton encryptBtn = (MaterialButton) findViewById(R.id.encrypt_btn);
        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText keyInput = (EditText) findViewById(R.id.key_input);
                String key = keyInput.getText().toString();
                if (key.isEmpty()) {
                    Toast.makeText(TextPad.this, "Please enter a key before encrypting!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (key.length() < 32) {
                    Toast.makeText(TextPad.this, "Key must be at least 32 characters long!", Toast.LENGTH_SHORT).show();
                    return;
                } else{
                    EditText noteText = (EditText) findViewById(R.id.text_body);
                String textToEncrypt = noteText.getText().toString();
                try {
                    byte[] keyBytes = key.getBytes("UTF-8");
                    SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                    byte[] encryptedText = cipher.doFinal(textToEncrypt.getBytes());
                    String finalEncryptedText = Base64.encodeToString(encryptedText, Base64.DEFAULT);
                    noteText.setText(finalEncryptedText);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TextPad.this, "Encryption failed!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        });

        final MaterialButton decryptBtn = (MaterialButton) findViewById(R.id.decrypt_btn);
        decryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText noteText = (EditText) findViewById(R.id.text_body2);
                EditText keyText = (EditText) findViewById(R.id.key_input);
                String textToDecrypt = noteText.getText().toString();
                String key = keyText.getText().toString();
                if (key.isEmpty()) {
                    Toast.makeText(TextPad.this, "Please enter a key before decrypting!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (key.length() < 32) {
                    Toast.makeText(TextPad.this, "Key must be at least 32 characters long!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        byte[] keyBytes = key.getBytes("UTF-8");
                        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");
                        Cipher cipher = Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                        byte[] decodedEncryptedText = Base64.decode(textToDecrypt, Base64.DEFAULT);
                        byte[] decryptedText = cipher.doFinal(decodedEncryptedText);
                        String finalDecryptedText = new String(decryptedText);
                        noteText.setText(finalDecryptedText);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(TextPad.this, "Decryption failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}