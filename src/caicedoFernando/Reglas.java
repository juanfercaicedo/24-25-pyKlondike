package src.caicedoFernando;

public class Reglas {

    public boolean puedeMoverAPalo(Carta carta, PilaCartas pilaPalo) {
        if (carta == null)
            return false;
        int cantidadCartasEnPalo = pilaPalo.obtenerCantidad();
        Carta cartaEnTope = null;
        if (cantidadCartasEnPalo > 0)
            cartaEnTope = pilaPalo.obtenerCartaEn(cantidadCartasEnPalo - 1);

        if (cartaEnTope == null)
            return carta.obtenerValor().equals("A");

        return carta.obtenerPalo() == cartaEnTope.obtenerPalo() &&
                obtenerValorNumerico(carta.obtenerValor()) == obtenerValorNumerico(cartaEnTope.obtenerValor()) + 1;
    }

    public boolean puedeMoverDeColumnaAColumna(Carta cartaOrigen, Carta cartaDestino) {
        if (cartaDestino == null)
            return true;

        if (cartaOrigen.obtenerPalo() == cartaDestino.obtenerPalo())
            return false;

        return obtenerValorNumerico(cartaOrigen.obtenerValor()) == obtenerValorNumerico(cartaDestino.obtenerValor())
                - 1;
    }

    private int obtenerValorNumerico(String valor) {
        if (valor.equals("A"))
            return 1;
        if (valor.equals("J"))
            return 11;
        if (valor.equals("Q"))
            return 12;
        if (valor.equals("K"))
            return 13;

        int numero = 0;
        for (int i = 0; i < valor.length(); i++) {
            numero = numero * 10 + (valor.charAt(i) - '0');
        }
        return numero;
    }
}
