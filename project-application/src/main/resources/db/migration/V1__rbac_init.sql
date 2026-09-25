CREATE TABLE sys_user (
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username      VARCHAR(50)  NOT NULL,
    password      VARCHAR(100) NOT NULL,
    nickname      VARCHAR(50),
    status        SMALLINT     NOT NULL DEFAULT 1,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CONSTRAINT uk_sys_user_username UNIQUE (username)
);

CREATE TABLE sys_role (
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_code     VARCHAR(50)  NOT NULL,
    role_name     VARCHAR(50)  NOT NULL,
    description   VARCHAR(200),
    status        SMALLINT NOT NULL DEFAULT 1,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CONSTRAINT uk_sys_role_code UNIQUE (role_code)
);

CREATE TABLE sys_permission (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    permission_code     VARCHAR(100)  NOT NULL,
    permission_name     VARCHAR(50)   NOT NULL,
    type                SMALLINT      NOT NULL,
    parent_id           BIGINT,
    path                VARCHAR(200),
    sort                INT NOT NULL DEFAULT 0,
    status              SMALLINT NOT NULL DEFAULT 1,
    created_at          TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at          TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CONSTRAINT uk_sys_permission_code UNIQUE (permission_code)
);



CREATE TABLE sys_user_role (
    user_id       BIGINT       NOT NULL,
    role_id       BIGINT       NOT NULL,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CONSTRAINT pk_sys_user_role PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES sys_user(id),
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES sys_role(id)
);

CREATE TABLE sys_role_permission (
    role_id             BIGINT NOT NULL,
    permission_id       BIGINT NOT NULL,
    created_at          TIMESTAMPTZ  NOT NULL DEFAULT now(),
    updated_at          TIMESTAMPTZ  NOT NULL DEFAULT now(),
    CONSTRAINT pk_sys_role_permission PRIMARY KEY (permission_id, role_id),
    CONSTRAINT fk_role_permission_role FOREIGN KEY (role_id) REFERENCES sys_role(id),
    CONSTRAINT fk_role_permission_permission FOREIGN KEY (permission_id) REFERENCES sys_permission(id)
);