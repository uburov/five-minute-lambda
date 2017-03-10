package ai.cubic.skill.speechlet

import ai.cubic.skill.manager.IntentManager
import com.amazon.speech.speechlet.*
import groovy.util.logging.Slf4j

/**
 * Created by Salmond on 10/03/17.
 */
@Slf4j
class WorkoutSpeechlet implements Speechlet {
    private static IntentManager intentManager = new IntentManager()

    void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
    }

    SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        log.info("onLaunch requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return intentManager.welcomeText
    }

    SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return intentManager.processIntent(request)
    }

    void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        log.info("Session ended")
    }

}
