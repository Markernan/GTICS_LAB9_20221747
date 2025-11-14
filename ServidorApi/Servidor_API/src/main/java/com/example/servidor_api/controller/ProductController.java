package com.example.servidor_api.controller;
import java.util.List;
import java.util.Optional;

import com.example.servidor_api.model.entity.Product;
import com.example.servidor_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository repositorioProducto;

    // GET: Lista todos los productos
    @GetMapping
    public List<Product> obtenerTodosProductos() {
        return repositorioProducto.findAll();
    }

    // GET: Obtiene producto segun su ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> obtenerProductoPorId(@PathVariable Integer id) {
        Optional<Product> producto = repositorioProducto.findById(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET: Elimina producto
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
        if (repositorioProducto.existsById(id)) {
            repositorioProducto.deleteById(id);
            return new ResponseEntity<>("Producto eliminado correctamente", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
        }
    }

    // POST: Crea nuevo producto
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Product producto) {
        try {
            if (producto.getId() != null && repositorioProducto.existsById(producto.getId())) {
                return new ResponseEntity<>("El id del producto ya existe", HttpStatus.BAD_REQUEST);
            }

            Product productoCreado = repositorioProducto.save(producto);
            return new ResponseEntity<>(productoCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("error al crear producto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT: Actualiza producto existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Integer id, @RequestBody Product producto) {
        try {
            if (!repositorioProducto.existsById(id)) {
                return new ResponseEntity<>("Producto no encontrado", HttpStatus.NOT_FOUND);
            }

            producto.setId(id);
            Product productoActualizado = repositorioProducto.save(producto);

            return new ResponseEntity<>(productoActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("error al actualizar producto: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}