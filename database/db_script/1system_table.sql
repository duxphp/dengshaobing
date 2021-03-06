CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--select uuid_generate_v4()


--测试表
DROP TABLE IF EXISTS SYS_TEST;
CREATE TABLE SYS_TEST(
    ID serial PRIMARY KEY,
    NAME VARCHAR,
    AGE smallint,
    CASH DECIMAL(20, 2),
    UNIQUE_CODE UUID,
    BIRTHDATE DATE,
    CREATE_TIME TIMESTAMP DEFAULT NOW()
);

--user
DROP TABLE IF EXISTS SYS_USER;
CREATE TABLE SYS_USER(
    UNIQUE_CODE UUID PRIMARY KEY,
    NAME VARCHAR,
    MOBILE VARCHAR,
    EMAIL VARCHAR,
    TYPE VARCHAR,
    OPENID VARCHAR,
    STATUS VARCHAR,
    REMARK VARCHAR,
    CREATOR VARCHAR,
    CREATE_TIME TIMESTAMP DEFAULT NOW()
);

-- weixin user info
DROP TABLE IF EXISTS SYS_USERINFO_WX;
CREATE TABLE SYS_USERINFO_WX(
    UNIQUE_CODE UUID PRIMARY KEY,
    OPENID VARCHAR,
    NICKNAME VARCHAR,
    SEX VARCHAR,
    CITY VARCHAR,
    COUNTRY VARCHAR,
    PROVINCE VARCHAR,
    LANGUAGE VARCHAR,
    HEADIMGURL VARCHAR,
    UNIONID VARCHAR,
    GROUPID VARCHAR,
    REMARK VARCHAR,
    CREATE_TIME TIMESTAMP DEFAULT NOW()
);

--file store mapping
DROP TABLE IF EXISTS SYS_FILE_STORE_MAPPING;
CREATE TABLE SYS_FILE_STORE_MAPPING(
    UNIQUE_CODE UUID PRIMARY KEY,
    NAME VARCHAR,
    PATH VARCHAR,
    FILE_EXTENSION VARCHAR,
    FILE_SIZE BIGINT,
    REMARK VARCHAR,
    CREATOR VARCHAR,
    CREATE_TIME TIMESTAMP DEFAULT NOW()
);
--日志表：HTTP请求
DROP TABLE IF EXISTS LOG_HTTP;
CREATE TABLE LOG_HTTP(
    ID serial PRIMARY KEY,
    METHOD_NAME VARCHAR,
    URI VARCHAR,
    REQUEST VARCHAR,
    RESPONSE VARCHAR,
    CREATE_TIME TIMESTAMP DEFAULT NOW()
); 

--日志表：用户登录记录
DROP TABLE IF EXISTS LOG_VISIT;
CREATE TABLE LOG_VISIT(
    ID serial PRIMARY KEY,
    NAME VARCHAR,
    REMARK VARCHAR,
    CREATE_TIME TIMESTAMP DEFAULT NOW()
); 

--日志表：调用http返回错误消息日志
DROP TABLE IF EXISTS LOG_ERRORABLE;
CREATE TABLE LOG_ERRORABLE(
    ID serial PRIMARY KEY,
    URI VARCHAR,
    ERRORABLE VARCHAR,
    CREATE_TIME TIMESTAMP DEFAULT NOW()
); 

--address partition
DROP TABLE IF EXISTS DD_ADDRESS_PARTITION;
CREATE TABLE DD_ADDRESS_PARTITION(
    ID serial PRIMARY KEY,
    ADD_TYPE VARCHAR,
    PROVINCE_CODE VARCHAR,
    CITY_CODE VARCHAR,
    COUNTRY_CODE VARCHAR,
    TOWN_CODE VARCHAR,
    CODE VARCHAR,
    DESCRIPTION VARCHAR,
    CREATOR VARCHAR,
    CREATE_TIME TIMESTAMP DEFAULT NOW()
);
