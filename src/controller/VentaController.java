package controller;

import model.Venta;

/**
 * Importa la clase encargada de manejar los archivos de ventas.
 * Esta clase pertenece a la capa de acceso a datos (repository).
 */
import repository.ArchivoVentasRepository;

/**
 * Clase controlador del sistema de ventas.
 * 
 * Responsabilidad:
 * - Recibir solicitudes desde la vista (MenuVentas)
 * - Delegar las operaciones al repository
 * 
 * Nota:
 * - Esta clase no contiene lógica compleja
 * - Funciona como intermediario entre la vista y los datos
 */
public class VentaController {

    /**
     * Instancia única del repository.
     * 
     * - static: se comparte entre todas las instancias del controller
     * - final: no puede ser reasignada
     * 
     * Se usa para ejecutar todas las operaciones relacionadas con archivos.
     */
    static final ArchivoVentasRepository archivoVentasRepository = new ArchivoVentasRepository();

    /**
     * Crea un archivo de ventas.
     * 
     * Flujo:
     * - Recibe el nombre desde la vista
     * - Llama al repository para crear el archivo físico
     * 
     * @param nombreArchivo nombre del archivo sin extensión
     */
    public void crearArchivoVentas(String nombreArchivo) {
        archivoVentasRepository.crearArchivo(nombreArchivo);
    }

    /**
     * Muestra la lista de archivos disponibles.
     * 
     * Flujo:
     * - Llama al repository para listar los archivos
     * 
     * Nota:
     * - El método se llama dos veces, lo que provoca que la lista se imprima
     * duplicada.
     */
    public void abrirArchivos() {
        archivoVentasRepository.abrirArchivos();
    }

    /**
     * Registra una venta en un archivo específico.
     * 
     * Flujo:
     * - Recibe el índice del archivo seleccionado
     * - Recibe el objeto Venta con los datos
     * - Envía ambos al repository para guardarlos
     * 
     * @param seleccionArchivo posición del archivo en la lista
     * @param venta            objeto con producto, cantidad y precio
     */
    public void registrarVenta(int seleccionArchivo, Venta venta) {
        archivoVentasRepository.registrarVenta(seleccionArchivo, venta);
    }

    /**
     * Lee el contenido de un archivo de ventas.
     * 
     * Flujo:
     * - Recibe el índice del archivo
     * - Llama al repository para mostrar su contenido
     * 
     * @param seleccionArchivo posición del archivo
     */
    public void leerArchivoVentas(int seleccionArchivo) {
        archivoVentasRepository.leerArchivo(seleccionArchivo);
    }

    /**
     * Calcula el total de ventas de un archivo.
     * 
     * Flujo:
     * - Recibe el índice del archivo
     * - Llama al repository para procesar los datos
     * 
     * Nota:
     * - El método se llama dos veces, lo que provoca que el cálculo se ejecute
     * duplicado.
     * 
     * @param seleccionArchivo posición del archivo
     */
    public void calcularVentas(int seleccionArchivo) {
        archivoVentasRepository.calcularVentas(seleccionArchivo);
    }

    /**
     * Crea un respaldo de un archivo de ventas.
     * 
     * Flujo:
     * - Recibe el índice del archivo
     * - Llama al repository para copiar el archivo
     * 
     * @param seleccionArchivo posición del archivo
     */
    public void respaldarArchivoVentas(int seleccionArchivo) {
        archivoVentasRepository.respaldarArchivo(seleccionArchivo);
    }

    /**
     * Elimina un archivo de ventas.
     * 
     * Flujo:
     * - Recibe el índice del archivo
     * - Llama al repository para eliminarlo
     * 
     * @param seleccionArchivo posición del archivo
     */
    public void eliminarArchivoVentas(int seleccionArchivo) {
        archivoVentasRepository.eliminarArchivo(seleccionArchivo);
    }

    /**
     * Muestra la lista de archivos disponibles.
     * 
     * Flujo:
     * - Llama al repository para imprimir los archivos
     * 
     * Nota:
     * - El método se ejecuta dos veces, lo que duplica la salida en consola.
     */
    public void listaArchivos() {
        archivoVentasRepository.listaArchivos();
    }

    /**
     * Verifica si la lista de archivos existe.
     * 
     * @return true si la lista no es null
     * 
     * Nota:
     * - Este método NO valida si la lista está vacía
     * - Solo verifica que exista en memoria
     */
    public boolean isListaArchivosEmpty() {
        return ArchivoVentasRepository.LISTA_ARCHIVOS.isEmpty();
    }
}
