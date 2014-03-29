package com.marsrovers;

/**
 * Controla el comportamiento del Rover
 *
 * @see IRover
 * @author Jorge
 */
public class Rover implements IRover {

    private static final int LONGITUD_POSICION_ROVER = 3;
    private static final String ESPACIO = " ";

    private int x;
    private int y;
    private String direccion;
    private final IPlataforma plataforma;

    /**
     * Posiciona al Rover en la Plataforma. La posicion del Rover viene dada por
     * una coordenada x,y que indica su posicion dentro de la Plataforma y una
     * letra que indica su direccion relativa a los puntos cardinales
     *
     * @param plataforma Espacio sobre el cual se desplazara el Rover
     * @param posicionRover Posicion inicial del Rover
     * @see IPlataforma
     * @see Direcciones
     * @throws IllegalArgumentException Si la posicion del Rover no ha sido
     * especificada o no es valida
     */
    public Rover(IPlataforma plataforma, String posicionRover) {
        this.plataforma = plataforma;

        // ## Verificamos si la posicion del Rover ha sido introducida de manera correcta
        setPosicion(posicionRover);

        // ## Validamos si el rover se encuentra dentro de los limites de la Plataforma
        validarCoordenada(this.x, this.y);

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String getDireccion() {
        return direccion;
    }

    @Override
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Establece la posicion y direccion inicial del Rover
     *
     * @param posicionRover Posicion inicial del Rover
     * @throws IllegalArgumentException Si la posicion del Rover no ha sido
     * especificada o no es valida
     */
    private void setPosicion(String posicionRover) {

        if (posicionRover == null || posicionRover.isEmpty()) {
            throw new IllegalArgumentException("La posicion del Rover no ha sido especificada.");
        }

        String[] posicion = posicionRover.split(ESPACIO);
        if (posicion.length > LONGITUD_POSICION_ROVER) {
            throw new IllegalArgumentException("Posicion no valida");
        }

        this.x = Integer.parseInt(posicion[0]);
        this.y = Integer.parseInt(posicion[1]);
        this.direccion = posicion[2];
    }

    /**
     * Ejecuta los comandos de movimiento o direccion del Rover. Comandos
     * aceptados por el Rover <br><b>M</b> que le indica que avance una posicion
     * sobre su misma direccion, <br><b>L</b> que le indica que gire a la
     * izquierda sobre su posicion <br><b>R</b>
     * que le indica que gire a la derecha sobre su posicion
     *
     * @param comandosRover Cadena con los comandos a ejecutar por el Rover
     * @throws IllegalArgumentException Si el comando especificado es incorrecto
     * @throws IndexOutOfBoundsException Si el Rover al ejecutar un comando de
     * movimiento queda fuera de los limites de la plataforma
     * @see Comandos
     */
    public void ejecutarComandos(String comandosRover) {

        char[] comandos = comandosRover.toUpperCase().toCharArray();

        for (char comando : comandos) {

            if (String.valueOf(comando).equals(Comandos.AVANZAR)) {
                avanzar();
            } else {
                girar(String.valueOf(comando));
            }

        }
    }

    /**
     * Mueve al Rover una posicion sobre su misma direccion.
     *
     * @throws IndexOutOfBoundsException Si el Rover al ejecutar un comando de
     * movimiento queda fuera de los limites de la plataforma
     */
    public void avanzar() {

        int coordenadaX = this.x;
        int coordenadaY = this.y;

        if (this.direccion.equals(Direcciones.NORTE)) {
            coordenadaY = coordenadaY + 1;
        } else if (this.direccion.equals(Direcciones.ESTE)) {
            coordenadaX = coordenadaX + 1;
        } else if (this.direccion.equals(Direcciones.SUR)) {
            coordenadaY = coordenadaY - 1;
        } else if (this.direccion.equals(Direcciones.OESTE)) {
            coordenadaX = coordenadaX - 1;
        }

        if (validarCoordenada(coordenadaX, coordenadaY)) {
            this.x = coordenadaX;
            this.y = coordenadaY;
        }
    }

    /**
     * Gira el Rover hacia la izquierda o a la derecha sobre su posicion
     *
     * @param comandoDireccion L o R indicando la direccion sobre la cual girara
     * el Rover
     */
    public void girar(String comandoDireccion) {

        if (comandoDireccion.equals(Comandos.GIRAR_IZQUIERDA)) {
            girarIzquierda();
        } else if (comandoDireccion.equals(Comandos.GIRAR_DERECHA)) {
            girarDerecha();
        } else {
            throw new IllegalArgumentException("Comando no valido");
        }

    }

    /**
     * Valida si la coordenada del Rover esta dentro de los limites de la
     * Plataforma
     *
     * @param x Coordenada en x
     * @param y Coordenada en y
     * @return True si la coordenada se encuentra dentro de los limites de la
     * Plataforma o False si no
     * @see IPlataforma
     */
    private boolean validarCoordenada(int x, int y) {

        if ((x >= 0 && x < plataforma.getWidth()) && (y >= 0 && y < plataforma.getHeight())) {
            return true;
        } else {
            throw new IndexOutOfBoundsException(String.format("El Rover se encuentra fuera de los limites "
                    + "de la Plataforma. Posicion Rover: %s , Limite Plataforma: %s", toString(), plataforma.toString()));
        }
    }

    /**
     * Gira al rover hacia la izquierda en base a su direccion actual
     */
    private void girarIzquierda() {

        if (this.direccion.equals(Direcciones.NORTE)) {
            this.direccion = Direcciones.OESTE;
        } else if (this.direccion.equals(Direcciones.OESTE)) {
            this.direccion = Direcciones.SUR;
        } else if (this.direccion.equals(Direcciones.SUR)) {
            this.direccion = Direcciones.ESTE;
        } else if (this.direccion.equals(Direcciones.ESTE)) {
            this.direccion = Direcciones.NORTE;
        }

    }

    /**
     * Gira al Rover hacia la derecha en base a su direccion actual
     */
    private void girarDerecha() {

        if (this.direccion.equals(Direcciones.NORTE)) {
            this.direccion = Direcciones.ESTE;
        } else if (this.direccion.equals(Direcciones.ESTE)) {
            this.direccion = Direcciones.SUR;
        } else if (this.direccion.equals(Direcciones.SUR)) {
            this.direccion = Direcciones.OESTE;
        } else if (this.direccion.equals(Direcciones.OESTE)) {
            this.direccion = Direcciones.NORTE;
        }

    }

    @Override
    public String toString() {
        return String.format("%d %d %s", this.x, this.y, this.direccion);
    }

    /**
     * Comandos que el Rover puede ejecutar
     */
    public static class Comandos {

        public static final String AVANZAR = "M";
        public static final String GIRAR_IZQUIERDA = "L";
        public static final String GIRAR_DERECHA = "R";

    }

    /**
     * Direcciones relativas a los puntos cardinales que puede tomar el Rover
     */
    public static class Direcciones {

        public static final String NORTE = "N";
        public static final String ESTE = "E";
        public static final String SUR = "S";
        public static final String OESTE = "W";

    }
}
