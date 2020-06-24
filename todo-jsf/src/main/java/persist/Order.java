package persist;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    public enum Status {
        CREATED, PAID, SENT, RECEIVED, CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double price;

    @Column(length = 4096, nullable = false)
    private String phone;

    @Column(length = 4096, nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Order() {
    }

    public Order(Double price, String phone, String address, Status status) {
        this.price = price;
        this.phone = phone;
        this.address = address;
        this.status = status;
    }
}


