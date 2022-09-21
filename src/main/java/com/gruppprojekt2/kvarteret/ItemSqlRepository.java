package com.gruppprojekt2.kvarteret;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemSqlRepository extends CrudRepository<Item, Integer>
{
    Item findById(int id);
    List<Item> findAll();
    //void save(Item item);

    //@Query(value = "INSERT INTO item (firstName,lastName,email,password) values (?1,?1,?1,?1)", nativeQuery = true)
    //List<Dog> findDogsWithQueryAge(int age);

}
