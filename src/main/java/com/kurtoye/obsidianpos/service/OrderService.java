package com.kurtoye.obsidianpos.service;

import com.kurtoye.obsidianpos.domain.OrderStatus;
import com.kurtoye.obsidianpos.domain.PaymentType;
import com.kurtoye.obsidianpos.payload.dto.OrderDTO;

import java.util.List;

public interface OrderService {

    OrderDTO createOrder(OrderDTO orderDTO) throws Exception;
    OrderDTO getOrderById(Long orderId) throws Exception;
    List<OrderDTO> getOrdersByBranchId(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) throws Exception;
    List<OrderDTO> getOrdersByCashierId(Long cashierId) throws Exception;
    List<OrderDTO> getOrdersByCustomerId(Long customerId) throws Exception;
    List<OrderDTO> getTodaysOrdersByBranch(Long branchId) throws Exception;
    List<OrderDTO> getTopFiveRecentOrdersByBranchId(Long branchId) throws Exception;


}
