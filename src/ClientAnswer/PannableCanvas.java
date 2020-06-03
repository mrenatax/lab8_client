package ClientAnswer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
public class PannableCanvas extends Pane {
    DoubleProperty myScale = new SimpleDoubleProperty(1.0);
    public PannableCanvas() {
        setPrefSize(660, 660);
        setStyle("-fx-background-color: lightgrey; -fx-border-color: blue;");
        scaleXProperty().bind(myScale);
        scaleYProperty().bind(myScale);
    }
    public void addGrid(double w,double h) {
        Canvas grid = new Canvas(w, h);
        grid.setMouseTransparent(true);
        GraphicsContext gc = grid.getGraphicsContext2D();
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);
        double offset = 50;
        for( double i=offset; i < w; i+=offset) {
            gc.strokeLine( i, 0, i, h);
            gc.strokeLine( 0, i, w, i);
        }
        getChildren().add(grid);
        grid.toBack();
    }
    public double getScale() {
        return myScale.get();
    }
    public void setScale( double scale) {
        myScale.set(scale);
    }
    public void setPivot( double x, double y) {
        setTranslateX(getTranslateX()-x);
        setTranslateY(getTranslateY()-y);
    }
}