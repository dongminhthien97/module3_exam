CREATE DATABASE IF NOT EXISTS CodeGym_E;
USE CodeGym_E;


CREATE TABLE Product (
    ID INT PRIMARY KEY,
    Name VARCHAR(50),
    Price DOUBLE,
    Discount DECIMAL,
    Stock INT
);

CREATE TABLE Staff (
	ID INT PRIMARY KEY,
    Name VARCHAR(50),
    DateofBirth Date,
    Address VARCHAR(50)
);

CREATE TABLE Customer (
    ID INT PRIMARY KEY,
    Name VARCHAR(25),
    DateofBirth Date,
    PhoneNumber INT,
    Address VARCHAR(50),
    Email VARCHAR(50)
);


CREATE TABLE OrderDetail (
    oID INT PRIMARY KEY,
    cID INT,
    sID INT,
    oPayBy VARCHAR(50),
    oDateOrder DATE,
    oDateDelivery DATE,
    oAddressDelivery VARCHAR(50),
    FOREIGN KEY (cID) REFERENCES Customer(ID),
    FOREIGN KEY (sID) REFERENCES Staff(ID)
);

INSERT INTO Product (ID, Name, Price, Discount, Stock) VALUES 
('1','Rau Muống','50000','5','10'),
('2','Cải Ngọt','40000','10','20'),
('3','Cà Rốt','50000','20','15'),
('4','Khoai Tây','70000','15','20'),
('5','Táo Mỹ','80000','10','20'),
('6','Cam Sành','90000','14','20'),
('7','Hoa Hồng','10000','20','20'),
('8','Hoa Ly','20000','10','10'),
('9','Củ Cải','30000','15','10'),
('10','Rau Cần','40000','12','22');

INSERT INTO Staff (ID, Name, DateofBirth,Address) VALUES
('1','Minh Thiện','1997-10-03','Đà Nẵng'),
('2','Minh Thành','1998-02-03','Kon Tum'),
('3','Ngọc Châu','2000-11-03','Đà Nẵng'),
('4','Tuấn Ngọc','1993-10-10','Hà Nội'),
('5','Văn Chánh','1960-10-20','Kon Tum'),
('6','Văn Hải','1970-02-03','Quảng Nam'),
('7','Thị Mai','2002-05-05','Đà Nẵng'),
('8','Văn Tùng','1997-07-07','Sài Gòn'),
('9','Văn Thành','1995-04-03','Quảng Ngãi'),
('10','Minh Thông','1997-03-10','Đà Nẵng');

INSERT INTO Customer (ID, Name, DateofBirth,PhoneNumber,Address,Email) VALUES
('1','Thiện','1997-10-03','0303030303','Đà Nẵng','thien@gmail.com'),
('2','Hiền','1997-10-03','0303030302','Kontum','hien@gmail.com'),
('3','Châu','1997-10-03','0303030301','Đà Nẵng','chau@gmail.com'),
('4','Hùng','1997-10-03','0303030305','Đà Nẵng','hung@gmail.com'),
('5','Tùng','1997-10-03','0303030308','Hà Nội','tung@gmail.com'),
('6','Minh','1997-10-03','0303030309','Đà Nẵng','minh@gmail.com'),
('7','Mai','1997-10-03','0303030307','Đà Nẵng','mai@gmail.com'),
('8','Ngọc','1997-10-03','0303030306','Quảng Nam','ngoc@gmail.com'),
('9','Phúc','1997-10-03','0303030304','Đà Nẵng','phuc@gmail.com'),
('10','Tý','1997-10-03','0303030310','Quảng Nam','ty@gmail.com');


INSERT INTO OrderDetail (oID,cID,sID, oPayBy,oDateOrder,oDateDelivery,oAddressDelivery) VALUES
('1','2','1','Card','2025-09-05','2025-09-07','Kontum'),
('2','3','2','Card','2025-09-06','2025-09-07','Đà Nẵng'),
('3','4','3','Card','2025-09-02','2025-09-07','Đà Nẵng'),
('4','5','2','Card','2025-09-03','2025-09-07','Hà Nội'),
('5','6','6','Card','2025-09-01','2025-09-07','Đà Nẵng'),
('6','7','8','Card','2025-09-07','2025-09-08','Đà Nẵng'),
('7','5','2','Card','2025-09-09','2025-09-010','Hà Nội'),
('8','8','1','Card','2025-09-08','2025-09-09','Quảng Nam'),
('9','9','5','Card','2025-09-10','2025-09-11','Đà Nẵng'),
('10','10','4','Card','2025-09-11','2025-09-12','Quảng Nam');

select * from OrderDetail;





