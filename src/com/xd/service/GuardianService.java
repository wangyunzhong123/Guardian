package com.xd.service;
import com.xd.dao.GuardianDao;
import com.xd.entity.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * Created by tianxi on 16-6-24.
 */
@Service("guardianService")
public class GuardianService {
    @Resource(name="guardianDao")
    GuardianDao guardianDao;

    public int addUser(Guardian_user_profile user){
        return guardianDao.addUser(user);
    }
    public int deleteUser(int id){
        return guardianDao.deleteUser(id);
    }

    public List<Guardian_user_profile> getGuardian_user_profile_List(){
        return guardianDao.getGuardian_user_profile_List();
    }
    public Guardian_user_profile getUserByid(int id){
        return guardianDao.getUserByid(id);
    }
    public int addGuardianer(Guardian_guardianer user){
        return guardianDao.addGuardianer(user);
    }
    public Guardian_hostip getHostipByid(int id){
        return guardianDao.getHostipByid(id);
    }

    //根据ring_id获取用户
    public Guardian_user_profile getUserByRingid(String id){
        return guardianDao.getUserByRingid(id);
    }
    public Guardian_guardianer getGuardianerByid(int id){
        return guardianDao.getGuardianerByid(id);
    }
}
