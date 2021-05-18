package ar.edu.unju.edm.service;


import java.util.List;
import org.springframework.stereotype.Service;
import ar.edu.unju.edm.model.Producto;

@Service
public interface ProductoService {
//que se hace
	public void guardarProducto(Producto unProducto);
	public Producto crearProducto();
	public void modificarProducto(Producto productoModificado);
	public void eliminarProducto(Producto productoAEliminar);
	public Producto obtenerProducto(String nombreProducto);
	public List<Producto> obtenerTodoProducto();
	public Producto obtenerProductoNuevo();
	public Producto encontrarUnProducto(int codigo);
	public Producto obtenerUltimoProducto();

}