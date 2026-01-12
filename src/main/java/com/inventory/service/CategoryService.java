package com.inventory.service;

import com.inventory.model.Category;
import com.inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category saveCategory(String name) {
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }

    // YENİ METOD: Alt Kategori Ekleme
    @Transactional
    public Category addSubCategory(Long parentId, String name) {
        // 1. Üst kategoriyi bul
        Category parent = categoryRepository.findById(parentId)
                .orElseThrow(() -> new RuntimeException("Üst kategori bulunamadı!"));

        // 2. Yeni alt kategoriyi oluştur
        Category subCategory = new Category();
        subCategory.setName(name);
        
        // 3. Composite Bağlantısı: Üst kategoriye ekle
        parent.add(subCategory); 
        
        // 4. Kaydet (Cascade sayesinde alt kategori de kaydedilir)
        categoryRepository.save(parent);
        
        return subCategory;
    }
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    // Alt kategori ekleme, silme vb. işlemleri burada genişletebilirsin.
}