<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.model.WordGameModel" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Game Đoán Từ</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f8ff;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
        }
        
        .game-container {
            background-color: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 600px;
            margin-top: 30px;
            text-align: center;
        }
        
        h1 {
            color: #2c3e50;
            margin-bottom: 20px;
        }
        
        .word-display {
            font-size: 2.5rem;
            letter-spacing: 8px;
            margin: 30px 0;
            font-weight: bold;
            color: #3498db;
            font-family: monospace;
        }
        
        .input-container {
            margin: 20px 0;
        }
        
        input[type="text"] {
            padding: 10px 15px;
            font-size: 16px;
            border: 2px solid #3498db;
            border-radius: 5px;
            width: 60%;
            margin-right: 10px;
        }
        
        button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        
        button:hover {
            background-color: #2980b9;
        }
        
        .game-info {
            margin: 20px 0;
            font-size: 16px;
            color: #7f8c8d;
        }
        
        .guessed-words {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 10px;
            margin: 20px 0;
        }
        
        .guessed-word {
            background-color: #ecf0f1;
            padding: 5px 10px;
            border-radius: 5px;
            font-size: 14px;
        }
        
        .game-result {
            font-size: 24px;
            font-weight: bold;
            margin: 20px 0;
            padding: 10px;
            border-radius: 5px;
        }
        
        .win {
            background-color: #2ecc71;
            color: white;
        }
        
        .lose {
            background-color: #e74c3c;
            color: white;
        }
        
        .new-game-btn {
            background-color: #2ecc71;
            margin-top: 20px;
            padding: 12px 25px;
            font-size: 18px;
        }
        
        .new-game-btn:hover {
            background-color: #27ae60;
        }
        
        .remaining-guesses {
            font-size: 18px;
            color: #e74c3c;
            font-weight: bold;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <%
        // Lấy đối tượng game từ session
        WordGameModel gameModel = (WordGameModel) session.getAttribute("gameModel");
        
        // Nếu chưa có, khởi tạo trò chơi mới
        if (gameModel == null) {
            response.sendRedirect("guess-word");
            return;
        }
        
        boolean gameOver = gameModel.isGameOver();
        boolean won = gameModel.isWon();
        int remainingGuesses = gameModel.getRemainingGuesses();
        String currentGuess = gameModel.getCurrentGuessAsString();
        List<String> guessedWords = gameModel.getGuessedWords();
    %>
    
    <div class="game-container">
        <h1>Game Đoán Từ</h1>
        
        <div class="word-display">
            <%= currentGuess %>
        </div>
        
        <div class="remaining-guesses">
            Số lần đoán còn lại: <%= remainingGuesses %>
        </div>
        
        <% if (!gameOver) { %>
            <form action="guess-word" method="post" class="input-container">
                <input type="text" name="guess" placeholder="Nhập từ bạn đoán..." required autocomplete="off">
                <button type="submit">Đoán</button>
            </form>
        <% } else { %>
            <% if (won) { %>
                <div class="game-result win">
                    Chúc mừng! Bạn đã đoán đúng từ: <%= gameModel.getSecretWord() %>
                </div>
            <% } else { %>
                <div class="game-result lose">
                    Rất tiếc! Bạn đã thua. Từ bí mật là: <%= gameModel.getSecretWord() %>
                </div>
            <% } %>
            
            <form action="guess-word" method="post">
                <input type="hidden" name="newGame" value="true">
                <button type="submit" class="new-game-btn">Chơi lại</button>
            </form>
        <% } %>
        
        <% if (!guessedWords.isEmpty()) { %>
            <div class="game-info">
                <h3>Các từ đã đoán:</h3>
                <div class="guessed-words">
                    <% for (String word : guessedWords) { %>
                        <span class="guessed-word"><%= word %></span>
                    <% } %>
                </div>
            </div>
        <% } %>
    </div>
</body>
</html>
