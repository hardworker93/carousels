package com.example.carousels;

public class Item {
	
	
	String imageUri;
	String title;
	int resId;
	
	
	public Item(String imageUri,String title,int res){
		
		setImageUri(imageUri);
		setTitle(title);
		setResId(res);
	}
	
	
	public int getResId() {
		return resId;
	}


	public void setResId(int resId) {
		this.resId = resId;
	}


	public String getImageUri() {
		return imageUri;
	}
	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
