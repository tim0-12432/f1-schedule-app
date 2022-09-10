package de.tim0_12432.f1_schedule_app.ui.transitions;

import androidx.transition.ChangeBounds;
import androidx.transition.Fade;
import androidx.transition.TransitionSet;

public class FadeOutTransition extends TransitionSet {

    public FadeOutTransition() {
        setOrdering(ORDERING_TOGETHER);
        addTransition(new ChangeBounds())
                .addTransition(new Fade(Fade.OUT))
                .addTransition(new Fade(Fade.IN));
    }
}
