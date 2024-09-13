package su.bertram.bulk.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import su.bertram.bulk.model.Item;
import su.bertram.bulk.repository.ItemRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ItemController {
    @Autowired
    ItemRepository itemRepository;

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(required = false) String name){
        try{
            List<Item> items = new ArrayList<Item>();

            if (name == null)
                itemRepository.findAll().forEach(items::add);
            else
                itemRepository.findByNameContaining(name).forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") String id){
        Optional<Item> itemData = itemRepository.findById(id);

        if (itemData.isPresent())
            return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/item")
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        try {
            Item _item = itemRepository.save(new Item(item.getName(), item.isBalk(), item.getPurchaseDate()));
            return new ResponseEntity<>(_item, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("item/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable("id") String id, @RequestBody Item item){
        Optional<Item> itemData = itemRepository.findById(id);

        if (itemData.isPresent()){
            Item _item = itemData.get();
            _item.setBalk(item.isBalk());
            _item.setName(item.getName());
            _item.setPurchaseDate(item.getPurchaseDate());
            return new ResponseEntity<>(itemRepository.save(_item), HttpStatus.OK);
        }else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/item/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        try {
            itemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/items/isBalk")
    public ResponseEntity<List<Item>> findByIsBalk(){
        try{
            List<Item> items = itemRepository.findByIsBalk(true);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(items, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
