-- Database Name:
CREATE DATABASE ecoveggieApp;
USE ecoveggieApp;


-- USER TABLE STRUCTURE:
CREATE TABLE users (
    user_id INT NOT NULL IDENTITY(1,1), 
    username VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    phone VARCHAR(15) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at DATETIME,  
    updated_at DATETIME,
    PRIMARY KEY (user_id)
);
ALTER TABLE users ADD avatar VARCHAR(255);
ALTER TABLE users ADD birthday DATETIME;
ALTER TABLE users ADD COLUMN gender NVARCHAR(10);





-- CATEGORY TABLE STRUCTURE:
CREATE TABLE categories (
    category_id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    category_name NVARCHAR(255) NOT NULL,
    thumbnail VARCHAR(255)
);

-- PRODUCT TABLE STRUCTURE:
CREATE TABLE products (
    product_id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    product_name NVARCHAR(255) NOT NULL,
    description NVARCHAR(500),
    price DECIMAL(10,2) NOT NULL,
    instock_quantity INT NOT NULL,
    category_id INT NOT NULL,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME NULL,
    unit NVARCHAR(50) NOT NULL,
    FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE
);
-- ADD COLUMN "Số lượng đã bán"
ALTER TABLE products
ADD sold_quantity INT NOT NULL DEFAULT 0;


-- PRODUCT IMAGES TABLE STRUCTURE:
CREATE TABLE product_images (
    product_image_id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    product_id INT NOT NULL,
    product_image VARCHAR(255) NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);


CREATE TABLE address (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    province NVARCHAR(255),
    district NVARCHAR(255),
    wards NVARCHAR(255),
    detail NVARCHAR(255),
    phone VARCHAR(20),
    is_default BIT DEFAULT 0,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);



CREATE TABLE orders (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    customer_id INT NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    payment_method NVARCHAR(200),
    status NVARCHAR(50),
    note NVARCHAR(MAX), -- Hỗ trợ tiếng Việt và Unicode
    address_id INT,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME NULL,
    deleted_at DATETIME NULL,

    FOREIGN KEY (customer_id) REFERENCES users(user_id),
    FOREIGN KEY (address_id) REFERENCES address(id)
);


CREATE TABLE order_details (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,

    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);


CREATE TABLE cart (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
    created_at DATETIME DEFAULT GETDATE(),
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);

CREATE TABLE cart_item (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    cart_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL CHECK (quantity >= 1),

    FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);

CREATE TABLE points (
    id INT NOT NULL IDENTITY(1,1) PRIMARY KEY,
    user_id INT NOT NULL,
	total_points INT NOT NULL DEFAULT 0,
	created_at DATETIME DEFAULT GETDATE(),
	updated_at DATETIME,
	count_day INT,
    
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
);




