/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import static resources.serializarArchivo.deserializar;

/**
 *
 * @author Jes
 */
public class RecibirObjeto implements serializarArchivo {

    Object objeto;
    int puerto;
    String host;

    public RecibirObjeto(Object objeto, int puerto, String host) {
        this.objeto = objeto;
        this.puerto = puerto;
        this.host = host;
        recibe(puerto);
    }

    public static Object recibe(int puerto) {
        DatagramSocket socket;
        DatagramPacket paquete = null;
        byte recibidos[] = new byte[10000000];

        try {
            socket = new DatagramSocket(puerto);

            paquete = new DatagramPacket(recibidos, recibidos.length);
            socket.receive(paquete);

            socket.close();

        } catch (SocketException ex) {
            System.out.println("Error al asignar el socket");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error en la recepciĂłn del paquete");
        }

        return deserializar(paquete.getData());
    }

}
