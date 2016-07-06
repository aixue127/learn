package test.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cn.jdshow.common.mongodb.MongoDBSource;
import com.cn.jdshow.dao.UserDao;
import com.cn.jdshow.domain.Function;
import com.cn.jdshow.domain.Role;
import com.cn.jdshow.domain.RoleFunction;
import com.cn.jdshow.domain.UserRole;
import com.cn.jdshow.service.RoleFunctionService;
import com.mongodb.DBRef;

import test.BaseTest;

public class UserRoleTest extends BaseTest{
	MongoTemplate m = MongoDBSource.getMongoTemplate();
	@Autowired
	private RoleFunctionService  roleFunctionService;
	@Autowired
	private UserDao  userDao;
	
	public void test1() throws Exception{
		RoleFunction rf = new RoleFunction();
		
		Role r= new Role();
		r.setRoleCode("role1");
		r.setRoleName("roleName1");
		r.setModifyDate(new Date());
		r.setCreateDate(new Date());
		
		roleFunctionService.save(r);
		
		Function f = new Function();
		f.setFunCode("funcode1");
		f.setFunName("funname1");
		f.setJdurl("f1");
		f.setCreateDate(new Date());
		f.setModifyDate(new Date());
		
		roleFunctionService.save(f);
		
		rf.setFunction(f);
		rf.setRole(r);
		roleFunctionService.save(rf);
	}
	
	public void test2() throws Exception {
		Function f = new Function();
		f.setFunCode("p");
		f.setFunName("p");
		f.setJdurl("p");
		f.setCreateDate(new Date());
		f.setModifyDate(new Date());
		
		
		Function f1 = new Function();
		f1.setFunCode("p1");
		f1.setFunName("p1");
		f1.setJdurl("p1");
		f1.setCreateDate(new Date());
		f1.setModifyDate(new Date());
		
		roleFunctionService.save(f1);
		
		Function f2 = new Function();
		f2.setFunCode("p2");
		f2.setFunName("p2");
		f2.setJdurl("p2");
		f2.setCreateDate(new Date());
		f2.setModifyDate(new Date());
		
		roleFunctionService.save(f2);
		
		/*List<Function> fs = new ArrayList<Function>();
		fs.add(f1);
		fs.add(f2);
		
		f.setSubFuns(fs);*/
		
		roleFunctionService.save(f);
	}
	
	
	public void test3() throws Exception {
		Function f = roleFunctionService.findById(Function.class, 21);
		/*for(Function r : f.getSubFuns()) {
			r.getFunCode();
		}*/
	}
	
	
	public void test4() throws Exception {
		/*Function f = roleFunctionService.getUserFunction(19);
		System.out.println("====" + f.getId());*/
	}
	
	
	public void test5() throws Exception {
		MongoTemplate m = MongoDBSource.getMongoTemplate();
		DBRef d = new DBRef("User",1);
		m.updateFirst(new Query(Criteria.where("id").is(1)), new Update().set("user",  d), UserRole.class);
	}
	
	@Test
	public void test6() throws Exception {
		Map<String, Object> sqlmap = new HashMap<>();
		sqlmap.put("user", new DBRef("User", Integer.parseInt("1")));
		List<UserRole> urs = userDao.queryListBySqlmap(UserRole.class, sqlmap);
		System.out.println(urs.size());
	}
}
