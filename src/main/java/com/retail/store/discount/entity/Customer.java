package com.retail.store.discount.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;

	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PHONE_NO")
	private String phoneNumber;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "CREATED_AT")
	private LocalDate createdAt;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "UPDATED_AT")
	private LocalDate updatedAt;

	@Column(name = "CUSTOMER_TYPE")
	private String customerType;

}