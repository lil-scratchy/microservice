package scratchy.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DataRepository
    extends CrudRepository<SensorData, Long>
{

    public List<SensorData> findByNameAndDevice_id(String name, Long id);
}
