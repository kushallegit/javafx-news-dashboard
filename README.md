# Assignment2-Java3

---

# API= 1e417d82a32cef118105fb1a285d9404
Note: It's my free version API, do not use this one, please use your own API key, don't exploit this API key. :)

---

# 📰 JavaFX News App

A modern news feed application built using **JavaFX**, **OkHttp**, and **Gson**. It fetches real-time headlines from the [GNews API](https://gnews.io/) and displays them with images, titles, descriptions, and links to full articles.

---

## 📸 Demo

![JavaFX News App Screenshot](images/app.png) 

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
git clone https://github.com/kushallegit/Assignment2-Java3.git
cd Assignment2-Java3
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

![My Folder](images/folder_structure.png)

---

## 🔐 API Key

This app uses the [GNews API](https://gnews.io/).

Your key is already included in `NewsService.java`:

```java
private static final String API_URL =
  "https://gnews.io/api/v4/top-headlines?lang=en&token=1e417d82a32cef118105fb1a285d9404";
```

## 📸 Demo

My API request limit is 100 requests per day, so if i make more than 100 requests in a day, it gives me this error :(

![API_Error](images/api_limit.png)



---

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


