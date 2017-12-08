package com.bilgi_yarismasi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import android.os.Handler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class OyunSayfasi extends Activity {

    private Handler handler = new Handler();
    private Random  uret =new Random();
    private ArrayList<String[]> arrayLists;
    private TextView txtSkor,txtSoru,txtSure;
    private ProgressBar progressBar;
    private Button btnDogru,btnYanlis;
    private int progressStatus=0,maximum,rastgeleSoru,txtPuanInt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_sayfasi);
        Intent intent=getIntent();
        OyunSayfasi.this.setTitle(intent.getStringExtra("KullanıcıAdı"));
        init();
    }


    //Soruyu ekrana bastım
    private void oyunuBaslat(){
        btnVarsayılan();
        rastgeleSoru=uret.nextInt(maximum);
        txtSoru.setText(arrayLists.get(rastgeleSoru)[1]);
    }


    //Varsayılan buttonların rengi
    private void btnVarsayılan(){
        btnDogru.setBackgroundColor(Color.BLUE);
        btnDogru.setTextColor(Color.WHITE);
        btnYanlis.setBackgroundColor(Color.BLUE);
        btnYanlis.setTextColor(Color.WHITE);
    }

    //Nesneleri yakala
    private void init(){

        txtSure=findViewById(R.id.txtSure);
        txtSkor=findViewById(R.id.txtSkor);
        txtSoru=findViewById(R.id.txtSoru);
        progressBar=findViewById(R.id.progressBar);
        btnDogru=findViewById(R.id.btnDogru);
        btnYanlis=findViewById(R.id.btnYanlis);
    }

    // Uygulama 60sn süre ile progressbar ın değerini gösterir.
    private void timeStart(){
        // Start long running operation in a background thread
        new Thread(new Runnable() {

            public void run() {
                while (progressStatus < 60) {
                    progressStatus += 1;
                    // Update the progress bar and display the
                    //current value in the text view
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            txtSure.setText(progressStatus+"/"+progressBar.getMax());
                        }
                    });

                    if (progressStatus==60)
                    {
                        Intent intent=new Intent(getApplicationContext(),BitisSayfasi.class);
                        intent.putExtra("Skorunuz",String.valueOf(txtPuanInt));
                        startActivity(intent);
                        finish();
                    }
                    try {
                        // Sleep for 500 milliseconds.
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override  //Geri butonuna basıldığında sayfa yok olur.
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override  //Uygulama başlarken dosyadan veri çekiyorum.
    protected void onStart() {
        super.onStart();
        readFile();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeStart();
        oyunuBaslat();
    }

    //Ekranda gösterilecek solan sorular burada okunup ArrayListe taşınıyor.
    private void readFile(){
        arrayLists=new ArrayList<>();
        try {

            InputStream inputStream=getAssets().open("sorular.txt");
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String satir="";
            while ((satir=bufferedReader.readLine())!=null){
                arrayLists.add(satir.split(","));
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        maximum=arrayLists.size();
    }

    public void onClick_Dogru(View view) throws InterruptedException {

        if (arrayLists.get(rastgeleSoru)[2].equals("1")){
            btnDogru.setBackgroundColor(Color.GREEN);
            txtPuanInt+=10;
            txtSkor.setText("Skor  : "+txtPuanInt);
        }
        else {
            btnDogru.setBackgroundColor(Color.RED);
            txtPuanInt-=10;
            txtSkor.setText("Skor  : "+txtPuanInt);
        }
        oyunuBaslat();

    }

    public void onClick_Yanlis(View view) throws InterruptedException {

        if (arrayLists.get(rastgeleSoru)[2].equals("0")){
            btnYanlis.setBackgroundColor(Color.GREEN);
            txtPuanInt+=10;
            txtSkor.setText("Skor  : "+txtPuanInt);
        }
        else {
            btnYanlis.setBackgroundColor(Color.RED);
            txtPuanInt-=10;
            txtSkor.setText("Skor  : "+txtPuanInt);
        }
        oyunuBaslat();
    }


}
