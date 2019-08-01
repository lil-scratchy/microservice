package scratchy.device;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Accessors(chain = true)
@FieldNameConstants
@Entity
public class Location
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   id;

    @NotNull(message = "Latitude has to be set.")
    private Double latitude;

    @NotNull(message = "Longitude has to be set.")
    private Double longitude;
}
