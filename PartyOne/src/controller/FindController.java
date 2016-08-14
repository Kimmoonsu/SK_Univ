package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import utils.UiUtils;

import dao.FindDao;


public class FindController implements Controller {
	
	private FindDao findDao;
	
	public void setFindDao(FindDao findDao) { this.findDao = findDao; }
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("euc-kr");
		String keyword = UiUtils.toUnicode(request.getParameter("keyword"));
		String findState = UiUtils.toUnicode(request.getParameter("findState"));
		System.out.println("keyword : " + keyword);
		List findList = null;
		if(findState.equals("writer"))
		{
			findList = this.findDao.getWriterList(keyword);
		}else if(findState.equals("title"))
		{
			findList = this.findDao.getTitleList(keyword);
		}else if(findState.equals("sub"))
		{
			findList = this.findDao.getSubList(keyword);
		}
	
		Map model = new HashMap();
		model.put("contextList", findList);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("context");
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

}
