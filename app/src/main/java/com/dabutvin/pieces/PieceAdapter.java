package com.dabutvin.pieces;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class PieceAdapter extends BaseAdapter{
    private Context context;
    private List<PieceModel> pieces;

    public PieceAdapter(Context context, List<PieceModel> pieces){

        this.context = context;
        this.pieces = pieces;
    }
    @Override
    public int getCount() {
        return pieces.size();
    }

    @Override
    public Object getItem(int position) {
        return pieces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater =  (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_piece, parent, false);
        }

        ImageView imageView = (ImageView)convertView.findViewById(R.id.item_image);
        TextView titleTextView = (TextView)convertView.findViewById(R.id.item_title);
        TextView mediumTextView = (TextView)convertView.findViewById(R.id.item_medium);
        TextView artistTextView = (TextView)convertView.findViewById(R.id.item_artist);

        PieceModel piece = pieces.get(position);

        titleTextView.setText(piece.getTitle());
        mediumTextView.setText(", " + piece.getMedium());
        artistTextView.setText(piece.getArtist());
        Picasso.with(context).load(piece.getSrc()).into(imageView);

        return convertView;
    }
}
