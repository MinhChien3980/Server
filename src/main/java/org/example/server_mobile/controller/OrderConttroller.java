package org.example.server_mobile.controller;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.example.server_mobile.service.OrderService;
import org.springframework.web.bind.annotation.*;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
public class OrderConttroller {
    OrderService orderService;
}
