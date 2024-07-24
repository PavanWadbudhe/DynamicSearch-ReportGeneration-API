package com.ashokit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="JRTP_INSURANCE_PLAN")
@Data
public class InsurancePlanEntity {
	@Id
	private Integer planId;
	@Column(length = 30)
	private String planName;
	@Column(length = 30)
	private String holderName;
	private Integer holderSsn;
	@Column(length = 20)
	private String planStatus;

}
