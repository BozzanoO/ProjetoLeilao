package com.example.controller;

import com.example.entity.Cliente;
import com.example.service.ClienteService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<Cliente> salvar(@Body Cliente cliente) {
        Cliente novoCliente = clienteService.salvar(cliente);
        return HttpResponse.created(novoCliente);
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @Get("/{id}")
    public HttpResponse<Cliente> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.map(HttpResponse::ok).orElseGet(() -> HttpResponse.notFound());
    }


    @Post("/{id}")
    public HttpResponse<Cliente> atualizar(Long id, @Body Cliente clienteAtualizado) {
        try {
            Cliente atualizado = clienteService.atualizar(id, clienteAtualizado);
            return HttpResponse.ok(atualizado);
        } catch (IllegalArgumentException e) {
            return HttpResponse.notFound();
        }
    }
    

    @Delete("/{id}")
    public HttpResponse<Void> deletar(@PathVariable Long id) {
        try {
            clienteService.deletar(id);
            return HttpResponse.noContent();
        } catch (IllegalArgumentException e) {
            return HttpResponse.notFound();
        }
    }
}
