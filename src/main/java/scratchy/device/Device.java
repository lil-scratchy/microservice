package scratchy.device;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import scratchy.data.SensorData;

@Data
@Accessors(chain = true)
@FieldNameConstants
@Entity
public class Device
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long             id;

    @NaturalId
    @NotEmpty(message = "Data name cannot be empty.")
    private String           name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "Location has to be set.")
    private Location         location;

    @JsonIgnore
    @OneToMany(fetch = EAGER, orphanRemoval = true, cascade = ALL, mappedBy = SensorData.Fields.device)
    private List<SensorData> data;
}
