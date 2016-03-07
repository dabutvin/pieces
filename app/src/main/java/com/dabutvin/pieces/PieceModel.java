package com.dabutvin.pieces;

import org.json.JSONException;
import org.json.JSONObject;

public class PieceModel {
    private int id;
    private String title;
    private String medium;
    private String description;
    private String mainImageUrl;
    private String imageUrl2;
    private ArtistModel artist;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainImageUrl() {
        return mainImageUrl;
    }

    public void setMainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public ArtistModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistModel artist) {
        this.artist = artist;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static PieceModel ToPieceModel(JSONObject jsonObject) {
        PieceModel piece = new PieceModel();

        try {
            piece.setId(jsonObject.getInt("id"));
            piece.setTitle(jsonObject.getString("title"));
            piece.setMedium(jsonObject.getString("medium"));
            piece.setDescription(jsonObject.getString("description"));
            piece.setMainImageUrl(jsonObject.getString("mainImageUrl"));
            piece.setImageUrl2(jsonObject.getString("imageUrl2"));
            piece.setUrl(jsonObject.getString("url"));

            if (jsonObject.has("artist")) {
                JSONObject jsonArtistObject = jsonObject.getJSONObject("artist");
                ArtistModel artist = new ArtistModel();

                artist.setId(jsonArtistObject.getInt("id"));
                artist.setUsername(jsonArtistObject.getString("username"));
                artist.setUrl(jsonArtistObject.getString("url"));

                piece.setArtist(artist);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return piece;
    }
}
