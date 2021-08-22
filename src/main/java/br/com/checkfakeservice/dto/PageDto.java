package br.com.checkfakeservice.dto;

import lombok.Data;

@Data
public class PageDto {
	
	private Integer pageNo;
	private Integer pageSize;
	private String sortBy;

}
