package com.example.myanimelist;

import java.util.ArrayList;
import java.util.List;

public class AnimeRepositorio {

    List<Anime> animes = new ArrayList<>();

    interface Callback {
        void cuandoFinalice(List<Anime> animes);
    }

    AnimeRepositorio() {

    }

    List<Anime> obtener() {
        return animes;
    }

    void insertar(Anime anime, Callback callback) {
        animes.add(anime);
        callback.cuandoFinalice(animes);
    }

    void eliminar(Anime anime, Callback callback) {
        animes.remove(anime);
        callback.cuandoFinalice(animes);
    }

    void actualizar(Anime anime, float puntuacion, Callback callback) {
        anime.puntuacion = puntuacion;
        callback.cuandoFinalice(animes);
    }

}
