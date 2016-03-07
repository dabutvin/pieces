package com.dabutvin.pieces;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

public class DeserializePieceTask extends AsyncTask<String, Void, PieceModel>{

    private final PieceCallbackInterface callback;

    DeserializePieceTask(PieceCallbackInterface callback){
        this.callback = callback;
    }

    @Override
    protected PieceModel doInBackground(String... params) {
        String json = params[0];

        PieceModel piece = new PieceModel();

        try{
            JSONObject jsonObject = new JSONObject(json);
            JSONObject jsonPieceObject = jsonObject.getJSONObject("piece");

            piece.setId(jsonPieceObject.getInt("id"));
            piece.setTitle(jsonPieceObject.getString("title"));
            piece.setMedium(jsonPieceObject.getString("medium"));
            piece.setDescription(jsonPieceObject.getString("description"));
            piece.setMainImageUrl(jsonPieceObject.getString("mainImageUrl"));
            piece.setImageUrl2(jsonPieceObject.getString("imageUrl2"));
            piece.setUrl(jsonPieceObject.getString("url"));

            if (jsonPieceObject.has("artist")) {
                JSONObject jsonArtistObject = jsonPieceObject.getJSONObject("artist");
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

    @Override
    protected void onPostExecute(PieceModel result) {
        callback.onTaskFinished(result);
    }
}
