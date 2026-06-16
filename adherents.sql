-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mar. 16 juin 2026 à 12:49
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
  `role` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`numerolicence`, `nom`, `prenom`, `dernierelicenceactive`, `annee`, `tel1`, `tel2`, `adresse1`, `adresse2`, `mail1`, `mail2`, `commentaire`, `contact1`, `contact2`, `sexe`, `droitimage`, `login`, `motdepasse`, `role`) VALUES
('1', 'Pigeon', 'Jeanine', '2012', '1977', '0298731221', '0000', 'Quimper', 'null', 'poitevinchrichri@test.fr', 'oui', 'ceci est un bon commentaire', 'null', 'null', 'Pain', 'non', 'a', '$2a$10$.1TM/.Lp8y7VaXI0ElGMOeLR2w1gHyZbI.1gy4TWTJTWVCDV8Y2z2', 'admin'),
('12340000000000', 'Poitevin', 'timy', '2012', '1977', '0298731221', 'null', 'Quimper', 'null', 'poitevinchrichri@test.fr', 'null', 'commentaire', 'fromage', 'pain', 'non', 'oui', NULL, NULL, NULL),
('20106513152', 'Flamand', 'Jules', '2024/2025', '2010', '6515615', '513153531', 'njjnjn', 'tdrdtrccrf', 'vyutgtu', 'utytty', 'ttyty', 'tfyttyv', 'yttt', '1', '1', '', '', '0'),
('201165165151', 'Ntamack', 'Michel', '2024/2025', '2011', '35451', '5135135', 'kbkjn', 'jkkb', 'jkbkj', 'jnjnkj', 'ljnlnln', 'ljnln', 'ljbnljn', '1', '1', '', '', '0'),
('2012654654654', 'Elissalde', 'Jean-Baptiste', '2024/2025', '2012', '535135135', '353513151', 'kbjkbkjkj', 'kjbkljj', 'jbljlnjnl', 'lnll', 'lnlnlkn', 'lnlnln', 'ljnlnlkn', '1', '1', '', '', '0'),
('2012654654715', 'Lemarchand', 'Julien', '2024/2025', '2012', '65465451', '35035535', 'kjbkjkbkbj', 'jnhkbhvv', 'cftryctytc', 'trdycytyf', 'uvufuf', 'uhooiopihj', 'oihopop', '1', '1', '', '', '0'),
('2013254895', 'Becker', 'Byron', '2024/2025', '2013', '068468', '0658435', 'iugkkh', 'jgcgcf', 'kyfjg', 'bkkj', 'hfxgd', 'tdxyfc', 'fchfc', '1', '1', '', '', '0'),
('201425256', 'Durand', 'Laurine', '2024/2025', '2014', '65468', '6546846', 'jhvjhv', 'jgvjgv', 'jvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhb', '1', '1', '', '', '0'),
('2014584654685', 'Dupont', 'Antoine', '2024/2025', '2014', '68464', '684654', 'jgvjhgbkhku', 'kjbkkbghvbjh', 'jhvjhvjhvkh', 'jhvkhbk', 'hbkbkjkb', 'hfcdghchfc', 'chytfhtf', '1', '1', '', '', '0'),
('201525485241', 'Pichon', 'Vincent', '2024/2025', '2015', '6851513', '14131351135', 'jvhhb', 'gfxgfxc', 'jhgkgk', 'jhvkvh', 'jgcvjhv', 'jgckhkjb', 'khjbkjb', '1', '1', '', '', '0'),
('20155446545', 'Aldritt', 'Grégorie', '2024/2025', '2015', '6513515531', 'null', 'bhliblkhbhkb', 'null', 'jguvkjhkjhb', 'null', 'rgregerrgrgg', NULL, NULL, NULL, NULL, '', '', '0'),
('20176533', 'Penaud', 'Fabien', '2024/2025', '2017', '365135135', 'null', 'ibkkkkb', 'null', 'kbkjbbkjb', 'null', 'rgrtggg', NULL, NULL, NULL, NULL, '', '', '0'),
('2017654654655', 'Kelleher', 'Byron', '2024/2025', '2017', '6565151', '351351351', 'bkjkjb', 'kjhbkbk', 'kjbkb', 'kjbkjb', 'kjbkjb', 'kjbkjb', 'jukj', '1', '1', '', '', '0'),
('22', 'x', 'x', 'x', 'x', 'x', NULL, 'x', NULL, 'x', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2345324', 'Jean', 'Dufoin', 'oui', 'non', '1234', NULL, 'oui', NULL, 'zre', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('4312761', 'a', 'a', 'oui', 'oui', 'oui', NULL, 'oui', NULL, 'oui', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('43265', 'q', 'q', 'q', 'q', 'q', NULL, 'q', NULL, 'q', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('5376', 'i', 'i', 'i', 'i', 'i', NULL, 'i', NULL, 'i', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('786', 't', 't', 'oui', 'oui', 'oui', NULL, 'oui', NULL, 'oui', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8888', 'Montour', 'Jean-Paul', '333', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'JPMonour', '1234', 'modif'),
('957', 'Modif', 'Modif', '4321', '31', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'm', 'm', 'modif'),
('999', 'Picot', 'Simon', '3213', '1111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ysimon', '1234', 'consult'),
('aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', NULL, NULL, NULL, NULL, 'aa', '$2a$10$.1TM/.Lp8y7VaXI0ElGMOeLR2w1gHyZbI.1gy4TWTJTWVCDV8Y2z2', 'adherent'),
('df', 'zed', 'ef', 'hfch', 'hgch', 'fxgfc', 'gfx', 'fc', NULL, 'fchf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('eez', 'aqz', 'eare', 'zsd', 'resd', 'esd', NULL, 'resd', NULL, 'esd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('fc', 'rdf', 'yd', 'hfc', 'hfc', 'hfc', NULL, 'hfc', NULL, 'hfc', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('g', 'f', 'f', 'g', 'g', 'g', NULL, 'g', NULL, 'g', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('gcfd', 'ytf', 'fh', 'qeez', 'ze', 'eswsw', 'd', 'dsw', NULL, 'sw', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('gf', 'trdtd', 'tdf', 'gfxc', 'gf', 'gfxc', NULL, 'gf', NULL, 'gf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('kjhk', 'sjg', 'kkuk', 'kjkj', 'kjhkj', 'hgv', NULL, 'gv', NULL, 'hgv', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('v', 'v', 'v', 'v', 'v', 'v', NULL, 'v', NULL, 'v', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adherents`
--
ALTER TABLE `adherents`
  ADD PRIMARY KEY (`numerolicence`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
