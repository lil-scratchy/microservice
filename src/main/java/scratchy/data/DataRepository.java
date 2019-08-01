package scratchy.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DataRepository
    extends CrudRepository<SensorData, Long>, DataRepositoryCustom
{

    public List<SensorData> findByNameAndDevice_idOrderByCreatedDesc(String name, Long id);
}
