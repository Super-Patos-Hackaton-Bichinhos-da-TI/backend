
CREATE TABLE "users" (
    "id"        UUID PRIMARY KEY,
    "email"     VARCHAR(100) NOT NULL,
    "username"  VARCHAR(60) NOT NULL,
    "password"  VARCHAR(40) NOT NULL,
    "biography" VARCHAR(1000) NOT NULL,
    "office"    VARCHAR(100) NOT NULL,
    "xp"        INT,
    "level"     INT,
    "squad"     INT,
    "pet"       INT,
    "role"      INT
);