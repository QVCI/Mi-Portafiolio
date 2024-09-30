/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package scliente;

import java.io.*;
import java.net.*;
import java.nio.Buffer;
import java.util.prefs.Preferences;
import java.util.Scanner;

/**
 *
 * @author Alumno
 */
public class SCliente {
    private static final String SERVER_IP = "192.168.111.777";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            System.out.println("Conectado al servidor de MI Transporte ");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            Thread inputThread = new Thread(() -> {
                while (true) {
                    String message = scanner.nextLine();
                    writer.println(message);
                    writer.flush();
                }
            });
            inputThread.start();

            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Usuario MI Transporte: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
