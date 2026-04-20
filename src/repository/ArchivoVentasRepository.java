package repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import model.Venta;

public class ArchivoVentasRepository {
    private static final String RUTA_CARPETA = "src\\resources";
    private static final File CARPETA = new File(RUTA_CARPETA);
    public static final ArrayList<File> LISTA_ARCHIVOS = new ArrayList<>(Arrays.asList(CARPETA.listFiles()));

    public void crearArchivo(String nombreArchivo) {
        String rutaArchivo = "";

        rutaArchivo = RUTA_CARPETA + "\\" + nombreArchivo + ".txt";
        
        File archivo = new File(rutaArchivo);

        try {
            if (archivo.createNewFile()) {
                System.out.println("El archivo '" + nombreArchivo + "' fue creado exitosamente." );
                System.out.println();
                LISTA_ARCHIVOS.add(archivo);
            } else {
                System.out.println("El archivo '" + nombreArchivo + "' ya existe." );
                System.out.println();
            }
        } catch (IOException e) {
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
        try(FileWriter writer  = new FileWriter(LISTA_ARCHIVOS.get(seleccionArchivo-1), true)) {
            writer.write(venta.getProducto() + "-" + venta.getCantidad() + "-" + venta.getPrecioUnitario() + "\n");
            System.out.println("Venta registrada exitosamente en el archivo: " + LISTA_ARCHIVOS.get(seleccionArchivo-1).getName());  
            System.out.println();
        } catch (IOException e) {
            System.out.println("Ocurrió un error al registrar la venta.");
            System.out.println();

        }
    }

    public void leerArchivo(int seleccionArchivo) {
        try {
            File archivo = LISTA_ARCHIVOS.get(seleccionArchivo-1);
            Scanner scanner = new Scanner(archivo);

            if (archivo.exists()) {
                System.out.println("Ventas registradas en el archivo '" + archivo.getName() + "':");
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    String[] datosVenta = linea.split("-");
                    if (datosVenta.length == 3) {
                        String producto = datosVenta[0];
                        int cantidad = Integer.parseInt(datosVenta[1]);
                        double precioUnitario = Double.parseDouble(datosVenta[2]);
                        System.out.println("Producto: " + producto + ", Cantidad: " + cantidad + ", Precio Unitario: " + precioUnitario);
                    }
                }
                System.out.println();
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo.");
            System.out.println();
        }
    }

    public void calcularVentas(int seleccionArchivo) {
        try {
            File archivo = LISTA_ARCHIVOS.get(seleccionArchivo-1);
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
                System.out.println("El total de ventas registradas en el archivo '" + archivo.getName() + "' es: " + totalVentas);
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al calcular las ventas.");
            System.out.println();
        }
    }

    public void respaldarArchivo(int seleccionArchivo) {
        try {
            File archivoOriginal = LISTA_ARCHIVOS.get(seleccionArchivo-1);
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
                    System.out.println("Archivo '" + archivoOriginal.getName() + "' respaldado exitosamente como '" + nombreArchivoRespaldo + "'.");
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
        File archivo = LISTA_ARCHIVOS.get(seleccionArchivo-1);
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
