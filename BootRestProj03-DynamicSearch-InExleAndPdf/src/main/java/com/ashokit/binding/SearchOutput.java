package com.ashokit.binding;

import lombok.Data;

@Data
public class SearchOutput {
	private Integer planId;
	private String planName;
	private String holderName;
	private Integer holderSsn;
	private String planStatus;
}
