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
  
  <title>Sob Main Page</title>
</head>
<body>
    <div class="container-fluid bar">
        <div id="block_container" class="container">
          <div id="name_bar" class="container">
            <h1>Sob's Coffee</h1>
          </div>
          <c:if test="${sessionScope.userName == null}">
          <div id="login_bar">
            <a href="logInButton.do" id="login">Login</a>
          </div>
          </c:if>
          <c:if test="${sessionScope.userName != null}">
              <div class="btn-group" id="login_bar">
                <a class="btn btn-warning dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                    Welcome, ${sessionScope.userName}!
                </a>
                <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="profile.do">Profile Info</a></li>
                  <li><a class="dropdown-item" href="shopping.do">Shopping Cart</a></li>
                  <li><hr class="dropdown-divider"></li>
                  <li><a class="dropdown-item" href="logout.do">Logout</a></li>
                </ul>
            </div>
          </c:if>
        </div>
    </div>
    
    <div id="main" class="container-sm" style="background-image: url('resources/img/beans.jpg');background-position: center;background-repeat: no-repeat; background-size: cover;">
        <form action="productList.do" method="post">
          <input id="input" type="text" placeholder="City" value="${sessionScope.cityName}" name="cityName" class="form-control" />
          <button>Search</button>
        </form>
    </div>
    
    <div id="main_body" class="container">        
    <div class="container mt-3 row">
        <c:if test="${sessionScope.productList != null}">
        <c:forEach var="product" items="${sessionScope.productList}">
            <div class="card col-sm-6 m-2" style="width: 18rem; height: fit-content;">
                <img style="width: auto; height: 256px;" class="card-img-top mt-2" src="resources/img/${product.name}.jpg" alt="Card image cap">
                <div class="card-body">
                    <h5 class="card-title">${product.name}</h5>
                    <p class="card-text"><b>Type: </b>${product.type}</p>
                    <p class="card-text"><b>Service Time: </b>${product.serviceTime} min</p>
                    <form action="productInfo.do" method="post">
                        <input type="hidden" id="prod" name="product" value="${product.id}">
                        <button type="submit" value="Submit" class="btn btn-primary">Buy</button>
                    </form> 
                </div>
            </div>
        </c:forEach>
        </c:if>
    </div>
    </div>
    
    <div class="position-relative">
        <div class="position-fixed bottom-0 end-0 m-4">
            <form action="shopping.do" method="post">
                <button class="h1 btn-lg p-0">Shopping Cart!</button>
            </form>
        </div>
    </div>
  
    <c:if test="${sessionScope.prodInfo != null}">
    <!-- The Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
  
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">${product.name}</h4>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
  
        <!-- Modal body -->
        <div class="modal-body">
            <c:if test="${sessionScope.prodInfo != null}">
            <div>
                <p><b>Name </b>: ${sessionScope.prodInfo.name}</p>
                <p><b>Type </b>: ${sessionScope.prodInfo.type}</p>
                <p><b>Service Time </b>: ${sessionScope.prodInfo.serviceTime}</p>
                <p><b>Nutritional Information </b>: ${sessionScope.prodInfo.nutritionalInformation}</p>

                <c:if test="${sessionScope.userName == null}">
                    <div class="form-group">
                    <c:forEach var="preuS" items="${sessionScope.prodInfo.pricesList}">
                            <p><b>Price: </b> ${preuS.price}&emsp;<br>
                               <b>Size: </b> ${preuS.size}&emsp;
                    </c:forEach>
                    </div>
                </c:if>

                <c:if test="${sessionScope.userName != null}">
                    <form action="addProduct.do" method="post">
                        <div class="form-group">
                        <c:forEach var="preuS" items="${sessionScope.prodInfo.pricesList}">
                            <p><b>Price: </b> ${preuS.price}&emsp;<br>
                               <b>Size: </b> ${preuS.size}&emsp;
                               <input type="radio" id="prodSizeCheck" name="prodSize" value="${preuS.size},${preuS.price}" checked>&emsp;</p>
                        </c:forEach>
                        </div>
                        <div class="form-group">
                            <hr/>
                        <div class="form-group">
                            <label><br>&emsp;Sweetener:&ensp;</label>
                            <select id="edulcorant" name="edulcorant">
                                <option value="Sugar">Sugar</option>
                                <option value="Saccharin">Saccharin</option>
                                <option value="No sweetener" selected="selected">No sweetener</option>
                            </select>
                            <label><br>&emsp;Quantity:&ensp;</label>
                            <input type="number" min="1" max="10" name="quantity" value="1">
                            <input type="hidden" id="cityNameAdd" name="cityName" value="${sessionScope.store.cityName}}">
                            <input type="hidden" id="prodIdAdd" name="productId" value="${sessionScope.prodInfo.id}">
                            <input type="hidden" id="prodNameAdd" name="productName" value="${sessionScope.prodInfo.name}">
                        <button><b>Add Product</b></button>
                        </div>
                    </form>
                </c:if>

                <c:if test="${not empty addMessage}">
                    <p>${addMessage}</p>
                </c:if>
            </div>
            </c:if>
        </div>
        
    </div>
    </div>
    </c:if>
    
</body>
    <script>
        var myModal = new bootstrap.Modal(document.getElementById("myModal"), {});
        document.onreadystatechange = function () {
            myModal.show();
        };
    </script>
</html>