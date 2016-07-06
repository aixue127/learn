package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

public class RedisClusterTest {

	/*static JedisConnectionFactory jedisConnectionFactory = null;
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("/config/jdshow-session.xml");
		jedisConnectionFactory = (JedisConnectionFactory)context.getBean("jedisConnectionFactory");
	}

	public static void main1(String[] args) {
		RedisConnection connection = jedisConnectionFactory.getConnection();
		System.out.println(connection.getClientList().size());
	}*/
}
