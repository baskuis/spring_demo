package com.example.demo.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @Autowired
    ProductEntityCrudRepository productEntityCrudRepository;

    @GetMapping(path = "/contact")
    String contact() {
        return "phone: 414.123.1234";
    }

    @GetMapping(path = "/createProduct", produces = "text/html")
    String showProductForm() {
        String output = "<form action='' method='POST'>";
        output += "Name: <input name='name' type='text' /><br />";
        output += "Price: <input name='price' type='text' /><br />";
        output += "<input type='submit' />";
        output += "</form>";
        return output;
    }

    @PostMapping(path = "/createProduct")
    void createProduct(@ModelAttribute ProductEntity product) {
        if (product == null || product.getName() == null) {
            throw new RuntimeException("Name required");
        }
        if (product.getPrice() < 0) {
            throw new RuntimeException("Price cannot be negative");
        }
        productEntityCrudRepository.save(product);
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

        String myProducts = "<h2>Our products</h2>";
        for (ProductEntity p: products) {
            myProducts = myProducts + "<p>" + p.getName() + " $" + p.getPrice() + "</p>";
        }
        return myProducts;
    }

}
