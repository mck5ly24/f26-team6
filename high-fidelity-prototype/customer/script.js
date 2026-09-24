// Fit District - Customer Prototype
// JavaScript functionality

document.addEventListener("DOMContentLoaded", function () {

    const profileForm = document.getElementById("profileForm");

    if (profileForm) {

        const fullName = document.getElementById("fullName");
        const email = document.getElementById("email");
        const phone = document.getElementById("phone");
        const stylePreference = document.getElementById("stylePreference");
        const profileMessage = document.getElementById("profileMessage");

        // Load saved profile information
        const savedProfile = JSON.parse(localStorage.getItem("fitDistrictProfile"));

        if (savedProfile) {
            fullName.value = savedProfile.fullName;
            email.value = savedProfile.email;
            phone.value = savedProfile.phone;
            stylePreference.value = savedProfile.stylePreference;
        }

        // Save profile information
        profileForm.addEventListener("submit", function (event) {

            event.preventDefault();

            const profile = {
                fullName: fullName.value,
                email: email.value,
                phone: phone.value,
                stylePreference: stylePreference.value
            };

            localStorage.setItem(
                "fitDistrictProfile",
                JSON.stringify(profile)
            );

            profileMessage.textContent = "Profile saved successfully!";
        });
    }

});

// Subscribe buttons
const subscribeButtons = document.querySelectorAll(".subscribe-btn");

subscribeButtons.forEach(function (button) {
    button.addEventListener("click", function () {

        const card = button.closest(".offering-card");
        const provider = card.querySelector(".provider").textContent;

        let subscriptions =
            JSON.parse(localStorage.getItem("fitDistrictSubscriptions")) || [];

        if (!subscriptions.some(item => item.provider === provider)) {
            subscriptions.push({ provider: provider });

            localStorage.setItem(
                "fitDistrictSubscriptions",
                JSON.stringify(subscriptions)
            );

        }

        button.textContent = "Subscribed";
    });
});

// ===============================
// Display Subscriptions
// ===============================

const subscriptionsList = document.getElementById("subscriptionsList");

if (subscriptionsList) {
    const subscriptions =
        JSON.parse(localStorage.getItem("fitDistrictSubscriptions")) || [];

    subscriptionsList.innerHTML = "";

    if (subscriptions.length === 0) {
        subscriptionsList.innerHTML =
            "<p>You are not subscribed to any providers yet.</p>";
    } else
        subscriptions.forEach((subscription) => {
            const card = document.createElement("article");
            card.classList.add("offering-card");

            card.innerHTML = `
        <h3>${subscription.provider}</h3>
        <p>You are currently subscribed to this provider.</p>
        <button type="button" class="unsubscribe-btn">
            Unsubscribe
        </button>
    `;

            subscriptionsList.appendChild(card);

            const unsubscribeButton = card.querySelector(".unsubscribe-btn");

            unsubscribeButton.addEventListener("click", function () {
                const updatedSubscriptions = subscriptions.filter(
                    (item) => item.provider !== subscription.provider
                );

                localStorage.setItem(
                    "fitDistrictSubscriptions",
                    JSON.stringify(updatedSubscriptions)
                );

                card.remove();

                if (updatedSubscriptions.length === 0) {
                    subscriptionsList.innerHTML =
                        "<p>You are not subscribed to any providers yet.</p>";
                }
            });
        });
}