package com.llopez.sqliteapp;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.loader.content.CursorLoader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;

public class SecondExerciseFragment extends Fragment {
    private StudentsAdapter adaptador;

    public SecondExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_second_exercise, container, false);
        return layout;
    }
}