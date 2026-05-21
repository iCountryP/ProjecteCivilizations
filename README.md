

# 🏰 DOMINION ⚔️

**Dominion** es un videojuego de estrategia en tiempo real, gestión de recursos y supervivencia de oleadas desarrollado en el lenguaje **Java**. Construye tu civilización desde cero, gestiona tus recursos y prepara a tus tropas para sobrevivir a las constantes invasiones enemigas.

¿Serás capaz de resistir el asedio o tu civilización caerá en el olvido?

---

## 🎮 Sobre el Juego

En Dominion, asumes el papel de un Emperador de una nueva civilización. El objetivo principal es manejar y equilibrar el crecimiento de tus recursos con el poder militar reclutando tropas. El tiempo corre en tu contra: **cada 3 minutos, un ejército enemigo invadirá tus tierras**, y su fuerza y recursos crecerán  tras cada oleada. ¿Cuántas podrás sobrevivir?

Para garantizar el futuro de tu civilización, tendrás que construir edificios estratégicos en el mapa, mejorar tus tecnologías y reclutar un ejército equilibrado aprovechando mecánicas únicas como la "Santificación" de las tropas.

### ✨ Características Principales

* **⏱️ Recursos en Tiempo Real:** Generación automática de Madera, Comida, Hierro y Maná. Construye Granjas, Carpinterías, Herrerías y Torres Mágicas para multiplicar tu genración.
* **🗺️ Construcción Visual:** Sistema de cuadrícula interactiva (10x10) donde puedes colocar tus edificios y ver tu imperio crecer gráficamente.
* **⚔️ Tipos De Unidades:** 9 tipos de unidades militares divididas en Ataque, Defensa y Especiales (desde Espadachines y Ballesteros, hasta Cañones y Magos).
* **✨ Mecánicas Adicionales:** Recluta Sacerdotes en tu Iglesia para lanzar un "Hechizo de Área" que santifica a tus tropas veteranas, otorgándoles *buffos* permanentes de ataque y armadura, por la gloria del señor.
* **📈 Áumento de Tecnologías:** Invierte recursos en mejorar el nivel de Ataque y Defensa de toda tu civilización de forma global.
* **💾 Persistencia de Datos:** Sistema de guardado y carga de partidas integrado con una base de datos SQL. Deja tu marca escrita en la historia.

---

## 🛠️ Tecnologías Utilizadas

Este proyecto ha sido desarrollado aplicando la **Programación Orientada a Objetos**, utilizando sus ventajas como herencia, interfaces,e tc.

* **Lenguaje:** Java 
* **Interfaz Gráfica:** Java Swing (JFrame, JPanel, Graphics2D, etc.)
* **Base de Datos:** SQL (Conexión JDBC)
* **Control de Flujo:** Gestión de eventos (`ActionListeners`), Temporizadores (`javax.swing.Timer`) y Excepciones personalizadas para las situaciones del juego.

---

## 🚀 Instalación y Ejecución

## Instalación (Programa de java)
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/iCountryP/ProjecteCivilizations.git
   ```

2. Exportar la carpeta del proyecto en eclipse.

3. Asegurarse de usar el jdk correspondiente (25.0.2) y el nivel de compilación de Java 25.

4. Ajustar los parametros de las credenciales de tu base de datos:
   ```java
   public final class DatabaseUtils {

    private static final String USER = "game";
    private static final String PASSWORD = "lancero777";
    private static final String DB_URL = "jdbc:mysql://localhost/dominion?serverTimezone=UTC";
	
	private DatabaseUtils() {

	}
   ```

## Instalación (Base de datos)

*Nosotros hemos utilizado Ubuntu para hostear nuestra base de datos, así que el tutorial a continuación serán los pasos para Ubuntu o distribuciones parecidas.*

1. Instalar MySQL Server:
   ```bash
   sudo apt install mysql-server
   ```

2. Activar el servicio:
   ```bash
   sudo service mysql start
   ```

3. Accedemos a la base de datos:
   ```bash
   sudo mysql -u root
   ```

4. Dentro de la base de datos creamos el usuario que utilizará el programa python para acceder a la base de datos:
   ```sql
   CREATE USER 'game'@'host' IDENTIFIED BY 'contraseña';
   exit
   ```
> game --> Usuario que queramos usar para conectar el programa a la base de datos

> host --> IP donde se encuentre la base de datos, si está en local puede ponerse localhost o 127.0.0.1

> contraseña --> Cambiala por la contraseña que quieras utilizar

5. Ejecutar los scripts .sql en el siguiente orden (Se pueden encontrar en M02)
   ```bash
   sudo mysql -u root < create_database.sql
   ```

6. Le damos permisos al usuario dentro de la base de datos recien creada
   ```sql
   GRANT ALL PRIVILEGES ON dominion* TO 'game'@'localhost';
   FLUSH PRIVILEGES;
   ```

---


---
## ✒️ Autores

Equipo de desarrollo:
* **Hector Gonzalez** - Programador
* **Anjie Chen** - Diseñador web
* **Iván Muriel** - Programador
