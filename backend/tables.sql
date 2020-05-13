CREATE TABLE content(
    id                           int auto_increment primary key,
    title                        varchar(128)       null,
    context                      text               not null,
    create_time                  datetime           null,
    update_time                  datetime           null
);