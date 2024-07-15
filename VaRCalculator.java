import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VaRCalculator extends Application {

    // Parameters for Monte Carlo simulation
    private static final int SIMULATIONS = 10000;
    private static final double CONFIDENCE_LEVEL = 0.99;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Value at Risk (VaR) Calculator");

        // Input fields
        TextField initialInvestmentField = new TextField();
        initialInvestmentField.setPromptText("Initial Investment");

        TextField meanReturnField = new TextField();
        meanReturnField.setPromptText("Mean Return (%)");

        TextField stdDevReturnField = new TextField();
        stdDevReturnField.setPromptText("Standard Deviation of Return (%)");

        Button calculateButton = new Button("Calculate VaR and ES");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        calculateButton.setOnAction(e -> {
            try {
                double initialInvestment = Double.parseDouble(initialInvestmentField.getText());
                double meanReturn = Double.parseDouble(meanReturnField.getText()) / 100; // Convert percentage to decimal
                double stdDevReturn = Double.parseDouble(stdDevReturnField.getText()) / 100; // Convert percentage to decimal

                List<Double> simulatedReturns = simulateReturns(initialInvestment, meanReturn, stdDevReturn);
                double var = calculateVaR(simulatedReturns, CONFIDENCE_LEVEL);
                double es = calculateES(simulatedReturns, var);

                // Perform backtesting
                int backtestViolations = performBacktesting(simulatedReturns, var);

                resultArea.setText(String.format("VaR (Value at Risk) at %.2f%% confidence level: $%.2f\n", CONFIDENCE_LEVEL * 100, var)
                        + String.format("ES (Expected Shortfall): $%.2f\n", es)
                        + String.format("Backtesting violations (out of %d): %d\n", SIMULATIONS, backtestViolations));
            } catch (NumberFormatException ex) {
                resultArea.setText("Please enter valid numerical values.");
            }
        });

        VBox vbox = new VBox(10, initialInvestmentField, meanReturnField, stdDevReturnField, calculateButton, resultArea);
        Scene scene = new Scene(vbox, 400, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List<Double> simulateReturns(double initialInvestment, double meanReturn, double stdDevReturn) {
        List<Double> returns = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < SIMULATIONS; i++) {
            double simulatedReturn = initialInvestment * Math.exp((meanReturn - 0.5 * Math.pow(stdDevReturn, 2)) + stdDevReturn * random.nextGaussian());
            returns.add(simulatedReturn - initialInvestment);
        }

        Collections.sort(returns);
        return returns;
    }

    private double calculateVaR(List<Double> returns, double confidenceLevel) {
        int index = (int) ((1 - confidenceLevel) * returns.size());
        return -returns.get(index);
    }

    private double calculateES(List<Double> returns, double var) {
        double sum = 0;
        int count = 0;
        for (double ret : returns) {
            if (ret < -var) {
                sum += ret;
                count++;
            }
        }
        return -(sum / count);
    }

    private int performBacktesting(List<Double> returns, double var) {
        int violations = 0;
        for (double ret : returns) {
            if (ret < -var) {
                violations++;
            }
        }
        return violations;
    }
}
