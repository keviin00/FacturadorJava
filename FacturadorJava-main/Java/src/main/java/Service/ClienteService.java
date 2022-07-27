package Service;

import Entity.Cliente;
import Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return this.clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    public Cliente findById(Integer id) {

        var opCliente =  this.clienteRepository.findById(id);
        if (opCliente.isPresent()) {
            return opCliente.get();
        } else {
            return new Cliente();
        }
    }
}

