package com.cn.jdshow.common.ftp;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @Description: FTP服务类
 * @author mingxin.lu
 * @date 2016年6月18日 下午11:31:08   @version V1.0
 */
public class FtpUtil {
	
	
	
	/**
	 * 
	 * @Description: 文件上传
	 * @param localFile
	 * @param remotePath
	 * @param fileName
	 * @date 2016年6月19日 下午10:32:51
	 */
	public static void upload(MultipartFile localFile, String remotePath, String fileName) {
		FtpUtil.execute(new FtpOperator.UploadFile(localFile, remotePath, fileName)) ;
	}
	
	
	public static void execute(Runnable runnable) {
		FtpClientUtil.threadPoolService.execute(runnable);
	}
}
