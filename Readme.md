# GitHub Activity CLI

CLI en Java para consultar actividad reciente de un usuario de GitHub usando la API publica y mostrar resultados en terminal.

Proyecto basado en: https://roadmap.sh/projects/github-user-activity

## Caracteristicas actuales

- Solicita un `username` por consola.
- Consulta `https://api.github.com/users/{username}/events`.
- Convierte la respuesta JSON a objetos Java con Jackson.
- Muestra datos del actor en terminal con salida coloreada (Jansi).

## Stack tecnico

- Java (Maven)
- Jackson (`jackson-databind`)
- Jansi
- JUnit 4/5 (tests)

## Estructura principal

- `src/main/java/org/ginjol/Main.java`: punto de entrada CLI.
- `src/main/java/org/ginjol/githubactivity/ConnectionGithub.java`: llamada HTTP y parseo JSON.
- `src/main/java/org/ginjol/githubactivity/utils/Printer.java`: salida por consola.
- `src/main/java/org/ginjol/githubactivity/models/*`: modelos de datos.
- `src/test/java/ConnectionGithubTest.java`: test de conexion basico.

## Requisitos

- JDK **24** (segun `pom.xml`, `maven.compiler.source/target=24`).
- Maven 3.9+.
- Conexion a internet (acceso a `api.github.com`).

> Nota: en entornos con JDK inferior, Maven fallara con `invalid target release: 24`.

## Instalacion

```powershell
git clone https://github.com/ginjol83/githubActivity.git
cd githubActivity
```

## Compilar

```powershell
mvn clean compile
```

## Ejecutar

### Opcion 1 (recomendada): desde IntelliJ

Ejecuta la clase `org.ginjol.Main`.

### Opcion 2: desde terminal (PowerShell)

```powershell
mvn -q -DskipTests compile
mvn -q dependency:build-classpath -Dmdep.outputFile=cp.txt
java -cp "target/classes;$(Get-Content cp.txt -Raw)" org.ginjol.Main
```

## Uso

Al iniciar, la app pide un usuario:

```text
Intro Username!
```

Ejemplo de entrada:

```text
ginjol83
```

Salida esperada (formato aproximado):

```text
Github user data:
------------------
User Id: 23209646
Login: ginjol83
Url: https://api.github.com/users/ginjol83
Avatar: https://avatars.githubusercontent.com/u/23209646?
```

## Tests

```powershell
mvn test
```

Si aparece `invalid target release: 24`, instala JDK 24 o ajusta temporalmente `source/target` en `pom.xml` a tu version local.

## Limitaciones actuales

- Solo se procesa el primer evento del array de actividad.
- El parseo de `payload` asume campos de `PushEvent`.
- Falta manejo robusto de errores para usuarios inexistentes o respuestas vacias.

## Roadmap sugerido

- Mostrar varios eventos recientes (no solo el primero).
- Formatear salida por tipo de evento (`PushEvent`, `WatchEvent`, `CreateEvent`, etc.).
- Mejorar manejo de errores HTTP y validaciones null-safe.
- Agregar mas tests unitarios y de integracion.

## Creditos

- Challenge: https://roadmap.sh/projects/github-user-activity

pendiente : 

Muestra la actividad obtenida en la terminal.

JavaScript

Output:
- Pushed 3 commits to kamranahmedse/developer-roadmap
- Opened a new issue in kamranahmedse/developer-roadmap
- Starred kamranahmedse/developer-roadmap

Gestionar los errores de forma adecuada, como nombres de usuario no válidos o fallos en la API.


