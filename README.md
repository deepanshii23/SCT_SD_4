# 📚 Book Scraper in Java (Without Jsoup)

This project is a simple **Java-based web scraper** that fetches product details (books) from the website [Books to Scrape](http://books.toscrape.com/) and saves the extracted data into a **text file** in a clean, human-readable format.

Unlike other scrapers, this project **does not use external libraries** such as Jsoup. Instead, it relies only on **Java’s built-in HttpClient** and **regular expressions** for HTML parsing.

---

## 📖 Project Description
- The program sends an HTTP request to the website.
- It extracts key product details from the HTML:
  - **Book Title**
  - **Price**
  - **Availability**
- Each product is written in a text file as **key → value pairs**, e.g.:

```
"Title" -> A Light in the Attic
"Price" -> £51.77
"Availability" -> In stock
```

## 📂 Project Structure

```
BookScraper/
├── ScraperKeyValue.java # Main Java source file
├── products.txt # Output file (generated after running)
└── README.md # Documentation (this file)
```

## ▶️ How to Run the Program

### **Option 1: Run from Terminal**
1. Save the code as `ScraperKeyValue.java`.
2. Open terminal/command prompt in the project folder.
3. Compile the program:
   ```bash
   javac ScraperKeyValue.java
   java ScraperKeyValue
   ```
After execution, you’ll find a file called products.txt in the same folder.
