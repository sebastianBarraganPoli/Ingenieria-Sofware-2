package co.edu.poli.proyectotienda.modelo;

public class GestionCliente {
    private String info = "Nombre: Ana Pérez, Dirección: Bogotá";

    public String mostrarInfo() {
        return "🧾 Info Cliente: " + info;
    }

    public String actualizarInfo(String nuevaInfo) {
        this.info = nuevaInfo;
        return "✅ Información actualizada: " + info;
    }
}
