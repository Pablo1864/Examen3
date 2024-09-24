package com.app.tp3listatareas.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.app.tp3listatareas.databinding.FragmentCargarBinding;


public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel vm;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(CargarViewModel.class);
        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Escuchar cuando se agrega un auto para limpiar los campos
        vm.getAutoAgregadoLiveData().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean agregado) {
                if (agregado) {
                    limpiarCampos();
                }
            }
        });

        binding.btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener valores de los campos
                String patente = binding.editPatente.getText().toString().trim();
                String marca = binding.editMarca.getText().toString().trim();
                String modelo = binding.editModelo.getText().toString().trim();
                String precio = binding.editPrecio.getText().toString().trim();

                // Validar y agregar el auto a través del ViewModel
                vm.validarYAgregarAuto(patente, marca, modelo, precio);
            }
        });

        return root;
    }

    private void limpiarCampos() {
        binding.editPatente.setText("");
        binding.editMarca.setText("");
        binding.editModelo.setText("");
        binding.editPrecio.setText("");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


