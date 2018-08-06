package com.aprendiz.ragp.colorapp2.controllers;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aprendiz.ragp.colorapp2.R;
import com.aprendiz.ragp.colorapp2.models.GestorDB;
import com.aprendiz.ragp.colorapp2.models.Score;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Resumen extends AppCompatActivity {
    TextView txtCorrectas, txtIncorrectas, txtPorcentaje;
    Bitmap bitmap;
    File file;
    Button btnFace;
    ConstraintLayout contenedorR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        inizialite();
        inputData();
        screenShot(contenedorR);


        btnFace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Compartir();
            }
        });
    }

    private void Compartir() {

        String nameFile = saveToInternalStorage(bitmap);
        Toast.makeText(this, ""+nameFile, Toast.LENGTH_SHORT).show();
        File file = new File(nameFile);
        Intent intent = new Intent();
        intent.setType("image/jpg");
        intent.setAction(Intent.ACTION_SEND);
        Uri uri = Uri.fromFile(file);
        intent.putExtra(Intent.EXTRA_STREAM,uri);
        startActivity(Intent.createChooser(intent,"send"));
    }

    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtCorrectasR);
        txtIncorrectas = findViewById(R.id.txtIncorrectasR);
        txtPorcentaje = findViewById(R.id.txtPorcentajeR);
        btnFace = findViewById(R.id.btnFace);
        contenedorR = findViewById(R.id.contenedorR);
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


    public void screenShot(View view) {
        bitmap = getBitmapOFRootView(view);
        createImage(bitmap);



    }

    private Bitmap getBitmapOFRootView(View v) {
        View rootview = v.getRootView();
        rootview.setDrawingCacheEnabled(true);
        Bitmap bitmap1 = rootview.getDrawingCache();
        return bitmap1;
    }

    private void createImage(Bitmap bmp) {

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 40, bytes);
        file = new File(Environment.getExternalStorageDirectory() +
                "/capturedscreenandroid1.jpg");
        try {
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes.toByteArray());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private String saveToInternalStorage(Bitmap bitmapImage) {

        String path = Environment.getExternalStorageDirectory().toString() + "/" + "hola.jpg";

        View v = getWindow().getDecorView().getRootView();
        v.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);

        OutputStream out = null;
        File imageFile = new File(path);

        try {
            out = new FileOutputStream(imageFile);
            // choose JPEG format
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
        } catch (FileNotFoundException e) {
            // manage exception
        } catch (IOException e) {
            // manage exception
        } finally {

            try {
                if (out != null) {
                    out.close();
                }

            } catch (Exception exc) {
            }

        }
        return path;
    }


}
