package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Service
public class CookiesService {

    public String getLocationByCookie(HttpServletRequest request) {

        Cookie[] cookies = request.getCookies();
        String cookieValue = "";
        for (Cookie c : cookies) {
            if ("lang".equals(c.getName())) {
                cookieValue = c.getValue();
            }
        }
        return cookieValue;
    }

    public void setLocationInCookie(Locale locale, HttpServletResponse response) {

        Cookie cookie = new Cookie("lang", locale.toString());
        response.addCookie(cookie);
    }
}
