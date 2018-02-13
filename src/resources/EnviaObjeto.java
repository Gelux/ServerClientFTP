/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import static resources.serializarArchivo.serializar;

/**
 *
 * @author Jes
 */
public class EnviaObjeto implements serializarArchivo{
    Object objeto;
    String hostdestino;
    int puerto;
    
    public EnviaObjeto(Object objeto, String hostdestino, int puerto){
        this.objeto = objeto;
        this.hostdestino = hostdestino;
        this.puerto = puerto;
        Envia(objeto, hostdestino, puerto);
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
}
