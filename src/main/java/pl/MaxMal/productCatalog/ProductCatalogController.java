package pl.MaxMal.productCatalog;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.MaxMal.productCatalog.Product;
import pl.MaxMal.productCatalog.ProductCatalog;


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
