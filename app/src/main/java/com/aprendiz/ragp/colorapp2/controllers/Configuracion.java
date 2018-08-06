package com.aprendiz.ragp.colorapp2.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.aprendiz.ragp.colorapp2.R;

public class Configuracion extends AppCompatActivity {
    RadioButton rbtnTiempo, rbtnAciertos;
    EditText txtTiempo;
    SharedPreferences juegoC;
    ImageButton btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        juegoC= getSharedPreferences("juegoC",MODE_PRIVATE);
        iniziliate();
        inputData();
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData(v);
            }
        });
    }

    private void saveData(View v) {
        int modo =1;
        int tiempo =3;
        SharedPreferences.Editor editor = juegoC.edit();
        if (rbtnTiempo.isChecked()){
            modo=1;
        }
        if (rbtnAciertos.isChecked()){
            modo=2;
        }

        try {
            int tmp= Integer.parseInt(txtTiempo.getText().toString());
            if (tmp>0 && tmp<11){
                tiempo=tmp;
            }else {
                Snackbar.make(v,"No se guardará el tiempo por no estar en el rango permitido",Snackbar.LENGTH_SHORT).show();
            }
        }catch (Exception e){

        }

        editor.putInt("modo",modo);
        editor.putInt("tiempo",tiempo);
        editor.commit();
        Intent intent = new Intent(Configuracion.this,JuegoC.class);
        startActivity(intent);
        finish();



    }


    private void iniziliate() {
        rbtnTiempo = findViewById(R.id.rbtnTiempo);
        rbtnAciertos = findViewById(R.id.rbtnAciertos);
        txtTiempo = findViewById(R.id.txtTiempoP);
        btnHome = findViewById(R.id.btnHome);
    }

    private void inputData() {
        int modo = juegoC.getInt("modo",1);
        int tiempo = juegoC.getInt("tiempo",3);
        if (modo==1){
            rbtnTiempo.setChecked(true);
        }

        if (modo==2){
            rbtnAciertos.setChecked(true);
        }

        txtTiempo.setText(Integer.toString(tiempo));
    }
}
