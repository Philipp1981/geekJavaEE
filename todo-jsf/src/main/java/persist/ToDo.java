package persist;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Table(name="todos")
@Entity
@NamedQuery(name = "ToDo.findAll", query = "SELECT t FROM ToDo t")
public class ToDo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 4096, nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate targetDate;

    @ManyToOne
    private Category category;

    public ToDo() {
    }

    public ToDo(Long id, String description, LocalDate targetDate, Category category) {
        this.id = id;
        this.description = description;
        this.targetDate = targetDate;
        this.category = category;
    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

//C:\Program Files\MySQL\MySQL Server 5.7\bin\mysqld
// D:\wildfly-20.0.0.Final\bin\jboss-cli.bat --connect --file=todo-jsf\mysql-driver.cli
 //       D   C:\wildfly-20.0.0.Final\bin\jboss-cli.bat --connect --file=todo-jsf\datasource.cli