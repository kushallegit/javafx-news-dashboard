# Assignment2-Java3

# API= 1e417d82a32cef118105fb1a285d9404

# 📰 JavaFX News App

A modern news feed application built using **JavaFX**, **OkHttp**, and **Gson**. It fetches real-time headlines from the [GNews API](https://gnews.io/) and displays them with images, titles, descriptions, and links to full articles.

---

## 📸 Demo

![JavaFX News App Screenshot](screenshot.png) <!-- Add an actual screenshot of your app -->

---

## 🚀 Features

- Fetches top headlines from GNews
- Displays news articles with images, titles, and descriptions
- Scroll to load more articles (infinite scrolling)
- Opens full articles in the browser
- Shows a loading animation while fetching more
- Language filter set to English only

---

## 🧰 Tech Stack

- Java 17+
- JavaFX
- OkHttp
- Gson
- GNews API

---

## 🔧 How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/kushallegit/javafx-news-app.git
cd javafx-news-app

## 📥 2. Import into Your IDE

Open the project in **IntelliJ IDEA**, **Eclipse**, or **NetBeans**.

### 🛠️ Set up JavaFX SDK

Make sure to configure JavaFX in your IDE and add the following VM options:

```bash
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
