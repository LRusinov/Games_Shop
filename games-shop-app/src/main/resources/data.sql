INSERT INTO GENRE (NAME)
VALUES ('SURVIVAL');
INSERT INTO GENRE (NAME)
VALUES ('HORROR');
INSERT INTO GENRE (NAME)
VALUES ('LIFE SIMULATION');
INSERT INTO GENRE (NAME)
VALUES ('FIRST-PERSON SHOOTER');
INSERT INTO GENRE (NAME)
VALUES ('ADVENTURE');
INSERT INTO GENRE (NAME)
VALUES ('ACTION');
INSERT INTO GENRE (NAME)
VALUES ('SPORTS');
INSERT INTO GENRE (NAME)
VALUES ('RACING');
INSERT INTO GENRE (NAME)
VALUES ('FIGHTING');
INSERT INTO GENRE (NAME)
VALUES ('OPEN-WORLD');

INSERT INTO PLATFORM (NAME)
VALUES ('PLAYSTATION 5');
INSERT INTO PLATFORM (NAME)
VALUES ('PLAYSTATION 4');
INSERT INTO PLATFORM (NAME)
VALUES ('PLAYSTATION 3');
INSERT INTO PLATFORM (NAME)
VALUES ('PC');
INSERT INTO PLATFORM (NAME)
VALUES ('XBOX 360');
INSERT INTO PLATFORM (NAME)
VALUES ('NINTENDO SWITCH');
INSERT INTO PLATFORM (NAME)
VALUES ('XBOX ONE');

INSERT INTO PUBLISHER (NAME, LOGO_PICTURE_URL, YEAR_OF_CREATION, DESCRIPTION)
VALUES ('Endnight', 'https://www.creativeheads.net/media_nx.ashx?id=5742&size=med', 2011,
        'Endnight Games was initially a 4-person indie studio developing The Forest, later largely expanding the gaming studio and hiring freelancers. This studio is currently based in Vancouver, Canada.');
INSERT INTO PUBLISHER (NAME, LOGO_PICTURE_URL, YEAR_OF_CREATION, DESCRIPTION)
VALUES ('CD Projekt',
        'https://upload.wikimedia.org/wikipedia/en/thumb/6/68/CD_Projekt_logo.svg/330px-CD_Projekt_logo.svg.png', 1994,
        'The company began by translating major video-game releases into Polish, collaborating with Interplay Entertainment for two Baldur''s Gate games. CD Projekt was working on the PC version of Baldur''s Gate: Dark Alliance when Interplay experienced financial difficulties.');
INSERT INTO PUBLISHER (NAME, LOGO_PICTURE_URL, YEAR_OF_CREATION, DESCRIPTION)
VALUES ('Ubisoft',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/7/78/Ubisoft_logo.svg/330px-Ubisoft_logo.svg.png', 1986,
        'Ubi Soft Entertainment is a French video game publisher headquartered in Saint-Mandé with development studios across the world.');
INSERT INTO PUBLISHER (NAME, LOGO_PICTURE_URL, YEAR_OF_CREATION, DESCRIPTION)
VALUES ('EA Sports',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6f/EA_Sports_monochrome_logo.svg/330px-EA_Sports_monochrome_logo.svg.png',
        1991,
        'EA Sports is a division of Electronic Arts that develops and publishes sports video games. Formerly a marketing gimmick of Electronic Arts, in which they tried to imitate real-life sports networks by calling themselves the "EA Sports Network" (EASN) with pictures or endorsements with real commentators such as John Madden.');
INSERT INTO PUBLISHER (NAME, LOGO_PICTURE_URL, YEAR_OF_CREATION, DESCRIPTION)
VALUES ('Warner Bros. Games',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/Warner_Bros._Games.svg/330px-Warner_Bros._Games.svg.png',
        2004,
        'Warner Bros. Games is an American video game publisher based in Burbank, California, and part of the Global Streaming and Interactive Entertainment unit of Warner Bros. Discovery.The publisher was founded as Warner Bros.');
INSERT INTO PUBLISHER (NAME, LOGO_PICTURE_URL, YEAR_OF_CREATION, DESCRIPTION)
VALUES ('Sony Interactive Entertainment',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ef/Sony_Interactive_Entertainment_logo_%282016%29.svg/330px-Sony_Interactive_Entertainment_logo_%282016%29.svg.png',
        1993,
        'Sony Interactive Entertainment is a multinational video game and digital entertainment company owned by multinational conglomerate Sony. SIE primarily operates the PlayStation brand of video game consoles and products.');
INSERT INTO PUBLISHER (NAME, LOGO_PICTURE_URL, YEAR_OF_CREATION, DESCRIPTION)
VALUES ('Xbox Game Studios',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/5/52/Xbox_Game_Studios.svg/330px-Xbox_Game_Studios.svg.png',
        2000,
        'Xbox Game Studios (previously known as Microsoft Studios, Microsoft Game Studios, and Microsoft Games) is an American video game publisher and part of the Microsoft Gaming division based in Redmond, Washington.');
INSERT INTO PUBLISHER (NAME, LOGO_PICTURE_URL, YEAR_OF_CREATION, DESCRIPTION)
VALUES ('Electronic Arts',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/8/81/Electronic_Arts_2020.svg/330px-Electronic_Arts_2020.svg.png',
        1982,
        'Electronic Arts Inc. (EA) is an American video game company headquartered in Redwood City, California. Founded in May 1982 by Apple employee Trip Hawkins, the company was a pioneer of the early home computer game industry and promoted the designers and programmers responsible for its games as "software artists."');
INSERT INTO PUBLISHER (NAME, LOGO_PICTURE_URL, YEAR_OF_CREATION, DESCRIPTION)
VALUES ('Bethesda Softworks',
        'https://upload.wikimedia.org/wikipedia/commons/thumb/d/db/Bethesda_Softworks_Logo.svg/330px-Bethesda_Softworks_Logo.svg.png',
        1986,
        'Bethesda Softworks LLC is an American video game publisher based in Rockville, Maryland. The company was founded by Christopher Weaver in 1986 as a division of Media Technology Limited, and in 1999 became a subsidiary of ZeniMax Media.');

INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Wolfenstein II: The New Colossus', '2017-10-27', 100,
        'The eighth main entry in the Wolfenstein series and the sequel to 2014''s Wolfenstein: The New Order, the game is set in an alternate history which takes place in 1961 following the Nazi victory in the Second World War.',
        9, 'https://upload.wikimedia.org/wikipedia/en/5/54/Wolfenstein-ii-the-new-colossus-cover.jpeg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Halo Infinite', '2021-12-08', 120,
        'The campaign follows the human supersoldier Master Chief and his fight against the enemy Banished on the Forerunner ringworld Zeta Halo, also known as Installation 07.',
        7, 'https://assets-prd.ignimgs.com/2020/07/24/halo-infinite-button-2020-1595617876660.jpg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Wolfenstein: The New Order', '2014-05-20', 70,
        'The story follows war veteran William "B.J." Blazkowicz and his efforts to stop the Nazis from ruling over the world.',
        9, 'https://upload.wikimedia.org/wikipedia/en/9/95/Wolfenstein_The_New_Order_cover.jpg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Need for Speed Heat', '2019-10-08', 80,
        'Need for Speed Heat is a racing game set in an open world environment called Palm City, a fictionalised version of Miami, Florida, and its surrounding areas.',
        8, 'https://upload.wikimedia.org/wikipedia/en/7/7f/Cover_Art_of_Need_for_Speed_Heat.png');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Need for Speed Payback', '2017-10-10', 50,
        'Need for Speed Payback is a racing game set in an open world environment of Fortune Valley; a fictional version of Las Vegas, Nevada. It is focused on "action driving".',
        8, 'https://upload.wikimedia.org/wikipedia/en/6/64/Need_for_Speed_Payback_standard_edition_cover_art.jpg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Forza Horizon 5', '2021-11-09', 130,
        'Forza Horizon 5 is a racing video game set in an open world environment based in a fictional representation of Mexico. The game has the largest map in the entire Forza Horizon series, being 50% larger than its predecessor, Forza Horizon 4',
        7, 'https://assets-prd.ignimgs.com/2021/08/24/forza-horizon-5-button-fin-1629830068379.jpg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Gran Turismo 7', '2022-03-04', 100,
        'Gran Turismo 7 features the return of the single player campaign, GT Simulation Mode. Other returning features are the return of traditional racing tracks and vehicles, Special Events, Championships, Driving School and more.',
        6, 'https://upload.wikimedia.org/wikipedia/en/1/14/Gran_Turismo_7_cover_art.jpg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Mortal Kombat 11', '2019-04-23', 100,
        'Like the previous three games in the series, including Mortal Kombat Mobile, Mortal Kombat 11 is a 2.5D fighting game',
        5, 'https://upload.wikimedia.org/wikipedia/en/7/7e/Mortal_Kombat_11_cover_art.png');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('EA Sports UFC 4', '2020-08-14', 120,
        'Like its predecessor, UFC 4 is a fighting game based on the mixed martial arts promotion Ultimate Fighting Championship (UFC). There are 229 unique fighters, with 81 alternate versions.',
        4, 'https://upload.wikimedia.org/wikipedia/en/3/35/UFC_4_cover_art.png');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('FIFA 23', '2022-09-30', 130,
        'FIFA 23 features a degree of crossplay. Crossplay is available in FIFA Ultimate Team Division Rivals, FUT Champions, FUT Ultimate Online Draft, FUT Online Friendlies, FUT Play a Friend, Online Friendlies and Online Seasons.',
        4, 'https://upload.wikimedia.org/wikipedia/en/a/a6/FIFA_23_Cover.jpg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('FIFA 22', '2022-10-01', 100,
        'FIFA 22 features a degree of crossplay. Crossplay is available in FIFA Ultimate Team Division Rivals, FUT Champions, FUT Ultimate Online Draft, FUT Online Friendlies, FUT Play a Friend, Online Friendlies and Online Seasons.',
        4, 'https://upload.wikimedia.org/wikipedia/en/6/6c/FIFA_22_Cover.jpg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Assassin''s Creed Valhalla', '2020-11-10', 120,
        'Assassin''s Creed Valhalla is an action role-playing video game structured around several main story arcs and numerous optional side-missions, called "World Events".',
        3, 'https://upload.wikimedia.org/wikipedia/en/f/ff/Assassin%27s_Creed_Valhalla_cover.jpg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('The Witcher 3: Wild Hunt', '2015-05-19', 100,
        'The Witcher 3: Wild Hunt is an action role-playing game with a third-person perspective. Players control Geralt of Rivia, a monster slayer known as a Witcher.',
        2, 'https://upload.wikimedia.org/wikipedia/en/0/0c/Witcher_3_cover_art.jpg');

INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('Sons of the Forest', '2023-02-23', 60,
        'Similarly to The Forest, Sons of the Forest puts players in control of a protagonist stranded on an island inhabited by cannibals. Players can build weapons and buildings to aid in their survival.',
        1, 'https://upload.wikimedia.org/wikipedia/en/thumb/3/3d/Sons_of_the_Forest.jpg/330px-Sons_of_the_Forest.jpg');
INSERT INTO GAME (NAME, RELEASE_DATE, PRICE, DESCRIPTION, PUBLISHER_ID, PICTURE_URL)
VALUES ('The Sims 4', '2017-11-17', 100,
        'The Sims 4 is a social simulation game, similar to preceding titles in the series. There is no primary objective or goal to achieve, and instead of fulfilling objectives, the player is encouraged to make choices.',
        8, 'https://upload.wikimedia.org/wikipedia/en/7/7f/Sims4_Rebrand.png');

INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('The Sims 4', 'LIFE SIMULATION');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Wolfenstein II: The New Colossus', 'ACTION');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Wolfenstein II: The New Colossus', 'ADVENTURE');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Wolfenstein II: The New Colossus', 'FIRST-PERSON SHOOTER');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Wolfenstein: The New Order', 'ACTION');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Wolfenstein: The New Order', 'ADVENTURE');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Wolfenstein: The New Order', 'FIRST-PERSON SHOOTER');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Halo Infinite', 'FIRST-PERSON SHOOTER');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Halo Infinite', 'ADVENTURE');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Need for Speed Heat', 'RACING');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Need for Speed Payback', 'RACING');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Forza Horizon 5', 'RACING');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Gran Turismo 7', 'RACING');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Mortal Kombat 11', 'FIGHTING');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('EA Sports UFC 4', 'FIGHTING');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('EA Sports UFC 4', 'SPORTS');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('FIFA 23', 'SPORTS');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('FIFA 22', 'SPORTS');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Assassin''s Creed Valhalla', 'ACTION');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Assassin''s Creed Valhalla', 'ADVENTURE');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('The Witcher 3: Wild Hunt', 'OPEN-WORLD');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('The Witcher 3: Wild Hunt', 'ADVENTURE');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Sons of the Forest', 'SURVIVAL');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Sons of the Forest', 'HORROR');
INSERT INTO GAME_GENRE (GAME_NAME, GENRE_NAME)
VALUES ('Sons of the Forest', 'OPEN-WORLD');

INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('The Sims 4', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Wolfenstein II: The New Colossus', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Wolfenstein II: The New Colossus', 'PLAYSTATION 4');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Wolfenstein II: The New Colossus', 'XBOX ONE');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Wolfenstein: The New Order', 'PLAYSTATION 3');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Wolfenstein: The New Order', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Halo Infinite', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Halo Infinite', 'XBOX ONE');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Halo Infinite', 'XBOX 360');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Need for Speed Heat', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Need for Speed Heat', 'PLAYSTATION 4');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Need for Speed Payback', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Forza Horizon 5', 'PLAYSTATION 5');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Forza Horizon 5', 'PLAYSTATION 4');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Gran Turismo 7', 'PLAYSTATION 5');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Gran Turismo 7', 'PLAYSTATION 4');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Mortal Kombat 11', 'PLAYSTATION 5');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Mortal Kombat 11', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Mortal Kombat 11', 'PLAYSTATION 4');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('EA Sports UFC 4', 'PLAYSTATION 4');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('EA Sports UFC 4', 'PLAYSTATION 5');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('FIFA 23', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('FIFA 23', 'PLAYSTATION 3');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('FIFA 23', 'PLAYSTATION 4');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('FIFA 23', 'PLAYSTATION 5');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('FIFA 22', 'PLAYSTATION 5');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('FIFA 22', 'PLAYSTATION 3');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Assassin''s Creed Valhalla', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Assassin''s Creed Valhalla', 'PLAYSTATION 5');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('The Witcher 3: Wild Hunt', 'PLAYSTATION 5');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('The Witcher 3: Wild Hunt', 'PC');
INSERT INTO GAME_PLATFORM (GAME_NAME, PLATFORM_NAME)
VALUES ('Sons of the Forest', 'PC');

INSERT INTO LOGIN (USERNAME, PASSWORD) values ('user', 'user');

INSERT INTO CLIENT (USERNAME) values ('user');
