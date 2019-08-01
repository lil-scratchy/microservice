package scratchy.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DataRepository
    extends CrudRepository<SensorData, Long>, DataRepositoryCustom
{

    public List<SensorData> findByNameAndDevice_idOrderByCreatedDesc(String name, Long id);

    @Query(value = "select * from (SELECT sd.name, MAX(created) as last_data from sensor_data as sd where sd.device_id = ?1 group by sd.name) as last_data2 inner join sensor_data on sensor_data.name = last_data2.name and sensor_data.created = last_data", nativeQuery = true)
    public List<SensorData> findLatest(Long deviceId);
}
