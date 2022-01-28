package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyServlet extends HttpServlet {

    public MyServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.getWriter().append("Welcome to My Servlet!!!!!!!");
        System.out.println("Welcome to My Servlet!");

        ServletConfig sconf = this.getServletConfig();
        String configstr = sconf.getInitParameter("Secret");
        response.getWriter().append("\n" + configstr);
        response.getWriter().append("\n" + this.getInitParameter("Secret"));

        ServletContext sconxt = this.getServletContext();
        String conxtstr = sconxt.getInitParameter("AppName");
        response.getWriter().append("\n" + conxtstr);

        HttpSession session = request.getSession();
        System.out.println(session.getId());

        session.setMaxInactiveInterval(900);

        session.setAttribute("currentUser", "{ 'username':'Ryan','password':'pass' }");

        System.out.println(session.getAttribute("currentUser"));

        System.out.println(request.getRequestURL());
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());


        String uri = request.getRequestURI();

        switch(uri) {

            case "/ServletEx/test": {
                response.getWriter().append("\nTest Worked!");
                break;
            } case "/ServletEx/googleredirect": {
                response.sendRedirect("https://www.google.com");
                break;
            } case "/ServletEx/secondredirect": {
                response.sendRedirect("/ServletEx/SecondServlet");
                break;
            } case "/ServletEx/secondforward": {
                //Forwards needs a RequestDispatcher
                RequestDispatcher rd = request.getRequestDispatcher("/SecondServlet");
                rd.forward(request, response);
            }
            default: {
                response.sendError(418);
                break;
            }

        }


    }


}
