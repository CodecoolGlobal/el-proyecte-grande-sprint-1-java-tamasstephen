package com.example.demo.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn (name="profile_id")
    @JsonIgnore
    private CreatorProfile causeProfile;

    private String email;
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private GrantedAuthority grantedAuthorities;

    public void payUser(){
        //TODO: implement payment
    }

    public boolean hasSameId(long id){
        return this.id == id;
    }

    public void setContent(CreatorProfile causeProfile){
        this.causeProfile = causeProfile;
    }

    public String getEmail(){
        return this.email;
    }

    public boolean isMatchingEmail(String email){
        return email.equals(this.email);
    }

    public boolean isValidPassword(String password) {
        return password.equals(this.password);
    }

    public boolean isCreatorProfileAvailable(){
        return causeProfile != null;
    }

    public boolean isAdmin(){
        return grantedAuthorities.getAuthority().equals("ROLE_ADMIN");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities == null ? List.of() : List.of(grantedAuthorities);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}

