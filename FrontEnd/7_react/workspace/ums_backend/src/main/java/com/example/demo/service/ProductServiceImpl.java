package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.ProductDTO;
import com.example.demo.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductMapper pmapper;

	@Override
	public boolean addProduct(ProductDTO product) {
		return pmapper.insert(product) == 1;
	}

	@Override
	public boolean modifyProduct(ProductDTO product) {
		return pmapper.update(product) == 1;
	}

	@Override
	public boolean updateLikeCnt(long prodnum) {
		return pmapper.updateLikeCnt(prodnum) == 1;
	}

	@Override
	public boolean removeProduct(long prodnum) {
		return pmapper.delete(prodnum) == 1;
	}
	
	@Override
	public long getTotal(Criteria cri) {
		String category = cri.getCategory();
		if(category == null) {
			cri.setCategory("none");
		}
		return pmapper.countProduct(cri);
	}

	@Override
	public List<ProductDTO> getList(Criteria cri) {
		int startrow = 0;
		startrow = (cri.getPagenum()-1)*cri.getAmount();
		String category = cri.getCategory();
		if(category == null) {
			cri.setCategory("none");
		}
		List<ProductDTO> list = pmapper.selectProducts(cri, startrow);
		return list;
	}

	@Override
	public ProductDTO getDetail(long prodnum) {
		return pmapper.selectByProdnum(prodnum);
	}
	
}
