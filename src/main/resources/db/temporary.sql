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

insert into template (template_title, template_description,space_wall_category_id, created_at, updated_at,deleted_at)
values
    ('면접확인서','면접자가 회사에 요청하는 면접확인서 양식입니다.',1,NOW(), NOW(), NULL),
    ('인턴 경력증명서','인턴분에게 발급하는 경력증명서입니다. (제출용도 포함)',1,NOW(), NOW(), NULL),
    ('디자이너 등록하기','디자이너로 등록하기 위해 이력서와 포트폴리오 그리고 관심분야를 제출해주세요.',1,NOW(), NOW(), NULL),
    ('채용 지원서 (자버)','채용 지원서를 작성해주세요.',1,NOW(), NOW(), NULL),

    ('재직증명서','역요청 기능으로 근로자가 관리자에게 증명서 발급을 요청할 수 있습니다.(전자문서 환경설정에서 역요청 설정)',2,NOW(), NOW(), NULL),
    ('퇴사자 경력증명서','퇴사한 직원에게 발급하는 경력증명서입니다.',2,NOW(), NOW(), NULL),
    ('개인정보 수집·이용 동의서','근로자의 개인정보 수집 ·이용 동의서 양식입니다.',2,NOW(), NOW(), NULL),
    ('중소기업 취업자 소득세 감면신청서','「조세특례제한법」 제30조제1항 및 같은 법 시행령 제27조제5항에 따라 위와 같이 중소기업 취업자에 대한 소득세 감면을 신청할 수 있는 신청서입니다.(청년용)',2,NOW(), NOW(), NULL),

    ('♥연애계약서♥ - 여자친구Ver','[개인용 서식]사랑하는 연인들이 서로 지킬 약속에 대해 작성하는 문서입니다.',3,NOW(), NOW(), NULL),
    ('하우스메이트 계약서','[개인용 서식]하우스메이트에 관한 규칙을 정하는 계약서입니다.',3,NOW(), NOW(), NULL),
    ('부모님 감사 카드','[개인용 서식]부모님께 보낼 수 있는 간단한 감사 카드입니다.',3,NOW(), NOW(), NULL),
    ('친구 우정 카드','[개인용 서식]친구에게 보낼 수 있는 간단한 우정 카드입니다.',3,NOW(), NOW(), NULL),

    ('프리랜서 계약서','외부직원(프리랜서)과의 업무계약 진행 시 작성하는 문서 양식입니다.',4,NOW(), NOW(), NULL),
    ('근로계약서 - 정규직 ','근로계약 만료일이 없는 근로계약서입니다. 월급 / 연봉 / 시급 선택이 가능합니다.',4,NOW(), NOW(), NULL),
    ('근로계약서 - 계약직 ','기한 정함이 있는 경우(계약직)의 근로 계약서 양식입니다. 월급 / 연봉 / 시급 선택이 가능합니다.',4,NOW(), NOW(), NULL),
    ('근로계약서 - 시급제 ','파트타임(아르바이트)에 따른 시급제 근로 계약서 양식입니다.',4,NOW(), NOW(), NULL);


insert into space_wall_category  (space_wall_category)
values
    ('CAREER'),
    ('PERSONAL'),
    ('EVENT'),
    ('ENTERPRISE'),
    ('BASIC');