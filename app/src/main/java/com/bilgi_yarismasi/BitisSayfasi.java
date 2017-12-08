package com.bilgi_yarismasi;

import android.app.Activity;
import android.app.AlertDialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;

import java.util.HashSet;
import java.util.Set;

public class BitisSayfasi extends Activity {

    private static SharedPreferences preferences;
    private String skorum;
    private Set<String> puanlarim;
    private FragmentList fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitis_sayfasi);

        puanlarim=new HashSet<>();
        preferences=getSharedPreferences("SkorTablosu",MODE_PRIVATE);

        Intent intent=getIntent();
        skorum=intent.getStringExtra("Skorunuz").toString();

        puanlarim=preferences.getStringSet("SkorSet",new HashSet<String>());

        puanlarim.add(skorum);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putStringSet("SkorSet",puanlarim);
        editor.commit();

        fragmentList=new FragmentList(puanlarim);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container,fragmentList);
        fragmentTransaction.commit();
    }


    public static void setList(Set<String> setlist){
        SharedPreferences.Editor editor=preferences.edit();
        editor.putStringSet("SkorSet",setlist);
        editor.commit();
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
