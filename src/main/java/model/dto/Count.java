package model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Count {
	
	private long movie;
	private long cafe;
	private long telecom;
	private long transprtation;
	private long onshop;
	private long offshop;
	private long food;
	private long others;
}
