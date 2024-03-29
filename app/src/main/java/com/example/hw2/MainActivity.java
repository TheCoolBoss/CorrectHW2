package com.example.hw2;
/**
 * Contains all tools (SeekBars, Buttons, RadioButtons, etc.)
 * and their respective listeners
 *
 * @author Stephen Nguyen
 * @date 9/29/19
 *
 * Possible caveat: The ExampleInstrumentedTest file has issues, but the program still runs normally
 */

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener,
        Spinner.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener
{
    //View that these listeners affect
    Face face;

    //Used for determining which radiobutton is selected
    String chosenButton = " ";

    //Used for spinner options
    String[] list = {"Style 1 (Rectangle)", "Style 2 (Oval)", "Style 3 (Round Rect)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        face = (Face) findViewById(R.id.face);
        SeekBar red = (SeekBar) findViewById(R.id.redBar);
        SeekBar green = (SeekBar) findViewById(R.id.greenBar);
        SeekBar blue = (SeekBar) findViewById(R.id.blueBar);
        Button randomFace = (Button) findViewById(R.id.randomFace);
        Spinner hairstyle = (Spinner) findViewById(R.id.hairstyle);
        RadioGroup options = (RadioGroup) findViewById(R.id.options);

        randomFace.setOnClickListener(this);
        red.setOnSeekBarChangeListener(this);
        blue.setOnSeekBarChangeListener(this);
        green.setOnSeekBarChangeListener(this);
        hairstyle.setOnItemSelectedListener(this);
        options.setOnCheckedChangeListener(this);

        /**
         External Citation
         Date: 28 September 2019
         Problem: Did not know how to work with a spinner
         Resource:
         https://www.javatpoint.com/android-spinner-example
         Solution: I used the example code.
         */

        ArrayAdapter styles = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,list);
        styles.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairstyle.setAdapter(styles);
    }

    /**
     * Randomizes face features when button is pressed
     * @param v: face (which extends SurfaceView) that is being randomized
     */
    public void onClick(View v)
    {
        SeekBar red = findViewById(R.id.redBar);
        SeekBar green = findViewById(R.id.greenBar);
        SeekBar blue = findViewById(R.id.blueBar);
        Spinner hairstyle = findViewById(R.id.hairstyle);

        face.randomize();

        //Sets seekbar progress based on selected radiobutton
        //Color based on the color generated by randomize method
        if (chosenButton.equals("eyes"))
        {
            red.setProgress(face.eyeR);
            green.setProgress(face.eyeG);
            blue.setProgress(face.eyeB);
        }
        else if (chosenButton.equals("skin"))
        {
            red.setProgress(face.skinR);
            green.setProgress(face.skinG);
            blue.setProgress(face.skinB);
        }
        else if (chosenButton.equals("hair"))
        {
            red.setProgress(face.hairR);
            green.setProgress(face.hairG);
            blue.setProgress(face.hairB);
        }

        //Sets spinner option based on what shape randomize method draws
        if (face.hairStyle == 0)
        {
            hairstyle.setSelection(0);
        }
        else if (face.hairStyle == 1)
        {
            hairstyle.setSelection(1);
        }
        else if (face.hairStyle == 2)
        {
            hairstyle.setSelection(2);
        }
        face.invalidate();
    }

    /**
     * Creates a color based on seekbar progress
     * and sets it to the selected feature on the face
     * @param sb; seekbar that has this listener
     * @param progress: progress of that seekbar
     * @param fromUser: if user moved the seekbar
     */
    public void onProgressChanged(SeekBar sb, int progress, boolean fromUser)
    {
        /**
         External Citation
         Date: 29 September 2019
         Problem: Had trouble setting color based on seekbar progress
         Resource:
         https://stackoverflow.com/questions/23896216/set-3-seekbars-to-change-rgb-channels
         Solution: I used the example solution.
         */
        switch (sb.getId())
        {
            case R.id.redBar:
            {
                if (chosenButton.equals("eyes"))
                {
                    face.eyeR = progress;
                }
                else if (chosenButton.equals("skin"))
                {
                    face.skinR = progress;
                }
                else if (chosenButton.equals("hair"))
                {
                    face.hairR = progress;
                }
            }
            break;

            case R.id.greenBar:
            {
                if (chosenButton.equals("eyes"))
                {
                    face.eyeG = progress;
                }
                else if (chosenButton.equals("skin"))
                {
                    face.skinG = progress;
                }
                else if (chosenButton.equals("hair"))
                {
                    face.hairG = progress;
                }
            }
            break;

            case R.id.blueBar:
            {
                if (chosenButton.equals("eyes"))
                {
                    face.eyeB = progress;
                }
                else if (chosenButton.equals("skin"))
                {
                    face.skinB = progress;
                }
                else if (chosenButton.equals("hair"))
                {
                    face.hairB = progress;
                }
            }
            break;
        }

        //Nothing is updated if no radiobutton is selected
        if (chosenButton.equals("eyes"))
        {
            int newColor = Color.argb(255, face.eyeR, face.eyeG, face.eyeB);
            face.eye.setColor(newColor);
        }
        else if (chosenButton.equals("skin"))
        {
            int newColor = Color.argb(255, face.skinR, face.skinG, face.skinB);
            face.skin.setColor(newColor);
        }
        else if (chosenButton.equals("hair"))
        {
            int newColor = Color.argb(255, face.hairR, face.hairG, face.hairB);
            face.hair.setColor(newColor);
        }
        face.invalidate();
    }

    /**
     * Changes value of chosenButton instance variable depending
     * on which button is pressed
     * @param rg: Radiogroup that has this listener
     * @param id: id of the chosen button
     */
    public void onCheckedChanged(RadioGroup rg, int id)
    {
        /**
         External Citation
         Date: 29 September 2019
         Problem: Had trouble determining which radiobutton is selected
         Resource:
         https://stackoverflow.com/questions/8323778/how-to-set-onclicklistener-on-a-radiobutton-in-android
         Solution: I used the example solutions (there were multiple provided).
         */
        View rButton = rg.findViewById(id);
        int choice = rg.indexOfChild(rButton);
        SeekBar red = findViewById(R.id.redBar);
        SeekBar green = findViewById(R.id.greenBar);
        SeekBar blue = findViewById(R.id.blueBar);
        switch (choice)
        {
            case 0:
            {
                chosenButton = "eyes";
                red.setProgress(face.eyeR);
                green.setProgress(face.eyeG);
                blue.setProgress(face.eyeB);
            }
            break;
            case 1:
            {
                chosenButton = "skin";
                red.setProgress(face.skinR);
                green.setProgress(face.skinG);
                blue.setProgress(face.skinB);
            }
            break;
            case 2:
            {
                chosenButton = "hair";
                red.setProgress(face.hairR);
                green.setProgress(face.hairG);
                blue.setProgress(face.hairB);
            }
            break;
        }
    }

    /**
     External Citation
     Date: 29 September 2019
     Problem: Did not know meaning behind this abstract method's parameters
     Resource:
     https://developer.android.com/reference/android/widget/AdapterView.OnItemSelectedListener.html
     Solution: I used the documentation.
     */

    /**
     * Determines hairstyle to draw based on spinner selection
     * @param av: adapterview where selection occurred
     * @param v: view that was selected
     * @param pos: position of view
     * @param id: id of selection
     */
    public void onItemSelected(AdapterView<?> av, View v, int pos, long id)
    {
        /**
         External Citation
         Date: 28 September 2019
         Problem: Did not know how to get a spinner's selected value
         Resource:
         https://developer.android.com/guide/topics/ui/controls/spinner.html
         Solution: I used the example code and documentation.
         */
        if (av.getItemAtPosition(pos).equals(list[0]))
        {
            face.hairStyle = 0;
        }
        else if (av.getItemAtPosition(pos).equals(list[1]))
        {
            face.hairStyle = 1;
        }
        else
        {
            face.hairStyle = 2;
        }
        face.invalidate();
    }

    //Methods that are unused, but need to be declared due to them being abstract
    public void onStartTrackingTouch(SeekBar sb)
    {

    }

    public void onStopTrackingTouch (SeekBar sb)
    {

    }
    public void onNothingSelected(AdapterView av)
    {

    }
}
