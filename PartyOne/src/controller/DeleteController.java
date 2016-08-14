package controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Context;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import utils.UiUtils;
import dao.ContextDao;
import dao.FormDao;


public class DeleteController implements Controller {
 private FormDao formDao= null;
 private ContextDao contextDao = null;
 public void setFormDao(FormDao formDao) {
  this.formDao = formDao;
 }
 public void setContextDao(ContextDao contextDao) {
	  this.contextDao = contextDao;
	 }
 @RequestMapping(value="delete.do",method=RequestMethod.POST)
@Override
public ModelAndView handleRequest(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
	 response.setCharacterEncoding("euc-kr");
	 String context_id = request.getParameter("context_id");
	 System.out.println("context : " + context_id);
	 
	 Context context = new Context();
	 context.setContext_id(Integer.parseInt(context_id));
	 formDao.deletePlayer(context);
	 List contextList = this.contextDao.getContextList();
		Map model = new HashMap();
		model.put("contextList", contextList);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("context");
		modelAndView.addAllObjects(model);
		return modelAndView;
}
 


 

 
}
