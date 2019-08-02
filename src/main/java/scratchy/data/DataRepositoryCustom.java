package scratchy.data;

import java.util.List;

import org.springframework.util.MultiValueMap;

public interface DataRepositoryCustom
{

    public List<SensorData> search(MultiValueMap<String, String> queryParameters);
}
