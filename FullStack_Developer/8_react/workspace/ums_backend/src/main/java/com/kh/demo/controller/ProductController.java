package com.kh.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.demo.domain.dto.Criteria;
import com.kh.demo.domain.dto.PageDTO;
import com.kh.demo.domain.dto.ProductDTO;
import com.kh.demo.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/product/*")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("list/**")
	public HashMap<String, Object> list(HttpServletRequest req) throws UnsupportedEncodingException{
		Object temp = req.getSession().getAttribute("loginUser");
		String loginUser = "";
		if(temp != null) {
			loginUser = (String)temp;
		}
		Criteria cri = new Criteria();
		String pathVariable = req.getRequestURI().split("/product/list/")[1];
		int pagenum = Integer.parseInt(pathVariable.split("/")[0]);
		String category = URLDecoder.decode(pathVariable.split("/")[1],"UTF-8");
		cri.setPagenum(pagenum);
		cri.setCategory(category);
		
		PageDTO page = new PageDTO(service.getTotal(cri), cri);
		List<ProductDTO> list = service.getList(cri);
		
		HashMap<String, Object> datas = new HashMap<>();
		datas.put("page", page);
		datas.put("list", list);
		datas.put("loginUser", loginUser);
		System.out.println(datas);
		return datas;
	}
	
	@PostMapping("add")
	public ResponseEntity<String> add(@RequestBody ProductDTO product){
		if(service.add(product)) {
			return new ResponseEntity<String>("O",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("X",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("{prodnum}")
	public ResponseEntity<ProductDTO> get(@PathVariable("prodnum") int prodnum, HttpServletRequest req) {
		ProductDTO product = service.getDetail(prodnum);
		return new ResponseEntity<ProductDTO>(product,HttpStatus.OK);
	}
	
	@PutMapping("like/{prodnum}")
	public void like(@PathVariable("prodnum") int prodnum) {
		service.like(prodnum);
	}
	
	@PutMapping("{prodnum}")
	public void modify(@PathVariable("prodnum") int prodnum, @RequestBody ProductDTO product) {
		product.setProdnum(prodnum);
		service.modify(product);
	}
	
	@DeleteMapping("{prodnum}")
	public void delete(@PathVariable("prodnum") int prodnum) {
		service.remove(prodnum);
	}
	
	
}















