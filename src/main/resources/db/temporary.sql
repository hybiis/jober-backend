INSERT INTO member(member_name, member_email, password, phone_number, member_ship, created_at, updated_at, deleted_at)
VALUES ('변백현', 'baek2@gmail.com', '1234', '010-1175-1312', 'FREE', NOW(), NOW(), NULL),
       ('이수민', 'sumin@gmail.com', '1234', '010-1135-2486', 'FREE', NOW(), NOW(), NULL),
       ('김영희', 'younghee@gmail.com', '1234', '010-1158-3741', 'FREE', NOW(), NOW(), NULL),
       ('정민준', 'minjun@gmail.com', '1234', '010-1179-4285', 'STANDARD', NOW(), NOW(), NULL),
       ('박지영', 'jiyoung@gmail.com', '1234', '010-1136-5279', 'FREE', NOW(), NOW(), NULL),
       ('최우진', 'woojin@gmail.com', '1234', '010-1157-6384', 'FREE', NOW(), NOW(), NULL),
       ('한지훈', 'jihun@gmail.com', '1234', '010-1178-7942', 'STANDARD', NOW(), NOW(), NULL),
       ('서예진', 'yejin@gmail.com', '1234', '010-1139-8425', 'FREE', NOW(), NOW(), NULL),
       ('윤다미', 'dami131@gmail.com', '1234', '010-6012-4904', 'FREE', NOW(), NOW(), NULL),
       ('이하빈', 'habin132@gmail.com', '1234', '010-6123-6015', 'STANDARD', NOW(), NOW(), NULL),
       ('박서인', 'seoin133@gmail.com', '1234', '010-6234-7126', 'FREE', NOW(), NOW(), NULL),
       ('최지안', 'jian134@gmail.com', '1234', '010-6345-8237', 'FREE', NOW(), NOW(), NULL),
       ('김하늘', 'haneul135@gmail.com', '1234', '010-6456-9348', 'FREE', NOW(), NOW(), NULL),
       ('윤하나', 'hana136@gmail.com', '1234', '010-6567-0459', 'FREE', NOW(), NOW(), NULL),
       ('이서하', 'seoha137@gmail.com', '1234', '010-6678-1560', 'FREE', NOW(), NOW(), NULL),
       ('박다은', 'daeun138@gmail.com', '1234', '010-6789-2671', 'FREE', NOW(), NOW(), NULL),
       ('최수하', 'sooha139@gmail.com', '1234', '010-6890-3782', 'FREE', NOW(), NOW(), NULL),
       ('김지하', 'jinha140@gmail.com', '1234', '010-6901-4893', 'FREE', NOW(), NOW(), NULL);


INSERT INTO add_space (space_title, space_type, member_id, created_at, updated_at, deleted_at)
VALUES ('변백현', 'PERSONAL', 1, NOW(), NOW(), NULL),
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
       ('임시회사명', 'ORGANIZATION', 10, NOW(), NOW(), NULL),
       ('패스트캠퍼스 X Jober', 'ORGANIZATION', 1, NOW(), NOW(), NULL),
       ('근로계약서', 'ORGANIZATION', 1, NOW(), NOW(), NULL);

INSERT INTO member_group (member_hashtag_type, account_type, add_space_id, member_id, created_at, updated_at,
                          deleted_at)
VALUES ('EMPTY', 'PERSONAL', 1, 1, NOW(), NOW(), NULL),
       ('FRIEND', 'PERSONAL', 1, 3, NOW(), NOW(), NULL),
       ('FRIEND', 'PERSONAL', 1, 2, NOW(), NOW(), NULL),
       ('FRIEND', 'ENTERPRISE', 1, 6, NOW(), NOW(), NULL),
       ('FRIEND', 'PERSONAL', 1, 7, NOW(), NOW(), NULL),
       ('FRIEND', 'ENTERPRISE', 1, 8, NOW(), NOW(), NULL),
       ('FRIEND', 'PERSONAL', 1, 9, NOW(), NOW(), NULL),
       ('FRIEND', 'ENTERPRISE', 1, 10, NOW(), NOW(), NULL),
       ('EMPTY', 'PERSONAL', 2, 1, NOW(), NOW(), NULL),
       ('FRIEND', 'ENTERPRISE', 2, 3, NOW(), NOW(), NULL),
       ('FRIEND', 'ENTERPRISE', 2, 5, NOW(), NOW(), NULL),
       ('FRIEND', 'PERSONAL', 2, 10, NOW(), NOW(), NULL),
       ('FRIEND', 'PERSONAL', 2, 9, NOW(), NOW(), NULL),
       ('FRIEND', 'ENTERPRISE', 2, 7, NOW(), NOW(), NULL),
       ('FRIEND', 'ENTERPRISE', 2, 4, NOW(), NOW(), NULL);

INSERT INTO space_wall_category (space_wall_category)
VALUES ('CAREER'),
       ('PERSONAL'),
       ('EVENT'),
       ('ENTERPRISE'),
       ('BASIC');

INSERT INTO template (template_title, template_description, space_wall_category_id, created_at, updated_at, deleted_at)
VALUES ('면접확인서', '면접자가 회사에 요청하는 면접확인서 양식입니다.', 1, NOW(), NOW(), NULL),
       ('인턴 경력증명서', '인턴분에게 발급하는 경력증명서입니다. (제출용도 포함)', 1, NOW(), NOW(), NULL),
       ('디자이너 등록하기', '디자이너로 등록하기 위해 이력서와 포트폴리오 그리고 관심분야를 제출해주세요.', 1, NOW(), NOW(), NULL),
       ('채용 지원서 (자버)', '채용 지원서를 작성해주세요.', 1, NOW(), NOW(), NULL),

       ('재직증명서', '역요청 기능으로 근로자가 관리자에게 증명서 발급을 요청할 수 있습니다.(전자문서 환경설정에서 역요청 설정)', 2, NOW(), NOW(), NULL),
       ('퇴사자 경력증명서', '퇴사한 직원에게 발급하는 경력증명서입니다.', 2, NOW(), NOW(), NULL),
       ('개인정보 수집·이용 동의서', '근로자의 개인정보 수집 ·이용 동의서 양식입니다.', 2, NOW(), NOW(), NULL),
       ('중소기업 취업자 소득세 감면신청서',
        '「조세특례제한법」 제30조제1항 및 같은 법 시행령 제27조제5항에 따라 위와 같이 중소기업 취업자에 대한 소득세 감면을 신청할 수 있는 신청서입니다.(청년용)', 2, NOW(), NOW(),
        NULL),

       ('♥연애계약서♥ - 여자친구Ver', '[개인용 서식]사랑하는 연인들이 서로 지킬 약속에 대해 작성하는 문서입니다.', 3, NOW(), NOW(), NULL),
       ('하우스메이트 계약서', '[개인용 서식]하우스메이트에 관한 규칙을 정하는 계약서입니다.', 3, NOW(), NOW(), NULL),
       ('부모님 감사 카드', '[개인용 서식]부모님께 보낼 수 있는 간단한 감사 카드입니다.', 3, NOW(), NOW(), NULL),
       ('친구 우정 카드', '[개인용 서식]친구에게 보낼 수 있는 간단한 우정 카드입니다.', 3, NOW(), NOW(), NULL),

       ('프리랜서 계약서', '외부직원(프리랜서)과의 업무계약 진행 시 작성하는 문서 양식입니다.', 4, NOW(), NOW(), NULL),
       ('근로계약서 - 정규직 ', '근로계약 만료일이 없는 근로계약서입니다. 월급 / 연봉 / 시급 선택이 가능합니다.', 4, NOW(), NOW(), NULL),
       ('근로계약서 - 계약직 ', '기한 정함이 있는 경우(계약직)의 근로 계약서 양식입니다. 월급 / 연봉 / 시급 선택이 가능합니다.', 4, NOW(), NOW(), NULL),
       ('근로계약서 - 시급제 ', '파트타임(아르바이트)에 따른 시급제 근로 계약서 양식입니다.', 4, NOW(), NOW(), NULL);

INSERT INTO space_wall (blocks, share_url, add_space_id, member_id, space_wall_category, flag, created_at, updated_at,
                        deleted_at)
VALUES ('[
        {"position":1, "block_type":"fileBlock", "block_id":1, "block_UUID": "c4c78f17-e9a8-4937-adfb-5c19c27c1197"},
        {"position":2, "block_type":"listBlock", "block_id":2, "block_UUID": "d4d88e28-f9b9-4948-bcdb-6d29d27e2198"},
        {"position":3, "block_type":"snsBlock", "block_id":1, "block_UUID": "ase99f39-ga0a-5959-dede-7e39f37f3299"},
        {"position":3, "block_type":"snsBlock", "block_id":2, "block_UUID": "v5e99f39-ga0a-5959-dede-7e39f37f3299"},
        {"position":4, "block_type":"fileBlock", "block_id":2, "block_UUID": "h7e99f39-ga0a-5959-dede-7e39f37f3299"}
    ]', 'https://javajober.shop/combined1', 1, 1, 'PERSONAL', 'PENDING', NOW(), NULL, NOW()),

       ('[
        {"position":1, "block_type":"wallInfoBlock", "block_id":4, "block_UUID": "f6f10g40-hb1b-6969-efef-8f49g48g439a"},
        {"position":2, "block_type":"freeBlock", "block_id":1, "block_UUID": "g7g11h51-ic2c-7070-fgfg-9g50h59h549b"},
        {"position":3, "block_type":"templateBlock", "block_id":1, "block_UUID": "h8h12i62-jd3d-8181-ghgh-0h61i60i659c"},
        {"position":3, "block_type":"templateBlock", "block_id":2, "block_UUID": "g6h12i62-jd3d-8181-ghgh-0h61i60i659c"},
        {"position":3, "block_type":"templateBlock", "block_id":3, "block_UUID": "j3h12i62-jd3d-8181-ghgh-0h61i60i659c"},
        {"position":4, "block_type":"freeBlock", "block_id":2, "block_UUID": "j8g11h51-ic2c-7070-fgfg-9g50h59h549b"}
    ]', 'https://javajober.shop/combined2', 2, 1, 'ENTERPRISE', 'SAVED', NOW(), NULL, NULL),

       ('[
        {"position":1, "block_type":"fileBlock", "block_id":7, "block_UUID": "i9i13j73-ke4e-9292-hihi-1i72j71j769d"},
        {"position":2, "block_type":"listBlock", "block_id":8, "block_UUID": "j0j14k84-lf5f-0303-ijij-2j83k82k879e"},
        {"position":3, "block_type":"snsBlock", "block_id":9, "block_UUID": "k1k15l95-mg6g-1414-jkjk-3k94l93l989f"}
    ]', 'https://javajober.shop/combined3', 3, 2, 'CAREER', 'PENDING', NOW(), NULL, NULL),

       ('[
        {"position":1, "block_type":"wallInfoBlock", "block_id":10, "block_UUID": "l2l16m06-ng7h-2525-klkl-4l05m04m099g"},
        {"position":2, "block_type":"freeBlock", "block_id":11, "block_UUID": "m3m17n17-oh8i-3636-lmlm-5m16n15n109h"},
        {"position":3, "block_type":"templateBlock", "block_id":12, "block_UUID": "n4n18o28-pi9j-4747-mnmn-6n27o26o219i"}
    ]', 'https://javajober.shop/combined4', 4, 2, 'PERSONAL', 'SAVED', NOW(), NULL, NULL),

       ('[
        {"position":1, "block_type":"fileBlock", "block_id":13, "block_UUID": "o5o19p39-qj0k-5858-nonp-7o38p37p329j"},
        {"position":2, "block_type":"listBlock", "block_id":14, "block_UUID": "p6p20q40-rk1l-6969-opoq-8p49q48q439k"},
        {"position":3, "block_type":"snsBlock", "block_id":15, "block_UUID": "q7q21r51-sl2m-7070-pqpr-9q50r59r549l"}
    ]', 'https://javajober.shop/combined5', 5, 3, 'BASIC', 'PENDING', NOW(), NULL, NULL);

