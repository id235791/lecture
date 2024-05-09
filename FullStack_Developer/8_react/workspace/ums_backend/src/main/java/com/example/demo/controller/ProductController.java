package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.Criteria;
import com.example.demo.domain.dto.PageDTO;
import com.example.demo.domain.dto.ProductDTO;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/prod/*")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping(value="list/**")
	public String list(HttpServletRequest req) throws UnsupportedEncodingException {
		Criteria cri = new Criteria();
		String pathVariable = req.getRequestURI().split("/prod/list/")[1];
		int pagenum = Integer.parseInt(pathVariable.split("/")[0]);
		int idx = pathVariable.indexOf("/");
		String category = URLDecoder.decode(pathVariable.substring(idx+1),"UTF-8");
		cri.setPagenum(pagenum);
		cri.setCategory(category);
		String loginUser = (String)req.getSession().getAttribute("loginUser");
		ObjectNode json = JsonNodeFactory.instance.objectNode();
		json.put("loginUser", loginUser);
		
		PageDTO page = new PageDTO(service.getTotal(cri), cri);
		List<ProductDTO> list = service.getList(cri);
		
		json.putPOJO("page",page);
		json.putPOJO("list",list);
		
		System.out.println(json.toString());
		return json.toString();
	}
	@GetMapping(value="add")
	public String add(HttpServletRequest req) {
		String loginUser = (String)req.getSession().getAttribute("loginUser");
		ObjectNode json = JsonNodeFactory.instance.objectNode();
		json.put("loginUser", loginUser);
		
		return json.toString();
	}
	@PostMapping(value="add")
	public ResponseEntity<String> add(@RequestBody ProductDTO product) {
		if(service.addProduct(product)){
			return new ResponseEntity<String>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	@GetMapping(value="{prodnum}")
	public String get(@PathVariable("prodnum")long prodnum, HttpServletRequest req){
		String loginUser = (String)req.getSession().getAttribute("loginUser");
		ObjectNode json = JsonNodeFactory.instance.objectNode();
		json.put("loginUser", loginUser);
		
		ProductDTO product = service.getDetail(prodnum);
		json.putPOJO("product", product);
		
		return json.toString();
	}
	@PatchMapping("like/{prodnum}")
	public void like(@PathVariable("prodnum")long prodnum) {
		service.updateLikeCnt(prodnum);
	}
	@PutMapping("{prodnum}")
	public void modify(@RequestBody ProductDTO product,@PathVariable("prodnum")long prodnum) {
		System.out.println(product);
		service.modifyProduct(product);
	}
	@DeleteMapping("{prodnum}")
	public void delete(@PathVariable("prodnum")long prodnum) {
		service.removeProduct(prodnum);
	}
	
}









