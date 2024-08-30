<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Ticket</title>
    <style>
        /* Define CSS styles for the form */
        body {
            font-family: Arial, sans-serif;
            padding: 20px;
        }
        h2 {
            text-align: center;
        }
        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
        }
        input[type="text"],
        select,
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<jsp:include page="navigation.jsp" />

<h2>Add New Ticket</h2>
<% String message = (String) request.getAttribute("message"); %>
<% String error = (String) request.getAttribute("error"); %>

<% if (message != null && !message.isEmpty()) { %>
    <p class="success"><%= message %></p>
<% } else if (error != null && !error.isEmpty()) { %>
    <p class="error"><%= error %></p>
<% } %>

<form action="AddTicketServlet" method="post">
	<label for="ticket_id">ID:</label>
    <input type="text" id="ticket_id" name="ticket_id" required>
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required>
    <label for="description">Description:</label>
    <textarea id="description" name="description" rows="4" required></textarea>
    <label for="priority">Priority:</label>
    <input type="text" id="priority" name="priority" required>
    <label for="status">Status:</label>
    <input type="text" id="status" name="status" required>
    <label for="assigned_to">Assigned To (User ID):</label>
    <input type="text" id="assigned_to" name="assigned_to" required>
    <input type="submit" value="Add Ticket">
</form>

</body>
</html>
