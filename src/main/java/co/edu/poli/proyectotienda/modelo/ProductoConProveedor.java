package co.edu.poli.proyectotienda.modelo;

public class ProductoConProveedor implements ProductoFlyweight{
    private String nombreProducto;
    private double precio;
    private Proveedor proveedor;

    public ProductoConProveedor(String nombreProducto, double precio, Proveedor proveedor) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.proveedor = proveedor;
    }

    @Override
    public String mostrarDetalles() {
        return "🛒 Producto: " + nombreProducto + ", Precio: $" + precio + ", Proveedor: " + proveedor.getNombre();
    }
}
