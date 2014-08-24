/*******************************************************************************
 * Copyright 2012-2014 Kevin Klopfenstein.
 *
 * This file is part of AnGmEngine.
 *
 * AnGmEngine is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AnGmEngine is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AnGmEngine.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package com.kklop.angmenginetest.sprite;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.test.AndroidTestCase;
import android.util.Log;

import com.kklop.angmengine.game.sprite.Sprite;
import com.kklop.angmengine.game.sprite.bound.Bound;
import com.kklop.angmengine.game.sprite.bound.rect.RectBound;
import com.kklop.angmengine.game.sprite.hitbox.HitBox;
import com.kklop.angmenginetest.mock.sprite.MockSprite;

public class SpriteTest extends AndroidTestCase {
	public final String TAG = getClass().getName();
	
	public static final Bound BOUND = new RectBound(new PointF(0,0), 
			new PointF(1800,600));
	
	public void testSpriteCollision() throws Exception {
		Resources res = getContext().getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost);
		Log.d(TAG, "Bitmap width is " + bmp.getWidth() + 
				" and bitmap height is " + bmp.getHeight());
		assertTrue("Bitmap is valid.", bmp != null && bmp.getWidth() > 0);
		Sprite sprite = new MockSprite(BOUND, com.kklop.angmenginetest.R.drawable.ghost, 25, 25, 60, null, res);
		Sprite sprite2 = new MockSprite(BOUND, com.kklop.angmenginetest.R.drawable.ghost, 23, 23, 60, null, res);
		assertTrue("Sprites have collided", sprite.collided(sprite2));
	}
	
	public void testStaticSpriteUpdate()  throws Exception {
		Resources res = getContext().getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost);
		Log.d(TAG, "Bitmap width is " + bmp.getWidth() + 
				" and bitmap height is " + bmp.getHeight());
		assertTrue("Bitmap is valid.", bmp != null && bmp.getWidth() > 0);
		Sprite sprite = new MockSprite(BOUND, com.kklop.angmenginetest.R.drawable.ghost, 25, 25, 60, null, res);
		Thread.sleep(100);
		sprite.update(System.currentTimeMillis(), 120, 120, 2, false);
		assertTrue("Sprite has moved.", sprite.getX() > 25 
				&& sprite.getY() > 25);
	}
	
	public void testSpriteCollidedHitBox() throws Exception {
		Resources res = getContext().getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.grave);
		Log.d(TAG, "Bitmap width is " + bmp.getWidth() + 
				" and bitmap height is " + bmp.getHeight());
		assertTrue("Bitmap is valid.", bmp != null && bmp.getWidth() > 0);
		Sprite sprite1 = new MockSprite(BOUND, com.kklop.angmenginetest.R.drawable.ghost, 25, 25, 60, null, res);
		Sprite sprite2 = new MockSprite(BOUND, com.kklop.angmenginetest.R.drawable.ghost, 25, 25, 60, null, res);
		assertTrue("Sprites are colliding.", sprite1.collided(sprite2));
		sprite2.addHitbox(new HitBox(0, (bmp.getHeight()-1), 
				bmp.getWidth(), bmp.getHeight()));
		assertTrue("Sprites are colliding.", sprite1.collided(sprite2));
		sprite2.setY(sprite2.getY() + 185);
		assertFalse("Sprites are no longer colliding.", 
				sprite1.collided(sprite2));
	}
}
