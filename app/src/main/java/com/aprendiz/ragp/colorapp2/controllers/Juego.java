package com.aprendiz.ragp.colorapp2.controllers;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aprendiz.ragp.colorapp2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Juego extends AppCompatActivity implements OnClickListener{
    TextView txtCorrectas, txtIncorrectas, txtPorcentaje, txtPalabra, txtTiempo;
    ImageButton btnColor1,btnColor2, btnColor3, btnColor4;
    int icR, ipR, ab=0, valorcito;
    public static int correctas, incorrectas, porcentaje, intentos;
    boolean bandera = true;
    int [] segundos={0,30};
    List<Integer> listaColores = new ArrayList<>();
    List<String> listaPalabras = new ArrayList<>();
    List<Integer> listaRandom = new ArrayList<>();
    ProgressBar pBTimepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
        inizialite();
        cleanV();
        listGame();
        randomizer();
        chronometer();
    }




    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtCorrectas);
        txtIncorrectas = findViewById(R.id.txtIncorrectas);
        txtPorcentaje = findViewById(R.id.txtPorcentaje);
        txtPalabra = findViewById(R.id.txtPalabra);
        txtTiempo = findViewById(R.id.txtTiempo);

        btnColor1 = findViewById(R.id.btnColor1);
        btnColor2 = findViewById(R.id.btnColor2);
        btnColor3 = findViewById(R.id.btnColor3);
        btnColor4 = findViewById(R.id.btnColor4);
        pBTimepo = findViewById(R.id.pBTiempo);

        btnColor1.setOnClickListener(this);
        btnColor2.setOnClickListener(this);
        btnColor3.setOnClickListener(this);
        btnColor4.setOnClickListener(this);

    }

    private void chronometer() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            segundos[0]++;
                            segundos[1]--;
                            txtTiempo.setText("Tiempo: "+segundos[1]);
                            pBTimepo.setProgress(segundos[1]);

                            if (segundos[0]==3){
                                segundos[0]=0;
                                intentos++;
                                incorrectas++;
                                randomizer();
                                inputData();
                            }
                            endGame();

                        }
                    });
                }
            }
        });
        thread.start();
    }

    private void endGame() {
        if (ab==0 &&  (segundos[1]==0 || incorrectas==3 ) ){
            bandera=false;

            ab=1;
        }
    }

    private void cleanV() {
        correctas =0;
        incorrectas=0;
        porcentaje=0;
        intentos=0;
        bandera=true;
        ab=0;
        pBTimepo.setMax(30);
        txtTiempo.setText("Tiempo: "+segundos[1]);
        pBTimepo.setProgress(segundos[1]);
    }

    private void listGame() {
        listaPalabras  = new ArrayList<>();
        listaColores= new ArrayList<>();
        listaPalabras.add("AMARILLO");
        listaColores.add(getColor(R.color.colorAmarillo));
        listaPalabras.add("AZUL");
        listaColores.add(getColor(R.color.colorAzul));
        listaPalabras.add("ROJO");
        listaColores.add(getColor(R.color.colorRojo));
        listaPalabras.add("VERDE");
        listaColores.add(getColor(R.color.colorVerde));


    }

    private void randomizer() {
        listaRandom = listaColores;
        ipR = (int) (Math.random() * 4);
        icR = (int) (Math.random() * 4);
        Collections.shuffle(listaRandom);

        txtPalabra.setText(listaPalabras.get(ipR));
        txtPalabra.setTextColor(listaColores.get(icR));

        btnColor1.setBackgroundColor(listaColores.get(0));
        btnColor2.setBackgroundColor(listaColores.get(1));
        btnColor3.setBackgroundColor(listaColores.get(2));
        btnColor4.setBackgroundColor(listaColores.get(3));

    }


    private void validar() {
        intentos++;
        if (valorcito==1){
            if (icR==0){
                correctas++;
            }else {
                incorrectas++;
            }
        }


        if (valorcito==2){
            if (icR==1){
                correctas++;
            }else {
                incorrectas++;
            }
        }


        if (valorcito==3){
            if (icR==2){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        if (valorcito==4){
            if (icR==3){
                correctas++;
            }else {
                incorrectas++;
            }
        }
        endGame();

        randomizer();
        segundos[0]=0;
        inputData();

    }

    private void inputData() {
        if (intentos>0){
            if (correctas>0){
                float tmp1 = intentos;
                float tmp2 = correctas;
                double tmpP= (tmp2/tmp1) *100;
                porcentaje = (int) tmpP;

            }else{
                porcentaje=0;
            }
        }else {
            porcentaje=100;
        }

        txtCorrectas.setText(Integer.toString(correctas));
        txtPorcentaje.setText(Integer.toString(porcentaje));
        txtIncorrectas.setText(Integer.toString(incorrectas));


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnColor1:
                valorcito=1;
                validar();
                break;

            case R.id.btnColor2:
                valorcito=2;
                validar();
                break;


            case R.id.btnColor3:
                valorcito=3;
                validar();
                break;

            case R.id.btnColor4:
                valorcito=4;
                validar();
                break;

        }
    }



}
