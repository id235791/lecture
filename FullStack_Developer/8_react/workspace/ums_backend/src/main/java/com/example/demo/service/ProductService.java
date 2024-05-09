package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.ProductDTO;

public interface ProductService {
	boolean addProduct(ProductDTO product);
	
	boolean modifyProduct(ProductDTO product);
	boolean updateLikeCnt(long prodnum);
	
	boolean removeProduct(long prodnum);
	
	long getTotal(Criteria cri);
	List<ProductDTO> getList(Criteria cri);
	ProductDTO getDetail(long prodnum);
}
