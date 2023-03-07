package com.Trading.NewProject.AlgorithmicTradingApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.Trading.NewProject.AlgorithmicTradingApplication.Entity.Orders;
import com.Trading.NewProject.AlgorithmicTradingApplication.Service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderservice;

	@GetMapping("/Order_Entry")
	public ModelAndView orderEntry_table() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("Order_Entry");
		return mv;
	}

	@GetMapping("/Ordetable")
	public ModelAndView Ordertable() {
		List<Orders> orders = orderservice.fetch_Orders();

		ModelAndView mv = new ModelAndView();
		mv.addObject("orders", orders);
		mv.setViewName("OrderD");
		return mv;
	}

	@PostMapping("/orders/save")
	public String saveOrder(@ModelAttribute Orders order, @RequestParam("action") String action) {
		if (action.equals("modify")) {
			int priority_queue = orderservice.max_Priority();
			order.setPriority_queue(priority_queue);
			orderservice.save(order);
			return "Modified values saved in database" + order.getOrder_id();
		} else if (action.equals("delete")) {

			order.setOrder_status("Deleted");
			orderservice.save(order);
			return "deleted";

		}
		return "";
	}

	@GetMapping("/Orders/{order_id}")
	public ModelAndView getOrder(@PathVariable("order_id") Integer order_id) {

		Orders order = orderservice.findOrderById(order_id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("order", order);
		mv.setViewName("Order_Editpage");
		return mv;
	}

	@PostMapping("/create_Order")
	public String save_Order(@RequestParam("side") String side, @RequestParam("company_Id") String company_Id,
			@RequestParam("quantity") int quantity, @RequestParam("price") double price) {

		Integer priority_queue = orderservice.max_Priority();
		Orders order = new Orders();
		order.setCompany_Id(company_Id);
		order.setSide(side);
		order.setQuantity(quantity);
		order.setPrice(price);
		order.setPriority_queue(priority_queue);
		order.setOrder_status("pending");
		orderservice.create_Order(order);
		return "Order is created";
	}

}
