import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {

    private TextView display;
    private String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.display_text);

        // Set OnClickListener for each button
        int[] buttonIds = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
                           R.id.button4, R.id.button5, R.id.button6, R.id.button7,
                           R.id.button8, R.id.button9, R.id.button_plus, R.id.button_minus,
                           R.id.button_multiply, R.id.button_divide, R.id.button_clear,
                           R.id.button_equals};

        for (int id : buttonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    handleButtonClick(button.getText().toString());
                }
            });
        }
    }

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "+":
            case "-":
            case "*":
            case "/":
                input += " " + buttonText + " ";
                break;
            case "=":
                input = evaluate(input);
                break;
            case "C":
                input = "";
                break;
            default:
                input += buttonText;
        }
        display.setText(input);
    }

    private String evaluate(String expression) {
        try {
            // Using Android's built-in Expression class to evaluate the expression
            net.objecthunter.exp4j.Expression e = new net.objecthunter.exp4j.ExpressionBuilder(expression).build();
            double result = e.evaluate();
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }
}
