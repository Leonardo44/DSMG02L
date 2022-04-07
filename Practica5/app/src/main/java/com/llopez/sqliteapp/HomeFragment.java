package com.llopez.sqliteapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class HomeFragment extends Fragment {
    private Button btn1, btn2, btn3;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        btn1 = layout.findViewById(R.id.buttonOpenExerciseOne);
        btn2 = layout.findViewById(R.id.buttonOpenExerciseTwo);
        btn3 = layout.findViewById(R.id.buttonOpenExerciseThree);

        btn1.setOnClickListener(view -> {
            openExerciseOne();
        });

        btn2.setOnClickListener(view -> {
            openExerciseTwo();
        });

        btn3.setOnClickListener(view -> {
            openExerciseThree();
        });

        return layout;
    }

    private void openExerciseOne() {
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_firstExerciseFragment);
    }

    private void openExerciseTwo() {
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_secondExerciseFragment);
    }

    private void openExerciseThree() {
        NavHostFragment.findNavController(this).navigate(R.id.action_homeFragment_to_thirdExerciseFragment);
    }
}