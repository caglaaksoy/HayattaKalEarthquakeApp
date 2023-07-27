package com.example.earthquake_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean flashOn;
    private ImageButton imageButton;
    private boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        //toolbar yazı silme
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");  // Başlık metnini siler
        toolbar.setSubtitle("");

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this,drawerLayout,toolbar, R.string.menu_open, R.string.menu_close);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();




        MediaPlayer mpButtonDuduk = MediaPlayer.create(this, R.raw.depremduduk);

        ImageButton buttonduduk = findViewById(R.id.duduk);
        //    Button alinan_button = (Button) myView.findViewById(R.id.button1);
        //DUDUK CALMA START
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
        //DUDUK CALMA END

        //FLASH AÇMA START
        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);

        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        flashOn = false;
        imageButton = findViewById(R.id.fener);

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
        //FLASH ACMA END
        //112 start

        Button callButton = findViewById(R.id.callButton);
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



        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id==R.id.anasayfaitem){
                    loadFragment(new HomeFragment());
                    findViewById(R.id.mainlinear).setVisibility(View.GONE);
                } else if (id==R.id.depremcantasiitem) {
                    loadFragment(new DepremCantaFragment());
                    findViewById(R.id.mainlinear).setVisibility(View.GONE);
                }
                else if (id==R.id.toplanmaalaniitem) {
                    loadFragment(new ToplanmaAlaniFragment());
                    findViewById(R.id.mainlinear).setVisibility(View.GONE);
                }else if (id==R.id.hakkimizdaitem) {
                    loadFragment(new HakkimizdaFragment());
                    findViewById(R.id.mainlinear).setVisibility(View.GONE);
                }else if (id==R.id.bagisyapitem) {
                    loadFragment(new BagisFragment());
                    findViewById(R.id.mainlinear).setVisibility(View.GONE);
                }else if(id==R.id.bizeulasitem) {
                    loadFragment(new PuanlaFragment());
                    findViewById(R.id.mainlinear).setVisibility(View.GONE);
                }
                else if(id==R.id.depremanindayapitem) {
                    loadFragment(new YapilacakFragment());
                    findViewById(R.id.mainlinear).setVisibility(View.GONE);
                }
                else if(id==R.id.depremsonrasiitem) {
                    loadFragment(new DepremSonrasiFragment());
                    findViewById(R.id.mainlinear).setVisibility(View.GONE);
                }
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // İlgili kodu ekleyin
        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}