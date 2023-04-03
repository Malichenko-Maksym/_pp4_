package pl.jkanclerz.productCatalog;

import pl.jkanclerz.productcatalog.Product;

import java.util.List;

public interface ProductStorage {
    List<Product> allProducts();

    void add(Product newProduct);

    Product loadById(String productId);

    List<Product> allPublishedProducts();
}
