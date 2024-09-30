package com.example.mitransporteonline;import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.checkerframework.checker.units.qual.A;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Estacion extends BottomSheetDialogFragment implements AdapterView.OnItemClickListener, View.OnClickListener
{
    private String numestacion = "";
    private LinearLayout selecciondesistema;
    private LinearLayout selecciondeestacion;
    private LinearLayout seleccionincidente;
    private LinearLayout seleccionenvio;
    private String num1 = "0";
    private String num2 = "0";
    private int clicks;
    private String num3 = "0";
    private String num4 = "0";
    private AutoCompleteTextView estacion;
    private ImageButton metro;
    private ImageButton metrobus;
    private ImageButton cablebus;
    private ImageButton mandar;
    private ImageButton transporteselectricos;
    private ArrayAdapter<String> MetroOrigen;
    private ArrayAdapter<String> MetrobusOrigen;
    private ArrayAdapter<String> CablebusOrigen;
    private ArrayAdapter<String> TEOrigen;
    private boolean MetroOrigenseleccionado = false;
    private boolean MetrobusOrigenseleccionado = false;
    private boolean CablebusOrigenseleccionado = false;
    private boolean TransportesElectricosOrigenseleccionado = false;
    private boolean vdescompuesto = false;
    private boolean vaccidente = false;
    private boolean vaglomeracion = false;
    private boolean vretraso = false;

    private TextView vehiculodescompuesto;
    private TextView instrucciones;
    private TextView accidente;
    private TextView aglomeracion;
    private TextView retraso;
    int colorgrisclaro = 0xFF555555;
    private View guideOverlay;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {


        return inflater.inflate(R.layout.estacion, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        View decorView = requireActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        Usuario usuario = new Usuario();

        clicks = 0;
        metro = requireView().findViewById(R.id.metro);
        metrobus = requireView().findViewById(R.id.metrobus);
        cablebus = requireView().findViewById(R.id.cablebus);
        transporteselectricos = requireView().findViewById(R.id.transporteselectricos);
        seleccionenvio = requireView().findViewById(R.id.seleccionenvio);
        selecciondesistema = requireView().findViewById(R.id.selecciondesistema);
        seleccionincidente = requireView().findViewById(R.id.seleccionincidente);
        estacion = requireView().findViewById(R.id.estacion);
        mandar = requireView().findViewById(R.id.mandar);

        vehiculodescompuesto = requireView().findViewById(R.id.vehiculodescompuesto);
        accidente = requireView().findViewById(R.id.accidente);
        selecciondeestacion = requireView().findViewById(R.id.selecciondeestacion);
        aglomeracion = requireView().findViewById(R.id.aglomeracion);
        retraso = requireView().findViewById(R.id.retraso);

        estacion.setOnItemClickListener(this);



        if (usuario.isTutorial())
        {

            guideOverlay = requireView().findViewById(R.id.guiadeusuario);
            guideOverlay.setVisibility(View.VISIBLE);
            instrucciones = requireView().findViewById(R.id.instrucciones);
            guideOverlay.setOnClickListener(v ->
            {
                switch (clicks)
                {

                    case 0:
                        primertutorial();
                        clicks++;
                        break;

                    case 1:
                        segundotutorial();

                        break;
                    case 2:
                        tercertutorial();

                        break;
                    case 3:
                        cuartotutorial();

                        break;
                    case 4:
                        quintotutorial();

                        break;
                }
            });
        }
        metro.setOnClickListener(this);
        metrobus.setOnClickListener(this);
        cablebus.setOnClickListener(this);
        mandar.setOnClickListener(this);
        transporteselectricos.setOnClickListener(this);

        vehiculodescompuesto.setOnClickListener(this);
        accidente.setOnClickListener(this);
        aglomeracion.setOnClickListener(this);
        retraso.setOnClickListener(this);
    }
    private void primertutorial()
    {

        instrucciones.setVisibility(View.VISIBLE);


        instrucciones.setText("Selecciona un sistema");


        selecciondesistema.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.metro));

    }
    private void segundotutorial()
    {
        clicks++;
        instrucciones.setVisibility(View.VISIBLE);


        instrucciones.setText("Selecciona la estación a reportar");


        selecciondesistema.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primario));
        selecciondeestacion.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.metro));

    }
    private void tercertutorial()
    {
        clicks++;
        instrucciones.setVisibility(View.VISIBLE);


        instrucciones.setText("Selecciona el o incidentes");


        selecciondeestacion.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primario));
        seleccionincidente.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.metro));

    }

    private void cuartotutorial()
    {
        clicks++;
        instrucciones.setVisibility(View.VISIBLE);


        instrucciones.setText("Presiona para publicar");


        seleccionincidente.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primario));
        seleccionenvio.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.metro));

    }
    private void quintotutorial()
    {
        Usuario usuario = new Usuario();
        usuario.setTutorial(false);
        instrucciones.setVisibility(View.VISIBLE);





        seleccionenvio.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.primario));
        guideOverlay.setVisibility(View.INVISIBLE);
        dismiss();


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        boolean estacionseleccionada = false;
        View v = requireActivity().getCurrentFocus();
        if (parent.getAdapter() == MetroOrigen || parent.getAdapter() == MetrobusOrigen || parent.getAdapter() == CablebusOrigen
                || parent.getAdapter() == TEOrigen)
        {
            String estacionorigennombre = (String) parent.getItemAtPosition(position);
            int indexorigen = 0;
            if (MetroOrigenseleccionado)
            {
                for (int i = EstacionesNodos.getIniciometro() - 1; i < EstacionesNodos.getFinmetro(); i++)
                {
                    if (estacionorigennombre.equals(EstacionesNodos.getEstaciones().get(i)))
                    {
                        indexorigen = i;
                        numestacion = String.valueOf(indexorigen+1);
                        estacionseleccionada = true;
                        vehiculodescompuesto.setEnabled(true);
                        accidente.setEnabled(true);
                        aglomeracion.setEnabled(true);
                        retraso.setEnabled(true);
                        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        View vista = requireView(); // Puedes cambiar esto a la vista específica en la que deseas cerrar el teclado
                        imm.hideSoftInputFromWindow(vista.getWindowToken(), 0);

                        break;
                    }
                }
            }
            if (MetrobusOrigenseleccionado)
            {
                for (int i = EstacionesNodos.getIniciometrobusl1()-1; i < EstacionesNodos.getFinmetrobusl7()-1; i++)
                {
                    if (estacionorigennombre.equals(EstacionesNodos.getEstaciones().get(i))) {
                        indexorigen = i;

                        numestacion = String.valueOf(indexorigen+1);


                        estacionseleccionada = true;
                        vehiculodescompuesto.setEnabled(true);
                        accidente.setEnabled(true);
                        aglomeracion.setEnabled(true);
                        retraso.setEnabled(true);
                        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        View vista = requireView(); // Puedes cambiar esto a la vista específica en la que deseas cerrar el teclado
                        imm.hideSoftInputFromWindow(vista.getWindowToken(), 0);

                        break;
                    }
                }
            }
            if (CablebusOrigenseleccionado)
            {
                for (int i = EstacionesNodos.getIniciocablebusl1()-1; i < EstacionesNodos.getFincablebusl2(); i++)
                {
                    if (estacionorigennombre.equals(EstacionesNodos.getEstaciones().get(i))) {
                        indexorigen = i;

                        numestacion = String.valueOf(indexorigen+1);


                        estacionseleccionada = true;
                        vehiculodescompuesto.setEnabled(true);
                        accidente.setEnabled(true);
                        aglomeracion.setEnabled(true);
                        retraso.setEnabled(true);

                        InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        View vista = requireView(); // Puedes cambiar esto a la vista específica en la que deseas cerrar el teclado
                        imm.hideSoftInputFromWindow(vista.getWindowToken(), 0);
                        break;
                    }
                }
            }
            else
            {
                if (TransportesElectricosOrigenseleccionado)
                {
                    Log.d("Hola", "Estamos en ");
                    for (int i = EstacionesNodos.getIniciotrenligero()-1; i < EstacionesNodos.getFintrolebusl10(); i++)
                    {
                        if (estacionorigennombre.equals(EstacionesNodos.getEstaciones().get(i)))
                        {
                            indexorigen = i;

                            numestacion = String.valueOf(indexorigen+1);


                            estacionseleccionada = true;
                            vehiculodescompuesto.setEnabled(true);
                            accidente.setEnabled(true);
                            aglomeracion.setEnabled(true);
                            retraso.setEnabled(true);

                            InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            View vista = requireView(); // Puedes cambiar esto a la vista específica en la que deseas cerrar el teclado
                            imm.hideSoftInputFromWindow(vista.getWindowToken(), 0);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onClick(View v)
    {

        ArrayList<String> estacionesMetro = EstacionesMetro();
        ArrayList<String> estacionesMetrobus = EstacionesMetrobus();
        ArrayList<String> estacionesCablebus = EstacionesCablebus();
        ArrayList<String> estacionesTE = EstacionesTransportesElectricos();
        if (v.getId() == metro.getId())
        {
            MetroOrigen = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, estacionesMetro);
            estacion.setAdapter(MetroOrigen);
            estacion.setEnabled(true);
            MetroOrigenseleccionado = true;
            MetrobusOrigenseleccionado = false;
            CablebusOrigenseleccionado = false;
            TransportesElectricosOrigenseleccionado = false;
            metro.setBackgroundColor(colorgrisclaro);
            estacion.setText("");
            metrobus.setBackgroundColor(Color.WHITE);
            cablebus.setBackgroundColor(Color.WHITE);
            transporteselectricos.setBackgroundColor(Color.WHITE);
        }
        if (v.getId() == metrobus.getId())
        {
            MetrobusOrigen = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, estacionesMetrobus);
            estacion.setAdapter(MetrobusOrigen);
            estacion.setEnabled(true);
            MetrobusOrigenseleccionado = true;
            MetroOrigenseleccionado = false;
            CablebusOrigenseleccionado = false;
            TransportesElectricosOrigenseleccionado = false;
            metrobus.setBackgroundColor(colorgrisclaro);
            estacion.setText("");
            metro.setBackgroundColor(Color.WHITE);
            cablebus.setBackgroundColor(Color.WHITE);
            transporteselectricos.setBackgroundColor(Color.WHITE);
        }
        if (v.getId() == cablebus.getId())
        {
            CablebusOrigen = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, estacionesCablebus);
            estacion.setAdapter(CablebusOrigen);
            estacion.setEnabled(true);
            MetrobusOrigenseleccionado = false;
            MetroOrigenseleccionado = false;
            CablebusOrigenseleccionado = true;
            TransportesElectricosOrigenseleccionado = false;
            cablebus.setBackgroundColor(colorgrisclaro);
            estacion.setText("");
            metro.setBackgroundColor(Color.WHITE);
            metrobus.setBackgroundColor(Color.WHITE);
            transporteselectricos.setBackgroundColor(Color.WHITE);
        }
        if (v.getId() == transporteselectricos.getId()) {
            TEOrigen = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, estacionesTE);
            estacion.setAdapter(TEOrigen);
            estacion.setEnabled(true);
            MetrobusOrigenseleccionado = false;
            MetroOrigenseleccionado = false;
            CablebusOrigenseleccionado = false;
            TransportesElectricosOrigenseleccionado = true;
            Log.d("HOLA", "Se selecciono TE");
            transporteselectricos.setBackgroundColor(colorgrisclaro);
            estacion.setText("");
            metro.setBackgroundColor(Color.WHITE);
            metrobus.setBackgroundColor(Color.WHITE);
            cablebus.setBackgroundColor(Color.WHITE);
        }

        if (vehiculodescompuesto.getId() == v.getId())
        {
            if (!vdescompuesto)
            {
                vdescompuesto = true;
                vehiculodescompuesto.setBackgroundResource(R.drawable.rojoprendido);
                num1 = "1";

            }
            else
            {
                if (vdescompuesto)
                {
                    vdescompuesto = false;
                    vehiculodescompuesto.setBackgroundResource(R.drawable.rojoapagado);
                    num1 = "0";
                }
            }
        }
        if (accidente.getId() == v.getId())
        {
            if (!vaccidente)
            {
                vaccidente = true;
                accidente.setBackgroundResource(R.drawable.rojoprendido);
                num2 = "1";

            }
            else
            {
                if (vaccidente)
                {
                    vaccidente = false;
                    accidente.setBackgroundResource(R.drawable.rojoapagado);
                    num2 = "0";
                }
            }
        }
        if (aglomeracion.getId() == v.getId())
        {
            if (!vaglomeracion)
            {
                vaglomeracion = true;
                aglomeracion.setBackgroundResource(R.drawable.amarilloprendido);
                num3 = "1";

            }
            else
            {
                if (vaglomeracion)
                {
                    vaglomeracion = false;
                    aglomeracion.setBackgroundResource(R.drawable.amarilloapagado);
                    num3 = "0";
                }
            }
        }
        if (retraso.getId() == v.getId())
        {
            if (!vretraso)
            {
                vretraso = true;
                retraso.setBackgroundResource(R.drawable.verdeprendido);
                num4 = "1";

            }
            else
            {
                if (vretraso)
                {
                    vretraso = false;
                    retraso.setBackgroundResource(R.drawable.verdeapagado);
                    num4 = "0";
                }
            }
        }
        if (mandar.getId() == v.getId())
        {
            if (num1.equals("1")||num2.equals("1")||num3.equals("1")||num4.equals("1"))
            {
                Anuncio anuncio = new Anuncio();
                anuncio.setCausa(num1 + ":" + num2 + ":" + num3 + ":" + num4);
                // Obtener la hora actual del sistema
                LocalTime horaActual = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    horaActual = LocalTime.now();
                }
                // Obtener las preferencias compartidas
                SharedPreferences prefs = requireActivity().getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
                String udi = prefs.getString("UDI", null);



// Convertir LocalTime a una cadena con formato específico (HH:mm:ss.SSS)
                DateTimeFormatter formatter = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String horaActualStr = horaActual.format(formatter);
                    anuncio.setHoraEnvio(horaActualStr);
                }


                long ultimaEnviada = prefs.getLong("ultima_enviada", 0);
                long tiempoActual = System.currentTimeMillis();
                long tiempoTranscurrido = tiempoActual - ultimaEnviada;
                long tiempoMinimoEntreMensajes = 21*60*1000;
                anuncio.setEstacion(String.valueOf(numestacion));
                anuncio.setUidUsuario(udi);
                if (tiempoTranscurrido >= tiempoMinimoEntreMensajes) {
                HttpHandler httpHandler = new HttpHandler(requireContext());

                httpHandler.execute(anuncio);

                   dismiss();

                Estaticos estaticos = new Estaticos();
                    if (!estaticos.error)
                    {
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putLong("ultima_enviada", tiempoActual);
                        editor.apply();
                    }
              } else
                {

                    Toast.makeText(requireContext(), "Espera un poco antes de enviar otro mensaje", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(requireContext(), "Seleccione al menos una opción", Toast.LENGTH_SHORT).show();
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
    @Override
    public void onStart()
    {
        Usuario usuario = new Usuario();
        super.onStart();
        if(usuario.isTutorial())
        {
            primertutorial();

            BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
            if (dialog != null) {
                dialog.setCanceledOnTouchOutside(false);
                dialog.setCancelable(false);
                View bottomSheet = dialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
                if (bottomSheet != null) {
                    BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);
                    behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }
        }
    }
}
