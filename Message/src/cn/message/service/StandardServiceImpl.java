package cn.message.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.message.dao.StandardMapper;
import cn.message.pojo.Message;

@Service
public class StandardServiceImpl implements StandardService{
	@Resource
	private StandardMapper standardMapper;
	@Override
	public List<Message> getMessageList(String keys,int currentPageNo, int pageSize) {
		// TODO Auto-generated method stub
		return standardMapper.getMessageList(keys,currentPageNo,pageSize);
	}
	
	
	@Override
	public int getCount(String keys) {
		// TODO Auto-generated method stub
		return standardMapper.getCount(keys);
	}


	@Override
	public boolean messageAdd(Message message) {
		boolean flag = false;
		try{
			if(standardMapper.messageAdd(message)>0){
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public boolean messageMod(Message message) {
		boolean flag = false;
		try{
			if(standardMapper.messageMod(message)>0){
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	
	}


	@Override
	public boolean messageDel(Integer id) {
		boolean flag = false;
		try{
			if(standardMapper.messageDel(id)>0){
				flag = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


	@Override
	public Message getStdNum(String stdNum) {
		return standardMapper.getStdNum(stdNum);
	}


	@Override
	public Message getMessageById(Integer id) {
		// TODO Auto-generated method stub
		return standardMapper.getMessageById(id);
	}

}
