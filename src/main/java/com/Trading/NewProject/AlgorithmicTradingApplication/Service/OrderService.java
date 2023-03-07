package com.Trading.NewProject.AlgorithmicTradingApplication.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Trading.NewProject.AlgorithmicTradingApplication.DAO.OrderRepo;
import com.Trading.NewProject.AlgorithmicTradingApplication.Entity.Orders;

@Service
public class OrderService {
	@Autowired
	OrderRepo orderrepo;
	

	
	public void create_Order(Orders order) {
		orderrepo.save(order);
		
	}
	public Integer max_Priority() {
		Integer maxpriority_queue = orderrepo.findmaxpriority_queue();
		 
		if(maxpriority_queue ==  0)
		{
			maxpriority_queue = 1;
			
		}
		else {
			++maxpriority_queue;
		}
		
		
		
		return maxpriority_queue;
		
	}
	
	public void change_orderstatus(int order_id) {
		
		
		orderrepo.updateOrderStatusToDeleted(order_id);
	}
	
	public List<Orders>  fetch_Orders(){
		
		List<Orders> orders = orderrepo.findAllByOrderBycompanyIdpricesideAsc();
		
		
		return orders;
	}
	
	public Orders findOrderById(Integer order_id) {
	   Orders order = orderrepo.findById(order_id).orElse(null);
	    		return order;
	}
	public void save(Orders order) {
		orderrepo.save(order);
		
		
		
	}

}
