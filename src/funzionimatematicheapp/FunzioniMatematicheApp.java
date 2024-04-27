package funzionimatematicheapp;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FunzioniMatematicheApp extends Application {
    private static final double X_MIN = -10.0;
    private static final double X_MAX = 10.0;
    private static final double DELTA_X = 0.1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Visualizzazione Funzioni Matematiche");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Funzione Matematica");

        VBox vbox = new VBox();

        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton[] radioButtons = new RadioButton[8];

        for (int i = 1; i <= 8; i++) {
            final int functionNumber = i;
            radioButtons[i - 1] = new RadioButton("Funzione " + i);
            radioButtons[i - 1].setToggleGroup(toggleGroup);
            radioButtons[i - 1].setOnAction(e -> {
                if (radioButtons[functionNumber - 1].isSelected()) {
                    drawFunction(lineChart, functionNumber);
                }
            });
            vbox.getChildren().add(radioButtons[i - 1]);
        }

        vbox.getChildren().add(lineChart);

        Scene scene = new Scene(vbox, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawFunction(LineChart<Number, Number> lineChart, int functionNumber) {
        lineChart.getData().clear();

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        
          switch (functionNumber) {
            case 1 -> series.setName("Sinusoide");
            case 2 -> series.setName("Iperbole");
            case 3 -> series.setName("Cosinusoide");
            case 4 -> series.setName("Rumore b.co");
            case 5 -> series.setName("Sinusoide smorzata");
            case 6 -> series.setName("Parabola");
            case 7 -> series.setName("Segmento inclinato");
            case 8 -> series.setName("Tangente");
            //default = "";
        }
        
        ObservableList<XYChart.Data<Number, Number>> data = series.getData();

        double x = X_MIN;
        while (x <= X_MAX) {
            double y = calculateFunction(x, functionNumber);
            data.add(new XYChart.Data<>(x, y));
            x += DELTA_X;
        }

        lineChart.getData().add(series);
    }

    private double calculateFunction(double x, int functionNumber) {
        // Implementa la logica per calcolare il valore della funzione matematica in base al numero di funzione selezionato.
        // Ad esempio, puoi utilizzare uno switch o una serie di if-else per associare il numero di funzione alla funzione corrispondente.
        // Restituisci il valore della funzione calcolato.
        // Esempio:
        return switch (functionNumber) {
            case 1 -> Math.sin(x);                    //Sinusoide
            case 2 -> Math.pow(x, 3);               //Iperbole
            case 3 -> Math.cos(x);                    //Cosinusoide
            case 4 -> Math.sin(Math.pow(x, 3));   //Rumore b.co
            case 5 -> Math.sin(x)/x;                  //Sinusoide smorzata
            case 6 -> Math.pow(x, 2)+x*-1.0;        //Parabola discendente
            case 7 -> 1/3.0*x/2;                        //Segmento inclinato
            case 8 -> Math.tan(x);                    //Tangente
            default -> 0.0;
        };
       
    }
}
