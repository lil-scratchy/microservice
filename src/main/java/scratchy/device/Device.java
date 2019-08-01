package scratchy.device;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

@Data
@Accessors(chain = true)
@FieldNameConstants
@Entity
public class Device
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long     id;

    @NaturalId
    @NotEmpty(message = "Data name cannot be empty.")
    private String   name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull(message = "Location has to be set.")
    private Location location;
}
