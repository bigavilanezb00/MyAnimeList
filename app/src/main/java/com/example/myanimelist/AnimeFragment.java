package com.example.myanimelist;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class AnimeFragment extends Fragment {

    ImageButton toradora;
    ImageButton kaguya;
    ImageButton bunny_girl;
    ImageButton nisekoi;
    ImageButton horimiya;
    NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);

        toradora = view.findViewById(R.id.toradora);
        kaguya = view.findViewById(R.id.kaguya);
        bunny_girl = view.findViewById(R.id.bunny_girl);
        nisekoi = view.findViewById(R.id.nisekoi);
        horimiya = view.findViewById(R.id.horimiya);

        toradora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_animeFragment_to_toradoraFragment);
            }
        });

        kaguya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_animeFragment_to_kaguyaFragment);
            }
        });

        bunny_girl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_animeFragment_to_bunnyGirlFragment);
            }
        });

        nisekoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_animeFragment_to_nisekoiFragment);
            }
        });

        horimiya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_animeFragment_to_horimiyaFragment);
            }
        });

    }
}