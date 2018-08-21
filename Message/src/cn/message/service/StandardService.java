package cn.message.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.message.pojo.Message;

public interface StandardService {
	public List<Message> getMessageList(String keys,int currentPageNo, int pageSize);
	
	public int getCount(String keys);
	
	public boolean messageAdd(Message message);
	
	public boolean messageMod(Message message);
	
	public boolean messageDel(Integer id);
	
	public Message getStdNum(String stdNum);
	
	public Message getMessageById(Integer id);
}
