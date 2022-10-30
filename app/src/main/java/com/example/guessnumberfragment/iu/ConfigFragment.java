package com.example.guessnumberfragment.iu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.guessnumberfragment.GuessNumberApplication;
import com.example.guessnumberfragment.R;
import com.example.guessnumberfragment.data.User;
import com.example.guessnumberfragment.databinding.FragmentConfigBinding;


/**
 * <h1>Proyecto GuessNumberFragment</h1>
 * Este proyecto es un juego donde el usuario deberá de adivinar un número aleatorio entre el 1 y el 100 en un número
 * de intentos establecido por el mismo.
 *
 * @author Christian Gaitán
 * @version 1.0
 *
 * @see android.content.ComponentCallbacks
 * @see android.view.View.OnCreateContextMenuListener
 * @see androidx.fragment.app.Fragment
 * @see androidx.activity.result.ActivityResultCaller
 * @see androidx.core.view.MenuProvider
 * @see androidx.lifecycle.HasDefaultViewModelProviderFactory
 * @see androidx.lifecycle.LifecycleOwner
 * @see androidx.lifecycle.ViewModelStoreOwner
 * @see androidx.savedstate.SavedStateRegistryOwner
 */
public class ConfigFragment extends Fragment implements MenuProvider {
    // Variable de tipo FragmentConfigBinding donde se recogen los elementos del XML activity_config.
    private FragmentConfigBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentConfigBinding.inflate(getLayoutInflater());
        binding.setUser((((GuessNumberApplication)getActivity().getApplication()).getUser()));
        binding.btStartGame.setOnClickListener(view -> StartGame());
        return binding.getRoot();
    }

    /**
     * Método que se llama cuando la vista se ha creado, aquí se manda a crear el menú.
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().addMenuProvider(this, getViewLifecycleOwner(),  Lifecycle.State.RESUMED);
    }

    /**
     * Método que se llama cuando se pulsa sobre el botón btStartGame definido en el XML.
     */
    private void StartGame() {
        try {
            if (binding.getUser().getName() == null || binding.getUser().getName().isEmpty() || binding.getUser().getNtries() == null  || binding.getUser().getNtries().isEmpty() || Integer.parseInt(binding.getUser().getNtries()) < 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            Toast.makeText(getView().getContext(), getResources().getString(R.string.IntroduceValidUser), Toast.LENGTH_SHORT).show();
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putParcelable(User.KEY, binding.getUser());

        NavHostFragment.findNavController(this).navigate(R.id.action_configFragment_to_playFragment, bundle);
    }

    /**
     * Método que se llama para inflar el menú.
     * @param menu
     * @param menuInflater
     */
    @Override
    public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.main, menu);
    }

    /**
     * Método que se llama cuando se selecciona una de las opciones del menú.
     * @param item
     * @return
     */
    @Override
    public boolean onMenuItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_aboutus:
                NavHostFragment.findNavController(this).navigate(R.id.action_configFragment_to_aboutUsFragment);
                break;
        }
        return true;
    }
}