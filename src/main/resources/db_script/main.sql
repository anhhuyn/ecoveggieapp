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

