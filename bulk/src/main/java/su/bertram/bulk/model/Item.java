package su.bertram.bulk.model;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "isbalk")
    private boolean isBalk;

    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;

    public Item(String name, boolean isBalk, LocalDateTime purchaseDate) {
        this.name = name;
        this.isBalk = isBalk;
        this.purchaseDate = purchaseDate;
    }

    public Item() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBalk() {
        return isBalk;
    }

    public void setBalk(boolean balk) {
        isBalk = balk;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isBalk=" + isBalk +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
