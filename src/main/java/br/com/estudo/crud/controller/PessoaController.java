package br.com.estudo.crud.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @PostMapping
    public String save() {
        return "Salvo com sucesso";
    }

    @GetMapping
    public String getAll() {
        return "Listando todos registros";
    }

    @GetMapping("/{pessoa_id}")
    public String getById(@PathVariable(value = "pessoa_id") int id) {
        return "Registro " + id;
    }

    @PutMapping("/{pessoa_id}")
    public String update(@PathVariable(value = "pessoa_id") int id) {
        return "Atualizado " + id;
    }

    @DeleteMapping("/{pessoa_id}")
    public String delete(@PathVariable(value = "pessoa_id") int id) {
        return "Removido " + id;
    }

}
