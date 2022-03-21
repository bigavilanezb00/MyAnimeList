package com.example.myanimelist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myanimelist.databinding.FragmentMostrarAnimeBinding;


public class MostrarAnimeFragment extends Fragment {

    private FragmentMostrarAnimeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mostrar_anime, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnimesViewModel animesViewModel = new ViewModelProvider(requireActivity()).get(AnimesViewModel.class);

        animesViewModel.seleccionado().observe(getViewLifecycleOwner(), new Observer<Anime>() {
            @Override
            public void onChanged(Anime anime) {
                binding.nombre.setText(anime.nombre);
                binding.descripcion.setText(anime.descripcion);
                binding.puntuacion.setRating(anime.puntuacion);

                binding.puntuacion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        if(fromUser){
                            animesViewModel.actualizar(anime, rating);
                        }
                    }
                });
            }
        });

    }
}