package com.cn.jdshow.common.ftp;

import org.springframework.web.multipart.MultipartFile;

public class FtpOperator {

	static class UploadFile implements Runnable{
		private MultipartFile localFile;
		private String remotePath;
		private String fileName;
		
		public UploadFile(MultipartFile localFile, String remotePath, String fileName) {
			this.localFile = localFile;
			this.remotePath = remotePath;
			this.fileName = fileName;
		}
		
		@Override
		public void run() {
			// 获取FTPClient
			FtpClientExpand ftpClient = FtpClientUtil.getClient();
			// 创建目录文件，并进入到指定目录
			try {
				FtpClientUtil.createDirectory(ftpClient, remotePath, remotePath);
				ftpClient.storeFile(fileName, localFile.getInputStream());
				FtpClientUtil.closeClient(ftpClient);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
