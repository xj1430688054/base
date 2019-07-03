package com.xj.base.controller.admin.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xj.base.common.JsonResult;
import com.xj.base.controller.BaseController;
import com.xj.base.entity.Notice;
import com.xj.base.entity.NoticeType;
import com.xj.base.entity.User;
import com.xj.base.service.INoticeService;
import com.xj.base.service.INoticeTypeSerivce;
import com.xj.base.service.IUserService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/notice")
public class NoticeController extends BaseController{
	
	@Autowired
	private INoticeService noticeService;
	@Autowired
	private INoticeTypeSerivce noticeTypeService;
	@Autowired
	private IUserService userService;

	
	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/notice/index";
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public Page<Notice> list() {
		SimpleSpecificationBuilder<Notice> builder = new SimpleSpecificationBuilder<Notice>();
		Map<Integer, String> map1 = new HashMap<>();
		Map<Integer, String> map2 = new HashMap<>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.add("name", Operator.likeAll.name(), searchText);
		}
		
		List<NoticeType> list = noticeTypeService.findAll();
		for (NoticeType noticeType : list) {
			map1.put(noticeType.getId(), noticeType.getName());
		}
		 List<User> userList = userService.findAll();
		for (User user : userList) {
			map2.put(user.getId(), user.getNickName());
		}
		
		Page<Notice> page = noticeService.findAll(builder.generateSpecification(),getPageRequest());
		for (Notice notice : page) {
			notice.setTypeName(map1.get(notice.getTid()));
			notice.setAuthorName(map2.get(notice.getUid()));
		}
		return page;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		Map<String, String> map2 = new HashMap<>();
		List<NoticeType> type = noticeTypeService.findAll();
		for (NoticeType noticeType : type) {
			map2.put(String.valueOf(noticeType.getId()), noticeType.getName());
		}
		map.put("map2", map2);
		return "admin/notice/form";
	}
	

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Notice notice = noticeService.find(id);
		map.put("notice", notice);
		
		Map<String, String> map2 = new HashMap<>();
		List<Notice> list = noticeService.findAll();
		List<NoticeType> type = noticeTypeService.findAll();
		for (NoticeType noticeType : type) {
			map2.put(String.valueOf(noticeType.getId()), noticeType.getName());
		}
		map.put("map2", map2);
		map.put("list", list);
		return "admin/notice/form";
	}
	
	@RequestMapping(value= {"/edit"}, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Notice notice,ModelMap map){
		try {
			noticeService.saveOrUpdate(notice);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			noticeService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

}
