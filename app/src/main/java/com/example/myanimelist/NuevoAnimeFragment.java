package com.example.myanimelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.myanimelist.databinding.FragmentNuevoAnimeBinding;


public class NuevoAnimeFragment extends Fragment {

    private FragmentNuevoAnimeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentNuevoAnimeBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        AnimesViewModel animesViewModel = new ViewModelProvider(requireActivity()).get(AnimesViewModel.class);
        NavController navController = Navigation.findNavController(view);

        binding.agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.nombre.getText().toString();
                String descripcion = binding.descripcion.getText().toString();

                animesViewModel.insertar(new Anime(nombre, descripcion));

                navController.popBackStack();
            }
        });

    }
}