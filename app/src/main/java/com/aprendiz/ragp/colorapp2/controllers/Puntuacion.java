package com.aprendiz.ragp.colorapp2.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aprendiz.ragp.colorapp2.R;
import com.aprendiz.ragp.colorapp2.models.GestorDB;
import com.aprendiz.ragp.colorapp2.models.Score;

import java.util.List;

public class Puntuacion extends AppCompatActivity {
    TextView txtPrimero, txtSegundo, txtTercero, txtCuarto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);
        inizialite();
        inputData();
    }

    private void inizialite() {
        txtPrimero = findViewById(R.id.txtPrimero);
        txtSegundo = findViewById(R.id.txtSegundo);
        txtTercero = findViewById(R.id.txtTercero);
        txtCuarto = findViewById(R.id.txtCuarto);
    }

    private void inputData() {
        GestorDB gestorDB = new GestorDB(this);
        List<Score> results = gestorDB.listResults();
        if (results.size()>0){
            txtPrimero.setText(results.get(0).getPuntuacion()+"%");
        }

        if (results.size()>1){
            txtSegundo.setText(results.get(1).getPuntuacion()+"%");
        }


        if (results.size()>2){
            txtTercero.setText(results.get(2).getPuntuacion()+"%");
        }

        if (results.size()>3){
            txtCuarto.setText(results.get(3).getPuntuacion()+"%");
        }

    }

    public void finishP(View view) {
        finish();
    }
}
