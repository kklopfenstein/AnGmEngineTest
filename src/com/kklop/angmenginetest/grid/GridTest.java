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
package com.kklop.angmenginetest.grid;

import java.util.List;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.test.AndroidTestCase;
import android.util.Log;

import com.kklop.angmengine.game.grid.Grid;
import com.kklop.angmengine.game.sprite.Sprite;
import com.kklop.angmengine.game.sprite.bound.Bound;
import com.kklop.angmengine.game.sprite.bound.rect.RectBound;
import com.kklop.angmenginetest.mock.sprite.MockSprite;

public class GridTest extends AndroidTestCase {
	
	public final String TAG = getClass().getName();
	Resources res;
	
	public static final Bound BOUND1 = new RectBound(new PointF(0,0), 
			new PointF(1250, 750));
	public static final Bound BOUND2 = new RectBound(new PointF(0,0), 
			new PointF(1800, 900));
	
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
		Sprite sprite = new MockSprite(BOUND1, com.kklop.angmenginetest.R.drawable.ghost, 25, 25, 60, null, res);
		assertTrue("Bot left crnr is not null", sprite.getBotLeftCrnr().x > 0 
				&& sprite.getBotLeftCrnr().y > 0);
		Sprite sprite2 = new MockSprite(BOUND1, com.kklop.angmenginetest.R.drawable.ghost, 25, 25, 60, null, res);
		grid.addSprite(sprite);
		grid.addSprite(sprite2);
		assertTrue("Should have grid id", sprite.getGridId() == 0);
		assertTrue("Should have grid id", sprite2.getGridId() == 1);
	}
	
	public void testGridCollision() throws Exception {
		Grid grid = new Grid(1800, 900, 90);
		Resources res = getContext().getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost);
		Log.d(TAG, "Bitmap width is " + bmp.getWidth() + 
				" and bitmap height is " + bmp.getHeight());
		assertTrue("Bitmap is valid.", bmp != null && bmp.getWidth() > 0);
		Sprite sprite = new MockSprite(BOUND2, com.kklop.angmenginetest.R.drawable.ghost, 25, 25, 60, null, res);
		assertTrue("Bot left crnr is not null", sprite.getBotLeftCrnr().x > 0
				&& sprite.getBotLeftCrnr().y > 0);
		Sprite sprite2 = new MockSprite(BOUND2, com.kklop.angmenginetest.R.drawable.ghost, 23, 23, 60, null, res);
		grid.addSprite(sprite);
		grid.addSprite(sprite2);
		assertTrue("Should have grid id", sprite.getGridId() == 0);
		assertTrue("Should have grid id", sprite2.getGridId() == 1);
		List<Sprite> sprites = grid.getCollisions(sprite);
		assertTrue("Collided sprites should not be empty and should be one",
				sprites != null && sprites.size() >= 1);
		Log.d(TAG, "Collisions size " + sprites.size());
		for(Sprite s: sprites) {
			assertTrue("Sprite collision isn't istself", !s.equals(sprite));
		}
	}
	
	public void testGridUpdate() throws Exception {
		Grid grid = new Grid(1800, 900, 90);
		Resources res = getContext().getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost);
		Log.d(TAG, "Bitmap width is " + bmp.getWidth() + 
				" and bitmap height is " + bmp.getHeight());
		assertTrue("Bitmap is valid.", bmp != null && bmp.getWidth() > 0);
		Sprite sprite = new MockSprite(BOUND2, com.kklop.angmenginetest.R.drawable.ghost, 25, 25, 60, null, res);
		assertTrue("Bot left crnr is not null", sprite.getBotLeftCrnr().x > 0
				&& sprite.getBotLeftCrnr().y > 0);
		Sprite sprite2 = new MockSprite(BOUND2, com.kklop.angmenginetest.R.drawable.ghost, 900, 800, 60, null, res);
		grid.addSprite(sprite);
		grid.addSprite(sprite2);
		assertTrue("Should have grid id", sprite.getGridId() == 0);
		assertTrue("Should have grid id", sprite2.getGridId() == 1);
		List<Sprite> sprites = grid.getCollisions(sprite);
		assertTrue("Collided sprites should not be empty and should be one",
				sprites == null || sprites.size() == 0);
		Log.d(TAG, "Collisions size " + sprites.size());
		for(Sprite s: sprites) {
			assertTrue("Sprite collision isn't istself", !s.equals(sprite));
		}
		sprite2.setX(23);
		sprite2.setY(23);
		sprite2.setState(Sprite.SPRITE_STATE.MOVING);
		grid.update();
		sprites = grid.getCollisions(sprite);
		assertTrue("Collided sprites should not be empty and should be one",
				sprites != null && sprites.size() >= 1);
		Log.d(TAG, "Collisions size " + sprites.size());
		for(Sprite s: sprites) {
			assertTrue("Sprite collision isn't istself", !s.equals(sprite));
		}
	}
	
	public void testGridRemove() throws Exception {
		Grid grid = new Grid(1800, 900, 90);
		Resources res = getContext().getResources();
		Bitmap bmp = BitmapFactory.decodeResource(res, 
				com.kklop.angmenginetest.R.drawable.ghost);
		Log.d(TAG, "Bitmap width is " + bmp.getWidth() + 
				" and bitmap height is " + bmp.getHeight());
		assertTrue("Bitmap is valid.", bmp != null && bmp.getWidth() > 0);
		Sprite sprite = new MockSprite(BOUND2, com.kklop.angmenginetest.R.drawable.ghost, 25, 25, 60, null, res);
		assertTrue("Bot left crnr is not null", sprite.getBotLeftCrnr().x > 0
				&& sprite.getBotLeftCrnr().y > 0);
		Sprite sprite2 = new MockSprite(BOUND2, com.kklop.angmenginetest.R.drawable.ghost, 900, 800, 60, null, res);
		grid.addSprite(sprite);
		grid.addSprite(sprite2);
		assertTrue("Should have grid id", sprite.getGridId() == 0);
		assertTrue("Should have grid id", sprite2.getGridId() == 1);
		List<Sprite> sprites = grid.getCollisions(sprite);
		assertTrue("Collided sprites should not be empty and should be one",
				sprites == null || sprites.size() == 0);
		Log.d(TAG, "Collisions size " + sprites.size());
		for(Sprite s: sprites) {
			assertTrue("Sprite collision isn't istself", !s.equals(sprite));
		}
		sprite2.setX(23);
		sprite2.setY(23);
		sprite2.setState(Sprite.SPRITE_STATE.MOVING);
		grid.update();
		sprites = grid.getCollisions(sprite);
		assertTrue("Collided sprites should not be empty and should be one",
				sprites != null && sprites.size() >= 1);
		Log.d(TAG, "Collisions size " + sprites.size());
		for(Sprite s: sprites) {
			assertTrue("Sprite collision isn't istself", !s.equals(sprite));
		}
		grid.removeFromGrid(sprite2);
		sprites = grid.getCollisions(sprite);
		assertTrue("Collisions should be empty", sprites == null || 
				sprites.size() == 0);
	}
}
