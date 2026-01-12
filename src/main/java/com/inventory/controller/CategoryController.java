package com.inventory.controller;

import com.inventory.model.Category;
import com.inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid; // YENİ IMPORT
import java.util.List;

@RestController // Bu sınıfın bir API kapısı olduğunu belirtir
@RequestMapping("/api/categories") // Adresimiz: localhost:8080/api/categories
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // GET isteği: Tüm kategorileri getir
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // POST isteği: Yeni kategori ekle
    // Örnek JSON: "Elektronik"
    @PostMapping
    // @RequestBody'nin yanına @Valid ekliyoruz.
    // Bu şu demek: "Gelen veriyi Category sınıfına çevirirken içindeki @NotBlank kuralına bak."
    public Category createCategory(@Valid @RequestBody Category category) {
         // Not: Controller'ı "String name" yerine "Category category" alacak şekilde güncelliyoruz.
         // Çünkü Validation, Nesne alanları üzerinde çalışır.
         return categoryService.saveCategory(category.getName());
    }

    // YENİ ENDPOINT: Alt Kategori Ekle
    // Örnek: POST /api/categories/1/subcategories?name=Laptoplar
    @PostMapping("/{parentId}/subcategories")
    public Category createSubCategory(@PathVariable Long parentId, @RequestParam String name) {
        return categoryService.addSubCategory(parentId, name);
    }
}