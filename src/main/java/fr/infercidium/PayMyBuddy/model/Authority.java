package fr.infercidium.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    public Authority() {
        this.users = new ArrayList<>();
    }

    public Authority(final String nameC, final List<User> usersC) {
        this.name = nameC;
        this.users = usersC;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long idS) {
        this.id = idS;
    }

    public String getName() {
        return name;
    }

    public void setName(final String nameS) {
        this.name = nameS;
    }

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public void setUsers(final List<User> usersS) {
        this.users = usersS;
    }

    public void addUser(final User user) {
        users.add(user);
    }

    public void removeUser(final User user) {
        users.remove(user);
    }

    @Override
    public String toString() {
        return "Authority{"
                + "id = " + id
                + ", name = '" + name + '\''
                + ", users = " + users
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Authority)) return false;
        Authority authority = (Authority) o;
        return getId().equals(authority.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
