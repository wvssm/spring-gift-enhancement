package gift.controller;

import gift.dto.request.ProductRequest;
import gift.dto.response.OptionResponse;
import gift.dto.response.ProductResponse;
import gift.service.OptionService;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private final ProductService productService;
    private final OptionService optionService;

    public ProductRestController(ProductService productService, OptionService optionService) {
        this.productService = productService;
        this.optionService = optionService;
    }

    @PostMapping
    public ResponseEntity<Void> add(@Valid @RequestBody ProductRequest request) {
        productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        ProductResponse product = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getPagedProducts(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getPagedProducts(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        productService.updateById(id, request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}/options")
    public ResponseEntity<List<OptionResponse>> getProductOptions(@PathVariable Long id) {
        productService.findById(id);
        return ResponseEntity.ok().body(optionService.findByProductId(id));
    }
}