# VaR-Program

This is a Java application that calculates the Value at Risk (VaR) and Expected Shortfall (ES) using Monte Carlo simulations. It also includes a backtesting mechanism to validate the VaR model by comparing the predictions with actual returns.

## Features

- **Monte Carlo Simulation**: Generates a list of simulated returns based on user inputs.
- **Value at Risk (VaR) Calculation**: Calculates VaR at a specified confidence level.
- **Expected Shortfall (ES) Calculation**: Calculates ES based on the worst losses beyond the VaR.
- **Backtesting**: Validates the VaR model by counting the number of times the actual losses exceed the predicted VaR.

## Technologies Used

- Java
- JavaFX

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- JavaFX SDK

### Installing

1. **Clone the repository**:
    ```sh
    git clone <repository_url>
    cd <repository_directory>
    ```

2. **Ensure JavaFX is set up**:
    - Download JavaFX SDK from [GluonHQ](https://gluonhq.com/products/javafx/)
    - Unzip the JavaFX SDK to a desired location

3. **Compile and run the program**:
    ```sh
    javac --module-path <path_to_javafx_sdk> --add-modules javafx.controls VaRCalculator.java
    java --module-path <path_to_javafx_sdk> --add-modules javafx.controls VaRCalculator
    ```

    Replace `<path_to_javafx_sdk>` with the path to the `lib` directory of the unzipped JavaFX SDK.

### Usage

1. **Run the application**:
    ```sh
    java --module-path <path_to_javafx_sdk> --add-modules javafx.controls VaRCalculator
    ```

2. **Enter input values**:
    - Initial Investment
    - Mean Return (in percentage)
    - Standard Deviation of Return (in percentage)

3. **Click "Calculate VaR and ES"**:
    - The application will display the calculated VaR, ES, and the number of backtesting violations.

### Example

1. **Initial Investment**: 100000
2. **Mean Return**: 5
3. **Standard Deviation of Return**: 15

Click "Calculate VaR and ES" to see the results.

