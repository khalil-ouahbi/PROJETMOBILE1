package com.example.myapplication6;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication6.Annonce;
import com.example.myapplication6.Inscription;

public class MainActivity extends AppCompatActivity {

    EditText editTextEmail,editTextPassword ;
    Button buttonLogin;
    TextView textViewLink;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);

        editTextEmail= findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewLink = findViewById(R.id.textViewLink);

        buttonLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (db.checkUser(email, password)) {
                    showSuccessAlert();
                } else {
                    showErrorAlert();
                }

            }
        });

        textViewLink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Inscription.class);
                startActivity(intent);
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private boolean isValidPassword(String password) {
        return !TextUtils.isEmpty(password);
    }

    private void showSuccessAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Succès");
        builder.setMessage("Les informations de connexion sont correctes.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, Annonce.class);
                startActivity(intent);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void showErrorAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Erreur");
        builder.setMessage("Les informations de connexion ne sont pas correctes.");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}