package com.example.mitransporteonline;

import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.ads.MobileAds;
import android.Manifest;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.LoadAdError;

import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private View backgroundBar;
    private TextView text;
    private ImageView button;
    private float initialX;
    private InterstitialAd mInterstitialAd;
    private AlertDialog alertDialog;
    private static final String TAG = "MainActivity";
    private AdManager adManager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Usuario usuario = new Usuario();
        if (!Usuario.isCerrosesion())
        {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SharedPreferences prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        if (!prefs.getBoolean("Politicas", false))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            alertDialog = builder.create();

            mostrarAlertDialog();
        }


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        FirebaseApp.initializeApp(this);

        //ocultar navehación
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        /*anuncio*/
        adManager = AdManager.getInstance();

        // Cargar el anuncio
        cargarAnuncio();




        frameLayout = findViewById(R.id.frame_layout);
        backgroundBar = findViewById(R.id.background_bar);
        text = findViewById(R.id.text);
        button = findViewById(R.id.button);

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = event.getX();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getX() - initialX;
                        float translationX = button.getTranslationX() + deltaX;
                        updateButtonPosition(translationX);
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isButtonFullyActivated()) {
                            // Activar el botón
                        } else {
                            // Desactivar el botón
                        }
                        break;
                }
                return true;
            }

            private void updateButtonPosition(float translationX) {
                float maxTranslationX = backgroundBar.getWidth() - button.getWidth();
                translationX = Math.min(Math.max(translationX, 0), maxTranslationX);
                button.setTranslationX(translationX);
                float progress = translationX / maxTranslationX;
                arrancar(progress);
            }

            private void arrancar(float progress) {

                if (progress == 1.0) {
                    SharedPreferences prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
                    String correo = prefs.getString("email", null);
                    if (correo != null) {
                        Intent principal = new Intent(MainActivity.this, com.example.mitransporteonline.Principal.class);
                        startActivity(principal);
                        finish();
                    } else {
                        Intent bienvenido = new Intent(MainActivity.this, bienvenido.class);
                        startActivity(bienvenido);
                        finish();
                    }
                }
            }

            private boolean isButtonFullyActivated() {
                return button.getTranslationX() >= backgroundBar.getWidth() - button.getWidth();
            }
        });
    }

    // Define a method to set the FullScreenContentCallback
    private void cargarAnuncio() {
        AdRequest adRequest = new AdRequest.Builder().build(); // Aquí debes crear la solicitud de anuncio
        // Código para cargar el anuncio
        InterstitialAd.load(this, "ca-app-pub-6213229684878531/5660226377", adRequest,
                new InterstitialAdLoadCallback() {

                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // Guardar una referencia al anuncio cargado en el AdManager
                        adManager.setInterstitialAd(interstitialAd);
                    }
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }

                    // Otros métodos de la carga del anuncio
                });
    }

    private void mostrarAlertDialog() {



        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.politicas, null);


        TextView titleTextView = dialogView.findViewById(R.id.dialog_title);

        CheckBox checkBox = dialogView.findViewById(R.id.chequeado);
        

        TextView messageTextView = dialogView.findViewById(R.id.dialog_message);

        TextView aceptar = dialogView.findViewById(R.id.aceptar);



        // Usar alertDialog directamente
        alertDialog.setView(dialogView);
        alertDialog.setCancelable(false); // Impide que el diálogo se cierre al tocar fuera de él

        // Mueve esta línea aquí para mostrar el diálogo antes de asignar el OnClickListener
        alertDialog.show();

        aceptar.setOnClickListener(view -> {
            if (checkBox.isChecked())
            {

                alertDialog.dismiss(); // Usa directamente el objeto AlertDialog para cerrar el diálogo   
                System.out.println("debe cerrarse");
                SharedPreferences prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("Politicas", true);
                editor.apply();
            }
            else
            {
                Toast.makeText(this, "Primero debes aceptar los terminos y condiciones", Toast.LENGTH_SHORT).show();
            }
            // Acciones cuando se hace clic en el botón Aceptar
            
           
        });
    }


}


