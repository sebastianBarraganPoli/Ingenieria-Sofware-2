package co.edu.poli.proyectotienda.modelo;

class Empleado implements Componente {
    private String nombre;

    public Empleado(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Empleado: " + nombre);
    }
}
