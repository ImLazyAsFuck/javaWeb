<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ticket Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="card mx-auto" style="max-width: 800px;">
            <div class="card-header bg-primary text-white">
                <h3 class="text-center">Ticket Details</h3>
            </div>
            <div class="card-body">
                <div class="row mb-4">
                    <div class="col-md-6">
                        <h4>${movieTitle}</h4>
                        <p><strong>Screen Room:</strong> ${screenRoomName}</p>
                        <p><strong>Show Time:</strong> <fmt:formatDate value="${showTime}" pattern="dd/MM/yyyy HH:mm" /></p>
                    </div>
                    <div class="col-md-6 text-md-end">
                        <p><strong>Ticket ID:</strong> #${ticket.id}</p>
                        <p><strong>Booking Date:</strong> <fmt:formatDate value="${ticket.createdAt}" pattern="dd/MM/yyyy" /></p>
                    </div>
                </div>
                
                <hr>
                
                <h5>Booked Seats</h5>
                <div class="row">
                    <div class="col-md-12">
                        <ul class="list-group">
                            <c:forEach items="${ticket.seatList}" var="seat">
                                <li class="list-group-item d-flex justify-content-between align-items-center">
                                    Seat #${seat.id}
                                    <span class="badge bg-primary rounded-pill">
                                        <fmt:formatNumber value="${seat.price}" type="currency" currencySymbol="VND " />
                                    </span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
                
                <hr>
                
                <div class="row">
                    <div class="col-md-12 text-end">
                        <h4>Total Amount: <fmt:formatNumber value="${ticket.totalPrice}" type="currency" currencySymbol="VND " /></h4>
                    </div>
                </div>
                
                <div class="mt-4 text-center">
                    <p class="text-muted">Thank you for booking with us!</p>
                    <a href="/movies" class="btn btn-outline-primary me-2">Browse Movies</a>
                    <a href="/tickets/customer/${ticket.customerId}" class="btn btn-outline-secondary">My Tickets</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
