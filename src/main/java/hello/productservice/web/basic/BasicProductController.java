package hello.productservice.web.basic;

import hello.productservice.domain.product.Product;
import hello.productservice.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/products")
@RequiredArgsConstructor
public class BasicProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "basic/products";
    }

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        productRepository.save(new Product("productA", 10000, 10));
        productRepository.save(new Product("productB", 20000, 20));

    }

    @GetMapping("/{productId}")
    public String product(@PathVariable Long productId, Model model) {
        Product product = productRepository.findById(productId);
        model.addAttribute("product", product);
        return "basic/product";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productRepository.save(product);
        return "basic/product";
    }

}
