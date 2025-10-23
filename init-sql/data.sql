-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: suspect
-- ------------------------------------------------------
-- Server version	8.0.43
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;

/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;

/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;

/*!50503 SET NAMES utf8mb4 */;

/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;

/*!40103 SET TIME_ZONE='+00:00' */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categorias`
--
DROP TABLE IF EXISTS `categorias`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE
    `categorias` (
        `id_categoria` bigint NOT NULL AUTO_INCREMENT,
        `imagenurl` varchar(255) NOT NULL,
        `titulo` varchar(255) NOT NULL,
        PRIMARY KEY (`id_categoria`)
    ) ENGINE = InnoDB AUTO_INCREMENT = 10 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorias`
--
LOCK TABLES `categorias` WRITE;

/*!40000 ALTER TABLE `categorias` DISABLE KEYS */;

INSERT INTO
    `categorias`
VALUES
    (
        1,
        'https://infonegocios.info/content/images/2024/02/20/440026/conversions/equipos-futbol-inversion-europa-medium-size.jpg',
        'Equipos de Fútbol'
    ),
    (
        2,
        'https://www.autopista.es/uploads/s1/57/58/02/1/article-significado-logotipos-nombres-marcas-coches-primera-parte-audi-bmw-ferrrari-5980441e9095f.jpeg',
        'Marcas de Coches'
    ),
    (
        3,
        'https://elagentecine.cl/wp-content/uploads/2025/03/40-personajes-de-Disney.jpg',
        'Personajes de Disney'
    ),
    (
        4,
        'https://e1.pxfuel.com/desktop-wallpaper/330/3/desktop-wallpaper-europe-map-flags-europe-map.jpg',
        'Banderas del Mundo'
    ),
    (
        6,
        'https://www.sitecentre.com.au/uploads/2021/07/famous-logo-designers-what-they-got-right-1000x1000.jpg',
        'Adivina el Logo'
    ),
    (
        9,
        'https://editorial.uefa.com/resources/0285-18f0344d0211-ad1d1162342f-1000/fbl-euro-2024-qualifer-svk-por.jpeg',
        'Futbolistas'
    );

/*!40000 ALTER TABLE `categorias` ENABLE KEYS */;

UNLOCK TABLES;

--
-- Table structure for table `preguntas`
--
DROP TABLE IF EXISTS `preguntas`;

/*!40101 SET @saved_cs_client     = @@character_set_client */;

/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE
    `preguntas` (
        `id_categoria` bigint NOT NULL,
        `id_pregunta` bigint NOT NULL AUTO_INCREMENT,
        `imagenurl` varchar(255) NOT NULL,
        `opcion_a` varchar(255) DEFAULT NULL,
        `opcion_b` varchar(255) DEFAULT NULL,
        `opcion_c` varchar(255) DEFAULT NULL,
        `respuesta_correcta` varchar(255) NOT NULL,
        `tipo_pregunta` enum ('ESCRIBIR', 'OPCIONES', 'VERDADERO_FALSO', 'VF') NOT NULL,
        `pregunta` varchar(255) NOT NULL,
        `dificultad` enum ('DIFICIL', 'FACIL', 'MEDIO') NOT NULL,
        `sonidourl` varchar(255) DEFAULT NULL,
        `hay_sonido` bit (1) DEFAULT NULL,
        PRIMARY KEY (`id_pregunta`),
        KEY `FKpdyyq7alsnjeernf18d12hyr7` (`id_categoria`),
        CONSTRAINT `FKpdyyq7alsnjeernf18d12hyr7` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id_categoria`),
        CONSTRAINT `preguntas_chk_1` CHECK (
            (
                `tipo_pregunta` in (
                    _utf8mb4 'ESCRIBIR',
                    _utf8mb4 'OPCIONES',
                    _utf8mb4 'VF'
                )
            )
        )
    ) ENGINE = InnoDB AUTO_INCREMENT = 45 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preguntas`
--
LOCK TABLES `preguntas` WRITE;

/*!40000 ALTER TABLE `preguntas` DISABLE KEYS */;

INSERT INTO
    `preguntas`
VALUES
    (
        1,
        1,
        'https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/893px-Real_Madrid_CF.svg.png',
        'Barcelona',
        'Atlético de Madrid',
        'Sevilla',
        'Real Madrid',
        'OPCIONES',
        '¿Qué equipo es el de la imagen?',
        'FACIL',
        NULL,
        NULL
    ),
    (
        4,
        3,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Flag_of_Nepal.svg/984px-Flag_of_Nepal.svg.png',
        '',
        '',
        '',
        'Nepal',
        'ESCRIBIR',
        'Escribe el país al que pertenece la bandera:',
        'DIFICIL',
        NULL,
        NULL
    ),
    (
        2,
        4,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/9/90/Mercedes-Logo.svg/1200px-Mercedes-Logo.svg.png',
        '',
        '',
        '',
        'Mercedes',
        'ESCRIBIR',
        'El logo de la imagen pertenece a...',
        'FACIL',
        NULL,
        NULL
    ),
    (
        4,
        6,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e8/Flag_of_Ecuador.svg/1280px-Flag_of_Ecuador.svg.png',
        'Ecuador',
        'Colombia',
        'Venezuela',
        'Ecuador',
        'OPCIONES',
        '¿De qué país es esta bandera?',
        'FACIL',
        NULL,
        NULL
    ),
    (
        4,
        7,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/3/3e/Flag_of_New_Zealand.svg/2560px-Flag_of_New_Zealand.svg.png',
        'Australia',
        'Nueva Zelanda',
        'Reino Unido',
        'Nueva Zelanda',
        'OPCIONES',
        '¿De qué país es la bandera?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        4,
        8,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/d/dc/Flag_of_Puerto_Rico_%281952%E2%80%931995%29.svg/250px-Flag_of_Puerto_Rico_%281952%E2%80%931995%29.svg.png',
        '',
        '',
        '',
        'Cuba',
        'ESCRIBIR',
        'Existe una bandera muy parecida a la de Puerto Rico, ¿Sabrías escribir cuál?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        6,
        9,
        'https://images.icon-icons.com/3685/PNG/512/spotify_logo_icon_229290.png',
        '',
        '',
        '',
        'Spotify',
        'ESCRIBIR',
        'Escribe a que plataforma pertenece el logo',
        'FACIL',
        NULL,
        NULL
    ),
    (
        6,
        10,
        'https://images.icon-icons.com/2699/PNG/512/nvidia_logo_icon_169902.png',
        'OpenAI',
        'Meta',
        'Nvidia',
        'Nvidia',
        'OPCIONES',
        'A qué empresa pertenece el logo',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        6,
        11,
        'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsrr7555j3ab0ZZ7UNDQxvVMAmaTXPlKC2Ow&s',
        'Foster Hollywood',
        'Carls Jr',
        'Subway',
        'Carls Jr',
        'OPCIONES',
        'A qué cadena de comida rápida pertenece este logo',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        6,
        12,
        'https://1000logos.net/wp-content/uploads/2018/08/huawei-technologies-logo.jpg',
        '',
        '',
        '',
        'Huawei',
        'ESCRIBIR',
        'Escribe el nombre del fabricante de móviles de China que tiene este logo: ',
        'DIFICIL',
        NULL,
        NULL
    ),
    (
        6,
        13,
        'https://www.lavanguardia.com/files/og_thumbnail/uploads/2020/09/15/5faa699a61c6d.jpeg',
        'Patagonia',
        'Paramount Pictures',
        'Toblerone',
        'Toblerone',
        'OPCIONES',
        'La montaña de la imagen aparece representada en un logo muy icónico, ¿sabrías en cual?',
        'DIFICIL',
        NULL,
        NULL
    ),
    (
        6,
        14,
        'https://st3.depositphotos.com/31206640/36834/v/450/depositphotos_368340424-stock-illustration-single-black-pear-symbol-white.jpg',
        '',
        '',
        '',
        'Apple',
        'ESCRIBIR',
        'La marca famosa de una fruta mordida se llama...',
        'FACIL',
        NULL,
        NULL
    ),
    (
        2,
        15,
        'https://jesantcarstore.com/wp-content/uploads/2025/09/Kuga_9865LZC__2_-removebg-preview.png',
        '',
        '',
        '',
        'Ford',
        'ESCRIBIR',
        'Un logo con un óvalo azul y letras blancas corresponde a…',
        'FACIL',
        NULL,
        NULL
    ),
    (
        2,
        16,
        'https://upload.wikimedia.org/wikipedia/commons/5/5a/Mitsubishi_logo.svg',
        'Sukuki',
        'Toyota',
        'Mitsubishi',
        'Mitsubishi',
        'OPCIONES',
        '¿Qué fabricante japonés es el del logo?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        2,
        17,
        'https://i.etsystatic.com/32129826/r/il/493ebf/4477218278/il_fullxfull.4477218278_8a9m.jpg',
        '',
        '',
        '',
        'Aventador',
        'ESCRIBIR',
        'El coche de la silueta pertenece a un modelo de Lamborghini, en concreto a...',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        2,
        18,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Volkswagen_logo_2019.svg/330px-Volkswagen_logo_2019.svg.png',
        'Audi',
        'Lamborghini',
        'Mercedes',
        'Mercedes',
        'OPCIONES',
        'Una de las siguientes marcas no pertenece al grupo Volkswagen',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        2,
        19,
        'https://justitalia.es/wp-content/uploads/2020/09/419149_Italia_thumb_708.jpg',
        'Porsche',
        'Fiat',
        'Ducati',
        'Fiat',
        'OPCIONES',
        'Es una marca de coches italiana',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        2,
        20,
        'https://static.motor.es/fotos-jato/toyota/uploads/toyota-rav4-6696eb232f69d.jpg',
        '',
        '',
        '',
        'Lexus',
        'ESCRIBIR',
        'Toyota tiene una marca de lujo llamada... ',
        'DIFICIL',
        NULL,
        NULL
    ),
    (
        2,
        21,
        'https://www.bmw.com.mx/content/dam/bmw/common/all-models/m-series/series-overview/bmw-m-series-seo-overview-ms-04.jpg',
        'Manejemos juntos',
        'Puro placer de conducir',
        'Simplemente se siente bien',
        'Puro placer de conducir',
        'OPCIONES',
        '¿Cuál es el eslogan de BMW?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        2,
        22,
        'https://images-porsche.imgix.net/-/media/0683EEF17ADA4D6ABAA276C65235E96C_29BC6C3357784A859B8A0E4B36EE15F8_CZ25W14IX0010-911-carrera-4-gts-side?w=2560&h=697&q=45&crop=faces%2Centropy%2Cedges&auto=format',
        'Ferrari',
        'Chevrolet',
        'Porsche',
        'Porsche',
        'OPCIONES',
        '¿Qué fabricante produce un modelo 911?',
        'FACIL',
        NULL,
        NULL
    ),
    (
        3,
        23,
        'https://lumiere-a.akamaihd.net/v1/images/gallery_princess_snowwhite_4_1799917e_c73fc046.jpeg?region=3,0,2175,1223&width=960',
        '',
        '',
        '',
        'Manzana',
        'ESCRIBIR',
        '¿Qué fruta muerde Blancanieves?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        9,
        24,
        'https://fifpro.org/media/fhmfhvkx/messi-world-cup.jpg?rxy=0.48356841796117644,0.31512414378031967&width=1600&height=1024&rnd=133210253587130000',
        'verdadero',
        'falso',
        '',
        'verdadero',
        'VF',
        'Messi tiene más balones de oro que Maradona y Cristiano juntos',
        'FACIL',
        NULL,
        NULL
    ),
    (
        9,
        25,
        'https://www.tudn.com/_next/image?url=https%3A%2F%2Fst1.uvnimg.com%2Fdc%2F32%2F1e082b0d4c60b3824fd28fca934c%2Fgettyimages-1210874209.jpg&w=1280&q=75',
        'Ronaldo Nazário',
        'Ronaldinho',
        'Neymar',
        'Ronaldo Nazário',
        'OPCIONES',
        '¿Qué delantero brasileño es apodado “O Fenômeno”?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        9,
        26,
        'https://estaticos-cdn.prensaiberica.es/clip/1d757324-b6f8-4e79-9bba-6e583dddcd93_16-9-discover-aspect-ratio_default_0.jpg',
        '',
        '',
        '',
        'Ronaldinho',
        'ESCRIBIR',
        'Qué jugador brasileño ganó el Balón de Oro en 2005 y 2006',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        9,
        27,
        'https://e01-elmundo.uecdn.es/assets/multimedia/imagenes/2021/05/08/16204803133350.jpg',
        'Verdadero',
        'Falso',
        '',
        'Verdadero',
        'VF',
        'Neymar nunca ha ganado la Champions League',
        'FACIL',
        NULL,
        NULL
    ),
    (
        9,
        28,
        'https://img2.rtve.es/n/16135814',
        'Maldini',
        'Pirlo',
        'Francesco Totti',
        'Francesco Totti',
        'VF',
        '¿Qué jugador italiano es conocido como “Il Divin Codino”?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        9,
        29,
        'https://a.espncdn.com/photo/2024/0704/r1354446_1295x729_16-9.jpg',
        'Wayne Rooney',
        'Steven Gerrard',
        'David Beckham',
        'David Beckham',
        'VF',
        '¿Qué jugador inglés es famoso por su tiro libre en la Euro 2004 contra Grecia?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        6,
        30,
        'https://ams3.digitaloceanspaces.com/graffica/2016/03/grafficalogoapple-scaled.webp',
        'verdadero',
        'falso',
        '',
        'falso',
        'VF',
        'Este fue el primer logo de Apple',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        6,
        31,
        'https://i.pinimg.com/736x/71/68/73/716873fe756b95b91591fd712022e002.jpg',
        'American Airlines',
        'Korean Air',
        'Lufthansa',
        'Korean Air',
        'OPCIONES',
        '¿A qué aerolínea pertenece este logo?',
        'DIFICIL',
        NULL,
        NULL
    ),
    (
        6,
        32,
        'https://i.pinimg.com/736x/f6/4f/56/f64f5611aabc03a17fa0a1ddfc7d0490.jpg',
        '',
        '',
        '',
        'Twitch',
        'ESCRIBIR',
        'La plataforma con este logo compite principalmente con',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        3,
        33,
        'https://static.wikia.nocookie.net/disney/images/4/44/ZapatoCristal.png/revision/latest?cb=20150714072902&path-prefix=es',
        '',
        '',
        '',
        'Cenicienta',
        'ESCRIBIR',
        'Esta princesa perdió su zapatilla de cristal',
        'FACIL',
        NULL,
        NULL
    ),
    (
        6,
        34,
        'https://s1.elespanol.com/2018/08/27/social/humor-el_rey_leon-la_jungla_-_social_333478673_94773643_1706x960.jpg',
        'verdadero',
        'falso',
        '',
        'verdadero',
        'VF',
        'Simba es hijo de Mufasa',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        6,
        35,
        'https://images.ecestaticos.com/DnosZ-m66QPCjPAy5aImVqjhU-I=/0x0:991x705/992x706/filters:fill(white):format(jpg)/f.elconfidencial.com%2Foriginal%2Fa97%2Fced%2Fd85%2Fa97cedd851ee031f65237a67c9ba5363.jpg',
        'Maléfica',
        'Cruella de Vil',
        'Ursula',
        'Maléfica',
        'OPCIONES',
        '¿Quién es el villano de La Bella Durmiente?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        6,
        36,
        'https://uvn-brightspot.s3.amazonaws.com/assets/vixes/btg/101-dalmatas-6.jpg',
        'Cruella de Vil',
        'Maléfica',
        'Ursula',
        'Cruella de Vil',
        'OPCIONES',
        '¿Qué villana aparece en 101 Dálmatas?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        1,
        37,
        'https://res.cloudinary.com/brentford-fc/image/upload/f_auto,q_auto:best,f_auto,q_100,c_fill,ar_16:9/Mbeumo_Man_Utd_woxvoi.jpg',
        'verdadero',
        'falso',
        '',
        'verdadero',
        'VF',
        'El Manchester United es conocido como \'The Red Devils\'',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        1,
        38,
        'https://upload.wikimedia.org/wikipedia/en/e/eb/Manchester_City_FC_badge.svg',
        '',
        '',
        '',
        'Manchester City',
        'ESCRIBIR',
        '¿Qué equipo es conocido como \'Los Citizens\'?',
        'DIFICIL',
        NULL,
        NULL
    ),
    (
        1,
        39,
        'https://www.arsenal.com/sites/default/files/styles/desktop_16x9/public/images/psg-team-pic-villa_lz6d6a82.png?h=cdee7847&auto=webp&itok=5-UqUkOg',
        'verdadero',
        'falso',
        '',
        'verdadero',
        'VF',
        'El PSG pertenece a un grupo de inversión de Catar',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        1,
        40,
        'https://upload.wikimedia.org/wikipedia/commons/thumb/a/aa/Serie_A.svg/966px-Serie_A.svg.png',
        'AC Milán',
        'Juventus',
        'Roma',
        'Juventus',
        'OPCIONES',
        '¿Cuál es el equipo más exitoso de Italia en títulos de liga?',
        'MEDIO',
        NULL,
        NULL
    ),
    (
        1,
        41,
        'https://assets-es.imgfoot.com/media/cache/642x382/malo-gusto-2425.jpg',
        'verdadero',
        'falso',
        '',
        'falso',
        'VF',
        'El Chelsea ganó la Champions League por primera vez en 2005',
        'DIFICIL',
        NULL,
        NULL
    ),
    (
        1,
        42,
        'https://backend.liverpoolfc.com/sites/default/files/styles/xl/public/2024-06/lfc-digital-crest-story-23062024.jpg?itok=S9Bq8wQe',
        '1889',
        '1892',
        '1900',
        '1892',
        'OPCIONES',
        '¿En qué año se fundó el club Liverpool FC?',
        'DIFICIL',
        NULL,
        NULL
    ),
    (
        1,
        43,
        'https://platform.acmilan.theoffside.com/wp-content/uploads/sites/4/chorus/uploads/chorus_asset/file/22891629/1318995420.jpg?quality=90&strip=all&crop=14.350572443743,0,71.298855112515,100',
        '',
        '',
        '',
        'Rossoneri',
        'ESCRIBIR',
        '¿Cuál es el apodo del AC Milan?',
        'DIFICIL',
        NULL,
        NULL
    ),
    (
        1,
        44,
        'https://upload.wikimedia.org/wikipedia/commons/3/3f/ParcdesPrincesCoupedesClubsChampions1956.png',
        'AC Milan',
        'Real Madrid',
        'Benfica',
        'Real Madrid',
        'OPCIONES',
        '¿Qué equipo ganó la primera Champions League de la historia?',
        'FACIL',
        NULL,
        NULL
    );

/*!40000 ALTER TABLE `preguntas` ENABLE KEYS */;

UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;

/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-23  2:06:16