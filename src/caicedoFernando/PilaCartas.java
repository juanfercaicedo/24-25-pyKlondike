package src.caicedoFernando;

public class PilaCartas {
    private final Carta[] pilaDeCartas;
    private int cantidadCartas;

    public PilaCartas(int capacidadMaxima) {
        pilaDeCartas = new Carta[capacidadMaxima];
        cantidadCartas = 0;
    }

    public void agregarCarta(Carta carta) {
        if (cantidadCartas < pilaDeCartas.length) {
            pilaDeCartas[cantidadCartas] = carta;
            cantidadCartas = cantidadCartas + 1;
        }
    }

    public Carta sacarCarta() {
        Carta carta = null;
        if (cantidadCartas > 0) {
            cantidadCartas = cantidadCartas - 1;
            carta = pilaDeCartas[cantidadCartas];
            pilaDeCartas[cantidadCartas] = null;
        }
        return carta;
    }

    public boolean estaVacia() {
        return cantidadCartas == 0;
    }

    public int obtenerCantidad() {
        return cantidadCartas;
    }

    public Carta obtenerCartaEn(int indice) {
        return (indice >= 0 && indice < cantidadCartas) ? pilaDeCartas[indice] : null;
    }
}
