package com.marsrovers;

/**
 * Controla los limites del espacio sobre el cual se desplazaran los Rovers
 *
 * @see IPlataforma
 * @author Jorge
 */
public class Plataforma implements IPlataforma {

    private static final int LONGITUD_COORDENADAS_PLATAFORMA = 2;
    private static final String ESPACIO = " ";

    private int width;
    private int height;

    /**
     * Establece la coordenada superior derecha de la plataforma validando si
     * esta ha sido especificada de manera correcta
     *
     * @param coordenadaPlataforma Coordenada indicando el maximo valor en x,y
     * que tendra la plataforma
     * @throws IllegalArgumentException Si la coordenada no ha sido especificada
     * o es incorrecta
     */
    public Plataforma(String coordenadaPlataforma) {
        setCoordenada(coordenadaPlataforma);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    /**
     * Establece la coordenada de la esquina superior derecha de la plataforma
     *
     * @param coordenadaPlataforma Maximo valor en x,y de la Plataforma
     * @throws IllegalArgumentException Si la coordenada no ha sido especificada
     * o es incorrecta
     */
    private void setCoordenada(String coordenadaPlataforma) {

        if (coordenadaPlataforma == null || coordenadaPlataforma.isEmpty()) {
            throw new IllegalArgumentException("La coordenada de la Plataforma no ha sido especificada.");
        }

        String[] coordenada = coordenadaPlataforma.split(ESPACIO);
        if (coordenada.length > LONGITUD_COORDENADAS_PLATAFORMA) {
            throw new IllegalArgumentException("Coordenada no valida.");
        }

        this.width = Integer.parseInt(coordenada[0]) + 1;
        this.height = Integer.parseInt(coordenada[1]) + 1;
    }

    @Override
    public String toString() {
        return String.format("%d %d", this.width, this.height);
    }

}
