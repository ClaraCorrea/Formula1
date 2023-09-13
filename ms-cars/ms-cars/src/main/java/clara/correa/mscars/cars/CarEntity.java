package clara.correa.mscars.cars;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import clara.correa.mscars.pilots.PilotEntity;
import lombok.Getter;
import lombok.Setter;

@Document("cars")
@Getter
public class CarEntity {

	 @Id
	 private String id;
	 @Setter
	 private String brand;
	@Setter
	 private String model;
	 @Setter
	 private String year;
	 @Setter
	 private Integer speed;
	 @Setter
	 private PilotEntity pilot;
	 
	 public CarEntity(String brand, String model, String year, Integer speed, PilotEntity pilot) {
		this.brand = brand;
		this.model = model;
		this.year = year;
		this.speed = speed;
		this.pilot = pilot;
	}	 
}