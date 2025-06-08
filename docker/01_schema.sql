-- 기존 테이블 삭제
DROP TABLE IF EXISTS schedule CASCADE;
DROP TABLE IF EXISTS payment CASCADE;
DROP TABLE IF EXISTS reservation_detail CASCADE;
DROP TABLE IF EXISTS reservation CASCADE;
DROP TABLE IF EXISTS car CASCADE;
DROP TABLE IF EXISTS hotel CASCADE;
DROP TABLE IF EXISTS flight CASCADE;
DROP TABLE IF EXISTS admin CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS company CASCADE;

-- 회사 테이블
CREATE TABLE company (
                         company_id      SERIAL PRIMARY KEY,
                         name            VARCHAR(100) NOT NULL UNIQUE,
                         business_number VARCHAR(30),
                         domain_email    VARCHAR(100), -- ex: @company.com
                         contact_email   VARCHAR(100),
                         contact_phone   VARCHAR(20),
                         created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         is_deleted      BOOLEAN DEFAULT FALSE
);
COMMENT ON TABLE company IS '회사(기업 고객) 정보 테이블';
COMMENT ON COLUMN company.company_id IS '회사 고유 ID';
COMMENT ON COLUMN company.domain_email IS '사내 이메일 도메인';

-- 사용자 테이블 (개인 + 기업 통합)
CREATE TABLE "user" (
                        user_id         SERIAL PRIMARY KEY,
                        email           VARCHAR(100) NOT NULL UNIQUE,
                        password        VARCHAR(255),
                        name            VARCHAR(100),
                        user_type       VARCHAR(10) NOT NULL CHECK (user_type IN ('PERSONAL', 'CORPORATE')),
                        company_id      INTEGER,
                        phone           VARCHAR(20),
                        birth_date      DATE,
                        passport_name   VARCHAR(100),
                        nationality     VARCHAR(50),
                        marketing_agree BOOLEAN DEFAULT FALSE,
                        created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        is_deleted      BOOLEAN DEFAULT FALSE,
                        CONSTRAINT fk_user_company FOREIGN KEY (company_id) REFERENCES company(company_id)
);

COMMENT ON TABLE "user" IS '개인/기업 회원 정보를 저장하는 테이블';

COMMENT ON COLUMN "user".user_id         IS '회원 고유 ID';
COMMENT ON COLUMN "user".email           IS '이메일 (로그인 ID)';
COMMENT ON COLUMN "user".password        IS '비밀번호 (암호화 저장)';
COMMENT ON COLUMN "user".name            IS '이름';
COMMENT ON COLUMN "user".user_type       IS '회원 구분 (PERSONAL, CORPORATE)';
COMMENT ON COLUMN "user".company_id      IS '기업회원일 경우 회사 ID (외래키)';
COMMENT ON COLUMN "user".phone           IS '연락처';
COMMENT ON COLUMN "user".birth_date      IS '생년월일';
COMMENT ON COLUMN "user".passport_name   IS '여권상 이름';
COMMENT ON COLUMN "user".nationality     IS '국적';
COMMENT ON COLUMN "user".marketing_agree IS '마케팅 수신 동의 여부';
COMMENT ON COLUMN "user".created_at      IS '계정 생성일시';
COMMENT ON COLUMN "user".is_deleted      IS '삭제 여부 (Soft delete)';

-- 관리자 테이블
CREATE TABLE admin (
                       admin_id    SERIAL PRIMARY KEY,
                       email       VARCHAR(100) NOT NULL UNIQUE,
                       password    VARCHAR(255) NOT NULL,
                       name        VARCHAR(100),
                       created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON TABLE admin IS '여행사 관리자 계정';

-- 항공편
CREATE TABLE flight (
                        flight_id       SERIAL PRIMARY KEY,
                        name            VARCHAR(100),
                        origin          VARCHAR(100),
                        destination     VARCHAR(100),
                        departure_time  TIMESTAMP,
                        arrival_time    TIMESTAMP,
                        price           NUMERIC(10,2),
                        created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 호텔
CREATE TABLE hotel (
                       hotel_id    SERIAL PRIMARY KEY,
                       name        VARCHAR(100),
                       location    VARCHAR(100),
                       rating      INTEGER,
                       price       NUMERIC(10,2),
                       created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 차량
CREATE TABLE car (
                     car_id      SERIAL PRIMARY KEY,
                     model       VARCHAR(100),
                     price       NUMERIC(10,2),
                     is_insured  BOOLEAN,
                     created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 예약
CREATE TABLE reservation (
                             reservation_id    SERIAL PRIMARY KEY,
                             user_id           INT REFERENCES "user"(user_id),
                             company_id        INT REFERENCES company(company_id),
                             reservation_type  VARCHAR(20) CHECK (reservation_type IN ('FLIGHT', 'HOTEL', 'CAR')),
                             status            VARCHAR(20) DEFAULT 'PENDING',
                             created_at        TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
COMMENT ON COLUMN reservation.company_id IS '예약 소속 회사 ID (조회용)';

-- 예약 상세
CREATE TABLE reservation_detail (
                                    detail_id       SERIAL PRIMARY KEY,
                                    reservation_id  INT REFERENCES reservation(reservation_id) ON DELETE CASCADE,
                                    target_id       INT, -- flight_id, hotel_id, car_id
                                    check_in        TIMESTAMP,
                                    check_out       TIMESTAMP
);

-- 결제
CREATE TABLE payment (
                         payment_id      SERIAL PRIMARY KEY,
                         reservation_id  INT REFERENCES reservation(reservation_id) ON DELETE CASCADE,
                         amount          NUMERIC(10,2),
                         payment_method  VARCHAR(20), -- e.g., CARD, TOSS
                         status          VARCHAR(20),
                         paid_at         TIMESTAMP
);

-- 일정
CREATE TABLE schedule (
                          schedule_id     SERIAL PRIMARY KEY,
                          user_id         INT REFERENCES "user"(user_id),
                          title           VARCHAR(100),
                          start_time      TIMESTAMP,
                          end_time        TIMESTAMP,
                          created_at      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);