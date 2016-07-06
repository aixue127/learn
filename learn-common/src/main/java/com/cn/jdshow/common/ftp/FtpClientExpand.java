package com.cn.jdshow.common.ftp;

import org.apache.commons.net.ftp.FTPClient;

public class FtpClientExpand extends FTPClient{

	/**
	 * 工作标识
	 */
	public String _index = "";

	/**
	 * 工作状态
	 */
	public int _status = 0;
	
	public String get_index() {
		return _index;
	}

	public void set_index(String _index) {
		this._index = _index;
	}
	
	public int get_status() {
		return _status;
	}

	public void set_status(int _status) {
		this._status = _status;
	}

	public FtpClientExpand() {
		super();
	}
}
