package microteam.configuration;

import microteam.log.LogAggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Log {

    private static final Logger logger = LoggerFactory.getLogger(Log.class);

    /**
     * Bean configuration to initialize log aggregator service.
     * @return LogAggregator instance
     */
    @Bean
    public LogAggregator logAggregator() {
        logger.info("LogAggregator Bean initialized.");
        return new LogAggregator();
    }
}
