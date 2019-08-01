package scratchy.device;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.HEAD;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = {"origin", "content-type", "accept", "authorization"}, methods = {GET, POST, PUT,
        DELETE, OPTIONS, HEAD})
@RestController
@RequestMapping("devices")
public class DeviceResource
{

    @Autowired
    private DeviceRepository repository;

    @GetMapping
    public Iterable<Device> all()
    {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Device> create(@RequestBody Device device)
    {
        return new ResponseEntity<>(repository.save(device), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Device> read(@PathVariable Long id)
    {
        Optional<Device> device = repository.findById(id);
        if (device.isPresent()) {
            return new ResponseEntity<>(device.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Device> update(@PathVariable Long id, @RequestBody Device device)
    {
        Optional<Device> existingDevice = repository.findById(id);
        if (!existingDevice.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(repository.save(device), HttpStatus.OK);
    }
}
