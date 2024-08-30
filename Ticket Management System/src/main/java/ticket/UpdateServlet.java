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

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rollNumber = request.getParameter("studentId");
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
            String sql = "UPDATE ticket SET ticket_id=?, title=?, description=?, priority=?, status=?, assigned_to=? WHERE ticket_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, ticket_id);
            ps.setString(2, title);
            ps.setString(3, description);
            ps.setString(4, priority);
            ps.setString(5, status);
            ps.setInt(6, assigned_to);
            ps.setString(7, rollNumber);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("viewAccounts.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
