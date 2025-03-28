package co.edu.poli.proyectotienda.modelo;
import java.util.ArrayList;
import java.util.List;

public class Departamento implements Componente {
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
    public String mostrarInfo() {
        StringBuilder info = new StringBuilder("🏢 Departamento: " + nombre + "\n");
        for (Componente c : componentes) {
            info.append("   ➜ ").append(c.mostrarInfo()).append("\n");
        }
        return info.toString();
    }
}