package com.example.mitransporteonline;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import com.google.gson.reflect.TypeToken;

public class ConsultarMensajesHandler extends AsyncTask<Void, Void, List<Mensaje>> {

    private final ConsultarMensajesCallback callback;


    public interface ConsultarMensajesCallback {
        void onMensajesObtenidos(List<Mensaje> mensajes);
    }

    public ConsultarMensajesHandler(ConsultarMensajesCallback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Mensaje> doInBackground(Void... voids) {
        try {
        
            URL url = new URL("http:");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

          
            connection.setRequestMethod("GET");

      
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
            
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
               
                List<Mensaje> mensajes = parsearRespuesta(reader);
                inputStream.close();
                return mensajes;
            } else {
        
                Log.e("HTTP", "Error al consultar mensajes: " + responseCode);
            }

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(List<Mensaje> mensajes) {
        super.onPostExecute(mensajes);

        if (callback != null) {
            callback.onMensajesObtenidos(mensajes);
        }
    }

   
    private List<Mensaje> parsearRespuesta(BufferedReader reader) {
        StringBuilder respuestaJson = new StringBuilder();
        String linea;

        try {
            while ((linea = reader.readLine()) != null) {
                respuestaJson.append(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Gson().fromJson(respuestaJson.toString(), new TypeToken<List<Mensaje>>() {}.getType());
    }
}
