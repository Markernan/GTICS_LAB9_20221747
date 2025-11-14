package com.example.cliente_web.controller;

import com.example.cliente_web.model.entity.Product;

import com.example.cliente_web.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String paginaInicio(Model modelo) {
        List<Product> productos = productService.obtenerTodosProductos();

        modelo.addAttribute("productos", productos);
        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscarProducto(@RequestParam Integer id, Model modelo) {

        Product producto = productService.obtenerProductoPorId(id);
        List<Product> productos = productService.obtenerTodosProductos();


        modelo.addAttribute("productoBuscado", producto);

        modelo.addAttribute("productos", productos);

        modelo.addAttribute("idBuscado", id);

        return "inicio";
    }
}