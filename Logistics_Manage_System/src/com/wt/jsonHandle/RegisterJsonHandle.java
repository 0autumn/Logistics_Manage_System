package com.wt.jsonHandle;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.wt.entity.User;
import com.wt.service.UserService;

public class RegisterJsonHandle extends ActionSupport implements 
	ModelDriven<User>, Preparable{

	private static final long serialVersionUID = 1L;

	private Map<String, Object> dataMap;
	
	private UserService userService;
	
	private User model;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	
	// ע�ᴴ���û�
	public String save(){
		
		dataMap = new HashMap<String, Object>();
		
		ActionContext context = ActionContext.getContext();
		
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		String email = request.getParameter("email");

		// �����ݿ��в���һ���û�����
		if(username != null && password != null && email != null){
			System.out.println("username --- " + username + " password --- " + password + " email--- " + email);
			
			User user = new User();
			
			user.setUser_name(username);
			user.setUser_password(password);
			user.setUser_email(email);
			
			userService.saveOrUpdate(user);
			
			dataMap.put("user", model);
			dataMap.put("code", 0);
		}
			
		return SUCCESS;
	}

	@Override
	public void prepare() throws Exception {}

	@Override
	public User getModel() {
		return model;
	}
	
}
