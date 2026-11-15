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

    

}