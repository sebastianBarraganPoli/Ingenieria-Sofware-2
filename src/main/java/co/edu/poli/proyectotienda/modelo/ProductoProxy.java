package co.edu.poli.proyectotienda.modelo;

public class ProductoProxy implements Producto {
    private ProductoReal productoReal;
    private Usuario usuario;

    public ProductoProxy(Usuario usuario, String nombre, double precio) {
        this.usuario = usuario;
        this.productoReal = new ProductoReal(nombre, precio);
    }

    @Override
    public String mostrarDetalles() {
        if (usuario.getNivelAcceso() >= 2) {
            return productoReal.mostrarDetalles();
        } else {
            return "❌ Acceso denegado. No tiene permisos para ver los detalles del producto.";
        }
    }
}
