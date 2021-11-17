package pl.coderslab.charity.component;

import java.util.Locale;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {
    @Autowired
    private MessageSource messageSource;
    private MessageSourceAccessor accessor;

    public Messages() {
    }

    @PostConstruct
    private void init() {
        Locale locale = Locale.getDefault();
        this.accessor = new MessageSourceAccessor(this.messageSource, locale);
    }

    public String get(String code) {
        return this.accessor.getMessage(code);
    }

//    public void setLocale(Language language) {
//        Locale locale = Locale.forLanguageTag("pl");
//        if (language != null) {
//            locale = language == Language.EN ? Locale.forLanguageTag("en") : Locale.forLanguageTag("pl");
//        }
//
//        this.accessor = new MessageSourceAccessor(this.messageSource, locale);
//    }
    public void setLocale(String language) {
        Locale locale = Locale.getDefault();
        if (!"".equals(language)) {
            locale = "en".equals(language) ? Locale.forLanguageTag("en") : Locale.forLanguageTag(language);
        }

        this.accessor = new MessageSourceAccessor(this.messageSource, locale);
    }
}