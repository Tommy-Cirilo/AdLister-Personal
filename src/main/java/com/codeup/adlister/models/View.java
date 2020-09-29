package com.codeup.adlister.models;

class View {
    private String user_id, title, description;

    public View(String user_id, String title, String description) {
        this.user_id = user_id;
        this.title = title;
        this.description = description;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
