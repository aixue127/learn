package test.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.cn.jdshow.common.mongodb.MongoDBSource;
import com.cn.jdshow.domain.Function;
import com.cn.jdshow.domain.Role;
import com.cn.jdshow.domain.RoleFunction;
import com.cn.jdshow.domain.User;
import com.cn.jdshow.domain.UserRole;
import com.cn.jdshow.service.RoleFunctionService;
import com.mongodb.DBRef;

import test.BaseTest;

public class FunctionTest extends BaseTest{

	@Autowired
	private RoleFunctionService  roleFunctionService;
	
	public void addFunction() throws Exception{
		Function f = new Function();
		f.setFunCode("001");
		f.setFunName("后台管理");
		f.setOrderd("10");
		roleFunctionService.save(f);
		
		Function f11 = new Function();
		f11.setFunCode("00101");
		f11.setFunName("用户管理");
		f11.setParentFun(f);
		f11.setOrderd("11");
		f11.setJdurl("http://localhost:8080/jdshow/user/goQueryUser");
		roleFunctionService.save(f11);
		
		Function f12 = new Function();
		f12.setFunCode("00102");
		f12.setFunName("角色管理");
		f12.setParentFun(f);
		f12.setOrderd("12");
		f12.setJdurl("http://localhost:8080/jdshow/rolefun/goQueryRole");
		roleFunctionService.save(f12);
		
		Function f13 = new Function();
		f13.setFunCode("00103");
		f13.setFunName("权限管理");
		f13.setParentFun(f);
		f13.setOrderd("13");
		f13.setJdurl("http://localhost:8080/jdshow/rolefun/goQueryFunction");
		roleFunctionService.save(f13);
		
		/*Function f2 = new Function();
		f2.setFunCode("002");
		f2.setFunName("第二个");
		roleFunctionService.save(f2);
		
		Function f21 = new Function();
		f21.setFunCode("00201");
		f21.setFunName("第二个01");
		f21.setParentFun(f2);
		roleFunctionService.save(f21);
		
		Function f22 = new Function();
		f22.setFunCode("00202");
		f22.setFunName("第二个02");
		f22.setParentFun(f2);
		roleFunctionService.save(f22);
		
		Function f23 = new Function();
		f23.setFunCode("00203");
		f23.setFunName("第二个03");
		f23.setParentFun(f2);
		roleFunctionService.save(f23);*/
		
		Role r = new Role();
		r.setRoleCode("001");
		r.setRoleName("管理员");
		roleFunctionService.save(r);
		
		User u = new User();
		u.setUserName("管理员");
		u.setLoginName("admin");
		u.setPassWord("123456");
		roleFunctionService.save(u);
		
		
		UserRole ur = new UserRole();
		ur.setUser(u);
		ur.setRole(r);
		roleFunctionService.save(ur);
		
		RoleFunction rf1 = new RoleFunction();
		rf1.setFunction(f11);
		rf1.setRole(r);
		roleFunctionService.save(rf1);
		
		RoleFunction rf2 = new RoleFunction();
		rf2.setFunction(f12);
		rf2.setRole(r);
		roleFunctionService.save(rf2);
		
		RoleFunction rf3 = new RoleFunction();
		rf3.setFunction(f13);
		rf3.setRole(r);
		roleFunctionService.save(rf3);
		
		/*RoleFunction rf3 = new RoleFunction();
		rf3.setFunction(f21);
		rf3.setRole(r);
		roleFunctionService.save(rf3);
		
		RoleFunction rf4 = new RoleFunction();
		rf4.setFunction(f22);
		rf4.setRole(r);
		roleFunctionService.save(rf4);
		
		RoleFunction rf5 = new RoleFunction();
		rf5.setFunction(f23);
		rf5.setRole(r);
		roleFunctionService.save(rf5);*/
		
	}
	
	
	public void test1() throws Exception {
	/*	Function f5 = roleFunctionService.findById(Function.class, 5);
		System.out.println("==============" +f5.getParentFun().getParentFun().getFunName());*/
		
	}
	

	public void test2() throws Exception {
		List<Function> list =roleFunctionService.queryUserFunction(1);
		for(Function f : list) {
			System.out.println(f.getFunName());
		}
		
	}
	
	public void test3() {
		MongoTemplate m = MongoDBSource.getMongoTemplate();
		DBRef d = new DBRef("Function","1");
		m.updateFirst(new Query(Criteria.where("id").is(7)), new Update().set("parentFun",  d), Function.class);
		System.out.println("execute end....");
	}
	
	
	public void Test4() {
		MongoTemplate m = MongoDBSource.getMongoTemplate();
		DBRef d = new DBRef("Function", 6);
		List<RoleFunction> rf = m.find(new Query(Criteria.where("function").is(d)), RoleFunction.class);
		for(RoleFunction f : rf) {
			System.out.println(f.getId());
		}
	}
	
	
	public void test5() {
		MongoTemplate m = MongoDBSource.getMongoTemplate();	
		List<Function> fs = m.find(new Query(Criteria.where("parentFun").exists(true)), Function.class);
		System.out.println(fs.size());
	}
	
	@Test
	public void test6() {
		MongoTemplate m = MongoDBSource.getMongoTemplate();
		m.updateFirst(new Query(Criteria.where("id").is(4)), new Update().set("jdurl",  "http://localhost:8080/learn-web/jdshow/rolefun/goQueryFunction")
				.set("funName", "权限管理"), Function.class);
		System.out.println("execute end....");
	}
}
