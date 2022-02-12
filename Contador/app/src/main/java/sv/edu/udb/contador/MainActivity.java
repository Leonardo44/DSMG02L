package sv.edu.udb.contador;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MainViewModel viewModel;
    private TextView tvValue;
    private Button btnAddValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvValue = findViewById(R.id.tvValue);
        btnAddValue = findViewById(R.id.btnAddValue);

        // Get the ViewModel.
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        // Create the observer which updates the UI.
        final Observer<Integer> valueObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer newValue) {
                // Update the UI, in this case, a TextView.
                tvValue.setText("" + newValue +"");
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.getCurrentValue().observe(this, valueObserver);

        btnAddValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addValue();
            }
        });
    }
}
