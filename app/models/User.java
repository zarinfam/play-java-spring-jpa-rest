package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by mohsen on 4/5/15.
 */
@Entity
@Table(name = "TBL_USER")
public class User implements Serializable {

    public User() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "USER_NAME")
    private String name;

    @OneToMany(mappedBy = "user")
    private List<RoleOfUser> userRoles;

    public User(long userId) {
        this.id = userId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RoleOfUser> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleOfUser> userRoles) {
        this.userRoles = userRoles;
    }
}
