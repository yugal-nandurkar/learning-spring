CREATE TABLE USER_DETAILS (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              name VARCHAR(20) NOT NULL,
                              birthday DATE NOT NULL
);

CREATE TABLE post (
    id INT AUTO_INCREMENT PRIMARY KEY, -- Primary key with auto-increment
    user_id INT,                      -- Foreign key referencing the User table
    description VARCHAR(255),         -- Column for post description
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES user_details(id) -- Foreign key constraint
);

