# DROP table `order`;
# DROP TABLE product;
# DROP TABLE brand;
# DROP TABLE member;


CREATE TABLE brand
(
    id         BIGINT AUTO_INCREMENT                                           NOT NULL PRIMARY KEY,
    name       VARCHAR(50)                                                     NOT NULL,
    corp_name  VARCHAR(50)                                                     NOT NULL,
    tel        VARCHAR(50)                                                     NOT NULL,
    address    VARCHAR(50)                                                     NOT NULL,
    created_at timestamp default CURRENT_TIMESTAMP                             NOT NULL,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE member
(
    id           BIGINT AUTO_INCREMENT                                           NOT NULL PRIMARY KEY,
    nick_name    VARCHAR(50)                                                     NOT NULL,
    address      VARCHAR(50)                                                     NOT NULL,
    age          INT                                                             NOT NULL,
    email        VARCHAR(255)                                                    NOT NULL,
    phone_number VARCHAR(50)                                                     NOT NULL,
    created_at   timestamp default CURRENT_TIMESTAMP                             NOT NULL,
    updated_at   timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE product
(
    id         BIGINT AUTO_INCREMENT                                           NOT NULL PRIMARY KEY,
    brand_id   BIGINT                                                          NOT NULL,
    name       VARCHAR(50)                                                     NOT NULL,
    category   VARCHAR(50)                                                     NOT NULL,
    status     VARCHAR(50)                                                     NOT NULL,
    price      DECIMAL                                                         NOT NULL,
    created_at timestamp default CURRENT_TIMESTAMP                             NOT NULL,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_BRAND FOREIGN KEY (brand_id) REFERENCES brand (id);

CREATE TABLE `order`
(
    id         BIGINT AUTO_INCREMENT                                           NOT NULL PRIMARY KEY,
    member_id  BIGINT                                                          NOT NULL,
    product_id BIGINT                                                          NOT NULL,
    amount     INT                                                             NOT NULL,
    created_at timestamp default CURRENT_TIMESTAMP                             NOT NULL,
    updated_at timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL
);

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_MEMBER FOREIGN KEY (member_id) REFERENCES member (id);

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

INSERT INTO member (id, nick_name, address, age, email, phone_number, created_at, updated_at)
VALUES (1, 'wang gyu', 'seoul', 29, 'tjdhkdrb@naver.com', '01025075337', '2022-05-15 18:38:08', '2022-05-15 18:38:08');
INSERT INTO member (id, nick_name, address, age, email, phone_number, created_at, updated_at)
VALUES (2, 'hye su', 'daejeon', 40, 'hy@naver.com', '01045341234', '2022-05-15 18:38:08', '2022-05-15 18:38:08');
INSERT INTO member (id, nick_name, address, age, email, phone_number, created_at, updated_at)
VALUES (3, 'su bin', 'busan', 25, 'bin@gmail.com', '01065346424', '2022-05-15 18:38:08', '2022-05-15 18:38:08');
INSERT INTO member (id, nick_name, address, age, email, phone_number, created_at, updated_at)
VALUES (4, 'jin ho', 'sejeon', 15, 'jin@line.com', '01068593534', '2022-05-15 18:38:08', '2022-05-15 18:38:08');

INSERT INTO brand (id, name, corp_name, tel, address, created_at, updated_at)
VALUES (1, 'galaxy', 'samsung', '0249825349', 'seoul', '2022-05-15 18:40:20', '2022-05-15 18:40:20');
INSERT INTO brand (id, name, corp_name, tel, address, created_at, updated_at)
VALUES (2, 'iphone', 'apple', '0314953290', 'busan', '2022-05-15 18:40:20', '2022-05-15 18:40:20');
INSERT INTO brand (id, name, corp_name, tel, address, created_at, updated_at)
VALUES (3, 'outback', 'outback', '0315495924', 'busan', '2022-05-15 18:40:20', '2022-05-15 18:40:20');
INSERT INTO brand (id, name, corp_name, tel, address, created_at, updated_at)
VALUES (4, 'nike', 'nike', '0293520595', 'seoul', '2022-05-15 18:40:20', '2022-05-15 18:40:20');

INSERT INTO product (id, brand_id, name, category, price, status, created_at, updated_at)
VALUES (1, 1, 'GALAXY S8', 'ELECTRONICS', 1000, 'ON_SALE', '2022-05-15 18:43:01', '2022-05-15 18:43:01');
INSERT INTO product (id, brand_id, name, category, price, status, created_at, updated_at)
VALUES (2, 1, 'GALAXY S20', 'ELECTRONICS', 2000, 'SOLD', '2022-05-15 18:43:01', '2022-05-15 18:43:01');
INSERT INTO product (id, brand_id, name, category, price, status, created_at, updated_at)
VALUES (3, 2, 'IPHONE PRO 10', 'ELECTRONICS', 1500, 'ON_SALE', '2022-05-15 18:43:01', '2022-05-15 18:43:01');
INSERT INTO product (id, brand_id, name, category, price, status, created_at, updated_at)
VALUES (4, 2, 'IPHONE MAX 10', 'ELECTRONICS', 3000, 'ON_SALE', '2022-05-15 18:43:01', '2022-05-15 18:43:01');
INSERT INTO product (id, brand_id, name, category, price, status, created_at, updated_at)
VALUES (5, 3, 'MAX DIET', 'FOOD', 200, 'SOLD', '2022-05-15 18:43:01', '2022-05-15 18:43:01');
INSERT INTO product (id, brand_id, name, category, price, status, created_at, updated_at)
VALUES (6, 3, 'COOKIE', 'FOOD', 300, 'ON_SALE', '2022-05-15 18:43:01', '2022-05-15 18:43:01');
INSERT INTO product (id, brand_id, name, category, price, status, created_at, updated_at)
VALUES (7, 4, 'FOOD1', 'CLOTHES', 700, 'ON_SALE', '2022-05-15 18:43:01', '2022-05-15 18:43:01');
INSERT INTO product (id, brand_id, name, category, price, status, created_at, updated_at)
VALUES (8, 4, 'FOOD2', 'CLOTHES', 3000, 'ON_SALE', '2022-05-15 18:43:01', '2022-05-15 18:43:01');

INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (1, 1, 1, 3, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (2, 1, 2, 2, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (3, 2, 3, 4, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (4, 3, 4, 4, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (5, 2, 5, 3, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (6, 3, 6, 7, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (7, 3, 7, 6, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (8, 4, 8, 6, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (9, 4, 1, 3, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (10, 3, 2, 5, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (11, 2, 5, 2, '2022-05-15 18:43:56', '2022-05-15 18:43:56');
INSERT INTO `order` (id, member_id, product_id, amount, created_at, updated_at)
VALUES (12, 1, 6, 10, '2022-05-15 18:43:56', '2022-05-15 18:43:56');

