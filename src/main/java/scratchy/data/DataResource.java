package scratchy.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("devices/{deviceId}/data")
public class DataResource
{

    @Autowired
    private DataRepository repository;

    @GetMapping("{dataName}")
    public ResponseEntity<List<SensorData>> byName(@PathVariable("deviceId") Long deviceId, @PathVariable("dataName") String dataName)
    {
        return new ResponseEntity<List<SensorData>>(repository.findByNameAndDevice_id(dataName, deviceId), HttpStatus.OK);
    }
}
