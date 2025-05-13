package co.edu.poli.proyectotienda.chainofresponsibility;

import co.edu.poli.proyectotienda.modelo.Pedido;

public class CalculadorImpuestos extends ProcesadorBase {
    private static final double TASA_IMPUESTO_ESTANDAR = 0.16;
    
    @Override
    public boolean procesarPedido(Pedido pedido) {
        System.out.println("Calculando impuestos para pedido #" + pedido.getId());
        
        // Obtener la región fiscal del cliente (simplificado)
        String region = obtenerRegionFiscal(pedido.getCliente().getDireccion());
        
        // Calcular impuestos según la región
        double subtotal = pedido.calcularSubtotal();
        double tasaImpuesto = obtenerTasaImpuesto(region);
        double impuestos = subtotal * tasaImpuesto;
        
        // Añadir impuestos al pedido
        pedido.setImpuestos(impuestos);
        
        System.out.println("Impuestos calculados: $" + impuestos);
        return procesarSiguiente(pedido);
    }
    
    private String obtenerRegionFiscal(String direccion) {
        // Lógica simplificada para determinar región fiscal
        return "REGION_ESTANDAR";
    }
    
    private double obtenerTasaImpuesto(String region) {
        // Lógica simplificada para obtener tasa de impuesto según región
        switch (region) {
            case "REGION_ESPECIAL":
                return 0.05;
            case "REGION_REDUCIDA":
                return 0.10;
            default:
                return TASA_IMPUESTO_ESTANDAR;
        }
    }
}
