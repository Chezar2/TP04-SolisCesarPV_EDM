package ar.edu.unju.edm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sun.el.stream.Optional;

import ar.edu.unju.edm.model.Cliente;

@Repository
public interface IclienteDAO extends CrudRepository<Cliente, Integer>{

		@Query("from Cliente c order by c.idCliente")
		public List<Cliente> obtenerClientes();
			
			
	}
