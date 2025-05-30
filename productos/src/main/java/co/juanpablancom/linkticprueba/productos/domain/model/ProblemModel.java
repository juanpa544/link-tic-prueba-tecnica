package co.juanpablancom.linkticprueba.productos.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemModel {

	private String type;
	private String title;
	private int status;
	private String detail;
	private String instance;

}
