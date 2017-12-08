package com.bilgi_yarismasi;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;



/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class FragmentList extends Fragment {


    ListView lstView;
    private Set<String> setlist;
    ArrayAdapter listViewAdapter;
    private SharedPreferences preferences;

    @SuppressLint("ValidFragment")
    public FragmentList(Set<String> puanlarim){
           setlist=new HashSet<>(puanlarim);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_skorlar, container, false);
        View view = inflater.inflate(R.layout.fragment_list_skorlar, container, false);
        lstView = view.findViewById(R.id.lstView);
        refresh();

        lstView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, final int i, long l) {

                AlertDialog alertMessage = new AlertDialog.Builder(getActivity()).create();
                alertMessage.setTitle("Sil");
                alertMessage.setMessage(adapterView.getAdapter().getItem(i)+"  Silmek İstediğinizden Emin Misiniz?");

                alertMessage.setButton(AlertDialog.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which) {
                       remove(adapterView.getAdapter().getItem(i).toString());
                    }
                });

                alertMessage.setButton(AlertDialog.BUTTON_NEGATIVE,"CANCEL", new DialogInterface.OnClickListener(){

                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                alertMessage.show();
                return false;
            }
        });

        return view;
    }

    //Listeyi yenile
    private void refresh(){

        listViewAdapter = new ArrayAdapter<>(
                getActivity(),android.R.layout.simple_list_item_1, setlist.toArray()
        );
        lstView.setAdapter(listViewAdapter);
        BitisSayfasi.setList(setlist);
    }

    //Listeden eleman sil
    private void remove(String item){
        setlist.remove(item);
        refresh();
       // (new BitisSayfasi()).setList(setlist);
    }

    public Set<String> getSetlist(){
        return setlist;
    }

}
