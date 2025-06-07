
-- 회사 데이터 예시
INSERT INTO company (name, business_number, domain_email, contact_email, contact_phone)
VALUES
    ('에이치주식회사', '123-45-67890', '@hcompany.com', 'contact@hcompany.com', '02-1234-5678'),
    ('비즈코리아', '456-78-90123', '@bizkorea.co.kr', 'info@bizkorea.co.kr', '02-9876-5432');

-- 사용자 데이터 예시
-- 개인 사용자
INSERT INTO "user" (email, name, user_type)
VALUES
    ('user1@gmail.com', '김철수', 'PERSONAL'),
    ('user2@naver.com', '이영희', 'PERSONAL');

-- 기업 사용자 (사내 이메일, 기업 소속)
INSERT INTO "user" (email, password, name, user_type, company_id)
VALUES
    ('john@hcompany.com', 'hashed_pw1', 'John Kim', 'CORPORATE', 1),
    ('lee@bizkorea.co.kr', 'hashed_pw2', 'Lee Hana', 'CORPORATE', 2);

-- 관리자 계정
INSERT INTO admin (email, password, name)
VALUES
    ('admin@travelbiz.com', 'admin_pw', '최관리');

-- 항공편 예시
INSERT INTO flight (name, origin, destination, departure_time, arrival_time, price)
VALUES
    ('대한항공 KE123', 'ICN', 'NRT', '2025-07-01 09:00', '2025-07-01 11:30', 350000),
    ('아시아나 OZ456', 'ICN', 'LAX', '2025-07-02 14:00', '2025-07-02 22:00', 1200000);

-- 호텔 예시
INSERT INTO hotel (name, location, rating, price)
VALUES
    ('서울호텔', '서울 강남구', 4, 150000),
    ('도쿄비즈니스호텔', '일본 도쿄', 3, 120000);

-- 차량 예시
INSERT INTO car (model, price, is_insured)
VALUES
    ('쏘나타', 80000, TRUE),
    ('카니발', 100000, FALSE);