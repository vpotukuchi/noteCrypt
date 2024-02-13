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

public class TextPad_Basic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_pad_basic);

        final MaterialButton encryptBtn = (MaterialButton) findViewById(R.id.encrypt_btn_1);
        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText noteText = (EditText) findViewById(R.id.text_body_1);
                String textToEncrypt = noteText.getText().toString();
                try {
                    byte[] key = "qwertyuiopasdfghjklzxcvbnm123456".getBytes("UTF-8");
                    SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
                    byte[] encryptedText = cipher.doFinal(textToEncrypt.getBytes());
                    String finalEncryptedText = Base64.encodeToString(encryptedText, Base64.DEFAULT);
                    noteText.setText(finalEncryptedText);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TextPad_Basic.this, "Encryption failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final MaterialButton decryptBtn = (MaterialButton) findViewById(R.id.decrypt_btn_1);
        decryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText noteText = (EditText) findViewById(R.id.text_body2_1);
                String textToDecrypt = noteText.getText().toString();
                try {
                    byte[] key = "qwertyuiopasdfghjklzxcvbnm123456".getBytes("UTF-8");
                    SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
                    Cipher cipher = Cipher.getInstance("AES");
                    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                    byte[] decodedEncryptedText = Base64.decode(textToDecrypt, Base64.DEFAULT);
                    byte[] decryptedText = cipher.doFinal(decodedEncryptedText);
                    String finalDecryptedText = new String(decryptedText);
                    noteText.setText(finalDecryptedText);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(TextPad_Basic.this, "Decryption failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
