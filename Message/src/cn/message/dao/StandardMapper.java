package cn.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.message.pojo.Message;

public interface StandardMapper {
	public List<Message> getMessageList(@Param("keys")String keys,
										@Param("currentPageNo")int currentPageNo, 
										@Param("pageSize")int pageSize);
	
	public Message getStdNum(@Param("stdNum")String stdNum);
	
	public Message getMessageById(@Param("id")Integer id);
	
	public int getCount(@Param("keys")String keys);
	
	public int messageAdd(Message message);
	
	public int messageMod(Message message);
	
	public int messageDel(@Param("id")Integer id);
}
