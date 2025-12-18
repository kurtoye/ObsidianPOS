package com.kurtoye.obsidianpos.controller;

import com.kurtoye.obsidianpos.domain.OrderStatus;
import com.kurtoye.obsidianpos.domain.PaymentType;
import com.kurtoye.obsidianpos.payload.dto.OrderDTO;
import com.kurtoye.obsidianpos.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) throws Exception{
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) throws Exception{
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByBranchId(@PathVariable Long branchId,
                                                            @RequestParam(required = false) Long customerId,
                                                            @RequestParam(required = false) Long cashierId,
                                                            @RequestParam(required = false) PaymentType paymentType,
                                                            @RequestParam(required = false) OrderStatus orderStatus) throws Exception{
        return ResponseEntity.ok(orderService.getOrdersByBranchId(branchId, customerId, cashierId, paymentType, orderStatus));
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCashierId(@PathVariable Long cashierId) throws Exception{
        return ResponseEntity.ok(orderService.getOrdersByCashierId(cashierId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId) throws Exception{
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @GetMapping("/today/branch/{branchId}")
    public ResponseEntity<List<OrderDTO>> getTodaysOrdersByBranch(@PathVariable Long branchId) throws Exception {
        return ResponseEntity.ok(orderService.getTodaysOrdersByBranch(branchId));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDTO>> getRecentOrdersByBranch(@PathVariable Long branchId) throws Exception{
        return ResponseEntity.ok(orderService.getTopFiveRecentOrdersByBranchId(branchId));
    }


}
