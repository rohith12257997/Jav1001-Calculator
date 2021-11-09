package codewithcal.au.calculatorappexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;



public class MainActivity extends AppCompatActivity
{
// Created two text views one is called as workingsTv ie used for inputs and resultsTv for output
    TextView workingsTV;
    TextView resultsTV;

    String workings = "";

    // these are used for power operations
    String formula = "";
    String tempFormula = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews()
    {
        // initialising the variables from the textview
        workingsTV = (TextView)findViewById(R.id.workingsTextView);
        resultsTV = (TextView)findViewById(R.id.resultTextView);
    }

    //Setting the values given by the user to the screen every time we call this method to set the values to the screen
    private void setWorkings(String givenValue)
    {
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }


    public void equalsOnClick(View view)
    {

        // At the start the result is null
        Double result = null;
        // we have imported the rhino class for math calculation here we are declaring the Scriptengine
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        // calling the power of function
        checkForPowerOf();

        try {
            //calculating the results
            result = (double)engine.eval(formula);
        } catch (ScriptException e)
        {
            // included the toast to say the user that the input is invalid when we type some error like multiple decimals etc
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }
//if the results is not equal to null then only set the result
        if(result != null)
            resultsTV.setText(String.valueOf(result.doubleValue()));

    }

    private void checkForPowerOf()
    {
        // creating the array list for checking the symbol for ^ this in the entire String
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for(int i = 0; i < workings.length(); i++)
        {
            if (workings.charAt(i) == '^')

                // if found call the power method to calculate the method we are calling here
                indexOfPowers.add(i);
        }

        formula = workings;
        tempFormula = workings;
        for(Integer index: indexOfPowers)
        {
            changeFormula(index);
        }
        formula = tempFormula;
    }

    private void changeFormula(Integer index)
    {
        String numberLeft = "";
        String numberRight = "";

        // checking the end of the length if its char or not for the first half for ex: 5^25 here 5 is first half
        for(int i = index + 1; i< workings.length(); i++)
        {
            if(isNumeric(workings.charAt(i)))
                numberRight = numberRight + workings.charAt(i);
            else
                break;
        }
        // checking the end of the length if its char or not for the 2nd half for ex: 5^25 here 25 is 2nd half
        for(int i = index - 1; i >= 0; i--)
        {
            if(isNumeric(workings.charAt(i)))
                numberLeft = numberLeft + workings.charAt(i);
            else
                break;
        }

        //now giving thm both for math pow method to calculate
        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow("+numberLeft+","+numberRight+")";
        // now changing the old one with new one
        tempFormula = tempFormula.replace(original,changed);
    }


    // this is to check the given one is number or not for calculating the power of a number
    private boolean isNumeric(char c)
    {
        if((c <= '9' && c >= '0') || c == '.')
            return true;

        return false;
    }


    public void clearOnClick(View view)
    {
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    // Simple toggling the for brackets are present or not
    public void bracketsOnClick(View view)
    {
        if(leftBracket)
        {
            setWorkings("(");
            leftBracket = false;
        }
        else
            {
                setWorkings(")");
                leftBracket = true;
            }
    }

    public void powerOfOnClick(View view)
    {
        setWorkings("^");
    }

    public void divisionOnClick(View view)
    {
        setWorkings("/");
    }

    public void sevenOnClick(View view)
    {
        setWorkings("7");
    }

    public void eightOnClick(View view)
    {
        setWorkings("8");
    }

    public void nineOnClick(View view)
    {
        setWorkings("9");
    }

    public void timesOnClick(View view)
    {
        setWorkings("*");
    }

    public void fourOnClick(View view)
    {
        setWorkings("4");
    }

    public void fiveOnClick(View view)
    {
        setWorkings("5");
    }

    public void sixOnClick(View view)
    {
        setWorkings("6");
    }

    public void minusOnClick(View view)
    {
        setWorkings("-");
    }

    public void oneOnClick(View view)
    {
        setWorkings("1");
    }

    public void twoOnClick(View view)
    {
        setWorkings("2");
    }

    public void threeOnClick(View view)
    {
        setWorkings("3");
    }

    public void plusOnClick(View view)
    {
        setWorkings("+");
    }

    public void decimalOnClick(View view)
    {
        setWorkings(".");
    }

    public void zeroOnClick(View view)
    {
        setWorkings("0");
    }

}
