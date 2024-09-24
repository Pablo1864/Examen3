package com.app.tp3listatareas.ui.cargar;

import static com.app.tp3listatareas.MainActivity.autosLista;


import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.app.tp3listatareas.MainActivity;
import com.app.tp3listatareas.Modelo.Auto;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CargarViewModel extends AndroidViewModel {

    private MutableLiveData<List<Auto>> autosLiveData;
    private MutableLiveData<Boolean> autoAgregadoLiveData; // Para notificar cuando se agrega un auto
    private Set<String> patentesSet;

    public CargarViewModel(@NonNull Application application) {
        super(application);
        autosLiveData = new MutableLiveData<>(autosLista);
        autoAgregadoLiveData = new MutableLiveData<>();

        patentesSet = new HashSet<>();
        for (Auto auto : autosLista) {
            patentesSet.add(auto.getPatente());
        }
    }

    public LiveData<List<Auto>> getAutosLiveData() {
        return autosLiveData;
    }

    public LiveData<Boolean> getAutoAgregadoLiveData() {
        return autoAgregadoLiveData;
    }

    public void validarYAgregarAuto(String patente, String marca, String modelo, String precio) {
        if (patente.isEmpty() || marca.isEmpty() || modelo.isEmpty() || precio.isEmpty()) {
            Toast.makeText(getApplication(), "Por favor llene los campos vacios", Toast.LENGTH_SHORT).show();
        } else if (patentesSet.contains(patente)) {
            Toast.makeText(getApplication(), "Ya existe un auto con esa patente", Toast.LENGTH_SHORT).show();
        } else {
            Auto nuevoAuto = new Auto(patente, marca, modelo, precio);
            MainActivity.autosLista.add(nuevoAuto);

            patentesSet.add(patente);

            autosLiveData.setValue(MainActivity.autosLista);
            Toast.makeText(getApplication(), "¡Auto agregado con éxito!", Toast.LENGTH_SHORT).show();

            autoAgregadoLiveData.setValue(true);
        }
    }
}

