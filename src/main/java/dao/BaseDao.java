package dao;
import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

/**
 * Hibernate通用的持久层接口
 *
 */
public interface BaseDao<T> {
    /**
     * 保存
     *
     * @param entity
     *
     * @return oid
     */
    public Serializable save(T entity);

    /**
     * 更新
     *
     * @param entity
     */
    public void update(T entity);

    /**
     * 保存或更新
     *
     * @param entity
     */
    public void saveOrUpdate(T entity);

    /**
     * 删除
     *
     * @param entity
     */
    public void delete(T entity);

    /**
     * description:
     * create time: 9:06 2018/6/8
     * 
      * @Param: null
     * @return 
     */
    public void deleteAll(List<T> entity);
    
    /**
     * 通过对象标识符获取对象
     *
     * @param oid
     * @return 标识符对应的对象，没找大则返回null
     */


    public T findById(Serializable oid);

    /**
     * 返回所有对象的列表
     *
     * @return
     */
    public List<T> findAll();

    /**
     * 查找满足条件的总记录数
     *
     * @param detachedCriteria
     * @return
     */
    Integer findRecordNumByPage(DetachedCriteria detachedCriteria);

    /**
     * 向分页对象中设置记录
     *
     * @param detachedCriteria
     *            离线查询对象
     * @param startIndex
     *            开始索引
     * @param pageSize
     *            每页记录数
     * @return
     */
    List<T> findByPage(DetachedCriteria detachedCriteria, Integer startIndex, Integer pageSize);

    /**
     * 通过条件查询
     *
     * @param detachedCriteria
     * @return
     */
    List<T> findByCriteria(DetachedCriteria detachedCriteria);

    /**
     * 通用更新方法
     *
     * @param queryName
     *            命名查询的名字，在映射文件中定义
     * @param objects
     *            参数列表
     */
    public void executeUpdate(String queryName, Object... objects);
}
