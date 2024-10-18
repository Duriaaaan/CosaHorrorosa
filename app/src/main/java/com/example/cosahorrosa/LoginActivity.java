package com.example.cosahorrosa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity{
   EditText username, password;
   Button loginbutton, registerButton;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_login);

       username = findViewById(R.id.username);
       password = findViewById(R.id.password);
       loginbutton = findViewById(R.id.login_button);
       registerButton = findViewById(R.id.register_button);

       //boton de inicio de sesión
       loginbutton.setOnClickListener(v -> {
           String user = username.getText().toString();
           String pass = password.getText().toString();
           //validar usuario y contraseña
           if (user.equals("admin") && pass.equals("admin")){
               Intent intent = new Intent(LoginActivity.this, MainActivity.class);
               startActivity(intent);
           }else{
               Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
           }
       });

       //Boton de registro
       registerButton.setOnClickListener(v ->{
           Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
           startActivity(intent);
       });
   }
}

