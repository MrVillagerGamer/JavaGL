
public class Test {

	public static void main(String[] args) {
		
		JGE.glCreateViewport();
		
		float[][] positiveTranslationMatrix = JGE.glInitMatrixTranslation(400, 300, 0);
		float[][] negativeTranslationMatrix = JGE.glInitMatrixTranslation(-400, -300, 0);
		float[][] xAxisRotationMatrix = JGE.glInitMatrixRotationX(0.3f);
		float[][] yAxisRotationMatrix = JGE.glInitMatrixRotationY(0.2f);
		float[][] zAxisRotationMatrix = JGE.glInitMatrixRotationZ(0.1f);
		float[][] scaleMatrix = JGE.glInitMatrixScale(3, 3, 3);
		
		float[] vector1 = new float[] {
				0, 0, 0, 1
		};
		
		float[] vector2 = new float[] {
				100, 100, 0, 1
		};
		
		float[] vector3 = new float[] {
				100, 0, 0, 1
		};
		vector1 = JGE.glMultiplyVectorMatrix(vector1, scaleMatrix);
		vector2 = JGE.glMultiplyVectorMatrix(vector2, scaleMatrix);
		vector3 = JGE.glMultiplyVectorMatrix(vector3, scaleMatrix);
		
		vector1 = JGE.glMultiplyVectorMatrix(vector1, positiveTranslationMatrix);
		vector2 = JGE.glMultiplyVectorMatrix(vector2, positiveTranslationMatrix);
		vector3 = JGE.glMultiplyVectorMatrix(vector3, positiveTranslationMatrix);
		
		
		
		while(true)
		{
			// Sets position to center of screen to change origin
			vector1 = JGE.glMultiplyVectorMatrix(vector1, negativeTranslationMatrix);
			vector2 = JGE.glMultiplyVectorMatrix(vector2, negativeTranslationMatrix);
			vector3 = JGE.glMultiplyVectorMatrix(vector3, negativeTranslationMatrix);
			
			// Rotates the vectors on the x axis
			vector1 = JGE.glMultiplyVectorMatrix(vector1, xAxisRotationMatrix);
			vector2 = JGE.glMultiplyVectorMatrix(vector2, xAxisRotationMatrix);
			vector3 = JGE.glMultiplyVectorMatrix(vector3, xAxisRotationMatrix);
			
			// Rotates the vectors on the y axis
			vector1 = JGE.glMultiplyVectorMatrix(vector1, yAxisRotationMatrix);
			vector2 = JGE.glMultiplyVectorMatrix(vector2, yAxisRotationMatrix);
			vector3 = JGE.glMultiplyVectorMatrix(vector3, yAxisRotationMatrix);
			
			// Rotates the vectors on the z axis
			vector1 = JGE.glMultiplyVectorMatrix(vector1, zAxisRotationMatrix);
			vector2 = JGE.glMultiplyVectorMatrix(vector2, zAxisRotationMatrix);
			vector3 = JGE.glMultiplyVectorMatrix(vector3, zAxisRotationMatrix);
			
			// Sets position back to top left to reset origin
			vector1 = JGE.glMultiplyVectorMatrix(vector1, positiveTranslationMatrix);
			vector2 = JGE.glMultiplyVectorMatrix(vector2, positiveTranslationMatrix);
			vector3 = JGE.glMultiplyVectorMatrix(vector3, positiveTranslationMatrix);
			
			// re initializes the buffers and graphics
			JGE.glPrepareGraphics();
			
			// Clears the screen (must be called between prepare and dispose or an error will occur)
			JGE.glClearColor(0, 0, 0);
			
			// Draws a triangle (must be called between prepare and dispose or an error will occur)
			JGE.glDrawVectors(vector1, vector2, vector3);
			
			// Disposes the graphics and swaps the buffers
			JGE.glDisposeGraphics();
		}

	}

}
