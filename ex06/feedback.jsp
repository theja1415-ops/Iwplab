<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Feedback Form</title>
</head>
<body>
    <h2>Feedback Form</h2>
    <form action="submitFeedback.jsp" method="post">
        Name:
        <input type="text" name="username" required>
        <br><br>
        Feedback:
        <textarea name="feedback" required></textarea>
        <br><br>
        <input type="submit" value="Submit Feedback">
    </form>
</body>
</html>