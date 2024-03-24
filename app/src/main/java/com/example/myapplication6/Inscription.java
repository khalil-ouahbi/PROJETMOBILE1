package com.example.myapplication6;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Inscription extends AppCompatActivity {

    private RadioButton Option1;

    private EditText editTextEmail;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private RadioGroup radioGroup;
    private Button buttonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription);

        Option1 = findViewById(R.id.Option1);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        radioGroup = findViewById(R.id.radioGroup);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        // Définition de l'action à effectuer lors de la sélection d'une option radio
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
               Option1.setText("Option sélectionnée: " + radioButton.getText());
            }
        });

        // Définition de l'action à effectuer lors du clic sur le bouton "Créer un compte"
        buttonCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des valeurs des champs email et mot de passe
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String confirmPassword = editTextConfirmPassword.getText().toString().trim();

                // Vérification de la correspondance entre le mot de passe et sa confirmation
                if (!password.equals(confirmPassword)) {
                    Toast.makeText(Inscription.this, "Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Vérification de la validité des champs email et mot de passe
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(Inscription.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Traitement supplémentaire, tel que l'enregistrement du compte utilisateur dans la base de données
                // Cette partie devrait être adaptée en fonction de la logique de votre application

                // Affichage d'un message de succès
                Toast.makeText(Inscription.this, "Compte créé avec succès", Toast.LENGTH_SHORT).show();
            }
        });
    }
    Spinner citySpinner = findViewById(R.id.citiesSpinner);

    // Liste des villes
    String[] cities = {"Paris", "New York", "Tokyo", "London", "Berlin", "Sydney"};

    // Adapter pour les options du Spinner
    ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    // Liaison de l'adapter au Spinner
       // citySpinner.setAdapter(adapter);
}
