package ticket;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddTicketServlet")
public class AddTicketServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        int ticket_id = Integer.parseInt(request.getParameter("ticket_id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String priority = request.getParameter("priority");
        String status = request.getParameter("status");
        int assigned_to = Integer.parseInt(request.getParameter("assigned_to"));

        String url = "jdbc:mysql://localhost:3306/ticketdb";
        String username = "root";
        String password = "janu@123";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO ticket (ticket_id, title, description, priority, status, assigned_to) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticket_id);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, priority);
            ps.setString(5, status);
            ps.setInt(6, assigned_to);
            

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                request.setAttribute("message", "Ticket added successfully!");
                request.getRequestDispatcher("viewAccounts.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Failed to add Ticket.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace for debugging
            request.setAttribute("error", "Database error: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

}
