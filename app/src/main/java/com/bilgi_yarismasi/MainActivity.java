package com.bilgi_yarismasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private TextView txtKullaniciAdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtKullaniciAdi=findViewById(R.id.txtKullaniciAdi);
    }

    public void onCikis(View view) {
      /*  AlertDialog alertMessage = new AlertDialog.Builder(this).create();
        alertMessage.setTitle("Çıkış");
        alertMessage.setMessage("Çıkış Yapmak İstediğinizden Emin Misiniz?");

        alertMessage.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertMessage.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL", new DialogInterface.OnClickListener(){

            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertMessage.show();*/
      Intent intent=new Intent(this,BitisSayfasi.class);
      startActivity(intent);
    }

    public void onOyunuBaslat(View view) {

        if (txtKullaniciAdi.getText().toString().equals(""))
            Toast.makeText(this, "Kullanıcı Adınızı Giriniz !!!", Toast.LENGTH_SHORT).show();
        else{
            Intent intent=new Intent(this,OyunSayfasi.class);
            intent.putExtra("KullanıcıAdı",txtKullaniciAdi.getText().toString());
            startActivity(intent);
        }
    }
}
