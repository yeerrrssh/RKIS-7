CREATE TABLE IF NOT EXISTS fridge (
    id SERIAL PRIMARY KEY,
    model VARCHAR(255),
    brand VARCHAR(255),
    color VARCHAR(255),
    price DECIMAL,
    quantity INT
    );


INSERT INTO fridge (model, brand, color, price, quantity)
VALUES
    ('A-12', 'LG', 'White', 35000, 4),
    ('TSL-3', 'LG', 'Black', 38000, 8),
    ('TZ-30', 'Atlant', 'Beige', 130000, 5),
    ('FRozen', 'Samsung', 'Gray', 64000, 12),
    ('XYZ-35', 'Atlant', 'White', 55000, 14),
    ('JYP', 'Snaige', 'Red', 99000, 1),
    ('CUBE', 'Bosch', 'Black', 85000, 16),
    ('YG', 'Liebherr', 'Orange', 38500, 18);