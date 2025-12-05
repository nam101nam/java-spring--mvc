package com.example.demo.controller.admin;

import java.util.List;

import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.Product;
import com.example.demo.domain.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UploadService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {
    final private UploadService uploadService;
    final private ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getProduct(Model model) {
        List<Product> products = this.productService.fetchProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @RequestMapping("/admin/product/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "/admin/product/create";
    }

    @PostMapping(value = "/admin/product/create")
    public String createProductPage(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("productFile") MultipartFile file) {

        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println("Field error in object '" + error.getObjectName() + "' on field '" + error.getField()
                    + "': " + error.getDefaultMessage());
        }
        if (newProductBindingResult.hasErrors()) {
            return "/admin/product/create";
        }
        String fileName = this.uploadService.handleUploadFile(file, "products");
        product.setImage(fileName);
        this.productService.creaProduct(product);
        return "redirect:/admin/product";
    }

    // View product detail page

    @RequestMapping("/admin/product/{id}")
    public String getProductDetail(Model model, @PathVariable Long id) {
        Product product = this.productService.fetchProductById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("id", id);
        return "/admin/product/detail";
    }

    // Get delete product page

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @PathVariable Long id) {
        // User user = new User();
        // user.setId(id);
        model.addAttribute("product", new Product());
        return "/admin/product/delete";
    }

    @RequestMapping(value = "/admin/product/delete", method = RequestMethod.POST)
    public String deleteProductPage(Model model, @ModelAttribute("product") Product product) {
        this.productService.deleteProduct(product.getId());
        return "redirect:/admin/product";
    }

    // Get update product page

    @RequestMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable Long id) {
        Product currentProduct = this.productService.fetchProductById(id).get();
        model.addAttribute("newProduct", currentProduct);
        return "/admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String updateUser(Model model, @ModelAttribute("newProduct") @Valid Product product,
            @RequestParam("productFile") MultipartFile file,
            BindingResult newProductBindingResult) {
        if (newProductBindingResult.hasErrors()) {
            return "admin/product/update";
        }
        Product currentProduct = this.productService.fetchProductById(product.getId()).get();
        if (!file.isEmpty()) {
            String img = this.uploadService.handleUploadFile(file, "products");
            currentProduct.setImage(img);

        }
        currentProduct.setName(product.getName());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setQuantity(product.getQuantity());
        currentProduct.setDetailDesc(product.getDetailDesc());
        currentProduct.setShortlDesc(product.getShortlDesc());
        currentProduct.setFactory(product.getFactory());
        currentProduct.setTarget(product.getTarget());
        this.productService.creaProduct(currentProduct);

        return "redirect:/admin/product";
    }

}
