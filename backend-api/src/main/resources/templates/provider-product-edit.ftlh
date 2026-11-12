<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Edit Product | Fit District</title>

    <link rel="stylesheet" href="/css/styles.css">
</head>

<body>

    <aside class="sidebar">
        <div class="sidebar-logo">
            <h2>FIT <span>DI$TRICT</span></h2>
            <p>Provider Portal</p>
        </div>

        <nav class="sidebar-nav">
            <a href="/provider/products" class="active">
                Manage Products
            </a>
        </nav>
    </aside>

    <main class="dashboard">

        <header class="dashboard-header">
            <div>
                <p class="page-label">PROVIDER PORTAL</p>
                <h1>Edit Product</h1>
                <p>Update your product information.</p>
            </div>
        </header>

        <section class="dashboard-section">

            <form action="/provider/products/${product.id}/edit" method="post" class="product-form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-group">
                    <label for="name">Product Name</label>
                    <input type="text" id="name" name="name" value="${product.name}" required>
                </div>

                <div class="form-group">
                    <label for="price">Price ($)</label>
                    <input type="number" id="price" name="price" min="0" step="0.01" value="${product.price?c}"
                        required>
                </div>

                <div class="form-group">
                    <label for="stockQuantity">Stock Quantity</label>
                    <input type="number" id="stockQuantity" name="stockQuantity" min="0"
                        value="${product.stockQuantity!0}" required>
                </div>

                <div class="form-group full-width">
                    <label for="description">Description</label>
                    <textarea id="description" name="description">${(product.description!'')}</textarea>
                </div>

                <div class="full-width">
                    <button type="submit" class="primary-btn">
                        Save Changes
                    </button>

                    <a href="/provider/products" class="primary-btn">
                        Cancel
                    </a>
                </div>

            </form>

        </section>

    </main>

</body>

</html>