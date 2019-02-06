package com.lti.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lti.dao.GenericRepository;
import com.lti.dto.ShoppingDTO;
import com.lti.entity.Order;
import com.lti.entity.Payment;
import com.lti.entity.Product;

@Service
public class ShoppingService {
	
	@Autowired
	GenericRepository genericRepository;
	
	@Transactional
	public void placeOrder(ShoppingDTO dto) {
		
		Product pro = genericRepository.fetchById(Product.class, dto.getProductId());
		
		double totalAmount = pro.getPrice()* dto.getQuantity();
		
		Order ob = new Order();
		ob.setDate(new Date());
		ob.setAmount(totalAmount);
		genericRepository.store(ob);
		
		
		Payment payment = new Payment();
		payment.setMode(dto.getPaymentMode());
		payment.setAmount(pro.getPrice()* dto.getQuantity());
		genericRepository.store(payment);
		
		pro.setStock(pro.getStock() -dto.getQuantity());
		genericRepository.store(pro);
	
	}

}
