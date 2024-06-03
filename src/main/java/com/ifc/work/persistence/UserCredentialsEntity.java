package com.ifc.work.persistence;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NamedQuery(name = "UserCredentialsEntity.findByUserId", query = "select new UserEntity(u.id,u.activeUser,u.birth,u.cpf,u.gender,u.name) from UserEntity u where u.id=:id")
@NamedQuery(name = "UserCredentialsEntity.findByLogin", query = "select new UserCredentialsEntity(u.id,u.login,u.password,u.userId) from UserCredentialsEntity u where u.login=:login")

@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "tb_userscredentials")
public class UserCredentialsEntity implements UserDetails  {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(nullable = false)
    protected String login;
    @Column(nullable = false)
    protected String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId_fk", nullable = false)
    protected UserEntity userId;

    public UserCredentialsEntity() {
    }

    public UserCredentialsEntity(Long id, String login, String password, UserEntity user) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userId = user;
    }

    public UserCredentialsEntity( String login, String password, UserEntity user) {
        this.login = login;
        this.password = password;
        this.userId = user;
    }

    //testcommit


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return this.password;
    }


    public String getUsername() {
        return this.login;
    }


    public boolean isAccountNonExpired() {
        return true;
    }


    public boolean isAccountNonLocked() {
        return true;
    }


    public boolean isCredentialsNonExpired() {
        return true;
    }


    public boolean isEnabled() {
        return true;
    }
}
