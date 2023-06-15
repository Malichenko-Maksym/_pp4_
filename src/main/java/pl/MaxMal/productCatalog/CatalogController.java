//package pl.MaxMal.productCatalog;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import pl.MaxMal.productCatalog.Product;
//import pl.MaxMal.productCatalog.ProductCatalog;
//
//import java.util.List;
//
//@RestController
//public class CatalogController {
//
//    private ProductCatalog productCatalog;
//
//    CatalogController(ProductCatalog productCatalog) {
//        this.productCatalog = productCatalog;
//    }
//
//    @GetMapping("/api/products")
//    List<Product> allProducts() {
//        return productCatalog.allPublishedProducts();
//    }
//}
