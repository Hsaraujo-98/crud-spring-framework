package br.com.estudo.crud.model.dto;

import br.com.estudo.crud.model.PessoaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String cpf;
    private String rg;
    private String email;

    public PessoaModel toModel() {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(this, PessoaModel.class);
    }

}
