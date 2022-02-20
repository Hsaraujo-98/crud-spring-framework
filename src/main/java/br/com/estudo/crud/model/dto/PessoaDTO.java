package br.com.estudo.crud.model.dto;

import br.com.estudo.crud.model.PessoaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class PessoaDTO {

    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String cpf;
    private String rg;
    private String email;

    public PessoaModel toModel() {
        log.info("Iniciando conversão de DTO, para Model");
        ModelMapper mapper = new ModelMapper();
        log.info("Finalizando conversão de DTO, para Model");
        return mapper.map(this, PessoaModel.class);
    }

}
