CREATE TABLE member (
    id BINARY(16) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(255) NOT NULL,
    registered_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    disabled_at DATETIME,
    PRIMARY KEY (id),
    UNIQUE (email),
    UNIQUE (nickname)
);

CREATE TABLE article (
    id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    member_id BINARY(16) NOT NULL,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    is_private TINYINT(1) NOT NULL DEFAULT true,
    view_count INT UNSIGNED NOT NULL DEFAULT 0,
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    deleted_at DATETIME,
    PRIMARY KEY (id),
    UNIQUE (id, title),
    FOREIGN KEY (member_id) REFERENCES member(id)
);

CREATE TABLE comment (
    id INT UNSIGNED AUTO_INCREMENT NOT NULL,
    member_id BINARY(16) NOT NULL,
    article_id INT UNSIGNED NOT NULL,
    content VARCHAR(255),
    created_at DATETIME NOT NULL,
    updated_at DATETIME NOT NULL,
    deleted_at DATETIME,
    PRIMARY KEY (id),
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (article_id) REFERENCES article(id)
);

CREATE TABLE member_session (
    id BINARY(32) UNIQUE NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id)
);
