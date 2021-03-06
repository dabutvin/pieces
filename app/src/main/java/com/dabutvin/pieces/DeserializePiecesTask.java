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
                pieces.add(PieceModel.ToPieceModel(jsonPieceObject));
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
