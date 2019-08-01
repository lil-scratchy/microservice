package scratchy.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Accessors(chain = true)
@FieldNameConstants
@Entity
public class SensorData
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   id;

    @NotEmpty(message = "Data name cannot be empty.")
    private String name;

    @NotNull(message = "Data value cannot be null.")
    private Double value;

    @CreatedDate
    private Date   created;
}
