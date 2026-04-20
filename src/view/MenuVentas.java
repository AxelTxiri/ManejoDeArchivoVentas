package view;

/**
 * Importa la excepción que ocurre cuando el usuario ingresa un tipo de dato incorrecto.
 * Ejemplo: escribir letras cuando se espera un número.
 */
import java.util.InputMismatchException;

// Permite leer datos desde la consola (entrada del usuario).
import java.util.Scanner;

/**
 * Importa el controlador de ventas.
 * Se encarga de ejecutar la lógica del programa.
 */
import controller.VentaController;
/**
 * Importa el modelo Venta.
 * Representa los datos de una venta (producto, cantidad, precio).
 */
import model.Venta;
import repository.ArchivoVentasRepository;

/**
 * Clase que representa el menú del sistema (interfaz de usuario).
 * Aquí se muestra el menú y se gestionan las opciones del usuario.
 */
public class MenuVentas {
    static final Scanner SCANNER = new Scanner(System.in);

    /**
     * Método principal del menú.
     * Muestra las opciones y controla el flujo del programa.
     */
    public void showMenu() {
        // Instancia del controlador para ejecutar acciones
        VentaController ventaController = new VentaController();
        // Variable que guarda la opción seleccionada por el usuario
        int option = 0;

        /**
         * Ciclo que mantiene el menú activo hasta que el usuario elija salir (opción 8)
         */
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
            // Metodo que lee la opción del usuario
            option = leerOpcion();
            System.out.println("**************************************");
            System.out.println();

            /**
             * Estructura que ejecuta acciones dependiendo de la opción elegida
             */
            switch (option) {
                case 1:
                    // Crear archivo de ventas
                    System.out.println("Opcion 1: Crear archivo de ventas.");
                    String nombreArchivo = "";

                    // Se pide el nombre del archivo al usuario (sin extensión)
                    System.out.println("Ingrese el nombre del archivo de ventas (sin extension):");
                    nombreArchivo = SCANNER.nextLine();

                    // Se envía el nombre al controlador para crear el archivo
                    ventaController.crearArchivoVentas(nombreArchivo);
                    break;
                case 2:
                    // Mostrar lista de archivos de ventas disponibles
                    System.out.println("Opcion 2: Abrir archivos de ventas.");
                    ventaController.abrirArchivos();
                    break;
                case 3:
                    // Registrar una venta en un archivo de ventas
                    System.out.println("Opcion 3: Registrar venta.");

                    // Se verifica si hay archivos disponibles
                    if (!ventaController.isListaArchivosEmpty()) {
                        int seleccionArchivo = 0;
                        String nombreProducto = "";
                        int cantidad = 0;
                        double precioUnitario = 0.0;

                        // Mostrar archivos disponibles para registrar la venta
                        System.out.println("Seleccione un archivo de ventas para registrar la venta:");
                        ventaController.listaArchivos();

                        // Selección del archivo donde se registrará la venta
                        System.out.println("Ingrese el numero del archivo:");
                        seleccionArchivo = leerOpcion();

                        if (seleccionArchivo < 1 || seleccionArchivo - 1 >= ArchivoVentasRepository.LISTA_ARCHIVOS.size()) {
                            System.out.println("Opcion no valida. Por favor, ingrese un numero del 1 al " + ArchivoVentasRepository.LISTA_ARCHIVOS.size() + ".");
                            System.out.println();
                            break;
                        } else {
                            // Datos de la venta
                            System.out.println("Ingrese el nombre del producto:");
                            nombreProducto = SCANNER.nextLine();
                            System.out.println("Ingrese la cantidad:");
                            cantidad = SCANNER.nextInt();
                            SCANNER.nextLine();
                            System.out.println("Ingrese el precio unitario:");
                            precioUnitario = SCANNER.nextDouble();
                            SCANNER.nextLine();

                            // Se crea el objeto Venta con los datos ingresados
                            Venta venta = new Venta(nombreProducto, cantidad, precioUnitario);

                            // Se envía al controlador para registrar la venta en el archivo seleccionado
                            ventaController.registrarVenta(seleccionArchivo, venta);
                        }
                    } else {
                        // No hay archivos disponibles para registrar la venta
                        System.out.println("No se encontraron archivos de ventas donde registrar la venta.");
                        System.out.println();
                    }

                    break;
                case 4:
                    // Leer archivo de ventas y mostrar su contenido
                    System.out.println("Opcion 4: Leer archivo de ventas.");

                    // Se verifica si hay archivos disponibles para leer
                    if (!ventaController.isListaArchivosEmpty()) {
                        int seleccionArchivo = 0;

                        // Se muestra la lista de archivos disponibles para leer
                        System.out.println("Seleccione un archivo de ventas para leer su contenido:");
                        ventaController.listaArchivos();
                        System.out.println("Ingrese el numero del archivo:");
                        seleccionArchivo = leerOpcion();

                        if (seleccionArchivo < 1 || seleccionArchivo - 1 >= ArchivoVentasRepository.LISTA_ARCHIVOS.size()) {
                            System.out.println("Opcion no valida. Por favor, ingrese un numero del 1 al " + ArchivoVentasRepository.LISTA_ARCHIVOS.size() + ".");
                            System.out.println();
                            break;
                        } else {
                            // Se envía la selección al controlador para leer el archivo y mostrar su
                            // contenido
                            ventaController.leerArchivoVentas(seleccionArchivo);
                        }
                    } else {
                        // Mensaje si no hay archivos disponibles
                        System.out.println("No se encontraron archivos de ventas para leer.");
                        System.out.println();
                    }
                    break;
                case 5:
                    System.out.println("Opcion 5: Calcular las ventas.");

                    if (!ventaController.isListaArchivosEmpty()) {
                        int seleccionArchivo = 0;
                        System.out.println("Seleccione un archivo para calcular las ventas registradas:");
                        ventaController.listaArchivos();
                        System.out.println("Ingrese el numero del archivo:");
                        seleccionArchivo = leerOpcion();

                        if (seleccionArchivo < 1 || seleccionArchivo - 1 >= ArchivoVentasRepository.LISTA_ARCHIVOS.size()) {
                            System.out.println("Opcion no valida. Por favor, ingrese un numero del 1 al " + ArchivoVentasRepository.LISTA_ARCHIVOS.size() + ".");
                            System.out.println();
                            break;
                        } else {
                            ventaController.calcularVentas(seleccionArchivo);
                        }
                    } else {
                        System.out.println("No se encontraron archivos de ventas para calcular las ventas.");
                        System.out.println();
                    }
                    break;
                case 6:
                    // Opción para calcular el total de ventas de un archivo
                    System.out.println("Opcion 6: Respaldar archivo ventas.");

                    if (!ventaController.isListaArchivosEmpty()) {
                        int seleccionArchivo = 0;
                        System.out.println("Seleccione un archivo para respaldar:");
                        ventaController.listaArchivos();
                        System.out.println("Ingrese el numero del archivo:");
                        seleccionArchivo = leerOpcion();

                        if (seleccionArchivo < 1 || seleccionArchivo - 1 >= ArchivoVentasRepository.LISTA_ARCHIVOS.size()) {
                            System.out.println("Opcion no valida. Por favor, ingrese un numero del 1 al " + ArchivoVentasRepository.LISTA_ARCHIVOS.size() + ".");
                            System.out.println();
                            break;
                        } else {
                            // Se envía la selección al controlador para calcular el total de ventas del
                            // archivo seleccionado
                            ventaController.respaldarArchivoVentas(seleccionArchivo);
                        }
                    } else {
                        // Mensaje si no hay archivos disponibles para respaldar
                        System.out.println("No se encontraron archivos de ventas para respaldar.");
                        System.out.println();
                    }
                    break;
                case 7:
                    // Opción para eliminar un archivo de ventas
                    System.out.println("Opcion 7: Eliminar archivo de ventas.");

                    if (!ventaController.isListaArchivosEmpty()) {
                        int seleccionArchivo = 0;
                        System.out.println("Seleccione un archivo para eliminar:");
                        ventaController.listaArchivos();
                        System.out.println("Ingrese el numero del archivo:");
                        seleccionArchivo = leerOpcion();

                        if (seleccionArchivo < 1 || seleccionArchivo - 1 >= ArchivoVentasRepository.LISTA_ARCHIVOS.size()) {
                            System.out.println("Opcion no valida. Por favor, ingrese un numero del 1 al " + ArchivoVentasRepository.LISTA_ARCHIVOS.size() + ".");
                            System.out.println();
                            break;
                        } else {
                            // Se envía la selección al controlador para eliminar el archivo seleccionado
                            ventaController.eliminarArchivoVentas(seleccionArchivo);
                        }
                    } else {
                        System.out.println("No se encontraron archivos de ventas para eliminar.");
                        System.out.println();
                    }

                    break;
                case 8:
                    // Opción para salir del programa
                    System.out.println("Cerrando programa...");
                    break;
                default:
                    // Se ejecuta si el usuario ingresa una opción fuera del rango permitido (1-8)
                    System.out.println("Opcion no valida. Por favor, ingrese un numero del 1 al 8.");
                    System.out.println();
            }

            option = 0; // Reinicia la opción para evitar que se ejecute el menú sin una nueva selección
        } while (option != 8);
        SCANNER.close();
    }

    /**
     * Método para leer una opción numérica ingresada por el usuario.
     * 
     * return el número ingresado por el usuario o -1 si ocurre un error
     */
    public int leerOpcion() {
        try {

            // Se intenta leer un número entero ingresado por el usuario
            int option = SCANNER.nextInt();

            // Se limpia el buffer (salto de línea pendiente)
            SCANNER.nextLine();

            // Se retorna la opción ingresada
            return option;
        } catch (InputMismatchException e) {
            // Se ejecuta si el usuario no ingresa un número válido (ej: letras)
            System.out.println("ERROR: Entrada no valida. Por favor, ingrese un numero valido.");
            System.out.println();
            SCANNER.nextLine();

            // Se retorna -1 para indicar que hubo un error
            return -1;
        }
    }
}
