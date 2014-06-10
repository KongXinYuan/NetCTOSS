package org.tarena.netctoss.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.CostDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Cost;
@Repository("costDAO")
public class CostDAOImp extends BaseDAO implements CostDAO {

	public List<Cost> findAll() throws DAOException {
		String hql = "from Cost where status='0'";
		List<Cost> list = getHibernateTemplate().find(hql);
		return list;
	}
	//sortNo排序编号1x：月租，2x：基费，3x：时长；x1：升序，x2：降序
	@SuppressWarnings("unchecked")
	public List<Cost> findPage(final Integer page, final Integer pageSize, final Integer sortNo) throws DAOException {
		List<Cost> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "from Cost";
				if (sortNo / 10 == 1){
					hql = hql + " order by baseCost ";
				} else if (sortNo / 10 == 2) {
					hql = hql + " order by unitCost ";
				} else if (sortNo / 10 == 3) {
					hql = hql + " order by baseDuration ";
				}
				if (sortNo % 10 == 1){
					hql = hql + "asc";
				} else if (sortNo % 10 == 2){
					hql = hql + "desc";
				}
				Query query = session.createQuery(hql);
				Integer begin = (page - 1) * pageSize;
				query.setFirstResult(begin);
				query.setMaxResults(pageSize);
				List<Cost> list = query.list();
				return list;
			}
		});		
		return list;
	}

	//获取页数
	public Integer totalPages(Integer pageSize) throws DAOException {
		String hql = "select count(id) from Cost";
		List list = getHibernateTemplate().find(hql);
		Integer count = Integer.parseInt(list.get(0).toString());
		Integer totalPages = 1;
		if (count % pageSize == 0) {
			totalPages = count / pageSize;
		} else {
			totalPages = count / pageSize + 1;
		}
		return totalPages;
	}

	//添加记录
	public void insert(Cost cost) throws DAOException {
		System.out.println("**********************************"+cost.getId());
		getHibernateTemplate().save(cost);
	}

	//更新记录信息
	public void update(Cost cost) throws DAOException {
		getHibernateTemplate().update(cost);
	}

	//删除记录
	public void delete(Integer id) throws DAOException {
		Cost cost = (Cost) getHibernateTemplate().get(Cost.class, id);
		getHibernateTemplate().delete(cost);
	}

	//根据ID查记录
	public Cost findById(Integer id) throws DAOException {
		Cost cost = (Cost) getHibernateTemplate().get(Cost.class, id);
		return cost;
	}

	//根据资费名查记录
	public Cost findByName(String name) throws DAOException {
		String hql = "from Cost where name = ?";
		List list = getHibernateTemplate().find(hql,name);
		if (!list.isEmpty()){
			return (Cost)list.get(0);
		}
		return null;
	}

}
