package services;

import java.io.Serializable;
import java.time.LocalDate;

public class ToDoDto implements Serializable {

    private Long id;

    private String description;

    private LocalDate targetDate;

    private Long categoryId;

    private String categoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ToDoDto() {
    }

    public ToDoDto(Long id, String description, LocalDate targetDate, Long categoryId, String categoryName) {
        this.id = id;
        this.description = description;
        this.targetDate = targetDate;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
