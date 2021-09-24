package com.example.CRUD_Spring_Boot.model;


import com.example.CRUD_Spring_Boot.repository.UserRepository;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
//@NoArgsConstructor//работает и без этих аннотаций CRUD
@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column (name = "name")
    private String firstName;

    @Column(name = "age" )
    private int age;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

    //@Transient
    //private String roleForHTML;


    @ManyToMany(fetch = FetchType.LAZY)//cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))

    private Set<Role> roles;


    public User() {
    }

    public User(Long id, String mail, String password) {
        this.id = id;
        this.mail = mail;
        this.password = password;
    }

    public User(String mail, String password, Set<Role> roles) {
        this.mail = mail;
        this.password = password;
        this.roles = roles;
    }
/*    public String getRoleStr(User user){
        String roleStr = new String();
        Set<Role>set = user.getRoles();
        for (Role role : set){
            roleStr+= role.getRole().replace("ROLE"," ").replace("["," ").replace("]","").
                    replace(","," ").replace("_"," ");
        }
        return roleStr;
    }*/

    public String getRoleStr(Set<Role> set){
        String roleStr = new String();
        for (Role role : set){
            roleStr+= role.getRole().replace("ROLE"," ").replace("[",
                            " ").replace("]","").
                    replace(","," ").replace("_"," ");
        }
        return roleStr;
    }




    public User( String firstName, int age, String lastName, String mail, String password) {
        this.firstName = firstName;
        this.age = age;
        this.lastName = lastName;
        this.mail = mail;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }

    @Override
    public String getUsername() {
        return firstName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }


}
