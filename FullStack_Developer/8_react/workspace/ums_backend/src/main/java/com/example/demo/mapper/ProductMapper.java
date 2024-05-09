package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.ProductDTO;

@Mapper
public interface ProductMapper {
	int insert(ProductDTO product);
	int update(ProductDTO product);
	int delete(long prodnum);
	ProductDTO selectByProdnum(long prodnum);
	long countProduct(Criteria cri);
	List<ProductDTO> selectProducts(@Param("cri") Criteria cri, @Param("startrow") int startrow);
	int updateLikeCnt(long prodnum);
	void deleteAll(String userid);
}
