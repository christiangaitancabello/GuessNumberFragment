package com.example.guessnumberfragment.iu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guessnumberfragment.R;
import com.example.guessnumberfragment.data.Game;
import com.example.guessnumberfragment.databinding.FragmentEndGameBinding;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;

/**
 * Clase donde se muestra el resultado del juego.
 */
public class EndGameFragment extends Fragment {
    // Variable de tipo FragmentEndGameBinding donde se recogen los elementos del XML activity_end_game.
    private FragmentEndGameBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentEndGameBinding.inflate(getLayoutInflater());

        binding.btnPlayAgain.setOnClickListener(view -> PlayAgain());

        binding.setGame(getArguments().getParcelable(Game.KEY));
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ShowResult();
    }

    /**
     * Método para mostrar el resultado del juego.
     */
    private void ShowResult() {
        if (binding.getGame().getWin()) {
            binding.konfettiView.setOnClickListener(view -> explode());
            binding.tvWinLose.setTextColor(ContextCompat.getColor(getView().getContext(), R.color.green));
            binding.tvWinLose.setText(getResources().getString(R.string.VictoryTitle));
            binding.tvMessage.setText(getResources().getString(R.string.VictoryMessage) + " " + binding.getGame().getnTries());

        }
        else {
            binding.tvWinLose.setTextColor(ContextCompat.getColor(getView().getContext(), R.color.red));
            binding.tvWinLose.setText(getResources().getString(R.string.LoseTitle));
            binding.tvMessage.setText(getResources().getString(R.string.LoseMessage) + " " + binding.getGame().getnToGuess());
        }
    }

    /**
     * Método que se llama cuando se pulsa sobre el botón btPlayAgain definido en el XML, manda
     * al usuario al ConfigFragment, simulando un reinicio de la aplicación.
     */
    private void PlayAgain() {
        NavHostFragment.findNavController(this).navigate(R.id.action_endGameFragment_to_configFragment);
    }

    /**
     * Este método es el que se llama cuando se pulsa sobre la vista konfettiView definido en el XML,
     * en caso de que el usuario haya ganado, en el que se hace uso de la librería konfetti.
     *
     * @see nl.dionsegijn.konfetti
     */
    public void explode() {
        Random r = new Random();

        EmitterConfig emitterConfig = new Emitter(100L, TimeUnit.MILLISECONDS).max(100);
        binding.konfettiView.start(
                new PartyFactory(emitterConfig)
                        .spread(360)
                        .shapes(Arrays.asList(Shape.Square.INSTANCE, Shape.Circle.INSTANCE, new Shape.DrawableShape(ContextCompat.getDrawable(getView().getContext().getApplicationContext(), R.drawable.ic_heart), true)))
                        .colors(Arrays.asList(0xfce18a, 0xff726d, 0xf4306d, 0xb48def))
                        .setSpeedBetween(0f, 30f)
                        .position(new Position.Relative(r.nextDouble(), r.nextDouble()))
                        .build()
        );
    }
}