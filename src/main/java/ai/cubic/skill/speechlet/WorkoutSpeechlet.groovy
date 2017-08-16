package ai.cubic.skill.speechlet

import ai.cubic.skill.manager.IntentManager
import co.voicelabs.sdk.alexa.VoiceInsights
import com.amazon.speech.speechlet.*
import groovy.util.logging.Slf4j

/**
 * Created by Salmond on 10/03/17.
 */
@Slf4j
class WorkoutSpeechlet implements Speechlet {
    private static String voiceLabsToken = "24492400-07ce-11a7-2aa4-0e61e4c2ee12"
    private VoiceInsights voiceInsights = null
    private static IntentManager intentManager = new IntentManager()

    void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException {
        log.info("onSessionStarted requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        voiceInsights = new VoiceInsights(voiceLabsToken, session)
    }

    SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        log.info("onLaunch: requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        return intentManager.getWelcomeText(request.locale)
    }

    SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        log.info("Intent: ${request?.intent?.name} for request: ${request.getRequestId()} with session: ${session.getSessionId()}")
        log.info("onIntent requestId={}, sessionId={}", request.getRequestId(),
                session.getSessionId());
        def response =  intentManager.processIntent(request)
        voiceInsights.track(request?.intent?.name, request?.intent?.slots, response?.outputSpeech)
        return response
    }

    void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException {
        log.info("Session ended")
    }

}
