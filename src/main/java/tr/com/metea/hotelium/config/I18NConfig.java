package tr.com.metea.hotelium.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author Mete Aydin
 * @since 28.10.2021
 */
@Configuration
public class I18NConfig {
    @Bean(name = "messageSource")
    public MessageSource bundleMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/messages");
        messageSource.setCacheSeconds(10);
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
