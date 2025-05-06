package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WordGameModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final List<String> SECRET_WORDS = Arrays.asList(
            "programming", "computer", "keyboard", "monitor", "internet",
            "smartphone", "algorithm", "technology", "database", "javascript",
            "vietnam", "hanoi", "hochiminh", "danang", "nhatrang",
            "coding", "developer", "application", "software", "network"
    );
    
    private String secretWord;
    private char[] currentGuess;
    private int remainingGuesses;
    private List<String> guessedWords;
    private boolean gameOver;
    private boolean won;
    public WordGameModel() {
        initGame();
    }
    
    // Khởi tạo trò chơi mới
    public void initGame() {
        // Chọn từ bí mật ngẫu nhiên
        Random random = new Random();
        secretWord = SECRET_WORDS.get(random.nextInt(SECRET_WORDS.size()));
        
        // Khởi tạo trạng thái hiện tại của từ đang đoán
        currentGuess = new char[secretWord.length()];
        Arrays.fill(currentGuess, '_');
        
        // Khởi tạo các giá trị khác
        remainingGuesses = 6; // Số lần đoán tối đa
        guessedWords = new ArrayList<>();
        gameOver = false;
        won = false;
    }

    public void makeGuess(String guess) {
        if (gameOver) {
            return;
        }
        
        if (guess == null || guess.trim().isEmpty()) {
            return;
        }
        
        guess = guess.toLowerCase().trim();

        if (guessedWords.contains(guess)) {
            return;
        }

        guessedWords.add(guess);

        if (guess.equals(secretWord)) {
            for (int i = 0; i < secretWord.length(); i++) {
                currentGuess[i] = secretWord.charAt(i);
            }
            won = true;
            gameOver = true;
            return;
        }

        boolean foundMatch = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (i < guess.length() && secretWord.charAt(i) == guess.charAt(i)) {
                currentGuess[i] = secretWord.charAt(i);
                foundMatch = true;
            }
        }

        if (!foundMatch) {
            remainingGuesses--;
        }

        boolean allGuessed = true;
        for (char c : currentGuess) {
            if (c == '_') {
                allGuessed = false;
                break;
            }
        }
        
        if (allGuessed) {
            won = true;
            gameOver = true;
        }

        if (remainingGuesses <= 0) {
            gameOver = true;
        }
    }

    public String getSecretWord() {
        return secretWord;
    }
    
    public char[] getCurrentGuess() {
        return currentGuess;
    }
    
    public String getCurrentGuessAsString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentGuess.length; i++) {
            sb.append(currentGuess[i]);
            if (i < currentGuess.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    
    public int getRemainingGuesses() {
        return remainingGuesses;
    }
    
    public List<String> getGuessedWords() {
        return guessedWords;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public boolean isWon() {
        return won;
    }
}
