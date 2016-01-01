package com.dabutvin.pieces;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DeserializePieceDetailTask extends AsyncTask<String, Void, PieceDetailModel>{

    private final PieceDetailCallbackInterface callback;

    DeserializePieceDetailTask(PieceDetailCallbackInterface callback){
        this.callback = callback;
    }

    @Override
    protected PieceDetailModel doInBackground(String... params) {
        String json = params[0];

        PieceDetailModel pieceDetail = new PieceDetailModel();

        try{
            JSONObject jsonObject = new JSONObject(json);
            pieceDetail.setId(jsonObject.getInt("id"));
            pieceDetail.setArtist(jsonObject.getString("artist"));
            pieceDetail.setTitle(jsonObject.getString("title"));
            pieceDetail.setMedium(jsonObject.getString("medium"));
            pieceDetail.setDescription(jsonObject.getString("description"));

            JSONArray jsonPieceSrcsetArray = jsonObject.getJSONArray("srcset");
            List<String> pieceSrcSet = pieceDetail.getSrcset();
            for(int i =0; i<jsonPieceSrcsetArray.length();i++) {
                pieceSrcSet.add(jsonPieceSrcsetArray.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pieceDetail;
    }

    @Override
    protected void onPostExecute(PieceDetailModel result) {
        callback.onTaskFinished(result);
    }
}
