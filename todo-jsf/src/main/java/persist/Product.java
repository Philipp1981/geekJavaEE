package persist;

public class Product {
    private Long id;
    private String category;
    private String title;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product(Long id, String category, String title, Double price) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.price = price;
    }

    public Product() {
    }
}
