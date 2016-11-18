--order 
DROP TABLE IF EXISTS ES_ORDER;
CREATE TABLE ES_ORDER(
    UNIQUE_CODE UUID PRIMARY KEY,
    USER_UC UUID,
    SUMMARY VARCHAR(255),
    TOTAL_AMT DECIMAL(20, 2),
    STATUS VARCHAR(20),
    REMARK VARCHAR(255),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);
--order item
DROP TABLE IF EXISTS ES_ORDER_ITEM;
CREATE TABLE ES_ORDER_ITEM(
    UNIQUE_CODE UUID PRIMARY KEY,
    ORDER_UC UUID,
    PRODUCT_UC UUID,
    PRODUCT_COUNT DECIMAL(20, 2),
    TOTAL_AMT DECIMAL(20, 2),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);

--product
DROP TABLE IF EXISTS ES_PRODUCT;
CREATE TABLE ES_PRODUCT(
    UNIQUE_CODE UUID PRIMARY KEY,
    NAME VARCHAR(255),
    PRICE DECIMAL(20, 2),
    TYPE VARCHAR(20),
    THUMBNAIL UUID,
    BRIEF VARCHAR(255),
    DETAIL_DESCIPTION VARCHAR(5000),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);

--shopping cart
DROP TABLE IF EXISTS ES_SHOPPING_CART;
CREATE TABLE ES_SHOPPING_CART(
    UNIQUE_CODE UUID PRIMARY KEY,
    USER_UC UUID,
    PRODUCT_UC UUID,
    PRODUCT_COUNT DECIMAL(20, 2),
    REMARK VARCHAR(255),
    CREATOR VARCHAR(255),
    CREATE_TIME TIMESTAMP DEFAULT SYSDATE
);
