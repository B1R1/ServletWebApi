package ua.burko.servletapi;

import org.json.simple.JSONObject;
import ua.burko.servletapi.searcher.Searcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This servlet which get parameters from client, send them to Searcher and then send results as response.
 *
 * @author Roman Burko <roman.burko@gmail.com>
 * @version 1.0
 */
@SuppressWarnings("unchecked")
@WebServlet("/result")
public class Servlet extends HttpServlet {

    private static final int CHAR_LIMIT = 1000;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String text = "";
            int charLimit = CHAR_LIMIT;
            int stringLength = 0;
            boolean metaData = false;

            JSONObject jsonObject = new JSONObject();

            String queryText = request.getParameter("q");
            if (!queryText.isEmpty() && queryText.trim().length() > 0)
                text = queryText;

            if (!request.getParameter("limit").isEmpty()) {
                int queryLimit = Integer.parseInt(request.getParameter("limit"));
                if (queryLimit != 0)
                    charLimit = queryLimit;
            }
            if (!request.getParameter("length").isEmpty()) {
                int queryLength = Integer.parseInt(request.getParameter("length"));
                if (queryLength != 0)
                    stringLength = queryLength;
            }

            if (request.getParameter("includeMetaData").equals("true"))
                metaData = true;

            jsonObject.put("text", Searcher.search(text.toLowerCase(), charLimit, stringLength));

            if (metaData) {
                jsonObject.put("metaData", Searcher.getMetaData());
            }

            response.setContentType("application/json");
            response.getWriter().write(JsonResolver.resolve(jsonObject.toJSONString()));
        }
        catch (NullPointerException e) {
            response.sendRedirect("/");
        }
    }
}
