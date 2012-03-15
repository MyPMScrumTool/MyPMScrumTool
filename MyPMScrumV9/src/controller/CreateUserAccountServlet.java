package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domainModel.User;
import domainModel.Role;

/**
 * Servlet implementation class CreateUserAccountServlet
 */
@WebServlet("/CreateUserAccountServlet")
public class CreateUserAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final pmPersistence.Database myDb = new pmPersistence.Database("jdbc:mysql://localhost:3306/", "com.mysql.jdbc.Driver", "mypmscrumdb", "root", "scrumPM2012");   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/htm;charset=UTF-8");
        PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		String password2 = request.getParameter("password2").trim();
		String email = request.getParameter("email").trim();
		//DesignPageManuel page= new DesignPageManuel();
		DesignPage page = new DesignPage();
		
		if(email == null || email.isEmpty())
		{
			page.pageCon("<br>You must access this page through the link provided in the invitation email<br>");
			out.println(page.getPage());
			out.close();
			return;
		}
		if((userName == null) || userName.isEmpty() || (password == null) || password.isEmpty()){
			page.pageCon("<br>Please enter a valid user and password<br> ");
			out.println(page.getPage());
			out.close();
			return;
		}
		if(userName.length()>45 || password.length()>10){
			page.pageCon("<br>Your user must less than 45 characteres<br><br> and your password less than 10 characteres<br>");
			out.println(page.getPage());
			out.close();
			return;
		}
		if((password2 == null) || !(password.equals(password2)))
		{
			page.pageCon("<br>Passwords do not match<br>");
			out.println(page.getPage());
			out.close();
			return;
		}
		
		User emailUser = User.findByEmail(myDb, email);
		if(emailUser == null)
		{
			page.pageCon("<br>This email address does not have an invitation<br>");
			out.println(page.getPage());
			out.close();
			return;
		}
		if(emailUser.getUserName() != null)
		{
			page.pageCon("<br>This email address has already been registered<br>");
			out.println(page.getPage());
			out.close();
			return;
		}
		User nameUser = User.findByName(myDb, userName);
		if(nameUser != null)
		{
				page.pageCon("<br>This user already exists<br>Please use a different User name<br>");
				out.println(page.getPage());
				out.close();
				return;
		}
		//if(password.equals(password2))
		//{
			//User usr = User.findByEmail(myDb, email)
		//User usr = new User(myDb);
			emailUser.setUserName(userName);
			emailUser.setPassword(password);
			emailUser.setRole(Role.getRole(myDb, Role.STUDENT));
			if(emailUser.persist())
			{
				//myDb.storePersistentObject(emailUser);
				page.pageCon("<br>User succesfully registered!<br>");
			}
			else
			{
				page.pageCon("<br>Unknown error.  Could not register user!<br>");
			}
			
			out.println(page.getPage());
		//return;
		//}
		//else{
			//page.pageCon("<br>Password does not match!<br>");
			//out.println(page.getPage());
		//}
		out.close();
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
