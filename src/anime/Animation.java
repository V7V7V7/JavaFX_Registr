package anime;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.scene.Node;


public class Animation {
    private TranslateTransition animation;

    public Animation(Node node) {
        animation = new TranslateTransition(Duration.millis(100), node);
        animation.setFromX(0f);
        animation.setByX(10f);
        animation.setCycleCount(3);
        animation.setAutoReverse(true);
    }
    public void play() {
        animation.playFromStart();
    }
}
