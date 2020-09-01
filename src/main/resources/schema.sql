drop table if exists car;

create table car
(
    id    BIGINT SERIAL NOT NULL,
    make  VARCHAR(255)  NOT NULL,
    model VARCHAR(255)  NOT NULL,
    year  INT           NOT NULL
);

