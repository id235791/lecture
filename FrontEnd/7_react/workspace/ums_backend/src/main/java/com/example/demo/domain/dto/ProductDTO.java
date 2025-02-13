package com.example.demo.domain.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private long prodnum;
	private String prodname;
	private int prodprice;
	private int prodamount;
	private String prodinfo;
	private String prodcategory;
	private int likecnt;
	private String regdate;
	private String userid;
}
