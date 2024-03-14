package com.example;

import com.example.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryServiceTest {

    @BeforeEach
    void setUp() {
        CategoryService categoryService = new CategoryService();
        categoryService.clearCategories();
    }

    // El test requiere que la clase CategoryService tenga un método createCategory,
    // así como un método getCategoryById.
    @Test
    void createCategoryTest() {
        CategoryService categoryService = new CategoryService();
        categoryService.createCategory("category1");

        assertEquals(1, categoryService.getCategoryList().size());
        String categoryAsString = categoryService.getCategoryById(1L).toString();
        assertEquals("Category{id=1, name='category1'}", categoryAsString);
        System.out.println(categoryAsString);

    }

    @Test
    void getCategoryList() {
        CategoryService categoryService = new CategoryService();
        categoryService.createCategory("category1");
        categoryService.createCategory("category2");
        categoryService.createCategory("category3");

        assertEquals(3, categoryService.getCategoryList().size());
        System.out.println(categoryService.getCategoryList());
    }

    @Test
    void eraseCategoryTest() {
        CategoryService categoryService = new CategoryService();
        categoryService.createCategory("category1");
        categoryService.createCategory("category2");
        categoryService.createCategory("category3");
        categoryService.eraseCategory(2L);
        assertEquals(2, categoryService.getCategoryList().size());
        System.out.println(categoryService.getCategoryList());
    }

}
