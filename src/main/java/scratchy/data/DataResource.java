package scratchy.data;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scratchy.device.Device;
import scratchy.device.DeviceRepository;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = {"origin", "content-type", "accept", "authorization"}, methods = {GET, POST, PUT,
        DELETE, OPTIONS, HEAD})
@RestController
@RequestMapping("devices/{deviceId}/data")
public class DataResource
{

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DataRepository   dataRepository;

    @GetMapping("{dataName}")
    public ResponseEntity<List<SensorData>> byName(@PathVariable("deviceId") Long deviceId, @PathVariable("dataName") String dataName)
    {
        return new ResponseEntity<List<SensorData>>(dataRepository.findByNameAndDevice_idOrderByCreatedDesc(dataName, deviceId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SensorData> create(@PathVariable("deviceId") Long deviceId, @RequestBody SensorData data)
    {
        Optional<Device> device = deviceRepository.findById(deviceId);
        if (!device.isPresent()) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        data.setDevice(device.get());
        return new ResponseEntity<>(dataRepository.save(data), HttpStatus.CREATED);
    }
}
