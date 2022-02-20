package br.com.estudo.crud.controller;

import br.com.estudo.crud.model.PessoaModel;
import br.com.estudo.crud.model.dto.PessoaDTO;
import br.com.estudo.crud.service.impl.PessoaServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoas")
@Log4j2
public class PessoaController {

    @Autowired
    private PessoaServiceImpl pessoaService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PessoaDTO dto) {
        log.info(":::Request recebida: {}", dto.toString());
        try {
            PessoaModel model = pessoaService.insert(dto);
            log.info("Fim do processamento da requisição com SUCESSO: {}", model.toString());
            return ResponseEntity.status(HttpStatus.OK).body(model);
        } catch (Exception e) {
            log.error("Erro ao salvar registro: ", e.getMessage());
            log.info(":::Fim do processamento da requisição com ERRO");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar registro: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        try {
            List<PessoaModel> list = pessoaService.list();
            if (!list.isEmpty() && list.size() > 0) {
                log.info("Fim do processamento da requisição com SUCESSO: {}", list.toString());
                return ResponseEntity.status(HttpStatus.OK).body(list);
            } else {
                log.info("Fim do processamento da requisição com SUCESSO, não há registros na base", list.toString());
                return ResponseEntity.status(HttpStatus.OK).body("Não há registros na base");
            }
        } catch (Exception e) {
            log.error("Erro ao listar registros: ", e.getMessage());
            log.info(":::Fim do processamento da requisição com ERRO");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar registros: " + e.getMessage());
        }
    }

    @GetMapping("/{pessoa_id}")
    public ResponseEntity<?> getById(@PathVariable(value = "pessoa_id") Long id) {
        log.info(":::Request recebida: ID {}", id);
        try {
            PessoaModel model = pessoaService.consultation(id);
            if (model != null) {
                log.info(":::Fim do processamento da requisição com SUCESSO: {}", model.toString());
                return ResponseEntity.status(HttpStatus.OK).body(model);
            } else {
                log.info(":::Fim do processamento da requisição com SUCESSO, registro não encontrado na base");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registro não encontrado na base");
            }
        } catch (Exception e) {
            log.error("Erro ao consultar registro: ", e.getMessage());
            log.info(":::Fim do processamento da requisição com ERRO");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao consultar registro: " + e.getMessage());
        }
    }

    @PutMapping("/{pessoa_id}")
    public ResponseEntity<?> update(@RequestBody PessoaDTO dto, @PathVariable(value = "pessoa_id") Long id) {
        log.info(":::Request recebida: ID {} + {}", id, dto.toString());
        try {
            PessoaModel model = pessoaService.edit(dto, id);
            log.info(":::Fim do processamento da requisição com SUCESSO: {}", model.toString());
            return ResponseEntity.status(HttpStatus.OK).body(model);
        } catch (Exception e) {
            log.error("Erro ao atualizar registro: ", e.getMessage());
            log.info(":::Fim do processamento da requisição com ERRO");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar registro: " + e.getMessage());
        }
    }

    @DeleteMapping("/{pessoa_id}")
    public ResponseEntity<?> delete(@PathVariable(value = "pessoa_id") Long id) {
        log.info(":::Request recebida: ID {}", id);
        try {
            pessoaService.delete(id);
            log.info(":::Fim do processamento da requisição com SUCESSO");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            log.error("Erro ao remover registro: ", e.getMessage());
            log.info(":::Fim do processamento da requisição com ERRO");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao remover registro: " + e.getMessage());
        }
    }

}
