/**
 * Importa la clase MenuVentas del paquete view.
 * Permite usarla directamente en este archivo.
 */
import view.MenuVentas;

/**
 * Clase principal del programa.
 * Aquí inicia la ejecución.
 */
public class Main {

    /**
     * Método principal.
     * Es el punto de entrada del programa.
     */
    public static void main(String[] args) {

        // Se crea un objeto de tipo MenuVentas (menú del sistema)
        MenuVentas menu = new MenuVentas();

        // Se llama al método que muestra el menú y controla el programa
        menu.showMenu();
    }
}
