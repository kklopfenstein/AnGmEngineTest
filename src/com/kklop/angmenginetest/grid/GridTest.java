package com.kklop.angmenginetest.grid;

import android.test.AndroidTestCase;

import com.kklop.angmengine.game.grid.Grid;
import com.kklop.angmengine.game.grid.exception.GridException;

public class GridTest extends AndroidTestCase {

	public void testGrid() {
		try {
			Grid grid = new Grid(14, 14, 14);
		} catch(GridException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch(Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}
