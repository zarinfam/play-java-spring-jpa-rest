package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mohsen on 4/5/15.
 */
@Entity
@Table(name = "ROLE_OF_USER")
public class RoleOfUser implements Serializable {
    public RoleOfUser() {
    }

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", foreignKey = @ForeignKey(name = "FK_1"))
    private Role role;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public RoleOfUser(Role role, User user) {
        this.role = role;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
