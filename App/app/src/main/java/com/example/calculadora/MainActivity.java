package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtOne;
    private EditText edtTwo;
    private Button btnSum;
    private Button btnRest;
    private Button btnMult;
    private Button btnDiv;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtOne = findViewById(R.id.edVOne);
        edtTwo = findViewById(R.id.edvTwo);
        tvResult = findViewById(R.id.tvResult);
        btnSum = findViewById(R.id.btnSum);
        btnRest = findViewById(R.id.btnRest);
        btnMult = findViewById(R.id.btnMult);
        btnDiv = findViewById(R.id.btnDiv);

        btnSum.setOnClickListener (new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Double result = getValueOne() + getValueTwo();
                setResult(result);
            }
        });

        btnRest.setOnClickListener (new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Double result = getValueOne() - getValueTwo();
                setResult(result);
            }
        });

        btnMult.setOnClickListener (new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Double result = getValueOne() * getValueTwo();
                setResult(result);
            }
        });

        btnDiv.setOnClickListener (new View.OnClickListener()
        {
            public void onClick(View view)
            {
                Double result = getValueOne() / getValueTwo();

                if (getValueTwo() == 0) {
                    errorDivMessage();
                } else {
                    setResult(result);
                }
            }
        });
    }

    private Double getValueOne() {
        try {
            return Double.parseDouble(edtOne.getText().toString());
        } catch (Exception  error) {
            Toast.makeText(this, "Ingrese valor válido", Toast.LENGTH_SHORT).show();
            return Double.parseDouble("0");
        }
    }

    private Double getValueTwo() {
        try {
            return Double.parseDouble(edtTwo.getText().toString());
        } catch (Exception  error) {
            Toast.makeText(this, "Ingrese valor válido", Toast.LENGTH_SHORT).show();
            return Double.parseDouble("0");
        }
    }

    private void setResult(Double value) {
        tvResult.setText("Resultado: " + value);
    }

    private void errorDivMessage() {
        Toast.makeText(this, "Segúndo número no puede ser 0", Toast.LENGTH_SHORT).show();
    }
}
