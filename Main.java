import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import org.w3c.dom.Node;

public class Main {



    private static final String RUTA_XML = "ranking.xml";

    // Clase auxiliar interna para manejar los datos fácilmente antes de guardarlos
    private static class EntradaRanking {
        String nombre;
        double puntuacion;

        EntradaRanking(String nombre, double puntuacion) {
            this.nombre = nombre;
            this.puntuacion = puntuacion;
        }
    }




    public static void main(String[] args) {

        menuPrincipal();

    }

    public static void menuPrincipal() {

        boolean menuActivo = true;

        while (menuActivo) {

            Scanner scanner = new Scanner(System.in);

            System.out.println(
                    "-------------------------------------------------------------------------------------------------------");
            System.out
                    .println("            BIENVENIDO A TORNEITO XTART -- LOS MEJORES 5 CLASIFICADOS SE GANAN 5 EUROS");
            System.out.println(
                    "-------------------------------------------------------------------------------------------------------");

            System.out.println("\n1) Nueva Partida \n\n2) Consultar Ranking \n\n3) Ayuda \n\n4) Salir\n\n");

            System.out.println(
                    "Patrocinadores Oficiales: El Cortes Inglés ------ MediaMarkt ----- Apple ---- Ajuntament de Valencia ---- Samsung ---- Copa Monopatín \n");

            String menuOpcion = scanner.nextLine();
            System.out.println("\n");

            switch (menuOpcion) {
                case "1":

                    iniciarPartida(scanner);

                    break;

                case "2":

                    mostrarRanking(scanner);

                    break;

                case "3":

                    mostrarAyuda(scanner);

                    break;

                case "4":

                    menuActivo = false;
                    break;

                default:
                    System.out.println("Opción Invalida");
                    break;
            }

        }

    }

    public static void iniciarPartida(Scanner scanner) {

        System.out.println("==========================================");
        System.out.println("           EL DESPERTAR DEL HÉROE         ");
        System.out.println("==========================================\n");

        System.out.println(
                "Caminas de noche en medio de la lluvia en Plaza Espanya, Valencia y entras a Xtart para resguardarte. Ángela, de recepción, te mira con desconfianza y te pregunta:");
        System.out.print("— Eh, tú, ¿eres estudiante?... ¿Cómo te llamas?: ");
        String nombreJugador = scanner.nextLine();

        Personaje jugador = new Personaje(nombreJugador, 100, 15, 25);

        System.out.println("\n— Bien, " + jugador.getNombre()
                + ". Si quieres salir de este edificio con vida, tendrás que abrirte paso.");
        System.out.println(
                "Subes las escaleras hacia el piso 1. Entre las sombras aparece una figura bloqueando el paso.");
        System.out.println("— ¡Alto ahí! Nadie pasa por mi piso sin pagar peaje —grita José Luis.\n");

        Personaje joseLuis = new Personaje("José Luis", 60, 8, 14);
        boolean ganoCombate1 = simularCombate(jugador, joseLuis);

        if (!ganoCombate1) {
            return;
        }

        System.out.println(
                "José Luis cae derrotado al suelo. Logras subir al piso 3 y haces una pequeña parada en el baño.");
        System.out.println(
                "La iluminación es muy baja. Enciendes la luz; un monstruo con armadura oxidada y cara de diablo afila su espada.");
        System.out.println(
                "— Así que tú eres el que derrotó a José Luis... Veamos si puedes contra mí. Jarvis, activa el modo combate —dice Esteban.\n");

        Personaje esteban = new Personaje("Esteban", 85, 12, 18);
        boolean ganoCombate2 = simularCombate(jugador, esteban);

        if (!ganoCombate2) {
            return;
        }

        System.out.println(
                "Esteban cae de rodillas, vencido. Subes al último piso y finalmente llegas a las puertas de la fortaleza.");
        System.out.println("Allí te espera el líder de la guardia, imponente y con una mirada fría.");
        System.out.println("— Has llegado lejos, " + jugador.getNombre()
                + ", pero tu viaje termina aquí. Te enseñaré de lo que es capaz el rey de los kahoots —sentencia Pedro.\n");

        Personaje pedro = new Personaje("Pedro", 110, 15, 22);
        boolean ganoCombate3 = simularCombate(jugador, pedro);

        if (!ganoCombate3) {
            return;
        }

        int bonificacionPuntos = bonoPuntuacion(jugador);

        System.out.println("==========================================");
        System.out.println("¡FELICIDADES " + jugador.getNombre().toUpperCase() + "!");
        System.out.println("Has derrotado a José Luis, Esteban y Pedro.");
        System.out.println("Puntuación: " + jugador.getNumPuntos() + " puntos.");
        System.out.println("Bono de puntuación: " + bonificacionPuntos + " puntos.");
        jugador.setNumPuntos(jugador.getNumPuntos() + bonificacionPuntos);
        System.out.println("Puntuación total " + jugador.getNumPuntos() + " puntos");
        actualizarRanking(jugador);
        System.out.println("==========================================\n");

    }

    public static int bonoPuntuacion(Personaje jugador) {

        int vida = jugador.getVida();
        int bonoPuntuacion;

        if (vida >= 30 ) {
            bonoPuntuacion = 30;
        }
        else if (vida >= 15) {
            bonoPuntuacion = 15;
        }
        else {
            bonoPuntuacion = 5;
        }

        
        
        
        return bonoPuntuacion;

    }


    // METODO NUEVO PARA LEER EL XML
    public static void mostrarRanking(Scanner scanner) {

        boolean mostrarRanking = true;

        while (mostrarRanking) {

            try {
                File archivo = new File("ranking.xml");
                DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
                DocumentBuilder constructor = fabrica.newDocumentBuilder();
                Document documento = constructor.parse(archivo);
                documento.getDocumentElement().normalize();

                NodeList lista = documento.getElementsByTagName("jugador");

                System.out.println("========= TOP " + lista.getLength() + " RANKING =========");

                for (int i = 0; i < lista.getLength(); i++) {
                    Element jugador = (Element) lista.item(i);

                    String id = jugador.getAttribute("id");
                    String nombre = jugador.getElementsByTagName("nombre").item(0).getTextContent();
                    String puntuacion = jugador.getElementsByTagName("puntuacion").item(0).getTextContent();

                    System.out.println("#" + id + " | " + nombre + " - " + puntuacion + " pts");
                }
                System.out.println("===================================\n");

            } catch (Exception e) {
                System.out.println("Error al leer el ranking XML: " + e.getMessage());
            }

            System.out.println("1) Volver");
            String mostrarRankingDecision = scanner.nextLine();

            if (mostrarRankingDecision.equals("1")) {
                mostrarRanking = false;
            }

        }

    }

    //NUEVA FUNCION PARA ACTUALIZAR EL RANKING
    public static void actualizarRanking(Personaje personaje) {
        try {
            File archivo = new File("ranking.xml");
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(archivo);
            documento.getDocumentElement().normalize();

            // Paso A: Leer los jugadores actuales y guardarlos en una lista
            List<EntradaRanking> listaRanking = new ArrayList<>();
            NodeList nodosJugadores = documento.getElementsByTagName("jugador");

            for (int i = 0; i < nodosJugadores.getLength(); i++) {
                Element jugador = (Element) nodosJugadores.item(i);
                String nombre = jugador.getElementsByTagName("nombre").item(0).getTextContent();
                double puntos = Double.parseDouble(jugador.getElementsByTagName("puntuacion").item(0).getTextContent());
                
                listaRanking.add(new EntradaRanking(nombre, puntos));
            }

            // Paso B: Añadir a nuestro personaje a la lista
            listaRanking.add(new EntradaRanking(personaje.getNombre(), personaje.getNumPuntos()));

            // Paso C: Ordenar de mayor a menor.
            // Al usar Double.compare(b, a), Java mantiene el orden original si hay empate (sort estable).
            listaRanking.sort((a, b) -> Double.compare(b.puntuacion, a.puntuacion));

            // Paso D: Quedarnos solo con el Top 5
            if (listaRanking.size() > 5) {
                listaRanking = listaRanking.subList(0, 5);
            }

            // Paso E: Limpiar los nodos <jugador> antiguos dentro de <ranking>
            Element etiquetaRanking = (Element) documento.getElementsByTagName("ranking").item(0);
            while (etiquetaRanking.hasChildNodes()) {
                etiquetaRanking.removeChild(etiquetaRanking.getFirstChild());
            }

            // Paso F: Volver a crear los 5 elementos <jugador> ya ordenados y con ID del 1 al 5
            for (int i = 0; i < listaRanking.size(); i++) {
                EntradaRanking entrada = listaRanking.get(i);

                Element nuevoJugador = documento.createElement("jugador");
                nuevoJugador.setAttribute("id", String.valueOf(i + 1));

                Element nuevoNombre = documento.createElement("nombre");
                nuevoNombre.setTextContent(entrada.nombre);

                Element nuevaPuntuacion = documento.createElement("puntuacion");
                // Si el número es entero (ej: 300.0), lo guardamos sin ".0" para mantener tu formato limpio
                if (entrada.puntuacion % 1 == 0) {
                    nuevaPuntuacion.setTextContent(String.valueOf((int) entrada.puntuacion));
                } else {
                    nuevaPuntuacion.setTextContent(String.valueOf(entrada.puntuacion));
                }

                nuevoJugador.appendChild(nuevoNombre);
                nuevoJugador.appendChild(nuevaPuntuacion);
                etiquetaRanking.appendChild(nuevoJugador);
            }

            // Paso G: Guardar los cambios en el archivo XML manteniendo el DOCTYPE
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            // Preservar la referencia a ranking.dtd si existe
            if (documento.getDoctype() != null) {
                transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, documento.getDoctype().getSystemId());
            }

            DOMSource origen = new DOMSource(documento);
            StreamResult destino = new StreamResult(archivo);
            transformer.transform(origen, destino);

            System.out.println("Ranking comprobado y actualizado correctamente.");

        } catch (Exception e) {
            System.out.println("Error al modificar el ranking XML: " + e.getMessage());
        }
    }







    public static void mostrarAyuda(Scanner scanner) {

        boolean mostrarAyuda = true;

        while (mostrarAyuda) {

            System.out.println(
                    "¿QUÉ ES TORNEITO XTART?: ES UN JUEGO EN DONDE COMPETIRAS DERROTANDO ENEMIGOS. SI CONSIGUES SUFICIENTES PUNTOS PODRÁS CLASIFICAR EN EL RANKING Y COMPETIR POR GRANDES PREMIOS \n\n ");

            System.out.println(
                    "CUANDO EMPIECE UN COMBATE PODRAS DECIDIR ENTRE ATACAR, REVISAR INVENTARIO Y ABANDONAR. ASEGURATE DE ATACAR Y SI NECESITAS CURARTE PUEDES REVISAR INVENTARIO. SIEMPRE TENDRAS 3 CURACIONES PERO CUIDADO QUE SE AGOTAN.\n\n ");

            System.out.println(
                    "QUE PASA SI ABANDONO?: SI ABANDONAS VOLVERAS AL MENÚ PRINCIPAL, SIN EMBARGO, LOS PUNTOS QUE HAYAS GANADO SE GUARDARAN EN EL RANKING \n\n ");

            System.out.println("1) Volver");
            String mostrarAyudaOpcion = scanner.nextLine();

            if (mostrarAyudaOpcion.equals("1")) {
                mostrarAyuda = false;
            }

        }

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
            actualizarRanking(jugador);

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
            actualizarRanking(jugador);

            return false;
        }

    }

}