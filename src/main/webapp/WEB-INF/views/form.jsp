<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header class="header--form-page">
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="1" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="2">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
            <p data-step="3">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form modelattribute="donation" method="post">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>

                <c:forEach var="category" items="${categories}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="checkbox" name="categories" id="input-categories" value="${category.id}"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                        <input type="number" name="quantity" step="1" min="1" id="input-number-bags"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 3 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>

                <c:forEach var="institution" items="${institutions}">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="radio" name="institution" value="${institution.id}" id="input-organization"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                            <div class="title" id="input-organization-name${institution.id}">
                                    ${institution.name}
                            </div>
                            <div class="subtitle">
                                    ${institution.description}
                            </div>
                            </span>
                        </label>
                    </div>
                </c:forEach>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Ulica <input type="text" name="street" id="input-address"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Miasto <input type="text" name="city" id="input-city"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Kod pocztowy <input type="text" name="zipCode" id="input-postcode"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Numer telefonu <input type="phone" name="phone" id="input-phone"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Data <input type="date" name="pickUpDate" id="input-date"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Godzina <input type="time" name="pickUpTime" id="input-time"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <textarea name="pickUpComment" rows="5" id="textarea-more-info"></textarea>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" onclick="summaryForm()">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="5">
                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>
                                <span class="summary--text"
                                      id="span-count-bags"></span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text" id="span-institution"></span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul>
                                <li id="li-address"></li>
                                <li id="li-city"></li>
                                <li id="li-postcode"></li>
                                <li id="li-phone"></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul>
                                <li id="li-date"></li>
                                <li id="li-time"></li>
                                <li id="li-more-info"></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <script type="text/javascript">

                    function summaryForm() {

                        let index = document.querySelectorAll('input[name="institution"]');
                        let selectedValue;
                        for (const i of index) {
                            if (i.checked) {
                                selectedValue = i.value;
                                break;
                            }
                        }

                        let input_number_bags = document.getElementById("input-number-bags");
                        let input_organization = document.getElementById("input-organization-name" + selectedValue);
                        let input_address = document.getElementById("input-address");
                        let input_city = document.getElementById("input-city");
                        let input_postcode = document.getElementById("input-postcode");
                        let input_phone = document.getElementById("input-phone");
                        let input_date = document.getElementById("input-date");
                        let input_time = document.getElementById("input-time");
                        let textarea_more_info = document.getElementById("textarea-more-info");

                        document.getElementById("span-count-bags").innerText = input_number_bags.value + ' worki ubrań w dobrym stanie dla dzieci';
                        document.getElementById("span-institution").innerText = input_organization.innerText;
                        document.getElementById("li-address").innerText = input_address.value;
                        document.getElementById("li-city").innerText = input_city.value;
                        document.getElementById("li-postcode").innerText = input_postcode.value;
                        document.getElementById("li-phone").innerText = input_phone.value;
                        document.getElementById("li-date").innerText = input_date.value;
                        document.getElementById("li-time").innerText = input_time.value;
                        if (textarea_more_info.value === '') {
                            document.getElementById("li-more-info").innerText = 'Brak uwag';
                        } else {
                            document.getElementById("li-more-info").innerText = textarea_more_info.value;
                        }
                    }
                </script>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>
        </form>
    </div>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>