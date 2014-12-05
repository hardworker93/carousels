package com.example.carousels;

import java.util.List;

import com.example.carousels.R;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {

	public static final String LOGTAG ="carousels";
	private List<List<Item>> mRows;
	Context mContext;
	public MainRecyclerAdapter(List<List<Item>> objects,Context context) {
		mContext = context;
		mRows = objects;

	}

	static class ViewHolder extends RecyclerView.ViewHolder{
		public  RecyclerView mRecyclerViewRow;
		public ViewHolder(View itemView) {
			super(itemView);
			mRecyclerViewRow =(RecyclerView)itemView.findViewById(R.id.recyclerView_row);
		}
	}

	@Override
	public int getItemCount() {

		return mRows.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		List<Item> RowItems = mRows.get(position);
		LinearLayoutManager layoutManager = new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false);		
		holder.mRecyclerViewRow.setLayoutManager(layoutManager);
		holder.mRecyclerViewRow.setHasFixedSize(true);
		RowRecyclerAdapter rowsRecyclerAdapter = new RowRecyclerAdapter(mContext,RowItems);   		
		holder.mRecyclerViewRow.setAdapter(rowsRecyclerAdapter);
		
		
		final RecyclerView finalRecyclerView = holder.mRecyclerViewRow;
		
		finalRecyclerView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged (RecyclerView recyclerView, int newState){
				
				switch(newState){
					
				case RecyclerView.SCROLL_STATE_IDLE:  
					
					float targetBottomPosition1 = finalRecyclerView.getX();
					float targetBottomPosition2 = finalRecyclerView.getX() + finalRecyclerView.getWidth();
					
					Log.i(LOGTAG,"targetBottomPosition1 = " + targetBottomPosition1);
					Log.i(LOGTAG,"targetBottomPosition2 = " + targetBottomPosition2);
					
					View v1 = finalRecyclerView.findChildViewUnder(targetBottomPosition1,0);
					View v2 = finalRecyclerView.findChildViewUnder(targetBottomPosition2,0);
					
					float x1 = targetBottomPosition1;
					if(v1!=null){
						x1 =v1.getX(); 
					}
					
					float x2 = targetBottomPosition2;
					if(v2!=null){
						x2 =v2.getX(); 
					}
					
					
					Log.i(LOGTAG,"x1 = " + x1);
					Log.i(LOGTAG,"x2 = " + x2); 
					
					float dx1 = Math.abs(finalRecyclerView.getX()-x1 );
					float dx2 = Math.abs(finalRecyclerView.getX()+ finalRecyclerView.getWidth()-x2);
					
					Log.i(LOGTAG,"dx1 = " + dx1);
					Log.i(LOGTAG,"dx2 = " + dx2);
					
					float visiblePortionOfItem1 = 0;
					float visiblePortionOfItem2 = 0;
					
					if(x1<0 && v1 != null){
						visiblePortionOfItem1 = v1.getWidth() - dx1;
					}
					
					if(v2 != null){
						visiblePortionOfItem2 = v2.getWidth() - dx2;
					}
					
					Log.i(LOGTAG,"visiblePortionOfItem1 = " + visiblePortionOfItem1);
					Log.i(LOGTAG,"visiblePortionOfItem2 = " + visiblePortionOfItem2);
					
					int position = 0;	
					if(visiblePortionOfItem1>=visiblePortionOfItem2){
						position = finalRecyclerView.getChildPosition(finalRecyclerView.findChildViewUnder(targetBottomPosition1,0));
					}else{
			
						position = finalRecyclerView.getChildPosition(finalRecyclerView.findChildViewUnder(targetBottomPosition2,0));
					}
					finalRecyclerView.scrollToPosition(position);
					break;
				
				case RecyclerView.SCROLL_STATE_DRAGGING:
					 
					break;
				
				case RecyclerView.SCROLL_STATE_SETTLING:
					
					break;
				
				}
			}

			@Override
			public void onScrolled (RecyclerView recyclerView, int dx, int dy){

//				Log.i(LOGTAG,"X = " + dx + " and Y = " + dy);
			}
		});
	}


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
		LayoutInflater inflater =    
				(LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		View convertView = inflater.inflate(R.layout.row, parent, false);
		return new ViewHolder(convertView);
	}

}
