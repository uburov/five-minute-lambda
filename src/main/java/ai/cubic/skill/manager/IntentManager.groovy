package ai.cubic.skill.manager

import com.amazon.speech.speechlet.IntentRequest
import com.amazon.speech.speechlet.SpeechletResponse
import com.amazon.speech.ui.*

/**
 * Created by Salmond on 10/03/17.
 */
class IntentManager {
    private static final String WELCOME = 'Welcome to 5-minute plank workout. You can see how to perform exercises at your Alexa app. Take a push up position and say "ready" to start'
    private static final String WELCOME_DE = 'Willkommen beim 5-Minuten Plank Training. In der Alexa App kannst Du nachsehen, wie man die Übungen ausführt. Nimm nun die Liegestützen Position ein und sage „bereit“ um zu starten.'
    private static final String WELCOME_REPROMPT = 'When you are ready to start your workout, say "I am ready"'
    private static final String WELCOME_REPROMPT_DE = 'Wenn du bereit bist, dein Training zu starten, dann sag einfach: "Ich bin bereit”'
    private static final String HELP =
            'This is 5-minute plank workout. 4 simple exercise to strengthen your core muscles. Look at your Alexa app to learn how to perform the exercises. To start the workout, say "I am ready"'
    private static final String HELP_DE = 'Dies ist ein 5-Minuten Plank Training. Das sind 4 einfache Übung zur Stärkung deiner Rumpfmuskulatur. In Deiner Alexa App kannst du nachsehen, wie man die Übungen durchführt. Um das Training zu beginnen, sag einfach "Ich bin bereit"'
    private static final String HELP_REPROMPT = 'Ask me to open 5-minute plank skill again whenever you are ready'
    private static final String HELP_REPROMPT_DE = 'Starte das 5-Minuten Plank Training, wann immer du bereit bist.'
    private static final String WORKOUT = '''
<speak>
\t<p>First excercise: full plank</p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t50 seconds left <break time="7s"/>
\t40 seconds <break time="8s"/>
\t30 seconds <break time="8s"/>
\t20 seconds <break time="8s"/>
\t10 seconds <break time="8s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>

\t10 seconds rest <break time="5s"/>
\t<p>Elbow plank, in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t20 seconds <break time="7s"/>
\t10 seconds <break time="7s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>

\t10 seconds rest <break time="5s"/>
\t<p>One leg plank, in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="8s"/>
\t10 seconds left <break time="5s"/>
\t<p>Change the leg, in</p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<break time="8s"/>
\t10 seconds left <break time="6s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<p>Good job!</p>
\t<break time="600ms"/>

\t10 seconds rest <break time="5s"/>
\t<p>Side plank, in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t20 seconds left <break time="6s"/>
\t10 seconds <break time="6s"/>
\t<p>Switch sides, in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\tSwitch! <break time="3s"/>
\t<break time="10s"/>
\t20 seconds left <break time="6s"/>
\t10 seconds <break time="6s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>

\t10 seconds rest <break time="5s"/>
\t<p>Full plank, in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t20 seconds left <break time="6s"/>
\t10 seconds left <break time="6s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>

\t10 seconds rest and the last, elbow plank<break time="4s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\tStart!
\t<break time="10s"/>
\t20 seconds left <break time="6s"/>
\t10 seconds  <break time="6s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\tStop!
\t<p>Congratulations! Your 5-minute plank workout is complete! See you tomorrow!</p>
\t<p>Please, review the skill on Amazon!</p>
</speak>'''

    private static final String WORKOUT_DE = '''
<speak>
\t<p>Erste Übung: Push Plank</p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\tNoch 50 Sekunden <break time="7s"/>
\t40 Sekunden <break time="8s"/>
\t30 Sekunden <break time="8s"/>
\t20 Sekunden <break time="8s"/>
\t10 Sekunden <break time="8s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>

\t10 Sekunden Erholung <break time="5s"/>
\t<p>Unterarmstütz, in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\t20 Sekunden <break time="7s"/>
\t10 Sekunden <break time="7s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>

\t10 Sekunden Erholung <break time="5s"/>
\t<p>Einbeiniger Unterarmstütz, in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="8s"/>
\tNoch 10 Sekunden <break time="5s"/>
\t<p>Wechsel das Bein, in</p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<break time="8s"/>
\tNoch 10 Sekunden <break time="6s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<p>Gut gemacht!</p>
\t<break time="600ms"/>

\t10 Sekunden Erholung <break time="5s"/>
\t<p>Seitliche Planke in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\tNoch 20 Sekunden <break time="6s"/>
\t10 Sekunden <break time="6s"/>
\t<p>Seiten wechseln in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\tWechsel! <break time="3s"/>
\t<break time="10s"/>
\tNoch 20 Sekunden <break time="6s"/>
\t10 Sekunden <break time="6s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>

\t10 Sekunden Erholung <break time="5s"/>
\t<p>Push Plank, in </p>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\t<audio src="https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/beep.mp3" />
\t<break time="10s"/>
\tNoch 20 Sekunden <break time="6s"/>
\t10 Sekunden <break time="6s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>

\t10 Sekunden Ruhe und die letzte Übung, Unterarmstütz<break time="4s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\tStart!
\t<break time="10s"/>
\tNoch 20 Sekunden <break time="6s"/>
\t10 Sekunden  <break time="6s"/>
\t3 <break time="600ms"/>
\t2 <break time="600ms"/>
\t1 <break time="600ms"/>
\tStopp!
\t<p>Glückwunsch! Du hast dein 5-Minuten Plank Training absolviert! Bis morgen! Wir freuen uns auf Deine </p>
\t<p>Bewertung bei Amazon!</p>
</speak>'''

    public SpeechletResponse getWelcomeText(Locale locale) {
        PlainTextOutputSpeech out = new PlainTextOutputSpeech()
        out.setText(locale.equals(Locale.GERMANY) ? WELCOME_DE : WELCOME)

        Reprompt reprompt = new Reprompt()
        def repromptSpeech = new PlainTextOutputSpeech()
        repromptSpeech.text = locale.equals(Locale.GERMANY) ? WELCOME_REPROMPT_DE : WELCOME_REPROMPT
        reprompt.setOutputSpeech(repromptSpeech)
        return SpeechletResponse.newAskResponse(out, reprompt, createCard(locale))
    }

    public SpeechletResponse getWorkout(Locale locale) {
        def outputSpeech = new SsmlOutputSpeech()
        outputSpeech.ssml = locale.equals(Locale.GERMANY) ? WORKOUT_DE : WORKOUT
        return SpeechletResponse.newTellResponse(outputSpeech)
    }

    public SpeechletResponse getHelpText(Locale locale) {
        def outputSpeech = new PlainTextOutputSpeech()
        outputSpeech.text = locale.equals(Locale.GERMANY) ? HELP_DE : HELP
        def reprompt = new Reprompt()
        def repromptSpeech = new PlainTextOutputSpeech()
        repromptSpeech.text = locale.equals(Locale.GERMANY) ? HELP_REPROMPT_DE : HELP_REPROMPT
        reprompt.outputSpeech = repromptSpeech
        return SpeechletResponse.newAskResponse(outputSpeech, reprompt, createCard(locale))
    }

    private StandardCard createCard(Locale locale) {
        def card = new StandardCard()
        card.title = locale.equals(Locale.GERMANY) ? 'Trainingsplan' : 'Workout plan'
        card.text = locale.equals(Locale.GERMANY) ? 'Plank Übungen' :'Plank exercises'
        def image = new Image()
        image.largeImageUrl = locale.equals(Locale.GERMANY) ? 'https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/excercises_de.jpg' : 'https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/excercises.jpg'
        image.smallImageUrl = locale.equals(Locale.GERMANY) ? 'https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/excercises_de.jpg' : 'https://s3.amazonaws.com/cubic.resources.skills/five_min_plank/excercises.jpg'
        card.image = image
        card
    }

    public SpeechletResponse getCancelText(Locale locale) {
        def outputSpeech = new PlainTextOutputSpeech()
        outputSpeech.text = locale.equals(Locale.GERMANY) ? 'Okay, bis später' : 'Ok, see you later'
        return SpeechletResponse.newTellResponse(outputSpeech)
    }

    public SpeechletResponse processIntent(IntentRequest request) {
        switch (request?.intent?.name) {
            case 'Help':
            case 'AMAZON.HelpIntent':
                return getHelpText(request.locale)
            case 'Ready':
                return getWorkout(request.locale)
            case 'Cancel':
            case 'AMAZON.StopIntent':
            case 'AMAZON.CancelIntent':
                return getCancelText(request.locale)
            default:
                return getWelcomeText(request.locale)
        }
    }
}
