package com.paganini.thurler.simpleTasker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paganini.thurler.simpleTasker.models.Task;
import com.paganini.thurler.simpleTasker.models.User;
import com.paganini.thurler.simpleTasker.repositories.TaskRepository;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserserService userserService;

    public Task findById(Integer id){
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }

    
    public List<Task> findAllByUserId(Integer id){
        List<Task> tasks = this.taskRepository.findByUser_Id(id);
        return tasks;
    }

    @Transactional
    public Task create (Task obj){
        User user = this.userserService.findByID(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj= this.taskRepository.save(obj);
        return obj;
    }
    
    @Transactional
    public Task update (Task obj){
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delte(Integer id){
        findById(id);
        try{
            this.taskRepository.deleteById(id);
        }catch(Exception e){
            throw new RuntimeException("Cannot delete, there are associated entities");
        }
    }

}
