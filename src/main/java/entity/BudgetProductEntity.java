package entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity(name = "produtoOrcamento")
public class BudgetProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome")
    private String productName;
    @Column(name = "valor")
    private Double productValue;
    @Column(name ="quantidade")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    private BudgetEntity budget;

    public BudgetProductEntity() {
    }

    public BudgetProductEntity(Long id, String productName, Double productValue, Integer quantity, BudgetEntity budget) {
        this.id = id;
        this.productName = productName;
        this.productValue = productValue;
        this.quantity = quantity;
        this.budget = budget;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductValue() {
        return productValue;
    }

    public void setProductValue(Double productValue) {
        this.productValue = productValue;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BudgetEntity getBudget() {
        return budget;
    }

    public void setBudget(BudgetEntity budget) {
        this.budget = budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetProductEntity that = (BudgetProductEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
