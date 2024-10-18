package com.example.cosahorrosa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText newUsername, newPassword;
    Button registerNewUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newUsername = findViewById(R.id.new_username);
        newPassword = findViewById(R.id.new_password);
        registerNewUserButton = findViewById(R.id.register_new_user_button);

        registerNewUserButton.setOnClickListener(v -> {
            String newUser = newUsername.getText().toString();
            String newPass = newPassword.getText().toString();
            //Aquí se guardan los datos
            Toast.makeText(this,"Usuario registrado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }

}
