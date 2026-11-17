
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <title>Fit District | Create Account</title>
    <link rel="stylesheet" href="/styles.css">
</head>

<body>
    <header>
        <div class="logo">
            <h1>Fit District</h1>
            <p>Discover. Compare. Save.</p>
        </div>
    </header>

    <main>
        <section class="page-section">
            <p class="eyebrow">JOIN FIT DISTRICT</p>
            <h2>Create Your Account</h2>
            <p>Register to manage your customer profile.</p>
        </section>

        <section class="profile-container">

            <#if error??>
                <p role="alert">${error?html}</p>
            </#if>

            <form action="/customers/register" method="post">

                <#if _csrf??>
                    <input type="hidden"
                           name="${_csrf.parameterName}"
                           value="${_csrf.token}">
                </#if>

                <label for="fullName">Full Name</label>
                <input type="text"
                       id="fullName"
                       name="fullName"
                       required>

                <label for="email">Email</label>
                <input type="email"
                       id="email"
                       name="email"
                       required>

                <label for="phoneNumber">Phone Number</label>
                <input type="tel"
                       id="phoneNumber"
                       name="phoneNumber">

                <label for="stylePreference">Style Preference</label>
                <select id="stylePreference"
                        name="stylePreference">
                    <option value="">Select your style</option>
                    <option value="Streetwear">Streetwear</option>
                    <option value="Casual">Casual</option>
                    <option value="Athletic">Athletic</option>
                    <option value="Formal">Formal</option>
                </select>

                <button type="submit">
                    Create Account
                </button>

            </form>
        </section>
    </main>

    <footer>
        <p>&copy; 2026 Fit District</p>
    </footer>
</body>
</html>
