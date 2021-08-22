package br.com.checkfakeservice.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.checkfakeservice.dto.MemePaginadoDto;
import br.com.checkfakeservice.entity.Meme;
import br.com.checkfakeservice.mapper.MemeMapper;
import br.com.checkfakeservice.repository.MemeRepository;
import br.com.checkfakeservice.util.FileUtils;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Slf4j
@ActiveProfiles(profiles = "test")
public class MemeControllerTest {
		
	@Autowired
	private MemeRepository memeRepository;
		
	@Autowired
	private MemeMapper memeMapper;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private Meme meme = Meme.builder().nome("nomeMeme").isFake(true).imagemNome("nomeImagem").build();
	
	private String JsonRetornoPaginadoEsperado;
	
	
	public MemeControllerTest() throws Exception {
		JsonRetornoPaginadoEsperado = FileUtils.readFileAsString("jsons/JsonMemeRetornoPaginado.json");
	}

	@BeforeEach 
	void setUp() {
		
	}
	
	@Test
	void salvaMeme() throws IOException {
		HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", this.getTestFile());


        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        String serverUrl = "/meme/salvar";
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
        log.info("Response code: " + response.getStatusCode());
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);               
	}
	
	@Test
	void getMeme() throws IOException {
		memeRepository.save(meme);
		
        String serverUrl = "/meme/listarPaginado?pageNo=0&pageSize=1&sortBy=id";
        ResponseEntity<MemePaginadoDto> response = restTemplate.getForEntity(serverUrl, MemePaginadoDto.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        
        MemePaginadoDto memePaginadoDtoRetornado = response.getBody();
        MemePaginadoDto memePaginadoDtoEsperado = memeMapper.JsonToMemePaginadoDto(this.JsonRetornoPaginadoEsperado);
        Assertions.assertThat(memePaginadoDtoRetornado).isEqualTo(memePaginadoDtoEsperado);
	}
	
	private Resource getTestFile() throws IOException {
        Path testFile = Files.createTempFile("test-file", ".txt");
        log.info("Creating and Uploading Test File: " + testFile);
        Files.write(testFile, "Hello World !!, This is a test file.".getBytes());
        return new FileSystemResource(testFile.toFile());
    }
	
	private String postFile(String filename, byte[] someByteArray, String serverUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition
                .builder("form-data")
                .name("file")
                .filename(filename)
                .build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());
        HttpEntity<byte[]> fileEntity = new HttpEntity<>(someByteArray, fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        
        ResponseEntity<String> response = restTemplate.exchange(
        		serverUrl,
                HttpMethod.POST,
                requestEntity,
                String.class);
        return response.getBody();

    }
}
