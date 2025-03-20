package co.edu.poli.proyectotienda.modelo;

public class Proveedor {
    private String nombre;
    private Certificacion certificacion;
    private Evaluacion evaluacion;
    private PoliticaEntrega politicaEntrega;

    // Constructor privado para forzar el uso del Builder
    private Proveedor(ProveedorBuilder builder) {
        this.nombre = builder.nombre;
        this.certificacion = builder.certificacion;
        this.evaluacion = builder.evaluacion;
        this.politicaEntrega = builder.politicaEntrega;
    }

    // Clase interna estática Builder
    public static class ProveedorBuilder {
        private String nombre;
        private Certificacion certificacion;
        private Evaluacion evaluacion;
        private PoliticaEntrega politicaEntrega;

        public ProveedorBuilder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public ProveedorBuilder setCertificacion(Certificacion certificacion) {
            this.certificacion = certificacion;
            return this;
        }

        public ProveedorBuilder setEvaluacion(Evaluacion evaluacion) {
            this.evaluacion = evaluacion;
            return this;
        }

        public ProveedorBuilder setPoliticaEntrega(PoliticaEntrega politicaEntrega) {
            this.politicaEntrega = politicaEntrega;
            return this;
        }

        public Proveedor build() {
            return new Proveedor(this);
        }
    }
}

