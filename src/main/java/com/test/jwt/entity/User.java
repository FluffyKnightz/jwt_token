package com.test.jwt.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(length = 30, nullable = false, unique = true)
	private String name;

	@Column(length = 35, nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "VARCHAR(10)", nullable =  false)
	private Role role;

}
