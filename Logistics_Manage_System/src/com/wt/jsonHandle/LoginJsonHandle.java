package com.wt.jsonHandle;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wt.entity.User;
import com.wt.service.UserService;

public class LoginJsonHandle extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> dataMap;
	
	private UserService userService;
	
	private Map<String, Object> session;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String jsonLogin() {
		
		dataMap = new HashMap<String, Object>();
		
		ActionContext context = ActionContext.getContext();
		
		HttpServletRequest request = (HttpServletRequest)context.get(ServletActionContext.HTTP_REQUEST);
		
		String username = request.getParameter("username");
		
		String password = request.getParameter("password");
		
		// ����ʱʹ��
//		System.out.println("username ---- " + username);
//		System.out.println("password ---- " + password);
//		System.out.println("-----------------------------");
		
		User user = userService.userCheck(username, password);
		
		// ����ʱʹ�� 
//		System.out.println("user **** " + user);
//		System.out.println("user ------- " + user.getUser_name() + " --- " + user.getUser_password());
		
		if(user == null){
			dataMap.put("data", "�û������������");
			dataMap.put("code", 1);
		}
		else{
			
			session = ActionContext.getContext().getSession();
			
			session.put("username", user.getUser_name());
			
			// ����ʱʹ��
			//System.out.println("session.put ---- " + user.getUser_name());
			
			dataMap.put("user", user);
			dataMap.put("code", 0);
		}

		return SUCCESS;
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}

}
