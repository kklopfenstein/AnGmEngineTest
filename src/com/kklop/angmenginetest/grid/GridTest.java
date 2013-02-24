package com.kklop.angmenginetest.grid;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;
import android.util.Log;

import com.kklop.angmengine.game.grid.Grid;
import com.kklop.angmengine.game.sprite.Sprite;
import com.kklop.angmenginetest.mock.sprite.MockSprite;

public class GridTest extends AndroidTestCase {
	
	public final String TAG = getClass().getName();
	Resources res;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		res = getContext().getResources();
	}
	
	public void testGrid() throws Exception {
		Grid grid = new Grid(1250, 750, 50);
		Resources res = getContext().getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost);
		Log.d(TAG, "Bitmap width is " + bmp.getWidth() + 
				" and bitmap height is " + bmp.getHeight());
		assertTrue("Bitmap is valid.", bmp != null && bmp.getWidth() > 0);
		Sprite sprite = new MockSprite(BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost), 25, 25, 60);
		assertTrue("Bot left crnr is not null", sprite.getBotLeftCrnr().x > 0 
				&& sprite.getBotLeftCrnr().y > 0);
		Sprite sprite2 = new MockSprite(BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost), 25, 25, 60);
		grid.addSprite(sprite);
		grid.addSprite(sprite2);
		assertTrue("Should have grid id", sprite.getGridId() == 0);
		assertTrue("Should have grid id", sprite2.getGridId() == 1);
	}
	
}
