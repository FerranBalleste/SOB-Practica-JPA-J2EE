<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <link rel="stylesheet" href="<c:url value="/resources/css/styles.css" />" />
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
  
  <title>Login</title>
</head>
<body>
    <div class="container-fluid bar">
        <div id="block_container" class="container">
          <div id="name_bar" class="container">
            <h1>Sob's Coffee</h1>
          </div>
          <div id="login_bar">
             <a href="<c:url value="/" />" id="login">Return</a>
          </div>
        </div>
    </div>
    <div id="main" class="container-sm">
        
        <form action="login.do" class="form-horizontal" method="post">
            <div class="input-group mb-3">
                <input id="userNameInput" placeholder="Username" type="text" name="userName" value="" class="form-control" required/>
                <span class="input-group-text"> </span>
                <input id="passwordInput" placeholder="Password" type="password" name="password" value="" class="form-control" required/>
                <button class="btn btn-success" type="submit" value="Login" id="button-addon2">Login</button>
             </div>
            </div>
        </form>
    </div>
    <div id="top_bar">
        <c:if test="${not empty message}">
            <p>${message}</p>
        </c:if>
    </div>
</body>
</html>