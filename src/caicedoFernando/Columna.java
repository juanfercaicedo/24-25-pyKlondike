package src.caicedoFernando;

public class Columna {
    private final Carta[] cartasEnColumna;
    private int cantidadEnColumna;

    public Columna(int capacidadMaxima) {
        cartasEnColumna = new Carta[capacidadMaxima];
        cantidadEnColumna = 0;
    }

    public void agregarCarta(Carta carta) {
        if (cantidadEnColumna < cartasEnColumna.length) {
            cartasEnColumna[cantidadEnColumna] = carta;
            cantidadEnColumna = cantidadEnColumna + 1;
        }
    }

    public Carta sacarCarta() {
        Carta carta = null;
        if (cantidadEnColumna > 0) {
            cantidadEnColumna = cantidadEnColumna - 1;
            carta = cartasEnColumna[cantidadEnColumna];
            cartasEnColumna[cantidadEnColumna] = null;
        }
        return carta;
    }

    public void voltearUltimaCarta() {
        if (cantidadEnColumna > 0) {
            cartasEnColumna[cantidadEnColumna - 1].voltear();
        }
    }

    public void mostrarColumna(int numero) {
        System.out.print("Columna [" + numero + "]: ");
        int indice = 0;
        while (indice < cantidadEnColumna) {
            System.out.print(cartasEnColumna[indice].mostrar());
            indice = indice + 1;
        }
        System.out.println();
    }

    public Carta obtenerUltimaCarta() {
        return cantidadEnColumna > 0 ? cartasEnColumna[cantidadEnColumna - 1] : null;
    }

    public boolean estaVacia() {
        return cantidadEnColumna == 0;
    }

    public int obtenerCantidad() {
        return cantidadEnColumna;
    }

    public Carta obtenerCartaEn(int indice) {
        return (indice >= 0 && indice < cantidadEnColumna) ? cartasEnColumna[indice] : null;
    }

}
