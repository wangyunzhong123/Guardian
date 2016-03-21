package com.xd.shiro;

import com.xd.entity.User;
import com.xd.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Set;

/**
 * Created by tianxi on 16-3-21.
 */
public class MyShiroRealm extends AuthorizingRealm {

    // 用于获取用户信息及用户权限信息的业务接口
    @Resource(name="loginService")
    LoginService loginService;

    // 获取授权信息
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        User principal=(User)principals.getPrimaryPrincipal();
        System.out.println("AuthorizationInfo,,key=="+principal.getPassword());
        User user = loginService.getUserByKey(principal.getPassword());
        System.out.println("AuthorizationInfo,,,"+user.toString());
        if (user != null)
        {
            // 查询用户授权信息
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            Set<Permission> permissions=user.getPermissions();
//            if(!permissions.isEmpty())
//            {
//                for(Permission permission:permissions)
//                {
//                    info.addRole(permission.toString());
//                }
//            }
            info.addRole("admin");
            info.addRole("user");
            System.out.println("AuthorizationInfo");
            return info;
        }
        return null;
    }

    // 获取认证信息
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException
    {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 通过表单接收的用户名
        String username = token.getUsername();
        char password[] = token.getPassword();
        String str = String.copyValueOf(password);
        if (str != null && !"".equals(str))
        {
            User user = loginService.getUserByKey(str);
            if (user != null)
            {
                return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
            }
        }
        return null;
    }

}
