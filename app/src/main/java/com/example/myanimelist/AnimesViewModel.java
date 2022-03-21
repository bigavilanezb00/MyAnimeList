package com.example.myanimelist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class AnimesViewModel  extends AndroidViewModel {

    AnimeRepositorio animeRepositorio;

    MutableLiveData<List<Anime>> listAnimesMutableLiveData = new MutableLiveData<>();

    public AnimesViewModel(@NonNull Application application) {
        super(application);

        animeRepositorio = new AnimeRepositorio();

        listAnimesMutableLiveData.setValue(animeRepositorio.obtener());
    }

    MutableLiveData<Anime> animeSeleccionado = new MutableLiveData<>();

    void seleccionar(Anime anime){
        animeSeleccionado.setValue(anime);
    }

    MutableLiveData<Anime> seleccionado(){
        return animeSeleccionado;
    }


    MutableLiveData<List<Anime>> obtener() {
        return listAnimesMutableLiveData;
    }

    void insertar(Anime anime) {
        animeRepositorio.insertar(anime, new AnimeRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Anime> animes) {
                listAnimesMutableLiveData.setValue(animes);
            }
        });
    }

    void eliminar(Anime anime) {
        animeRepositorio.eliminar(anime, new AnimeRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Anime> animes) {
                listAnimesMutableLiveData.setValue(animes);

            }
        });
    }

    void actualizar(Anime anime, float puntuacion){
        animeRepositorio.actualizar(anime, puntuacion, new AnimeRepositorio.Callback() {
            @Override
            public void cuandoFinalice(List<Anime> animes) {
                listAnimesMutableLiveData.setValue(animes);
            }
        });
    }

}
