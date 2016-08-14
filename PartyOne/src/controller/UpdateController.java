package controller;

import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Context;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import utils.UiUtils;
import dao.ContextDao;
import dao.FormDao;

public class UpdateController implements Controller {
	private FormDao formDao = null;
	private ContextDao contextDao = null;

	public void setFormDao(FormDao formDao) {
		this.formDao = formDao;
	}

	public void setContextDao(ContextDao contextDao) {
		this.contextDao = contextDao;
	}

	@RequestMapping(value = "update.do", method = RequestMethod.POST)
	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("euc-kr");
		String context_id = request.getParameter("context_id");
		System.out.println("context_id : " + context_id);
		String location = UiUtils.toUnicode(request.getParameter("location"));
		String context_title = UiUtils.toUnicode(request.getParameter("title"));
		String context_sub = UiUtils.toUnicode(request.getParameter("sub"));
		HttpSession session = request.getSession();
		String context_writer = UiUtils.toUnicode((String) session.getAttribute("NAME"));
		System.out.println("location : " + location);
		Context context = new Context();
		context.setContext_id(Integer.parseInt(context_id));
		context.setContext_writer(context_writer);
		context.setDate(new Date());
		context.setContext_title(context_title);
		context.setContext_sub(context_sub);
		context.setLocation(location);
		context.setImg("img/psd-4.jpg");
		
		//formDao.updatePlayer(context);
		Map model = new HashMap();
		model.put("contextList", context);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("form");
		modelAndView.addAllObjects(model);
		return modelAndView;
	}

}
