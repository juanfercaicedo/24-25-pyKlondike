package src.caicedoFernando;

import java.util.Scanner;

public class Solitario {
    private final Tablero tablero;

    public Solitario() {
        tablero = new Tablero();
    }

    public void iniciarJuego() {
        Scanner entrada = new Scanner(System.in);
        int opcionElegida = 0;

        while (opcionElegida != 9) {
            mostrarOpcionesAlUsuario();
            tablero.mostrarEstado();
            System.out.print("Elige una opción [1-9]: ");
            opcionElegida = entrada.nextInt();

            if (opcionElegida == 1) {
                tablero.moverCartaDeBarajaADescarte();
            }
            if (opcionElegida == 8) {
                tablero.voltearCartasDeDescarteABaraja();
            }
        }

        entrada.close();
    }

    private void mostrarOpcionesAlUsuario() {
        System.out.println("OPCIONES>");
        System.out.println("  1. Mover de Baraja a Descarte");
        System.out.println("  2. Mover de Descarte a Palo");
        System.out.println("  3. Mover de Descarte a Columna");
        System.out.println("  4. Mover de Palo a Columna");
        System.out.println("  5. Mover de Columna a Palo");
        System.out.println("  6. Mover de Columna a Columna");
        System.out.println("  7. Voltear carta de Columna");
        System.out.println("  8. Voltear Descarte en Baraja");
        System.out.println("  9. Salir");
    }
}
