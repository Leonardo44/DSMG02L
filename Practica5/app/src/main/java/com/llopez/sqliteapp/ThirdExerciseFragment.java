package com.llopez.sqliteapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ThirdExerciseFragment extends Fragment {
    private EditText edt1,edt2,edt3, edt4, edt5;
    private Button btn1, btn2, btn3, btn4;

    public ThirdExerciseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_third_exercise, container, false);

        edt1 = layout.findViewById(R.id.edt1);
        edt2 = layout.findViewById(R.id.edt2);
        edt3 = layout.findViewById(R.id.edt3);
        edt4 = layout.findViewById(R.id.edt4);
        edt5 = layout.findViewById(R.id.edt5);
        btn1 = layout.findViewById(R.id.buttonOne);
        btn2 = layout.findViewById(R.id.buttonTwo);
        btn3 = layout.findViewById(R.id.buttonThree);
        btn4 = layout.findViewById(R.id.buttonFour);

        btn1.setOnClickListener(view -> {
            crear(view);
        });

        btn2.setOnClickListener(view -> {
            modificar(view);
        });

        btn3.setOnClickListener(view -> {
            eliminarEmail(view);
        });

        btn4.setOnClickListener(view -> {
            consultarEmail(view);
        });

        return layout;
    }

    public void crear(View v) {
        PersonaSQLiteOpenHelper admin = new  PersonaSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = edt1.getText().toString();
        String apellido = edt2.getText().toString();
        String edad = edt3.getText().toString();
        String telefono = edt4.getText().toString();
        String email = edt5.getText().toString();
        ContentValues registro = new ContentValues();

        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        registro.put("edad", edad);
        registro.put("telefono", telefono);
        registro.put("codigo", email);
        bd.insert("persona", null, registro);
        bd.close();

        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        edt5.setText("");
        Toast.makeText(getActivity(), "Se cargaron los datos de la persona",Toast.LENGTH_SHORT).show();
    }

    public void consultarEmail(View v) {
        PersonaSQLiteOpenHelper admin = new PersonaSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String email = edt5.getText().toString();
        Cursor fila = bd.rawQuery("select nombre, apellido, edad, telefono, codigo from persona where codigo='" + email +"'", null);

        if (fila.moveToFirst()) {
            edt1.setText(fila.getString(0));
            edt2.setText(fila.getString(1));
            edt3.setText(fila.getString(2));
            edt4.setText(fila.getString(3));
        } else {
            Toast.makeText(getActivity(), "No existe una persona con dicho email", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void eliminarEmail(View v) {
        PersonaSQLiteOpenHelper admin = new PersonaSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String email = edt5.getText().toString();
        int cant = bd.delete("persona", "codigo='" + email + "'", null);
        bd.close();

        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        edt5.setText("");

        if (cant == 1) {
            Toast.makeText(getActivity(), "Se borró la persona con dicho codigo", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "No existe una persona con dicho codigo",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void modificar(View v) {
        PersonaSQLiteOpenHelper admin = new PersonaSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nombre = edt1.getText().toString();
        String apellido = edt2.getText().toString();
        String edad = edt3.getText().toString();
        String telefono = edt4.getText().toString();
        String email = edt5.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("apellido", apellido);
        registro.put("edad", edad);
        registro.put("telefono", telefono);

        int cant = bd.update("persona", registro, "codigo='" + email +"'", null);
        bd.close();

        edt1.setText("");
        edt2.setText("");
        edt3.setText("");
        edt4.setText("");
        edt5.setText("");

        if (cant == 1) {
            Toast.makeText(getActivity(), "se modificaron los datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "no existe una persona con el codigo ingresado", Toast.LENGTH_SHORT).show();
        }
    }
}