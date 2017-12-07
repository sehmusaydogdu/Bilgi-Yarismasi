package com.bilgi_yarismasi;

import android.app.Activity;
import android.app.AlertDialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BitisSayfasi extends Activity {


    ListFragment listFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitis_sayfasi);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        listFragment = new ListFragment();
        fragmentTransaction.replace(R.id.fragment_container,listFragment);
        fragmentTransaction.commit();



    }

    public void onOyunuBaslat(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onCikis(View view) {
        AlertDialog alertMessage = new AlertDialog.Builder(this).create();
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
        alertMessage.show();
    }
}
