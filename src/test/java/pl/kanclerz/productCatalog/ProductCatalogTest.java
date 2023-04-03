package pl.kanclerz.productCatalog;

import org.junit.jupiter.api.Test;

import pl.jkanclerz.productCatalog.ProductCatalog;
import pl.jkanclerz.productcatalog.Product;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class ProductCatalogTest{

    private ProductCatalog thereISProductCatalog() {
    }

    @Test
    void  itAllowsToAddProduct(){
        //Arrange
        ProductCatalog catalog = thereISProductCatalog();
        //Act

        //Assert
        List<Product> producs = catalog.allProducts();
        assert 1 == producs.size();
    }

//    @Test
//    void itExposeEmptyCollectionOfProduct(){
//        ProductCatalog catalog = thereISProductCatalog();
//        String productId = catalog.addProduct("lego set 8080","nice one");
//
//        List<Product> products = catalog.allProducts();
//
//        assert products.isEmpty()==true;
//    }

    @Test
    void itAllowsToChangePrice(){
        ProductCatalog catalog = thereISProductCatalog();
        String productId = catalog.addProduct("lego set 8080","nice one");

        catalog.changePrice(productId, BigDecimal.valueOf(10));
    }

    @Test
    void  itAllowsToPublishProduct(){
        ProductCatalog catalog = thereISProductCatalog();
        String productId = catalog.addProduct("lego set 8080","ff");
        catalog.changePrice(productId, BigDecimal.valueOf(10));
        catalog.assignImage(productId, "nice.jpeg");

        catalog.publishProduct(productId);

        assertEquals(1, catalog.allPublishedProducts());

    }




}