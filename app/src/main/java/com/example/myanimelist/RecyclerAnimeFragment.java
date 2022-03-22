package com.example.myanimelist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.myanimelist.databinding.FragmentRecyclerAnimeBinding;
import com.example.myanimelist.databinding.ViewholderAnimeBinding;

import java.util.List;


public class RecyclerAnimeFragment extends Fragment {

    private FragmentRecyclerAnimeBinding binding;
    AnimesViewModel animesViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentRecyclerAnimeBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animesViewModel = new ViewModelProvider(requireActivity()).get(AnimesViewModel.class);
        navController = Navigation.findNavController(view);

        binding.addAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_global_nuevoAnimeFragment);
            }
        });


        AnimesAdapter animesAdapter;
        animesAdapter = new AnimesAdapter();

        binding.recyclerView.setAdapter(animesAdapter);

        animesViewModel.obtener().observe(getViewLifecycleOwner(), new Observer<List<Anime>>() {
            @Override
            public void onChanged(List<Anime> animes) {
                animesAdapter.establecerLista(animes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.RIGHT  | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Anime anime = animesAdapter.obtenerAnime(posicion);
                animesViewModel.eliminar(anime);

            }
        }).attachToRecyclerView(binding.recyclerView);




    }

    class AnimesAdapter extends RecyclerView.Adapter<AnimeViewHolder> {

        List<Anime> animes;

        @NonNull
        @Override
        public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new AnimeViewHolder(ViewholderAnimeBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {

            Anime anime = animes.get(position);

            holder.binding.nombre.setText(anime.nombre);
            holder.binding.valoracion.setRating(anime.puntuacion);

            holder.binding.valoracion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    if(fromUser) {
                        animesViewModel.actualizar(anime, rating);
                    }
                }
            });


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animesViewModel.seleccionar(anime);
                    navController.navigate(R.id.action_global_mostrarAnimeFragment);
                }
            });
        }


        @Override
        public int getItemCount() {
            return animes != null ? animes.size() : 0;
        }


        public void establecerLista(List<Anime> animes){
            this.animes = animes;
            notifyDataSetChanged();
        }

        public Anime obtenerAnime(int posicion){
            return animes.get(posicion);
        }

    }

    class AnimeViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderAnimeBinding binding;

        public AnimeViewHolder(ViewholderAnimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}