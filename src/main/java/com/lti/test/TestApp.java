package com.lti.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lti.dao.GenericRepository;
import com.lti.dto.ShoppingDTO;
import com.lti.entity.Product;
import com.lti.service.ShoppingService;

public class TestApp {
	
	
	@Test
	public void placeOrder() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml"); 
		ShoppingService ss = (ShoppingService) context.getBean(ShoppingService.class);
		
		ShoppingDTO dto = new ShoppingDTO();
		dto.setPaymentMode("CARD");
		dto.setProductId(333);
		dto.setQuantity(10);
		ss.placeOrder(dto);
	}
	
	@Test
	public void addProduct() {
		
		Product p = new Product();
		p.setName("BagPack");
		p.setPrice(5000);
		p.setStock(100);
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml"); 
		GenericRepository ed = (GenericRepository) context.getBean(GenericRepository.class);
		ed.store(p);
	}

}
