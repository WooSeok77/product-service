package hello.productservice.domain;

import hello.productservice.domain.product.Product;
import hello.productservice.domain.product.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProductRepositoryTest {

    ProductRepository productRepository = new ProductRepository();

    @AfterEach
    void afterEach() {
        productRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Product product = new Product("productA", 10000, 10);
        //when
        Product saveProduct = productRepository.save(product);

        //then
        Product findProduct = productRepository.findById(product.getId());
        assertThat(findProduct).isEqualTo(saveProduct);
    }

    @Test
    void findAll() {
        //given
        Product Product1 = new Product("Product1", 10000, 10);
        Product Product2 = new Product("Product2", 20000, 20);
        productRepository.save(Product1);
        productRepository.save(Product2);
        //when
        List<Product> result = productRepository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(Product1, Product2);
    }

    @Test
    void updateProduct() {
        //given
        Product product = new Product("product1", 10000, 10);
        Product saveProduct = productRepository.save(product);
        Long productId = saveProduct.getId();
        //when
        Product updateParam = new Product("product2", 20000, 30);
        productRepository.update(productId, updateParam);
        Product findProduct = productRepository.findById(productId);
        //then
        assertThat(findProduct.getProductName()).isEqualTo(updateParam.getProductName());
        assertThat(findProduct.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findProduct.getQuantity()).isEqualTo(updateParam.getQuantity());
        }
    }

