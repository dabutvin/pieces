package com.dabutvin.pieces;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DeserializePiecesTask extends AsyncTask<String, Void, List<PieceModel>> {

    private PiecesCallbackInterface callback;

    DeserializePiecesTask(PiecesCallbackInterface callback) {

        this.callback = callback;
    }

    @Override
    protected List<PieceModel> doInBackground(String... params) {
        String json = params[0];

        List<PieceModel> pieces = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonPiecesArray = jsonObject.getJSONArray("pieces");
            for(int i=0; i<jsonPiecesArray.length(); i++) {
                JSONObject jsonPieceObject = jsonPiecesArray.getJSONObject(i);
                PieceModel piece = new PieceModel();

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

                pieces.add(piece);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pieces;
    }

    @Override
    protected void onPostExecute(List<PieceModel> result) {
        Log.d("Deserializer", "Deserialized " + result.size() + " pieces");
        callback.onTaskFinished(result);
    }
}
