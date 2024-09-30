package mitransporte;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MITransporte extends JFrame {

    private Connection connection;

    public MITransporte() {
        // Configuración de la ventana
        setTitle("Aplicación de Transporte");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Conectar automáticamente al iniciar la aplicación
        conectarBaseDeDatos("jdbc:mysql://diacxa.sytes.net:3306:3306/Anuncios", "root", "1234");

        // Crear botones
        JButton altaButton = new JButton("Realizar Alta");
        JButton bajaButton = new JButton("Realizar Baja");
        JButton consultaButton = new JButton("Consultar Mensajes");

        // Configurar acciones de los botones
        altaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarAlta();
            }
        });

        bajaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMensajesParaBaja();
            }
        });

        consultaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarMensajes();
            }
        });

        // Configurar diseño de la interfaz
        setLayout(new FlowLayout());
        add(altaButton);
        add(bajaButton);
        add(consultaButton);

        // Hacer visible la ventana
        setVisible(true);
    }

    private void conectarBaseDeDatos(String jdbcUrl, String usuario, String contraseña) {
        try {
            connection = DriverManager.getConnection(jdbcUrl, usuario, contraseña);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException ex) {
            System.err.println("Error al conectar a la base de datos: " + ex.getMessage());
        }
    }

    private void realizarAlta() {
        if (connection != null) {
            try {
                // Solicitar información al usuario mediante cuadros de diálogo
                String titulo = JOptionPane.showInputDialog("Ingrese el titulo:");

                // Verificar si se ingresó la estación
                if (titulo != null && !titulo.isEmpty()) {
                    String causa = JOptionPane.showInputDialog("Ingrese la causa:");

                   // Obtener la hora actual del sistema
LocalTime horaActual = LocalTime.now();

// Convertir LocalTime a una cadena con formato específico (HH:mm:ss.SSS)
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
String horaActualStr = horaActual.format(formatter);

                   

                    // Ejemplo de realizar una alta (insertar un nuevo registro)
                    String sql = "INSERT INTO Anuncios (ESTACION, CAUSA, HORAPUBLICACION, UID) VALUES (?, ?, ?, 2)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        // Setear valores para la nueva fila
                        preparedStatement.setString(1, titulo);
                        preparedStatement.setString(2, causa);
                        preparedStatement.setString(3, horaActualStr);
                        // Ejecutar la inserción
                        preparedStatement.executeUpdate();
                        System.out.println("Alta realizada exitosamente");
                    }
                } else {
                    System.out.println("Alta cancelada. No se ingresó la estación.");
                }
            } catch (SQLException ex) {
                System.err.println("Error al realizar alta: " + ex.getMessage());
            }
        } else {
            System.err.println("No se pudo realizar el alta. Conexión no establecida.");
        }
    }

    private void mostrarMensajesParaBaja() {
        if (connection != null) {
            try {
                // Obtener mensajes desde la base de datos
                List<String> mensajes = obtenerMensajes();

                // Convertir la lista de mensajes a un arreglo para el cuadro de lista
                String[] opciones = mensajes.toArray(new String[0]);

                // Mostrar cuadro de lista para que el usuario elija un mensaje
                String seleccion = (String) JOptionPane.showInputDialog(
                        this,
                        "Seleccione un mensaje para eliminar:",
                        "Mensajes Disponibles",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opciones,
                        opciones[0]
                );

                // Verificar si el usuario seleccionó un mensaje
                if (seleccion != null) {
                    realizarBaja(seleccion);
                } else {
                    System.out.println("Operación de Baja cancelada. No se seleccionó ningún mensaje.");
                }
            } catch (SQLException ex) {
                System.err.println("Error al obtener mensajes: " + ex.getMessage());
            }
        } else {
            System.err.println("No se pudo realizar la operación de Baja. Conexión no establecida.");
        }
    }

    private void consultarMensajes() {
        if (connection != null) {
            try {
                // Obtener mensajes desde la base de datos
                List<String> mensajes = obtenerMensajes();

                // Mostrar mensajes en un cuadro de diálogo
                StringBuilder mensajeCompleto = new StringBuilder("Mensajes Disponibles:\n\n");
                for (String mensaje : mensajes) {
                    mensajeCompleto.append(mensaje).append("\n");
                }

                JOptionPane.showMessageDialog(this, mensajeCompleto.toString(), "Consulta de Mensajes", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                System.err.println("Error al obtener mensajes: " + ex.getMessage());
            }
        } else {
            System.err.println("No se pudo realizar la consulta. Conexión no establecida.");
        }
    }

    private List<String> obtenerMensajes() throws SQLException {
        List<String> mensajes = new ArrayList<>();

        // Ejemplo de consulta para obtener mensajes (puedes adaptarlo según tu esquema de base de datos)
        String sql = "SELECT IDMensaje, ESTACION, CAUSA, HORAPUBLICACION FROM Anuncios";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int idMensaje = resultSet.getInt("IDMensaje");
                String titulo = resultSet.getString("ESTACION");
                String causa = resultSet.getString("CAUSA");
                String horaPublicacion = resultSet.getString("HORAPUBLICACION");

                // Formatear la información del mensaje
                String mensaje = String.format("ID: %d, Estación: %s, Causa: %s, Hora: %s", idMensaje, titulo, causa, horaPublicacion);

                mensajes.add(mensaje);
            }
        }

        return mensajes;
    }

    private void realizarBaja(String mensajeSeleccionado) {
        if (connection != null) {
            try {
                // Obtener el ID del mensaje seleccionado
                int idMensaje = obtenerIdMensajeDesdeTexto(mensajeSeleccionado);

                // Confirmar la eliminación con el usuario
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar el mensaje seleccionado?");
                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Ejemplo de realizar una baja (eliminar un registro)
                    String sql = "DELETE FROM Anuncios WHERE IDMensaje = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        // Setear el valor para la condición de eliminación
                        preparedStatement.setInt(1, idMensaje);
                        // Ejecutar la eliminación
                        preparedStatement.executeUpdate();
                        System.out.println("Baja realizada exitosamente");
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error al realizar baja: " + ex.getMessage());
            }
        } else {
            System.err.println("No se pudo realizar la baja. Conexión no establecida.");
        }
    }

    private int obtenerIdMensajeDesdeTexto(String texto) {
        // Extraer el ID de la cadena (puedes adaptarlo según el formato)
        String[] partes = texto.split(",");
        String idString = partes[0].substring(4).trim(); // Saltar "ID: " y quitar espacios al inicio y al final
        return Integer.parseInt(idString);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MITransporte();
            }
        });
    }
}

