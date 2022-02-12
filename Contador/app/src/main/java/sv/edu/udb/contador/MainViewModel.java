package sv.edu.udb.contador;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<Integer> currentValue;

    public MutableLiveData<Integer> getCurrentValue() {
        if (currentValue == null) {
            currentValue = new MutableLiveData<>(0);
        }
        return currentValue;
    }

    public void addValue() {
        if (currentValue.getValue() < 9) {
            currentValue.setValue(currentValue.getValue() + 1);
        } else {
            currentValue.setValue(0);
        }
    }
}
