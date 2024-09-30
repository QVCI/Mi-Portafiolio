package com.example.mitransporteonline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class bienvenido extends AppCompatActivity implements View.OnClickListener
{
    private LinearLayout IniciarSesion;
    private LinearLayout Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bienvenido);
    
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        IniciarSesion = findViewById(R.id.IniciarSesion);
        Registro = findViewById(R.id.Registro);

        IniciarSesion.setOnClickListener(this);
        Registro.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == IniciarSesion.getId())
        {
            Intent InicioSesion = new Intent(this, InicioSesion.class);
            startActivity(InicioSesion);
        }
        else
        {
            if (v.getId() == Registro.getId())
            {
                Intent Registro = new Intent(this, Registro.class);
                startActivity(Registro);

            }
        }
    }
}