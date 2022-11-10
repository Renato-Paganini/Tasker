package com.paganini.thurler.simpleTasker.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;



@Entity
@Table(name = User.TABLENAME)
public class User {
    public static final String TABLENAME = "user";

    public interface CreateUser{

    }
    public interface UpdateUser{

    }

    //This is how a table is made on springboot
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name="username", length = 100, nullable = false, unique = true)
    @NotNull(groups = CreateUser.class)
    @NotEmpty(groups = CreateUser.class)
    @Size(groups = CreateUser.class ,min = 2, max = 100)
    private String username;
    
    @JsonProperty(access = Access.WRITE_ONLY)
    @Column (name= "password", length=20, nullable=false)
    @NotNull(groups = {CreateUser.class, UpdateUser.class})
    @NotEmpty(groups ={CreateUser.class, UpdateUser.class})
    @Size(min = 5, max = 20, groups = {CreateUser.class, UpdateUser.class})
    private String password;
    
   @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>(100);

    //It needs an empty constructor
    public User(){

    }


    public User(Integer id,
            @NotNull(groups = CreateUser.class) @NotEmpty(groups = CreateUser.class) @Size(groups = CreateUser.class, min = 2, max = 100) String username,
            @NotNull(groups = { CreateUser.class, UpdateUser.class }) @NotEmpty(groups = { CreateUser.class,
                    UpdateUser.class }) @Size(min = 5, max = 20, groups = { CreateUser.class,
                            UpdateUser.class }) String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    public static String getTablename() {
        return TABLENAME;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }


    public List<Task> getTasks() {
        return tasks;
    }


    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    


}
