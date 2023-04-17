package pl.jkanclerz;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jkanclerz.productcatalog.Product;
import pl.jkanclerz.productcatalog.ProductCatalog;


import java.util.*;

@RestController
public class ProductCatalogController {

    private ProductCatalog catalog;

    ProductCatalogController(ProductCatalog catalog){
        this.catalog = catalog;
    }

    @GetMapping("/api/products")
    List<Product> allProducts(){
        return catalog.allPublishedProducts();
    }
}
