<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<jsp:include page="fragments/header.jsp" flush="true"/>

<header class="header--form-page">
    <jsp:include page="fragments/header-menu.jsp" flush="true"/>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                <spring:message code="form.h1.slogan.inner-text"/> <br/>
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title"><spring:message code="form.div.slogan.inner-text"/> </div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span><spring:message code="form.div.span1.slogan.inner-text"/> </span></div>
                    </li>
                    <li>
                        <div><em>2</em><span><spring:message code="form.div.span2.slogan.inner-text"/> </span></div>
                    </li>
                    <li>
                        <div><em>3</em><span><spring:message code="form.div.span3.slogan.inner-text"/> </span></div>
                    </li>
                    <li>
                        <div><em>4</em><span><spring:message code="form.div.span4.slogan.inner-text"/> </span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3><spring:message code="form.form-steps.h3.inner-text"/> </h3>
            <p data-step="1" class="active">
                <spring:message code="form.form-steps.p1.inner-text"/>
            </p>
            <p data-step="2">
                <spring:message code="form.form-steps.p2.inner-text"/>
            </p>
            <p data-step="3">
                <spring:message code="form.form-steps.p3.inner-text"/>
            </p>
            <p data-step="4">
                <spring:message code="form.form-steps.p4.inner-text"/>
            </p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/4</div>

        <form modelattribute="donation" method="post">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3><spring:message code="form.form-steps.step-1.h3.inner-text"/> </h3>

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
                    <button type="button" class="btn next-step">
                    <spring:message code="form.form-steps.btn-next.inner-text"/> </button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3><spring:message code="form.form-steps.step-2.h3.inner-text"/> </h3>

                <div class="form-group form-group--inline">
                    <label>
                        <spring:message code="form.form-steps.step-2.label-input.inner-text"/>
                        <input type="number" name="quantity" step="1" min="1" id="input-number-bags"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">
                        <spring:message code="form.form-steps.btn-prev.inner-text"/>
                    </button>
                    <button type="button" class="btn next-step">
                        <spring:message code="form.form-steps.btn-next.inner-text"/>
                    </button>
                </div>
            </div>

            <!-- STEP 3 -->
            <div data-step="3">
                <h3><spring:message code="form.form-steps.step-3.h3.inner-text"/> </h3>

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
                    <button type="button" class="btn prev-step">
                        <spring:message code="form.form-steps.btn-prev.inner-text"/>
                    </button>
                    <button type="button" class="btn next-step">
                        <spring:message code="form.form-steps.btn-next.inner-text"/>
                    </button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="4">
                <h3><spring:message code="form.form-steps.step-4.h3.inner-text"/> </h3>
<input value="${loggedUser}" type="hidden" id="hidden-value">
                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4><spring:message code="form.form-steps.step-4.h4-col1.inner-text"/> </h4>
                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="form.form-steps.step-4.label-address.inner-text"/>
                                <input type="text" name="street" id="input-address"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="form.form-steps.step-4.label-city.inner-text"/>
                                <input type="text" name="city" id="input-city"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="form.form-steps.step-4.label-zipcode.inner-text"/>
                                <input type="text" name="zipCode" id="input-postcode"/>
                            </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="form.form-steps.step-4.label-phone.inner-text"/>
                                <input type="phone" name="phone" id="input-phone"/>
                            </label>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4><spring:message code="form.form-steps.step-4.h4-col2.inner-text"/> </h4>
                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="form.form-steps.step-4.label-data.inner-text"/>
                                <input type="date" name="pickUpDate" id="input-date"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="form.form-steps.step-4.label-time.inner-text"/>
                                <input type="time" name="pickUpTime" id="input-time"
                                                   min="09:00" max="21:00" required/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                <spring:message code="form.form-steps.step-4.label-info.inner-text"/>
                                <textarea name="pickUpComment" rows="5" id="textarea-more-info"></textarea>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">
                        <spring:message code="form.form-steps.btn-prev.inner-text"/>
                    </button>
                    <button type="button" class="btn next-step">
                        <spring:message code="form.form-steps.btn-next.inner-text"/>
                    </button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="5">
                <h3><spring:message code="form.form-steps.step-5.h3.inner-text"/> </h3>

                <div class="summary">
                    <div class="form-section">
                        <h4><spring:message code="form.form-steps.step-5.h4-give.inner-text"/> </h4>
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
                            <h4><spring:message code="form.form-steps.step-5.h4-address.inner-text"/> </h4>
                            <ul>
                                <li id="li-address"></li>
                                <li id="li-city"></li>
                                <li id="li-postcode"></li>
                                <li id="li-phone"></li>
                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4><spring:message code="form.form-steps.step-5.h4-time.inner-text"/> </h4>
                            <ul>
                                <li id="li-date"></li>
                                <li id="li-time"></li>
                                <li id="li-more-info"></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">
                        <spring:message code="form.form-steps.btn-prev.inner-text"/>
                    </button>
                    <button type="submit" class="btn">
                        <spring:message code="form.form-steps.btn-submit.inner-text"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</section>

<jsp:include page="fragments/footer.jsp" flush="true"/>