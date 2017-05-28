package com.example.zhelon.camaraaplication.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhelon.camaraaplication.R;
import com.example.zhelon.camaraaplication.adapter.PhotoAdapter;
import com.example.zhelon.camaraapplication.pojo.Photo;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;


public class ListUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;


    public ListUserFragment() {
    }


    public static ListUserFragment newInstance(int position) {
        ListUserFragment fragment = new ListUserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_user, container, false);
        createDummyReciclerView(view);
        return view;
    }

    private void createDummyReciclerView(View v) {

        List items = new ArrayList();

        Realm realms = Realm.getDefaultInstance();
        RealmResults<Photo> resultPhoto = realms.where(Photo.class).findAll();

        items.addAll(resultPhoto);


        // Obtener el Recycler
        recycler = (RecyclerView) v.findViewById(R.id.recyclerview);
        recycler.setHasFixedSize(true);

        // Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(v.getContext());
        recycler.setLayoutManager(lManager);

        // Crear un nuevo adaptador
        adapter = new PhotoAdapter(items);
        recycler.setAdapter(adapter);
    }




}
