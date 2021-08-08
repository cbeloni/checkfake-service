package br.com.checkfakeservice.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import br.com.checkfakeservice.dto.MemePaginadoDto;

@Component
public class MemeMapper {
		
	public MemePaginadoDto JsonToMemePaginadoDto(String jsonPaginado) {		
		return new Gson().fromJson(jsonPaginado, MemePaginadoDto.class);
	}
	

}
