package com.xd.dao;
import com.xd.entity.*;
import com.xd.shiro.ShiroLoginUtil;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tianxi on 16-6-24.
 */
@Repository("guardianDao")
@Transactional
public class GuardianDao extends HibernateDaoSupport{

    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactoryOverride(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    //
    public int addUser(Guardian_user_profile user){
        System.out.println("addUser");
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        session.clear();
        return user.getId();
    }

    //删除用户，下线用户
    public int deleteUser(int id){
        Guardian_user_profile user = getUserByid(id);
        getHibernateTemplate().delete(user);
        return 1;
    }
    public int addGuardianer(Guardian_guardianer user){
        System.out.println("addGuardianer");

        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        session.clear();
        return user.getId();
    }

    //
    public List<Guardian_user_profile> getGuardian_user_profile_List(){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Guardian_user_profile";
        Query query = session.createQuery(hql);

        return query.list();
    }

    //id获取用户
    public Guardian_user_profile getUserByid(int id){
        return getHibernateTemplate().get(Guardian_user_profile.class,id);
    }

    public Guardian_guardianer getGuardianerByid(int id){
        return getHibernateTemplate().get(Guardian_guardianer.class,id);
    }

    //根据ring_id获取用户
    public Guardian_user_profile getUserByRingid(String id){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Guardian_user_profile";
        Query query = session.createQuery(hql);
//        query.setEntity("id", id);
        List<Guardian_user_profile> list=query.list();
        for(Guardian_user_profile user:list){
            if(user.getRing_id().equals(id))
                return user;
        }
        return null;
    }
    //得到平板ip
    public Guardian_hostip getHostipByid(int id){
        return getHibernateTemplate().get(Guardian_hostip.class,id);
    }
}
