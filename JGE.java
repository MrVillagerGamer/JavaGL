package dev.braiden.javagl;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class JGE 
{
	// Variable declarations
	private static int width = 800;
	private static int height = 600;
	private static String title = "JavaGL";
	private static JFrame frame;
	private static Canvas canvas;
	private static BufferStrategy strategy;
	private static Graphics graphics;
	
	// JavaGL draw modes
	public static final int GL_DRAW_MODE_LINE = 0;
	public static final int GL_DRAW_MODE_FILL = 1;
	
	// Sets window features
	public static void glViewportWidth(int width)
	{
		JavaGL.width = width;
	}
	
	public static void glViewportHeight(int height)
	{
		JavaGL.height = height;
	}
	
	public static void glViewportTitle(String title)
	{
		JavaGL.title = title;
	}
	
	// Creates the window
	public static void glCreateViewport()
	{
		frame = new JFrame();
		frame.setTitle(title);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		canvas = new Canvas();
		canvas.setSize(width, height);
		
		frame.add(canvas);
		frame.pack();
		frame.setVisible(true);
		
		canvas.createBufferStrategy(3);
	}
	
	// Re initialized buffers and graphics
	public static void glPrepareGraphics()
	{
		strategy = canvas.getBufferStrategy();
		graphics = strategy.getDrawGraphics();
	}
	
	// Disposes the graphics and swaps buffers
	public static void glDisposeGraphics()
	{
		strategy.show();
		graphics.dispose();
	}
	
	// Clears the screen to a color
	public static void glClearColor(float r, float g, float b)
	{
		graphics.setColor(new Color(r, g, b));
		graphics.fillRect(0, 0, width, height);
	}
	
	// Draws 3 vectors as a triangle
	public static void glDrawVectors(float[] vector1, float[] vector2, float[] vector3)
	{
		Polygon p = new Polygon();
		p.addPoint((int) vector1[0], (int) vector1[1]);
		p.addPoint((int) vector2[0], (int) vector2[1]);
		p.addPoint((int) vector3[0], (int) vector3[1]);
		graphics.setColor(Color.WHITE);
		graphics.fillPolygon(p);
	}
	
	// Overloaded method for the one above, takes in a draw mode
	public static void glDrawVectors(int drawMode, float[] vector1, float[] vector2, float[] vector3)
	{
		Polygon p = new Polygon();
		p.addPoint((int) vector1[0], (int) vector1[1]);
		p.addPoint((int) vector2[0], (int) vector2[1]);
		p.addPoint((int) vector3[0], (int) vector3[1]);
		graphics.setColor(Color.WHITE);
		if(drawMode == JavaGL.GL_DRAW_MODE_LINE)
			graphics.drawPolygon(p);
		if(drawMode == JavaGL.GL_DRAW_MODE_FILL)
			graphics.fillPolygon(p);
	}
	
	// Creates an identity matrix
	public static float[][] glInitMatrixIdentity()
	{
		float[][] matrix = new float[4][4];
		matrix[0][0] = 1; matrix[0][1] = 0; matrix[0][2] = 0; matrix[0][3] = 0; 
		matrix[1][0] = 0; matrix[1][1] = 1; matrix[1][2] = 0; matrix[1][3] = 0; 
		matrix[2][0] = 0; matrix[2][1] = 0; matrix[2][2] = 1; matrix[2][3] = 0; 
		matrix[3][0] = 0; matrix[3][1] = 0; matrix[3][2] = 0; matrix[3][3] = 1; 
		return matrix;
	}
	
	// Creates a scale matrix
	public static float[][] glInitMatrixScale(float x, float y, float z)
	{
		float[][] matrix = new float[4][4];
		matrix[0][0] = x; matrix[0][1] = 0; matrix[0][2] = 0; matrix[0][3] = 0; 
		matrix[1][0] = 0; matrix[1][1] = y; matrix[1][2] = 0; matrix[1][3] = 0; 
		matrix[2][0] = 0; matrix[2][1] = 0; matrix[2][2] = z; matrix[2][3] = 0; 
		matrix[3][0] = 0; matrix[3][1] = 0; matrix[3][2] = 0; matrix[3][3] = 1; 
		return matrix;
	}
	
	// Creates a translation matrix
	public static float[][] glInitMatrixTranslation(float x, float y, float z)
	{
		float[][] matrix = new float[4][4];
		matrix[0][0] = 1; matrix[0][1] = 0; matrix[0][2] = 0; matrix[0][3] = x;
		matrix[1][0] = 0; matrix[1][1] = 1; matrix[1][2] = 0; matrix[1][3] = y;
		matrix[2][0] = 0; matrix[2][1] = 0; matrix[2][2] = 1; matrix[2][3] = z;
		matrix[3][0] = 0; matrix[3][1] = 0; matrix[3][2] = 0; matrix[3][3] = 1;
		
		return matrix;
	}
	
	// Creates rotation matrices
	public static float[][] glInitMatrixRotationX(float x)
	{
		float[][] matrix = new float[4][4];
		float rad = (float) Math.toRadians(x);
		matrix[0][0] = 1; matrix[0][1] = 0;                     matrix[0][2] = 0;                      matrix[0][3] = 0;
		matrix[1][0] = 0; matrix[1][1] = (float) Math.cos(rad); matrix[1][2] = (float) -Math.sin(rad); matrix[1][3] = 0;
		matrix[2][0] = 0; matrix[2][1] = (float) Math.sin(rad); matrix[2][2] = (float)  Math.cos(rad); matrix[2][3] = 0;
		matrix[3][0] = 0; matrix[3][1] = 0;                     matrix[3][2] = 0;                      matrix[3][3] = 1;
	
		return matrix;
	}
	
	public static float[][] glInitMatrixRotationY(float y)
	{
		float[][] matrix = new float[4][4];
		float rad = (float) Math.toRadians(y);
		matrix[0][0] = (float) Math.cos(rad);  matrix[0][1] = 0; matrix[0][2] = (float) Math.sin(rad); matrix[0][3] = 0;
		matrix[1][0] = 0;                      matrix[1][1] = 1; matrix[1][2] = 0;                     matrix[1][3] = 0;
		matrix[2][0] = (float) -Math.sin(rad); matrix[2][1] = 0; matrix[2][2] = (float) Math.cos(rad); matrix[2][3] = 0;
		matrix[3][0] = 0;                      matrix[3][1] = 0; matrix[3][2] = 0;                     matrix[3][3] = 1;
		
		return matrix;
	}
	
	public static float[][] glInitMatrixRotationZ(float z)
	{
		float[][] matrix = new float[4][4];
		float rad = (float) Math.toRadians(z);
		matrix[0][0] = (float) Math.cos(rad); matrix[0][1] = (float) -Math.sin(rad); matrix[0][2] = 0; matrix[0][3] = 0;
		matrix[1][0] = (float) Math.sin(rad); matrix[1][1] = (float) Math.cos(rad); matrix[1][2] = 0; matrix[1][3] = 0;
		matrix[2][0] = 0; matrix[2][1] = 0; matrix[2][2] = 1; matrix[2][3] = 0;
		matrix[3][0] = 0; matrix[3][1] = 0; matrix[3][2] = 0; matrix[3][3] = 1;
		
		return matrix;
	}
	
	// Multiplies a vector and a matrix, returns a vector
	public static float[] glMultiplyVectorMatrix(float[] vector, float[][] matrix)
	{
		float[] vector2 = new float[4];
		
		float x = 0;
		float y = 0;
		float z = 0;
		float w = 0;
		
		x += matrix[0][0] * vector[0];
		x += matrix[0][1] * vector[1];
		x += matrix[0][2] * vector[2];
		x += matrix[0][3] * vector[3];
		
		y += matrix[1][0] * vector[0];
		y += matrix[1][1] * vector[1];
		y += matrix[1][2] * vector[2];
		y += matrix[1][3] * vector[3];
		
		z += matrix[2][0] * vector[0];
		z += matrix[2][1] * vector[1];
		z += matrix[2][2] * vector[2];
		z += matrix[2][3] * vector[3];
		
		w += matrix[3][0] * vector[0];
		w += matrix[3][1] * vector[1];
		w += matrix[3][2] * vector[2];
		w += matrix[3][3] * vector[3];
		
		vector2[0] = x;
		vector2[1] = y;
		vector2[2] = z;
		vector2[3] = w;
		
		return vector2;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
