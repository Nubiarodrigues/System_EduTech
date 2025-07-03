package com.edutech.backend.entities;

import com.edutech.backend.enuns.RoleUser;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.util.Collection;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SuperBuilder
public abstract class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	protected Long id;

	protected String name;
	protected String email;
	protected String password;
	protected String registration;

	@Enumerated(EnumType.STRING)
	protected RoleUser role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == RoleUser.ADMIN) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_ADMIN"),
					new SimpleGrantedAuthority("ROLE_OPERATOR"),
					new SimpleGrantedAuthority("ROLE_TEACHER"),
					new SimpleGrantedAuthority("ROLE_STUDENT"),
					new SimpleGrantedAuthority("ROLE_COORDINATOR")
			);
		} else if (this.role == RoleUser.OPERATOR) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_OPERATOR"),
					new SimpleGrantedAuthority("ROLE_STUDENT")
			);
		} else if (this.role == RoleUser.TEACHER) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_TEACHER"),
					new SimpleGrantedAuthority("ROLE_STUDENT")
			);
		} else if (this.role == RoleUser.COORDINATOR) {
			return List.of(
					new SimpleGrantedAuthority("ROLE_COORDINATOR"),
					new SimpleGrantedAuthority("ROLE_STUDENT")
			);
		} else if (this.role == RoleUser.STUDENT){
			return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
		}

		return List.of();
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}
}
