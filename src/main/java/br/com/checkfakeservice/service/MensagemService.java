package br.com.checkfakeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.checkfakeservice.entity.Mensagem;
import br.com.checkfakeservice.repository.MensagemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class MensagemService {
	
	@Autowired
	private MensagemRepository mensagemRepository;
	
	public Page<Mensagem> listar(final Pageable pageable){
		return mensagemRepository.findAll(pageable);
		
	}

}
