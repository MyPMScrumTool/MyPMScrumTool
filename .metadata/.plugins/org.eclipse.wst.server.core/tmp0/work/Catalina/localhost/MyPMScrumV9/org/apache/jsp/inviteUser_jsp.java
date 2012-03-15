package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import domainModel.Role;
import model.PMSession;

public final class inviteUser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.List<java.lang.String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<java.lang.String>(3);
    _jspx_dependants.add("/pageElements/HeaderFile.html");
    _jspx_dependants.add("/pageElements/LeftMenuFile.html");
    _jspx_dependants.add("/pageElements/FooterFile.html");
  }

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.List<java.lang.String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Language\" content=\"en-us\">\r\n");
      out.write("<meta http-equiv=\"Content-Type\"\r\n");
      out.write("\tcontent=\"text/html; charset=windows-1252\">\r\n");
      out.write("<title>myPM-ScrumTool</title>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td colspan=2>");
      out.write("<img border=\"0\" src=\"images/h8y09170.jpg\" width=\"1024\" height=\"163\">\r\n");
      out.write("\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td>");
      out.write("LEFT MENU\r\n");
      out.write("\r\n");
      out.write("\t\t\t<td>\r\n");
      out.write("\t\t\t\t<table>\r\n");
      out.write("\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t<td colspan=2>Invite User: <!------------ JSP CODE TO READ FROM DATATBASE ---------->\r\n");
      out.write("\t\t\t\t\t\t\t");
 
							
							String userName = (String) session.getAttribute("userName");
							String userPassword = (String)session.getAttribute("userPassword");
							model.PMSession pmSession = new PMSession(userName, userPassword);
							if(!pmSession.isSession()){
								out.println("You should login first");
							
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<a href=\"login.jsp\">login</a>\r\n");
      out.write("\t\t\t\t\t\t\t");
 
							}
							else{
								if(!pmSession.isInstructor()){
									out.println("You are not allowed to invite a user becuase you should be the instructor");
								}
								else{
									out.println("Welcome back" + session.getAttribute("userName"));
									pmPersistence.Database myDb = new pmPersistence.Database("jdbc:mysql://localhost:3306/", "com.mysql.jdbc.Driver", "mypmscrumdb", "root", "scrumPM2012");       
									pmPersistence.RetrieveResult result = myDb.retrieveAllPersistentObjects(Role.class, Role.TABLE);
									domainModel.Role roleObj = (domainModel.Role)result.next();
									if(roleObj == null){
										out.println("No Role is available");
									}
									else
									{
	
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t<form action=\"InviteUserServlet\" method=\"get\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td>User Email:\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"userEmail\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td>User Type:\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td><select name=\"roleDesc\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<option>Select type</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");

												do{
												
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
 out.println(roleObj.getDescription()); 
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t");
 roleObj = (Role) result.next(); 
												}while(roleObj != null);
												
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td>Class No.:\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td><select name=\"classNo\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"SE430\">SE430</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"SE477\">SE477</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</td></tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<td colspan=2 align=center>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<input type=\"submit\" value=\"Invite\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<input type=\"reset\" value=\"reset\">\r\n");
      out.write("\t\t\t\t\t\t\t\t</form> ");

									}
								
								}
								}
      out.write("\r\n");
      out.write("\t\t\t\t</table>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td colspan=2>\r\n");
      out.write("\t\t");
      out.write("FOOTER \r\n");
      out.write("\r\n");
      out.write("\t\t</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\r\n");
      out.write("\t</table>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
