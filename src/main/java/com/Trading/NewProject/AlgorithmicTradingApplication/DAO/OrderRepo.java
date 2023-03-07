package com.Trading.NewProject.AlgorithmicTradingApplication.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Trading.NewProject.AlgorithmicTradingApplication.Entity.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

	@Query(value = "SELECT max(priority_queue) FROM Orders")
	Integer findmaxpriority_queue();

	@Modifying
	@Query("UPDATE Orders o SET o.order_status= 'deleted' WHERE o.order_id = :order_id")
	void updateOrderStatusToDeleted(@Param("order_id") int order_id);

	@Query("SELECT o FROM Orders o WHERE o.order_status <> 'Deleted' ORDER BY o.company_Id,o.price,o.side")
	List<Orders> findAllByOrderBycompanyIdpricesideAsc();

}