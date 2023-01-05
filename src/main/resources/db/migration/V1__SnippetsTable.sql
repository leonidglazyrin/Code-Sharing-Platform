CREATE TABLE snippets (
    id bytea NOT NULL,
    load_date timestamp(6),
    piece_of_code text,
    time bigint,
    views bigint,
    PRIMARY KEY (id)
)