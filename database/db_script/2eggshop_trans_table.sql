--order 
DROP TABLE IF EXISTS ES_ORDER;
CREATE TABLE ES_ORDER(
    UNIQUE_CODE UUID PRIMARY KEY,
    USER_UC UUID,
    SUMMARY VARCHAR(255),
    TOTAL_AMT DECIMAL(20, 2),
    RECEIVER_NAME VARCHAR(255),
   	PROVINCE VARCHAR(255),
    CITY VARCHAR(255),
    DISTRICT VARCHAR(255),
    ADDRESS VARCHAR(255),
    CONTACT VARCHAR(255),
    PAYMENT_TYPE VARCHAR(20),
    STATUS VARCHAR(20),
    REMARK VARCHAR(255),
    PAY_STATUS VARCHAR(20),
    PAY_MESSAGE VARCHAR(255),
    PREPAY_ID VARCHAR(64),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);
--order item
DROP TABLE IF EXISTS ES_ORDER_ITEM;
CREATE TABLE ES_ORDER_ITEM(
    ORDER_UC UUID,
    PRODUCT_UC UUID,
    PRODUCT_COUNT DECIMAL(20, 2),
    PRODUCT_PRICE DECIMAL(20, 2),
    SUB_AMT DECIMAL(20, 2),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);

--product
DROP TABLE IF EXISTS ES_PRODUCT;
CREATE TABLE ES_PRODUCT(
    UNIQUE_CODE UUID PRIMARY KEY,
    NAME VARCHAR(255),
    PRICE DECIMAL(20, 2),
    CATEGORY UUID,
    THUMBNAIL UUID,
    BRIEF VARCHAR(255),
    DETAIL_DESCIPTION VARCHAR(5000),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);

--product media
DROP TABLE IF EXISTS ES_PRODUCT_MEDIA;
CREATE TABLE ES_PRODUCT_MEDIA(
    UNIQUE_CODE UUID PRIMARY KEY,
    PRODUCT_UC UUID,
    ORDER_NUM INT,
    THUMBNAIL UUID,
    MEDIA_TYPE VARCHAR(20),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);

--shopping cart
DROP TABLE IF EXISTS ES_SHOPPING_CART;
CREATE TABLE ES_SHOPPING_CART(
    UNIQUE_CODE UUID PRIMARY KEY,
    SELECTED BOOLEAN DEFAULT TRUE,
    USER_UC UUID,
    PRODUCT_UC UUID,
    PRODUCT_COUNT DECIMAL(20, 2),
    REMARK VARCHAR(255),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);

--main swiper
DROP TABLE IF EXISTS ES_MAIN_SWIPER;
CREATE TABLE ES_MAIN_SWIPER(
    UNIQUE_CODE UUID PRIMARY KEY,
    PRODUCT_UC UUID,
    THUMBNAIL UUID,
   	ORDER_NUM INT,
   	VALID_TIME TIMESTAMP,
    REMARK VARCHAR(255),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);

--product category
DROP TABLE IF EXISTS ES_PRODUCT_CATEGORY;
CREATE TABLE ES_PRODUCT_CATEGORY(
    UNIQUE_CODE UUID PRIMARY KEY,
    THUMBNAIL UUID,
   	ORDER_NUM INT,
   	NAME VARCHAR(255),
    REMARK VARCHAR(255),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);

--deliverty address
DROP TABLE IF EXISTS ES_DELIVERY_ADDRESS;
CREATE TABLE ES_DELIVERY_ADDRESS(
    UNIQUE_CODE UUID PRIMARY KEY,
    USER_UC UUID,
    RECEIVER_NAME VARCHAR(255),
   	PROVINCE VARCHAR(255),
    CITY VARCHAR(255),
    DISTRICT VARCHAR(255),
    ADDRESS VARCHAR(255),
    CONTACT VARCHAR(255),
    DEFAULT_FLAG VARCHAR(20),
    CREATOR VARCHAR(255),
    LAST_SELECT_TIME TIMESTAMP DEFAULT SYSDATE,
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);
