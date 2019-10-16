package com.cardfit.project.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Benefit {
	private String movie;
	private String cafe;
	private String transportation;
	private String telecom;
	private String offshop;
	private String onshop;
	private String food;
	private String others;
	
}
