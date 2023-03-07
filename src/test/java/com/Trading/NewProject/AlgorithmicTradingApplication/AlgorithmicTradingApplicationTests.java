package com.Trading.NewProject.AlgorithmicTradingApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.Trading.NewProject.AlgorithmicTradingApplication.Entity.Orders;
import com.Trading.NewProject.AlgorithmicTradingApplication.Service.OrderService;


@SpringBootTest
class AlgorithmicTradingApplicationTests {

	@Mock
	private OrderService orderservice;

	@InjectMocks
	private Orders order;
	

	@BeforeEach
	public void setup() {
		order = new Orders();
		order.setOrder_id(100);
		order.setCompany_Id("ABC");
		order.setSide("buy");
		order.setQuantity(100);
		order.setPrice(10.0);
		order.setOrder_status("pending");
		order.setPriority_queue(3);

	}
	@Test
	public void testCreateOrder() {		
		orderservice.create_Order(order);
		assertEquals(order.getOrder_id(), 100);
		assertNotNull(order.getOrder_id());
		
		
		
}
	 @Test
	    public void testMaxPriority() {
	        Integer priority_queue = orderservice.max_Priority();
	        assertNotNull(priority_queue);
	    }
	 
	 
	
	@Test
	 public void testChangeOrderStatus() {
		 
		 order.setPrice(300);
	       order.setQuantity(200);
	       orderservice.create_Order(order);
	       assertEquals(Double.valueOf(300), Double.valueOf(order.getPrice()));
	       
	       
	    }

	
	
}
