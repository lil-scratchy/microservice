package scratchy.data;

import static javax.persistence.FetchType.LAZY;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import scratchy.device.Device;

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

    @ManyToOne(fetch = LAZY, optional = false)
    private Device device;

    @CreatedDate
    private Date   created;
}
