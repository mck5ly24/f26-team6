/*
  Name: Ronald Hester Jr.
  Date: 09/20/2026
  CSC 340

  Fit Di$trict Milestone 2 Prototype
  Provider Portal Interactions
*/


// ======================================================
// PROVIDER - MANAGE PRODUCTS
// ======================================================

const addButton = document.getElementById("showAddProduct");
const closeButton = document.getElementById("closeAddProduct");
const addSection = document.getElementById("addProductSection");
const addForm = document.getElementById("addProductForm");
const productList = document.getElementById("productList");
const searchInput = document.getElementById("providerProductSearch");


// ----------------------
// OPEN ADD PRODUCT FORM
// ----------------------

if (addButton && addSection) {

    addButton.addEventListener("click", function () {

        addForm.reset();

        addForm.dataset.editing = "false";
        addForm.editingCard = null;

        const submitButton =
            addForm.querySelector('button[type="submit"]');

        submitButton.textContent = "Add Product";

        addSection.style.display = "block";

        addSection.scrollIntoView({
            behavior: "smooth"
        });

    });

}


// ----------------------
// CLOSE ADD PRODUCT FORM
// ----------------------

if (closeButton && addSection) {

    closeButton.addEventListener("click", function () {

        addSection.style.display = "none";

        addForm.reset();

        addForm.dataset.editing = "false";
        addForm.editingCard = null;

        const submitButton =
            addForm.querySelector('button[type="submit"]');

        submitButton.textContent = "Add Product";

    });

}


// ----------------------
// ADD / UPDATE PRODUCT
// ----------------------

if (addForm && productList) {

    addForm.addEventListener("submit", function (event) {

        event.preventDefault();

        const name =
            document.getElementById("productName").value.trim();

        const category =
            document.getElementById("productCategory").value;

        const price =
            document.getElementById("productPrice").value;


        if (name === "" || category === "" || price === "") {

            alert("Please complete all required product fields.");

            return;
        }


        // EDIT EXISTING PRODUCT

        if (addForm.dataset.editing === "true") {

            const card = addForm.editingCard;

            card.querySelector("h3").textContent =
                name;

            card.querySelector(
                ".provider-product-info p"
            ).textContent = category;

            card.querySelector(
                ".provider-product-info strong"
            ).textContent =
                "$" + Number(price).toFixed(2);


            addForm.dataset.editing = "false";
            addForm.editingCard = null;

            addForm.querySelector(
                'button[type="submit"]'
            ).textContent = "Add Product";

            addForm.reset();

            addSection.style.display = "none";

            alert("Product updated successfully!");

            return;
        }


        // ADD NEW PRODUCT

        const product =
            document.createElement("article");

        product.className =
            "provider-product-card";

        product.innerHTML = `
            <div class="product-placeholder">
                NEW
            </div>

            <div class="provider-product-info">

                <span class="status-badge">
                    ACTIVE
                </span>

                <h3>${name}</h3>

                <p>${category}</p>

                <strong>
                    $${Number(price).toFixed(2)}
                </strong>

            </div>

            <div class="product-metrics">

                <span>0 views</span>

                <span>No ratings</span>

            </div>

            <div class="product-actions">

                <button
                    type="button"
                    class="edit-btn"
                >
                    Edit
                </button>

                <button
                    type="button"
                    class="delete-btn"
                >
                    Remove
                </button>

            </div>
        `;


        productList.prepend(product);

        addForm.reset();

        addSection.style.display = "none";

        alert("Product added successfully!");

    });

}


// ----------------------
// EDIT PRODUCT
// ----------------------

document.addEventListener("click", function (event) {

    if (!event.target.classList.contains("edit-btn")) {
        return;
    }

    const card =
        event.target.closest(".provider-product-card");

    if (!card || !addForm || !addSection) {
        return;
    }

    const nameInput =
        document.getElementById("productName");

    const categoryInput =
        document.getElementById("productCategory");

    const priceInput =
        document.getElementById("productPrice");

    const descriptionInput =
        document.getElementById("productDescription");


    // Make sure fields can be edited
    nameInput.disabled = false;
    nameInput.readOnly = false;

    categoryInput.disabled = false;

    priceInput.disabled = false;
    priceInput.readOnly = false;

    descriptionInput.disabled = false;
    descriptionInput.readOnly = false;


    // Get current product information
    const currentName =
        card.querySelector("h3").textContent.trim();

    const currentCategory =
        card.querySelector(
            ".provider-product-info p"
        ).textContent.trim();

    const currentPrice =
        card.querySelector(
            ".provider-product-info strong"
        ).textContent
            .replace("$", "")
            .trim();


    // Put current information into form
    nameInput.value = currentName;

    categoryInput.value = currentCategory;

    priceInput.value = currentPrice;

    descriptionInput.value =
        "Update the product description here.";


    // Tell the form which card is being edited
    addForm.dataset.editing = "true";

    addForm.editingCard = card;


    // Change button
    const submitButton =
        addForm.querySelector(
            'button[type="submit"]'
        );

    submitButton.textContent =
        "Save Changes";


    // Show form
    addSection.style.display =
        "block";


    // Focus the product name automatically
    nameInput.focus();


    // Scroll to form
    addSection.scrollIntoView({
        behavior: "smooth",
        block: "start"
    });

});

// ----------------------
// REMOVE PRODUCT
// ----------------------

document.addEventListener("click", function (event) {

    if (!event.target.classList.contains("delete-btn")) {
        return;
    }

    const card =
        event.target.closest(".provider-product-card");

    if (!card) {
        return;
    }


    const productName =
        card.querySelector("h3").textContent;


    const confirmed =
        confirm(
            "Remove " +
            productName +
            " from your products?"
        );


    if (confirmed) {

        card.remove();

        alert("Product removed.");

    }

});


// ----------------------
// SEARCH PRODUCTS
// ----------------------

if (searchInput) {

    searchInput.addEventListener("input", function () {

        const searchValue =
            searchInput.value
                .trim()
                .toLowerCase();


        const products =
            document.querySelectorAll(
                ".provider-product-card"
            );


        products.forEach(function (product) {

            const name =
                product.querySelector("h3")
                    .textContent
                    .toLowerCase();

            const category =
                product.querySelector(
                    ".provider-product-info p"
                )
                    .textContent
                    .toLowerCase();


            if (
                name.includes(searchValue) ||
                category.includes(searchValue)
            ) {

                product.style.display = "grid";

            } else {

                product.style.display = "none";

            }

        });

    });

}



// ======================================================
// PROVIDER - PROFILE
// ======================================================

const profileForm =
    document.getElementById("providerProfileForm");

const editProfileButton =
    document.getElementById("editProfileButton");

const saveProfileButton =
    document.getElementById("saveProfileButton");


// ----------------------
// EDIT PROFILE
// ----------------------

if (
    editProfileButton &&
    profileForm &&
    saveProfileButton
) {

    editProfileButton.addEventListener(
        "click",
        function () {

            const fields =
                profileForm.querySelectorAll(
                    "input, textarea"
                );


            fields.forEach(function (field) {

                field.disabled = false;

            });


            editProfileButton.style.display =
                "none";

            saveProfileButton.style.display =
                "inline-block";

        }
    );

}


// ----------------------
// SAVE PROFILE
// ----------------------

if (
    profileForm &&
    editProfileButton &&
    saveProfileButton
) {

    profileForm.addEventListener(
        "submit",
        function (event) {

            event.preventDefault();


            const brandName =
                document.getElementById(
                    "brandName"
                ).value.trim();

            const category =
                document.getElementById(
                    "providerCategory"
                ).value.trim();


            if (brandName === "") {

                alert(
                    "Brand name cannot be empty."
                );

                return;
            }


            const displayBrand =
                document.getElementById(
                    "displayBrandName"
                );

            const displayCategory =
                document.getElementById(
                    "displayCategory"
                );


            if (displayBrand) {

                displayBrand.textContent =
                    brandName;

            }


            if (displayCategory) {

                displayCategory.textContent =
                    category;

            }


            const fields =
                profileForm.querySelectorAll(
                    "input, textarea"
                );


            fields.forEach(function (field) {

                field.disabled = true;

            });


            saveProfileButton.style.display =
                "none";

            editProfileButton.style.display =
                "inline-block";


            alert(
                "Provider profile updated successfully!"
            );

        }
    );

}



// ======================================================
// PROVIDER - REVIEWS
// ======================================================


// ----------------------
// OPEN REPLY BOX
// ----------------------

document.addEventListener("click", function (event) {

    if (!event.target.classList.contains("reply-btn")) {
        return;
    }


    const review =
        event.target.closest(
            ".provider-review-card"
        );


    if (!review) {
        return;
    }


    const replyArea =
        review.querySelector(
            ".provider-reply"
        );


    const existingReply =
        replyArea.querySelector(
            ".reply-display p"
        );


    const existingText =
        existingReply
            ? existingReply.textContent.trim()
            : "";


    replyArea.innerHTML = `
        <div class="reply-form">

            <textarea
                class="reply-textarea"
                placeholder="Write your response..."
            >${existingText}</textarea>

            <button
                type="button"
                class="save-reply-btn"
            >
                Save Reply
            </button>

        </div>
    `;


    event.target.style.display =
        "none";

});


// ----------------------
// SAVE REVIEW REPLY
// ----------------------

document.addEventListener("click", function (event) {

    if (
        !event.target.classList.contains(
            "save-reply-btn"
        )
    ) {
        return;
    }


    const review =
        event.target.closest(
            ".provider-review-card"
        );


    const replyArea =
        review.querySelector(
            ".provider-reply"
        );


    const textarea =
        replyArea.querySelector(
            ".reply-textarea"
        );


    const replyText =
        textarea.value.trim();


    if (replyText === "") {

        alert(
            "Please enter a reply before saving."
        );

        return;
    }


    replyArea.innerHTML = `
        <div class="reply-display">

            <strong>
                Cazuals replied:
            </strong>

            <p>${replyText}</p>

        </div>
    `;


    const replyButton =
        review.querySelector(
            ".reply-btn"
        );


    replyButton.textContent =
        "Edit Reply";

    replyButton.style.display =
        "inline-block";


    alert("Reply saved successfully!");

});


// ----------------------
// FILTER REVIEWS
// ----------------------

const reviewFilter =
    document.getElementById(
        "reviewFilter"
    );


if (reviewFilter) {

    reviewFilter.addEventListener(
        "change",
        function () {

            const selectedRating =
                reviewFilter.value;


            const reviews =
                document.querySelectorAll(
                    ".provider-review-card"
                );


            reviews.forEach(function (review) {

                if (
                    selectedRating === "all" ||
                    review.dataset.rating ===
                    selectedRating
                ) {

                    review.style.display =
                        "block";

                } else {

                    review.style.display =
                        "none";

                }

            });

        }
    );

}



// ======================================================
// PROVIDER - ACTIVITY
// ======================================================

const activityFilter =
    document.getElementById(
        "activityFilter"
    );


if (activityFilter) {

    activityFilter.addEventListener(
        "change",
        function () {

            const selectedType =
                activityFilter.value;


            const items =
                document.querySelectorAll(
                    ".timeline-item"
                );


            items.forEach(function (item) {

                if (
                    selectedType === "all" ||
                    item.dataset.type ===
                    selectedType
                ) {

                    item.style.display =
                        "flex";

                } else {

                    item.style.display =
                        "none";

                }

            });

        }
    );

}



// ======================================================
// SIGN OUT BUTTONS
// ======================================================

const signOutButtons =
    document.querySelectorAll(".sidebar-bottom button");

signOutButtons.forEach(function (button) {

    button.addEventListener("click", function () {

        const confirmed =
            confirm("Are you sure you want to sign out?");

        if (confirmed) {

            // Redirect to the prototype login page
            window.location.href = "login.html";

        }

    });

});

// ======================================================
// SYSADMIN - REVIEW MODERATION
// ======================================================

const adminReviewFilter =
    document.getElementById("adminReviewFilter");

const reportedCount =
    document.getElementById("reportedCount");

const removedCount =
    document.getElementById("removedCount");

const dismissedCount =
    document.getElementById("dismissedCount");

const emptyModeration =
    document.getElementById("emptyModeration");


// Update number of reported reviews
function updateModerationQueue() {

    const remainingReports =
        document.querySelectorAll(".moderation-card");

    if (reportedCount) {
        reportedCount.textContent =
            remainingReports.length;
    }

    if (
        emptyModeration &&
        remainingReports.length === 0
    ) {

        emptyModeration.style.display =
            "block";

    }

}


// ----------------------
// REMOVE REVIEW
// ----------------------

document.addEventListener("click", function (event) {

    if (
        !event.target.classList.contains(
            "remove-review-btn"
        )
    ) {
        return;
    }


    const card =
        event.target.closest(".moderation-card");

    const productName =
        card.querySelector("h3").textContent.trim();


    const confirmed =
        confirm(
            "Remove the reported review for " +
            productName +
            "?"
        );


    if (!confirmed) {
        return;
    }


    card.remove();


    if (removedCount) {

        removedCount.textContent =
            Number(removedCount.textContent) + 1;

    }


    updateModerationQueue();


    alert(
        "The review has been removed from Fit Di$trict."
    );

});


// ----------------------
// DISMISS REPORT
// ----------------------

document.addEventListener("click", function (event) {

    if (
        !event.target.classList.contains(
            "dismiss-report-btn"
        )
    ) {
        return;
    }


    const card =
        event.target.closest(".moderation-card");


    const confirmed =
        confirm(
            "Dismiss this report and keep the review?"
        );


    if (!confirmed) {
        return;
    }


    card.remove();


    if (dismissedCount) {

        dismissedCount.textContent =
            Number(dismissedCount.textContent) + 1;

    }


    updateModerationQueue();


    alert(
        "Report dismissed. The review will remain visible."
    );

});


// ----------------------
// FILTER REPORTS
// ----------------------

if (adminReviewFilter) {

    adminReviewFilter.addEventListener(
        "change",
        function () {

            const selectedReason =
                adminReviewFilter.value;


            const reports =
                document.querySelectorAll(
                    ".moderation-card"
                );


            reports.forEach(function (report) {

                if (
                    selectedReason === "all" ||
                    report.dataset.reason ===
                    selectedReason
                ) {

                    report.style.display =
                        "block";

                } else {

                    report.style.display =
                        "none";

                }

            });

        }
    );

}


// ======================================================
// SYSADMIN - USAGE STATISTICS
// ======================================================

const statisticsPeriod =
    document.getElementById("statisticsPeriod");


if (statisticsPeriod) {

    statisticsPeriod.addEventListener(
        "change",
        function () {

            const period =
                statisticsPeriod.value;


            // LAST 7 DAYS
            if (period === "7") {

                document.getElementById("statUsers").textContent =
                    "2,418";

                document.getElementById("statUsersChange").textContent =
                    "+42 this week";

                document.getElementById("statViews").textContent =
                    "4,630";

                document.getElementById("statViewsChange").textContent =
                    "+5% from last week";

                document.getElementById("statCustomerActivity").textContent =
                    "1,724";

                document.getElementById("statProviderActivity").textContent =
                    "286";

                document.getElementById("viewsValue").textContent =
                    "4,630";

                document.getElementById("searchesValue").textContent =
                    "3,210";

                document.getElementById("reviewsValue").textContent =
                    "924";

                document.getElementById("updatesValue").textContent =
                    "286";

                document.getElementById("viewsBar").style.width =
                    "78%";

                document.getElementById("searchesBar").style.width =
                    "60%";

                document.getElementById("reviewsBar").style.width =
                    "38%";

                document.getElementById("updatesBar").style.width =
                    "22%";

            }


            // LAST 30 DAYS
            if (period === "30") {

                document.getElementById("statUsers").textContent =
                    "2,418";

                document.getElementById("statUsersChange").textContent =
                    "+184 this month";

                document.getElementById("statViews").textContent =
                    "18,420";

                document.getElementById("statViewsChange").textContent =
                    "+12% from last month";

                document.getElementById("statCustomerActivity").textContent =
                    "6,842";

                document.getElementById("statProviderActivity").textContent =
                    "1,126";

                document.getElementById("viewsValue").textContent =
                    "18,420";

                document.getElementById("searchesValue").textContent =
                    "13,205";

                document.getElementById("reviewsValue").textContent =
                    "3,892";

                document.getElementById("updatesValue").textContent =
                    "1,126";

                document.getElementById("viewsBar").style.width =
                    "92%";

                document.getElementById("searchesBar").style.width =
                    "72%";

                document.getElementById("reviewsBar").style.width =
                    "48%";

                document.getElementById("updatesBar").style.width =
                    "32%";

            }


            // LAST 90 DAYS
            if (period === "90") {

                document.getElementById("statUsers").textContent =
                    "2,418";

                document.getElementById("statUsersChange").textContent =
                    "+516 in 90 days";

                document.getElementById("statViews").textContent =
                    "51,760";

                document.getElementById("statViewsChange").textContent =
                    "+28% over 90 days";

                document.getElementById("statCustomerActivity").textContent =
                    "19,408";

                document.getElementById("statProviderActivity").textContent =
                    "3,481";

                document.getElementById("viewsValue").textContent =
                    "51,760";

                document.getElementById("searchesValue").textContent =
                    "38,410";

                document.getElementById("reviewsValue").textContent =
                    "10,205";

                document.getElementById("updatesValue").textContent =
                    "3,481";

                document.getElementById("viewsBar").style.width =
                    "98%";

                document.getElementById("searchesBar").style.width =
                    "82%";

                document.getElementById("reviewsBar").style.width =
                    "58%";

                document.getElementById("updatesBar").style.width =
                    "42%";

            }

        }
    );

}

// ======================================================
// FIT DI$TRICT LOGIN
// ======================================================

const loginForm = document.getElementById("loginForm");

if (loginForm) {

    loginForm.addEventListener("submit", function (event) {

        // Prevent the form from refreshing the page
        event.preventDefault();

        const email =
            document.getElementById("loginEmail").value.trim();

        const password =
            document.getElementById("loginPassword").value.trim();

        if (email === "" || password === "") {
            alert("Please enter your email and password.");
            return;
        }

        // Prototype login goes to Provider Dashboard
        window.location.href = "provider-dashboard.html";

    });

}


// ======================================================
// PROTOTYPE ROLE LOGIN
// ======================================================

const roleButtons =
    document.querySelectorAll(".role-login-btn");

roleButtons.forEach(function (button) {

    button.addEventListener("click", function () {

        const role = button.dataset.role;

        if (role === "provider") {
            window.location.href = "provider-dashboard.html";
        }

        if (role === "admin") {
            window.location.href = "admin-dashboard.html";
        }

    });

});


// ======================================================
// FORGOT PASSWORD
// ======================================================

const forgotPassword =
    document.getElementById("forgotPassword");

if (forgotPassword) {

    forgotPassword.addEventListener("click", function (event) {

        event.preventDefault();

        alert(
            "Password reset instructions would be sent to your email."
        );

    });

}


// ======================================================
// CREATE ACCOUNT
// ======================================================

const createAccount =
    document.getElementById("createAccount");

if (createAccount) {

    createAccount.addEventListener("click", function (event) {

        event.preventDefault();

        alert(
            "Account registration would open here in the completed Fit Di$trict system."
        );

    });

}