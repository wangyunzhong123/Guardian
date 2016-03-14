package com.xd.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by tianxi on 16-3-14.
 */
@Repository("loginDao")
@Transactional
public class LoginDao {

    @Autowired
    @Qualifier("sessionFactory")
    SessionFactory sessionFactory;

}
