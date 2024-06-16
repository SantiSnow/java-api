CREATE TABLE IF NOT EXISTS Runs (
    id INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title VARCHAR(300) NOT NULL,
    kilometers INT NOT NULL,
    startTime TIMESTAMP NOT NULL,
    endTime TIMESTAMP NOT NULL,
    location VARCHAR(300) NOT NULL
);