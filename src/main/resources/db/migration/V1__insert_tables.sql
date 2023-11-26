-- Create Users Table
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE
);

-- Create Ingredients Table
CREATE TABLE ingredients (
                             id SERIAL PRIMARY KEY,
                             name VARCHAR(255) NOT NULL,
                             unit VARCHAR(50),
                             approximated_price INTEGER
);

-- Create Meals Table
CREATE TABLE meals (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       instruction TEXT,
                       approximated_price INTEGER
);

-- Create Groups Table
CREATE TABLE groups (
                        id SERIAL PRIMARY KEY,
                        owner_id INTEGER NOT NULL,
                        name VARCHAR(255) NOT NULL,
                        number_of_members INTEGER,
                        members jsonb,
                        FOREIGN KEY (owner_id) REFERENCES users(id)
);

-- Create GroupsMealsLink Table
CREATE TABLE groups_meals_link (
                                   id SERIAL PRIMARY KEY,
                                   group_id INTEGER NOT NULL,
                                   meal_id INTEGER NOT NULL,
                                   approximated_price INTEGER,
                                   members jsonb,
                                   FOREIGN KEY (group_id) REFERENCES groups(id),
                                   FOREIGN KEY (meal_id) REFERENCES meals(id)
);

-- Create MealsIngredientsLink Table
CREATE TABLE meals_ingredients_link (
                                        id SERIAL PRIMARY KEY,
                                        meal_id INTEGER NOT NULL,
                                        ingredient_id INTEGER NOT NULL,
                                        quantity DECIMAL(10,2),
                                        FOREIGN KEY (meal_id) REFERENCES meals(id),
                                        FOREIGN KEY (ingredient_id) REFERENCES ingredients(id)
);
