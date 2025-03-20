package co.edu.poli.proyectotienda.modelo;
import java.util.ArrayList;
import java.util.List;

class Departamento implements Componente {
    private String nombre;
    private List<Componente> componentes = new ArrayList<>();

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public void agregar(Componente componente) {
        componentes.add(componente);
    }

    public void remover(Componente componente) {
        componentes.remove(componente);
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Departamento: " + nombre);
        for (Componente c : componentes) {
            c.mostrarInfo();
        }
    }
}