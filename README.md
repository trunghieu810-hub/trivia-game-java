
# Trivia Quiz Game

A simple Java console-based trivia quiz game developed as a university Object-Oriented Programming project.

## Overview

This project allows up to two players to participate in a trivia quiz game. Players can be added through a menu, answer randomly selected trivia questions loaded from a text file, and receive scores based on their answers.

## Features

- Add up to two players
- Load trivia questions from a text file
- Ask random questions during the game
- Award 2 points for correct answers
- Deduct 1 point for incorrect answers
- Prevent scores from going below 0
- Display current player details and final results
- Automatically handle single-player answering

## Project Files

- `TriviaGame.java` - main game logic and menu handling
- `Player.java` - player details and score management
- `Question.java` - question and answer model
- `TriviaQuestions.txt` - trivia question data file

## Requirements

- Java JDK 8 or above
- A Java IDE such as IntelliJ IDEA, Eclipse, or VS Code

## How to Run in an IDE

1. Download or clone this repository
2. Open the project folder in your Java IDE
3. Make sure `TriviaQuestions.txt` remains in the project folder
4. Compile and run `TriviaGame.java`
5. Use the console to interact with the menu

## How to Run from Terminal

Compile:
```bash
javac TriviaGame.java Player.java Question.java

