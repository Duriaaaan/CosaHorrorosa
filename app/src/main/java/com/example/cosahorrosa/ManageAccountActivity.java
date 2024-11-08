package com.example.cosahorrosa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ManageAccountActivity extends AppCompatActivity {
    EditText etOldUsername, etNewUsername, etNewPassword;
    Button btnUpdate, btnDelete;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        databaseHelper = new DatabaseHelper(this);

        etOldUsername = findViewById(R.id.et_old_username);
        etNewUsername = findViewById(R.id.et_new_username);
        etNewPassword = findViewById(R.id.et_new_password);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

        // Actualizar usuario
        btnUpdate.setOnClickListener(v -> {
            String oldUsername = etOldUsername.getText().toString();
            String newUsername = etNewUsername.getText().toString();
            String newPassword = etNewPassword.getText().toString();

            if (!oldUsername.isEmpty() && !newUsername.isEmpty() && !newPassword.isEmpty()) {
                boolean isUpdated = databaseHelper.updateUser(oldUsername, newUsername, newPassword);
                if (isUpdated) {
                    Toast.makeText(ManageAccountActivity.this, "Cuenta actualizada correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ManageAccountActivity.this, "Error al actualizar la cuenta", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ManageAccountActivity.this, "Por favor, llena todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

        // Eliminar usuario
        btnDelete.setOnClickListener(v -> {
            String username = etOldUsername.getText().toString();
            if (!username.isEmpty()) {
                boolean isDeleted = databaseHelper.deleteUser(username);
                if (isDeleted) {
                    Toast.makeText(ManageAccountActivity.this, "Cuenta eliminada correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ManageAccountActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ManageAccountActivity.this, "Error al eliminar la cuenta", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(ManageAccountActivity.this, "Por favor, ingresa el nombre de usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
