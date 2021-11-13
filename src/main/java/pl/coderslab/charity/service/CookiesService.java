package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
}
