package org.tarena.netctoss.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.netctoss.dao.AccountDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Account;

public class TestAccountDAO {
	public static AccountDAO dao;
	static{ 
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext-component.xml");
		dao = (AccountDAO) ac.getBean("accountDAO");
	}
	@Test
	public void findIdByidcard() throws DAOException{
		System.out.println(dao.findIdByidcard("330902197108270429"));
	}
	@Test
	public void findPage() throws DAOException{
		System.out.println(dao.findPage("33", null, null, "1", 1, 5));
	}
	@Test
	public void totalPages() throws DAOException{
		System.out.println(dao.totalPages("33", null, null, "1", 5));
	}
	@Test
	public void findByIdcard() throws DAOException{
		System.out.println(dao.findByIdcard("330902197108270429"));
	}
	@Test
	public void findById() throws DAOException{
		System.out.println(dao.findById(1005));
	}
	@Test
	public void update() throws DAOException{
		Account a = dao.findById(1005);		
		System.out.println("Before Modify:"+a);
		a.setZipcode("500041");
		a.setQq("10000");
		dao.update(a);
		System.out.println("After Modify:"+a);
	}
	@Test
	public void setStatus() throws DAOException{
		Account a1 = dao.findById(1081);		
		dao.setStatus(1081, "0");
		Account a2 = dao.findById(1081);
		System.out.println("Before Modify:"+a1);
		System.out.println("After Modify:"+a2);
	}
	@Test
	public void insert() throws DAOException{
		Account a = dao.findById(1081);
		a.setRealName("¥Ù‘Ù");
		a.setIdcardNo("401123198802031246");
		a.setGender("0");
		a.setCreateDate(new Date(System.currentTimeMillis()));
		a.setLoginName("daizei");
		dao.insert(a);
		System.out.println(dao.findByIdcard("401123198802031246"));
	}
	@Test
	public void setLastLogin() throws DAOException{
		Account a1 = dao.findById(1081);
		dao.setLastLogin(1081, "192.168.0.2");
		Account a2 = dao.findById(1081);
		System.out.println("Before Modify:"+a1);
		System.out.println("After Modify:"+a2);
	}
	@Test
	public void findPwdByName() throws DAOException{
		System.out.println("pwd:"+dao.findPwdByName("zhangsan"));
	}
}
