

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import facebook4j.Facebook;
import facebook4j.FacebookFactory;

/**
 * Servlet implementation class FaceLogin
 */
@WebServlet("/FaceLogin")
public class FaceLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaceLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Facebook facebook = new FacebookFactory().getInstance();
		 // facebook �ν��Ͻ��� �����մϴ�.
        facebook.setOAuthAppId("186905434977843", "06bd1e7d7fbc5526492c8327817189ef");
        facebook.setOAuthPermissions("email, publish_actions, publish_stream, user_likes, friends_likes, read_stream");
 // ���� ��û
        request.getSession().setAttribute("facebook", facebook);
 // ���ǿ� ���� facebook �ν��Ͻ��� ��Ϥ�
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/CallbackServlet");
 // �ݹ��ּҸ� ��������. http://�������ּ�/callback�Դϴ�
        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));
 // ������ ��û�ϰ� ���� �ּҷ� �����̷�Ʈ�մϴ�
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
