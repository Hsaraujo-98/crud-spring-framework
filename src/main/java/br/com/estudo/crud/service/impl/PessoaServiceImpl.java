package br.com.estudo.crud.service.impl;

import br.com.estudo.crud.model.PessoaModel;
import br.com.estudo.crud.model.dto.PessoaDTO;
import br.com.estudo.crud.repository.PessoaRepository;
import br.com.estudo.crud.service.PessoaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Log4j2
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public PessoaModel insert(PessoaDTO dto) {
        log.info("Iniciando inserção do registro");
        PessoaModel model = null;
        try {
            model = pessoaRepository.save(dto.toModel());
            log.info("Sucesso na atualização do registro: {}", model.toString());
        } catch (Exception e) {
            log.error("Falha ao inserir registro: {}", e.getMessage());
            throw e;
        }
        return model;
    }

    @Override
    public List<PessoaModel> list() {
        log.info("Iniciando a listagem dos registros");
        List<PessoaModel> listModel = null;
        try {
            listModel = pessoaRepository.findAll();
            log.info("Sucesso na listagem dos registros: {}", listModel.toString());
        } catch (Exception e) {
            log.error("Falha ao listar registros: {}", e.getMessage());
            throw e;
        }
        return listModel;
    }

    @Override
    public PessoaModel consultation(Long id) {
        log.info("Iniciando consulta de registro, ID: {}", id);
        PessoaModel model = null;
        try {
            model = pessoaRepository.findById(id).get();
            log.info("Sucesso ao conusltar registro");
        } catch (NoSuchElementException e) {
            log.info("Registro não encontrado");
        } catch (Exception e) {
            log.error("Falha ao consultar registro: {}", e.getMessage());
            throw e;
        }
        return model;
    }

    @Override
    public PessoaModel edit(PessoaDTO dto, Long id) {
        log.info("Iniciando atualização do registro, ID: ", id);
        PessoaModel model = null;
        try {
            model = pessoaRepository.save(dto.toModel());
            log.info("Sucesso na atualização do registro");
        } catch (Exception e) {
            log.error("Falha ao atualizar registro: {}", e.getMessage());
            throw e;
        }
        return model;
    }

    @Override
    public void delete(Long id) {
        log.info("Iniciando remoção do registro, ID: {}", id);
        try {
            pessoaRepository.deleteById(id);
            log.info("Sucesso na remoção do registro");
        } catch (Exception e) {
            log.error("Falha ao remover registro: {}", e.getMessage());
            throw e;
        }
    }
}
