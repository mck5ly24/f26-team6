
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fit District | Customer Profile</title>
    <link rel="stylesheet" href="/styles.css">
</head>

<body>

    <header>
        <div class="logo">
            <h1>Fit District</h1>
            <p>Discover. Compare. Save.</p>
        </div>

        <nav>
            <a href="/customers/register">Register</a>
            <a href="/customers/profile/${customer.customerId?c}">Profile</a>
        </nav>
    </header>

    <main>

        <section class="page-section">
            <p class="eyebrow">YOUR ACCOUNT.</p>
            <h2>Customer Profile</h2>
            <p>
                Manage your Fit District customer information.
            </p>
        </section>

        <section class="profile-container">

            <#-- Display success message -->
            <#if success??>
                <div class="success-message" role="status">
                    <p>${success?html}</p>
                </div>
            </#if>

            <#-- Display error message -->
            <#if error??>
                <div class="error-message" role="alert">
                    <p>${error?html}</p>
                </div>
            </#if>

            <h3>Account Information</h3>

            <p>
                <strong>Customer ID:</strong>
                ${customer.customerId?c}
            </p>

            <p>
                <strong>Account Status:</strong>
                ${(customer.accountStatus!'ACTIVE')?html}
            </p>

            <form
                id="profileForm"
                action="/customers/profile/${customer.customerId?c}"
                method="post"
            >

                <#-- CSRF token if enabled -->
                <#if _csrf??>
                    <input
                        type="hidden"
                        name="${_csrf.parameterName}"
                        value="${_csrf.token}"
                    >
                </#if>

                <label for="fullName">
                    Full Name
                </label>

                <input
                    type="text"
                    id="fullName"
                    name="fullName"
                    value="${(customer.fullName!'')?html}"
                    placeholder="Enter your full name"
                    required
                >

                <label for="email">
                    Email
                </label>

                <input
                    type="email"
                    id="email"
                    name="email"
                    value="${(customer.email!'')?html}"
                    placeholder="Enter your email"
                    required
                >

                <label for="phoneNumber">
                    Phone Number
                </label>

                <input
                    type="tel"
                    id="phoneNumber"
                    name="phoneNumber"
                    value="${(customer.phoneNumber!'')?html}"
                    placeholder="Enter your phone number"
                >

                <label for="stylePreference">
                    Style Preference
                </label>

                <select
                    id="stylePreference"
                    name="stylePreference"
                >
                    <option value="">
                        Select your style
                    </option>

                    <#list ["Streetwear", "Casual", "Athletic", "Formal"] as style>
                        <option
                            value="${style}"
                            <#if (customer.stylePreference!'') == style>
                                selected
                            </#if>
                        >
                            ${style}
                        </option>
                    </#list>
                </select>

                <button type="submit">
                    Save Profile
                </button>

            </form>

        </section>

    </main>

    <footer>
        <p>&copy; 2026 Fit District</p>
    </footer>

</body>
</html>
