package servlets;

import java.io.IOException;
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
        String page_ref = request.getParameter("page");

        if (nome_usuario == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                    .forward(request, response);
        } else if (action != null) {
            if (action.equals("logout")) {
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                        .forward(request, response);
            }
        } else if (page_ref != null) {
            int page = Integer.parseInt(page_ref);
            session.setAttribute("page_number", page);
            ArrayList<String> list_items = (ArrayList<String>) session.getAttribute("list_items");
            ArrayList<String> list_page = (ArrayList<String>) session.getAttribute("list_page");

            list_page = popArrayList(list_items, list_page, page);
            session.setAttribute("list_page", list_page);

            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String action = request.getParameter("action");

        int page = 1;

        if (action.equals("register")) {
            String nome_usuario = request.getParameter("username");
            if (nome_usuario.equals("")) {
                String message = "Empty username";
                request.setAttribute("empty_username", message);
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                        .forward(request, response);
                return;
            } else {
                session.setAttribute("username", nome_usuario);
                session.setAttribute("page_number", page);
            }
        } else if (action.equals("add")) {
            String get_item = request.getParameter("input_item");

            if (!get_item.equals("")) {
                ArrayList<String> list_items = (ArrayList<String>) session.getAttribute("list_items");

                if (list_items == null) {
                    list_items = new ArrayList<>();
                }

                list_items.add(get_item);
                session.setAttribute("list_items", list_items);

                ArrayList<String> list_page = (ArrayList<String>) session.getAttribute("list_page");
                if (list_page == null) {
                    list_page = new ArrayList<>();
                }

                page = (int) session.getAttribute("page_number");
                list_page = popArrayList(list_items, list_page, page);
                session.setAttribute("list_page", list_page);

            }
        } else if (action.equals("delete")) {
            String selected_item = request.getParameter("item");
            page = (int) session.getAttribute("page_number");
            ArrayList<String> list_page = (ArrayList<String>) session.getAttribute("list_page");
            ArrayList<String> list_items = (ArrayList<String>) session.getAttribute("list_items");
            list_items.remove(selected_item);
            session.setAttribute("list_items", list_items);

            list_page = popArrayList(list_items, list_page, page);
            session.setAttribute("list_page", list_page);

        }

        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp")
                .forward(request, response);
    }

    private ArrayList<String> popArrayList(ArrayList<String> list_items, ArrayList<String> list_page, int page) {
        int start = (page * 10) - 10;
        int last = list_items.size() - start;
        if (last > 10) {
            last = 10;
        }
        list_page.clear();
        for (int x = 0; x < last; x++) {
            list_page.add(list_items.get(start + x));
        }
        return list_page;
    }
}
