package co.edu.poli.proyectotienda.modelo;

public class Usuario {
    private String nombre;
    private int nivelAcceso;

    public Usuario(String nombre, int nivelAcceso) {
        this.nombre = nombre;
        this.nivelAcceso = nivelAcceso;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public String getNombre() {
        return nombre;
    }
    
}
