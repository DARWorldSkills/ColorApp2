package com.aprendiz.ragp.colorapp2.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.colorapp2.R;
import com.aprendiz.ragp.colorapp2.models.GestorDB;
import com.aprendiz.ragp.colorapp2.models.Score;

import java.io.File;

public class Resumen extends AppCompatActivity {
    TextView txtCorrectas, txtIncorrectas, txtPorcentaje;
    Bitmap bitmap;
    File file;
    Button btnFace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        inizialite();
        inputData();
    }

    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtCorrectasR);
        txtIncorrectas = findViewById(R.id.txtIncorrectasR);
        txtPorcentaje = findViewById(R.id.txtPorcentajeR);
        btnFace = findViewById(R.id.btnFace);
    }

    private void inputData() {
        if (MenuJ.guardar==1){
            int correctas = Juego.correctas;
            int incorrectas = Juego.incorrectas;
            int porcentaje = Juego.porcentaje;
            Score score = new Score();
            score.setPuntuacion(porcentaje);
            score.setIncorrectas(incorrectas);

            txtCorrectas.setText(Integer.toString(correctas));
            txtIncorrectas.setText(Integer.toString(incorrectas));
            txtPorcentaje.setText(Integer.toString(porcentaje));

            GestorDB gestorDB = new GestorDB(this);
            gestorDB.inputData(score);

        }else {
            int correctas = JuegoC.correctas;
            int incorrectas = JuegoC.incorrectas;
            int porcentaje = JuegoC.porcentaje;
            Score score = new Score();
            score.setPuntuacion(porcentaje);
            score.setIncorrectas(incorrectas);

            txtCorrectas.setText(Integer.toString(correctas));
            txtIncorrectas.setText(Integer.toString(incorrectas));
            txtPorcentaje.setText(Integer.toString(porcentaje));


        }
    }

    public void twi(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Correctas" + txtCorrectas.getText().toString()+ "Incorrectas" + txtIncorrectas.getText().toString()+ "Porcentaje" + txtPorcentaje.getText().toString());
        intent.setPackage("com.twitter.android");

        try {
            startActivity(intent);

        } catch (Exception e) {
            Toast.makeText(this, "No cuentas con esta app, Por favor instala esta app", Toast.LENGTH_SHORT).show();
            e.printStackTrace();

        }

    }



}
