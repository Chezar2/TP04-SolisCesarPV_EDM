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
import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.service.IClienteService;

@Controller
public class ClienteController {
	private static final Log LOGGER = LogFactory.getLog(ClienteController.class);
	
	@Autowired
	@Qualifier("impmysql")
	IClienteService clienteService;
	
	/*@Autowired
	@Qualifier("OtraImp")
	IClienteService otroCslienteService;
	*/

	
	@GetMapping("/cliente/mostrar")
	public String cargarCliente(Model model) {
		model.addAttribute("unCliente", clienteService.crearCliente());
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}
	
	
	@GetMapping("/cliente/editar/{nroDocumento}")
	public String editarCliente(Model model, @PathVariable(name="nroDocumento") int dni) throws Exception{
		try {
			//permite realizar una accion, y si ocurre error no se cae el program
			Cliente clienteEncontrado = clienteService.encontrarUnCliente(dni);
			model.addAttribute("unCliente", clienteEncontrado);
			model.addAttribute("editMode", "true");
		}
		catch(Exception e)
		{//pasar excepcione a html
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unCliente", clienteService.crearCliente());
			model.addAttribute("editMode", "false");
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return("cliente");
	}

	@PostMapping("/cliente/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unCliente") Cliente nuevoCliente, Model model) {
		LOGGER.info("METHOD: ingresando el metodo Guardar");
		clienteService.guardarCliente(nuevoCliente);		
		LOGGER.info("Tamaño del Listado: "+ clienteService.obtenerTodosClientes().size());
		return "redirect:/cliente/mostrar";
	}
	
	@PostMapping("/cliente/modificar")
	public String modificarCliente(@ModelAttribute("unCliente") Cliente clienteModificado, Model model) {
		LOGGER.info("METHOD: ingresando el metodo modificar");
		try {
			clienteService.modificarCliente(clienteModificado);
			model.addAttribute("unCliente", new Cliente());
			model.addAttribute("editMode", "false");
		}
		catch(Exception e)
		{
			model.addAttribute("formUsuarioErrorMessage", e.getMessage());
			model.addAttribute("unCliente", clienteModificado);
			model.addAttribute("clientes", clienteService.obtenerTodosClientes());
			model.addAttribute("editMode","true");
		}
		model.addAttribute("clientes", clienteService.obtenerTodosClientes());
		return ("cliente");
	}
	
	//modal peticiones
	@GetMapping("/cliente/eliminarCliente/{id}")
	public String eliminarCliente(Model model, @PathVariable(name="nroDocumento") int dni) {		
		try {			clienteService.eliminarCliente(dni);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/cliente/mostrar";
	}
	
	
	
}
