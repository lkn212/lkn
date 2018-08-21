package cn.message.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.message.pojo.Message;
import cn.message.service.StandardService;
import cn.message.tools.PageSupport;

@Controller
@RequestMapping(value="/message")
public class MessageController {
	private Logger log = Logger.getLogger(MessageController.class);
	@Resource
	private StandardService standardService;
	
	//查询全部信息
	@RequestMapping(value="/showmessage.html")
	public String getMessageList(@RequestParam(value=("keys"),required=false)String keys,
								 @RequestParam(value="pageIndex",required=false)String pageIndex,
								 Model model){
		log.info("keys========="+keys);
		log.info("pageIndex========="+pageIndex);
		List<Message> messagelist = null;
		//设置页面容量
		int pageSize = 3;
		//当前页码
		int currentPageNo = 1;
		
		if(keys == null){
			keys="";
		}
		if(pageIndex != null){
			try{
				currentPageNo = Integer.valueOf(pageIndex);
			}catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		//总数量
		int totalCount	= standardService.getCount(keys);
		//总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);

    	int totalPageCount = pages.getTotalPageCount(); 
		//控制首尾页
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
		
		messagelist = standardService.getMessageList(keys, (currentPageNo-1)*pageSize, pageSize);
		model.addAttribute("messagelist", messagelist);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		
		return "showmessage";
	}
	
	//增加
	@RequestMapping(value="/messageadd.html",method=RequestMethod.GET)
	public String add(@ModelAttribute("message")Message message){
		return "messageadd";
	}
	
	@RequestMapping(value="/addSave.html",method=RequestMethod.POST)
	public String addSave(Message message,
						  HttpServletRequest req,
						  @RequestParam(value="a_packagePath",required=false)MultipartFile attach){
		String packagePath = null;
		//判断文件是否为空
		if(!attach.isEmpty()){
			String path = req.getSession().getServletContext().getRealPath("static"+File.pathSeparator+"uploadfiles");
//			String oldFileName = attach.getOriginalFilename();//原文件名
			/*String suffix = FilenameUtils.getExtension(oldFileName);//原文件后缀
			int filesize = 500000;*/
			
			String fileName = System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"";
			File targetFile = new File(path,fileName);
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			
			//保存
			try{
				attach.transferTo(targetFile);
			}catch (Exception e) {
				e.printStackTrace();
				req.setAttribute("uploadFileError", "上传失败！");
				return "messageadd";
			}
			packagePath = path + File.separator+fileName;
		}
		message.setPackagePath(packagePath);
		if(standardService.messageAdd(message)){
			return "redirect:/message/showmessage.html";
		}
		return "messageadd";
	}
	
	//通过标准号stuNum进行同名验证
	@RequestMapping(value="/snexist.html")
	@ResponseBody
	public Object stuNumIsExist(@RequestParam String stdNum){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		log.debug("stdNum==============="+stdNum);
		if(StringUtils.isNullOrEmpty(stdNum)){ 
			resultMap.put("stdNum", "exist");
		}else{
			Message message = standardService.getStdNum("stdNum");
			if(null!=message){
				resultMap.put("stdNum", "exist");
			}else{
				resultMap.put("stdNum", "noexist");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}
	
	//修改
	//根据ID获取用户信息
	@RequestMapping(value="/modify.html",method=RequestMethod.GET)
	public String getMessageById(@RequestParam Integer id,Model model){
		Message message = standardService.getMessageById(id);
		model.addAttribute(message);
		return "modify";
	}
	//根据用户id保存修改的信息
	@RequestMapping(value="/modifySave.html",method=RequestMethod.POST)
	public String modifySave(Message message){
		log.debug("id==================="+message.getId());
		if(standardService.messageMod(message)){
			return "redirect:/message/showmessage.html";
		}
		return "modify";
	}
	
	//删除
	@RequestMapping(value="/del.html",method=RequestMethod.POST)
	@ResponseBody
	public String delMessage(@RequestParam String[] id){
		if(null != id){
			for (int i = 0; i < id.length; i++) {
				int mid = Integer.valueOf(id[i]);
				if(standardService.messageDel(mid)){
					return "success";
				}else{
					return "error";
				}
			}
		}
		return "error";
	}
	
}
