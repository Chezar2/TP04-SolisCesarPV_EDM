package ar.edu.unju.edm.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.ProductoService;

@Controller
public class ProductoController {
	private static final Log LOGGER = LogFactory.getLog(ProductoController.class);
	
	@Autowired
	@Qualifier("impmysqlproducto")
	ProductoService iProductoService;
	
@GetMapping("/producto/mostrar")
public String cargarProducto(Model model) {
//model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
//model.addAttribute("productos",iProductoService.obtenerTodoProducto());
	model.addAttribute("unProducto",iProductoService.crearProducto());
	model.addAttribute("productos", iProductoService.obtenerTodoProducto());
	return("producto");
}
@GetMapping("/producto/editar/{codigo}")
public String editarProducto(Model model, @PathVariable(name="codigo") int codigo) throws Exception{
	try {
		//permite realizar una accion, y si ocurre error no se cae el program
		Producto productoEncontrado = iProductoService.encontrarUnProducto(codigo);
		model.addAttribute("unProducto", productoEncontrado);
		model.addAttribute("editMode", "true");
	}
	catch(Exception e)
	{//pasar excepcione a html
		model.addAttribute("formUsuarioErrorMessage", e.getMessage());
		model.addAttribute("unProducto", iProductoService.crearProducto());
		model.addAttribute("editMode", "false");
	}
	model.addAttribute("productos", iProductoService.obtenerTodoProducto());
	return("producto");
}
	
	@PostMapping("/producto/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
		iProductoService.guardarProducto(nuevoProducto);
		
		return("redirect:/producto/mostrar");
	}
	
	
	
	@PostMapping("/producto/modificar")
	public String modificarProducto(@ModelAttribute("unProducto") Producto productoModificado, Model model) {
		LOGGER.info("METHOD: ingresando el metodo modificar");
		try {
			iProductoService.modificarProducto(productoModificado);
			model.addAttribute("unProducto", new Producto());
			model.addAttribute("editMode", "false");
		}
		catch(Exception e)
		{
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unProducto", productoModificado);
			model.addAttribute("productos", iProductoService.obtenerTodoProducto());
			model.addAttribute("editMode","true");
		}
		model.addAttribute("productos", iProductoService.obtenerTodoProducto());
		return ("producto");
	}
/*
	@GetMapping("/ultimo")
	public String cargarUltimoProducto(Model model) {
		model.addAttribute("ultimoProducto", iProductoService.obtenerUltimoProducto());
		return("mostrar-ultimo");
	}
	@GetMapping("/volver")
	public String cargarNuevoProducto(Model model) {
		return("redirect:/producto");
	}
	*/

}

