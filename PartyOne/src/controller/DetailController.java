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

import dao.ContextDao;
import dao.DetailDao;


public class DetailController implements Controller {
	
	private DetailDao detailDao;
	
	public void setDetailDao(DetailDao detailDao) { this.detailDao = detailDao; }
	
	@RequestMapping(value="detail.do",method=RequestMethod.POST)
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse arg1) throws Exception {
		
		String context_id = UiUtils.toUnicode(request.getParameter("context_id"));
		List detailList = this.detailDao.getDetailList(Integer.parseInt(context_id));
		Map model = new HashMap();
		model.put("detailList", detailList);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("detail");
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

}
