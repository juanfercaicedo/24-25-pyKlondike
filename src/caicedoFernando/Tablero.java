package src.caicedoFernando;

import java.util.Random;

public class Tablero {
    private final PilaCartas baraja;
    private PilaCartas descarte;
    private final PilaCartas[] palos;
    private final Columna[] columnas;
    private final Reglas reglas;

    private static final char[] SIMBOLOS_PALOS = { '♠', '♥', '♦', '♣' };
    private static final String[] VALORES_CARTAS = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

    final int CARTAS_TOTALES = 52;
    final int CARTAS_MAXIMAS_POR_PILA = 13;
    final int CARTAS_MAXIMAS_POR_COLUMNA = 20;

    public Tablero() {

        baraja = new PilaCartas(CARTAS_TOTALES);
        descarte = new PilaCartas(CARTAS_TOTALES);
        palos = new PilaCartas[4];
        columnas = new Columna[7];
        reglas = new Reglas();

        for (int i = 0; i < 4; i++) {
            palos[i] = new PilaCartas(CARTAS_MAXIMAS_POR_PILA);
        }

        for (int i = 0; i < 7; i++) {
            columnas[i] = new Columna(CARTAS_MAXIMAS_POR_COLUMNA);
        }

        llenarBarajaConCartasMezcladas();
        repartirCartasAColumnas();
    }

    private void llenarBarajaConCartasMezcladas() {
        Random generador = new Random();
        Carta[] todasLasCartas = new Carta[52];
        int totalCartas = 0;

        for (int paloIndex = 0; paloIndex < 4; paloIndex++) {
            for (int valorIndex = 0; valorIndex < CARTAS_MAXIMAS_POR_PILA; valorIndex++) {
                todasLasCartas[totalCartas] = new Carta(VALORES_CARTAS[valorIndex], SIMBOLOS_PALOS[paloIndex], false);
                totalCartas++;
            }
        }

        while (totalCartas > 0) {
            int indiceAleatorio = generador.nextInt(totalCartas);
            Carta cartaSeleccionada = todasLasCartas[indiceAleatorio];
            todasLasCartas[indiceAleatorio] = todasLasCartas[totalCartas - 1];
            totalCartas--;
            baraja.agregarCarta(cartaSeleccionada);
        }
    }

    private void repartirCartasAColumnas() {
        for (int columnaIndex = 0; columnaIndex < 7; columnaIndex++) {
            for (int cartaIndex = 0; cartaIndex <= columnaIndex; cartaIndex++) {
                Carta carta = baraja.sacarCarta();
                if (cartaIndex == columnaIndex)
                    carta.voltear();
                columnas[columnaIndex].agregarCarta(carta);
            }
        }
    }

    public void mostrarEstado() {
        System.out.print("BARAJA: ");
        System.out.println(baraja.estaVacia() ? "" : "[? ?]");

        System.out.print("Descarte: ");
        for (int i = 0; i < descarte.obtenerCantidad(); i++) {
            System.out.print(descarte.obtenerCartaEn(i).mostrar());
        }
        System.out.println();

        for (int i = 0; i < 4; i++) {
            System.out.print((i + 1) + "º Palo: ");
            int cartasEnPalo = palos[i].obtenerCantidad();
            if (cartasEnPalo == 0)
                System.out.println("No hay cartas en el palo");
            else
                System.out.println(palos[i].obtenerCartaEn(cartasEnPalo - 1).mostrar());
        }

        for (int i = 0; i < 7; i++) {
            columnas[i].mostrarColumna(i + 1);
        }
    }

    public void moverCartaDeBarajaADescarte() {
        Carta carta = baraja.sacarCarta();
        if (carta != null) {
            carta.voltear();
            descarte.agregarCarta(carta);
        }
    }

    public void voltearCartasDeDescarteABaraja() {
        int cantidad = descarte.obtenerCantidad();
        for (int i = cantidad - 1; i >= 0; i--) {
            Carta carta = descarte.obtenerCartaEn(i);
            if (carta != null)
                carta.voltear();
            baraja.agregarCarta(carta);
        }
        descarte = new PilaCartas(CARTAS_TOTALES);
    }

    public boolean moverCartaDeDescarteAPalo() {
        if (descarte.estaVacia())
            return false;

        Carta carta = descarte.obtenerCartaEn(descarte.obtenerCantidad() - 1);
        for (int i = 0; i < 4; i++) {
            if (reglas.puedeMoverAPalo(carta, palos[i])) {
                palos[i].agregarCarta(descarte.sacarCarta());
                return true;
            }
        }
        return false;
    }

    public boolean moverCartaEntreColumnas(int columnaOrigen, int columnaDestino) {
        if (columnaOrigen < 0 || columnaOrigen >= 7 || columnaDestino < 0 || columnaDestino >= 7)
            return false;

        Carta cartaOrigen = columnas[columnaOrigen].obtenerCartaEn(columnas[columnaOrigen].obtenerCantidad() - 1);
        if (cartaOrigen == null)
            return false;

        Carta cartaDestino = columnas[columnaDestino].obtenerCartaEn(columnas[columnaDestino].obtenerCantidad() - 1);
        if (cartaDestino != null && !reglas.puedeMoverDeColumnaAColumna(cartaOrigen, cartaDestino))
            return false;

        columnas[columnaDestino].agregarCarta(columnas[columnaOrigen].sacarCarta());
        return true;
    }
}
