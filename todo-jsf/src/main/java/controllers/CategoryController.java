package controllers;

import persist.Category;
import persist.CategoryRepository;
import persist.Product;
import persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class CategoryController implements Serializable {

    @Inject
    private CategoryRepository categoryRepository;

    private Category category;

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String createCategory(){
        this.category=new Category();
        return "category.xhtml?faces-redirect=true";
    }

    public List<Category> getAllCategory() throws SQLException {
        return categoryRepository.findAll();
    }

    public String editCategory(Category category){
        this.category=new Category();
        return "category.xhtml?faces-redirect=true";
    }

    public void deleteCategory(Category category) throws SQLException {
        categoryRepository.delete(category.getId());
    }

    public String saveCategory(Category category) throws SQLException {
        if(category.getId()==null) categoryRepository.insert(category);
        else categoryRepository.update(category);
        return "category.xhtml?faces-redirect=true";
    }

}

