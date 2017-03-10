import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.encoder.LayoutWrappingEncoder
import com.google.cloud.logging.GoogleCloudLoggingV2Layout

import static ch.qos.logback.classic.Level.*

def appenderList = ["LAMBDA"]

logger("ai.cubic", DEBUG)

appender("LAMBDA", ConsoleAppender) {
    encoder(LayoutWrappingEncoder) {
        layout(GoogleCloudLoggingV2Layout) {
            appendLineSeparator = true
            serviceName = appName
            jsonFormatter = new com.google.cloud.logging.GSONJsonFormatter()
        }
    }
}

root(INFO, appenderList)