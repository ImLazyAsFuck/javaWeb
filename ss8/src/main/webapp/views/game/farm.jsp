<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Trang Trại</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0fff0;
            padding: 20px;
            background-image: url("https://i.pinimg.com/736x/d9/e9/8e/d9e98e13191bfa33f55b835effbaa9cc.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;
        }
        .farm-grid {
            display: grid;
            grid-template-columns: repeat(5, 1fr);
            gap: 15px;
        }
        .plot {
            border: 2px dashed #ccc;
            padding: 10px;
            background-color: #fff8dc;
            text-align: center;
            border-radius: 10px;
        }
        .plot img {
            max-width: 80px;
        }
        select, button {
            margin-top: 5px;
        }
    </style>
</head>
<body>
<h1 >🌾 Giao diện Game Nông Trại</h1>
<div class="mt-10">
    <a href="/game/home" class="text-sm text-blue-700 hover:underline">
        ⬅ Quay lại trang chủ nông trại
    </a>
</div>
<div class="farm-grid">
    <c:forEach var="plot" items="${plots}">
        <div class="plot">
            <h4>Ô đất #${plot.id}</h4>

            <c:choose>
                <c:when test="${plot.seeds == null}">
                    <form action="/game/farm" method="post">
                        <input type="hidden" name="plotId" value="${plot.id}" />
                        <label>Chọn hạt giống:</label><br/>
                        <select name="seedName">
                            <c:forEach var="seed" items="${seeds}">
                                <option value="${seed.seeds.id}">${seed.seeds.seedsName}</option>
                            </c:forEach>
                        </select><br/>
                        <button type="submit">🌱 Trồng</button>
                    </form>
                </c:when>

                <c:otherwise>
                    <p><strong>${plot.seeds.seedsName}</strong></p>
                    <img src="${plot.seeds.imageUrl}" alt="${plot.seeds.seedsName}" />
                    <p>Đang phát triển...</p>
                </c:otherwise>
            </c:choose>
        </div>
    </c:forEach>
</div>
</body>
</html>
