package com.ifc.work.persistence;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@NamedQuery(name = "UserPermissionsEntity.findByUserId", query = "select new UserEntity(u.id,u.activeUser,u.birth,u.cpf,u.gender,u.name) from UserEntity u where u.id=:id")
@NamedQuery(name = "UserPermissionsEntity.findByUserCredentialsId", query = "select new UserPermissionsEntity(u.id,u.isAdmin,u.isOperator,u.isAssistant,u.userCredentialsId) from UserPermissionsEntity u where u.userCredentialsId.id=:id")
@NamedQuery(name = "UserPermissionsEntity.findByLogin", query = "select new UserCredentialsEntity(u.id,u.login,u.password,u.userId) from UserCredentialsEntity u where u.login=:login")


@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_users_permissions")
public class UserPermissionsEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="isAdmin")
    boolean isAdmin;

    @Column(name="isOperator")
    boolean isOperator;

    @Column(name="isAssistant")
    boolean isAssistant;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_credentialsId_fk")
    private UserCredentialsEntity userCredentialsId;

    public UserPermissionsEntity() {
    }

    public UserPermissionsEntity(Long id,boolean admin, boolean operator, boolean assistant, UserCredentialsEntity user) {
        this.id= id;
        this.isAdmin = admin;
        this.isOperator = operator;
        this.isAssistant = assistant;
        this.userCredentialsId = user;
    }
    public UserPermissionsEntity(boolean admin, boolean operator, boolean assistant, UserCredentialsEntity user) {
        this.isAdmin = admin;
        this.isOperator = operator;
        this.isAssistant = assistant;
        this.userCredentialsId = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isAdmin == true) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        }
        return List.of(
                new SimpleGrantedAuthority("ROLE_USER")
        );
    }

    @Override
    public String getPassword() {
        return userCredentialsId.getPassword();
    }

    @Override
    public String getUsername() {
        return userCredentialsId.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}