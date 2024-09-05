package com.api.rest.SpringSecurity;

import com.api.rest.SpringSecurity.entities.Permission;
import com.api.rest.SpringSecurity.entities.Role;
import com.api.rest.SpringSecurity.entities.User;
import com.api.rest.SpringSecurity.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository userRepository) {
		return (args) -> {
			/* Crear permisos */
			Permission createPermission = Permission
					.builder()
					.name("CREATE")
					.build();
			Permission readPermission = Permission
					.builder()
					.name("READ")
					.build();
			Permission updatePermission = Permission
					.builder()
					.name("UPDATE")
					.build();
			Permission deletePermission = Permission
					.builder()
					.name("DELETE")
					.build();
			/* Crear rol */
			Role adminRole = Role
					.builder()
					.name(Role.RoleName.ADMIN)
					.permissions(Set.of(
							createPermission,
							readPermission,
							updatePermission,
							deletePermission
					))
					.build();
			Role userRole = Role
					.builder()
					.name(Role.RoleName.USER)
					.permissions(Set.of(
							createPermission,
							readPermission
					))
					.build();
			/* Crear usuario */
			User user = User
					.builder()
					.username("eze")
					.password("2378")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(userRole))
					.build();

			User admin = User
					.builder()
					.username("admin")
					.password("4314")
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(adminRole))
					.build();

			userRepository.saveAll(List.of(admin, user));
		};
	}

}
