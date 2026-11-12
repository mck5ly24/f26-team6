<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Products | Fit District</title>
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

        <div class="sidebar-bottom">
            <p>Provider Dashboard</p>
        </div>

    </aside>
    <main class="dashboard">

        <header class="dashboard-header">
            <div>
                <p class="page-label">PROVIDER PORTAL</p>
                <h1>Manage Products</h1>
                <p>View and manage your Fit Di$trict product listings.</p>
            </div>
            <button class="primary-btn" id="showAddProduct" type="button">
                + Add Product
            </button>
        </header>

        <!-- Add Product Form -->
        <section class="dashboard-section add-product-section" id="addProductSection" style="display: none;">

            <div class="section-title">
                <div>
                    <p class="page-label">NEW LISTING</p>
                    <h2>Add Product</h2>
                </div>
                <button type="button" class="close-btn" id="closeAddProduct">×</button>
            </div>

            <form id="addProductForm" class="product-form" action="/provider/products" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-group">
                    <label for="productName">Product Name</label>
                    <input type="text" id="productName" name="name" required>
                </div>

                <div class="form-group">
                    <label for="productPrice">Price ($)</label>
                    <input type="number" id="productPrice" name="price" min="0" step="0.01" required>
                </div>

                <div class="form-group">
                    <label for="productStock">Stock Quantity</label>
                    <input type="number" id="productStock" name="stockQuantity" min="0" required>
                </div>

                <div class="form-group full-width">
                    <label for="productDescription">Description</label>
                    <textarea id="productDescription" name="description"></textarea>
                </div>

                <div class="full-width">
                    <button type="submit" class="primary-btn">
                        Save Product
                    </button>
                </div>

            </form>
        </section>

        <section class="dashboard-section">
            <div class="section-title">
                <div>
                    <p class="page-label">YOUR STORE</p>
                    <h2>Product Listings</h2>
                </div>
            </div>

            <#if products?has_content>
                <#list products as product>

                    <article class="provider-product-card">

                        <div class="provider-product-info">

                            <span class="status-badge">
                                ${product.moderationStatus}
                            </span>

                            <h3>${product.name}</h3>

                            <p>${product.description!''}</p>

                            <strong>
                                $${product.price?string["0.00"]}
                            </strong>

                            <a href="/provider/products/${product.id}/edit" class="primary-btn">
                                Edit Product
                            </a>


                            <form action="/provider/products/${product.id}/delete" method="post"
                                style="display: inline-block;"
                                onsubmit="return confirm('Are you sure you want to delete this product?');">

                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                                <button type="submit" class="primary-btn">
                                    Delete Product
                                </button>

                            </form>

                        </div>

                    </article>

                </#list>
                <#else>
                    <p>No products found.</p>
            </#if>
        </section>
    </main>


    <script>
        const addButton = document.getElementById("showAddProduct");
        const closeButton = document.getElementById("closeAddProduct");
        const addSection = document.getElementById("addProductSection");

        addButton.addEventListener("click", function () {
            addSection.style.display = "block";
            addSection.scrollIntoView({ behavior: "smooth" });
        });

        closeButton.addEventListener("click", function () {
            addSection.style.display = "none";
        });
    </script>
</body>

</html>