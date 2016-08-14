package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import dao.ContextDao;


public class ContextController implements Controller {
	
	private ContextDao contextDao;
	
	public void setContextDao(ContextDao contextDao) { this.contextDao = contextDao; }
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		List contextList = this.contextDao.getContextList();
		Map model = new HashMap();
		model.put("contextList", contextList);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("context");
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

}
