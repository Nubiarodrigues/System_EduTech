package com.edutech.backend.entities;

import com.edutech.backend.enuns.RoleUser;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
	private Long id;

	private String name;
	private String email;
	private String password;
	private String registration;
	private Long idSchool;

	@Enumerated(EnumType.STRING)
	private RoleUser role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (this.role == null) {
			return List.of();
		}

		switch (this.role) {
			case ADMIN:
				return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
			case OPERATOR:
				return List.of(new SimpleGrantedAuthority("ROLE_OPERATOR"));
			case TEACHER:
				return List.of(new SimpleGrantedAuthority("ROLE_TEACHER"));
			case COORDINATOR:
				return List.of(new SimpleGrantedAuthority("ROLE_COORDINATOR"));
			case STUDENT:
				return List.of(new SimpleGrantedAuthority("ROLE_STUDENT"));
			default:
				return List.of();
		}
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
