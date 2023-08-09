package hello.productservice.domain.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor //기본생성자
@Data
public class Product {

    private Long id;
    private String productName;
    private Integer price;
    private Integer quantity;

//    public Product() {
//
//    }

    public Product(String productName, Integer price, Integer quantity) {
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }
}
