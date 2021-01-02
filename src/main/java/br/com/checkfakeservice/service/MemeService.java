package br.com.checkfakeservice.service;

import br.com.checkfakeservice.dto.MemeDto;
import br.com.checkfakeservice.entity.Meme;
import br.com.checkfakeservice.exceptions.BadRequestMeme;
import br.com.checkfakeservice.repository.MemeRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class MemeService {

    @Autowired
    private MemeRepository memeRepository;

    public Meme salvar(MultipartFile file, MemeDto memeDto){
        try {
        	
            Meme meme = Meme.builder().nome(memeDto.getNome())
                    .imagem(file.getBytes())
                    .imagemNome(file.getOriginalFilename())
                    .isFake(true)
                    .date(new Date())
                    .build();

            return memeRepository.save(meme);
        } catch (Exception e){
            throw new BadRequestMeme("Falha carregar image: " + e.getMessage() + " tracer: " +  e.getStackTrace());
        }
    }

    public Meme obterMeme(Meme meme){
        return memeRepository.findById(meme.getId()).orElseThrow(() -> new BadRequestMeme("Não encontrado id mencionado"));
    }

    public List<Meme> obterTodos(){
        return memeRepository.findAll();
    }

    public Page<Meme> obterTodos(final Pageable pageable) {
        return memeRepository.findAll(pageable);
    }

    public void remover(Meme meme){
        memeRepository.delete(meme);
    }
}
