--liquibase formatted sql

--changeset Giang:2


INSERT INTO role VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');

INSERT INTO seat_type VALUES (1,'Classic','3CB371', 15),(2,'VIP','ffb703', 30);

INSERT INTO `user` VALUES
(1,NULL,NULL,NULL,NULL,NULL,'Jack',NULL,NULL,'Daniel','$2a$10$90JM.duiCjHdXxHiAe2wpODQEs3IkU/tKMQp1KDKbtzK/HFexC9FW',NULL,NULL,NULL,NULL,NULL,'admin'),
(2,NULL,NULL,NULL,NULL,NULL,'Tullamore',NULL,NULL,'Dev','$2a$10$GwTxHY85SLFmtKfBk3uV3uu9lbwUnOaXjDcpYgIwibz26ViukmPE2',NULL,NULL,NULL,NULL,NULL,'tullamore.dev'),
(3,NULL,NULL,NULL,NULL,NULL,'Jager',NULL,NULL,'Meister','$2a$10$BVoj0Tt48tIY6Il0rXwdHu2BeND2F8m8BIqaJ4YtLvQz7zSaKFzoK',NULL,NULL,NULL,NULL,NULL,'jager.meister');

INSERT INTO user_roles VALUES (1,1),(2,2),(3,2);