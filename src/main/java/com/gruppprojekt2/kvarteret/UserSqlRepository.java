package com.gruppprojekt2.kvarteret;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserSqlRepository  extends CrudRepository<Siteuser, Integer>
{
    //Siteuser findByUser_name(String username);

    /*
    @Query(value = "SELECT s FROM Siteuser s WHERE s.User_name = ?1")
    Siteuser findUserByName(String name);*/
}
