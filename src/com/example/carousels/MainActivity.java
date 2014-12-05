package com.example.carousels;

import java.util.ArrayList;
import java.util.List;

import com.example.carousels.R;



import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.Log;
import android.view.View;


public class MainActivity extends Activity {

	public static final String LOGTAG ="carousels";
	
	int numOfRows = 11;
	int numOfColumns = 9;
	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private LinearLayoutManager mLayoutManager;
	boolean scrolling;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);   
		mRecyclerView = (RecyclerView)findViewById(R.id.recyclerview_rootview);
		mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);		
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setHasFixedSize(true);

		List<List<Item>> listOfListOfItems = new ArrayList<List<Item>>();

		for(int i = 0 ; i<numOfRows ; i++){
			List<Item> listOfItems = new ArrayList<Item>();
			for(int j = 0 ; j<numOfColumns ; j++){
				int drawableResourceId = this.getResources().getIdentifier("img"+String.valueOf(1+j + (10*i)), "drawable", this.getPackageName());
				Item item = new Item("img"+String.valueOf(j+1),String.valueOf(1+j + (10*i)),drawableResourceId);	
				listOfItems.add(item);
			}
			listOfListOfItems.add(listOfItems);
		}

		MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(listOfListOfItems,this);   		
		mRecyclerView.setAdapter(mainRecyclerAdapter);

		mRecyclerView.setOnScrollListener(new OnScrollListener() {
			@Override
			public void onScrollStateChanged (RecyclerView recyclerView, int newState){
				
				switch(newState){
					
				case RecyclerView.SCROLL_STATE_IDLE:  
					
					Log.i(LOGTAG,"X = " + (mRecyclerView.getX() + mRecyclerView.getWidth() )+ " and Y = " + (mRecyclerView.getY()+ mRecyclerView.getHeight()));
					float targetBottomPosition1 = mRecyclerView.getY();
					float targetBottomPosition2 = mRecyclerView.getY() + mRecyclerView.getHeight();
					
					Log.i(LOGTAG,"targetBottomPosition1 = " + targetBottomPosition1);
					Log.i(LOGTAG,"targetBottomPosition2 = " + targetBottomPosition2);
					
					View v1 = mRecyclerView.findChildViewUnder(500, targetBottomPosition1);
					View v2 = mRecyclerView.findChildViewUnder(500, targetBottomPosition2);
					
					float y1 = targetBottomPosition1;
					if(v1!=null){
						y1 =v1.getY(); 
					}
					
					float y2 = targetBottomPosition2;
					if(v2!=null){
						y2 =v2.getY(); 
					}
					
					Log.i(LOGTAG,"y1 = " + y1);
					Log.i(LOGTAG,"y2 = " + y2); 
					
					float dy1 = Math.abs(y1-mRecyclerView.getY() );
					float dy2 = Math.abs(y2-(mRecyclerView.getY()+ mRecyclerView.getHeight()));
					
					Log.i(LOGTAG,"dy1 = " + dy1);
					Log.i(LOGTAG,"dy2 = " + dy2);
					
					float visiblePortionOfItem1 = 0;
					float visiblePortionOfItem2 = 0;
					
					if(y1<0 && v1 != null){
						visiblePortionOfItem1 = v1.getHeight() - dy1;
					}
					
					if(v2 != null){
						visiblePortionOfItem2 = v2.getHeight() - dy2;
					}
					
					
					int position = 0;	
					if(visiblePortionOfItem1<=visiblePortionOfItem2){
						position = mRecyclerView.getChildPosition(mRecyclerView.findChildViewUnder(500, targetBottomPosition1));
					}else{
			
						position = mRecyclerView.getChildPosition(mRecyclerView.findChildViewUnder(500, targetBottomPosition2));
					}
					mRecyclerView.scrollToPosition(position);

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


}
