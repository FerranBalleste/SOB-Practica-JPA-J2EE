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
 
  <title>Profile</title>
</head>
<body>
    <div class="container-fluid bar">
        <div id="block_container" class="container">
          <div id="name_bar" class="container">
            <h1>Sob's Coffee</h1>
          </div>
            <div class="btn-group" id="login_bar">
                <a class="btn btn-warning dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                    Welcome, ${sessionScope.userName}!
                </a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="shopping.do">Shopping Cart</a></li>
                  <li><hr class="dropdown-divider"></li>
                  <li><a class="dropdown-item" href="logout.do">Logout</a></li>
                </ul>
            </div>
                        <div id="login_bar">
        <a href="<c:url value="/" />" id="login">Return</a>
        </div>
        </div>
    </div>
    <div class="container-sm" style="display: flex;width:100%;height:100%;background-color: whitesmoke;">
        <div class="form-group">
        <hr/>
        <p><b>Username: </b>${sessionScope.userName}</p>
        <p><b>Email: </b>${sessionScope.userEmail}</p>
        <hr/>
        </div>
        <hr/>
    </div>
</body>
</html>