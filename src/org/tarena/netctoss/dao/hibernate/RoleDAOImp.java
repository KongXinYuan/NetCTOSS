package org.tarena.netctoss.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.RoleDAO;
import org.tarena.netctoss.pojo.Role;
import org.tarena.netctoss.pojo.RolePrivilege;
import org.tarena.netctoss.pojo.RolePrivilegeId;
@Repository("roleDAO")
public class RoleDAOImp extends BaseDAO implements RoleDAO {
	
	@SuppressWarnings("unchecked")
	public List<Role> findAll() throws DAOException {
		String hql = "from Role";
		List<Role> list = getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Role> findPage(final Integer page, final Integer pageSize)
			throws DAOException {
		List<Role> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery("from Role");
				Integer begin = (page - 1) * pageSize;
				query.setFirstResult(begin);
				query.setMaxResults(pageSize);
				List<Role> list = query.list();
				for (Role role:list) {
					Query query1 = session.createSQLQuery("select PRIVILEGE_ID from ROLE_PRIVILEGE where ROLE_ID=?").addScalar("PRIVILEGE_ID",Hibernate.INTEGER);
					query1.setInteger(0, role.getId());
					List<Integer> privilegeIds = query1.list();
					role.setPrivilegeIds(privilegeIds);
				}
				return list;
			}
		});
		return list;
	}

	public Role findById(Integer id) throws DAOException {
		
		Role role = (Role) getHibernateTemplate().get(Role.class, id);
		String hql = "from RolePrivilege r where r.id.roleId=?";		
		List<RolePrivilege> list = getHibernateTemplate().find(hql,role.getId());
		List<Integer> privilegeIds = new ArrayList<Integer>();
		for (RolePrivilege rp:list) {
			privilegeIds.add(rp.getId().getPrivilegeId());
		}
		role.setPrivilegeIds(privilegeIds);
		return role;
	}

	public void update(final Role role) throws DAOException {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery("delete from RolePrivilege where id.roleId=?");
				query.setInteger(0, role.getId());
				query.executeUpdate();
				Role r = (Role) session.get(Role.class, role.getId());
				r.setName(role.getName());
				session.update(r);
				for (Integer privilegeId:role.getPrivilegeIds()){
					RolePrivilege rp = new RolePrivilege(new RolePrivilegeId(r.getId(), privilegeId));
					session.save(rp);
				}
				return null;
			}
		});
	}

	public void delete(final Integer id) throws DAOException {
		getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Role role = (Role) session.get(Role.class, id);		
				Query query = session.createQuery("delete from RolePrivilege where id.roleId=?");
				query.setInteger(0, id);
				query.executeUpdate();
				session.delete(role);
				return null;
			}
		});		
	}

	public void insert(Role role) throws DAOException {
		getHibernateTemplate().save(role);
		for (Integer privilegeId:role.getPrivilegeIds()){
			RolePrivilege rp = new RolePrivilege(new RolePrivilegeId(role.getId(), privilegeId));
			getHibernateTemplate().save(rp);
		}
	}

	public Integer totalPages(Integer pageSize) throws DAOException {
		String Hql = "select count(id) from Role";
		List list = getHibernateTemplate().find(Hql);
		int count = Integer.parseInt(list.get(0).toString());
		int totalPages = 0;
		if (count % pageSize == 0) {
			totalPages = count / pageSize;
		} else {
			totalPages = count / pageSize + 1;
		}
		return totalPages;
	}


	public Role findByName(String name) throws DAOException {
		String hql = "from Role where name=?";
		List list = getHibernateTemplate().find(hql, name);
		if (list.isEmpty()){
			return null;
		} else {
			Role role = (Role) list.get(0);
			String hql2 = "from RolePrivilege r where r.id.roleId=?";		
			List<RolePrivilege> list2 = getHibernateTemplate().find(hql,role.getId());
			List<Integer> privilegeIds = new ArrayList<Integer>();
			for (RolePrivilege rp:list2) {
				privilegeIds.add(rp.getId().getPrivilegeId());
			}
			role.setPrivilegeIds(privilegeIds);
			return role;
		}
	}

	public List<Integer> getRoleIdByPrivilegeId(Integer privilegeId)
			throws DAOException {
		String hql = "from RolePrivilege where id.privilegeId=?";
		List<RolePrivilege> list = getHibernateTemplate().find(hql,privilegeId);
		List<Integer> list1 = new ArrayList<Integer>();
		for (RolePrivilege rp:list) {
			list1.add(rp.getId().getRoleId());
		}
		return list1;
	}


}
