package com.dabutvin.pieces;

import java.util.ArrayList;
import java.util.List;

public class PieceDetailModel {
    private int id;
    private String title;
    private String artist;
    private String medium;
    private String description;
    private List<String> srcset = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSrcset() {
        return srcset;
    }

    public void setSrcset(List<String> srcset) {
        this.srcset = srcset;
    }
}
