package org.jsp.cda.structure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ResponseStructure<T> {
	private int httpStatus;
	private String message;
	private T body;
}
