package br.com.estudo.crud.service.impl;

import br.com.estudo.crud.controller.PessoaController;
import br.com.estudo.crud.model.PessoaModel;
import br.com.estudo.crud.model.dto.PessoaDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @InjectMocks
    private PessoaController controllerTest;

    @Mock
    private PessoaServiceImpl serviceTest;

    public PessoaDTO pessoaDTO = null;

    public PessoaModel pessoaModel = null;

    @BeforeEach
    public void init() {
        pessoaDTO = new PessoaDTO("Francisco", "Lucca Enrico da Rocha", LocalDate.parse("1961-01-12"), "15706403350", "266540764", "francisco_lucca_darocha@kinouchi.com.br");
        pessoaModel = pessoaDTO.toModel();
        pessoaModel.setId(1L);
    }

    @Test
    public void insertTest() {
        Mockito.when(serviceTest.insert(Mockito.any())).thenReturn(pessoaModel);
        ResponseEntity responseEntity = controllerTest.save(pessoaDTO);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK, "Teste insert OK");
        Assertions.assertEquals(responseEntity.getBody().getClass(), PessoaModel.class, "Teste insert OK");
    }

    @Test
    public void getAllTest() {
        List<PessoaModel> modelList = new ArrayList<>();
        modelList.add(pessoaModel);
        Mockito.when(serviceTest.list()).thenReturn(modelList);
        ResponseEntity responseEntity = controllerTest.getAll();
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK, "Teste get All OK");
        Assertions.assertNotNull(responseEntity.getBody(), "Teste get All OK");
        modelList.clear();
        responseEntity = controllerTest.getAll();
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK, "Teste get All OK");
        Assertions.assertNotNull(responseEntity.getBody(), "Teste get All OK");
    }

    @Test
    public void getByIdTest() {
        Mockito.when(serviceTest.consultation(Mockito.anyLong())).thenReturn(pessoaModel);
        ResponseEntity responseEntity = controllerTest.getById(1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK, "Teste get All OK");
        Assertions.assertEquals(responseEntity.getBody().getClass(), PessoaModel.class, "Teste get id OK");
        Mockito.when(serviceTest.consultation(Mockito.anyLong())).thenReturn(null);
        responseEntity = controllerTest.getById(1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND, "Teste get id OK");
    }

    @Test
    public void updateTest() {
        Mockito.when(serviceTest.edit(Mockito.any(PessoaDTO.class), Mockito.any())).thenReturn(pessoaModel);
        ResponseEntity responseEntity = controllerTest.update(pessoaDTO, 1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK, "Teste update OK");
        Assertions.assertEquals(responseEntity.getBody().getClass(), PessoaModel.class, "Teste update OK");
    }

    @Test
    public void deleteTest() {
        ResponseEntity responseEntity = controllerTest.delete(1L);
        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK, "Teste delete OK");
        Assertions.assertNull(responseEntity.getBody(), "Teste delete OK");
    }

    @Test
    public void exceptionsTest() {
        Mockito.doThrow(new Exception()).when(serviceTest);
        ResponseEntity responseEntity = controllerTest.save(pessoaDTO);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), "Teste exception insert OK");
        responseEntity = controllerTest.getAll();
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), "Teste exception lista OK");
        responseEntity = controllerTest.getById(1L);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), "Teste exception conulta OK");
        responseEntity = controllerTest.update(pessoaDTO, 1L);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), "Teste exception update OK");
        responseEntity = controllerTest.delete(1L);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode(), "Teste exception delete OK");
    }

}