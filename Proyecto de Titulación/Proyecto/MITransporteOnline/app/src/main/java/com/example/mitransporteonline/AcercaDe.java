package com.example.mitransporteonline;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AcercaDe extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ImageButton config;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_acerca_de);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        config = findViewById(R.id.config);
        config.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcercaDe.this, configuracion.class);
                startActivity(intent);
                finish();
            }
        });
        if (savedInstanceState == null) {
            principaltutorial videoFragment = new principaltutorial();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.vistadefragmentos, videoFragment);
            transaction.commit();
        }






        NavigationView navigationView = findViewById(R.id.navigation_view);

      
        drawerLayout = findViewById(R.id.main);

        ImageView menuImageView = findViewById(R.id.menu);



        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            MenuItem menuItem = navigationView.getMenu().getItem(i);

       
            View actionView = menuItem.getActionView();

            if (actionView == null) {
               
                actionView = LayoutInflater.from(this).inflate(R.layout.infladoacercade, null);
                menuItem.setActionView(actionView);
            }

            ImageView logoImageView = actionView.findViewById(R.id.logo);
            TextView textoTextView = actionView.findViewById(R.id.texto);

            if (menuItem.getItemId() == R.id.menu_item1)
            {
                logoImageView.setImageResource(R.drawable.calculemos);
                textoTextView.setText("Cálculo de Rutas");
            } else
                if (menuItem.getItemId() == R.id.menu_item2)
                {
                logoImageView.setImageResource(R.drawable.reportes);
                textoTextView.setText("Avisos");
                } else


                        if (menuItem.getItemId() == R.id.menu_item4)
                        {
                            logoImageView.setImageResource(R.drawable.guardadorelleno);
                            textoTextView.setText("Rutas Guardadas");
                    } 
                        else
                        {
                            if (menuItem.hasSubMenu())
                            {
                                SubMenu subMenu = menuItem.getSubMenu();
                                for (int j = 0; j < subMenu.size(); j++)
                                {
                                    MenuItem subMenuItem = subMenu.getItem(j);

                                    // Obtener la vista personalizada
                                    View actionView2 = subMenuItem.getActionView();
                                    if (actionView2 == null) {
                                        // Inflar la vista personalizada si no existe
                                        actionView2 = LayoutInflater.from(this).inflate(R.layout.infladoacercade, null);
                                        subMenuItem.setActionView(actionView2);
                                    }
                                    ImageView logoImageView2 = actionView2.findViewById(R.id.logo);
                                    TextView textoTextView2 = actionView2.findViewById(R.id.texto);


                                        if (subMenuItem.getItemId() == R.id.escribircodigo) {
                                            logoImageView2.setImageResource(R.drawable.agregarpersona);
                                            textoTextView2.setText("Escribir Código");
                                        } else {
                                            if (subMenuItem.getItemId() == R.id.empezaracompartir) {
                                                logoImageView2.setImageResource(R.drawable.rastreador);
                                                textoTextView2.setText("Compartir Ubicación");
                                            } else {
                                                if (subMenuItem.getItemId() == R.id.generarcodigo) {
                                                    logoImageView2.setImageResource(R.drawable.agregarpersona);
                                                    textoTextView2.setText("Generar Codigo");
                                                }
                                            }
                                        }

                                }
                            }
                        }

            
        }
        menuImageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (!drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.openDrawer(navigationView);
                } else {
                    drawerLayout.closeDrawer(navigationView);
                }
            }
        });

        // Configurar el clic fuera del cajón de navegación para cerrarlo
        drawerLayout.setDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset == 0.0) {
                   
                    drawerLayout.setBackgroundResource(0);
                } else {
               
                    drawerLayout.setBackgroundResource(R.color.transparent_black); 
                }
            }
        });

      


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                System.out.println();

                if (id == R.id.menu_item1) {
                    replaceFragment(new tutorialcalculoderutas());
                    drawerLayout.closeDrawer(navigationView);
                    return true;
                }

                if (id == R.id.menu_item2) {
                    replaceFragment(new tutorialavisos());
                    drawerLayout.closeDrawer(navigationView);
                    return true;
                }
                if (id == R.id.generarcodigo) {
                    replaceFragment(new tutorialgenerarcodigo());
                    drawerLayout.closeDrawer(navigationView);
                    return true;
                }
                if (id == R.id.escribircodigo) {
                    replaceFragment(new tutorialescribircodigo());
                    drawerLayout.closeDrawer(navigationView);
                    return true;
                }
                if (id == R.id.empezaracompartir) {
                    replaceFragment(new tutorialempezaracompartir());
                    drawerLayout.closeDrawer(navigationView);
                    return true;
                }

                //RTP
                if (id == R.id.menu_item4) {
                    replaceFragment(new tutorialrutasguardadas());
                    drawerLayout.closeDrawer(navigationView);
                    return true;
                }


                System.out.println("OPC" + item.getItemId());
               
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        return true;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.vistadefragmentos, fragment);
        fragmentTransaction.addToBackStack(null); 
        fragmentTransaction.commit();
    }
}
