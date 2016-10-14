package com.hbm.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
//import org.hibernate.cfg.Configuration;

import com.hbm.model.*;

public class UserDAO extends DAO {
	//private User user;
	//private Activity act;
	
	//private static final Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
	//private static final StandardServiceRegistryBuilder srb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
	//private static final SessionFactory factory = cfg.buildSessionFactory(srb.build());
	
	public String addUser(String username,String password,boolean sex,String nickname)
	{
		//�����µ��û����������ʧ�ܻ������ظ����û�������null�����򷵻�id
		Session hibernateSession = factory.openSession();
		Transaction transaction = hibernateSession.beginTransaction();
		
		User user=new User();
		user.setUserName(username);
		user.setPassword(password);
		user.setSex(sex);
		user.setNickname(nickname);
		
		hibernateSession.save(user);
		String hql = "SELECT U.id FROM User U WHERE U.userName = :username";
		Query query = hibernateSession.createQuery(hql);
		query.setParameter("username",username);
		List results = query.list();
		
		String resultString = null;
		if (results.size() == 1)
			resultString = results.get(0).toString();
		transaction.commit();
		hibernateSession.close();
		return resultString;
	}
	public String findUser(String username,String password)
	{
		Session hibernateSession = factory.openSession();
		Transaction transaction = hibernateSession.beginTransaction();
		
		String hql = "SELECT U.id FROM User U WHERE U.userName = :username and U.password = :password";
		Query query = hibernateSession.createQuery(hql);
		query.setParameter("username",username);
		query.setParameter("password",password);
		List results = query.list();
		
		String resultString = null;
		if (results.size()>0)
			resultString = results.get(0).toString();
		transaction.commit();
		hibernateSession.close();
		return resultString;
	}
	public User getUser(String userId)
	{
		Session hibernateSession = factory.openSession();
		Transaction transaction = hibernateSession.beginTransaction();
		
		String hql = "FROM User U WHERE U.id=:userId";
		Query query = hibernateSession.createQuery(hql);
		query.setParameter("userId",userId);
		
		@SuppressWarnings("unchecked")
		List<User> results = query.list();
		System.out.println(results.size());
		User user = null;
		if (results.size()>0)
			user = results.get(0);
		transaction.commit();
		hibernateSession.close();
		return user;
	}
	public void joinActivity(String userId, String activityId)
	{
		Session hibernateSession = factory.openSession();
		Transaction transaction = hibernateSession.beginTransaction();
		
		ActivityDAO actDao=new ActivityDAO();
		Activity act = actDao.getActivity(activityId);
		MemberDAO memDao = new MemberDAO();
		String memberId = memDao.addMember(userId, activityId);
		Member member = memDao.getMember(memberId);
		User user = getUser(userId);
		user.getJoinedActivity().add(act);
		act.getMembers().add(member);
		
		hibernateSession.update(user);
		hibernateSession.update(act);
		
		transaction.commit();
		hibernateSession.close();
	}
	public void ownActivity(String userId, String activityId)
	{
		Session hibernateSession = factory.openSession();
		Transaction transaction = hibernateSession.beginTransaction();
		
		ActivityDAO actDao=new ActivityDAO();
		Activity act = actDao.getActivity(activityId);
		User user = getUser(userId);
		act.setOwner(user);
		user.getOwnActivity().add(act);
		
		hibernateSession.update(user);
		//hibernateSession.update(act);
		
		transaction.commit();
		hibernateSession.close();
	}
	public void updateUser(){}
	public void deleteUser(String userId){}
}