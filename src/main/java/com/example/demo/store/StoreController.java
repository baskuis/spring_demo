package com.example.demo.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @Autowired
    ProductEntityCrudRepository productEntityCrudRepository;

    @GetMapping(path = "/contact")
    String contact() {
        return "phone: 414.123.1234";
    }

    @PostMapping(path = "/createProduct")
    void createProduct(@RequestBody ProductEntity product) {
        productEntityCrudRepository.save(product);
    }

    void logSomeStuff() {

    }

    @GetMapping(path = "/home")
    String home() {

        ProductEntity bottle = new ProductEntity();
        bottle.setName("Best OJ ever");
        bottle.setPrice(33.88F);
        productEntityCrudRepository.save(bottle);

        ProductEntity cookie = new ProductEntity();
        cookie.setName("Best Cookie ever");
        cookie.setPrice(43.48F);
        productEntityCrudRepository.save(cookie);

        Iterable<ProductEntity> products = productEntityCrudRepository.findAll();

        products.forEach((p) -> {
            System.out.println(p.getName());
        });

        return "Hi home";
    }

}
