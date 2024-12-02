CREATE TABLE IF NOT EXISTS category (

            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            product_id INT NOT NULL,
            description VARCHAR(255),
            name VARCHAR(255)
) AUTO_INCREMENT = 50;

CREATE TABLE IF NOT EXISTS product
(
            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
            description VARCHAR(255),
            name VARCHAR(255),
            available_quantity INT NOT NULL,
            price DECIMAL(38, 2),
            category_id INT,
                        CONSTRAINT fk1m FOREIGN KEY (category_id) REFERENCES category(id)
) AUTO_INCREMENT = 50;

SET @@auto_increment_increment = 50;

