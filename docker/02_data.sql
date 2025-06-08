-- 회사 데이터
INSERT INTO company (name, business_number, domain_email, contact_email, contact_phone)
VALUES
    ('에이컴퍼니', '123-45-67890', '@acompany.com', 'admin@acompany.com', '010-1234-5678'),
    ('비컴퍼니', '234-56-78901', '@bcompany.com', 'contact@bcompany.com', '010-2222-3333'),
    ('씨컴퍼니', '345-67-89012', '@ccompany.com', 'hello@ccompany.com', '010-3456-7890'),
    ('디컴퍼니', '456-78-90123', '@dcompany.com', 'info@dcompany.com', '010-4567-8901'),
    ('이컴퍼니', '567-89-01234', '@ecompany.com', 'mail@ecompany.com', '010-5678-9012');

-- 사용자 데이터 (개인/기업 혼합)
INSERT INTO "user" (
    email, password, name, user_type, company_id, phone, birth_date,
    passport_name, nationality, marketing_agree, created_at, is_deleted
) VALUES
      ('alice@example.com', '$2a$10$7C1xFgG3zLqO4MRPGYCCxehNHSTIta1xe4UPtxkuSV.6KQ2p9DRo6', 'Alice Kim',  'PERSONAL',  NULL, '010-1234-5678', '1990-05-10', 'Alice KIM',  'KOR', true,  NOW(), false),
      ('bob@example.com',   '$2a$10$7C1xFgG3zLqO4MRPGYCCxehNHSTIta1xe4UPtxkuSV.6KQ2p9DRo6', 'Bob Lee',    'PERSONAL',  NULL, '010-9876-5432', '1988-02-28', 'Bob LEE',   'KOR', false, NOW(), false),
      ('carol@corp.com',    '$2a$10$7C1xFgG3zLqO4MRPGYCCxehNHSTIta1xe4UPtxkuSV.6KQ2p9DRo6', 'Carol Park', 'CORPORATE', 1,    '010-1111-2222', '1985-12-01', 'Carol PARK','KOR', true,  NOW(), false),
      ('david@corp.com',    '$2a$10$7C1xFgG3zLqO4MRPGYCCxehNHSTIta1xe4UPtxkuSV.6KQ2p9DRo6', 'David Choi', 'CORPORATE', 1,    '010-3333-4444', '1982-07-15', 'David CHOI','KOR', false, NOW(), false),
      ('eve@example.com',   '$2a$10$7C1xFgG3zLqO4MRPGYCCxehNHSTIta1xe4UPtxkuSV.6KQ2p9DRo6', 'Eve Han',    'PERSONAL',  NULL, '010-5555-6666', '1995-09-21', 'Eve HAN',   'KOR', true,  NOW(), false);

-- 관리자
INSERT INTO admin (email, password, name)
VALUES
    ('admin1@travel.com', 'hashed_admin_pw1', '관리자1'),
    ('admin2@travel.com', 'hashed_admin_pw2', '관리자2'),
    ('admin3@travel.com', 'hashed_admin_pw3', '관리자3'),
    ('admin4@travel.com', 'hashed_admin_pw4', '관리자4'),
    ('admin5@travel.com', 'hashed_admin_pw5', '관리자5');

-- 항공편
INSERT INTO flight (name, origin, destination, departure_time, arrival_time, price)
VALUES
    ('KE101', '서울', '도쿄', '2025-07-01 09:00', '2025-07-01 11:30', 250000),
    ('KE102', '서울', '뉴욕', '2025-07-02 12:30', '2025-07-02 22:00', 950000),
    ('KE103', '부산', '오사카', '2025-07-03 07:45', '2025-07-03 09:00', 190000),
    ('KE104', '인천', '런던', '2025-07-04 23:10', '2025-07-05 05:40', 1250000),
    ('KE105', '김포', '제주', '2025-07-05 14:00', '2025-07-05 15:10', 100000);

-- 호텔
INSERT INTO hotel (name, location, rating, price)
VALUES
    ('서울호텔', '서울', 5, 120000),
    ('제주비치호텔', '제주', 4, 90000),
    ('센트럴호텔', '부산', 4, 100000),
    ('마운틴리조트', '강원도', 3, 85000),
    ('시티뷰호텔', '인천', 4, 95000);

-- 차량
INSERT INTO car (model, price, is_insured)
VALUES
    ('쏘나타', 50000, true),
    ('K5', 55000, true),
    ('아반떼', 48000, true),
    ('그랜저', 70000, true),
    ('카니발', 80000, true);