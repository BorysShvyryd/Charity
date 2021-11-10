document.addEventListener("DOMContentLoaded", function () {

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

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

    /**
     * Switching between form steps
     */
    class FormSteps {
        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
        }

        /**
         * All events that are happening in form
         */
        events() {

            function checkedCurrentStep(currentStep) {
                switch (currentStep) {
                    case 1: {
                        for (let category of document.querySelectorAll("#input-categories"))
                            if (category.checked) return true;
                        return false;
                    }
                    case 2: {
                        let input_number_bags = document.getElementById("input-number-bags");
                        input_number_bags.style.borderColor = "black";
                        if (input_number_bags.value > 0) return true;
                        input_number_bags.style.borderColor = "red";
                        return false;
                    }
                    case 3: {
                        let index = document.querySelectorAll('input[name="institution"]');
                        for (const i of index) {
                            if (i.checked) {
                                return true;
                            }
                        }
                        return false;
                    }
                    case 4: {
                        let result = true;
                        let input_address = document.getElementById("input-address");
                        input_address.style.borderColor = "black";
                        if (input_address.value === "") {
                            input_address.style.borderColor = "red";
                            result = false;
                        }
                        let input_city = document.getElementById("input-city");
                        input_city.style.borderColor = "black";
                        if (input_city.value === "") {
                            input_city.style.borderColor = "red";
                            result = false;
                        }
                        let input_postcode = document.getElementById("input-postcode");
                        input_postcode.style.borderColor = "black";
                        if (input_postcode.value === "") {
                            input_postcode.style.borderColor = "red";
                            result = false;
                        }
                        let input_phone = document.getElementById("input-phone");
                        input_phone.style.borderColor = "black";
                        if (input_phone.value === "") {
                            input_phone.style.borderColor = "red";
                            result = false;
                        }
                        let input_date = document.getElementById("input-date");
                        input_date.style.borderColor = "black";
                        if (input_date.value === "") {
                            input_date.style.borderColor = "red";
                            result = false;
                        }
                        let input_time = document.getElementById("input-time");
                        input_time.style.borderColor = "black";
                        if (input_time.value === "") {
                            input_time.style.borderColor = "red";
                            result = false;
                        }
                        return result;
                    }
                    default:
                        return true;
                }
            }

            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    if (checkedCurrentStep(this.currentStep)) {
                        e.preventDefault();
                        this.currentStep++;
                        this.updateForm();
                    }
                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }

                // if (this.currentStep == 4) {
                //   document.getElementById("input-address").value =
                //       document.getElementById("hidden-value").value.name;
                //   document.getElementById("input-city").value = "";
                //   document.getElementById("input-postcode").value = "";
                //   document.getElementById("input-phone").value =
                //       document.getElementById("hidden-value").value.name;
                // }

                if (this.currentStep == 5) {
                    summaryForm();
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            // TODO: get data from inputs and show them in summary
        }

    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }
});
