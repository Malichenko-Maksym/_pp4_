package pl.jkanclerz.productCatalog;

import pl.jkanclerz.productcatalog.Product;


import java.util.ArrayList;
import java.util.List;

public class DbProductStorage implements ProductStorage {
    @Override
    public List<Product> allProducts() {
        return null;
    }

    @Override
    public void add(Product newProduct) {

    }

    @Override
    public Product loadById(String productId) {
        return null;
    }

    @Override
    public List<Product> allPublishedProducts() {
        return null;
    }
}
