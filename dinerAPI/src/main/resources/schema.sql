CREATE TABLE IF NOT EXISTS USER_ (
    ID INT not null AUTO_INCREMENT,
    NAME VARCHAR(255) not null,
    CITY VARCHAR(255),
    STATE VARCHAR(255),
    ZIPCODE INT,
    PEANUT_ALLERGY BIT,
    EGG_ALLERGY BIT,
    DAIRY_ALLERGY BIT,
    PRIMARY KEY (ID)
    );