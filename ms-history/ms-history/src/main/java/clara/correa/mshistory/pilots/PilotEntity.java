package clara.correa.mshistory.pilots;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Document("pilots")
public class PilotEntity {

	@Id
	private String id;
	@Setter
	private String name;
	@Setter
	private Integer age;
}