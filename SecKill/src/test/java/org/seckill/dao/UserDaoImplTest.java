package org.seckill.dao;

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import org.seckill.dao.redis.UserDaoImpl;
import org.seckill.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.After;
import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;

/** 
* UserDaoImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 3, 2017</pre> 
* @version 1.0 
*/
@ContextConfiguration(locations = {"classpath:spring/redis-spring.xml"})
public class UserDaoImplTest {
    UserDaoImpl userDao;
    @org.junit.Test
public void testSaveUser() throws Exception {
        User user = new User();
        user.setId(2);
        user.setName("rona");
        userDao.saveUser(user);
}

/** 
* 
* Method: getUser(final int id) 
* 
*/
@org.junit.Test
public void testGetUser() throws Exception {

}

} 
