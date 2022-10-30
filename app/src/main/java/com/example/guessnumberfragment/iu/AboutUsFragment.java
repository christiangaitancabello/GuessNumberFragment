package com.example.guessnumberfragment.iu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guessnumberfragment.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

/**
 * Clase que muestra el contenido del Fragment AboutUs.
 */
public class AboutUsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AboutView view = AboutBuilder.with(getContext())
                .setName(getResources().getString(R.string.creatorName))
                .setSubTitle(getResources().getString(R.string.aboutUsSubtitle))
                .setBrief(getResources().getString(R.string.creatorContact))
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGitHubLink(getResources().getString(R.string.creatorGitHubUser))
                .addLinkedInLink(getResources().getString(R.string.creatorLinkedIn))
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();

        return view;
    }
}