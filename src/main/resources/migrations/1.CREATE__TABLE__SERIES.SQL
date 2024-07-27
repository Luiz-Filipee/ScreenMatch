CREATE TABLE series
(
    id               BIGINT PRIMARY KEY,
    atores           VARCHAR(255),
    avaliacao        DOUBLE PRECISION,
    genero           VARCHAR(255),
    poster           VARCHAR(255),
    sinopse          VARCHAR(255),
    titulo           VARCHAR(255),
    total_temporadas INTEGER
);