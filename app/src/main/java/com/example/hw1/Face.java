package com.example.hw1;
/**
 * Represents a face to draw
 *
 * @author Stephen Nguyen
 * @date 9/29/19
 *
 * Possible caveat: Many position variables, such as margins, are hardcoded
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.Random;

public class Face extends SurfaceView
{
    public int skinColor;
    public int hairColor;
    public int eyeColor;
    public int hairStyle;

    Paint skin = new Paint();
    Paint hair = new Paint();
    Paint eye = new Paint();

    //Red, green, and blue values of each feature
    int skinR;
    int skinG;
    int skinB;
    int eyeR;
    int eyeG;
    int eyeB;
    int hairR;
    int hairG;
    int hairB;

    public Face (Context c, AttributeSet attrs)
    {
        super(c, attrs);
        setWillNotDraw(false);
        randomize();
    }

    /**
     * Sets all instance variables to a random number
     * with appropriate bounds
     */
    public void randomize()
    {
        Random numGen = new Random();
        /**
         External Citation
         Date: 28 September 2019
         Problem: Did not know how to create a color as an int
         Resource:
         https://stackoverflow.com/questions/5280367/android-generate-random-color-on-click
         Solution: I used the solution/example code.
         */
        skinR = numGen.nextInt(256);
        skinG = numGen.nextInt(256);
        skinB = numGen.nextInt(256);
        eyeR = numGen.nextInt(256);
        eyeG = numGen.nextInt(256);
        eyeB = numGen.nextInt(256);
        hairR = numGen.nextInt(256);
        hairG = numGen.nextInt(256);
        hairB = numGen.nextInt(256);

        skinColor = Color.argb(255, skinR, skinG, skinB);
        hairColor = Color.argb(255, hairR, hairG, hairB);
        eyeColor = Color.argb(255, eyeR, eyeG, eyeB);
        hairStyle = numGen.nextInt(3);

        skin.setColor(skinColor);
        hair.setColor(hairColor);
        eye.setColor(eyeColor);
    }

    /**
     * Draws the face on canvas
     * @param c: canvas to draw on
     */
    public void onDraw(Canvas c)
    {
        c.drawCircle(600, 500, 450, skin);
        drawHair(c);
        drawEye(c, 380);
        drawEye(c, 800);
    }

    /**
     * Draws an eye
     * @param xPos: x position of the circle
     * The y value is intended to be the same, so there is no need for a parameter
     * for it
     */
    private void drawEye(Canvas c, float xPos)
    {
        c.drawCircle(xPos, 400, 100, eye);
    }

    /**
     * Draws "hair" (which is just a basic shape) for the face
     * @param c: canvas to draw on
     */
    private void drawHair(Canvas c)
    {
        if (hairStyle == 0)
        {
            c.drawRect(250, 50, 950, 150, hair);
        }
        else if (hairStyle == 1)
        {
            c.drawOval(200, 50, 1000, 200, hair);
        }
        else
        {
            c.drawRoundRect(200, 50, 1000, 200, 150, 350, hair);
        }
    }
}
