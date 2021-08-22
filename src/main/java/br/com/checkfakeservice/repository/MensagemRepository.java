package br.com.checkfakeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.checkfakeservice.entity.Mensagem;

public interface MensagemRepository extends JpaRepository<Mensagem, Long>, PagingAndSortingRepository<Mensagem, Long> {

}
