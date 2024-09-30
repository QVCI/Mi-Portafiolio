package com.example.mitransporteonline;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class Costos extends BottomSheetDialogFragment {

    private LinearLayout fondometro;
    private TextView costometro;
    private LinearLayout fondometrobus;
    private TextView costometrobus;
    private LinearLayout fondocablebus;
    private TextView costocablebus;

    private LinearLayout fondotransporteelectrico;
    private TextView costotransporteselectricos;
    private TextView total;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.activity_costos, container, false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        View decorView = requireActivity().getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);


        fondometro = requireView().findViewById(R.id.fondometro);
        costometro = requireView().findViewById(R.id.costometro);

        fondometrobus = requireView().findViewById(R.id.fondometrobus);
        costometrobus = requireView().findViewById(R.id.costometrobus);

        fondocablebus = requireView().findViewById(R.id.fondocablebus);
        costocablebus = requireView().findViewById(R.id.costocablebus);


        fondotransporteelectrico = requireView().findViewById(R.id.fondotransporteelectrico);
        costotransporteselectricos = requireView().findViewById(R.id.costotransporteselectricos);

        total = requireView().findViewById(R.id.total);

        List<Nodos> nodosUsados = new ArrayList<>();

        nodosUsados = Estaticos.nodosUsados;
        int cmetro = 0;
        int cmetrobus = 0;
        int ccablebus = 0;
        int ctrenligero = 0;
        int ctrolebus = 0;

        int tmetro = 0;
        int tmetrobus = 0;
        int tcablebus = 0;
        int ttrenligero = 0;
        int ttrolebus = 0;
        int transporteselectricos = 0;


        int costo = 0;

        boolean metro = false;

        boolean metrobusL1 = false;
        boolean metrobusL2 = false;
        boolean metrobusL3 = false;
        boolean metrobusL4 = false;
        boolean metrobusL5 = false;
        boolean metrobusL6 = false;
        boolean metrobusL7 = false;

        boolean cablebusL1 = false;
        boolean cablebusL2 = false;
        boolean trenligero = false;
        boolean trolebusL1 = false;
        boolean trolebusL2 = false;
        boolean trolebusL3 = false;
        boolean trolebusL4 = false;
        boolean trolebusL5 = false;
        boolean trolebusL6 = false;
        boolean trolebusL7 = false;
        boolean trolebusL8 = false;
        boolean trolebusL9 = false;
        boolean trolebusL10 = false;
        for (Nodos nodo : nodosUsados)
        {
            int nodo1 = nodo.nodo1;
            if (nodo1 >= EstacionesNodos.getIniciometro() && nodo1 <= EstacionesNodos.getFinmetro())
            {
                if(!metro)
                {
                    metro = true;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    costo += 5;
                    cmetro += 5;
                    tmetro += 1;
                }
            }
            if(nodo1 >= EstacionesNodos.getIniciometrobusl1() && nodo1 <= EstacionesNodos.getFinmetrobusl1())
            {
                if(!metrobusL1)
                {
                    metro = false;
                    metrobusL1 = true;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }
            }
            if (nodo1 >= EstacionesNodos.getIniciometrobusl2() && nodo1 <= EstacionesNodos.getFinmetrobusl2())
            {
                if(!metrobusL2)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = true;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }
            }
            if (nodo1 >= EstacionesNodos.getIniciometrobusl3() && nodo1 <= EstacionesNodos.getFinmetrobusl3())
            {
                if(!metrobusL3)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = true;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }
            }
            if (nodo1 >= EstacionesNodos.getIniciometrobusl4() && nodo1 <= EstacionesNodos.getFinmetrobusl4())
            {
                boolean aeropuerto = false;
                boolean cobroextra = false;
                if(!metrobusL4)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = true;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    tmetrobus += 1;
                    if ( nodo1 == EstacionesNodos.getAeropuertoinicio() || nodo1 == EstacionesNodos.getAeropuertofin())
                    {
                        aeropuerto = true;
                    }

                    else
                    {
                        costo += 6;
                        cmetrobus += 6;
                    }

                }
                if(metrobusL4)
                {
                    if (aeropuerto && !cobroextra)
                    {
                        cobroextra = true;
                        costo += 24;
                        cmetrobus += 24;
                    }
                }
            }
            if (nodo1 >= EstacionesNodos.getIniciometrobusl5() && nodo1 <= EstacionesNodos.getFinmetrobusl5())
            {
                if(!metrobusL5)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = true;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }
            }
            if (nodo1 >= EstacionesNodos.getIniciometrobusl6() && nodo1 <= EstacionesNodos.getFinmetrobusl6())
            {
                if(!metrobusL6)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = true;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }
            }
            if (nodo1 >= EstacionesNodos.getIniciometrobusl7() && nodo1 <= EstacionesNodos.getFinmetrobusl7())
            {
                if(!metrobusL7)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = true;
                    cablebusL1 = false;
                    cablebusL2 = false;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    costo += 6;
                    cmetrobus += 6;
                    tmetrobus += 1;
                }
            }
            if (nodo1 >= EstacionesNodos.getIniciocablebusl1() && nodo1 <= EstacionesNodos.getFincablebusl1())
            {
                if(!cablebusL1)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = true;
                    cablebusL2 = false;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    costo += 7;
                    ccablebus +=7;
                    tcablebus +=1;
                }
            }
            if (nodo1 >= EstacionesNodos.getIniciocablebusl2() && nodo1 <= EstacionesNodos.getFincablebusl2())
            {
                if(!cablebusL2)
                {
                    metro = false;
                    metrobusL1 = false;
                    metrobusL2 = false;
                    metrobusL3 = false;
                    metrobusL4 = false;
                    metrobusL5 = false;
                    metrobusL6 = false;
                    metrobusL7 = false;
                    cablebusL1 = false;
                    cablebusL2 = true;
                    trenligero = false;
                    trolebusL1 = false;
                    trolebusL2 = false;
                    trolebusL3 = false;
                    trolebusL4 = false;
                    trolebusL5 = false;
                    trolebusL6 = false;
                    trolebusL7 = false;
                    trolebusL8 = false;
                    trolebusL9 = false;
                    trolebusL10 = false;
                    costo += 7;
                    ccablebus +=7;
                    tcablebus +=1;
                }
            }
            else
            {
                if (nodo1 >= EstacionesNodos.getIniciotrenligero() && nodo1 <= EstacionesNodos.getFintrenligero())
                {
                    if(!trenligero)
                    {
                        metro = false;
                        metrobusL1 = false;
                        metrobusL2 = false;
                        metrobusL3 = false;
                        metrobusL4 = false;
                        metrobusL5 = false;
                        metrobusL6 = false;
                        metrobusL7 = false;
                        cablebusL1 = false;
                        cablebusL2 = false;
                        trenligero = true;
                        trolebusL1 = false;
                        trolebusL2 = false;
                        trolebusL3 = false;
                        trolebusL4 = false;
                        trolebusL5 = false;
                        trolebusL6 = false;
                        trolebusL7 = false;
                        trolebusL8 = false;
                        trolebusL9 = false;
                        trolebusL10 = false;

                        costo += 3;
                        ctrenligero +=3;
                        ttrenligero += 1;
                    }
                }
                else
                {
                    if (nodo1 >= EstacionesNodos.getIniciotrolebusl1() && nodo1 <= EstacionesNodos.getFintrolebusl1())
                    {
                        if(!trolebusL1)
                        {
                            metro = false;
                            metrobusL1 = false;
                            metrobusL2 = false;
                            metrobusL3 = false;
                            metrobusL4 = false;
                            metrobusL5 = false;
                            metrobusL6 = false;
                            metrobusL7 = false;
                            cablebusL1 = false;
                            cablebusL2 = false;
                            trenligero = false;
                            trolebusL1 = true;
                            trolebusL2 = false;
                            trolebusL3 = false;
                            trolebusL4 = false;
                            trolebusL5 = false;
                            trolebusL6 = false;
                            trolebusL7 = false;
                            trolebusL8 = false;
                            trolebusL9 = false;
                            trolebusL10 = false;

                            costo += 7;
                            ctrolebus +=7;
                            ttrolebus += 1;
                        }
                    }
                    else
                    {
                        if (nodo1 >= EstacionesNodos.getIniciotrolebusl2() && nodo1 <= EstacionesNodos.getFintrolebusl2())
                        {
                            if(!trolebusL2)
                            {
                                metro = false;
                                metrobusL1 = false;
                                metrobusL2 = false;
                                metrobusL3 = false;
                                metrobusL4 = false;
                                metrobusL5 = false;
                                metrobusL6 = false;
                                metrobusL7 = false;
                                cablebusL1 = false;
                                cablebusL2 = false;
                                trenligero = false;
                                trolebusL1 = false;
                                trolebusL2 = true;
                                trolebusL3 = false;
                                trolebusL4 = false;
                                trolebusL5 = false;
                                trolebusL6 = false;
                                trolebusL7 = false;
                                trolebusL8 = false;
                                trolebusL9 = false;
                                trolebusL10 = false;

                                costo += 7;
                                ctrolebus +=7;
                                ttrolebus += 1;
                            }
                        }
                        else
                        {
                            if (nodo1 >= EstacionesNodos.getIniciotrolebusl3() && nodo1 <= EstacionesNodos.getFintrolebusl3())
                            {
                                if(!trolebusL3)
                                {
                                    metro = false;
                                    metrobusL1 = false;
                                    metrobusL2 = false;
                                    metrobusL3 = false;
                                    metrobusL4 = false;
                                    metrobusL5 = false;
                                    metrobusL6 = false;
                                    metrobusL7 = false;
                                    cablebusL1 = false;
                                    cablebusL2 = false;
                                    trenligero = false;
                                    trolebusL1 = false;
                                    trolebusL2 = false;
                                    trolebusL3 = true;
                                    trolebusL4 = false;
                                    trolebusL5 = false;
                                    trolebusL6 = false;
                                    trolebusL7 = false;
                                    trolebusL8 = false;
                                    trolebusL9 = false;
                                    trolebusL10 = false;

                                    costo += 7;
                                    ctrolebus +=7;
                                    ttrolebus += 1;
                                }
                            }
                            else
                            {
                                if (nodo1 >= EstacionesNodos.getIniciotrolebusl4() && nodo1 <= EstacionesNodos.getFintrolebusl4())
                                {
                                    if(!trolebusL4)
                                    {
                                        metro = false;
                                        metrobusL1 = false;
                                        metrobusL2 = false;
                                        metrobusL3 = false;
                                        metrobusL4 = false;
                                        metrobusL5 = false;
                                        metrobusL6 = false;
                                        metrobusL7 = false;
                                        cablebusL1 = false;
                                        cablebusL2 = false;
                                        trenligero = false;
                                        trolebusL1 = false;
                                        trolebusL2 = false;
                                        trolebusL3 = false;
                                        trolebusL4 = true;
                                        trolebusL5 = false;
                                        trolebusL6 = false;
                                        trolebusL7 = false;
                                        trolebusL8 = false;
                                        trolebusL9 = false;
                                        trolebusL10 = false;

                                        costo += 7;
                                        ctrolebus +=7;
                                        ttrolebus += 1;
                                    }
                                }
                                else
                                {
                                    if (nodo1 >= EstacionesNodos.getIniciotrolebusl5() && nodo1 <= EstacionesNodos.getFintrolebusl5())
                                    {
                                        if(!trolebusL5)
                                        {
                                            metro = false;
                                            metrobusL1 = false;
                                            metrobusL2 = false;
                                            metrobusL3 = false;
                                            metrobusL4 = false;
                                            metrobusL5 = false;
                                            metrobusL6 = false;
                                            metrobusL7 = false;
                                            cablebusL1 = false;
                                            cablebusL2 = false;
                                            trenligero = false;
                                            trolebusL1 = false;
                                            trolebusL2 = false;
                                            trolebusL3 = false;
                                            trolebusL4 = false;
                                            trolebusL5 = true;
                                            trolebusL6 = false;
                                            trolebusL7 = false;
                                            trolebusL8 = false;
                                            trolebusL9 = false;
                                            trolebusL10 = false;

                                            costo += 7;
                                            ctrolebus +=7;
                                            ttrolebus += 1;
                                        }
                                    }
                                    else
                                    {
                                        if (nodo1 >= EstacionesNodos.getIniciotrolebusl6() && nodo1 <= EstacionesNodos.getFintrolebusl6())
                                        {
                                            if(!trolebusL6)
                                            {
                                                metro = false;
                                                metrobusL1 = false;
                                                metrobusL2 = false;
                                                metrobusL3 = false;
                                                metrobusL4 = false;
                                                metrobusL5 = false;
                                                metrobusL6 = false;
                                                metrobusL7 = false;
                                                cablebusL1 = false;
                                                cablebusL2 = false;
                                                trenligero = false;
                                                trolebusL1 = false;
                                                trolebusL2 = false;
                                                trolebusL3 = false;
                                                trolebusL4 = false;
                                                trolebusL5 = false;
                                                trolebusL6 = true;
                                                trolebusL7 = false;
                                                trolebusL8 = false;
                                                trolebusL9 = false;
                                                trolebusL10 = false;

                                                costo += 7;
                                                ctrolebus +=7;
                                                ttrolebus += 1;
                                            }
                                        }
                                        else
                                        {
                                            if (nodo1 >= EstacionesNodos.getIniciotrolebusl7() && nodo1 <= EstacionesNodos.getFintrolebusl7())
                                            {
                                                if(!trolebusL7)
                                                {
                                                    metro = false;
                                                    metrobusL1 = false;
                                                    metrobusL2 = false;
                                                    metrobusL3 = false;
                                                    metrobusL4 = false;
                                                    metrobusL5 = false;
                                                    metrobusL6 = false;
                                                    metrobusL7 = false;
                                                    cablebusL1 = false;
                                                    cablebusL2 = false;
                                                    trenligero = false;
                                                    trolebusL1 = false;
                                                    trolebusL2 = false;
                                                    trolebusL3 = false;
                                                    trolebusL4 = false;
                                                    trolebusL5 = false;
                                                    trolebusL6 = false;
                                                    trolebusL7 = true;
                                                    trolebusL8 = false;
                                                    trolebusL9 = false;
                                                    trolebusL10 = false;

                                                    costo += 7;
                                                    ctrolebus +=7;
                                                    ttrolebus += 1;
                                                }
                                            }
                                            else
                                            {
                                                if (nodo1 >= EstacionesNodos.getIniciotrolebusl8() && nodo1 <= EstacionesNodos.getFintrolebusl8())
                                                {
                                                    if(!trolebusL8)
                                                    {
                                                        metro = false;
                                                        metrobusL1 = false;
                                                        metrobusL2 = false;
                                                        metrobusL3 = false;
                                                        metrobusL4 = false;
                                                        metrobusL5 = false;
                                                        metrobusL6 = false;
                                                        metrobusL7 = false;
                                                        cablebusL1 = false;
                                                        cablebusL2 = false;
                                                        trenligero = false;
                                                        trolebusL1 = false;
                                                        trolebusL2 = false;
                                                        trolebusL3 = false;
                                                        trolebusL4 = false;
                                                        trolebusL5 = false;
                                                        trolebusL6 = false;
                                                        trolebusL7 = false;
                                                        trolebusL8 = true;
                                                        trolebusL9 = false;
                                                        trolebusL10 = false;

                                                        costo += 7;
                                                        ctrolebus +=7;
                                                        ttrolebus += 1;
                                                    }
                                                }
                                                else
                                                {
                                                    if (nodo1 >= EstacionesNodos.getIniciotrolebusl9() && nodo1 <= EstacionesNodos.getFintrolebus9())
                                                    {
                                                        if(!trolebusL9)
                                                        {
                                                            metro = false;
                                                            metrobusL1 = false;
                                                            metrobusL2 = false;
                                                            metrobusL3 = false;
                                                            metrobusL4 = false;
                                                            metrobusL5 = false;
                                                            metrobusL6 = false;
                                                            metrobusL7 = false;
                                                            cablebusL1 = false;
                                                            cablebusL2 = false;
                                                            trenligero = false;
                                                            trolebusL1 = false;
                                                            trolebusL2 = false;
                                                            trolebusL3 = false;
                                                            trolebusL4 = false;
                                                            trolebusL5 = false;
                                                            trolebusL6 = false;
                                                            trolebusL7 = false;
                                                            trolebusL8 = false;
                                                            trolebusL9 = true;
                                                            trolebusL10 = false;

                                                            costo += 7;
                                                            ctrolebus +=7;
                                                            ttrolebus += 1;
                                                        }
                                                    }
                                                    else
                                                    {
                                                        if (nodo1 >= EstacionesNodos.getIniciotrolebusl10() && nodo1 <= EstacionesNodos.getFintrolebusl10())
                                                        {
                                                            if(!trolebusL10)
                                                            {
                                                                metro = false;
                                                                metrobusL1 = false;
                                                                metrobusL2 = false;
                                                                metrobusL3 = false;
                                                                metrobusL4 = false;
                                                                metrobusL5 = false;
                                                                metrobusL6 = false;
                                                                metrobusL7 = false;
                                                                cablebusL1 = false;
                                                                cablebusL2 = false;
                                                                trenligero = false;
                                                                trolebusL1 = false;
                                                                trolebusL2 = false;
                                                                trolebusL3 = false;
                                                                trolebusL4 = false;
                                                                trolebusL5 = false;
                                                                trolebusL6 = false;
                                                                trolebusL7 = false;
                                                                trolebusL8 = false;
                                                                trolebusL9 = false;
                                                                trolebusL10 = true;

                                                                costo += 7;
                                                                ctrolebus +=7;
                                                                ttrolebus += 1;
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
        }
        if (tmetro != 0)
        {
            fondometro.setBackgroundResource(R.drawable.sipasa);
            costometro.setText("$" + cmetro );
        }
        if (tmetrobus != 0)
        {
             fondometrobus.setBackgroundResource(R.drawable.sipasa);
             costometrobus.setText("$" + cmetrobus );
        }

        if (tcablebus != 0)
        {
            fondocablebus.setBackgroundResource(R.drawable.sipasa);
            costocablebus.setText("$" + ccablebus );
        }
        total.setText("$" + costo );
        if (ttrenligero != 0 || ttrolebus != 0)
        {

            transporteselectricos = ctrenligero + ctrolebus;
            fondotransporteelectrico.setBackgroundResource(R.drawable.sipasa);
            costotransporteselectricos.setText("$" + transporteselectricos );
        }

    }


}
