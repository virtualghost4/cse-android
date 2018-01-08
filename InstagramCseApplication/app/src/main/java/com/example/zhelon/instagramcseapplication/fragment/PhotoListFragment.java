package com.example.zhelon.instagramcseapplication.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhelon.instagramcseapplication.R;
import com.example.zhelon.instagramcseapplication.adapter.PhotoAdapter;
import com.example.zhelon.instagramcseapplication.pojo.Photo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by zhelon on 11-12-17.
 */

public class PhotoListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "position";


    private RecyclerView recyler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    public  PhotoListFragment(){

    }

    public static PhotoListFragment newInstance(int position) {
        PhotoListFragment fragment = new PhotoListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_list_photo, container, false);
        buildPhotoList(view);
        return view;
    }

    private void buildPhotoList(View view) {

        recyler = (RecyclerView) view.findViewById(R.id.photo_list);
//        recyler.setHasFixedSize(true);

        manager = new LinearLayoutManager(view.getContext());
        recyler.setLayoutManager(manager);


        List<Photo> listPhoto = getAllPhoto();

        adapter = new PhotoAdapter(listPhoto);
        recyler.setAdapter(adapter);
        recyler.setHasFixedSize(true);

    }


    private List<Photo> getAllPhoto() {
        Realm realms = Realm.getDefaultInstance();
        RealmResults<Photo> result = realms.where(Photo.class).findAll();

        return result;

    }

}
