package com.example.guessnumberfragment.iu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.guessnumberfragment.R;
import com.example.guessnumberfragment.data.Game;
import com.example.guessnumberfragment.data.User;
import com.example.guessnumberfragment.databinding.FragmentPlayBinding;

import java.util.Random;

/**
 * Clase donde se gestiona el juego.
 */
public class PlayFragment extends Fragment {
    // Variable de tipo FragmentPlayBinding donde se recogen los elementos del XML activity_play.
    private FragmentPlayBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Random random = new Random();

        binding = FragmentPlayBinding.inflate(getLayoutInflater());

        binding.setGame(new Game());
        binding.getGame().setnTries(0);
        binding.getGame().setnToGuess(random.nextInt(100) + 1);

        binding.btTryAgain.setOnClickListener(view -> TryAgain());
        binding.btTryAgain.setEnabled(false);

        binding.btTry.setOnClickListener(view -> GuessTry());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setUser(getArguments().getParcelable(User.KEY));
        UpdateInfoTries();
    }

    /**
     * Este método es el que se llama cuando se pulsa sobre el botón btTryAgain definido en el XML.
     */
    private void TryAgain() {
        binding.tvInfoMoreLess.setText("");
        binding.etGuessNumber.setEnabled(true);
        binding.etGuessNumber.setText("");
        binding.btTry.setEnabled(true);
        binding.btTryAgain.setEnabled(false);
    }

    /**
     * Método que informa al jugador de cuantos intentos lleva y el número de intentos que tiene en total.
     */
    private void UpdateInfoTries() {
        binding.tvInfoNTries.setText(getResources().getString(R.string.NTriesMessage)+ " " + binding.getGame().getnTries() + "/" + binding.getUser().getNtries());
    }

    /**
     * Este método es el que se llama cuando se pulsa sobre el botón btTry definido en el XML.
     */
    private void GuessTry() {
        if (binding.etGuessNumber.getText().toString().equals("")) {
            Toast.makeText(getView().getContext(), getResources().getString(R.string.NeedNumberMessage), Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            binding.getGame().setnTries(binding.getGame().getnTries() + 1);
            if (Integer.parseInt(binding.etGuessNumber.getText().toString()) > binding.getGame().getnToGuess()) {
                binding.tvInfoMoreLess.setText(getResources().getString(R.string.LowerNumberMessage));
            } else if (Integer.parseInt(binding.etGuessNumber.getText().toString()) < binding.getGame().getnToGuess()) {
                binding.tvInfoMoreLess.setText(getResources().getString(R.string.BiggerNumberMessage));
            } else {
                binding.getGame().setWin(true);
                EndGame();
                return;
            }
        } catch (Exception e) {
            binding.getGame().setnTries(binding.getGame().getnTries() - 1);
        }


        UpdateInfoTries();
        binding.etGuessNumber.setEnabled(false);
        binding.btTry.setEnabled(false);
        binding.btTryAgain.setEnabled(true);

        if (binding.getGame().getnTries() == Integer.parseInt(binding.getUser().getNtries())) {
            EndGame();
        }
    }

    /**
     * Método que lanza la Activity EndGame pasando información de si el usuario ha ganado o perdido.
     */
    private void EndGame() {
        Bundle bundle = new Bundle();

        bundle.putParcelable(Game.KEY, binding.getGame());

        NavHostFragment.findNavController(this).navigate(R.id.action_playFragment_to_endGameFragment, bundle);
    }
}