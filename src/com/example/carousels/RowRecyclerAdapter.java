package com.example.carousels;

import java.util.List;

import com.example.carousels.R;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class RowRecyclerAdapter extends RecyclerView.Adapter<RowRecyclerAdapter.ViewHolder> {


	private List<Item> mItems;
	Context mContext;
	public RowRecyclerAdapter(Context context,List<Item> objects) {
		mContext = context;
		mItems = objects;

	}

	static class ViewHolder extends RecyclerView.ViewHolder{
		public  ImageView mImageView;
		public  TextView mTextView;
		public View rootView;
		public ViewHolder(View itemView) {
			super(itemView);
			rootView = itemView;
			mImageView =(ImageView)itemView.findViewById(R.id.image);
			mTextView =(TextView)itemView.findViewById(R.id.title);
		}
	}

	@Override
	public int getItemCount() {

		return mItems.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		Item item = mItems.get(position);
		
//		Picasso.with(mContext).load(item.getImageUri()).into(holder.mImageView);
//		int drawableResourceId = mContext.getResources().getIdentifier(item.getImageUri(), "drawable", mContext.getPackageName());
		Picasso.with(mContext).load(item.getResId()).into(holder.mImageView);
		holder.mTextView.setText(item.getTitle());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
		LayoutInflater inflater =    
				(LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View convertView = inflater.inflate(R.layout.item, parent, false);
		return new ViewHolder(convertView);
	}

}
