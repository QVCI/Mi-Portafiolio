package com.example.mitransporteonline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;



public class ConexionInternet {


    public static boolean hayConexionInternet(Context contexto) {
        ConnectivityManager connectivityManager = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
   
            NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiInfo != null && wifiInfo.isConnected()) {
                return true; // Hay conexión Wi-Fi
            }

            NetworkInfo datosMovilesInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            return datosMovilesInfo != null && datosMovilesInfo.isConnected(); // Hay conexión de datos móviles
        }

        return false; // No hay conexión disponible
    }


}
