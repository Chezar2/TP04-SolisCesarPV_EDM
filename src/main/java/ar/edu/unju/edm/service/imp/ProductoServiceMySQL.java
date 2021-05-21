package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.repository.IProductoDAO;
import ar.edu.unju.edm.service.ProductoService;

@Service
@Qualifier("impmysqlproducto")
public class ProductoServiceMySQL implements ProductoService{
    @Autowired
	Producto unProducto;
    @Autowired
    IProductoDAO productoDAO;
    
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		productoDAO.save(unProducto);
	}
	@Override
	public Producto crearProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}
	@Override
	public void modificarProducto(Producto productoModificado) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void eliminarProducto(Producto productoAEliminar) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Producto obtenerProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Producto> obtenerTodoProducto() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoDAO.findAll() ;
	}
	@Override
	public Producto obtenerProductoNuevo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Producto encontrarUnProducto(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Producto obtenerProductoCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return productoDAO.findById(codigo).orElseThrow();
	}
    
}
