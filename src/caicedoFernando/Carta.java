package src.caicedoFernando;

public class Carta {
    private final String valor;
    private final char palo;
    private boolean esVisible;

    public Carta(String valor, char palo, boolean esVisible) {
        this.valor = valor;
        this.palo = palo;
        this.esVisible = esVisible;
    }

    public String mostrar() {
        return esVisible ? "[" + valor + " " + palo + "]" : "[? ?]";
    }

    public void voltear() {
        esVisible = !esVisible;
    }

    public boolean estaVisible() {
        return esVisible;
    }

    public char obtenerPalo() {
        return palo;
    }

    public String obtenerValor() {
        return valor;
    }
}
