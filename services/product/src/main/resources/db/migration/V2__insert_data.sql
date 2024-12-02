INSERT INTO category (id, description, name)
VALUES
        (50, 'Electronic devices and gadgets', 'Electronics'),
        (51, 'Books across various genres and topics', 'Books'),
        (52, 'Furniture and home decor items', 'Furniture'),
        (53, 'Clothing and fashion accessories', 'Apparel'),
        (54, 'Health and personal care products', 'Health & Beauty')

ON DUPLICATE KEY UPDATE name = VALUES(name);

INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES ('Test Product', 'Sample', 100, 9.99, 1),
        (50, 'Smartphone with 128GB storage and dual camera', 'Smartphone', 100, 699.99, 50),
        (51, '4K Ultra HD Smart LED TV 55 inches', 'Smart TV', 30, 1200.00, 50),
        (52, 'Bluetooth Noise Cancelling Headphones', 'Headphones', 75, 199.99, 50),
        (53, 'Mystery Novel by popular author', 'Mystery Book', 200, 15.99, 51),
        (54, 'Non-fiction guide to self-improvement', 'Self-Help Book', 150, 20.99, 51),
        (55, 'Ergonomic office chair with lumbar support', 'Office Chair', 50, 249.99, 52),
        (56, 'Wooden coffee table with storage', 'Coffee Table', 40, 180.00, 52),
        (57, 'Casual T-shirt, cotton, unisex', 'T-shirt', 500, 9.99, 53),
        (58, 'Designer leather wallet', 'Leather Wallet', 120, 49.99, 53),
        (59, 'Organic skin moisturizer', 'Moisturizer', 300, 25.50, 54),
        (60, 'Electric toothbrush with rechargeable battery', 'Toothbrush', 150, 39.99, 54);
