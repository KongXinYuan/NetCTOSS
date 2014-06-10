package org.tarena.netctoss.dao.hibernate;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.tarena.netctoss.dao.AccountDAO;
import org.tarena.netctoss.dao.DAOException;
import org.tarena.netctoss.pojo.Account;
import org.tarena.netctoss.pojo.Service;
/**封装对account表的操作*/
@Repository("accountDAO")
public class AccountDAOImp extends BaseDAO implements AccountDAO {
	//根据idcardNo，realName,loginName,status四项属性进行分页查询
	@SuppressWarnings("unchecked")
	public List<Account> findPage(final String idcardNo, final String realName,
			final String loginName, final String status, final Integer page, 
			final Integer pageSize)	throws DAOException {
		List<Account> list = getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session) throws HibernateException,
							SQLException {
						StringBuilder sql = new StringBuilder("from Account where ");
						List<Object> params = new ArrayList<Object>();
						//状态查询，-1为查找全部状态
						if (status == null || status.equals("-1")) {
							sql.append("1=1");
						} else {
							sql.append("status=?");
							params.add(status);
						}
						//身份证查询
						if (idcardNo != null && !"".equals(idcardNo)) {
							sql.append(" and idcardNo like ?");
							params.add("%" + idcardNo + "%");
						}
						//真实姓名查询
						if (realName != null && !"".equals(realName)) {
							sql.append(" and realName like ?");
							params.add("%" + realName + "%");
						}
						//登录名查询
						if (loginName != null && !"".equals(loginName)) {
							sql.append(" and loginName like ?");
							params.add("%" + loginName + "%");
						}
						Query query = session.createQuery(sql.toString());
						//为各条件赋值
						for (int i = 0; i < params.size(); i++) {
							query.setParameter(i,params.get(i));
						}
						//设置分页
						int begin = (page - 1) * pageSize;
						query.setFirstResult(begin);
						query.setMaxResults(pageSize);
						List<Account> list = query.list();
						return list;
					}
				});
		return list;		
	}

	//统计页数
	public Integer totalPages(final String idcardNo, final String realName,
			final String loginName, final String status, final Integer pageSize)
			throws DAOException {
		Integer totalPages = (Integer) getHibernateTemplate().execute(new HibernateCallback() {			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				StringBuilder sql = new StringBuilder("select count(id) from Account where ");
				List<Object> params = new ArrayList<Object>();
				//设置查询条件
				if (status == null || status.equals("-1")) {
					sql.append("1=1");
				} else {
					sql.append("status=?");
					params.add(status);
				}
				if (idcardNo != null && !"".equals(idcardNo)) {
					sql.append(" and idcardNo like ?");
					params.add("%" + idcardNo + "%");
				}
				if (realName != null && !"".equals(realName)) {
					sql.append(" and realName like ?");
					params.add("%" + realName + "%");
				}
				if (loginName != null && !"".equals(loginName)) {
					sql.append(" and loginName like ?");
					params.add("%" + loginName + "%");
				}
				Query query = session.createQuery(sql.toString());
				for (int i = 0; i < params.size(); i++) {
					query.setParameter(i,params.get(i));			
				}
				Object obj = query.uniqueResult();
				int totalPages = 1;
				//获取记录数并计算页数
				int count = Integer.parseInt(obj.toString());
				if (count % pageSize == 0){
					totalPages = count / pageSize;
				} else {
					totalPages = count / pageSize + 1;
				}
				return totalPages;
			}
		});
		return totalPages;
	}

	//根据ID查询记录
	public Account findById(Integer id) throws DAOException {
		Account account = (Account) getHibernateTemplate().get(Account.class, id);
		return account;
	}
	
	//根据身份证查询记录
	public Account findByIdcard(String idcard) throws DAOException {
		String hql = "from Account where idcardNo=?";		
		List list = getHibernateTemplate().find(hql, idcard);
		if (!list.isEmpty()) {
			return (Account) list.get(0);
		}
		return null;
	}

	//更新数据
	public void update(Account a) throws DAOException {
		getHibernateTemplate().update(a);
	}

	//设置状态status
	public void setStatus(Integer id, String status) throws DAOException {
		Account a = (Account) getHibernateTemplate().get(Account.class, id);		
		a.setStatus(status);
		//开通是清除暂停时间
		if ("0".equals(status)) {
			a.setCloseDate(null);
			a.setPauseDate(null);
		} else if ("1".equals(status)) {
			//暂停时写入暂停时间
			a.setPauseDate(new Date(System.currentTimeMillis()));
		} else if ("2".equals(status)) {
			//删除写入删除时间
			a.setCloseDate(new Date(System.currentTimeMillis()));
		}
		getHibernateTemplate().update(a);
	}

	//插入新数据
	public void insert(Account a) throws DAOException {
		getHibernateTemplate().save(a);

	}

	//更新最后登入时间和IP
	public void setLastLogin(Integer id, String lastLoginIp)
			throws DAOException {
		Account a = (Account) getHibernateTemplate().get(Account.class, id);
		a.setLastLoginTime(new Date(System.currentTimeMillis()));//设置登录时间
		a.setLastLoginIp(lastLoginIp);//设置登录IP
		getHibernateTemplate().update(a);
	}

	//根据登录名查找密码
	public String findPwdByName(String loginName) throws DAOException {
		String hql = "select loginPasswd from Account where loginName=?";
		List list = getHibernateTemplate().find(hql,loginName);
		if (!list.isEmpty()) {
			return (String)list.get(0);
		}
		return null;
	}

	//根据身份证查ID
	public Integer findIdByidcard(String idcard) throws DAOException {
		String hql = "select id from Account where idcardNo=?";
		List list = getHibernateTemplate().find(hql,idcard);
		if (!list.isEmpty()){
			return (Integer) list.get(0);
		}
		return null;
	}

	//设置account表关联的service记录状态
	public void setServiceStatusByAccountId(Integer accountId, String status)
			throws DAOException {
		Account a = (Account) getHibernateTemplate().get(Account.class, accountId);
		for (Service s:a.getServices()) {
			s.setStatus(status);
			//状态为开启时，清除关闭，和暂停时间
			if ("0".equals(status)) {
				s.setCloseDate(null);
				s.setPauseDate(null);
			} else if ("1".equals(status)) {
				//状态为暂停时，添加暂停时间
				s.setPauseDate(new Date(System.currentTimeMillis()));
			} else if ("2".equals(status)) {
				//状态为删除是，添加删除时间
				s.setCloseDate(new Date(System.currentTimeMillis()));
			}
			getHibernateTemplate().save(s);
		}
	}


}
