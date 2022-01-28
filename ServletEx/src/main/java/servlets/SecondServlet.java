package servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.io.IOException;

public class SecondServlet extends HttpServlet {

    public SecondServlet() {
        super();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().append("Welcome to the Second Servlet");
        System.out.println("Welcome to the Second Servlet");

        ServletConfig sconf = this.getServletConfig();
        String configstr = sconf.getInitParameter("Secret");
        response.getWriter().append("\n" + configstr);

        ServletContext sconxt = this.getServletContext();
        String conxtstr = sconxt.getInitParameter("AppName");
        response.getWriter().append("\n" + conxtstr);


        HttpSession session = request.getSession();

        System.out.println(session.getAttribute("currentUser"));
        session.invalidate();

        //Setting Cookies
        Cookie cookie = new Cookie("age","75");
        response.addCookie(cookie);

        System.out.println(request.getRequestURL());
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURI());

    }

}