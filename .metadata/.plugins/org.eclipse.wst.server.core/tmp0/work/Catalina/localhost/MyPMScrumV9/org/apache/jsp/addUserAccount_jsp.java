package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addUserAccount_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>MyPMScrumTool- Login</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<table>\r\n");
      out.write("<tr><td colspan=2> ");
      out.write("<img border=\"0\" src=\"images/h8y09170.jpg\" width=\"1024\" height=\"163\">\r\n");
      out.write("\r\n");
      out.write("\t<tr><td>\r\n");
      out.write("\t");
      out.write("LEFT MENU\r\n");
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t<td>\t\r\n");
      out.write("<form action=\"CreateUserAccountServlet\" method=\"get\">\r\n");

String email = (String)request.getAttribute("userEmail");
//String email = (String) session.getAttribute("userEmail");
out.println("<input type='hidden' name='email' value='"+email+"'>");

      out.write("\r\n");
      out.write("\r\n");
      out.write("<table>\r\n");
      out.write("\r\n");
      out.write("<tr><td colspan=2 align=center>\r\n");
      out.write("<p><b>Add User Account:</b></p>\r\n");
      out.write("\t\t\t<tr><td>\r\n");
      out.write("User Name:\r\n");
      out.write("<td><input type=\"text\" name=\"userName\">\r\n");
      out.write("<tr><td>Password:\r\n");
      out.write("<td>\r\n");
      out.write("<input type=\"password\" name=\"password\">\r\n");
      out.write("<tr><td>\r\n");
      out.write("<tr><td>Confirm password: \r\n");
      out.write("<td>\r\n");
      out.write("<input type=\"password\" name=\"password2\">\r\n");
      out.write("<tr><td colspan=2 align=center>\r\n");
      out.write("<input type=\"submit\" value=\"login\">\r\n");
      out.write("<input type=\"reset\" value=\"reset\">\r\n");
      out.write("\t</table>\r\n");
      out.write("</form>\r\n");
      out.write("<tr><td colspan=2> ");
      out.write("FOOTER \r\n");
      out.write("\r\n");
      out.write("\t\t\t\r\n");
      out.write("</table>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
