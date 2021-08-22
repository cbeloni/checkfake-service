package br.com.checkfakeservice.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = "test")
@Slf4j
public class MensagemControllerTest {
	
	private String jsonMensagemRetornoPaginadoSucesso;
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	void salvaMensagem_quandoRequisicaoValida() {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
       
        
		ResponseEntity<String> retorno = testRestTemplate.postForEntity("/mensagem/salvar", requestEntity, String.class);
		Assertions.assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.OK);				
	}
	
	@Test
	void getMensagem_quadoRequisicaoValida() {
		
		String serverUrl = "/mensagem/listar?pageNo=0&pageSize=1&sortBy=id";
		ResponseEntity<String> retorno = testRestTemplate.getForEntity(serverUrl, String.class);
		
		Assertions.assertThat(retorno.getStatusCode()).isEqualTo(HttpStatus.OK);
		//Assertions.assertThat(retorno.getBody()).isEqualTo(jsonMensagemRetornoPaginadoSucesso);
		
	}

}
