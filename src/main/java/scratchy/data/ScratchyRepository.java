package scratchy.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ScratchyRepository
    extends CrudRepository<SensorData, Long>
{

    public List<SensorData> findByName(String name);
}
