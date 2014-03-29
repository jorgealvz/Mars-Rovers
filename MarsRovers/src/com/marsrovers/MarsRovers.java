package com.marsrovers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class MarsRovers {

    public MarsRovers() {
    }

    public Plataforma crearPlataforma(String coordenada) {

        return new Plataforma(coordenada);
    }

    public Rover crearRover(Plataforma plataforma, String rover) {

        return new Rover(plataforma, rover);
    }

    public List<String> getContenidoArchivo(File file) {

        List<String> contenido = new ArrayList<String>();

        BufferedReader br = null;
        try {
            String linea = null;

            br = new BufferedReader(new FileReader(file));

            while ((linea = br.readLine()) != null) {
                contenido.add(linea);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MarsRovers.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MarsRovers.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(MarsRovers.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return contenido;
    }

    /**
     * @param args Ruta del archivo de comandos
     */
    public static void main(String[] args) {

        try {
            String rutaArchivo = args[0];

            MarsRovers marsRovers = new MarsRovers();

            List<String> lines = marsRovers.getContenidoArchivo(new File(rutaArchivo));
            if (lines.size() < 3 || (lines.size() % 2) == 0) {
                throw new IllegalArgumentException("El archivo de comandos no tiene el formato correcto");
            }

            // ## La primera linea contiene las coordenadas de la plataforma, instanciamos la plataforma
            String coordenadasPlataforma = lines.get(0).trim();
            Plataforma plataforma = marsRovers.crearPlataforma(coordenadasPlataforma);

            for (int i = 1; i < lines.size(); i = i + 2) {
                String line = lines.get(i).trim();

                // ## La linea contiene la posicion incial de un Rover, instanciamos el Rover
                Rover rover = marsRovers.crearRover(plataforma, line);

                line = lines.get(i + 1);
                // ## La linea contiene los comandos a ejecutar por el Rover
                rover.ejecutarComandos(line);

                System.out.println(rover.toString());
            }

        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MarsRovers.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
