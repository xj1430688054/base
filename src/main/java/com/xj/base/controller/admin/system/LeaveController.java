package com.xj.base.controller.admin.system;

import java.util.Date;
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
import com.xj.base.entity.Leave;
import com.xj.base.entity.LeaveType;
import com.xj.base.entity.User;
import com.xj.base.service.ILeaveService;
import com.xj.base.service.ILeaveTypeService;
import com.xj.base.service.IUserService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/leave")
public class LeaveController extends BaseController{
	
	@Autowired
	private ILeaveService leaveService;
	@Autowired
	private ILeaveTypeService leaveTypeService;
	@Autowired
	private IUserService userService;
	

	
	@RequestMapping("/index")
	public String index() {
		return "admin/leave/index";
	}
	
	@RequestMapping("/form")
	public String form(ModelMap map){
		Map<String, String> map2 = new HashMap<String, String>();
		List<LeaveType> list = leaveTypeService.findAll();
		for (LeaveType leaveType : list) {
			map2.put(String.valueOf(leaveType.getId()), leaveType.getName());
		}
		map.put("map2", map2);
		return "admin/leave/form";
	}
	

	@RequestMapping("/list")
	@ResponseBody
	public Page<Leave> list() {
		SimpleSpecificationBuilder<Leave> builder = new SimpleSpecificationBuilder<Leave>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.add("name", Operator.likeAll.name(), searchText);
		}
		
		Map<String, String> map2 = new HashMap<>();
		Map<String, String> map3 = new HashMap<>();
		List<LeaveType> list = leaveTypeService.findAll();
		List<User> list2 = userService.findAll();
		for (User user : list2) {
			map3.put(String.valueOf(user.getId()), user.getNickName());
		}
		for (LeaveType leaveType : list) {
			map2.put(String.valueOf(leaveType.getId()), leaveType.getName());
		}
		
		Page<Leave> page = leaveService.findAll(builder.generateSpecification(),getPageRequest());
		for (Leave leave : page) {
			if(leave.getPid() != null){
				leave.setpName(map3.get(leave.getPid()));	
			}
			leave.seteName(map3.get(leave.getEid()));
			leave.setLeaveName(map2.get(String.valueOf(leave.getLeaveid())));
		}
		return page;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult add(Leave leave, ModelMap map) {
		try {
			User user = (User)request.getSession().getAttribute("users");
			leave.setEid(String.valueOf(user.getId()));
			leave.setCreateTime(new Date());
			leave.setUpdateTime(new Date());
			leave.setStatus("0");
			leaveService.save(leave);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return  JsonResult.success();
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Leave leave = leaveService.find(id);
		map.put("leave", leave);
		
		List<Leave> list = leaveService.findAll();
		map.put("list", list);
		return "admin/leave/form";
	}
	
	@RequestMapping(value= {"/edit"}, method = RequestMethod.POST)

	public JsonResult edit(Leave leave,ModelMap map){
		try {
			User user = (User)request.getSession().getAttribute("users");
			leave.setEid(String.valueOf(user.getId()));
			leave.setCreateTime(new Date());
			leave.setUpdateTime(new Date());
			leave.setStatus("0");
			leaveService.save(leave);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return  JsonResult.success();
	}
	
	@RequestMapping(value = "/edits/{id}", method = RequestMethod.GET)
	public String edits(@PathVariable Integer id,ModelMap map) {
		Leave leave = leaveService.find(id);
		map.put("leave", leave);
		
		List<Leave> list = leaveService.findAll();
		map.put("list", list);
		return "admin/leave/form1";
	}
	
	@RequestMapping(value= {"/edits"}, method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edits(Leave leave,ModelMap map){
		try {
			User user = (User)request.getSession().getAttribute("users");
			Leave leavee = leaveService.find(leave.getId());
			leavee.setPid(String.valueOf(user.getId()));
			leavee.setUpdateTime(new Date());
			leavee.setStatus(leave.getStatus());
			leavee.setRemark(leave.getRemark());
			leaveService.save(leavee);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			leaveService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}

}
