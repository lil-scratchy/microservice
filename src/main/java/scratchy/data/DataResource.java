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
@RequestMapping("/data")
public class DataResource
{

    @Autowired
    private DataRepository repository;

    @GetMapping("/{name}")
    public ResponseEntity<List<SensorData>> byName(@PathVariable String name)
    {
        return new ResponseEntity<List<SensorData>>(repository.findByName(name), HttpStatus.OK);
    }
}
