package clara.correa.mshistory.races;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import clara.correa.mshistory.cars.CarEntity;
import lombok.Getter;
import lombok.Setter;

@Document("races")
@Getter
public class RaceEntity{
    @Id
    private String id;
    @Setter
    private String name;
	@Setter
    private String country;
    @Setter
    @Field(targetType = FieldType.DATE_TIME)
    private Date date;
    @Setter
    private List<CarEntity> cars;
    @Setter
    @Field(targetType = FieldType.DATE_TIME)
    private LocalDateTime dateCad;
    
	public RaceEntity(String name, String country, Date date, List<CarEntity> cars, LocalDateTime dateCad) {
		this.name = name;
		this.country = country;
		this.date = date;
		this.cars = cars;
		this.dateCad = dateCad;
	} 
}