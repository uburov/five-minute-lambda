package ai.cubic.skill.manager

import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.*

/**
 * Created by Salmond on 10/03/17.
 */
class IntentManager {
    private static
    final String WELCOME = 'Welcome to 5-minute plank workout. You can see how to perform exercises at your Alexa app. Take a push up position and say "ready" to start'
    private static final String WELCOME_REPROMPT = 'When you are ready to start your workout, say "I am ready"'
    private static final String HELP =
            'This is 5-minute plank workout. 4 simple exercise to strengthen your core muscles. Look at your Alexa app to learn how to perform the exercises. To start the workout, say "I am ready"'
    private static final String HELP_REPROMPT = 'Ask me to open 5-minute plank skill again whenever you are ready'
    private static final String WORKOUT = '''
<speak>
\t<p>First excercise: full plank</p>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t50 seconds left <break time="10s"/>
\t40 seconds <break time="10s"/>
\t30 seconds <break time="10s"/>
\t20 seconds <break time="10s"/>
\t10 seconds <break time="7s"/>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>

\t10 seconds rest <break time="7s"/>
\t<p>Elbow plank, in </p>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t20 seconds <break time="10s"/>
\t10 seconds <break time="7s"/>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>

\t10 seconds rest <break time="7s"/>
\t<p>One leg plank, in </p>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t10 seconds left <break time="7s"/>
\t<p>Change the leg in</p>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\t<break time="10s"/>
\t10 seconds left <break time="7s"/>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\t<p>Good job!</p>

\t10 seconds rest <break time="7s"/>
\t<p>Side plank, in </p>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t20 seconds left <break time="10s"/>
\t10 seconds <break time="7s"/>
\t<p>Switch sides in </p>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\tSwitch! <break time="3s"/>
\t<break time="10s"/>
\t20 seconds left <break time="10s"/>
\t10 seconds <break time="7s"/>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>

\t10 seconds rest <break time="7s"/>
\t<p>Full plank, in </p>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t20 seconds left <break time="10s"/>
\t10 seconds left <break time="7s"/>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>

\t10 seconds rest and the last, elbow plank<break time="7s"/>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\t<break time="10s"/>
\t20 seconds left <break time="10s"/>
\t10 seconds  <break time="7s"/>
\t3 <break time="1s"/>
\t2 <break time="1s"/>
\t1 <break time="1s"/>
\tStop!
\t<p>Congratulations! Your 5-minute plank workout is complete! See you tomorrow!</p>
</speak>'''


    public SpeechletResponse getWelcomeText() {
        PlainTextOutputSpeech out = new PlainTextOutputSpeech()
        out.setText(WELCOME)

        Reprompt reprompt = new Reprompt()
        def repromptSpeech = new PlainTextOutputSpeech()
        repromptSpeech.text = WELCOME_REPROMPT
        reprompt.setOutputSpeech(repromptSpeech)
        return SpeechletResponse.newAskResponse(out, reprompt, createCard())
    }

    public SpeechletResponse getWorkout() {
        def outputSpeech = new SsmlOutputSpeech()
        outputSpeech.ssml = WORKOUT
        return SpeechletResponse.newTellResponse(outputSpeech)
    }

    public SpeechletResponse getHelpText() {
        def outputSpeech = new PlainTextOutputSpeech()
        outputSpeech.text = HELP
        def reprompt = new Reprompt()
        def repromptSpeech = new PlainTextOutputSpeech()
        repromptSpeech.text = HELP_REPROMPT
        reprompt.outputSpeech = repromptSpeech
        return SpeechletResponse.newAskResponse(outputSpeech, reprompt, createCard())
    }

    private StandardCard createCard() {
        def card = new StandardCard()
        card.title = 'Workout plan'
        card.text = 'Plank exercises'
        def image = new Image()
        image.largeImageUrl = 'https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/excercises.jpg'
        image.smallImageUrl = 'https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/excercises.jpg'
        card.image = image
        card
    }

    public SpeechletResponse getCancelText() {
        def outputSpeech = new PlainTextOutputSpeech()
        outputSpeech.text = 'Ok, see you later'
        return SpeechletResponse.newTellResponse(outputSpeech)
    }

    public SpeechletResponse processIntent(IntentRequest request) {
        switch (request?.intent?.name) {
            case 'Help':
                return getHelpText()
            case 'Ready':
                return getWorkout()
            case 'Cancel':
                return getCancelText()
            default:
                return getWelcomeText()
        }
    }
}
