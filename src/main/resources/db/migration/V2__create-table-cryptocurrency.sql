CREATE TABLE TB_CRYPTOCURRENCY(
    ID_CRYPTOCURRENCY SERIAL PRIMARY KEY,
    NM_CRYPTOCURRENCY VARCHAR(255) NOT NULL,
    TX_SYMBOL VARCHAR(100) NOT NULL UNIQUE,
    TX_PATH_ICON VARCHAR(255) NOT NULL
);