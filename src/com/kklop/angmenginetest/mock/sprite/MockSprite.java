package com.kklop.angmenginetest.mock.sprite;

import android.graphics.Bitmap;

import com.kklop.angmengine.game.sprite.Sprite;
import com.kklop.angmengine.game.sprite.bound.Bound;

public class MockSprite extends Sprite {

	public MockSprite(Bound bound, Bitmap bitmap, float x, float y, int fps, String type) {
		super(bound, bitmap, x, y, fps, type);
	}

	
}
