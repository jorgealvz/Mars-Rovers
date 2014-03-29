package com.marsrovers;

/**
 * Establece los limites sobre los cuales se desplazaran los Rovers
 * @author Jorge
 */
public interface IPlataforma {

    /**
     * Retorna el valor maximo en x de la plataforma
     * @return Valor maximo en x
     */
    public int getWidth();

    /**
     * Retorna el valor maximo en y de la plataforma
     * @return Valor maximo en y
     */
    public int getHeight();
}
