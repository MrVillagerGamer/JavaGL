package dev.braiden.javagl;

public class Test {

	public static void main(String[] args) {
		
		JavaGL.glCreateViewport();
		
		float[][] positiveTranslationMatrix = JavaGL.glInitMatrixTranslation(400, 300, 0);
		float[][] negativeTranslationMatrix = JavaGL.glInitMatrixTranslation(-400, -300, 0);
		float[][] xAxisRotationMatrix = JavaGL.glInitMatrixRotationX(0.3f);
		float[][] yAxisRotationMatrix = JavaGL.glInitMatrixRotationY(0.2f);
		float[][] zAxisRotationMatrix = JavaGL.glInitMatrixRotationZ(0.1f);
		float[][] scaleMatrix = JavaGL.glInitMatrixScale(3, 3, 3);
		
		float[] vector1 = new float[] {
				0, 0, 0, 1
		};
		
		float[] vector2 = new float[] {
				100, 100, 0, 1
		};
		
		float[] vector3 = new float[] {
				100, 0, 0, 1
		};
		vector1 = JavaGL.glMultiplyVectorMatrix(vector1, scaleMatrix);
		vector2 = JavaGL.glMultiplyVectorMatrix(vector2, scaleMatrix);
		vector3 = JavaGL.glMultiplyVectorMatrix(vector3, scaleMatrix);
		
		vector1 = JavaGL.glMultiplyVectorMatrix(vector1, positiveTranslationMatrix);
		vector2 = JavaGL.glMultiplyVectorMatrix(vector2, positiveTranslationMatrix);
		vector3 = JavaGL.glMultiplyVectorMatrix(vector3, positiveTranslationMatrix);
		
		
		
		while(true)
		{
			// Sets position to center of screen to change origin
			vector1 = JavaGL.glMultiplyVectorMatrix(vector1, negativeTranslationMatrix);
			vector2 = JavaGL.glMultiplyVectorMatrix(vector2, negativeTranslationMatrix);
			vector3 = JavaGL.glMultiplyVectorMatrix(vector3, negativeTranslationMatrix);
			
			// Rotates the vectors on the x axis
			vector1 = JavaGL.glMultiplyVectorMatrix(vector1, xAxisRotationMatrix);
			vector2 = JavaGL.glMultiplyVectorMatrix(vector2, xAxisRotationMatrix);
			vector3 = JavaGL.glMultiplyVectorMatrix(vector3, xAxisRotationMatrix);
			
			// Rotates the vectors on the y axis
			vector1 = JavaGL.glMultiplyVectorMatrix(vector1, yAxisRotationMatrix);
			vector2 = JavaGL.glMultiplyVectorMatrix(vector2, yAxisRotationMatrix);
			vector3 = JavaGL.glMultiplyVectorMatrix(vector3, yAxisRotationMatrix);
			
			// Rotates the vectors on the z axis
			vector1 = JavaGL.glMultiplyVectorMatrix(vector1, zAxisRotationMatrix);
			vector2 = JavaGL.glMultiplyVectorMatrix(vector2, zAxisRotationMatrix);
			vector3 = JavaGL.glMultiplyVectorMatrix(vector3, zAxisRotationMatrix);
			
			// Sets position back to top left to reset origin
			vector1 = JavaGL.glMultiplyVectorMatrix(vector1, positiveTranslationMatrix);
			vector2 = JavaGL.glMultiplyVectorMatrix(vector2, positiveTranslationMatrix);
			vector3 = JavaGL.glMultiplyVectorMatrix(vector3, positiveTranslationMatrix);
			
			// re initializes the buffers and graphics
			JavaGL.glPrepareGraphics();
			
			// Clears the screen (must be called between prepare and dispose or an error will occur)
			JavaGL.glClearColor(0, 0, 0);
			
			// Draws a triangle (must be called between prepare and dispose or an error will occur)
			JavaGL.glDrawVectors(vector1, vector2, vector3);
			
			// Disposes the graphics and swaps the buffers
			JavaGL.glDisposeGraphics();
		}

	}

}
