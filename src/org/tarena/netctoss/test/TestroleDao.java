package org.tarena.netctoss.test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.dao.RoleDAO;
import org.tarena.netctoss.pojo.Role;
import org.tarena.netctoss.pojo.RolePrivilege;
import org.tarena.netctoss.pojo.RolePrivilegeId;

public class TestroleDao {
	public static RoleDAO dao;
	static {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		dao = (RoleDAO) ac.getBean("roleDAO");
	}
	
	@Test
	public void insert() throws DAOException{
		Role role = new Role();
		role.setName("效率123");
		for (int i = 1; i < 4; i++) {
			role.getPrivilegeIds().add(i);
		}
		dao.insert(role);		
	}
	
	public static void testUpdate() throws DAOException {
		dao.findById(1);
	}
	public static void findall() throws DAOException{
		List<Role> list = dao.findAll();
		for (Role r:list) {
			System.out.println(r.getId()+":"+r.getName());
		}
	}
	
	public static void findpage() throws DAOException{
		List<Role> list = dao.findPage(2, 2);
		for (Role r:list) {
			System.out.println(r.getId()+":"+r.getName());
		}
	}
	
	public static void findByid() throws DAOException{
		Role role = dao.findById(1);
		System.out.println(role.getId()+":"+role.getName());
		for (Object aa:role.getPrivilegeIds()){
			System.out.println(aa+"   "+aa.getClass());
		}
//		for (AdminInfo admin:role.getAdmins()){
//			System.out.println(admin.getId()+","+admin.getName());
//		}
	
	}
	public static void getRoleIdByPrivilegeId() throws DAOException{
		System.out.println(dao.getRoleIdByPrivilegeId(1));
	}	
	
	
	public static void update() throws DAOException{
		Role role = new Role();
		role.setName("啥速度");
		role.setId(146);
		Set<RolePrivilege> privilegeIds = new HashSet<RolePrivilege>();
		for (int i = 1; i < 4; i++) {
			role.getPrivilegeIds().add(i);
		}	

		dao.update(role);
	}
	
	public static void delete() throws DAOException{
		dao.delete(150);
	}
	
	public static void totalPages() throws DAOException{
		Object obj = dao.totalPages(2);
		System.out.println(obj);
		System.out.println(obj.getClass());
	}
	

}
