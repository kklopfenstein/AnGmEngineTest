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
package com.kklop.angmenginetest.noise;

import android.test.AndroidTestCase;
import android.util.Log;

import com.kklop.angmengine.game.noise.PerlinNoise;

public class PerlinNoiseTest extends AndroidTestCase {

	private static final String TAG = "PerlinNoiseTest";
	
	public void testGenerateWhiteNoise() throws Exception {
		PerlinNoise noise = new PerlinNoise();
		float noiseResult[][] = noise.generateWhiteNoise(100, 100);
		
		for(int i=0; i<noiseResult.length; i++) {
			for(int j=0; j<noiseResult[0].length; j++) {
				Log.i(TAG, "Value: " + noiseResult[i][j]);
				assertTrue("Value between 0.0 and 1.0", (0 <= noiseResult[i][j]) && (noiseResult[i][j] <= 1));
			}
		}
	}
	
	public void testGeneratePerlinNoise() throws Exception {
		PerlinNoise noise = new PerlinNoise();
		float baseNoise[][] = noise.generateWhiteNoise(100, 100);
		int octaveCount = 15;
		
		float perlinNoise[][] = noise.generatePerlinNoise(baseNoise, octaveCount);
		
		for(int i=0;i<perlinNoise.length;i++) {
			for(int j=0;j<perlinNoise[0].length;j++) {
				Log.i(TAG, "Perlin Value: " + perlinNoise[i][j]);
			}
		}
	}
	
}
