/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Jes
 */
public interface serializarArchivo  {
    
    
    public static byte[] serializar(Object objeto) {      
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        try (
                ObjectOutputStream os = new ObjectOutputStream(bytes)) {
            os.writeObject(objeto);
        } catch (IOException ex) {
            System.out.println("Error al crear el array de bytes");
        }
        return bytes.toByteArray();
    }
    
    public static Object deserializar(byte[] bytesRecibidos) {
        Object objeto = null;
        ByteArrayInputStream bytesDelPaquete = new ByteArrayInputStream(bytesRecibidos); 

    
            try (ObjectInputStream is = new ObjectInputStream(bytesDelPaquete)) {
                objeto = is.readObject();
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error al convertir el objeto");
        } catch (IOException ex) {
            System.out.println("Error al extraer datos del paquete");
        }
            return objeto;
    }
}
