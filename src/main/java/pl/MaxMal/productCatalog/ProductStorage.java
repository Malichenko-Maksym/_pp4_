package pl.MaxMal.productCatalog;

import pl.MaxMal.productCatalog.Product;

import java.util.List;

public interface ProductStorage {
    List<Product> allProducts();

    void add(Product newProduct);

    Product loadById(String productId);

    List<Product> allPublishedProducts();
}
