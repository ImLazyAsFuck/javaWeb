<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đặt Vé – Phòng Chiếu Số 8</title>
    <style>
        .seat, label.seat.available, button.seat.booked {
            display:inline-block; width:40px; height:40px;
            line-height:40px; text-align:center; margin:4px;
            border-radius:4px; font-weight:bold; user-select:none;
            font-family:Arial,sans-serif;
        }
        .seat-checkbox { display:none; }
        label.seat.available {
            background:#eee; color:#000; border:1px solid #ccc;
            cursor:pointer;
        }
        button.seat.booked {
            background:#999; color:#fff; border:1px solid #666;
            cursor:not-allowed;
        }
        .seat-checkbox:checked + label.seat.available {
            background:#007bff; color:#fff; border-color:#007bff;
        }
        .screen { margin:20px 0; font-weight:bold; }
        button.pay { margin-top:20px; padding:8px 16px; }
        .error { color:red; margin-top:10px; font-family:Arial,sans-serif; }
    </style>
</head>
<body>
<h1>Đặt Vé – Phòng Chiếu Số 8</h1>

<form action="b9" method="post">
    <div class="screen">MÀN HÌNH</div>
    <div>
        <c:forEach var="s" items="${seats}" varStatus="st">
            <c:choose>
                <c:when test="${s.booked}">
                    <button type="button" class="seat booked">${s.name}</button>
                </c:when>

                <c:otherwise>
                    <input type="checkbox"
                           name="selectedSeats"
                           value="${s.id}"
                           id="ck_${s.id}"
                           class="seat-checkbox"/>
                    <label for="ck_${s.id}" class="seat available">${s.name}</label>
                </c:otherwise>
            </c:choose>

            <c:if test="${(st.index + 1) % 10 == 0}"><br/></c:if>
        </c:forEach>
    </div>

    <button type="submit" class="pay">Thanh toán</button>
</form>

<c:if test="${not empty error}">
    <div class="error">${error}</div>
</c:if>

<c:if test="${not empty total}">
    <h2>Tổng tiền phải thanh toán: ${total} VND</h2>
    <p>Ghế bạn đã chọn:</p>
    <ul>
        <c:forEach var="c" items="${chosenSeats}">
            <li>${c.name} – ${c.price} VND</li>
        </c:forEach>
    </ul>
</c:if>
<a href="/">Back</a>
</body>
</html>