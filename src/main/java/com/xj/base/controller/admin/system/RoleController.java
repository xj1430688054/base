package com.xj.base.controller.admin.system;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xj.base.common.JsonResult;
import com.xj.base.controller.BaseController;
import com.xj.base.entity.Role;
import com.xj.base.service.IResourceService;
import com.xj.base.service.IRoleService;
import com.xj.base.service.specification.SimpleSpecificationBuilder;
import com.xj.base.service.specification.SpecificationOperator.Operator;

@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IResourceService resourceService;

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "admin/role/index";
	}

	@RequestMapping(value = { "/list" })
	@ResponseBody
	public Page<Role> list() {
		SimpleSpecificationBuilder<Role> builder = new SimpleSpecificationBuilder<Role>();
		String searchText = request.getParameter("searchText");
		if(StringUtils.isNotBlank(searchText)){
			builder.add("name", Operator.likeAll.name(), searchText);
			builder.addOr("description", Operator.likeAll.name(), searchText);
		}
		Page<Role> page = roleService.findAll(builder.generateSpecification(), getPageRequest());
		return page;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap map) {
		return "admin/role/form";
	}
	

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Integer id,ModelMap map) {
		Role role = roleService.find(id);
		map.put("role", role);
		return "admin/role/form";
	}
	
	
	@RequestMapping(value= {"/edit"},method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(Role role,ModelMap map){
		try {
			roleService.saveOrUpdate(role);
		} catch (Exception e) {
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(@PathVariable Integer id,ModelMap map) {
		try {
			
			roleService.delete(roleService.find(id));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.state(true,"请先删除该角色分配的权限或者用户取消关联此角色");
			return JsonResult.failure("请先删除该角色分配的权限");
		}
		return JsonResult.success();
	}
	
	@RequestMapping(value = "/grant/{id}", method = RequestMethod.GET)
	public String grant(@PathVariable Integer id, ModelMap map) {
		Role role = roleService.find(id);
		map.put("role", role);
		return "admin/role/grant";
	}

	@RequestMapping(value = "/grant/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult grant(@PathVariable Integer id,
			@RequestParam(required = false) String[] resourceIds, ModelMap map) {
		try {
			roleService.grant(id,resourceIds);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.failure(e.getMessage());
		}
		return JsonResult.success();
	}
}
