
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author vitor
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String nome_usuario = (String) session.getAttribute("username");
        session.setAttribute("username", nome_usuario);
        
        String action = request.getParameter("action");
        
        if (nome_usuario == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        }
        else if (action != null) {
            if (action.equals("logout")) {
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                        .forward(request, response);
            }
        }
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                .forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if (action.equals("register")) {
            String nome_usuario = request.getParameter("username");
            session.setAttribute("username", nome_usuario);
        } else if (action.equals("add")) {
            String get_item = request.getParameter("input_item");
            
            if (!get_item.equals("")) {                                        
            ArrayList<String> list_items = (ArrayList<String>) session.getAttribute("list_items");
            
            if (list_items == null)
                list_items = new ArrayList<>();
            
            list_items.add(get_item);
            session.setAttribute("list_items", list_items);
            }
        } else if (action.equals("delete")) {
            String selected_item = request.getParameter("item");
            ArrayList<String> list_items = (ArrayList<String>) session.getAttribute("list_items");
            list_items.remove(selected_item);
            session.setAttribute("list_items", list_items);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                .forward(request, response);
    }

}
