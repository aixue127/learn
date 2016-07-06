package test;

import com.cn.jdshow.common.mongodb.MongodbDMLUtil;
import com.cn.jdshow.domain.Function;
import com.cn.jdshow.vo.LoginInputVO;

public class MongodbDMLUtilTest {

	public static void getQueryByVO() {
		LoginInputVO vo = new LoginInputVO();
		vo.setLoginName("lumxl");
		vo.setPassWord("123444");
		MongodbDMLUtil.getQueryByVO(vo);
	}
	
	
	public static void getUpdate() {
		Function f = new Function();
		f.setFunCode("1");
		f.setFunName("2");
		f.setJdurl("xxx");
		f.setOrderd("333");
		
		Function pf = new Function();
		pf.setFunCode("1");
		pf.setFunName("2");
		pf.setJdurl("xxx");
		pf.setOrderd("333");
		
		 f.setParentFun(pf);
		 
		 MongodbDMLUtil.getUpdate(f);
	}
	
	public static void main(String[] args) {
		MongodbDMLUtilTest.getUpdate();
	}
}
