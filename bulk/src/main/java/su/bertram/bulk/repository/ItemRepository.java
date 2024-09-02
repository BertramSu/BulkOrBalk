package su.bertram.bulk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import su.bertram.bulk.model.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByIsBalk(boolean balk);

    List<Item> findByNameContaining(String name);
}
