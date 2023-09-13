package clara.correa.mshistory.cars;

import org.springframework.data.annotation.Id;

import clara.correa.mshistory.pilots.PilotEntity;
import lombok.Getter;
import lombok.Setter;

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
}
