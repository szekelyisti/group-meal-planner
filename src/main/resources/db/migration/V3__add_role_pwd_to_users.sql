-- Add a 'password' column of type VARCHAR
ALTER TABLE users
    ADD COLUMN password VARCHAR(255);
ALTER TABLE users
    ADD COLUMN role VARCHAR(255);
ALTER TABLE users
    ADD COLUMN username VARCHAR(255);


UPDATE users
    SET password = '$2a$10$VRJh6RWQ7EcQw2G9GMSKY.yhaw7bReoq5fJbBiomk1Xaosa2OgJni',
    role = 'ROLE_ADMIN',
    username = 'johndoe'
WHERE email = 'johndoe@example.com';

UPDATE users
    SET password = '$2a$10$mSmYDoB8jSrdrTvEK9vTIesiV53q5.B/Ln2IWweHmqRCC.7Rw.ZhG',
    role = 'ROLE_USER',
    username = 'janesmith'
WHERE email = 'janesmith@example.com';