package com.example.mitransporteonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class Comprapremium extends AppCompatActivity implements View.OnClickListener
{
    ImageButton atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprapremium);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        atras = findViewById(R.id.atras);
        atras.setOnClickListener(this);
        boolean hayConexion = ConexionInternet.hayConexionInternet(this);
        if (hayConexion)
        {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.URL)));


            if (intent.resolveActivity(getPackageManager()) != null)
            {

                startActivity(intent);
            }
            else
            {

            }
        }

    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == atras.getId())
        {
            boolean hayConexion = ConexionInternet.hayConexionInternet(this);
            if (hayConexion)
            {
                Intent Inicio = new Intent(this, Principal.class);
                startActivity(Inicio);
                finish();
            }
            else
            {
                Toast.makeText(this, "¡¡Sin conexión a internet!!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}