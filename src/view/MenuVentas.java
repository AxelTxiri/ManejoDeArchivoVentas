package view;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.VentaController;
import model.Venta;

public class MenuVentas {
    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        VentaController ventaController = new VentaController();
        int option = 0;

        do {
            System.out.println("**************************************");
            System.out.println("*                MENU                *");
            System.out.println("**************************************");
            System.out.println("*   1. Crear archivo de ventas.      *");
            System.out.println("*   2. Abrir archivo de ventas.      *");
            System.out.println("*   3. Registrar venta.              *");
            System.out.println("*   4. Leer archivo de ventas.       *");
            System.out.println("*   5. Calcular las ventas.          *");
            System.out.println("*   6. Respaldar archivo ventas.     *");
            System.out.println("*   7. Eliminar archivo de ventas.   *");
            System.out.println("*   8. Cerrar programa.              *");
            System.out.println("**************************************");
            System.out.println("*     Ingrese la opcion (numero):    *");
            System.out.println("**************************************");
            option = leerOpcion();
            System.out.println("**************************************");
            System.out.println();

                switch (option) {
                    case 1:
                        System.out.println("Opcion 1: Crear archivo de ventas.");
                        String nombreArchivo = "";

                        System.out.println("Ingrese el nombre del archivo de ventas (sin extension):");
                        nombreArchivo = scanner.next();
                        ventaController.crearArchivoVentas(nombreArchivo);
                        break;
                    case 2:
                        System.out.println("Opcion 2: Abrir archivos de ventas.");
                        ventaController.abrirArchivos();
                        break;
                    case 3:
                        System.out.println("Opcion 3: Registrar venta.");
                        
                        if(ventaController.isListaArchivosEmpty()) {
                            int seleccionArchivo = 0;
                            String nombreProducto = "";
                            int cantidad = 0;
                            double precioUnitario = 0.0;
                            System.out.println("Seleccione un archivo de ventas para registrar la venta:");
                            ventaController.listaArchivos();
                            System.out.println("Ingrese el numero del archivo:");
                            seleccionArchivo = leerOpcion();
                            scanner.nextLine();

                            System.out.println("Ingrese el nombre del producto:");
                            nombreProducto = scanner.nextLine();
                            System.out.println("Ingrese la cantidad:");
                            cantidad = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("Ingrese el precio unitario:");
                            precioUnitario = scanner.nextDouble();

                            Venta venta = new Venta(nombreProducto, cantidad, precioUnitario);

                            ventaController.registrarVenta(seleccionArchivo, venta);
                        } else {
                            System.out.println("No se encontraron archivos de ventas donde registrar la venta.");
                        }

                        break;
                    case 4:
                        System.out.println("Opcion 4: Leer archivo de ventas.");
                        
                        if(ventaController.isListaArchivosEmpty()) {
                            int seleccionArchivo = 0;
                            System.out.println("Seleccione un archivo de ventas para leer su contenido:");
                            ventaController.listaArchivos();
                            System.out.println("Ingrese el numero del archivo:");
                            seleccionArchivo = leerOpcion();

                            ventaController.leerArchivoVentas(seleccionArchivo);
                        } else {
                            System.out.println("No se encontraron archivos de ventas para leer.");
                        }
                        break;
                    case 5:
                        System.out.println("Opcion 5: Calcular las ventas.");
                        
                        if(ventaController.isListaArchivosEmpty()) {
                            int seleccionArchivo = 0;
                            System.out.println("Seleccione un archivo para calcular las ventas registradas:");
                            ventaController.listaArchivos();
                            System.out.println("Ingrese el numero del archivo:");
                            seleccionArchivo = leerOpcion();

                            ventaController.calcularVentas(seleccionArchivo);
                        } else {
                            System.out.println("No se encontraron archivos de ventas para calcular las ventas.");
                        }
                        break;
                    case 6:
                        System.out.println("Opcion 6: Respaldar archivo ventas.");
                        
                        if(ventaController.isListaArchivosEmpty()) {
                            int seleccionArchivo = 0;
                            System.out.println("Seleccione un archivo para respaldar:");
                            ventaController.listaArchivos();
                            System.out.println("Ingrese el numero del archivo:");
                            seleccionArchivo = leerOpcion();

                            ventaController.respaldarArchivoVentas(seleccionArchivo);
                        } else {
                            System.out.println("No se encontraron archivos de ventas para respaldar.");
                        }
                        break;
                    case 7:
                        System.out.println("Opcion 7: Eliminar archivo de ventas.");
                        
                        if(ventaController.isListaArchivosEmpty()) {
                            int seleccionArchivo = 0;
                            System.out.println("Seleccione un archivo para eliminar:");
                            ventaController.listaArchivos();
                            System.out.println("Ingrese el numero del archivo:");
                            seleccionArchivo = leerOpcion();

                            ventaController.eliminarArchivoVentas(seleccionArchivo);
                        } else {
                            System.out.println("No se encontraron archivos de ventas para eliminar.");
                        }

                        break;
                    case 8:
                        System.out.println("Cerrando programa...");
                        break;
                    default:
                        System.out.println("Opcion no valida. Por favor, ingrese un numero del 1 al 8.");
                        System.out.println();
                }
        } while (option != 8);
        scanner.close();
    }

    public int leerOpcion() {
        try {
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();
            return option;
        } catch (InputMismatchException e) {
            System.out.println("ERROR: Entrada no valida. Por favor, ingrese un numero valido.");
            System.out.println();
            return -1;
        }
    }
}
