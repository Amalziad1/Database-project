INSERT INTO product( `serial_num`,`product_name`, `price`,  `stock`,   `category`, `supplier_name`,`cost`)VALUES
(1,"Stickers pack1", "13", "100", "random","comp6","9"),(2,"Stickers pack2", "13", "100", "random","comp6","9"),
(3,"One piece stickers", "13", "100", "Anime","comp6","9"),(4,"demon slayer wallpaper", "13", "100", "Anime","comp6","9"),
(5,"wallframe", "13", "100", "random","comp6","9"),(6,"Twice cards", "13", "100", "kpop","comp6","9"),
(7,"HunterXhunter stickers", "13", "100", "Anime","comp6","9"),(8,"BTS pencilcase", "13", "100", "kpop","comp6","9"),
(9,"Stickers", "13", "100", "Anime","comp6","9"),(10,"Stickers", "13", "100", "Anime","comp6","9"),
(11,"Stickers", "13", "100", "Anime","comp6","9"),(12,"Stickers", "13", "100", "Anime","comp6","9"),
(13,"Stickers", "13", "100", "Anime","comp6","9"),(14,"Stickers", "13", "100", "Anime","comp6","9"),
(15,"Stickers", "13", "100", "Anime","comp6","9"), (16,"Stickers", "13", "100", "Anime","comp6","9"),
(17,"Stickers", "13", "100", "Anime","comp6","9"),(18,"Stickers", "13", "100", "Anime","comp6","9"),
(19,"Stickers", "13", "100", "Anime","comp6","9"),(20,"Stickers", "13", "100", "Anime","comp6","9");
-- ("comp1","10","100","pop","BTS Pencil case ",14785236, "7"),




insert into Customers values
(1, 'Zahia Abour', 'Ramallah'), (2, 'Sara Bader', 'Bethlehem'),
(3, 'Amal Ziad', 'Ramallah'), (4, 'Amali Abour', 'Ramallah'),
(5, 'Nada Hamarsheh', 'Ramallah'), (6, 'Aya Murra', 'Ramallah'),
(7, 'Aseel Sabri', 'Ramallah'), (8, 'Insaf Amer', 'Ramallah'),
(9, 'Sondos Abed', 'Ramallah'), (10, 'Jane Doe', 'Ramallah'),
(11, 'Hillary Clinton', 'Ramallah'), (12, 'Kim Kardashian', 'Ramallah'),
(13, 'Malak Raddad', 'Ramallah'), (14, 'Loor Swalhi', 'Ramallah'),
(15, 'Mustafa Abdo', 'Ramallah'), (16, 'Husein Abdo', 'Ramallah');

insert into orders (`orderId`, `productId`, `customerId`, `quantity`, `orderDate`, `orderStatus`) values
(1,1,5,1,"2020-10-9","yes"), (2,1,2,1,"2020-11-9","yes"),(3,1,2,1,"2020-11-19","yes"),
(4,1,1,1,"2021-1-9","yes"),(5,4,10,1,"2021-5-9","no"), (6,3,15,1,"2021-5-9","no"),
 (7,3,15,1,"2022-5-9","no"),(8,4,11,1,"2022-5-9","no"),(9,6,1,1,"2022-5-9","yes"),
 (10,2,2,1,"2022-5-9","yes"),(11,2,3,1,"2022-5-9","yes"),(12,2,3,1,"2022-5-9","no"),
(13,2,4,1,"2022-5-9","no"),(14,2,5,1,"2022-5-9","yes"),(15,1,5,1,"2022-1-9","no"),
(16,1,4,1,"2022-1-9","yes"),(17,1,6,1,"2022-1-9","no"),(18,1,6,1,"2022-1-9","yes"),
(19,1,8,1,"2022-1-9","no"),(20,1,8,1,"2022-1-9","yes"),(21,1,7,1,"2022-1-9","yes"),
(22,1,9,1,"2022-1-9","no"),(23,1,9,1,"2020-1-9","yes"),(24,1,10,1,"2020-1-9","no"),
(25,1,8,1,"2020-1-9","yes"),(26,1,12,1,"2020-1-9","no"),(27,1,14,1,"2020-1-9","yes"),
(28,1,13,1,"2020-1-9","yes"),(29,1,14,1,"2020-1-9","yes"),(30,1,16,1,"2020-1-9","yes"),
(31,1,15,1,"2020-1-9","no"),(32,1,10,1,"2020-1-9","no"),(33,1,5,1,"2020-1-9","yes"),
(34,1,16,1,"2023-1-15","yes"),(35,1,8,1,"2023-1-14","no"),(36,1,4,1,"2023-1-2","no"),
(37,1,2,1,"2023-2-1","yes"),(38,1,2,1,"2020-2-2","no"),(39,1,1,1,"2020-2-6","no"),
(40,1,13,1,"2020-2-4","no") ; 
 
 insert into C_Phone values 
 (1, 0592757187) , (1,2900987), 
 (2,0598510697), (3, 0598632544), (3,2400875),
 (4,0592789654), (5,2901154), (6,2901629), 
(7,059214569),  (7,059224569),  (8,0568963254), 
(9,059914145),  (10,2400365),  (11,2409874), 
(12,05695632),  (13,059123455),  (13,056325444), 
(14,0598563555),  (15,0598563333),  (16,059789655);
insert into Payment (`Pay_ID`, `O_ID`, `Pay_amount`,`Pay_Date`) values 
(1,1,51,"2020-1-9"), (2,2,70,"2020-1-5"), (3,5,50,"2021-10-9"),
(4,4,35,"2021-10-19"),(5,6,60,"2021-11-9"),(6,9,30,"2021-12-1"),
(7,10,60,"2022-1-9"),(8,11,25,"2022-1-11"),(9,12,13,"2022-1-19"),
(10,13,45,"2022-2-1"),(11,15,40,"2022-2-3"),(12,16,5,"2022-2-5"),
(13,17,15,"2022-3-10"),(14,18,36,"2022-3-11"),(15,19,25,"2022-3-5"),
(16,20,37,"2023-4-9"),(17,22,41.5,"2022-5-9"),(18,23,45.5,"2022-6-9"),
(19,24,30.5,"2023-1-9"),(20,26,70,"2023-1-10"),(21,27,25.5,"2022-2-1"),
(22,28,18,"2023-2-2"),(23,29,14,"2023-2-4"),(24,40,100,"2023-2-5")
;






 INSERT INTO supplier(`supplierID`,`supplier_Name`,`mobile_num`,`address`,`email`)
VALUES
(1,"Algazera","0598803656","Jenin","algazera@gmail.com"),
(2,"futuredesigns","0598767676","Bethlehem","futuredesigns2@gmail.com"),
(3,"HannenPrint","0558803616","Hebron","haneenr3@gmail.com"),
(4,"EinKarem","0598114554","Bethlehem","EinKarem@gmail.com"),
(5,"KarazDesigns","0598798476","Ramallah","karazDesigns@outlook.com");
 

SELECT Customers.C_ID, Customers.C_Name, Customers.C_Address, 
  GROUP_CONCAT(Phone SEPARATOR ', ') AS 'phone number(s)'
FROM  C_Phone, Customers
WHERE Customers.C_ID = C_Phone.C_ID AND Customers.C_Address LIKE '%ramallah%' 
GROUP BY Customers.C_ID;
