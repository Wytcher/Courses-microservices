CREATE TABLE tb_brazil_ufs
(
    id         SERIAL      NOT NULL,
    uf_name    VARCHAR(30) NOT NULL,
    uf_acronym VARCHAR(30) NOT NULL,

    CONSTRAINT PK_tb_brazil_ufs PRIMARY KEY (id)
);

CREATE TABLE tb_users
(
    id                        SERIAL       NOT NULL,
    first_name                VARCHAR(50)  NOT NULL,
    last_name                 VARCHAR(80)  NOT NULL,
    email                     VARCHAR(120) NOT NULL,
    password                  VARCHAR(255) NOT NULL,
    password_reset_token      VARCHAR(255),
    password_reset_token_date TIMESTAMP,

    CONSTRAINT PK_tb_users PRIMARY KEY (id),
    CONSTRAINT UQ_tb_users_email UNIQUE (email)
);

CREATE TABLE tb_roles
(
    id        SERIAL      NOT NULL,
    role_name VARCHAR(30) NOT NULL,

    CONSTRAINT PK_tb_roles PRIMARY KEY (id)
);

CREATE TABLE tb_user_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,

    CONSTRAINT FK_user_roles_user FOREIGN KEY (user_id) REFERENCES tb_users (id),
    CONSTRAINT FK_user_roles_role FOREIGN KEY (role_id) REFERENCES tb_roles (id)
);

CREATE TABLE tb_student_details
(
    id                 SERIAL      NOT NULL,
    user_id            INT         NOT NULL,
    student_birth_date DATE        NOT NULL,
    student_cpf        VARCHAR(15) NOT NULL,
    student_rg         VARCHAR(15) NOT NULL,
    student_phone      VARCHAR(15) NOT NULL,

    CONSTRAINT PK_tb_student_details PRIMARY KEY (id),
    CONSTRAINT UQ_student_cpf UNIQUE (student_cpf),
    CONSTRAINT UQ_student_rg UNIQUE (student_rg),
    CONSTRAINT FK_tb_student_details_user FOREIGN KEY (user_id) REFERENCES tb_users (id)
);

CREATE TABLE tb_student_address
(
    id       SERIAL       NOT NULL,
    user_id  INT          NOT NULL,
    uf_id    INT          NOT NULL,
    zipcode  VARCHAR(15)  NOT NULL,
    street   VARCHAR(120) NOT NULL,
    number   VARCHAR(10)  NOT NULL,
    district VARCHAR(80)  NOT NULL,
    city     VARCHAR(50)  NOT NULL,

    CONSTRAINT PK_tb_student_address PRIMARY KEY (id),
    CONSTRAINT FK_student_address_user FOREIGN KEY (user_id) REFERENCES tb_users (id),
    CONSTRAINT FK_student_address_uf FOREIGN KEY (uf_id) REFERENCES tb_brazil_ufs (id)
);

CREATE TABLE tb_employee_details
(
    id                        SERIAL      NOT NULL,
    user_id                   INT         NOT NULL,
    employee_birth_date       DATE        NOT NULL,
    employee_cpf              VARCHAR(15) NOT NULL,
    employee_rg               VARCHAR(15) NOT NULL,
    employee_phone            VARCHAR(15) NOT NULL,
    employee_work_card_number VARCHAR(30) NOT NULL,

    CONSTRAINT PK_tb_employee_details PRIMARY KEY (id),
    CONSTRAINT UQ_employee_cpf UNIQUE (employee_cpf),
    CONSTRAINT UQ_employee_rg UNIQUE (employee_rg),
    CONSTRAINT UQ_employee_work_card_number UNIQUE (employee_work_card_number),
    CONSTRAINT FK_employee_details_user FOREIGN KEY (user_id) REFERENCES tb_users (id)
);