package com.cn.jdshow.common.ftp;

import com.cn.jdshow.common.constant.PicturePathConstant;
import com.cn.jdshow.common.util.PropertyUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FtpClientUtil {

	public static final int SEMA_NUMBER = Integer.parseInt(PropertyUtil.getProjectValue("ftp.thread.count"));
	static Semaphore sema;
	
	public static final ConcurrentHashMap<String, FtpClientExpand> clientmap;
	
	static ExecutorService threadPoolService;
	
	private static String[] alyUsedClient;
	
	static ReentrantLock _lock;
	
	static {
		// 是否启用ftp服务
		if("true".equals(PropertyUtil.getProjectValue("ftp.server.used"))) {
			sema = new Semaphore(SEMA_NUMBER);
			clientmap = new ConcurrentHashMap<>();
			threadPoolService = Executors.newFixedThreadPool(SEMA_NUMBER);
			alyUsedClient = new String[SEMA_NUMBER];
			_lock = new ReentrantLock();
			// ftp服务器地址相关信息
			String ipserver = PropertyUtil.getProjectValue("ftp.server.ip");
			int port = Integer.parseInt(PropertyUtil.getProjectValue("ftp.server.port"));
			String username = PropertyUtil.getProjectValue("ftp.server.username");
			String password = PropertyUtil.getProjectValue("ftp.server.password");

			for(int i=0; i<SEMA_NUMBER; i++) {
				FtpClientExpand ftpClient =  validClient(ipserver, port, username, password);
				ftpClient.set_index(i + "");
				clientmap.put(i + "", ftpClient);
			}
		} else {
			clientmap = null;
		}

	}
	
	public static FtpClientExpand validClient(String hostname,int port,String username,String password) {
		try {
			FtpClientExpand ftpClient = new FtpClientExpand();
			ftpClient.connect(hostname, port);
			if(FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
				if(ftpClient.login(username, password)) {
					return ftpClient;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static FtpClientExpand getClient() {
		_lock.lock();
		FtpClientExpand ftpClient = null;
		try {
			for(int i=0; i<alyUsedClient.length; i++) {
				if(alyUsedClient[i]==null) {
					ftpClient = clientmap.get(i +"");
					ftpClient.set_status(1); // 工作中
					alyUsedClient[i] = i + "";
					sema.acquire();
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			_lock.unlock();
		}
		_lock.unlock();
		return ftpClient;
	}
	
	public static void closeClient(FtpClientExpand ftpClient) {
		String _index = ftpClient.get_index();
		ftpClient.set_status(0);
		alyUsedClient[Integer.parseInt(_index)] = null; // 设置该服务为未使用
 		sema.release();
	}
	
	static final Pattern _REG_MATCHER = Pattern.compile("\\w+\\/");
	public static boolean createDirectory(FTPClient ftpClient, String remotePath, String perfPath) throws Exception {
		
		if(!ftpClient.changeWorkingDirectory(remotePath)){ // 文件目录不存在，需要从上检查
			// 开始检查
			Matcher matcher = _REG_MATCHER.matcher(remotePath);
			String lastFolder = "";
		    while(matcher.find()) {
		    	lastFolder = matcher.group();
		    }
		    remotePath = remotePath.substring(0, remotePath.lastIndexOf(lastFolder));
		    createDirectory(ftpClient, remotePath, perfPath);
		} else {
			// 存在目录
			String needMake = perfPath;
			if(!"/".equals(remotePath)) {
				needMake = perfPath.replace(remotePath, "");
			}
			if(needMake.length() > 0) {
				createVKDirectory(ftpClient, needMake, remotePath);
			}
		}
		return true;
	}
	
	private static void createVKDirectory(FTPClient ftpClient, String needMake, String alyMake) throws Exception {
		String parentFolder = alyMake;
		String [] folders = needMake.split("/");
		for(int i=0,j=folders.length; i<j; i++) {
			ftpClient.makeDirectory(folders[i]);
			// 进入该工作目录
			parentFolder += "/" + folders[i];
			ftpClient.changeWorkingDirectory(parentFolder);
		}
	}
	
	public static void main(String args[])throws Exception {
		createDirectory(getClient(), PicturePathConstant.BRAND_BIG + "/test1/", PicturePathConstant.BRAND_BIG + "/test1/");
	}
}
