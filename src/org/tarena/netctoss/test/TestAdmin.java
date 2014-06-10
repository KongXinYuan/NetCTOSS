package org.tarena.netctoss.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.AdminInfoDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.AdminInfo;
import org.tarena.netctoss.pojo.Role;

public class TestAdmin {
	static AdminInfoDAO dao;
	static {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-component.xml");
		dao = (AdminInfoDAO) ac.getBean("adminInfoDAO");
	}
	@Test
	public void findPage() throws DAOException{
		List<AdminInfo> list = dao.findPage(1, 10);
		for (AdminInfo admin:list) {
			System.out.println(admin);
		}
	}
	@Test
	public void insert() throws DAOException{
		AdminInfo admin = new AdminInfo();
		admin.setAdminCode("qq4");
		admin.setPassword("qqqq14");
		admin.setName("qqqq13");
		admin.setTelephone("111111111");
		admin.setEmail("aasd@asd.ca");
		admin.setEnrolldate(new Date(System.currentTimeMillis()));
		int[] ids = {1,2,3};
		Set<Role> roles = new HashSet<Role>();
		for (int i:ids){
			Role role = new Role();
			role.setId(i);
			roles.add(role);
		}		
		admin.setRoles(roles);
		dao.insert(admin);
	}
	@Test
	public void update() throws DAOException{
		AdminInfo admin = dao.findById(1029);
		System.out.println(admin);
		admin.setName("sadasd");
		int[] ids = {1,2,3};
		admin.getRoles().clear();
		for (int id:ids){
			Role role = new Role();
			role.setId(id);
			admin.getRoles().add(role);
		}
		dao.update(admin);
	}
	@Test
	public void findRole1() throws DAOException{
		List<AdminInfo> admins = dao.findRole("资费管理员", 1, 10);
		for (AdminInfo admin:admins){
			System.out.print(admin.getId() +","+admin.getName());
//			for (Role role:admin.getRoles()){
//				System.out.print(","+role.getName());
//			}
//			System.out.println();
		}
	}
	@Test
	public void findRole2() throws DAOException{
		List<Integer> list = new ArrayList<Integer>();
//		list.add(1);
		list.add(2);
//		list.add(3);
		List<AdminInfo> admins = dao.findRole(list, 1, 10);
		for (AdminInfo admin:admins){
			System.out.print(admin.getId() +","+admin.getName());
//			for (Role role:admin.getRoles()){
//				System.out.print(","+role.getName());
//			}
//			System.out.println();
		}
	}
	@Test
	public void resetPwd() throws DAOException{
		String[] adminCodes = {"aaaaa","aaaaa1"};
		dao.resetPwd(adminCodes);
	}
	@Test
	public void findId() throws DAOException{
		AdminInfo admin = dao.findById(1031);
		System.out.println(admin);
	}
	@Test
	public void totalPages() throws DAOException{
		System.out.println(dao.totalPages(2));
	}
	@Test
	public void delete() throws DAOException{
		dao.delete(1048);
	}
	
	@Test
	public void updatePwdById() throws DAOException{
		dao.updatePwdById(1003, "111111");
	}
	
	@Test
	public void findByAdminCode() throws DAOException{
		AdminInfo admin = dao.findByAdminCode("aaaaa");
		System.out.println(admin);
	}

}
