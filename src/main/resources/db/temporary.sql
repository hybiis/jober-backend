INSERT INTO member(member_name, member_email, password, phone_number, created_at, updated_at, deleted_at)
VALUES ('변백현', 'baek2@gmail.com', '1234', '010-1175-1312', NOW(), NOW(), NULL),
       ('이수민', 'sumin@gmail.com', '1234', '010-1135-2486', NOW(), NOW(), NULL),
       ('김영희', 'younghee@gmail.com', '1234', '010-1158-3741', NOW(), NOW(), NULL),
       ('정민준', 'minjun@gmail.com', '1234', '010-1179-4285', NOW(), NOW(), NULL),
       ('박지영', 'jiyoung@gmail.com', '1234', '010-1136-5279', NOW(), NOW(), NULL),
       ('최우진', 'woojin@gmail.com', '1234', '010-1157-6384', NOW(), NOW(), NULL),
       ('한지훈', 'jihun@gmail.com', '1234', '010-1178-7942', NOW(), NOW(), NULL),
       ('서예진', 'yejin@gmail.com', '1234', '010-1139-8425', NOW(), NOW(), NULL),
       ('윤다미', 'dami131@gmail.com', '1234', '010-6012-4904', NOW(), NOW(), NULL),
       ('이하빈', 'habin132@gmail.com', '1234', '010-6123-6015', NOW(), NOW(), NULL);


INSERT INTO add_space (space_title, space_type, member_id, created_at, updated_at, deleted_at)
VALUES
    ('변백현', 'PERSONAL', 1, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 1, NOW(), NOW(), NULL),
    ('이수민', 'PERSONAL', 2, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 2, NOW(), NOW(), NULL),
    ('김영희', 'PERSONAL', 3, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 3, NOW(), NOW(), NULL),
    ('정민준', 'PERSONAL', 4, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 4, NOW(), NOW(), NULL),
    ('박지영', 'PERSONAL', 5, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 5, NOW(), NOW(), NULL),
    ('최우진', 'PERSONAL', 6, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 6, NOW(), NOW(), NULL),
    ('한지훈', 'PERSONAL', 7, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 7, NOW(), NOW(), NULL),
    ('서예진', 'PERSONAL', 8, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 8, NOW(), NOW(), NULL),
    ('윤다미', 'PERSONAL', 9, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 9, NOW(), NOW(), NULL),
    ('이하빈', 'PERSONAL', 10, NOW(), NOW(), NULL),
    ('임시회사명', 'ORGANIZATION', 10, NOW(), NOW(), NULL);


insert into member_group (member_hashtag_type, account_type, add_space_id, member_id, created_at, updated_at,deleted_at)
values
    ('FRIEND', 'PERSONAL', 1, 3,NOW(), NOW(), NULL),
    ('FRIEND', 'PERSONAL', 1, 2,NOW(), NOW(), NULL),
    ('FRIEND', 'ENTERPRISE', 1, 6,NOW(), NOW(), NULL),
    ('FRIEND', 'PERSONAL', 1, 7,NOW(), NOW(), NULL),
    ('FRIEND', 'ENTERPRISE', 1, 8,NOW(), NOW(), NULL),
    ('FRIEND', 'PERSONAL', 1, 9,NOW(), NOW(), NULL),
    ('FRIEND', 'ENTERPRISE', 1, 10,NOW(), NOW(), NULL),
    ('FRIEND', 'PERSONAL', 2, 1,NOW(), NOW(), NULL),
    ('FRIEND', 'ENTERPRISE', 2, 3,NOW(), NOW(), NULL),
    ('FRIEND', 'ENTERPRISE', 2, 5,NOW(), NOW(), NULL),
    ('FRIEND', 'PERSONAL', 2, 10,NOW(), NOW(), NULL),
    ('FRIEND', 'PERSONAL', 2, 9,NOW(), NOW(), NULL),
    ('FRIEND', 'ENTERPRISE', 2, 7,NOW(), NOW(), NULL),
    ('FRIEND', 'ENTERPRISE', 2, 4,NOW(), NOW(), NULL);

insert into template_auth (has_access, auth_member_id, created_at, updated_at,deleted_at)
values
    (false,1,NOW(), NOW(), NULL),
    (false,2,NOW(), NOW(), NULL),
    (false,3,NOW(), NOW(), NULL),
    (false,4,NOW(), NOW(), NULL),
    (false,5,NOW(), NOW(), NULL),
    (false,6,NOW(), NOW(), NULL),
    (false,7,NOW(), NOW(), NULL);