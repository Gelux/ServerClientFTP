/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import resources.EstructuraArchivosIO;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import resources.EnviaObjeto;
import resources.RecibirObjeto;
import resources.serializarArchivo;

/**
 *
 * @author Jes
 */
public class Server implements Runnable, serializarArchivo {
    JFileChooser jfc;
    EstructuraArchivosIO estructuraArchivos;
    String rutaInicial;
    EnviaObjeto enviar;
    RecibirObjeto recibir;
    
    int puertoEscucha;
    int puertoEnvio;
    String host;
    
    Server(String host, int puertoEscucha, int puertoEnvio){
        initializeChooser();
        this.host = host;
        this.puertoEscucha = puertoEscucha;
        this.puertoEnvio = puertoEnvio;
        Thread hiloServidor = new Thread(this);
    }
    
    @Override
    public void run(){
        while(true){
            
        }
    }
    
    private void initializeChooser() {
        jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setCurrentDirectory(new File("c:\\"));
        jfc.setDialogTitle("Selecciona el directorio que vas a compartir");

        if ((jfc.showDialog(jfc,"Seleccionar") == JFileChooser.CANCEL_OPTION)) {
            JOptionPane.showMessageDialog(null, "El servidor de archivos no ha arrancado");
        } else {
            File archivo = jfc.getSelectedFile();
            rutaInicial = archivo.getAbsolutePath();
            estructuraArchivos = new EstructuraArchivosIO(rutaInicial);
            EnviaObjeto enviaAux = new EnviaObjeto(estructuraArchivos, host, puertoEnvio);
        }
    }
    
    public static void main(String[] args) {
        new Server("localhost",  12345, 12345);
    }

}
