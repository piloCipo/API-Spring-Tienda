/*

package edu.tienda.core.controllers;

import edu.tienda.core.domain.Cliente;
import edu.tienda.core.exceptions.BadRequestException;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    private List<Cliente> clientes = new ArrayList<>(Arrays.asList(
            new Cliente("pilo", "1234", "rodrigo"),
            new Cliente("juli", "2345", "julieta"),
            new Cliente("san", "3456", "santiago")
    ));

    @GetMapping
    public ResponseEntity<?> getClientes(){
        return ResponseEntity.ok(clientes);
    }

     Endpoint para obtener un cliente por su nombre de usuario (username).
     El parámetro debe tener exactamente 3 caracteres, de lo contrario lanza una excepción BadRequest.
     Si se encuentra un cliente con ese username (ignorando mayúsculas/minúsculas), lo retorna.
     Si no se encuentra, lanza una excepción ResourceNotFound.
     Endpoint to retrieve a client by their username.
     The username must be exactly 3 characters long, otherwise a BadRequestException is thrown.
     If a client with that username is found (case-insensitive), it is returned.
     If no client is found, a ResourceNotFoundException is thrown.

    @GetMapping("/{userName}")
    public ResponseEntity<?> getCliente(@PathVariable String userName){
        if (userName.length() !=3) {
            throw new BadRequestException("el parametro de usuario debe contener 3 caracteres");
        }
        return  clientes.stream().
                filter(cliente -> cliente.getUsername().equalsIgnoreCase(userName)).
                findFirst().map(ResponseEntity::ok).
                orElseThrow(()-> new ResourceNotFoundException("cliente " + userName + " no encontrado"));
    }

    @PostMapping
    public ResponseEntity<?> altaCliente(@RequestBody Cliente cliente){
        clientes.add(cliente);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{UserName}")
                .buildAndExpand(cliente.getUsername()).toUri();
        return ResponseEntity.created(location).body(cliente);
    }

    @PutMapping
    public ResponseEntity<?> modificacionCliente(@RequestBody Cliente cliente){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(cliente.getUsername())).
                findFirst().orElseThrow();
        clienteEncontrado.setPassword(cliente.getPassword());
        clienteEncontrado.setNombre(cliente.getNombre());
        return ResponseEntity.ok(clienteEncontrado);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<?> deleteCliente(@PathVariable String userName){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();
        clientes.remove(clienteEncontrado);
        return ResponseEntity.noContent().build();
    }
}


 */
