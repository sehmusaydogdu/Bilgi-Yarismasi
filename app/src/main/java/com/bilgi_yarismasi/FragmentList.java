package com.bilgi_yarismasi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentList extends Fragment {


    ListView lstView;
    private ArrayList<String> list;
    ArrayAdapter<String> listViewAdapter;
    public FragmentList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_list_skorlar, container, false);
        View view = inflater.inflate(R.layout.fragment_list_skorlar, container, false);
        lstView = view.findViewById(R.id.lstView);

        list=new ArrayList<>();
        list.add("Merhaba");
        listViewAdapter = new ArrayAdapter<>(
                getActivity(),android.R.layout.simple_list_item_1,list
        );
        lstView.setAdapter(listViewAdapter);
        return view;
    }

}
