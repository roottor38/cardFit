package com.cardfit.project.service;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName="Shinhan", type="_doc") 
public class Shinhan {
	private String cardName;
	private String condition;
	private Benefit benefit;
}
