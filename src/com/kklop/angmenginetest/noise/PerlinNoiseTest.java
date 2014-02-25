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
