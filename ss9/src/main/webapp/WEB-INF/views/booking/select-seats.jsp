<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Select Seats</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .seat-container {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            justify-content: center;
            margin: 20px 0;
        }
        .seat {
            width: 50px;
            height: 50px;
            display: flex;
            align-items: center;
            justify-content: center;
            border: 1px solid #ccc;
            cursor: pointer;
            font-weight: bold;
        }
        .seat.available {
            background-color: #d4edda;
        }
        .seat.booked {
            background-color: #f8d7da;
            cursor: not-allowed;
        }
        .seat.selected {
            background-color: #cce5ff;
            border: 2px solid #0d6efd;
        }
        .movie-info {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 8px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Select Your Seats</h1>
        
        <div class="movie-info">
            <h3>${movieTitle}</h3>
            <div class="row">
                <div class="col-md-4">
                    <p><strong>Screen Room:</strong> ${screenRoomName}</p>
                </div>
                <div class="col-md-4">
                    <p><strong>Show Time:</strong> ${showTime}</p>
                </div>
                <div class="col-md-4">
                    <p><strong>Format:</strong> ${format}</p>
                </div>
            </div>
        </div>
        
        <div class="text-center mb-4">
            <div class="d-inline-block mx-2">
                <div class="seat available d-inline-block"></div> Available
            </div>
            <div class="d-inline-block mx-2">
                <div class="seat booked d-inline-block"></div> Booked
            </div>
            <div class="d-inline-block mx-2">
                <div class="seat selected d-inline-block"></div> Selected
            </div>
        </div>
        
        <div class="text-center mb-4">
            <div class="bg-dark text-white p-2 w-75 mx-auto">SCREEN</div>
        </div>
        
        <div class="seat-container">
            <c:forEach items="${seats}" var="seat">
                <div class="seat ${seat.status ? 'booked' : 'available'}" 
                     data-seat-id="${seat.id}" 
                     data-price="${seat.price}">
                    ${seat.id}
                </div>
            </c:forEach>
        </div>
        
        <div class="row mt-4">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        Selected Seats
                    </div>
                    <div class="card-body">
                        <div id="selected-seats-container">
                            <p>No seats selected</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        Price Summary
                    </div>
                    <div class="card-body">
                        <p><strong>Total Amount:</strong> <span id="total-price">0</span> VND</p>
                        <form id="booking-form">
                            <input type="hidden" id="schedule-id" value="${scheduleId}">
                            <input type="hidden" id="customer-id" value="${sessionScope.customer.id}">
                            <button type="button" id="book-button" class="btn btn-primary btn-block" disabled>Book Tickets</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            const selectedSeats = [];
            
            $('.seat.available').click(function() {
                const seatId = $(this).data('seat-id');
                const seatPrice = $(this).data('price');
                
                if ($(this).hasClass('selected')) {
                    // Deselect the seat
                    $(this).removeClass('selected');
                    const index = selectedSeats.findIndex(seat => seat.id === seatId);
                    if (index !== -1) {
                        selectedSeats.splice(index, 1);
                    }
                } else {
                    // Select the seat
                    $(this).addClass('selected');
                    selectedSeats.push({
                        id: seatId,
                        price: seatPrice
                    });
                }
                
                updateSelectedSeatsDisplay();
                updateTotalPrice();
            });
            
            function updateSelectedSeatsDisplay() {
                const container = $('#selected-seats-container');
                if (selectedSeats.length === 0) {
                    container.html('<p>No seats selected</p>');
                    $('#book-button').prop('disabled', true);
                } else {
                    let html = '<ul>';
                    selectedSeats.forEach(seat => {
                        html += `<li>Seat ${seat.id} - ${seat.price} VND</li>`;
                    });
                    html += '</ul>';
                    container.html(html);
                    $('#book-button').prop('disabled', false);
                }
            }
            
            function updateTotalPrice() {
                const totalPrice = selectedSeats.reduce((total, seat) => total + seat.price, 0);
                $('#total-price').text(totalPrice.toLocaleString());
            }
            
            $('#book-button').click(function() {
                if (selectedSeats.length === 0) {
                    alert('Please select at least one seat.');
                    return;
                }
                
                const seatIds = selectedSeats.map(seat => seat.id);
                const scheduleId = $('#schedule-id').val();
                const customerId = $('#customer-id').val();
                
                if (!customerId) {
                    window.location.href = '/login?redirect=/tickets/book/' + scheduleId;
                    return;
                }
                
                $.ajax({
                    url: '/tickets/book',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        customerId: customerId,
                        scheduleId: scheduleId,
                        seatIds: seatIds
                    }),
                    success: function(response) {
                        if (response.success) {
                            alert('Booking successful! Total price: ' + response.totalPrice + ' VND');
                            window.location.href = '/tickets/' + response.ticket.id;
                        } else {
                            alert('Booking failed: ' + response.error);
                        }
                    },
                    error: function(xhr) {
                        alert('Error: ' + xhr.responseJSON.error);
                    }
                });
            });
        });
    </script>
</body>
</html>
