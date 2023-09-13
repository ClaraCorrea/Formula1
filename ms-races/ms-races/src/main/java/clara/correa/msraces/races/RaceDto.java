package clara.correa.msraces.races;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import clara.correa.mscars.cars.CarEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor
@JsonSerialize
public class RaceDto implements Serializable {
    private String name;
    private String country;
    private Date date;
    private List<CarEntity> cars;
}