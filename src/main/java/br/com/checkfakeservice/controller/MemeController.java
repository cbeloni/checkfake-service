package br.com.checkfakeservice.controller;

import br.com.checkfakeservice.dto.MemeDto;
import br.com.checkfakeservice.entity.Meme;
import br.com.checkfakeservice.exceptions.BadRequestMeme;
import br.com.checkfakeservice.service.MemeService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@RestController
@RequestMapping("/meme")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemeController {

    static Logger logger = Logger.getLogger(MemeController.class.getName());

    @Autowired
    private MemeService memeService;

    @GetMapping("obter")
    public Meme obter(Meme meme){
        return memeService.obterMeme(meme);
    }

    @GetMapping("listar")
    public List<Meme> obterTodos(){
        return memeService.obterTodos();
    }
    
    @GetMapping("listarPaginado")
    public Page<Meme> obterTodosPaginado(@RequestParam Integer pageNo, 
                                         @RequestParam Integer pageSize, 
                                         @RequestParam String sortBy){

        Page<Meme> meme = null;
        try{
            logger.info("Inicio listarPaginado - Params: pageNo: "+pageNo+" pageSize: "+ pageSize +", sortBy: "+sortBy+"");
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC,sortBy));
            meme =  memeService.obterTodos(pageable);
            return meme;
        } catch (Exception ex){
            logger.error("Error em listarPaginado - Params: pageNo: "+pageNo+" pageSize: "+pageSize+", sortBy: "+sortBy+" - error: "+ex.getMessage()+"" );
            throw new BadRequestMeme("Erro ao obter lista de memes");
        } finally {
            logger.info("Fim listarPaginado - Params: pageNo: "+pageNo+" pageSize: "+ pageSize +", sortBy: "+sortBy+"");
        }
    }

    @PostMapping("salvar")
    public Meme salvar(@RequestParam("file") MultipartFile file, MemeDto meme) {
        return memeService.salvar(file, meme);
    }

    @DeleteMapping("remover")
    public String remover(Meme meme) {
        memeService.remover(meme);
        return "Removido com sucesso!!";
    }

}
