package com.example.mitransporteonline;

import static android.provider.Settings.System.getString;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpHandler extends AsyncTask<Anuncio, Void, String> {

    private final Context context;

    public HttpHandler(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Anuncio... anuncios) {
        try {
            URL url = new URL("http:");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

           
            String anuncioJson = convertirAnuncioAJson(anuncios[0]);
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = anuncioJson.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
            
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    return response.toString();
                }
            } else {
             
                return "Error al agregar anuncio: " + responseCode;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        final String responseMessage;
        System.out.println( result);
        Estaticos estaticos = new Estaticos();
        if (result.startsWith("Error"))
        {
            responseMessage = "Error al agregar anuncio. Por favor, inténtelo de nuevo.";

            estaticos.error = true;
        } else
        {
            if (result.contains("Baneado"))
            {
                responseMessage = "Su cuenta fue baneada por uso indebido";
            }
            else
                {
                responseMessage = result;
                estaticos.error = true;
            }
        }
        if (result.equals("Baneado"))
        {// 
            SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.prefs_file), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();

            FirebaseAuth.getInstance().signOut();
            Intent mainIntent = new Intent(context, MainActivity.class);
            context.startActivity(mainIntent);

            if (context instanceof Activity)
            {
                ((Activity) context).finish();
            }

        }

        if (context != null && context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(context, responseMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

  
    private String convertirAnuncioAJson(Anuncio anuncio) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("estacion", anuncio.getEstacion());
        jsonObject.addProperty("causa", anuncio.getCausa());
        jsonObject.addProperty("horaEnvio", anuncio.getHoraEnvio());
        jsonObject.addProperty("uidUsuario", anuncio.getUidUsuario());
        return gson.toJson(jsonObject);
    }
}
