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


public class FirstExerciseFragment extends Fragment {
    private EditText et1,et2,et3;
    private Button btn1, btn2, btn3, btn4, btn5;

    public FirstExerciseFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_first_exercise, container, false);
        et1 = layout.findViewById(R.id.edt1);
        et2 = layout.findViewById(R.id.edt2);
        et3 = layout.findViewById(R.id.edt3);
        btn1 = layout.findViewById(R.id.buttonOne);
        btn2 = layout.findViewById(R.id.buttonTwo);
        btn3 = layout.findViewById(R.id.buttonThree);
        btn4 = layout.findViewById(R.id.buttonFour);
        btn5 = layout.findViewById(R.id.buttonFive);

        btn1.setOnClickListener(view -> {
            alta(view);
        });

        btn2.setOnClickListener(view -> {
            consultaporcodigo(view);
        });

        btn3.setOnClickListener(view -> {
            consultapordescripcion(view);
        });

        btn4.setOnClickListener(view -> {
            bajaporcodigo(view);
        });

        btn5.setOnClickListener(view -> {
            modificacion(view);
        });

        return layout;
    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new  AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues(); registro.put("codigo", cod); registro.put("descripcion", descri); registro.put("precio", pre);
        bd.insert("articulos", null, registro);
        bd.close();

        et1.setText("");
        et2.setText("");
        et3.setText("");
        Toast.makeText(getActivity(), "Se cargaron los datos del  artículo",Toast.LENGTH_SHORT).show();
    }

    public void consultaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        Cursor fila = bd.rawQuery("select descripcion,precio from articulos where codigo =" + cod, null);

        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
        } else {
            Toast.makeText(getActivity(), "No existe un artículo con dicho código", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void consultapordescripcion(View v) { AdminSQLiteOpenHelper admin = new
            AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descri = et2.getText().toString();
        Cursor fila = bd.rawQuery("select codigo,precio from articulos where descripcion='" + descri +"'", null);

        if (fila.moveToFirst()) {
            et1.setText(fila.getString(0));

            et3.setText(fila.getString(1));
        } else {
            Toast.makeText(getActivity(), "No existe un artículo con dicha descripción", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void bajaporcodigo(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod= et1.getText().toString();
        int cant = bd.delete("articulos", "codigo=" + cod, null);
        bd.close();
        et1.setText(""); et2.setText(""); et3.setText("");

        if (cant == 1) {
            Toast.makeText(getActivity(), "Se borró el artículo con dicho código", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "No existe un artículo con dicho código",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String cod = et1.getText().toString();
        String descri = et2.getText().toString();
        String pre = et3.getText().toString();
        ContentValues registro = new ContentValues(); registro.put("codigo", cod);
        registro.put("descripcion", descri);
        registro.put("precio", pre);
        int cant = bd.update("articulos", registro, "codigo=" + cod, null);
        bd.close();
        if (cant == 1) {
            Toast.makeText(getActivity(), "se modificaron los datos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "no existe un artículo con el código ingresado", Toast.LENGTH_SHORT).show();
        }
    }
}