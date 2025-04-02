package co.edu.poli.proyectotienda.modelo;

public class EnvioNacional extends Envio{

    public EnvioNacional(Mercancia mercancia) {
        super(mercancia);
    }

    @Override
    public String entregar() {
        return "📦 Envío Nacional de " + mercancia.getTipo();
    }
}
