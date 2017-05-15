package main.models.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_roles", schema = "public", catalog = "rentcar")
public class UserRolesEntity {
    private int userRoleId;
    private String role;
    private UserentryEntity userentryEntity;

    @Id
    @Column(name = "user_role_id")
    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }

    @ManyToOne
    public UserentryEntity getUserentryEntity() {
        return userentryEntity;
    }

    public void setUserentryEntity(UserentryEntity userentryEntity) {
        this.userentryEntity = userentryEntity;
    }

    @Basic
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

        if (userRoleId != that.userRoleId) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userRoleId;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
