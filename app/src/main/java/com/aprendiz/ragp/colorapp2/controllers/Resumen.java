package com.aprendiz.ragp.colorapp2.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aprendiz.ragp.colorapp2.R;
import com.aprendiz.ragp.colorapp2.models.GestorDB;
import com.aprendiz.ragp.colorapp2.models.Score;

public class Resumen extends AppCompatActivity {
    TextView txtCorrectas, txtIncorrectas, txtPorcentaje;
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
}
