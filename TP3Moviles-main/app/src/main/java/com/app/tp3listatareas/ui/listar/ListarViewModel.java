package com.app.tp3listatareas.ui.listar;

import static com.app.tp3listatareas.MainActivity.autosLista;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.tp3listatareas.MainActivity;
import com.app.tp3listatareas.Modelo.Auto;

import java.util.Collections;
import java.util.Comparator;

public class ListarViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> listaOrdenadaBool;

    public ListarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getListaOrdenadaBool() {
        if (listaOrdenadaBool == null) {
            listaOrdenadaBool = new MutableLiveData<>();
        }
        return listaOrdenadaBool;
    }

    public void ordenarLista() {
        if (!autosLista.isEmpty()) {

            Collections.sort(autosLista, new Comparator<Auto>() {
                @Override
                public int compare(Auto auto1, Auto auto2) {
                    double precio1 = Double.parseDouble(auto1.getPrecio());
                    double precio2 = Double.parseDouble(auto2.getPrecio());
                    return Double.compare(precio2, precio1);
                }
            });
            listaOrdenadaBool.setValue(true);
        } else {
            Toast.makeText(getApplication(), "No hay autos guardados.", Toast.LENGTH_LONG).show();
        }
    }
}
