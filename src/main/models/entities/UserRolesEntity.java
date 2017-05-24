package main.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_roles", schema = "public", catalog = "rentcar")
public class UserRolesEntity {
    private String role;
    private UserentryEntity userentryEntity;

    @ManyToOne
    @JoinColumn(name = "userentry_id")
    public UserentryEntity getUserentryEntity() {
        return userentryEntity;
    }

    public void setUserentryEntity(UserentryEntity userentryEntity) {
        this.userentryEntity = userentryEntity;
    }

    @Id
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRolesEntity that = (UserRolesEntity) o;

        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
