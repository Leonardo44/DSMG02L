package sv.edu.udb.calculadoradesalario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView tvFirstTotal;
    private TextView tvISSS;
    private TextView tvAFP;
    private TextView tvRenta;
    private TextView tvTotal;
    private Button btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvFirstTotal = findViewById(R.id.tvTotal);
        tvISSS = findViewById(R.id.tvISSS);
        tvAFP = findViewById(R.id.tvAFP);
        tvRenta = findViewById(R.id.tvRenta);
        tvTotal = findViewById(R.id.tvTotalTwo);
        btnOut = findViewById(R.id.btnClose);

        Bundle bundle = getIntent().getExtras();
        Double valueHour = bundle.getDouble("VALUE_HOUR");
        Double hour = bundle.getDouble("HOUR");

        Double isss = (valueHour * hour) * 0.02;
        Double afp = (valueHour * hour) * 0.03;
        Double renta = (valueHour * hour) * 0.04;
        Double total = (valueHour * hour) - (isss + afp + renta);

        tvFirstTotal.setText("Total sin descuento: "+ ( valueHour * hour ) + "");
        tvISSS.setText("ISSS (2%): "+ String.format("%.2f", isss) + "");
        tvAFP.setText("AFP (3%): "+ String.format("%.2f", afp) + "");
        tvRenta.setText("Renta (4%): : "+ String.format("%.2f", renta) + "");
        tvTotal.setText("Salario neto: "+ String.format("%.2f", total) + "");

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
