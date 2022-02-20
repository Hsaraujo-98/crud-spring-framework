package br.com.estudo.crud.service;

import br.com.estudo.crud.model.PessoaModel;
import br.com.estudo.crud.model.dto.PessoaDTO;

import java.util.List;

public interface PessoaService {

    public PessoaModel insert(PessoaDTO dto);

    public List<PessoaModel> list();

    public PessoaModel consultation(Long id);

    public PessoaModel edit(PessoaDTO dto, Long id);

    public void delete(Long id);

}
