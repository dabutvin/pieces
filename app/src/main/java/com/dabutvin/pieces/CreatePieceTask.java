package com.dabutvin.pieces;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Dan on 3/6/2016.
 */
public class CreatePieceTask extends AsyncTask<PieceModel, Void, Boolean> {

    private BooleanCallbackInterface callback;

    CreatePieceTask(BooleanCallbackInterface callback){

        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(PieceModel... params) {

        PieceModel piece = params[0];
        String url = "http://pieces.azurewebsites.net/pieces/" + piece.getArtist().getId();

        HttpURLConnection urlConnection = null;
        Boolean error = false;



        Log.d("UPLOAD", url);
        try{
            urlConnection = (HttpURLConnection)new URL(url).openConnection();

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);

            OutputStream outputStream = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.write(formPost(piece));

            writer.flush();
            writer.close();
            outputStream.close();
            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                 // YAY!
            }

        }catch (MalformedURLException e){
            e.printStackTrace();
            error = true;
        }catch (IOException e){
            e.printStackTrace();
            error = true;
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }

        return !error;
    }

    private String formPost(PieceModel piece) {

        StringBuilder result = new StringBuilder();

        result.append("Title=");
        result.append(piece.getTitle());

        result.append("&Medium=");
        result.append(piece.getMedium());

        result.append("&Description=");
        result.append(piece.getDescription());

        result.append("&MainImageUrl=");
        result.append(piece.getMainImageUrl());

        result.append("&ImageUrl2=");
        result.append(piece.getImageUrl2());


        return result.toString();
    }

    @Override
    protected void onPostExecute(Boolean result){
        callback.onTaskFinished(result);
    }
}
