package com.example.flashkey;

import java.util.ArrayList;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListKeyContact extends ArrayAdapter<String>{
	private final Activity context;
	private final ArrayList<String> name;
	private final ArrayList<Integer> imageId;

public CustomListKeyContact(Activity context,
	ArrayList<String> web, ArrayList<Integer> imageId) {
	super(context, R.layout.list_row_layout, web);
	this.context = context;
	this.name = web;
	this.imageId = imageId;
}
@Override
public View getView(int position, View view, ViewGroup parent) {
	LayoutInflater inflater = context.getLayoutInflater();
	View rowView= inflater.inflate(R.layout.list_row_layout, null, true);
	TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
	ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
	txtTitle.setGravity(Gravity.CENTER);
	txtTitle.setText(name.get(position));
	imageView.setImageResource(imageId.get(position));
	return rowView;
}

}
