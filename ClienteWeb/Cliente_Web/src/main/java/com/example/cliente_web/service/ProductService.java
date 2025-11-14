package com.example.cliente_web.service;

import com.example.cliente_web.model.entity.Product;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.stereotype.Service;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {

    @Value("${app.api.url}")
    private String urlApi;

    @Value("${app.api.username}")
    private String usuario;

    @Value("${app.api.password}")

    private String contrasenha;

    public List<Product> obtenerTodosProductos() {
        try {
            RestTemplate plantillaRest = new RestTemplate();

            HttpEntity<String> entidad = new HttpEntity<>(crearCabeceras());

            ResponseEntity<Product[]> respuesta = plantillaRest.exchange(
                    urlApi,
                    HttpMethod.GET,
                    entidad,
                    Product[].class
            );
            return Arrays.asList(respuesta.getBody());
        } catch (Exception excepcion) {
            System.out.println("Error al obtener productos: " + excepcion.getMessage());

            return List.of();
        }
    }

    public Product obtenerProductoPorId(Integer id) {
        try {
            RestTemplate plantillaRest = new RestTemplate();
            HttpEntity<String> entidad = new HttpEntity<>(crearCabeceras());

            // espera un producto de forma individual
            ResponseEntity<Product> respuesta = plantillaRest.exchange(
                    urlApi + "/" + id,

                    HttpMethod.GET,

                    entidad,
                    Product.class
            );
            return respuesta.getBody();
        } catch (HttpClientErrorException.NotFound excepcion) {
            System.out.println("Producto con ID " + id + " no encontrado");

            return null;
        } catch (Exception excepcion) {

            System.out.println("Error al buscar producto: " + excepcion.getMessage());

            return null;
        }
    }

    private HttpHeaders crearCabeceras() {
        HttpHeaders cabeceras = new HttpHeaders();

        cabeceras.setBasicAuth(usuario, contrasenha);

        cabeceras.setContentType(MediaType.APPLICATION_JSON);
        return cabeceras;
    }
}