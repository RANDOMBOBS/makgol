<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    
    <style>
    /* Style the form container */
    h1 {
        text-align: center;
    }

    form {
        max-width: 300px;
        margin: 0 auto;
        padding: 20px;
        background: #f5f5f5;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
    }

    /* Style form labels and input fields */
    label {
        display: block;
        margin-top: 10px;
        font-weight: bold;
        margin-right : 10px;
        width: 100%;
    }

    input[type="text"],
    input[type="password"] {
        width: 90%;
        padding: 10px;
        margin-top: 5px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    /* Style the submit button */
    input[type="submit"] {
        display: block;
        width: 98%;
        padding: 10px;
        background: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        margin-top: 15px;
    }
</style>
    
</head>
<body>
    <h1>LOGIN</h1>
    <form method="post" action="/makgol/user/loginConfirm">
        <label for="email">이메일:</label>
        <input type="text" id="email" name="email" required><br><br>
        
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password" required><br><br>
        
        <input type="submit" value="로그인">
    </form>
</body>
</html>