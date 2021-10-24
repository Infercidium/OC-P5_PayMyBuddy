package fr.infercidium.PayMyBuddy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Authorities")
public class Authority {

    /**
     * Attribute id corresponding to id generate.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    /**
     * Attribute name corresponding to name choice.
     */
    @NotBlank(message = "name cannot be null or empty.")
    private String name;

    /**
     * Attribute users corresponding to users list.
     */
    @ManyToMany(mappedBy = "authorities")
    private List<User> users;

    /**
     * Basic builder.
     */
    public Authority() {
        this.users = new ArrayList<>();
    }

    /**
     * Complete builder.
     * @param nameC attribute.
     * @param usersC attribute.
     */
    public Authority(final String nameC, final List<User> usersC) {
        this.name = nameC;
        this.users = usersC;
    }

    /**
     * Getter of id.
     * @return id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter of id.
     * @param idS : new id.
     */
    public void setId(final Long idS) {
        this.id = idS;
    }

    /**
     * Getter of name.
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter of name.
     * @param nameS : new name.
     */
    public void setName(final String nameS) {
        this.name = nameS;
    }

    /**
     * Getter of users.
     * @return users.
     */
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Setter of users.
     * @param usersS : new users.
     */
    public void setUsers(final List<User> usersS) {
        this.users = usersS;
    }

    /**
     * ToString method.
     * @return toString.
     */
    @Override
    public String toString() {
        return "Authority{"
                + "id = " + id
                + ", name = '" + name + '\''
                + ", users = " + users
                + '}';
    }

    /**
     * Equals method.
     * @param o : element to compare.
     * @return result of the comparison.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Authority)) {
            return false;
        }
        Authority authority = (Authority) o;
        return getId().equals(authority.getId());
    }

    /**
     * HashCode method.
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
