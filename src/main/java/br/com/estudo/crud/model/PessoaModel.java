package br.com.estudo.crud.model;

import br.com.estudo.crud.model.dto.PessoaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PESSOA")
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private Date dataNascimento;
    private String cpf;
    private String rg;
    private String email;

    public PessoaDTO toDTO() {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(this, PessoaDTO.class);
    }

}
