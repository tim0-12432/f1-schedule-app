package de.tim0_12432.f1_schedule_app.ui.transitions;

import androidx.transition.ChangeBounds;
import androidx.transition.Fade;
import androidx.transition.TransitionSet;

public class FadeInTransition extends TransitionSet {

    public FadeInTransition() {
        setOrdering(ORDERING_SEQUENTIAL);
        addTransition(new Fade(Fade.OUT))
                .addTransition(new ChangeBounds())
                .addTransition(new Fade(Fade.IN));
    }
}
