package co.edu.poli.proyectotienda.modelo;

public class EnvioInternacional extends Envio{
    
    public EnvioInternacional(Mercancia mercancia) {
        super(mercancia);
    }

    @Override
    public String entregar() {
        return "✈️ Envío Internacional de " + mercancia.getTipo();
    }
}
