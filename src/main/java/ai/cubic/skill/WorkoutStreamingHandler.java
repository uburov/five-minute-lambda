package ai.cubic.skill;

import ai.cubic.skill.speechlet.WorkoutSpeechlet;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Salmond on 10/03/17.
 */
public class WorkoutStreamingHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<String>();
    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds.add("amzn1.ask.skill.92c43bb0-f582-48d5-92e9-be050317237b");
    }

    public WorkoutStreamingHandler() {
        super(new WorkoutSpeechlet(), supportedApplicationIds);
    }

}
