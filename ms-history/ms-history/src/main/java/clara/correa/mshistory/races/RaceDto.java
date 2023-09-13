package clara.correa.mshistory.races;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import clara.correa.mshistory.cars.CarEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor
@NoArgsConstructor
public class RaceDto implements Serializable{
    private String name;
    private String country;
    private Date date;
    private List<CarEntity> cars;
}