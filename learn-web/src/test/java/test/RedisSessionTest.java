package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class RedisSessionTest {

	static ShardedJedisPool jedisCluster = null;
	static {
		ApplicationContext context = new ClassPathXmlApplicationContext("/config/jdshow-session.xml");
        jedisCluster = (ShardedJedisPool)context.getBean("shardedJedisPool");
	}
	
	public static void main(String[] args) {
		String str = jedisCluster.getResource().get("1234");
		System.out.println("key:" + str);
	}

}
