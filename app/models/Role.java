package models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by mohsen on 4/5/15.
 */
@Entity
@Table(name = "TBL_ROLE")
public class Role implements Serializable {

    public Role() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue
    private Long id;

    @Column(name = "ROLE_NAME")
    private String name;

    public Role(long roleId) {
        this.id = roleId;
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
}
