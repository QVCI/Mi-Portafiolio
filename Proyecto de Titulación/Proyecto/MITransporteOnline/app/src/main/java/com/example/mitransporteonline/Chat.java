package com.example.mitransporteonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import java.util.List;

public class Chat extends AppCompatActivity implements View.OnClickListener, ConsultarMensajesHandler.ConsultarMensajesCallback
{
    private DrawerLayout drawerLayout;
    private ImageButton atras;

    private AdManager adManager;

    private LinearLayout chat;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final long TIEMPO_CONSULTA = 2 * 60 * 1000;
    private TextView publicar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        Usuario usuario = new Usuario();
        if (!usuario.isEspremium())
        {
            adManager = AdManager.getInstance();
            adManager.MostrarAnuncio(this);
        }


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        SharedPreferences datosguardados = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE);
        Log.d("Firebase", String.valueOf(usuario.isEspremium()));
        Log.d("Firebase", datosguardados.getString("FECHA", "01/01/2021"));
        FirebaseApp.initializeApp(this);

        atras = findViewById(R.id.atras);

        atras.setOnClickListener(this);

        publicar = findViewById(R.id.publicar);
        publicar.setOnClickListener(this);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
   
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_dark),
                getResources().getColor(android.R.color.holo_green_dark),
                getResources().getColor(android.R.color.holo_orange_dark),
                getResources().getColor(android.R.color.holo_red_dark)
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               
                new ConsultarMensajesHandler(Chat.this).execute();
            }
        });



        new ConsultarMensajesHandler(Chat.this).execute();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
        
                new ConsultarMensajesHandler(Chat.this).execute();

                
                handler.postDelayed(this, TIEMPO_CONSULTA);
            }
        }, TIEMPO_CONSULTA);



        NavigationView navigationView = findViewById(R.id.navigation_view);

      
        drawerLayout = findViewById(R.id.drawer_layout);

        ImageView menuImageView = findViewById(R.id.menu);
        menuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.openDrawer(navigationView);
                } else {
                    drawerLayout.closeDrawer(navigationView);
                }
            }
        });

   
        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset == 0.0) {
                   
                    drawerLayout.setBackgroundResource(0); // Puedes establecer el fondo que desees aquí
                } else {
                 
                    drawerLayout.setBackgroundResource(R.color.transparent_black); // Puedes establecer el fondo que desees aquí
                }
            }
        });

      
        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            MenuItem menuItem = navigationView.getMenu().getItem(i);

            View actionView = menuItem.getActionView();

            if (actionView == null) {
               
                actionView = LayoutInflater.from(this).inflate(R.layout.menu_item, null);
                menuItem.setActionView(actionView);
            }

           
            ImageView logoImageView = actionView.findViewById(R.id.logo);
            TextView textoTextView = actionView.findViewById(R.id.texto);

            if (menuItem.getItemId() == R.id.menu_item1) {
                logoImageView.setImageResource(R.drawable.metrologos);
                textoTextView.setText("Metro");
            } else if (menuItem.getItemId() == R.id.menu_item2) {
                logoImageView.setImageResource(R.drawable.metrobuslogos);
                textoTextView.setText("Metrobús");
            } else if (menuItem.getItemId() == R.id.menu_item3) {
                logoImageView.setImageResource(R.drawable.cablebuslogos);
                textoTextView.setText("Cablebús");
            } else if (menuItem.getItemId() == R.id.menu_item4) {
                logoImageView.setImageResource(R.drawable.rtplogos);
                textoTextView.setText("RTP");
            } else if (menuItem.getItemId() == R.id.menu_item5) {
                logoImageView.setImageResource(R.drawable.transporteslogos);
                textoTextView.setText("Transportes Eléctricos");
            } else
            {
                if (menuItem.getItemId() == R.id.menu_item6)
                {
                    logoImageView.setImageResource(R.drawable.mapita);
                    textoTextView.setText("Líneas");
                }
            }

          
        }

       
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                //METRO
                if (id == R.id.menu_item1)
                {
                    Intent MapaMetro = new Intent(Chat.this, com.example.mitransporteonline.MapaMetro.class);
                    startActivity(MapaMetro);
                    
                    return true;
                }
                //METROBUS
                if (id == R.id.menu_item2)
                {
                    Intent MapaMB = new Intent(Chat.this, com.example.mitransporteonline.MapaMetrobus.class);
                    startActivity(MapaMB);
                    
                    return true;
                }
                //CABLEBUS
                if (id == R.id.menu_item3)
                {
                    Intent MapaCablebus = new Intent(Chat.this, com.example.mitransporteonline.MapaCablebus.class);
                    startActivity(MapaCablebus);
                    
                    return true;
                }
                //RTP
                if (id == R.id.menu_item4)
                {
                    Intent MapaRTP = new Intent(Chat.this, com.example.mitransporteonline.MapaRTP.class);
                    startActivity(MapaRTP);
                    
                    return true;
                }
                //TRANSPORTES ELECTRICOS
                if (id == R.id.menu_item5)
                {
                    Intent MapaTE = new Intent(Chat.this, com.example.mitransporteonline.MapaTE.class);
                    startActivity(MapaTE);
                    
                    return true;
                }
                if (id == R.id.menu_item6) {
                    Intent MapaTE = new Intent(Chat.this, com.example.mitransporteonline.SistemasOnline.class);
                    startActivity(MapaTE);
                    finish();
                    return true;
                }

                System.out.println("OPC" + item.getItemId());
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == publicar.getId())
        {
            Estacion Estacion = new Estacion();
            Estacion.show(getSupportFragmentManager(), Estacion.getTag());
        }
        if (v.getId() == atras.getId())
        {
            Intent Principal = new Intent(this, com.example.mitransporteonline.Principal.class);
            startActivity(Principal);

            finish();
            
        }


    }

    @Override
    public void onMensajesObtenidos(List<Mensaje> mensajes)
    {
        LinearLayout layoutAvisos = findViewById(R.id.layoutavisos);
        layoutAvisos.removeAllViews();
        swipeRefreshLayout.setRefreshing(false);
        if (mensajes != null)
        {
            for (Mensaje mensaje : mensajes)
            {

                procesarMensaje(mensaje);

            
                if (mensaje.getUid() == 2)
                {

                    View avisoview = LayoutInflater.from(this).inflate(R.layout.avisos, null);
                    TextView estacion = avisoview.findViewById(R.id.estacion);
                    estacion.setText(mensaje.getEstacion());
                    TextView causa = avisoview.findViewById(R.id.causa);
                    causa.setText(mensaje.getCausa());
                    TextView horas = avisoview.findViewById(R.id.horas);
                    String[] parteshoras = mensaje.getHoraPublicacion().split(":");
                    if (parteshoras.length >= 2)
                    {
                        String horasYMinutos = parteshoras[0] + ":" + parteshoras[1];
                        horas.setText(horasYMinutos + " HRS");
                    }
                    layoutAvisos.addView(avisoview);
                }
                else
                {
                   
                    if (mensaje.getUid() == 1)
                    {
                        boolean causa1 = false;
                        boolean causa2 = false;
                        boolean causa3 = false;
                        boolean causa4 = false;
                        View reporteview = LayoutInflater.from(this).inflate(R.layout.reportes, null);
                        ImageView imagen = reporteview.findViewById(R.id.estacionimagen);
                        int numnodo = Integer.parseInt(mensaje.getEstacion());
                        if (numnodo >= EstacionesNodos.getIniciometro() && numnodo <= EstacionesNodos.getFinmetrobusl7()) 
                        {
                            
                           
                            
                            
                            String nombreImagen = "a_" + numnodo;
                            int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                            imagen.setImageResource(idImagen);


                        }
                        else
                        {
                            if (numnodo >= EstacionesNodos.getIniciotrenligero() && numnodo <= EstacionesNodos.getFintrenligero())
                            {
                                String nombreImagen = "a_" + numnodo;
                                int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                 imagen.setImageResource(idImagen);

                            }
                            else
                            {
                                if (numnodo >= EstacionesNodos.getIniciocablebusl1() && numnodo <= EstacionesNodos.getFincablebusl2()) {
                                    String nombreImagen = "a_" + numnodo;
                                    Log.d("Debug", " Esta en cablebus");
                                    int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                     imagen.setImageResource(idImagen);
                                } else {
                                    if (numnodo >= EstacionesNodos.getIniciotrolebusl2() && numnodo <= EstacionesNodos.getFintrolebusl2()) {
                                        String nombreImagen = "a_l2";
                                        int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                         imagen.setImageResource(idImagen);
                                    } else {
                                        if (numnodo >= EstacionesNodos.getIniciotrolebusl3() && numnodo <= EstacionesNodos.getFintrolebusl3()) {
                                            String nombreImagen = "a_l3";
                                            int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                             imagen.setImageResource(idImagen);
                                        } else {
                                            if (numnodo >= EstacionesNodos.getIniciotrolebusl4() && numnodo <= EstacionesNodos.getFintrolebusl4()) {
                                                String nombreImagen = "a_l4";
                                                int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                                 imagen.setImageResource(idImagen);
                                            } else {
                                                if (numnodo >= EstacionesNodos.getIniciotrolebusl5() && numnodo <= EstacionesNodos.getFintrolebusl5()) {
                                                    String nombreImagen = "a_l5";
                                                    int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                                     imagen.setImageResource(idImagen);
                                                } else {
                                                    if (numnodo >= EstacionesNodos.getIniciotrolebusl6() && numnodo <= EstacionesNodos.getFintrolebusl6()) {
                                                        String nombreImagen = "a_l6";
                                                        int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                                         imagen.setImageResource(idImagen);
                                                    } else {
                                                        if (numnodo >= EstacionesNodos.getIniciotrolebusl7() && numnodo <= EstacionesNodos.getFintrolebusl7()) {
                                                            String nombreImagen = "a_l7";
                                                            int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                                             imagen.setImageResource(idImagen);
                                                        } else {
                                                            if (numnodo >= EstacionesNodos.getIniciotrolebusl8() && numnodo <= EstacionesNodos.getFintrolebusl8()) {
                                                                String nombreImagen = "a_l8";
                                                                int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                                                 imagen.setImageResource(idImagen);
                                                            } else {
                                                                if (numnodo >= EstacionesNodos.getIniciotrolebusl9() && numnodo <= EstacionesNodos.getFintrolebus9()) {
                                                                    String nombreImagen = "a_l9";
                                                                    int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                                                     imagen.setImageResource(idImagen);
                                                                } else {
                                                                    if (numnodo >= EstacionesNodos.getIniciotrolebusl10() && numnodo <= EstacionesNodos.getFintrolebusl10()) {
                                                                        String nombreImagen = "a_l10";
                                                                        int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                                                         imagen.setImageResource(idImagen);
                                                                    }
                                                                    else
                                                                    {
                                                                        if (numnodo >= EstacionesNodos.getIniciotrolebusl1() && numnodo <= EstacionesNodos.getFintrolebusl1()) {
                                                                            String nombreImagen = "a_l1";
                                                                            int idImagen = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());
                                                                             imagen.setImageResource(idImagen);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                            }

                        }
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                       
                       
                        TextView estacion = reporteview.findViewById(R.id.estacion);
                        estacion.setText(EstacionesNodos.getEstaciones().get(Integer.parseInt(mensaje.getEstacion()) - 1 ));
                        String[] partescausas = mensaje.getCausa().split(":");
                        String numero1 = "";
                        String numero2 = "";
                        String numero3 = "";
                        String numero4 = "";


                        if (partescausas.length >= 4)
                        {
                            numero1 = partescausas[0];
                            numero2 = partescausas[1];
                            numero3 = partescausas[2];
                            numero4 = partescausas[3];
                        }
                        if (numero1.equals("1"))
                        {
                            causa1 = true;
                        }
                        if (numero2.equals("1"))
                        {
                            causa2 = true;
                        }
                        if (numero3.equals("1"))
                        {
                            causa3 = true;
                        }
                        if (numero4.equals("1"))
                        {
                            causa4 = true;
                        }
                        TextView vehiculodescompuesto = reporteview.findViewById(R.id.vehiculodescompuesto);
                        TextView accidente = reporteview.findViewById(R.id.accidente);
                        TextView aglomeracion = reporteview.findViewById(R.id.aglomeracion);
                        TextView retraso = reporteview.findViewById(R.id.retraso);
                        if (causa1)
                        {
                            vehiculodescompuesto.setBackgroundResource(R.drawable.rojoprendido);
                        }
                        if (causa2)
                        {
                            accidente.setBackgroundResource(R.drawable.rojoprendido);
                        }
                        if (causa3)
                        {
                            aglomeracion.setBackgroundResource(R.drawable.amarilloprendido);
                        }
                        if (causa4)
                        {
                            retraso.setBackgroundResource(R.drawable.verdeprendido);
                        }
                        TextView hora = reporteview.findViewById(R.id.hora);
                        String[] parteshoras = mensaje.getHoraPublicacion().split(":");
                        if (parteshoras.length >= 2)
                        {
                            String horasYMinutos = parteshoras[0] + ":" + parteshoras[1];
                            hora.setText(horasYMinutos + " HRS");
                        }
                        layoutAvisos.addView(reporteview);

                    }

                }
            }
        }
    }
    @Override
    protected void onDestroy() {
   
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
    private void procesarMensaje(Mensaje mensaje) {

        String id = String.valueOf(mensaje.getId());
        String estacion = mensaje.getEstacion();
        String causa = mensaje.getCausa();
        String horaPublicacion = mensaje.getHoraPublicacion();
        String uid = String.valueOf(mensaje.getUid());

        String mensajeCompleto = "ID: " + id + "\nEstación: " + estacion + "\nCausa: " + causa +
                "\nHora de publicación: " + horaPublicacion + "\nUID: " + uid;

    }
    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item)
                {
                    if (item.getItemId() == R.id.menuprincipal)
                    {
                        Intent Principal = new Intent(Chat.this, com.example.mitransporteonline.Principal.class);
                        startActivity(Principal);
                        
                    }
                    if (item.getItemId() == R.id.calcular)
                    {
                        Intent Mapa = new Intent(Chat.this, Crearruta.class);
                        startActivity(Mapa);
                        
                    }
                    if (item.getItemId() == R.id.anuncios)
                    {
                        Toast.makeText(Chat.this, "Ya estás en anuncios", Toast.LENGTH_SHORT).show();
                    }
                    return false;


                }
            };

}