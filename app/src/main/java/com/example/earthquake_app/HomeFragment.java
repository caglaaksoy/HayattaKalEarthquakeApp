package com.example.earthquake_app;

import android.content.Context;
import android.content.Intent;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;


public class HomeFragment extends Fragment {

    private MediaPlayer mpButtonDuduk;
    private boolean isPlaying;
    private ImageButton buttonduduk;

    private CameraManager cameraManager;
    private String cameraId;
    private boolean flashOn;
    private ImageButton imageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Duduk buttonu
        mpButtonDuduk = MediaPlayer.create(getActivity(), R.raw.depremduduk);
        buttonduduk = view.findViewById(R.id.duduk);
        isPlaying = false;

        buttonduduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlaying) {
                    mpButtonDuduk.start();
                    isPlaying = true;
                    buttonduduk.setImageResource(R.drawable.whistleasset);
                } else {
                    mpButtonDuduk.pause();
                    isPlaying = false;
                    buttonduduk.setImageResource(R.drawable.whistleasset);
                }
            }
        });

        // Flash acma
        cameraManager = (CameraManager) getActivity().getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        flashOn = false;
        imageButton = view.findViewById(R.id.fener);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (!flashOn) {
                        cameraManager.setTorchMode(cameraId, true);
                        flashOn = true;
                        imageButton.setImageResource(R.drawable.flashlight_on);
                    } else {
                        cameraManager.setTorchMode(cameraId, false);
                        flashOn = false;
                        imageButton.setImageResource(R.drawable.flashlight_off);
                    }
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        //flash end

        //112 start
        Button callButton = view.findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "112";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });
        //112 end

        return view;
    }
}