package co.edu.poli.proyectotienda.modelo;

public class Empleado implements Componente {
    private String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String mostrarInfo() {
        return "👨‍💼 Empleado: " + nombre;
    }
}