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
```
## 📥 2. Import into Your IDE

Open the project in **IntelliJ IDEA**, **Eclipse**, or **NetBeans**.

### 🛠️ 3. Set up JavaFX SDK

Make sure to configure JavaFX in your IDE and add the following VM options:

```bash
--module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml
```

## ▶️ 4. Build and Run

Run `Main.java` to launch the application.

---

## 📦 Project Structure

com.java3/ ├── Main.java # JavaFX entry point ├── NewsService.java # Fetches news data from GNews API ├── model/ │ ├── NewsArticle.java # Model for each news article │ └── NewsResponse.java # Wrapper for API response └── view/ └── NewsView.java # Builds the JavaFX UI

---

## 🔐 API Key

This app uses the [GNews API](https://gnews.io/).

Your key is already included in `NewsService.java`:

```java
private static final String API_URL =
  "https://gnews.io/api/v4/top-headlines?lang=en&token=1e417d82a32cef118105fb1a285d9404";
```
## 📌 To Do

- [ ] Add topic filters (e.g., technology, sports)
- [ ] Add search functionality
- [ ] Show error pop-ups for API failures
- [ ] Paginate instead of infinite scroll

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---

## 💬 Feedback

Got ideas or bugs? Feel free to [open an issue](../../issues) or submit a pull request. Contributions are welcome!


