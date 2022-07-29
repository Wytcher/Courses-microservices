CREATE TABLE tb_course_categories
(
    id            SERIAL       NOT NULL,
    category_name VARCHAR(120) NOT NULL,

    CONSTRAINT PK_tb_courses_category PRIMARY KEY (id)
);

CREATE TABLE tb_courses
(
    id              SERIAL       NOT NULL,
    category_id     INT          NOT NULL,
    employee_id     INT          NOT NULL,
    employee_name   VARCHAR(150) NOT NULL,
    course_name     VARCHAR(150) NOT NULL,
    course_duration VARCHAR(50)  NOT NULL,

    CONSTRAINT PK_tb_courses PRIMARY KEY (id),
    CONSTRAINT FK_tb_courses_category FOREIGN KEY (category_id) REFERENCES tb_course_categories (id)
);

CREATE TABLE tb_course_enrollments
(
    id                SERIAL       NOT NULL,
    course_id         INT          NOT NULL,
    student_id        INT          NOT NULL,
    student_name      VARCHAR(150) NOT NULL,
    enrollment_status INT          NOT NULL,

    CONSTRAINT PK_tb_course_enrollment PRIMARY KEY (id),
    CONSTRAINT FK_tb_course_enrollment_courses FOREIGN KEY (course_id) REFERENCES tb_courses (id)
);
