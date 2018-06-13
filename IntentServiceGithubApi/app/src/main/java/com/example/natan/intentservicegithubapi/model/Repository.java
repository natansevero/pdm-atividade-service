package com.example.natan.intentservicegithubapi.model;

public class Repository {
    private String name;
    private String autorName;
    private String description;
    private String imageLink;

    public Repository(String name, String autorName, String description, String imageLink) {
        this.name = name;
        this.autorName = autorName;
        this.description = description;
        this.imageLink = imageLink;
    }

    public Repository() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAutorName() {
        return autorName;
    }

    public void setAutorName(String autorName) {
        this.autorName = autorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "name='" + name + '\'' +
                ", autorName='" + autorName + '\'' +
                ", description='" + description + '\'' +
                ", imageLink='" + imageLink + '\'' +
                '}';
    }
}
