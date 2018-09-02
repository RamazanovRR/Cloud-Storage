package animationObject;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shiver {
    private TranslateTransition transition;

    public Shiver (Node node) {
        transition = new TranslateTransition(Duration.millis(70), node);
        transition.setFromX(0f);
        transition.setByX(10f);
        transition.setCycleCount(3);
        transition.setAutoReverse(true);
    }

    public void startAnimation() {
        transition.playFromStart();
    }
}
