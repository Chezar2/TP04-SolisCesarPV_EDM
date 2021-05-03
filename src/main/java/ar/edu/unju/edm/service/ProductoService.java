package ar.edu.unju.edm.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Producto;

@Service
public interface ProductoService {
//que se hace
	public void guardarProducto(Producto unProducto);
	public void modificarProducto(Producto unProductoAModificar);
	public void eliminarProducto(Producto productoAEliminar);
	public Producto obtenerProducto(String nombreProducto);
	public ArrayList<Producto> obtenerTodoProducto();
	public Producto obtenerProductoNuevo();
	public Producto obtenerUltimoProducto();
}