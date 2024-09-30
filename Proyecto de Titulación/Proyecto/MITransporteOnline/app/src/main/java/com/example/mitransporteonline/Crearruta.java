package com.example.mitransporteonline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.io.OutputStream;

public class Crearruta extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageButton atras;
    private AutoCompleteTextView origen;
    private AutoCompleteTextView destino;
    private TextView vamos;
    private ImageButton guardarruta;
    private static final int CODIGO_PERMISOS_UBICACION = 1;
    private ImageButton metroorigen;
    private ImageButton metrobusorigen;
    private ImageButton cablebusorigen;
    private ImageButton teorigen;
    private ImageButton metrodestino;
    private ImageButton metrobusdestino;
    private ImageButton cablebusdestino;
    private ImageButton tedestion;
    private boolean MetroOrigenseleccionado = false;
    private boolean MetrobusOrigenseleccionado = false;
    private boolean CablebusOrigenseleccionado = false;
    private boolean TeOrigenseleccionado = false;
    private boolean MetroDestinoseleccionado = false;
    private boolean MetrobusDestinoseleccionado = false;
    private boolean CablebusDestinoseleccionado = false;
    private boolean TeDestinoseleccionado = false;
    private ArrayAdapter<String> MetroOrigen;
    private ArrayAdapter<String> MetrobusOrigen;
    private ArrayAdapter<String> CablebusOrigen;
    private ArrayAdapter<String> Teorigen;
    private ArrayAdapter<String> MetroDestino;
    private ArrayAdapter<String> MetrobusDestino;
    private ArrayAdapter<String> CablebusDestino;
    private ArrayAdapter<String> Tedestino;

    int nodoorigen = -1;
    int nododestino = -1;

    int colorgrisclaro = 0xFF555555;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearruta);
        FirebaseApp.initializeApp(this);
        //ocultar navehación
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        atras = findViewById(R.id.atras);
        origen = findViewById(R.id.origen);
        destino = findViewById(R.id.destino);
        vamos = findViewById(R.id.vamos);
        guardarruta = findViewById(R.id.guardarruta);
        guardarruta.setImageResource(R.drawable.iconoguardado);
        guardarruta.setEnabled(true);

        metroorigen = findViewById(R.id.metroorigen);
        metrodestino = findViewById(R.id.metrodestino);
        metrobusorigen = findViewById(R.id.metrobusorigen);
        teorigen = findViewById(R.id.teorigen);

        metrobusdestino = findViewById(R.id.metrobusdestino);
        cablebusorigen = findViewById(R.id.cablebusorigen);
        cablebusdestino = findViewById(R.id.cablebusdestino);
        tedestion = findViewById(R.id.tedestino);

        atras.setOnClickListener(this);

        teorigen.setOnClickListener(this);
        guardarruta.setOnClickListener(this);
        metroorigen.setOnClickListener(this);
        metrobusorigen.setOnClickListener(this);
        metrobusorigen.setOnClickListener(this);

        metrobusdestino.setOnClickListener(this);
        metrodestino.setOnClickListener(this);
        cablebusorigen.setOnClickListener(this);
        cablebusdestino.setOnClickListener(this);
        tedestion.setOnClickListener(this);

        vamos.setOnClickListener(this);


        origen.setEnabled(false);
        destino.setEnabled(false);
        origen.setOnItemClickListener(this);
        destino.setOnItemClickListener(this);


    }
    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        ArrayList<String> estaciones = EstacionesNodos.getEstaciones();

        ArrayList<String> estacionesMetro = EstacionesMetro();
        ArrayList<String> estacionesMetrobus = EstacionesMetrobus();
        ArrayList<String> estacionesCablebus = EstacionesCablebus();
        ArrayList<String> estacionesTransportesElectricos = EstacionesTransportesElectricos();


// Crear conjuntos HashSet para eliminar duplicados



        if (R.id.metroorigen == id)
        {
            MetroOrigen = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estacionesMetro);
            origen.setAdapter(MetroOrigen);
            origen.setEnabled(true);
            origen.setText("");
            MetroOrigenseleccionado = true;
            MetrobusOrigenseleccionado = false;
            CablebusOrigenseleccionado = false;
            TeOrigenseleccionado = false;

            metroorigen.setBackgroundColor(colorgrisclaro);
            metroorigen.setBackgroundColor(colorgrisclaro);
            metrobusorigen.setBackgroundColor(Color.WHITE);
            cablebusorigen.setBackgroundColor(Color.WHITE);
            teorigen.setBackgroundColor(Color.WHITE);
        }
        else
        {
            if (R.id.metrobusorigen == id)
            {
                MetrobusOrigen = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estacionesMetrobus);
                origen.setAdapter(MetrobusOrigen);
                origen.setEnabled(true);
                origen.setText("");
                MetroOrigenseleccionado = false;
                MetrobusOrigenseleccionado = true;
                CablebusOrigenseleccionado = false;
                TeOrigenseleccionado = false;

                metroorigen.setBackgroundColor(Color.WHITE);
                metrobusorigen.setBackgroundColor(colorgrisclaro);
                cablebusorigen.setBackgroundColor(Color.WHITE);
                teorigen.setBackgroundColor(Color.WHITE);
            }
            else
            {
                if (R.id.cablebusorigen == id)
                {
                    CablebusOrigen = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estacionesCablebus);
                    origen.setAdapter(CablebusOrigen);
                    origen.setEnabled(true);
                    origen.setText("");
                    MetroOrigenseleccionado = false;
                    MetrobusOrigenseleccionado = false;
                    CablebusOrigenseleccionado = true;
                    TeOrigenseleccionado = false;

                    metroorigen.setBackgroundColor(Color.WHITE);
                    metrobusorigen.setBackgroundColor(Color.WHITE);
                    cablebusorigen.setBackgroundColor(colorgrisclaro);
                    teorigen.setBackgroundColor(Color.WHITE);
                }
                else
                {
                    if (R.id.teorigen == id)
                    {
                        Teorigen = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estacionesTransportesElectricos);
                        origen.setAdapter(Teorigen);
                        origen.setEnabled(true);
                        origen.setText("");
                        MetroOrigenseleccionado = false;
                        MetrobusOrigenseleccionado = false;
                        CablebusOrigenseleccionado = false;
                        TeOrigenseleccionado = true;
                        metroorigen.setBackgroundColor(Color.WHITE);
                        metrobusorigen.setBackgroundColor(Color.WHITE);
                        cablebusorigen.setBackgroundColor(Color.WHITE);
                        teorigen.setBackgroundColor(colorgrisclaro);
                    }
                }
            }
        }



        if (R.id.metrodestino == id)
        {
            MetroDestino = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estacionesMetro);
            destino.setAdapter(MetroDestino);
            destino.setEnabled(true);
            destino.setText("");
            MetroDestinoseleccionado = true;
            MetrobusDestinoseleccionado = false;
            CablebusDestinoseleccionado = false;
            TeDestinoseleccionado = false;

            metrodestino.setBackgroundColor(colorgrisclaro);
            metrobusdestino.setBackgroundColor(Color.WHITE);
            cablebusdestino.setBackgroundColor(Color.WHITE);
            tedestion.setBackgroundColor(Color.WHITE);
        }
        else
        {
            if (R.id.metrobusdestino == id)
            {
                MetrobusDestino = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estacionesMetrobus);
                destino.setAdapter(MetrobusDestino);
                destino.setEnabled(true);
                destino.setText("");
                MetroDestinoseleccionado = false;
                MetrobusDestinoseleccionado = true;
                CablebusDestinoseleccionado = false;
                TeDestinoseleccionado = false;

                metrodestino.setBackgroundColor(Color.WHITE);
                metrobusdestino.setBackgroundColor(colorgrisclaro);
                cablebusdestino.setBackgroundColor(Color.WHITE);
                tedestion.setBackgroundColor(Color.WHITE);

            }
            else
            {
                if (R.id.cablebusdestino == id)
                {
                    CablebusDestino = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estacionesCablebus);
                    destino.setAdapter(CablebusDestino);
                    destino.setEnabled(true);
                    destino.setText("");
                    MetroDestinoseleccionado = false;
                    MetrobusDestinoseleccionado = false;
                    CablebusDestinoseleccionado = true;
                    TeDestinoseleccionado = false;


                    metrodestino.setBackgroundColor(Color.WHITE);
                    metrobusdestino.setBackgroundColor(Color.WHITE);
                    cablebusdestino.setBackgroundColor(colorgrisclaro);
                    tedestion.setBackgroundColor(Color.WHITE);
                }
                else
                {
                    if (R.id.tedestino == id)
                    {
                        Tedestino = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estacionesTransportesElectricos);
                        destino.setAdapter(Tedestino);
                        destino.setEnabled(true);
                        destino.setText("");
                        MetroDestinoseleccionado = false;
                        MetrobusDestinoseleccionado = false;
                        CablebusDestinoseleccionado = false;
                        TeDestinoseleccionado = true;

                        metrodestino.setBackgroundColor(Color.WHITE);
                        metrobusdestino.setBackgroundColor(Color.WHITE);
                        cablebusdestino.setBackgroundColor(Color.WHITE);
                        tedestion.setBackgroundColor(colorgrisclaro);

                    }
                }
            }
        }
        if (R.id.vamos == id || R.id.guardarruta == id)
        {
            if (nodoorigen != -1 && nododestino != -1)
            {
                if (nodoorigen == nododestino)
                {
                    Toast.makeText(this, "No puedes seleccionar el mismo origen y destino", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if (R.id.vamos == id)
                    {
                        Estaticos.nodoInicio = nodoorigen + 1;
                        Estaticos.nodoFinal = nododestino + 1;

                        boolean hayConexion = ConexionInternet.hayConexionInternet(this);
                        if (!hayConexion)
                        {
                            Intent menu_o_calcular = new Intent(this, menu_o_costo.class);
                            startActivity(menu_o_calcular);

                        }
                        else
                        {
                            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                                Intent menu_o_calcular = new Intent(this, Mapa.class);
                                startActivity(menu_o_calcular);
                            } else {
                                // Los permisos de ubicación no están concedidos, solicitarlos
                                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CODIGO_PERMISOS_UBICACION);
                            }


                        }
                    }
                    if (R.id.guardarruta == id)
                    {
                        Estaticos.nodoInicio = nodoorigen + 1;
                        Estaticos.nodoFinal = nododestino + 1;
                        BaseDatos admin = new BaseDatos(this);
                        SQLiteDatabase basedatos = admin.getWritableDatabase();
                        try
                        {
                            ContentValues registro = new ContentValues();
                            registro.put("ORIGEN", Estaticos.nodoInicio);
                            registro.put("DESTINO", Estaticos.nodoFinal);

                            long result = basedatos.insert("rutas", null, registro);
                            if (result != -1)
                            {
                                Toast.makeText(this, "Ruta Guardada", Toast.LENGTH_SHORT).show();
                                guardarruta.setEnabled(false);
                                guardarruta.setImageResource(R.drawable.guardadorelleno);


                            }
                            else
                            {
                                Toast.makeText(this, "Error al guardar la ruta", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (Exception e)
                        {
                            Toast.makeText(this, "Error: " + e + ", Comunicate con soporte", Toast.LENGTH_LONG).show();
                        }
                        finally
                        {
                            basedatos.close();
                        }

                    }

                }
            }
            else
            {
                if (nodoorigen == 0 && nododestino == 0)
                {
                    Toast.makeText(this, "Primero debes ingresar el origen y destino", Toast.LENGTH_SHORT).show();
                }
                else
                if(nodoorigen == 0)
                {
                    Toast.makeText(this, "Ingrese un origen primero", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "Ingrese un destino primero", Toast.LENGTH_SHORT).show();
                }
            }

        }

       if (R.id.atras == id)
       {

           Intent Principal = new Intent(this, com.example.mitransporteonline.Principal.class);
           startActivity(Principal);
           finish();
       }

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        View v = this.getCurrentFocus();

        boolean origenSeleccionado = false;
        boolean destinoSeleccionado = false;
        if (parent.getAdapter() == MetroOrigen || parent.getAdapter() == MetrobusOrigen || parent.getAdapter() == CablebusOrigen || parent.getAdapter() == Teorigen)
        {
            String estacionorigennombre = (String) parent.getItemAtPosition(position);
            int indexorigen = -1;

            if (MetroOrigenseleccionado)
            {
                for (int i = EstacionesNodos.getIniciometro()-1; i < EstacionesNodos.getFinmetro(); i++)
                {
                    if (estacionorigennombre.equals(EstacionesNodos.getEstaciones().get(i)))
                    {
                        indexorigen = i;
                        nodoorigen = indexorigen;
                        System.out.println(nodoorigen);
                        origenSeleccionado = true;

                        if (v != null) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        }

                        break;
                    }
                }
            }
            else
            {
                if (MetrobusOrigenseleccionado)
                {
                    for (int i = EstacionesNodos.getIniciometrobusl1()-1; i < EstacionesNodos.getFinmetrobusl7(); i++)
                    {
                        if (estacionorigennombre.equals(EstacionesNodos.getEstaciones().get(i)))
                        {
                            indexorigen = i;
                            origenSeleccionado = true;
                            nodoorigen = indexorigen;
                            System.out.println(nodoorigen);
                            if (v != null) {
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            }

                            break;
                        }
                    }
                }
                else
                {
                    if (CablebusOrigenseleccionado)
                    {
                        for (int i = EstacionesNodos.getIniciocablebusl1()-1; i < EstacionesNodos.getFincablebusl2(); i++)
                        {
                            if (estacionorigennombre.equals(EstacionesNodos.getEstaciones().get(i)))
                            {
                                indexorigen = i;
                                origenSeleccionado = true;
                                nodoorigen = indexorigen;
                                System.out.println(nodoorigen);
                                if (v != null) {
                                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                }

                                break;
                            }
                        }
                    }
                    else
                    {
                        if (TeOrigenseleccionado)
                        {
                            for (int i = EstacionesNodos.getIniciotrenligero()-1; i < EstacionesNodos.getFintrolebusl10(); i++)
                            {
                                if (estacionorigennombre.equals(EstacionesNodos.getEstaciones().get(i)))
                                {
                                    indexorigen = i;
                                    origenSeleccionado = true;
                                    nodoorigen = indexorigen;
                                    System.out.println(nodoorigen);
                                    if (v != null) {
                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                    }

                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        else
        {
            if (parent.getAdapter() == MetroDestino || parent.getAdapter() == MetrobusDestino || parent.getAdapter() == CablebusDestino || parent.getAdapter() == Tedestino)
            {
                int indexdestino = -1;
                String estaciondestinonombre = (String) parent.getItemAtPosition(position);
                if (MetroDestinoseleccionado)
                {
                    for (int i = EstacionesNodos.getIniciometro()-1; i < EstacionesNodos.getFinmetro(); i++)
                    {
                        if (estaciondestinonombre.equals(EstacionesNodos.getEstaciones().get(i)))
                        {
                            indexdestino = i;
                            nododestino = indexdestino;
                            destinoSeleccionado = true;
                            System.out.println(nododestino);
                            if (v != null) {
                                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            }

                            break;
                        }
                    }
                }
                else
                {
                    if (MetrobusDestinoseleccionado)
                    {

                        for (int i = EstacionesNodos.getIniciometrobusl1()-1; i < EstacionesNodos.getFinmetrobusl7(); i++)
                        {
                            if (estaciondestinonombre.equals(EstacionesNodos.getEstaciones().get(i)))
                            {
                                indexdestino = i;
                                nododestino = indexdestino;
                                System.out.println(nododestino);
                                destinoSeleccionado = true;
                                if (v != null) {
                                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                }

                                break;
                            }
                        }
                    }
                    else
                    {
                        if (CablebusDestinoseleccionado)
                        {
                            for (int i = EstacionesNodos.getIniciocablebusl1()-1; i < EstacionesNodos.getFincablebusl2(); i++)
                            {
                                if (estaciondestinonombre.equals(EstacionesNodos.getEstaciones().get(i)))
                                {
                                    indexdestino = i;
                                    nododestino = indexdestino;
                                    System.out.println(nododestino);
                                    destinoSeleccionado = true;
                                    if (v != null) {
                                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                    }
                                    break;

                                }
                            }
                        }
                        else
                        {
                            if (TeDestinoseleccionado)
                            {
                                for (int i = EstacionesNodos.getIniciotrenligero()-1; i < EstacionesNodos.getFintrolebusl10(); i++)
                                {
                                    if (estaciondestinonombre.equals(EstacionesNodos.getEstaciones().get(i)))
                                    {
                                        indexdestino = i;
                                        nododestino = indexdestino;
                                        System.out.println(nododestino);
                                        destinoSeleccionado = true;
                                        if (v != null) {
                                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                                        }
                                        break;

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private ArrayList<String> EstacionesMetro() {
        ArrayList<String> Estaciones = new ArrayList<>();
        Estaciones.addAll(Inflado("Metro",1, 1, 20));
        Estaciones.addAll(Inflado("Metro",2, 21, 33));
        Estaciones.addAll(Inflado("Metro",2, 35, 44));
        Estaciones.addAll(Inflado("Metro",3, 45, 50));
        Estaciones.addAll(Inflado("Metro",3, 52, 52));
        Estaciones.addAll(Inflado("Metro",3, 54, 65));
        Estaciones.addAll(Inflado("Metro",4, 66, 71));
        Estaciones.addAll(Inflado("Metro",4, 73, 75));
        Estaciones.addAll(Inflado("Metro",5, 77, 81));
        Estaciones.addAll(Inflado("Metro",5, 83, 84));
        Estaciones.addAll(Inflado("Metro",5, 86, 88));
        Estaciones.addAll(Inflado("Metro",6, 89, 94));
        Estaciones.addAll(Inflado("Metro",6, 96, 96));
        Estaciones.addAll(Inflado("Metro",6, 98, 98));
        Estaciones.addAll(Inflado("Metro",7, 101, 103));
        Estaciones.addAll(Inflado("Metro",7, 105, 108));
        Estaciones.addAll(Inflado("Metro",7, 110, 113));
        Estaciones.addAll(Inflado("Metro",8, 114, 114));
        Estaciones.addAll(Inflado("Metro",8, 116, 116));
        Estaciones.addAll(Inflado("Metro",8, 118, 119));
        Estaciones.addAll(Inflado("Metro",8, 121, 121));
        Estaciones.addAll(Inflado("Metro",8, 123, 132));
        Estaciones.addAll(Inflado("Metro",9, 134, 135));
        Estaciones.addAll(Inflado("Metro",9, 137, 137));
        Estaciones.addAll(Inflado("Metro",9, 140, 143));
        Estaciones.addAll(Inflado("Metro","A", 146, 154));
        Estaciones.addAll(Inflado("Metro","B", 155, 165));
        Estaciones.addAll(Inflado("Metro","B", 167, 168));
        Estaciones.addAll(Inflado("Metro","B", 171, 172));
        Estaciones.addAll(Inflado("Metro","B", 175, 175));
        Estaciones.addAll(Inflado("Metro",12, 177, 178));
        Estaciones.addAll(Inflado("Metro",12, 180, 181));
        Estaciones.addAll(Inflado("Metro",12, 183, 183));
        Estaciones.addAll(Inflado("Metro",12, 185, 195));


        return Estaciones;
    }
    private ArrayList<String> EstacionesMetrobus() {
        ArrayList<String> Estaciones = new ArrayList<>();


        Estaciones.addAll(Inflado("Metrobús",1, 196, 241));

        Estaciones.addAll(Inflado("Metrobús",2, 242, 268));
        Estaciones.addAll(Inflado("Metrobús",2, 270, 278));

        Estaciones.addAll(Inflado("Metrobús",3, 279, 283));
        Estaciones.addAll(Inflado("Metrobús",3, 285, 298));
        Estaciones.addAll(Inflado("Metrobús",3, 300, 314));

        Estaciones.addAll(Inflado("Metrobús",4, 317, 328));
        Estaciones.addAll(Inflado("Metrobús",4, 330, 332));
        Estaciones.addAll(Inflado("Metrobús",4, 335, 337));
        Estaciones.addAll(Inflado("Metrobús",4, 339, 358));

        Estaciones.addAll(Inflado("Metrobús",5, 359, 385));
        Estaciones.addAll(Inflado("Metrobús",5, 387, 390));
        Estaciones.addAll(Inflado("Metrobús",5, 394, 409));

        Estaciones.addAll(Inflado("Metrobús",6, 410, 421));
        Estaciones.addAll(Inflado("Metrobús",6, 423, 428));
        Estaciones.addAll(Inflado("Metrobús",6, 430, 434));
        Estaciones.addAll(Inflado("Metrobús",6, 436, 446));

        Estaciones.addAll(Inflado("Metrobús",7, 447, 454));
        Estaciones.addAll(Inflado("Metrobús",7, 457, 457));
        Estaciones.addAll(Inflado("Metrobús",7, 461, 473));

        return Estaciones;
    }
    private ArrayList<String> EstacionesCablebus() {
        ArrayList<String> Estaciones = new ArrayList<>();


        Estaciones.addAll(Inflado("Cablebús",1, 478, 483));
        Estaciones.addAll(Inflado("Cablebús",2, 484, 490));

        return Estaciones;
    }
    private ArrayList<String> EstacionesTransportesElectricos() {
        ArrayList<String> Estaciones = new ArrayList<>();


        Estaciones.addAll(Inflado("Tren Ligero",1, 491, 508));


        Estaciones.addAll(Inflado("Trolebus",1, 509, 561));
        Estaciones.addAll(Inflado("Trolebus",1, 563, 603));

        Estaciones.addAll(Inflado("Trolebus",2, 604, 677));

        Estaciones.addAll(Inflado("Trolebus",3, 678, 731));

        Estaciones.addAll(Inflado("Trolebus",4, 732, 782));
        Estaciones.addAll(Inflado("Trolebus",4, 786, 818));
        Estaciones.addAll(Inflado("Trolebus",4, 820, 868));

        Estaciones.addAll(Inflado("Trolebus",5, 869, 900));
        Estaciones.addAll(Inflado("Trolebus",5, 903, 940));
        Estaciones.addAll(Inflado("Trolebus",5, 943, 978));

        Estaciones.addAll(Inflado("Trolebus",6, 979, 979));
        Estaciones.addAll(Inflado("Trolebus",6, 983, 985));
        Estaciones.addAll(Inflado("Trolebus",6, 988, 988));
        Estaciones.addAll(Inflado("Trolebus",6, 991, 991));
        Estaciones.addAll(Inflado("Trolebus",6, 993, 994));
        Estaciones.addAll(Inflado("Trolebus",6, 998, 998));
        Estaciones.addAll(Inflado("Trolebus",6, 1000, 1020));
        Estaciones.addAll(Inflado("Trolebus",6, 1022, 1044));
        Estaciones.addAll(Inflado("Trolebus",6, 1047, 1047));
        Estaciones.addAll(Inflado("Trolebus",6, 1050, 1050));
        Estaciones.addAll(Inflado("Trolebus",6, 1053, 1054));
        Estaciones.addAll(Inflado("Trolebus",6, 1061, 1061));
        Estaciones.addAll(Inflado("Trolebus",6, 1068, 1069));
        Estaciones.addAll(Inflado("Trolebus",6, 1074, 1074));



        Estaciones.addAll(Inflado("Trolebus",7, 1077, 1077));
        Estaciones.addAll(Inflado("Trolebus",7, 1082, 1093));
        Estaciones.addAll(Inflado("Trolebus",7, 1095, 1101));
        Estaciones.addAll(Inflado("Trolebus",7, 1103, 1140));
        Estaciones.addAll(Inflado("Trolebus",7, 1142, 1169));





        Estaciones.addAll(Inflado("Trolebus",8, 1170, 1185));
        Estaciones.addAll(Inflado("Trolebus",8, 1187, 1189));
        Estaciones.addAll(Inflado("Trolebus",8, 1191, 1192));
        Estaciones.addAll(Inflado("Trolebus",8, 1198, 1198));
        Estaciones.addAll(Inflado("Trolebus",8, 1204, 1215));


        Estaciones.addAll(Inflado("Trolebus",9, 1217, 1260));
        Estaciones.addAll(Inflado("Trolebus",9, 1263, 1266));

        Estaciones.addAll(Inflado("Trolebus",10, 1267, 1278));


        return Estaciones;
    }

    private ArrayList<String> Inflado(String Sistema, int Linea, int Desde, int Hasta)
    {  ArrayList<String> estaciones = EstacionesNodos.getEstaciones();
        ArrayList<String> Estaciones = new ArrayList<>();
        for (int i = (Desde-1); i < Hasta ; ++i)
        {
            Estaciones.add(estaciones.get(i));

            Log.d("Array"+Sistema, "Estaciones "+Linea+": " + estaciones.get(i)+ ", Nodo: " +(i +1));

        }

        return Estaciones;
    }
    private ArrayList<String> Inflado(String Sistema, String Linea, int Desde, int Hasta)
    {  ArrayList<String> estaciones = EstacionesNodos.getEstaciones();
        ArrayList<String> Estaciones = new ArrayList<>();
        for (int i = (Desde-1); i < Hasta ; ++i)
        {
            Estaciones.add(estaciones.get(i));
            Log.d("Array"+Sistema, "Estaciones "+Linea+": " + estaciones.get(i)+ ", Nodo: " +(i +1));

        }

        return Estaciones;
    }




}
