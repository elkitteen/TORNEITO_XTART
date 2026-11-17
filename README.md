# TORNEITO XTART

## 📝 Descripción
**TORNEITO XTART** Es un videojuego desarrollado en Java aplicando los principios de la **Programación Orientada a Objetos (POO)**. El objetivo del juego es enfrentarse a enemigos desafiantes, acumular puntos y competir por las mejores posiciones en el ranking. Los 5 mejores recibirán premios en metálico otorgados por nuestros patrocinadores oficiales. 

El proyecto combina la lógica de programación del videojuego con un sistema de gestión de datos basado en tecnologías de marcas: persistencia en XML, validación estricta mediante DTD, consultas XPath y transformaciones XSLT procesadas con el motor **Saxon**.

---

## ⚙️ Requisitos y versiones
* **Java JDK:** Versión 25 o superior.
* **Control de versiones:** Git.
* **Procesador XSLT/XPath:** Motor **Saxon**

---

## 📂 Estructura del proyecto
La estructura de directorios y archivos del repositorio está organizada de la siguiente manera:

```text
DAW_DESPERTAR_HEROE_GARCIA_REINALDOALEJANDRO
│
├── .vscode/                # Archivos necesarios para usar xslt
│   └── tasks.json          
│
├── .gitignore              # Evita subir a git archivos innecesarios
│
├── Main.java               # Código fuente en java
                            # Clase principal de ejecución
│
├── Personaje.java          # Clase personaje para el combate y guardar en el ranking
│   
├── ranking.css             # Apartado visual del html (creado por el xslt)
├
├── ranking.dtd             # Reglas para el xml
│
├── ranking.xml             # Gestión de información para el ranking
│
├── rankingTorneo.xslt      # Convertidor de xml a html
│
└── README.md               # Documentación del proyecto
```

---

## 📥 Compilación y ejecución

### 1. Clonar el repositorio
Abre tu terminal y ejecuta el siguiente comando:
```bash
git clone https://github.com/elkitteen/TORNEITO_XTART.git
cd TORNEITO_XTART
```

### 2. Compilación del código fuente
Para compilar todas las clases Java incluyendo la librería de Saxon situada en la ruta relativa `lib/`:
* **En Windows (CMD / PowerShell):**
  ```bash
  javac -cp ".;*" *.java
  ```
* **En Linux / macOS:**
  ```bash
  javac -cp ".:*" *.java
  ```

### 3. Ejecución del programa
Una vez compilado, puedes arrancar la aplicación ejecutando:
* **En Windows:**
  ```bash
  java -cp ".;*" Main
  ```
* **En Linux / macOS:**
  ```bash
  java -cp ".:*" Main
  ```

---

## 🎮 Reglas del juego
1. **Combate:** El jugador se enfrenta a oleadas de enemigos. Cada enemigo derrotado otorga puntos que se acumulan en la puntuación total.
2. **Fin de la partida:** Si la salud del jugador llega a cero o completa todos los combates, la partida finaliza.
3. **Clasificación:** Al terminar, la puntuación obtenida se registra automáticamente para competir en el ranking global del torneo.

---

## 💾 Persistencia
La persistencia de los datos del juego (como el ranking de jugadores y puntuaciones) se gestiona mediante ficheros estructurados en formato XML ubicados en la ruta relativa:
* **Fichero de datos:** `ranking.xml`
Cada vez que se registra una nueva partida, el sistema lee y escribe de forma automatizada sobre este archivo.

---

## 🛡️ Validación DTD
Para asegurar la integridad y la correcta estructura del archivo de persistencia, los documentos XML generados o leídos por la aplicación se validan contra un esquema DTD:
* **Esquema de validación:** `ranking.dtd`
El código Java comprueba que el archivo `ranking.xml` cumple estrictamente con las reglas definidas en este DTD antes de procesarlo.

---

## 🔍 Ejecución de XPath

    /torneito/ranking/jugador/nombre
    
    /torneito/ranking/jugador[@id='2']/puntuacion

    /torneito/ranking/jugador[puntuacion = 300]/nombre

    /torneito/ranking/jugador[1]/nombre

    /torneito/ranking/jugador[puntuacion > 300]/puntuacion
---

## 🔄 Transformación XSLT
El sistema permite transformar y exportar los datos del ranking XML a otros formatos mediante hojas de estilo XSLT procesadas con el motor **Saxon**:
* **Hoja de estilo:** `rankingTorneo.xslt`
* **Origen de datos:** `ranking.xml`

---

## 👤 Autor

* **REINALDO ALEJANDRO GARCIA VILLAMEDIANA**
* GitHub: [@elkitteen](https://github.com/elkitteen)