package ba.unsa.etf.rpr;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class CourseNews {

    private LocalDate date;
    private String news;

    public CourseNews(LocalDate date, String news) {
        this.date = date;
        this.news = news;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    @Override
    public String toString() {
        String s = "";
        String d = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
        s+=d+" - "+news;
        return s;
    }
}
