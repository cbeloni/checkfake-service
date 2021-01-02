package br.com.checkfakeservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.checkfakeservice.entity.Meme;

public interface MemeRepository extends JpaRepository<Meme,Long>, PagingAndSortingRepository<Meme, Long> {
  

}
