package br.com.estudo.crud.model;

import br.com.estudo.crud.model.dto.PessoaDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PESSOA")
@Log4j2
public class PessoaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private String cpf;
    private String rg;
    private String email;

    public PessoaDTO toDTO() {
        log.info("Iniciando conversão de Model, para DTO");
        ModelMapper mapper = new ModelMapper();
        log.info("Finalizando conversão de Model, para DTO");
        return mapper.map(this, PessoaDTO.class);
    }

}
