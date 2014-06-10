package org.tarena.netctoss.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.AdminInfoDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.AdminInfo;
/**封装对admin_info表的操作*/
@Repository("adminInfoDAO")
public class AdminInfoDAOImp extends BaseDAO implements AdminInfoDAO {
	//插入新记录
	public void insert(AdminInfo admin) throws DAOException {				
		getHibernateTemplate().save(admin);
	}

	//分页查询
	@SuppressWarnings("unchecked")
	public List<AdminInfo> findPage(final Integer page, final Integer pageSize)
			throws DAOException {
		List<AdminInfo> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery("from AdminInfo");
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
				return query.list();
			}
		});
		return list;
	}

	//按ID查询记录
	public AdminInfo findById(Integer id) throws DAOException {
		return (AdminInfo) getHibernateTemplate().get(AdminInfo.class, id);
	}

	//按admin_code查记录
	public AdminInfo findByAdminCode(String adminCode) throws DAOException {
		String hql = "from AdminInfo where adminCode=?";
		List list = getHibernateTemplate().find(hql, adminCode);
		if (!list.isEmpty()){
			return (AdminInfo) list.get(0);
		}
		return null;
	}

	//按id修改密码
	public void updatePwdById(Integer id, String pwd) throws DAOException {
		AdminInfo admin = (AdminInfo) getHibernateTemplate().get(AdminInfo.class, id);
		admin.setPassword(pwd);
		getHibernateTemplate().update(admin);
	}

	//更新记录
	public void update(AdminInfo admin) throws DAOException {
		getHibernateTemplate().update(admin);
	}

	//删除记录
	public void delete(Integer id) throws DAOException {
		AdminInfo admin = (AdminInfo) getHibernateTemplate().get(AdminInfo.class, id);		
		getHibernateTemplate().delete(admin);
	}

	//统计页数
	public Integer totalPages(Integer pageSize) throws DAOException {
		String hql = "select count(id) from AdminInfo";
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

	//根据角色名查找记录
	@SuppressWarnings("unchecked")
	public List<AdminInfo> findRole(final String roleName, final Integer page,
			final Integer pageSize) throws DAOException {
		List<AdminInfo> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				String hql = "select a from AdminInfo a join a.roles r where r.name like ?";
				Query query = session.createQuery(hql);
				query.setString(0, "%" + roleName + "%");
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
				List<Integer> list = query.list();			
				return list;
			}
		});
		return list;
	}
	
	public Integer totalPagesRole(String roleName, Integer pageSize) throws DAOException {
		String hql = "select count(a.id) from AdminInfo a join a.roles r where r.name like ?";
		List list = getHibernateTemplate().find(hql,roleName);
		Integer count = Integer.parseInt(list.get(0).toString());
		Integer totalPages = 1;
		if (count % pageSize == 0) {
			totalPages = count / pageSize;
		} else {
			totalPages = count / pageSize + 1;
		}
		return totalPages;
	}

	//根据权限查找记录
	@SuppressWarnings("unchecked")
	public List<AdminInfo> findRole(final List<Integer> roleIds, final Integer page,
			final Integer pageSize) throws DAOException {
		if (roleIds == null || roleIds.size() == 0) {
			return null;
		}
		List<AdminInfo> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				List<Integer> param = new ArrayList<Integer>();
				StringBuilder hql = new StringBuilder("select distinct(a) from AdminInfo a join a.roles r where r.id in (");
				for (int i = 0; i < roleIds.size(); i++){
					if (i > 0) {
						hql.append(",");
					}
					hql.append("?");
					param.add(roleIds.get(i));
				}
				hql.append(")");
				Query query = session.createQuery(hql.toString());
				for (int i = 0; i < param.size(); i++){
					query.setInteger(i, param.get(i));
				}
				query.setFirstResult((page - 1) * pageSize);
				query.setMaxResults(pageSize);
				List<AdminInfo> list = query.list();
				return list;
			}
		});
		return list;		
	}	
	
	public Integer totalPagesRole(List<Integer> roleIds, Integer pageSize) throws DAOException {
		if (roleIds == null || roleIds.size() == 0) {
			return null;
		}
		StringBuilder hql = new StringBuilder("select count(a) from AdminInfo a join a.roles r where r.id in (");
		List<Integer> param = new ArrayList<Integer>();
		for (int i = 0; i < roleIds.size(); i++){
			if (i > 0) {
				hql.append(",");
			}
			hql.append("?");
			param.add(roleIds.get(i));
		}
		hql.append(")");
		List list = getHibernateTemplate().find(hql.toString(),param.toArray());
		Integer count = Integer.parseInt(list.get(0).toString());
		Integer totalPages = 1;
		if (count % pageSize == 0) {
			totalPages = count / pageSize;
		} else {
			totalPages = count / pageSize + 1;
		}
		return totalPages;
	}
	
	//根据admin_code重置密码
	public void resetPwd(final String[] adminCodes) throws DAOException {
		if (adminCodes != null && adminCodes.length > 0){
			getHibernateTemplate().execute(new HibernateCallback() {
				public Object doInHibernate(Session session) throws HibernateException,
						SQLException {
					Query query = session.createSQLQuery("update ADMIN_INFO set PASSWORD=? where ADMIN_CODE=?");
					for (String adminCode:adminCodes){
						query.setString(0, adminCode);
						query.setString(1, adminCode);
						query.executeUpdate();
					}
					return null;
				}
			});	
		}
	}

}
