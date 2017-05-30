def logPattern = "%d{HH:mm:ss.SSS} [%thread] %highlight(%-5level) %logger{40}: %msg%n%ex{full, sun, java.lang, java.util, javax, CGLIB}"

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
}

root(INFO, ["STDOUT"])

logger("example", INFO)
logger("org.springframework", INFO)