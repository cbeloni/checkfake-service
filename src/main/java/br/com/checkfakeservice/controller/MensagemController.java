package br.com.checkfakeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.checkfakeservice.dto.PageDto;
import br.com.checkfakeservice.entity.Mensagem;
import br.com.checkfakeservice.service.MensagemService;

@RestController
@RequestMapping("mensagem")
public class MensagemController {

	@Autowired
	private MensagemService mensagemService;
	
	@GetMapping("/listar")
	public Page<Mensagem> listar(@ModelAttribute PageDto pageDto){
		Pageable pageable = PageRequest.of(pageDto.getPageNo(), 
				pageDto.getPageSize(), 
				Sort.by(Sort.Direction.DESC,pageDto.getSortBy()));
		return mensagemService.listar(pageable);
	}
	
}
