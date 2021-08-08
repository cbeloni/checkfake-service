package br.com.checkfakeservice.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemePaginadoDto {
	
	List<MemeDto> content;
	int number;
	int size;
	int totalElements;	
		
}
