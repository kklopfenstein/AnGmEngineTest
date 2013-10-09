package com.kklop.angmenginetest.mock.sprite;

import android.content.res.Resources;

import com.kklop.angmengine.game.exception.GameException;
import com.kklop.angmengine.game.sprite.Sprite;
import com.kklop.angmengine.game.sprite.bound.Bound;

public class MockSprite extends Sprite {

	public MockSprite(Bound bound, int bmp, float x, float y, int fps, String type, Resources res) throws GameException {
		super(bound, bmp, x, y, fps, type, res);
	}

	
}
