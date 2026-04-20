package repository;

/**
 * Clase que representa archivos y directorios en el sistema.
 * Permite crear, eliminar y manipular archivos.
 */
import java.io.File;

/**
 * Permite escribir texto dentro de un archivo.
 * Se usa para registrar ventas en los archivos .txt.
 */
import java.io.FileWriter;

/**
 * Maneja errores relacionados con operaciones de entrada/salida.
 * Ejemplo: errores al crear, leer o escribir archivos.
 */
import java.io.IOException;

/**
 * Estructura de datos dinámica tipo lista.
 * Se utiliza para almacenar los archivos de ventas en memoria.
 */
import java.util.ArrayList;

/**
 * Proporciona utilidades para trabajar con arreglos.
 * Puede usarse para convertir arreglos en listas u otras operaciones.
 */
import java.util.Arrays;

/**
 * Permite leer datos desde la consola o desde archivos.
 * Se usa para leer el contenido de archivos de ventas.
 */
import java.util.Scanner;

/**
 * Clase utilitaria para operaciones modernas con archivos.
 * Permite copiar, borrar y manipular archivos de forma eficiente.
 */
import java.nio.file.Files;

/**
 * Representa rutas de archivos de forma más flexible que File.
 * Se usa para definir origen y destino en operaciones de archivos.
 */
import java.nio.file.Path;

/**
 * Permite crear objetos Path a partir de rutas en forma de texto.
 */
import java.nio.file.Paths;

/**
 * Importa el modelo Venta.
 * Se utiliza para trabajar con los datos de una venta dentro del repository.
 */
import model.Venta;

/**
 * Clase encargada de manejar los archivos de ventas.
 * 
 * Aquí se centraliza todo lo relacionado con:
 * - creación de archivos
 * - lectura
 * - escritura
 * - eliminación
 * - respaldo
 */
public class ArchivoVentasRepository {

    /**
     * Ruta donde se almacenan los archivos de ventas.
     * 
     * - Es un String que representa la ubicación de la carpeta.
     * - En este caso: "src\resources"
     * - Todos los archivos .txt se crearán y gestionarán aquí.
     */
    private static final String RUTA_CARPETA = "src\\resources";

    /**
     * Objeto File que representa la carpeta física en el sistema.
     * 
     * - Se construye usando la ruta definida arriba.
     * - Permite acceder a los archivos dentro de esa carpeta.
     * - Se usa para operaciones como listar archivos.
     */
    private static final File CARPETA = new File(RUTA_CARPETA);

    /**
     * Lista que almacena los archivos encontrados en la carpeta.
     * 
     * Flujo:
     * - CARPETA.listFiles() obtiene todos los archivos dentro de la carpeta
     * - Arrays.asList(...) convierte el arreglo en una lista
     * - Se crea un ArrayList a partir de esa lista
     * 
     * Características:
     * - static: se comparte en toda la aplicación
     * - final: no se puede reasignar (pero sí modificar su contenido)
     * 
     * Uso:
     * - Permite trabajar con los archivos como una lista
     * - Se usa para mostrar archivos al usuario
     * - Se usa para seleccionar archivos por índice
     * - Se actualiza cada vez que se crea o elimina un archivo
     * 
     * Nota:
     * - Si la carpeta no existe o está vacía, la lista será vacía pero no null.
     * 
     */
    public static final ArrayList<File> LISTA_ARCHIVOS = new ArrayList<>(Arrays.asList(CARPETA.listFiles()));

    /**
     * Crea un archivo de ventas en la carpeta definida.
     * 
     * Flujo:
     * 1. Construye la ruta completa del archivo usando:
     * - la ruta base (RUTA_CARPETA)
     * - el nombre ingresado por el usuario
     * - la extensión ".txt"
     * 
     * 2. Crea un objeto File con esa ruta
     * 
     * 3. Intenta crear el archivo físicamente:
     * - Si no existe → lo crea
     * - Si ya existe → no hace nada
     * 
     * 4. Si se crea correctamente:
     * - Muestra mensaje en consola
     * - Agrega el archivo a LISTA_ARCHIVOS
     * 
     * 5. Si ocurre un error:
     * - Se captura la excepción
     * - Se muestra un mensaje de error
     * 
     * @param nombreArchivo nombre del archivo sin extensión
     */
    public void crearArchivo(String nombreArchivo) {
        // Variable que almacenará la ruta completa del archivo
        String rutaArchivo = "";
        // Construcción de la ruta: carpeta + nombre + extensión
        rutaArchivo = RUTA_CARPETA + "\\" + nombreArchivo + ".txt";
        // Se crea el objeto File con la ruta especificada
        File archivo = new File(rutaArchivo);

        try {
            // Intenta crear el archivo en el sistema
            if (archivo.createNewFile()) {
                // Mensaje si el archivo fue creado correctamente
                System.out.println("El archivo '" + nombreArchivo + "' fue creado exitosamente.");
                System.out.println();
                // Se agrega el archivo a la lista en memoria
                LISTA_ARCHIVOS.add(archivo);
            } else {
                // Mensaje si el archivo ya existía
                System.out.println("El archivo '" + nombreArchivo + "' ya existe.");
                System.out.println();
            }
        } catch (IOException e) {
            // Manejo de error si falla la creación del archivo
            System.out.println("Ocurrió un error al crear el archivo.");
            System.out.println();
        }
    }

    public void abrirArchivos() {
        int contador = 0;

        if (CARPETA.exists() && CARPETA.isDirectory()) {
            if (LISTA_ARCHIVOS != null) {
                System.out.println("Archivos de ventas disponibles:");
                for (File archivo : LISTA_ARCHIVOS) {
                    contador++;
                    if (archivo.isFile()) {
                        System.out.println(contador + ". " + archivo.getName());
                    }
                }
                System.out.println();
            } else {
                System.out.println("No se encontraron archivos de ventas.");
                System.out.println();
            }
        }
    }

    public void registrarVenta(int seleccionArchivo, Venta venta) {
        try (FileWriter writer = new FileWriter(LISTA_ARCHIVOS.get(seleccionArchivo - 1), true)) {
            writer.write(venta.getProducto() + "-" + venta.getCantidad() + "-" + venta.getPrecioUnitario() + "\n");
            System.out.println("Venta registrada exitosamente en el archivo: "
                    + LISTA_ARCHIVOS.get(seleccionArchivo - 1).getName());
            System.out.println();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar la venta.");
            System.out.println();

        }
    }

    public void leerArchivo(int seleccionArchivo) {
        try {
            File archivo = LISTA_ARCHIVOS.get(seleccionArchivo - 1);
            Scanner scanner = new Scanner(archivo);

            if (archivo.exists() && scanner.hasNextLine()) {
                System.out.println("Ventas registradas en el archivo '" + archivo.getName() + "':");
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] datosVenta = linea.split("-");
                    if (datosVenta.length == 3) {
                        String producto = datosVenta[0];
                        int cantidad = Integer.parseInt(datosVenta[1]);
                        double precioUnitario = Double.parseDouble(datosVenta[2]);
                        System.out.println("Producto: " + producto + ", Cantidad: " + cantidad + ", Precio Unitario: "
                                + precioUnitario);
                    }
                }
                System.out.println();
                scanner.close();
            }else {
                System.out.println("El archivo '" + archivo.getName() + "' está vacío o no existe.");
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo.");
            System.out.println();
        }
    }

    public void calcularVentas(int seleccionArchivo) {
        try {
            File archivo = LISTA_ARCHIVOS.get(seleccionArchivo - 1);
            Scanner scanner = new Scanner(archivo);
            double totalVentas = 0.0;

            if (archivo.exists()) {
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] datosVenta = linea.split("-");
                    if (datosVenta.length == 3) {
                        int cantidad = Integer.parseInt(datosVenta[1]);
                        double precioUnitario = Double.parseDouble(datosVenta[2]);
                        totalVentas += cantidad * precioUnitario;
                    }
                }
                scanner.close();
                leerArchivo(seleccionArchivo);
                System.out.println(
                        "El total de ventas registradas en el archivo '" + archivo.getName() + "' es: " + totalVentas);
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al calcular las ventas.");
            System.out.println();
        }
    }

    public void respaldarArchivo(int seleccionArchivo) {
        try {
            File archivoOriginal = LISTA_ARCHIVOS.get(seleccionArchivo - 1);
            String nombreArchivoRespaldo = archivoOriginal.getName().replace(".txt", "_respaldo.txt");

            Path origen = Paths.get(archivoOriginal.getAbsolutePath());
            Path destino = Paths.get(CARPETA.getAbsolutePath(), nombreArchivoRespaldo);

            for (File archivo : LISTA_ARCHIVOS) {
                if (archivo.getName().equals(nombreArchivoRespaldo)) {
                    System.out.println("Ya existe un respaldo para el archivo '" + archivoOriginal.getName() + "'.");
                    System.out.println();
                    return;
                } else {
                    Files.copy(origen, destino);
                    LISTA_ARCHIVOS.add(destino.toFile());
                    System.out.println("Archivo '" + archivoOriginal.getName() + "' respaldado exitosamente como '"
                            + nombreArchivoRespaldo + "'.");
                    System.out.println();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al respaldar el archivo.");
            System.out.println();
        }
    }

    public void eliminarArchivo(int seleccionArchivo) {
        File archivo = LISTA_ARCHIVOS.get(seleccionArchivo - 1);
        if (archivo.delete()) {
            System.out.println("Archivo '" + archivo.getName() + "' eliminado exitosamente.");
            System.out.println();
            LISTA_ARCHIVOS.remove(archivo);
        } else {
            System.out.println("No se pudo eliminar el archivo '" + archivo.getName() + "'.");
            System.out.println();
        }
    }

    public void listaArchivos() {
        int contador = 0;

        if (CARPETA.exists() && CARPETA.isDirectory()) {

            if (LISTA_ARCHIVOS != null) {
                System.out.println("Archivos de ventas disponibles:");
                for (File archivo : LISTA_ARCHIVOS) {
                    contador++;
                    if (archivo.isFile()) {
                        System.out.println(contador + ". " + archivo.getName());
                    }
                }
                System.out.println();
            }
        }
    }
}
