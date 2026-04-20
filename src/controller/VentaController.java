package controller;

import repository.ArchivoVentasRepository;

public class VentaController {
    public void crearArchivoVentas(String nombreArchivo) {
        ArchivoVentasRepository archivoVentasRepository = new ArchivoVentasRepository();
        
        archivoVentasRepository.crearArchivo(nombreArchivo);
    }

    public void abrirArchivos() {
        ArchivoVentasRepository archivoVentasRepository = new ArchivoVentasRepository();
        
        archivoVentasRepository.abrirArchivos();
    }

    public void registrarVenta(int seleccionArchivo, model.Venta venta) {
        ArchivoVentasRepository archivoVentasRepository = new ArchivoVentasRepository();
        
        archivoVentasRepository.registrarVenta(seleccionArchivo, venta);
    }

    public void leerArchivoVentas(int seleccionArchivo) {
        ArchivoVentasRepository archivoVentasRepository = new ArchivoVentasRepository();
        
        archivoVentasRepository.leerArchivo(seleccionArchivo);
    }

    public void calcularVentas(int seleccionArchivo) {
        ArchivoVentasRepository archivoVentasRepository = new ArchivoVentasRepository();
        
        archivoVentasRepository.calcularVentas(seleccionArchivo);
    }

    public void respaldarArchivoVentas(int seleccionArchivo) {
        ArchivoVentasRepository archivoVentasRepository = new ArchivoVentasRepository();
        
        archivoVentasRepository.respaldarArchivo(seleccionArchivo);
    }

    public void eliminarArchivoVentas(int seleccionArchivo) {
        ArchivoVentasRepository archivoVentasRepository = new ArchivoVentasRepository();
        
        archivoVentasRepository.eliminarArchivo(seleccionArchivo);
    }

    public void listaArchivos() {
        ArchivoVentasRepository archivoVentasRepository = new ArchivoVentasRepository();
        
        archivoVentasRepository.listaArchivos();
    }
    
    public boolean isListaArchivosEmpty() {
        return ArchivoVentasRepository.LISTA_ARCHIVOS != null;
    }
}
