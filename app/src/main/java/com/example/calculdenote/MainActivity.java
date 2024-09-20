package com.example.calculdenote;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText note1, note2, note3, note4;
    private TextView resultatTextView;
    private Button calculerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation des composants de l'interface
        note1 = findViewById(R.id.note1);
        note2 = findViewById(R.id.note2);
        note3 = findViewById(R.id.note3);
        note4 = findViewById(R.id.note4);
        resultatTextView = findViewById(R.id.resultatTextView);
        calculerButton = findViewById(R.id.calculerButton);

        // Gestion du clic sur le bouton Calculer
        calculerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Récupération des notes
                    double[] notes = new double[4];
                    notes[0] = Double.parseDouble(note1.getText().toString());
                    notes[1] = Double.parseDouble(note2.getText().toString());
                    notes[2] = Double.parseDouble(note3.getText().toString());
                    notes[3] = Double.parseDouble(note4.getText().toString());

                    // Validation des valeurs négatives ou hors plage (0-20)
                    for (double note : notes) {
                        if (note < 0) {
                            Toast.makeText(MainActivity.this, "Votre note ne peut pas être inférieure à 0", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (note > 20) {
                            Toast.makeText(MainActivity.this, "Votre note ne peut pas être supérieure à 20", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    // Calcul de la moyenne
                    double moyenne = calculerMoyenne(notes);

                    // Attribution de la mention
                    String mention = attribuerMention(moyenne);

                    // Affichage du résultat
                    resultatTextView.setText("Moyenne: " + String.format("%.2f", moyenne) + "\nMention: " + mention);
                } catch (NumberFormatException e) {
                    resultatTextView.setText("Veuillez entrer des valeurs valides pour toutes les notes.");
                }
            }
        });
    }

    // Méthode pour calculer la moyenne des notes
    private double calculerMoyenne(double[] notes) {
        double somme = 0;
        for (double note : notes) {
            somme += note;
        }
        return somme / notes.length;
    }

    // Méthode pour attribuer une mention en fonction de la moyenne
    private String attribuerMention(double moyenne) {
        if (moyenne >= 16) {
            return "Très bien";
        } else if (moyenne >= 14) {
            return "Bien";
        } else if (moyenne >= 12) {
            return "Assez bien";
        } else if (moyenne >= 10) {
            return "Passable";
        } else {
            return "Échec";
        }
    }
}
