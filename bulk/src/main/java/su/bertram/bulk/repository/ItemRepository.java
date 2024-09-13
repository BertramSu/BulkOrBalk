package su.bertram.bulk.repository;

import su.bertram.bulk.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ItemRepository extends MongoRepository<Item, String> {
    List<Item> findByIsBalk(boolean balk);

    List<Item> findByNameContaining(String name);
}