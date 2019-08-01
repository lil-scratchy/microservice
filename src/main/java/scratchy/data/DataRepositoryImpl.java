package scratchy.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.util.MultiValueMap;

public class DataRepositoryImpl
    implements DataRepositoryCustom
{

    private static final String            PAGE_NUMBER = "pageNumber";
    private static final String            PAGE_SIZE   = "pageSize";

    private static final Class<SensorData> entityType  = SensorData.class;

    @PersistenceContext
    private EntityManager                  entityManager;

    @Transactional
    @Override
    public List<SensorData> search(MultiValueMap<String, String> queryParameters)
    {
        return createQuery(queryParameters).getResultList();
    }

    private TypedQuery<SensorData> createQuery(MultiValueMap<String, String> queryParameters)
    {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<SensorData> criteria = builder.createQuery(SensorData.class);
        Root<SensorData> root = criteria.from(entityType);
        criteria.select(root);
        List<Predicate> restrictions = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        int pageNumber = 1;
        int pageSize = 10;
        for (Entry<String, List<String>> entry : queryParameters.entrySet()) {
            String key = entry.getKey();
            setValue(queryParameters, builder, root, restrictions, key);
            pageSize = PAGE_SIZE.equalsIgnoreCase(key) ? intValue(queryParameters.get(key).get(0), pageSize) : pageSize;
            pageNumber = PAGE_NUMBER.equalsIgnoreCase(key) ? intValue(queryParameters.get(key).get(0), pageNumber) : pageNumber;
        }
        if (!restrictions.isEmpty()) {
            criteria.where(restrictions.size() > 1 ? builder.and(restrictions.toArray(new Predicate[0])) : restrictions.get(0));
        }
        criteria.orderBy(orders.isEmpty() ? defaultOrder(builder, root) : orders);
        TypedQuery<SensorData> query = entityManager.createQuery(criteria);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query;
    }

    /**
     * Get the integer value of a string.
     * 
     * @return defaultValue when string cannot be formatted.
     */
    private int intValue(String string, int defaultValue)
    {
        try {
            return Integer.valueOf(string);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * Order by id by default.
     * 
     * @return List with single {@link Order} entry.
     */
    private List<Order> defaultOrder(CriteriaBuilder builder, Root<SensorData> root)
    {
        List<Order> order = new ArrayList<>();
        order.add(builder.asc(root.get("id")));
        return order;
    }

    private void setValue(Map<String, List<String>> queryParameters, CriteriaBuilder builder, Root<SensorData> root, List<Predicate> restrictions,
                          String key)
    {
        if (isAttribute(root, key)) {
            for (String value : queryParameters.get(key)) {
                if (isStringAttribute(root, key)) {
                    restrictions.add(builder.like(root.get(key), wildcard(value)));
                }
                else {
                    restrictions.add(builder.equal(root.get(key), value));
                }
            }
        }
    }

    /**
     * Check whether attribute is of type string.
     * 
     * @param root Representation of the java object.
     * @param attributeName Name of the Attribute.
     */
    private boolean isStringAttribute(Root<SensorData> root, String attributeName)
    {
        try {
            Path<Object> path = root.get(attributeName);
            return path.getJavaType().isAssignableFrom(String.class);
        } catch (IllegalArgumentException exception) {
            return false;
        }
    }

    /**
     * Check whether object contains given attribute.
     * 
     * @param root Representation of the java object.
     * @param attributeName Name of the Attribute.
     */
    private boolean isAttribute(Root<SensorData> root, String attributeName)
    {
        try {
            root.get(attributeName);
        } catch (IllegalArgumentException exception) {
            return false;
        }
        return true;
    }

    /**
     * Surround string with '%' wildcards for SQL query.
     * 
     * @param string String with missing wildcards, e.g. '%duck'
     * @return String with wildcards, e.g. '%duck%'
     */
    private static String wildcard(String string)
    {
        if (!string.startsWith("%")) {
            string = "%" + string;
        }
        if (!string.endsWith("%")) {
            string = string + "%";
        }
        return string;
    }
}
