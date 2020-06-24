package controllers;


import persist.Product;
import persist.ProductRepository;
import persist.ToDo;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@SessionScoped
@Named
public class ProductController implements Serializable{

   @Inject
    private ProductRepository productRepository;

    private Product product;

    private List<Product> products;

    public void preLoadProducts(ComponentSystemEvent componentSystemEvent){
        this.products=productRepository.findAll();
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct(){
        this.product=new Product();
        return "product.xhtml?faces-redirect=true";
    }

    public List<Product> getAllProduct() throws SQLException {
        return products;
    }

    public String editProduct(Product product){
        this.product=new Product();
        return "product.xhtml?faces-redirect=true";
    }

    public void deleteProduct(Product product) throws SQLException {
        productRepository.delete(product.getId());
    }

    public String saveProduct(Product product) throws SQLException {
        if(product.getId()==null) productRepository.insert(product);
        else productRepository.update(product);
        return "index.xhtml?faces-redirect=true";
    }

}
