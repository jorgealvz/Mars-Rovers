package com.marsrovers;

/**
 * Establece el comportamiento para posicionarse dentro de la Plataforma
 * @see IPlataforma
 * @author Jorge
 */
public interface IRover {

    /**
     * Retorna la coordenada en x del Rover 
     * @return Coordenada en x del Rover
     */
    public int getX();

    /**
     * Establece la coordenada en x del Rover
     * @param x Coordenada en x
     */
    public void setX(int x);

    /**
     * Retorna la coordenada en y del Rover
     * @return Coordenada en y del Rover
     */
    public int getY();

    /**
     * Establece la coordenada en y del Rover
     * @param y Coordenada en y
     */
    public void setY(int y);

    /**
     * Retorna la direccion del Rover relativa a un punto cardinal
     * @return Direccion del Rover
     * @see Rover.DIRECCIONES
     */
    public String getDireccion();

    /**
     * Establece la direccion del Rover relativa a un punto cardinal
     * @param direccion Direccion del Rover
     * @see Rover.DIRECCIONES
     */
    public void setDireccion(String direccion);
}
