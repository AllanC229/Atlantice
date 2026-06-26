-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : ven. 26 juin 2026 à 08:36
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
  `motdepasse` varchar(72) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `role` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `tentativeconnexion` int NOT NULL DEFAULT '5',
  `changementmdp` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`numerolicence`, `nom`, `prenom`, `dernierelicenceactive`, `annee`, `tel1`, `tel2`, `adresse1`, `adresse2`, `mail1`, `mail2`, `commentaire`, `contact1`, `contact2`, `sexe`, `droitimage`, `motdepasse`, `role`, `tentativeconnexion`, `changementmdp`) VALUES
('00111000111', 'Modif', 'Modif', '000000', '1889', '3333', NULL, 'Avenue du Michelin', NULL, 'mod@mod.mod', NULL, 'Responsable de categorie', NULL, NULL, NULL, NULL, '$2a$10$wom4xBNl0MWBJL9qPyCB/.4u1usAYASuLttCA76iBfwIOpOXxZD0S', 'modif', 5, 1),
('1', 'Pigeon', 'Jeanineuuuuuh', '2012', '1977', '0298731221', '0000', 'Quimper', 'null', 'poitevinchrichri@test.fr', 'oui', 'ceci est un bon commentaire', 'null', 'null', 'Pain', 'non', '$2a$10$JKbuJ2cIs8d.ndHVhn6QsO9pRa6yg4aVEVci.snkQqXhTnO9Wu5Ly', 'admin', 5, 1),
('20106513152', 'Flamand', 'Jules', '2024/2025', '2010', '6515615', '513153531', 'njjnjn', 'tdrdtrccrf', 'vyutgtu', 'utytty', 'ttyty', 'tfyttyv', 'yttt', '1', '1', '', '0', 5, 0),
('2012654654654', 'Elissalde', 'Jean-Baptiste', '2024/2025', '2012', '535135135', '353513151', 'kbjkbkjkj', 'kjbkljj', 'jbljlnjnl', 'lnll', 'lnlnlkn', 'lnlnln', 'ljnlnlkn', '1', '1', '', '0', 5, 0),
('2012654654715', 'Lemarchand', 'Julien', '2024/2025', '2012', '65465451', '35035535', 'kjbkjkbkbj', 'jnhkbhvv', 'cftryctytc', 'trdycytyf', 'uvufuf', 'uhooiopihj', 'oihopop', '1', '1', '', '0', 5, 0),
('2013254895', 'Becker', 'Byron', '2024/2025', '2013', '068468', '0658435', 'iugkkh', 'jgcgcf', 'kyfjg', 'bkkj', 'hfxgd', 'tdxyfc', 'fchfc', '1', '1', '', '0', 5, 0),
('201425256', 'Durand', 'Laurine', '2024/2025', '2014', '65468', '6546846', 'jhvjhv', 'jgvjgv', 'jvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhb', '1', '1', '', '0', 5, 0),
('2014584654685', 'Dupont', 'Antoine', '2024/2025', '2014', '68464', '684654', 'jgvjhgbkhku', 'kjbkkbghvbjh', 'jhvjhvjhvkh', 'jhvkhbk', 'hbkbkjkb', 'hfcdghchfc', 'chytfhtf', '1', '1', '', '0', 5, 0),
('201525485241', 'Pichon', 'Vincent', '2024/2025', '2015', '6851513', '14131351135', 'jvhhb', 'gfxgfxc', 'jhgkgk', 'jhvkvh', 'jgcvjhv', 'jgckhkjb', 'khjbkjb', '1', '1', '', '0', 5, 0),
('20155446545', 'Aldritt', 'Grégorie', '2024/2025', '2015', '6513515531', 'null', 'bhliblkhbhkb', 'null', 'jguvkjhkjhb', 'null', 'rgregerrgrgg', NULL, NULL, NULL, NULL, '', '0', 5, 0),
('20176533', 'Penaud', 'Fabien', '2024/2025', '2017', '365135135', 'null', 'ibkkkkb', 'null', 'kbkjbbkjb', 'null', 'rgrtggg', NULL, NULL, NULL, NULL, '', '0', 5, 0),
('2017654654655', 'Kelleher', 'Byron', '2024/2025', '2017', '6565151', '351351351', 'bkjkjb', 'kjhbkbk', 'kjbkb', 'kjbkjb', 'kjbkjb', 'kjbkjb', 'jukj', '1', '1', '', '0', 5, 0),
('2345324', 'Jean', 'Dufoin', 'oui', 'non', '1234', NULL, 'oui', NULL, 'zre', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, 0),
('8888', 'Montour', 'Jean-Paul', '333', '11', NULL, NULL, NULL, NULL, 'm', NULL, NULL, NULL, NULL, NULL, NULL, '1234', 'modif', 5, 0),
('aa', 'aaa', 'aaa', 'aa', 'aa', 'aa', 'aa', 'aa', 'aa', 'jean@pain.com', 'aa', 'aa', NULL, NULL, NULL, NULL, '$2a$10$JKbuJ2cIs8d.ndHVhn6QsO9pRa6yg4aVEVci.snkQqXhTnO9Wu5Ly', 'adherent', 5, 1),
('aezfsefdgrrthret', 'zzazazazazazaz', 'efzefzef', 'zzzz', 'zzzz', 'zzz', NULL, 'zzz', NULL, 'zzzz@zzzz.zzz', NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$8dNii8vt/Va.NirMluIcN.T3g9whZ31NHbrU8Fq43FZBoc21mOUH6', 'adherent', 5, 0),
('ggg', 'ggg', 'ggg', 'ggg', 'ggg', 'ggg', 'ggg', 'ggg', 'ggg', 'ggg@ggg.ggg', NULL, 'ggg', NULL, NULL, NULL, NULL, '$2a$10$z.U7zcxKe3J9q2WWazQc/eJLfB5oMBuCxjf4n9b5XRFiYKGFruJsW', 'adherent', 5, 0),
('ljkpodjrtjdrpoit', 'bdsfgdrttr', 'zzzz', 'zzzz', 'zzzz', 'zzzz', NULL, 'zzzz', NULL, 'zzzz@zzzz.zzzz', NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$zwi.zJ.1/6Ptd3.CQ8amL.rz7LYxD0A6vNNNjVdrmwhZH3zgOwS5O', 'adherent', 5, 0),
('qqqq', 'ergzerd', 'qqqq', 'qqqq', 'qqqq', 'qqqq', NULL, 'qqqq', 'qq', 'qqqq@qqq.qqq', NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$Fqx.FI3TVJeZNJrOM9OD8.qmVHN3XokUQ/Ojad3JSj9GqICh0FsS.', 'adherent', 5, 1),
('v', 'v', 'v', 'v', 'v', 'v', NULL, 'v', NULL, 'v', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 5, 0),
('zzzzzazasasada', 'zzzzz', 'zzzz', 'zzzz', 'z', 'z', NULL, 'zzzzz', NULL, 'ggg@ggg.ggg', NULL, NULL, NULL, NULL, NULL, NULL, '$2a$10$/KB/PMzjMCO4MWRRegLexOyj0Ur5TTu4ArmmbGi2rPlKk24pJ1Yzu', 'adherent', 5, 0);

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
