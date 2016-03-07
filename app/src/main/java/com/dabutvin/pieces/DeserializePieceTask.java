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
            JSONObject jsonObject = new JSONObject(json).getJSONObject("piece");
            piece = PieceModel.ToPieceModel(jsonObject);
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
