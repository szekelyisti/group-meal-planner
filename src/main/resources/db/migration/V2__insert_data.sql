-- Insert demo users
INSERT INTO users (name, email) VALUES
                                    ('John Doe', 'johndoe@example.com'),
                                    ('Jane Smith', 'janesmith@example.com');

-- Insert demo ingredients
INSERT INTO ingredients (name, unit, approximated_price) VALUES
                                                             ('Flour', 'kg', 250),
                                                             ('Sugar', 'kg', 300);

-- Insert demo meals
INSERT INTO meals (name, instruction, approximated_price) VALUES
                                                              ('Pizza', 'Instructions for making pizza', 2500),
                                                              ('Pasta', 'Instructions for making pasta', 3000);

-- Insert demo groups
INSERT INTO groups (owner_id, name, number_of_members, members) VALUES
    (1, 'Family', 4, '["John Doe", "Jane Smith", "Alice Jones", "Bob Brown"]');

-- Insert demo groups_meals_link
INSERT INTO groups_meals_link (group_id, meal_id, approximated_price) VALUES
    (1, 1, 2500);

-- Insert demo meals_ingredients_link
INSERT INTO meals_ingredients_link (meal_id, ingredient_id, quantity) VALUES
    (1, 1, 0.5);
