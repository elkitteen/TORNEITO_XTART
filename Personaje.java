import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Personaje {

    private String nombre;
    private int vida;
    private String[] inventario;
    private int danioMin;
    private int danioMax;
    private double numPuntos;

    public Personaje(String nombre, int vida, int danioMin, int danioMax) {
        this.nombre = nombre;
        this.vida = vida;
        this.danioMin = danioMin;
        this.danioMax = danioMax;
        this.inventario = new String[3];
        this.inventario[0] = "Pocion de curacion";
        this.inventario[1] = "Pocion de curacion";
        this.inventario[2] = "Pocion de curacion";

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public String[] getInventario() {
        return inventario;
    }

    public void setInventario(String[] inventario) {
        this.inventario = inventario;
    }

    public int getDanioMin() {
        return danioMin;
    }

    public void setDanioMin(int danioMin) {
        this.danioMin = danioMin;
    }

    public int getDanioMax() {
        return danioMax;
    }

    public void setDanioMax(int danioMax) {
        this.danioMax = danioMax;
    }

    public double getNumPuntos() {
        return numPuntos;
    }

    public void setNumPuntos(double numPuntos) {
        this.numPuntos = numPuntos;
    }

    public void atacar(Personaje objetivo) {

        Random random = new Random();

        int danio = random.nextInt((danioMax - danioMin) + 1) + danioMin;
        System.out.println(this.nombre + " ataca y produce " + danio + " de daño ");

        objetivo.recibirDanio(danio);
    }

    public void recibirDanio(int danio) {

        this.vida = vida - danio;

        if (this.vida < 0) {
            this.vida = 0;
        }

        System.out.println(this.nombre + " recibe " + danio + " de daño \n");

    }

    public void registrarInventario() {

        Scanner scanner = new Scanner(System.in);

        boolean pocionUsada = false;

        System.out.println(Arrays.toString(inventario));

        // BUCLE PARA RECORRER INVENTARIO
        for (int i = 0; i < inventario.length; i++) {

            // SI ENCUENTRA POCION DE CURACION SE CURA +30
            if (inventario[i] != null && inventario[i].equals("Pocion de curacion")) {

                System.out.println("Quieres usar una pocion de curacion? Y/N");
                String usarPocion = scanner.nextLine();

                if (usarPocion.equalsIgnoreCase("y")) {

                    inventario[i] = null;

                    this.vida = vida + 30;

                    // SI AL CURAR ES MAYOR A 100. SE ESTABLECE EN 100
                    if (this.vida > 100) {

                        this.vida = 100;

                    }

                    System.out.println(this.nombre + " se ha curado 30 de vida");
                    pocionUsada = true;
                    break;

                }

                else {
                    System.out.println("Haz decidido no usar la pocion");
                    System.out.println("");
                    pocionUsada = true;
                    break;
                }
            }
        }

        // VERIFICACION SI NO HAY POCIONES
        if (!pocionUsada) {
            System.out.println("NO TIENES POCIONES POBRE DE MIERDA");
        }

    }


}