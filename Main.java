public class Main {
    
    public static void main(String[] args) {
        
        System.out.println("Hola mundo");

        Personaje sungjinwoo = new Personaje("Sung Jinwoo", 100, 15, 30);

        Personaje ayanokoji = new Personaje("Ayanokoji", 100, 15, 30);

        sungjinwoo.atacar(ayanokoji);
        System.out.println(sungjinwoo.getVida());

        ayanokoji.atacar(sungjinwoo);

        sungjinwoo.registrarInventario();
        System.out.println(sungjinwoo.getVida());

    }

}
