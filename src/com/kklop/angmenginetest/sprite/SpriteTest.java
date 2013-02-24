package com.kklop.angmenginetest.sprite;

import com.kklop.angmengine.game.sprite.Sprite;
import com.kklop.angmenginetest.mock.sprite.MockSprite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.AndroidTestCase;
import android.util.Log;

public class SpriteTest extends AndroidTestCase {
	public final String TAG = getClass().getName();
	
	public void testSpriteCollision() throws Exception {
		Resources res = getContext().getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost);
		Log.d(TAG, "Bitmap width is " + bmp.getWidth() + 
				" and bitmap height is " + bmp.getHeight());
		assertTrue("Bitmap is valid.", bmp != null && bmp.getWidth() > 0);
		Sprite sprite = new MockSprite(BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost), 25, 25, 60);
		Sprite sprite2 = new MockSprite(BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost), 23, 23, 60);
		assertTrue("Sprites have collided", sprite.collided(sprite2));
	}
}
