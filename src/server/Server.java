/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Jes
 */
public class Server implements Runnable {
    JFileChooser jfc = new JFileChooser();
    EstructuraArchivosIO estructuraArchivos;
    String rutaInicial;
    
    int puertoEscucha;
    int puertoEnvio;
    String host;
    
    Server(String host, int puertoEscucha, int puertoEnvio){
        initializeChooser();
        this.host = host;
        this.puertoEscucha = puertoEscucha;
        this.puertoEnvio = puertoEnvio;
    }
    
    @Override
    public void run(){
        while(true){
            
        }
    }
    
    private void initializeChooser() {
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setCurrentDirectory(new File("c:\\"));
        jfc.setDialogTitle("Selecciona el directorio que vas a compartir");

        if ((jfc.showDialog(jfc,"Seleccionar") == JFileChooser.CANCEL_OPTION)) {
            JOptionPane.showMessageDialog(null, "El servidor de archivos no ha arrancado");
        } else {
            File archivo = jfc.getSelectedFile();
            rutaInicial = archivo.getAbsolutePath();
            estructuraArchivos = new EstructuraArchivosIO(rutaInicial);
        }
    }

    public static void Envia(Object objeto, String hostdestino, int puerto) {
        try {
            DatagramSocket socket;
            DatagramPacket paquete;
            byte bytesEnviar[];

            bytesEnviar = serializar(objeto);
            socket = new DatagramSocket();
            paquete = new DatagramPacket(bytesEnviar, bytesEnviar.length, InetAddress.getByName(hostdestino), puerto);
            socket.send(paquete);
            socket.close();

        } catch (SocketException ex) {
            System.out.println("Error al asignar el socket");
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            System.out.println("Error al crear el paquete");
        } catch (IOException ex) {
            System.out.println("Error en el envĂ­o del paquete");
        }

    }

    static byte[] serializar(Object objeto) {      
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (
                ObjectOutputStream os = new ObjectOutputStream(bytes)) {
            os.writeObject(objeto);
        } catch (IOException ex) {
            System.out.println("Error al crear el array de bytes");
        }
        return bytes.toByteArray();
    }

}
