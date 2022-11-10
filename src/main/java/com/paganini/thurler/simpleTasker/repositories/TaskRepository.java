package com.paganini.thurler.simpleTasker.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paganini.thurler.simpleTasker.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByUser_Id(Integer id);
    //Optional<Task> findById(Integer id);
    //Search by the userid


    //JPQL
    //@Query(value = "SELECT t FROM Task WHERE t.user.id = :id")
    //List<Task> findByUser_Id(@Param("id")Integer id);

  
    //SQL
    //@Query(nativeQuery = true, value = "SELECT * FROM task t WHERE t.user_id = :id")
    //List<Task> findByUser_Id(@Param("id")Integer id);
    
}
