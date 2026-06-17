-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mer. 17 juin 2026 à 11:48
-- Version du serveur : 8.0.30
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `webadherents`
--

-- --------------------------------------------------------

--
-- Structure de la table `adherents`
--

CREATE TABLE `adherents` (
  `numerolicence` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `dernierelicenceactive` varchar(30) NOT NULL,
  `annee` varchar(11) NOT NULL,
  `tel1` varchar(12) DEFAULT NULL,
  `tel2` varchar(12) DEFAULT NULL,
  `adresse1` varchar(100) DEFAULT NULL,
  `adresse2` varchar(100) DEFAULT NULL,
  `mail1` varchar(50) DEFAULT NULL,
  `mail2` varchar(50) DEFAULT NULL,
  `commentaire` varchar(300) DEFAULT NULL,
  `contact1` varchar(50) DEFAULT NULL,
  `contact2` varchar(50) DEFAULT NULL,
  `sexe` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `droitimage` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `login` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `motdepasse` varchar(72) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `role` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `tentativeconnexion` int NOT NULL DEFAULT '5'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`numerolicence`, `nom`, `prenom`, `dernierelicenceactive`, `annee`, `tel1`, `tel2`, `adresse1`, `adresse2`, `mail1`, `mail2`, `commentaire`, `contact1`, `contact2`, `sexe`, `droitimage`, `login`, `motdepasse`, `role`, `tentativeconnexion`) VALUES
('1', 'Pigeon', 'Jeanine', '2012', '1977', '0298731221', '0000', 'Quimper', 'null', 'poitevinchrichri@test.fr', 'oui', 'ceci est un bon commentaire', 'null', 'null', 'Pain', 'non', 'a', '$2a$10$.1TM/.Lp8y7VaXI0ElGMOeLR2w1gHyZbI.1gy4TWTJTWVCDV8Y2z2', 'admin', 5),
('20106513152', 'Flamand', 'Jules', '2024/2025', '2010', '6515615', '513153531', 'njjnjn', 'tdrdtrccrf', 'vyutgtu', 'utytty', 'ttyty', 'tfyttyv', 'yttt', '1', '1', '', '', '0', 5),
('201165165151', 'Ntamack', 'Michel', '2024/2025', '2011', '35451', '5135135', 'kbkjn', 'jkkb', 'jkbkj', 'jnjnkj', 'ljnlnln', 'ljnln', 'ljbnljn', '1', '1', '', '', '0', 5),
('2012654654654', 'Elissalde', 'Jean-Baptiste', '2024/2025', '2012', '535135135', '353513151', 'kbjkbkjkj', 'kjbkljj', 'jbljlnjnl', 'lnll', 'lnlnlkn', 'lnlnln', 'ljnlnlkn', '1', '1', '', '', '0', 5),
('2012654654715', 'Lemarchand', 'Julien', '2024/2025', '2012', '65465451', '35035535', 'kjbkjkbkbj', 'jnhkbhvv', 'cftryctytc', 'trdycytyf', 'uvufuf', 'uhooiopihj', 'oihopop', '1', '1', '', '', '0', 5),
('2013254895', 'Becker', 'Byron', '2024/2025', '2013', '068468', '0658435', 'iugkkh', 'jgcgcf', 'kyfjg', 'bkkj', 'hfxgd', 'tdxyfc', 'fchfc', '1', '1', '', '', '0', 5),
('201425256', 'Durand', 'Laurine', '2024/2025', '2014', '65468', '6546846', 'jhvjhv', 'jgvjgv', 'jvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhb', '1', '1', '', '', '0', 5),
('2014584654685', 'Dupont', 'Antoine', '2024/2025', '2014', '68464', '684654', 'jgvjhgbkhku', 'kjbkkbghvbjh', 'jhvjhvjhvkh', 'jhvkhbk', 'hbkbkjkb', 'hfcdghchfc', 'chytfhtf', '1', '1', '', '', '0', 5),
('201525485241', 'Pichon', 'Vincent', '2024/2025', '2015', '6851513', '14131351135', 'jvhhb', 'gfxgfxc', 'jhgkgk', 'jhvkvh', 'jgcvjhv', 'jgckhkjb', 'khjbkjb', '1', '1', '', '', '0', 5),
('20155446545', 'Aldritt', 'Grégorie', '2024/2025', '2015', '6513515531', 'null', 'bhliblkhbhkb', 'null', 'jguvkjhkjhb', 'null', 'rgregerrgrgg', NULL, NULL, NULL, NULL, '', '', '0', 5),
('20176533', 'Penaud', 'Fabien', '2024/2025', '2017', '365135135', 'null', 'ibkkkkb', 'null', 'kbkjbbkjb', 'null', 'rgrtggg', NULL, NULL, NULL, NULL, '', '', '0', 5),
('2017654654655', 'Kelleher', 'Byron', '2024/2025', '2017', '6565151', '351351351', 'bkjkjb', 'kjhbkbk', 'kjbkb', 'kjbkjb', 'kjbkjb', 'kjbkjb', 'jukj', '1', '1', '', '', '0', 5),
('22', 'x', 'x', 'x', 'x', 'x', NULL, 'x', NULL, 'x', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('2345324', 'Jean', 'Dufoin', 'oui', 'non', '1234', NULL, 'oui', NULL, 'zre', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('4312761', 'a', 'a', 'oui', 'oui', 'oui', NULL, 'oui', NULL, 'oui', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('43265', 'q', 'q', 'q', 'q', 'q', NULL, 'q', NULL, 'q', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('5376', 'i', 'i', 'i', 'i', 'i', NULL, 'i', NULL, 'i', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('8888', 'Montour', 'Jean-Paul', '333', '11', NULL, NULL, NULL, NULL, 'm', NULL, NULL, NULL, NULL, NULL, NULL, 'JPMonour', '1234', 'modif', 5),
('957', 'Modif', 'Modif', '4321', '31', NULL, NULL, NULL, NULL, 'mod', NULL, NULL, NULL, NULL, NULL, NULL, 'm', 'm', 'modif', 5),
('999', 'Picot', 'Simon', '3213', '1111', NULL, NULL, NULL, NULL, 'p', NULL, NULL, NULL, NULL, NULL, NULL, 'ysimon', '1234', 'consult', 5),
('aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', NULL, NULL, NULL, NULL, 'aa', '$2a$10$.1TM/.Lp8y7VaXI0ElGMOeLR2w1gHyZbI.1gy4TWTJTWVCDV8Y2z2', 'adherent', 5),
('b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', NULL, NULL, NULL, NULL, NULL, '$2a$10$T.7zMcZGWhYv.4B5XOI/aePk7PcR4B5krKQIHFD7d8T6gGGcNk7Xu', 'adherent', 5),
('df', 'zed', 'ef', 'hfch', 'hgch', 'fxgfc', 'gfx', 'fc', NULL, 'fchf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('eez', 'aqz', 'eare', 'zsd', 'resd', 'esd', NULL, 'resd', NULL, 'esd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('fc', 'rdf', 'yd', 'hfc', 'hfc', 'hfc', NULL, 'hfc', NULL, 'hfc', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('g', 'f', 'f', 'g', 'g', 'g', NULL, 'g', NULL, 'g', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('gcfd', 'ytf', 'fh', 'qeez', 'ze', 'eswsw', 'd', 'dsw', NULL, 'sw', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('gf', 'trdtd', 'tdf', 'gfxc', 'gf', 'gfxc', NULL, 'gf', NULL, 'gf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('kjhk', 'sjg', 'kkuk', 'kjkj', 'kjhkj', 'hgv', NULL, 'gv', NULL, 'hgv', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5),
('v', 'v', 'v', 'v', 'v', 'v', NULL, 'v', NULL, 'v', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5);

-- --------------------------------------------------------

--
-- Structure de la table `anneecategorie`
--

CREATE TABLE `anneecategorie` (
  `categories` varchar(30) NOT NULL,
  `annee` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `anneecategorie`
--

INSERT INTO `anneecategorie` (`categories`, `annee`) VALUES
('M12', '2014'),
('M12', '2013'),
('M14', '2012'),
('M14', '2011'),
('M16', '2010'),
('M16', '2009'),
('F15', '2010'),
('M10', '2015'),
('M10', '2016'),
('M8', '2017'),
('M8', '2018'),
('M6', '2019'),
('M6', '2020'),
('Baby', '2021'),
('F-18', '2009'),
('F-18', '2008'),
('F-18', '2007'),
('M19', '2008'),
('M19', '2007'),
('M19', '2006'),
('M+18', '<2006'),
('F+18', '<2007'),
('Kozs', '<2007'),
('Touch', '<2007'),
('Ouite', '12345');

-- --------------------------------------------------------

--
-- Structure de la table `categorieadherent`
--

CREATE TABLE `categorieadherent` (
  `numLic` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `idcategorie` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categorieadherent`
--

INSERT INTO `categorieadherent` (`numLic`, `idcategorie`) VALUES
('201425256', '4'),
('2013254895', '4'),
('2014584654685', '4'),
('201525485241', '3'),
('20155446545', '3'),
('20176533', '2'),
('2012654654654', '5'),
('201165165151', '5'),
('2012654654715', '5'),
('20106513152', '6'),
('2017654654655', '4'),
('999', '4'),
('999', '5'),
('1', '4'),
('1', '5'),
('1', '15'),
('1', '16'),
('957', '4'),
('957', '5'),
('8888', '4'),
('8888', '5'),
('8888', '17'),
('12', '4'),
('5762543', '5'),
('12340000000000', '11'),
('aa', '11'),
('aa', '11'),
('b', '11');

-- --------------------------------------------------------

--
-- Structure de la table `categoriesportive`
--

CREATE TABLE `categoriesportive` (
  `idcategorie` int UNSIGNED NOT NULL,
  `nomcategorie` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `categoriesportive`
--

INSERT INTO `categoriesportive` (`idcategorie`, `nomcategorie`) VALUES
(1, 'M6'),
(2, 'M8'),
(3, 'M10'),
(4, 'M12'),
(5, 'M14'),
(6, 'M16'),
(7, 'M+18'),
(8, 'M19'),
(9, 'F15'),
(10, 'F-18'),
(11, 'F+18'),
(12, 'Baby'),
(13, 'Touch'),
(14, 'Kozs'),
(15, 'Senior'),
(16, 'Rugby'),
(17, 'Football');

-- --------------------------------------------------------

--
-- Structure de la table `categorieutilisateur`
--

CREATE TABLE `categorieutilisateur` (
  `idUser` int NOT NULL,
  `categorieUser` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorieutilisateur`
--

INSERT INTO `categorieutilisateur` (`idUser`, `categorieUser`) VALUES
(123, 'M12'),
(777, 'Baby'),
(957, 'Baby'),
(999, 'M08'),
(8888, 'Kozs');

-- --------------------------------------------------------

--
-- Structure de la table `critereadherent`
--

CREATE TABLE `critereadherent` (
  `idcritere` int UNSIGNED NOT NULL,
  `numerolicence` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `valcritere` int UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `critereadherent`
--

INSERT INTO `critereadherent` (`idcritere`, `numerolicence`, `valcritere`) VALUES
(1, '1', 3),
(2, '1', 3),
(3, '1', 5),
(4, '1', 5),
(5, '1', 5),
(6, '1', 4),
(1, '12340000000000', 0),
(2, '12340000000000', 0),
(3, '12340000000000', 0),
(4, '12340000000000', 0),
(5, '12340000000000', 0),
(6, '12340000000000', 0),
(1, '20106513152', 2),
(2, '20106513152', 2),
(3, '20106513152', 2),
(4, '20106513152', 2),
(5, '20106513152', 2),
(6, '20106513152', 2),
(1, '201165165151', NULL),
(2, '201165165151', NULL),
(3, '201165165151', NULL),
(4, '201165165151', NULL),
(5, '201165165151', NULL),
(6, '201165165151', NULL),
(1, '2012654654654', NULL),
(2, '2012654654654', NULL),
(3, '2012654654654', NULL),
(4, '2012654654654', NULL),
(5, '2012654654654', NULL),
(6, '2012654654654', NULL),
(1, '2012654654715', NULL),
(2, '2012654654715', NULL),
(3, '2012654654715', NULL),
(4, '2012654654715', NULL),
(5, '2012654654715', NULL),
(6, '2012654654715', NULL),
(1, '2013254895', NULL),
(2, '2013254895', NULL),
(3, '2013254895', NULL),
(4, '2013254895', NULL),
(5, '2013254895', NULL),
(6, '2013254895', NULL),
(1, '201425256', NULL),
(2, '201425256', NULL),
(3, '201425256', NULL),
(4, '201425256', NULL),
(5, '201425256', NULL),
(6, '201425256', NULL),
(1, '2014584654685', NULL),
(2, '2014584654685', NULL),
(3, '2014584654685', NULL),
(4, '2014584654685', NULL),
(5, '2014584654685', NULL),
(6, '2014584654685', NULL),
(1, '201525485241', NULL),
(2, '201525485241', NULL),
(3, '201525485241', NULL),
(4, '201525485241', NULL),
(5, '201525485241', NULL),
(6, '201525485241', NULL),
(1, '20155446545', NULL),
(2, '20155446545', NULL),
(3, '20155446545', NULL),
(4, '20155446545', NULL),
(5, '20155446545', NULL),
(6, '20155446545', NULL),
(1, '20176533', NULL),
(2, '20176533', NULL),
(3, '20176533', NULL),
(4, '20176533', NULL),
(5, '20176533', NULL),
(6, '20176533', NULL),
(1, '2017654654655', NULL),
(2, '2017654654655', NULL),
(3, '2017654654655', NULL),
(4, '2017654654655', NULL),
(5, '2017654654655', NULL),
(6, '2017654654655', NULL),
(1, '22', NULL),
(2, '22', NULL),
(3, '22', NULL),
(4, '22', NULL),
(5, '22', NULL),
(6, '22', NULL),
(1, '2345324', NULL),
(2, '2345324', NULL),
(3, '2345324', NULL),
(4, '2345324', NULL),
(5, '2345324', NULL),
(6, '2345324', NULL),
(1, '4312761', NULL),
(2, '4312761', NULL),
(3, '4312761', NULL),
(4, '4312761', NULL),
(5, '4312761', NULL),
(6, '4312761', NULL),
(1, '43265', NULL),
(2, '43265', NULL),
(3, '43265', NULL),
(4, '43265', NULL),
(5, '43265', NULL),
(6, '43265', NULL),
(1, '5376', NULL),
(2, '5376', NULL),
(3, '5376', NULL),
(4, '5376', NULL),
(5, '5376', NULL),
(6, '5376', NULL),
(1, '786', NULL),
(2, '786', NULL),
(3, '786', NULL),
(4, '786', NULL),
(5, '786', NULL),
(6, '786', NULL),
(1, '8888', NULL),
(2, '8888', NULL),
(3, '8888', NULL),
(4, '8888', NULL),
(5, '8888', NULL),
(6, '8888', NULL),
(1, '957', NULL),
(2, '957', NULL),
(3, '957', NULL),
(4, '957', NULL),
(5, '957', NULL),
(6, '957', NULL),
(1, '999', NULL),
(2, '999', NULL),
(3, '999', NULL),
(4, '999', NULL),
(5, '999', NULL),
(6, '999', NULL),
(1, 'aa', 0),
(2, 'aa', 0),
(3, 'aa', 0),
(4, 'aa', 0),
(5, 'aa', 0),
(6, 'aa', 0),
(7, 'aa', 0),
(1, 'b', 0),
(2, 'b', 0),
(3, 'b', 0),
(4, 'b', 0),
(5, 'b', 0),
(6, 'b', 0),
(7, 'b', 0),
(1, 'df', NULL),
(2, 'df', NULL),
(3, 'df', NULL),
(4, 'df', NULL),
(5, 'df', NULL),
(6, 'df', NULL),
(1, 'eez', NULL),
(2, 'eez', NULL),
(3, 'eez', NULL),
(4, 'eez', NULL),
(5, 'eez', NULL),
(6, 'eez', NULL),
(1, 'fc', NULL),
(2, 'fc', NULL),
(3, 'fc', NULL),
(4, 'fc', NULL),
(5, 'fc', NULL),
(6, 'fc', NULL),
(1, 'g', NULL),
(2, 'g', NULL),
(3, 'g', NULL),
(4, 'g', NULL),
(5, 'g', NULL),
(6, 'g', NULL),
(1, 'gcfd', NULL),
(2, 'gcfd', NULL),
(3, 'gcfd', NULL),
(4, 'gcfd', NULL),
(5, 'gcfd', NULL),
(6, 'gcfd', NULL),
(1, 'gf', NULL),
(2, 'gf', NULL),
(3, 'gf', NULL),
(4, 'gf', NULL),
(5, 'gf', NULL),
(6, 'gf', NULL),
(1, 'kjhk', NULL),
(2, 'kjhk', NULL),
(3, 'kjhk', NULL),
(4, 'kjhk', NULL),
(5, 'kjhk', NULL),
(6, 'kjhk', NULL),
(1, 'o', NULL),
(2, 'o', NULL),
(3, 'o', NULL),
(4, 'o', NULL),
(5, 'o', NULL),
(6, 'o', NULL),
(1, 'v', NULL),
(2, 'v', NULL),
(3, 'v', NULL),
(4, 'v', NULL),
(5, 'v', NULL),
(6, 'v', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `criteres`
--

CREATE TABLE `criteres` (
  `idcritere` int UNSIGNED NOT NULL,
  `nomcritere` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `criteres`
--

INSERT INTO `criteres` (`idcritere`, `nomcritere`) VALUES
(1, 'endurance'),
(2, 'niveauphysique'),
(3, 'critere3'),
(4, 'critere4'),
(5, 'critere5'),
(6, 'critere6'),
(7, 'pain');

-- --------------------------------------------------------

--
-- Structure de la table `log`
--

CREATE TABLE `log` (
  `idlog` int NOT NULL,
  `idconnexion` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `logintime` datetime DEFAULT NULL,
  `lastactivity` datetime DEFAULT NULL,
  `logouttime` datetime DEFAULT NULL,
  `navhistory` text COLLATE utf8mb4_general_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `log`
--

INSERT INTO `log` (`idlog`, `idconnexion`, `logintime`, `lastactivity`, `logouttime`, `navhistory`) VALUES
(2, 'Poitevin Jeanine 1', '2026-03-24 12:51:32', NULL, NULL, NULL),
(3, 'Poitevin Jeanine 1', '2026-03-24 13:58:04', NULL, NULL, NULL),
(4, 'Poitevin Jeanine 1', '2026-03-24 14:03:10', NULL, NULL, NULL),
(5, 'Picot Simon 999', '2026-03-24 14:37:16', NULL, NULL, NULL),
(6, 'Picot Simon 999', '2026-03-24 14:38:23', NULL, NULL, NULL),
(7, 'Picot Simon 999', '2026-03-24 14:41:51', NULL, '2026-03-24 14:41:57', NULL),
(8, 'Poitevin Jeanine 1', '2026-03-25 08:45:09', NULL, NULL, NULL),
(9, 'Poitevin Jeanine 1', '2026-03-25 09:01:15', NULL, NULL, NULL),
(10, 'Picot Simon 999', '2026-03-25 09:09:17', NULL, NULL, NULL),
(11, 'Poitevin Jeanine 1', '2026-03-25 11:14:58', NULL, NULL, NULL),
(12, 'Poitevin Jeanine 1', '2026-03-25 11:18:31', NULL, '2026-03-25 11:19:35', NULL),
(13, 'Picot Simon 999', '2026-03-25 11:19:36', NULL, '2026-03-25 11:20:56', NULL),
(14, 'Poitevin Jeanine 1', '2026-03-26 08:16:24', NULL, NULL, NULL),
(15, 'Picot Simon 999', '2026-03-26 08:33:04', NULL, NULL, NULL),
(16, 'Picot Simon 999', '2026-03-26 08:34:18', NULL, '2026-03-26 08:35:51', NULL),
(17, 'Picot Simon 999', '2026-03-26 08:35:58', NULL, '2026-03-26 08:37:35', NULL),
(18, 'Picot Simon 999', '2026-03-26 08:38:05', NULL, NULL, NULL),
(19, 'Picot Simon 999', '2026-03-26 08:39:35', NULL, NULL, NULL),
(20, 'Picot Simon 999', '2026-03-26 08:39:42', NULL, '2026-03-26 08:42:17', NULL),
(21, 'Picot Simon 999', '2026-03-26 08:45:39', NULL, '2026-03-26 08:45:46', NULL),
(22, 'Poitevin Jeanine 1', '2026-03-26 08:45:49', NULL, '2026-03-26 08:47:38', NULL),
(23, 'Picot Simon 999', '2026-03-26 08:47:41', NULL, '2026-03-26 08:48:05', NULL),
(24, 'Picot Simon 999', '2026-03-26 08:48:11', NULL, NULL, NULL),
(25, 'Picot Simon 999', '2026-03-26 08:56:03', NULL, NULL, NULL),
(26, 'Picot Simon 999', '2026-03-26 08:58:09', NULL, '2026-03-26 09:00:58', NULL),
(27, 'Picot Simon 999', '2026-03-26 09:01:07', NULL, '2026-03-26 09:03:02', NULL),
(28, 'Picot Simon 999', '2026-03-26 09:03:18', NULL, NULL, NULL),
(29, 'Picot Simon 999', '2026-03-26 09:27:16', NULL, '2026-03-26 09:27:30', NULL),
(30, 'Picot Simon 999', '2026-03-26 11:34:49', NULL, NULL, NULL),
(31, 'Picot Simon 999', '2026-03-26 11:50:47', NULL, '2026-03-26 11:52:00', NULL),
(32, 'Picot Simon 999', '2026-03-26 11:53:45', NULL, '2026-03-26 11:53:58', NULL),
(33, 'Picot Simon 999', '2026-03-26 11:57:25', NULL, '2026-03-26 11:57:32', NULL),
(34, 'Poitevin Jeanine 1', '2026-03-26 11:57:34', NULL, NULL, NULL),
(35, 'Poitevin Jeanine 1', '2026-03-26 14:23:04', NULL, '2026-03-26 14:24:33', '0;'),
(36, 'Poitevin Jeanine 1', '2026-03-26 14:24:43', NULL, '2026-03-26 14:24:45', '0; logout;'),
(37, 'Picot Simon 999', '2026-03-26 14:32:33', NULL, NULL, '0;'),
(38, 'Picot Simon 999', '2026-03-26 14:34:35', '2026-03-26 14:34:36', NULL, '0; 1;'),
(39, 'Poitevin Jeanine 1', '2026-04-01 09:05:30', '2026-04-01 09:06:28', NULL, '0; 4;'),
(40, 'Poitevin Jeanine 1', '2026-04-01 12:35:20', NULL, NULL, '0;'),
(41, 'Poitevin Jeanine 1', '2026-04-02 08:22:55', '2026-04-02 08:24:54', '2026-04-02 08:26:17', '0; 4; logout;'),
(42, 'Poitevin Jeanine 1', '2026-04-02 08:29:22', '2026-04-02 08:29:25', NULL, '0; 2;'),
(43, 'Poitevin Jeanine 1', '2026-04-02 08:37:10', '2026-04-02 08:37:11', NULL, '0; 3;'),
(44, 'Poitevin Jeanine 1', '2026-04-02 08:41:27', '2026-04-02 08:41:41', '2026-04-02 08:41:46', '0; vers consulter les critères; vers création d\'adhérent; vers consulter les fiches administratives; vers consulter les critères; vers liste d\'adhérents par catégories; logout;'),
(45, 'Poitevin Jeanine 1', '2026-04-02 08:54:06', '2026-04-02 08:54:07', NULL, '0; vers consulter les critères;'),
(46, 'Poitevin Jeanine 1', '2026-04-02 08:58:09', '2026-04-02 08:58:10', '2026-04-02 08:58:49', '0; vers création d\'adhérent; logout;'),
(47, 'Poitevin Jeanine 1', '2026-04-02 11:48:12', '2026-04-02 11:49:20', '2026-04-02 11:50:09', '0; vers consulter les critères; vers consulter les critères; logout;'),
(48, 'Poitevin Jeanine 1', '2026-04-02 11:50:21', '2026-04-02 11:53:01', '2026-04-02 11:55:31', '0; vers consulter les catégories; vers consulter son profil; vers consulter les fiches administratives; suppression de l\'adhérent 009090 dans la BDD; logout;'),
(49, 'Poitevin Jeanine 1', '2026-04-02 13:07:47', '2026-04-02 13:08:21', NULL, '0; vers consulter son profil; vers liste d\'adhérents par catégories;'),
(50, 'Poitevin Jeanine 1', '2026-04-02 13:18:58', '2026-04-02 13:18:59', NULL, '0; vers création d\'adhérent;'),
(51, 'Poitevin Jeanine 1', '2026-04-02 13:30:54', '2026-04-02 13:30:55', '2026-04-02 13:32:03', '0; vers consulter son profil; logout;'),
(52, 'Poitevin Jeanine 1', '2026-04-02 13:32:13', '2026-04-02 13:32:15', NULL, '0; vers consulter son profil;'),
(53, 'Poitevin Jeanine 1', '2026-04-02 13:47:09', '2026-04-02 13:47:10', '2026-04-02 13:48:03', '0; vers consulter son profil; logout;'),
(54, 'Poitevin Jeanine 1', '2026-04-02 15:01:59', '2026-04-02 15:02:01', NULL, '0; vers consulter son profil;'),
(55, 'Poitevin Jeanine 1', '2026-04-03 08:35:18', '2026-04-03 08:35:20', NULL, '0; vers consulter son profil;'),
(56, 'Poitevin Jeanine 1', '2026-04-03 08:44:07', '2026-04-03 08:44:08', NULL, '0; vers consulter son profil;'),
(57, 'Poitevin Jeanine 1', '2026-04-03 09:27:55', '2026-04-03 09:27:56', '2026-04-03 09:29:12', '0; vers consulter son profil; logout;'),
(58, 'Poitevin Jeanine 1', '2026-04-03 09:30:17', '2026-04-03 09:30:18', NULL, '0; vers consulter son profil;'),
(59, 'Poitevin Jeanine 1', '2026-04-03 09:31:16', '2026-04-03 09:31:17', NULL, '0; vers consulter son profil;'),
(60, 'Pigeon Jeanine 1', '2026-04-03 11:28:03', '2026-04-03 11:28:44', NULL, '0; vers consulter son profil; modification de ses infos de profil;'),
(61, 'Poitevin Jeanine 1', '2026-04-03 11:43:59', '2026-04-03 11:44:01', '2026-04-03 11:44:16', '0; vers consulter son profil; logout;'),
(62, 'Poitevin Jeanine 1', '2026-04-03 11:45:01', '2026-04-03 11:45:02', NULL, '0; vers consulter son profil;'),
(63, 'Poitevin Jeanine 1', '2026-04-03 12:22:15', '2026-04-03 12:22:17', NULL, '0; vers consulter son profil;'),
(64, 'Poitevin Jeanine 1', '2026-04-03 12:26:14', '2026-04-03 12:26:15', NULL, '0; vers consulter son profil;'),
(65, 'Poitevin Jeanine 1', '2026-04-03 12:28:25', '2026-04-03 12:28:26', NULL, '0; vers consulter son profil;'),
(66, 'Poitevin Jeanine 1', '2026-04-03 12:35:38', '2026-04-03 12:35:39', '2026-04-03 12:36:16', '0; vers consulter son profil; logout;'),
(67, 'Poitevin Jeanine 1', '2026-04-03 12:36:25', '2026-04-03 12:36:27', '2026-04-03 12:38:00', '0; vers consulter son profil; logout;'),
(68, 'Poitevin Jeanine 1', '2026-04-03 12:38:07', '2026-04-03 12:38:08', '2026-04-03 12:38:46', '0; vers consulter son profil; logout;'),
(69, 'Poitevin Jeanine 1', '2026-04-03 12:39:00', '2026-04-03 12:39:25', NULL, '0; vers consulter son profil; modification de ses infos de profil;'),
(70, 'Pigeon Jeanine 1', '2026-04-03 12:46:37', '2026-04-03 12:46:39', NULL, '0; vers consulter son profil;'),
(71, 'Pigeon Jeanine 1', '2026-05-13 07:26:38', NULL, NULL, '0;'),
(72, 'Pigeon Jeanine 1', '2026-05-13 08:01:01', NULL, NULL, '0;'),
(73, 'Pigeon Jeanine 1', '2026-05-13 08:01:24', NULL, NULL, '0;'),
(74, 'Pigeon Jeanine 1', '2026-05-13 08:05:17', NULL, NULL, '0;'),
(75, 'Pigeon Jeanine 1', '2026-05-13 08:05:42', NULL, NULL, '0;'),
(76, 'Picot Simon 999', '2026-06-15 07:58:47', NULL, NULL, '0;'),
(77, 'Picot Simon 999', '2026-06-15 07:58:56', NULL, NULL, '0;'),
(78, 'Picot Simon 999', '2026-06-15 08:01:39', NULL, '2026-06-15 08:02:04', '0; logout;'),
(79, 'Pigeon Jeanine 1', '2026-06-15 08:02:07', '2026-06-15 08:16:37', NULL, '0; vers consulter son profil; vers consulter les catégories; vers création d\'adhérent; vers consulter les catégories; vers consulter les catégories; vers consulter les catégories; vers création d\'adhérent; vers consulter les catégories; vers création d\'adhérent; vers liste d\'adhérents par catégories; vers liste d\'adhérents par catégories; vers consulter les critères; ajout du critère pain dans la BDD; vers consulter les critères; vers consulter les critères;'),
(80, 'Pigeon Jeanine 1', '2026-06-15 12:10:43', '2026-06-15 12:10:45', NULL, '0; vers consulter son profil;'),
(81, 'Pigeon Jeanine 1', '2026-06-15 12:57:11', '2026-06-15 12:57:20', NULL, '0; vers consulter les fiches administratives;'),
(82, 'Picot Simon 999', '2026-06-15 13:11:28', '2026-06-15 13:11:35', '2026-06-15 13:11:48', '0; vers consulter les fiches administratives; vers consulter son profil; logout;'),
(83, 'Pigeon Jeanine 1', '2026-06-15 13:11:51', '2026-06-15 13:12:07', NULL, '0; vers consulter les catégories; vers création d\'adhérent; vers consulter les critères;'),
(84, 'Pigeon Jeanine 1', '2026-06-15 14:17:31', '2026-06-15 14:17:33', '2026-06-15 14:21:03', '0; vers création d\'adhérent; logout;'),
(85, 'Picot Simon 999', '2026-06-15 14:21:04', NULL, '2026-06-15 14:22:01', '0; logout;'),
(86, 'Modif Modif 957', '2026-06-15 14:22:04', '2026-06-15 14:22:06', NULL, '0; vers création d\'adhérent;'),
(87, 'Pigeon Jeanine 1', '2026-06-15 14:43:01', '2026-06-15 14:48:22', NULL, '0; vers création d\'adhérent; ajout de l\'adhérent 1528720933546 dans la BDD; ajout de l\'adhérent 1528720933546 dans la BDD;'),
(88, 'Pigeon Jeanine 1', '2026-06-15 14:48:48', '2026-06-15 14:49:01', NULL, '0; vers création d\'adhérent; ajout de l\'adhérent a dans la BDD;'),
(89, 'Pigeon Jeanine 1', '2026-06-16 07:18:41', '2026-06-16 07:20:22', NULL, '0; vers création d\'adhérent; ajout de l\'adhérent aa dans la BDD;'),
(90, 'Pigeon Jeanine 1', '2026-06-16 07:23:52', '2026-06-16 07:24:07', NULL, '0; vers création d\'adhérent; ajout de l\'adhérent aa dans la BDD;'),
(91, 'Pigeon Jeanine 1', '2026-06-16 07:28:03', '2026-06-16 07:28:15', NULL, '0; vers création d\'adhérent; ajout de l\'adhérent aa dans la BDD;'),
(92, 'aa aa aa', '2026-06-16 07:55:09', NULL, NULL, '0;'),
(93, 'aa aa aa', '2026-06-16 08:24:05', '2026-06-16 08:24:17', NULL, '0; vers liste d\'adhérents par catégories; vers liste d\'adhérents par catégories;'),
(94, 'aa aa aa', '2026-06-16 09:42:07', NULL, NULL, '0;'),
(95, 'aa aa aa', '2026-06-16 09:46:12', '2026-06-16 09:46:31', NULL, '0; vers consulter les critères; vers consulter les fiches administratives; vers consulter son profil;'),
(96, 'aa aa aa', '2026-06-16 11:46:29', NULL, '2026-06-16 11:46:30', '0; logout;'),
(97, 'aa aa aa', '2026-06-16 12:25:50', NULL, NULL, '0;'),
(98, 'aa aa aa', '2026-06-16 12:26:08', NULL, NULL, '0;'),
(99, 'aa aa aa', '2026-06-16 12:26:21', NULL, NULL, '0;'),
(100, 'aa aa aa', '2026-06-16 12:26:39', NULL, NULL, '0;'),
(101, 'aa aa aa', '2026-06-16 12:29:31', '2026-06-16 12:29:32', NULL, '0; vers consulter son profil;'),
(102, 'aa aa aa', '2026-06-16 12:46:02', NULL, NULL, '0;'),
(103, 'Pigeon Jeanine 1', '2026-06-16 12:48:53', '2026-06-16 12:52:51', NULL, '0; vers consulter son profil;'),
(104, 'Pigeon Jeanine 1', '2026-06-16 13:19:03', NULL, NULL, '0;'),
(105, 'Pigeon Jeanine 1', '2026-06-16 13:19:56', '2026-06-16 13:21:55', NULL, '0; vers consulter son profil; vers création d\'adhérent; vers consulter les catégories; 2; vers consulter les critères; vers liste d\'adhérents par catégories; vers consulter les critères; vers création d\'adhérent; ajout de l\'adhérent b dans la BDD;'),
(106, 'Pigeon Jeanine 1', '2026-06-17 08:15:16', NULL, NULL, '0;'),
(107, 'aa aa aa', '2026-06-17 09:58:55', NULL, NULL, '0;'),
(108, 'aa aa aa', '2026-06-17 11:45:43', '2026-06-17 11:46:02', NULL, '0; vers consulter son profil; vers consulter les fiches administratives; vers liste d\'adhérents par catégories;');

-- --------------------------------------------------------

--
-- Structure de la table `rechercheperso`
--

CREATE TABLE `rechercheperso` (
  `idUser` int NOT NULL,
  `nomRech` varchar(20) NOT NULL,
  `prenomRech` varchar(20) NOT NULL,
  `numLicenceRech` varchar(20) NOT NULL,
  `anneeRech` varchar(20) NOT NULL,
  `telRech` varchar(20) NOT NULL,
  `mailRech` varchar(20) NOT NULL,
  `categRech` varchar(20) NOT NULL,
  `contact` varchar(50) DEFAULT NULL,
  `sexe` tinyint(1) DEFAULT NULL,
  `droitimage` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rechercheperso`
--

INSERT INTO `rechercheperso` (`idUser`, `nomRech`, `prenomRech`, `numLicenceRech`, `anneeRech`, `telRech`, `mailRech`, `categRech`, `contact`, `sexe`, `droitimage`) VALUES
(1, '', '', '', '', '', '', 'M12', '', 0, 0),
(6, '', '', '', '', '', '', '', '', 0, 0);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adherents`
--
ALTER TABLE `adherents`
  ADD PRIMARY KEY (`numerolicence`);

--
-- Index pour la table `categoriesportive`
--
ALTER TABLE `categoriesportive`
  ADD PRIMARY KEY (`idcategorie`);

--
-- Index pour la table `categorieutilisateur`
--
ALTER TABLE `categorieutilisateur`
  ADD PRIMARY KEY (`idUser`);

--
-- Index pour la table `critereadherent`
--
ALTER TABLE `critereadherent`
  ADD PRIMARY KEY (`numerolicence`,`idcritere`),
  ADD KEY `idcritere` (`idcritere`);

--
-- Index pour la table `criteres`
--
ALTER TABLE `criteres`
  ADD PRIMARY KEY (`idcritere`);

--
-- Index pour la table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`idlog`);

--
-- Index pour la table `rechercheperso`
--
ALTER TABLE `rechercheperso`
  ADD PRIMARY KEY (`idUser`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `categoriesportive`
--
ALTER TABLE `categoriesportive`
  MODIFY `idcategorie` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `criteres`
--
ALTER TABLE `criteres`
  MODIFY `idcritere` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `log`
--
ALTER TABLE `log`
  MODIFY `idlog` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `critereadherent`
--
ALTER TABLE `critereadherent`
  ADD CONSTRAINT `critereadherent_ibfk_1` FOREIGN KEY (`idcritere`) REFERENCES `criteres` (`idcritere`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
