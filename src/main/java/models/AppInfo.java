package models;

public class AppInfo {
    private String title;
    private String category;
    private String author;
    private int numberOfDownloads;
    private String description;

    public int getNumberOfDownloads() {
        return numberOfDownloads;
    }

    public void setNumberOfDownloads(String numberOfDownloads) {
        String downloadsNumber = explode(numberOfDownloads, ":", 1);
        this.numberOfDownloads = Integer.parseInt(downloadsNumber);
    }

    public String getDescription() {
        return explode(description, ":", 1);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return explode(category, ":", 1);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return explode(author, ":", 1);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String explode(String string, String separator, int index) {
        String[] array = string.split(separator);
        return array[index].trim();
    }
}
