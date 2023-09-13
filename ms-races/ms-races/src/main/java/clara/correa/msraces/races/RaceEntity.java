package clara.correa.msraces.races;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import clara.correa.mscars.cars.CarEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
public class RaceEntity implements Serializable{
    @Setter
    private String name;
	@Setter
    private String country;
    @Setter
    @Field(targetType = FieldType.DATE_TIME)
    private Date date;
    @Setter
    private List<CarEntity> cars;
    
	public RaceEntity(String name, String country, Date date, List<CarEntity> cars) {
		this.name = name;
		this.country = country;
		this.date = date;
		this.cars = cars;
	}
}
