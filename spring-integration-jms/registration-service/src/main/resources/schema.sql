CREATE TABLE registration
(
    id  varchar(80) NOT NULL PRIMARY KEY,
    firstname   varchar(30) NOT NULL,
    lastname    varchar(30) NOT NULL,
    country      varchar(80) NOT NULL,
    message      varchar(255) NULL
);