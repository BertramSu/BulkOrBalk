package su.bertram.bulk.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "item")
public class Item {
    @Id
    private String id;

    private String name;
    private boolean isBalk;
    private LocalDateTime purchaseDate;

    public Item(String name, boolean isBalk, LocalDateTime purchaseDate) {
        this.name = name;
        this.isBalk = isBalk;
        this.purchaseDate = purchaseDate;
    }

    public Item() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
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
