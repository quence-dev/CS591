package com.example.ch4_q33;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String ToBeEncrypted;
    private int offset;
    private Button Encryptbtn;
    private EditText message;
    private TextView modulo_message;
    private TextView cipher_output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        if (i.hasExtra("Offset")){
            this.offset = i.getIntExtra("Offset",offset);
        }

        Encryptbtn = (Button) findViewById(R.id.encrypt_btn);
        message = (EditText) findViewById(R.id.message_to_encrypt);
        modulo_message = (TextView) findViewById(R.id.modulo_shift_text);
        cipher_output = (TextView) findViewById(R.id.Encrypted_Result);

        modulo_message.setText("current_shift_modulized = " + offset);

        Encryptbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                ToBeEncrypted = message.getText().toString();
                if(ToBeEncrypted.equals("")){
                    Toast.makeText(getBaseContext(), "Please input phrase to be encrypted",Toast.LENGTH_LONG).show();
                } else {
                    System.out.println("button clicked");
                    System.out.println("Here's our Message: " + ToBeEncrypted);
                    ToBeEncrypted = encrypt(ToBeEncrypted,offset).toString();
                    cipher_output.setText(ToBeEncrypted);
                }
            }
        });


    }


    public static StringBuffer encrypt(String text, int shift) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < text.length(); i++) {
            if (Character.isSpaceChar(text.charAt(i))){
                result.append(" ");
            } else
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char) (((int) text.charAt(i) +
                        shift - 65) % 26 + 65);
                System.out.println("appending ^ " + ch);
                result.append(ch);
            } else {
                char ch = (char) (((int) text.charAt(i) +
                        shift - 97) % 26 + 97);
                System.out.println("appending " + ch);
                result.append(ch);
            }
        }
        System.out.println(" Total string returned: " + result);
        return result;
    }
}