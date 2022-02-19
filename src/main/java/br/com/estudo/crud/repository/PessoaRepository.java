package br.com.estudo.crud.repository;

import br.com.estudo.crud.model.PessoaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

    PessoaModel findByCpf(String cpf);

    PessoaModel findByEmail(String email);

}
