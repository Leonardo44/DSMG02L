package sv.edu.udb.calculadoradesalario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText tvName;
    private EditText tvHour;
    private Button btnCalculate;

    static final double VALUE_HOUR = 8.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.ediTName);
        tvHour = findViewById(R.id.ediTHour);
        btnCalculate = findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailActivity();
            }
        });
    }

    private void openDetailActivity() {
        try {
            String name = tvName.getText().toString();
            Double hour = Double.parseDouble(tvHour.getText().toString());

            if (name.trim().isEmpty()) {
                toastValidation();
            } else {
                Intent i = new Intent(this, DetailActivity.class);

                i.putExtra("VALUE_HOUR", VALUE_HOUR);
                i.putExtra("NAME", name);
                i.putExtra("HOUR", hour);

                startActivity(i);
            }
        } catch (Exception  error) {
            toastValidation();
        }
    }

    private void toastValidation() {
        Toast.makeText(this, "Ingrese valores válido", Toast.LENGTH_SHORT).show();
    }
}
