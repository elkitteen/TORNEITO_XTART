import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("Hola mundo");

        Personaje sungjinwoo = new Personaje("Sung Jinwoo", 100, 15, 30);

        Personaje ayanokoji = new Personaje("Ayanokoji", 100, 25, 30);

        simularCombate(sungjinwoo, ayanokoji);

    }

    public static boolean simularCombate(Personaje jugador, Personaje enemigo) {

        Scanner scanner = new Scanner(System.in);
        boolean combateActivo = true;

        while (jugador.getVida() > 0 && enemigo.getVida() > 0 && combateActivo) {
            boolean turnoJugador = true;

            System.out.println("[" + jugador.getNombre() + "]" + "---------------- [VIDA: " + jugador.getVida() + "]");
            System.out.println("[" + enemigo.getNombre() + "]" + "---------------- [VIDA: " + enemigo.getVida() + "]");

            System.out.println("");

            System.out.println("ATACAR 1) \n" + "REVISAR INVENTARIO 2) \n" + "ABANDONAR 3)");

            String decision = scanner.nextLine();
            switch (decision) {

                // ATACAR
                case "1":

                    jugador.atacar(enemigo);

                    if (enemigo.getVida() <= 0) {

                        combateActivo = false;
                        break;

                    }

                    enemigo.atacar(jugador);

                    if (jugador.getVida() <= 0) {

                        combateActivo = false;
                        break;

                    }

                    break;

                // REVISAR INVENTARIO
                case "2":
                    jugador.registrarInventario();
                    break;

                // ABANDONAR
                case "3":
                    combateActivo = false;
                    break;

                default:
                    System.out.println("Opción Inválida");
                    break;

            }

        }

        // RESUMEN DEL COMBATE

        // SI HAZ SIDO DERROTADO
        if (jugador.getVida() <= 0) {

            System.out.println("HAS SIDO DERROTADO.");
            System.out.println("HAS PERDIDO TODOS TUS PUNTOS.");
            System.out.println("VOLVIENDO AL MENÚ PRINCIPAL...");
            jugador.setNumPuntos(0);
            

            return false;

        }

        // SI GANAS EL COMBATE
        else if (enemigo.getVida() <= 0) {
            System.out.println("HAS GANADO EL COMBATE.");
            System.out.println("HAS GANADO 100 PUNTOS.");
            System.out.println("SIGUES AVANZANDO...");
            jugador.setNumPuntos(jugador.getNumPuntos() + 100);
            

            return true;

        }

        // SI HAZ ABANDONADO MALVADO MALO
        else {

            System.out.println("HAS ABANDONADO, QUE MANCO.");
            System.out.println("HAS ACUMULADO: " + jugador.getNumPuntos() + " PUNTOS.");
            System.out.println("VOLVIENDO AL MENÚ PRINCIPAL...");
            
            return false;
        }

    }


}