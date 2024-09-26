CREATE TABLE `role`
(
    id        BIGINT AUTO_INCREMENT NOT NULL,
    deleted   BIT(1)                NOT NULL,
    role_name VARCHAR(255)          NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE token
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    deleted     BIT(1)                NOT NULL,
    token_value VARCHAR(255)          NULL,
    expiry_date datetime              NULL,
    user_id     BIGINT                NULL,
    CONSTRAINT pk_token PRIMARY KEY (id)
);

CREATE TABLE user
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    deleted        BIT(1)                NOT NULL,
    email          VARCHAR(255)          NULL,
    password       VARCHAR(255)          NULL,
    name           VARCHAR(255)          NULL,
    email_verified BIT(1)                NOT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    user_id  BIGINT NOT NULL,
    roles_id BIGINT NOT NULL
);

ALTER TABLE token
    ADD CONSTRAINT FK_TOKEN_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (roles_id) REFERENCES `role` (id);

ALTER TABLE user_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES user (id);