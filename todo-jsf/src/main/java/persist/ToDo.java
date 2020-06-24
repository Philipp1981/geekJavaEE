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

    public ToDo() {
    }

    public ToDo(Long id, String description, LocalDate targetDate) {
        this.id = id;
        this.description = description;
        this.targetDate = targetDate;
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
}

//
// D:\wildfly-20.0.0.Final\bin\jboss-cli.bat --connect --file=todo-jsf\mysql-driver.cli
 //       D:\Java EE\geekJavaEE>D:\Методички\wildfly-20.0.0.Final\bin\jboss-cli.bat --connect --file=todo-jsf\datasource.cli