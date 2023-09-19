-- 使用者帳戶(帳號,密碼)
-- usercenter(account,pw)
CREATE TABLE `usercenter` (
  `account` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `pw` char(20) NOT NULL,
  primary key (`account`) 
  
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `usercenter` (`account`,`pw`) VALUES
('A6209605','12345678'),
('A6209729','12345678');


-- ------------------------------------------------------------------
-- 使用者資訊(編號,暱稱,身高,體重,理想體重,每日希望攝取熱量,使用者帳號-連使用者帳戶的主建)
-- userdata(UNo, nickname, height, weight, hopeweight, hopekal, account-usercenter.account)

CREATE TABLE `userdata` (
  `UNo` int(10) NOT NULL auto_increment,
  `nickname` varchar(30),
  `height` varchar(10) COLLATE utf8_unicode_ci,
  `weight` int(10),
  `hopeweight` int(10),
  `hopekal` int(5) COLLATE utf8_unicode_ci,
  `account` char(20) COLLATE utf8_unicode_ci NOT NULL,
  primary key (`UNo`)
  
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `userdata` (`UNo`, `nickname`, `height`, `weight`, `hopeweight`, `hopekal`, `account`) VALUES
(001,'66',166,60,55,1200,'A6209605'),
(002,'MuMu',163,55,48,1400,'A6209729');


-- ------------------------------------------------------------------
-- 食物日記(使用者帳號-連使用者帳戶的主建,食物名稱-連食物庫的主建,時間,類型(肉.菜.蛋豆.飲料.點心.湯),份數,每一份量,卡路里,蛋白質,脂肪,碳水化合物,鈉)
-- record(account-usercenter.account, time, name-fooddata.name, type, portion, unit, kal, proteni, fat, carbohydrate, Na)

CREATE TABLE `record` (
  `account` char(20) COLLATE utf8_unicode_ci NOT NULL,
  `time` DATETIME COLLATE utf8_unicode_ci NOT NULL default '0000-00-00 00:00:00',
  `name` varchar(55) COLLATE utf8_unicode_ci NOT NULL default '--',    
  `type` varchar(55) COLLATE utf8_unicode_ci,
  `portion` double(6,1),
  `unit` varchar(15) COLLATE utf8_unicode_ci,
  `kal` int(6),
  `proteni` double(6,1),
  `fat` double(6,1),
  `carbohydrate` double(6,1),
  `Na` double(6,1),  
  primary key (`account`,`name`,`time`) 
  
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `record` (`account`, `time`, `name`, `type`, `portion`, `unit`, `kal`, `proteni`, `fat`, `carbohydrate`, `Na`) VALUES
('A6209605', '2020-03-25 12:10:00', 'steak',				 'MeatsAndProtein',  1 , '7 ounce',	 382, 31.8, 12.9,	 9.9, 841.4),
('A6209605', '2020-03-25 12:12:00', 'Uni-President_Sugar-FreeHigh-FiberSoyMilk',	 'Drink',			 1 , '450 ml',	 183, 15.3, 8.6,	 15.8, 86.0),
('A6209605', '2020-09-13 15:59:20', 'OysterOmelet',		  	 'Grains',			 1 , '100 g',	 193, 	 4,	 11,	 19,	479),

('A6209729', '2020-03-25 12:10:00', 'steak',				 'MeatsAndProtein',	 7 , '1 ounce',	 382, 31.8, 12.9,	 9.9,	 841.4),
('A6209729', '2020-09-13 15:44:03',	'IntestineVermicelli',	 'Grains',			 1 , '100 g',	 305,	11,	 1,		62,		2813),
('A6209729', '2020-09-13 15:59:10',	'ChickenFillet',		 'MeatsAndProtein',	 1 , '241 g',	 325,	23,	19,		12,		 572);

-- ------------------------------------------------------------------
-- 食物庫(食物名稱,類型(澱粉.蛋豆魚肉.甜點.飲品.奶.蔬果),份數,每一份量,卡路里,蛋白質,脂肪,碳水化合物,鈉)
-- fooddata(name,type,portion,unit,kal,proteni,fat,carbohydrate,Na)

CREATE TABLE `fooddata` (
  `name` varchar(55) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(55) COLLATE utf8_unicode_ci,
  `portion` double(6,1),
  `unit` varchar(15) COLLATE utf8_unicode_ci,
  `kal` int(6),
  `proteni` double(6,1),
  `fat` double(6,1),
  `carbohydrate` double(6,1),
  `Na` double(6,1),
  primary key (`name`) 
  
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `fooddata` (`name`, `type`, `portion`, `unit`, `kal`, `proteni`, `fat`, `carbohydrate`, `Na`) VALUES
('--', '--', 0 , '0', 0, 0, 0, 0, 0),

('steak', 'MeatsAndProtein', 1 , '7 ounce', 382, 31.8, 12.9, 9.9, 841.4),
('egg', 'MeatsAndProtein', 1 , '100 g', 155, 13, 11, 1.1, 124),
('SpicedCornedEgg', 'MeatsAndProtein', 1 , '100 g', 162, 15, 9.7, 3.8, 608),
('QuailEgg', 'MeatsAndProtein', 1 , '100 g', 160, 12.8, 11.1, 2.1, 0),
('Omelet', 'MeatsAndProtein', 1 , '100 g', 167, 11.1, 12.2, 2.2, 280),
('FishBalls', 'MeatsAndProtein', 1 , '100 g', 88, 5.6, 4.7, 5.6, 0),
('OverEgg', 'MeatsAndProtein', 1 , '100 g', 199, 13.5, 15, 1.4, 353),
('EggFooYoung', 'MeatsAndProtein', 1 , '62 g', 175, 1.7, 8.3, 23.3, 0),
('TeaEgg', 'MeatsAndProtein', 1 , '100 g', 132, 12.7, 8.3, 1.9, 293),
('HardBoiledEgg', 'MeatsAndProtein', 1 , '1 piece', 80, 8, 5.5, 0.6, 62),
('SoftBoiledEgg', 'MeatsAndProtein', 1 , '1 piece', 65, 5.2, 2.8, 0.8, 200),
('SteamedEggWithClams', 'MeatsAndProtein', 1 , '100 g', 344, 6.6, 1, 74.5, 52),
('SteamedStinkyTofu', 'MeatsAndProtein', 1 , '100 g', 82, 2.3, 0, 17.6, 0),
('SpicyStinkyTofu', 'MeatsAndProtein', 1 , '300 g', 353, 12.6, 6.9, 60, 0),
('UnbakedStinkyTofu', 'MeatsAndProtein', 1 , '100 g', 229, 8.7, 18.6, 7.6, 1749.2),
('Family_IntestinesAndStinkyTofu', 'MeatsAndProtein', 1 , '1 serving', 380, 13.7, 17.1, 42.7, 2258),
('SpicyDuckBloodStinkyTofu', 'MeatsAndProtein', 1 , '100 g', 91, 3.8, 7.1, 3, 391.3),
('BeanCurd', 'MeatsAndProtein', 1 , '100 g', 100, 4.3, 1.6, 16.3, 17),
('FriedBeanCurd', 'MeatsAndProtein', 1 , '100 g', 466, 51.7, 25.1, 11.2, 23),
('TofuSkin', 'MeatsAndProtein', 1 , '100 g', 258, 27, 12.8, 7.3, 0),
('CucumberHamWithBeanCurd', 'MeatsAndProtein', 1 , '100 g', 202, 8.8, 15.7, 7.3, 1320.8),
('ColdBeanCurd', 'MeatsAndProtein', 1 , '100 g', 221, 4.3, 13.8, 20, 108),
('MarinatedBeanCurd', 'MeatsAndProtein', 1 , '100 g', 224, 24.2, 4.1, 19.5, 791),

('Uni-President_Sugar-FreeHigh-FiberSoyMilk', 'Drink', 1 , '450 ml', 183, 15.3, 8.6, 15.8, 86.0),

('EggTart', 'Dessert', 1 , '100 g', 150, 12, 11.3, 0.6, 3),

-- 										份數,每一份量,	卡路里,蛋白質,	脂肪,碳水化合物,鈉
('StinkyTofu',		 		   			 'MeatsAndProtein',		 1 , '100 g',		 222,	 7.8,	20.8,	 2.9,	254),
('Pan_friedBun',						 'MeatsAndProtein',		 1 , '100 g',		 396,	10,		21,		35,		0),
('GrilledBirdEggs',						 'MeatsAndProtein',		 1 , '50 g',		 175,	11,		 9,		10,		73),
('TaiwaneseMeatball',	 				 'MeatsAndProtein',		 1 , '220 g',		 332,	 5,		 8,		58,		0),
('ChickenFillet',			 			 'MeatsAndProtein',		 1 , '241 g',		 325,	23,		19,		12,		572),

('OysterOmelet',			 			 'Grains',				 1 , '100 g',		 193,	 4,		11,		19,		479),
('SlicedPorkBun',			 			 'Grains',				 1 , '100 g',		 243,	 9,		 1,		48,		22),
('PigBloodCake',			 			 'Grains',				 1 , '100 g',		 192,	 8,		 0,		37,		414),
('Crepe',			 		 			 'Grains',				 1 , '100 g',		 506,	 5,		24,		66,		219),
('AnimalShapedEggPancakes',	 			 'Grains',				 1 , '30 g',		 100,	 2,		 3,		15,		35),
('YilanScallionPancake',	 			 'Grains',				 1 , '240 g',		 494,	18,		11,		79,		938),
-- ('Takoyaki-Original',		 			 'Grains',				 1 , '1 piece',		 45,	 0.3,	 4,		 1.7,	0),
-- ('Takoyaki-MinoShigeru',	 			 'Grains',				 1 , '1 piece',		 60,	 1.3,	 4,		 5.1,	0),
('Takoyaki',	 			 			 'Grains',				 1 , '1 piece',		 60,	 1.3,	 4,		 5.1,	0),
('PotatoBalls',			 				 'Grains',				 1 , '1 piece',		 41,	 0,		 1,		 6,		0),
('PepperBun',			 				 'Grains',				 1 , '1 serving',	 371,	22,		14,		37,		0),
('ScallionPie',			 				 'Grains',				 1 , '1 serving',	 371,	 8,		17,		45,		0),
('TaiwaneseSausageWithStickyRice',		 'Grains',				 1 , '1 serving',	 431,	20,		17,		48,		810),
('WheelPies',			 				 'Grains',				 1 , '1 unit',		 277,	 3,		11,		20,		0),
('IntestineVermicelli',					 'Grains',				 1 , '100 g',		 305,	 11,	 1,		62,		2813),

('LicoriceGuava',		 				 'FruitsAndVegetables',	 1 , '50 g',		 167,	 2,		 0,		39,		73),
('GrilledCorn',						 	 'FruitsAndVegetables',	 1 , '100 g',		 140,	16,		 7,		 0,		0),
('SugarcoatedHawsOnAStick',				 'FruitsAndVegetables',	 1 , '1 piece',		 18,	 0,		 0,		 4,		0);

-- ------------------------------------------------------------------------
drop trigger if exists `usercenter_trg`;
delimiter |
create trigger `usercenter_trg` 
after insert on `usercenter`
for each row
begin
	INSERT INTO `userdata` (`account`) VALUES (new.`account`);
    INSERT INTO `record` (`account`) VALUES (new.`account`);
end |
delimiter ;

drop trigger if exists `fooddata_trg`;
delimiter |
create trigger `fooddata_trg` 
after insert on `fooddata`
for each row
begin
	INSERT INTO `record` (`name`) VALUES (new.`name`);
end |
delimiter ;

-- ------------------------------------------------------------------------
alter table `userdata`
add constraint foreign key (`account`) references `usercenter`(`account`) on update cascade on delete cascade;
alter table `record`
add constraint foreign key (`name`) references `fooddata`(`name`) on update cascade on delete cascade;  
alter table `record`
add constraint foreign key (`account`) references `usercenter`(`account`) on update cascade on delete cascade;