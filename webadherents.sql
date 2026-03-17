-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mar. 17 mars 2026 à 09:13
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
  `numerolicence` varchar(20) NOT NULL,
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
  `sexe` tinyint(1) DEFAULT NULL,
  `droitimage` tinyint(1) DEFAULT NULL,
  `login` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `motdepasse` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `role` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`numerolicence`, `nom`, `prenom`, `dernierelicenceactive`, `annee`, `tel1`, `tel2`, `adresse1`, `adresse2`, `mail1`, `mail2`, `commentaire`, `contact1`, `contact2`, `sexe`, `droitimage`, `login`, `motdepasse`, `role`) VALUES
('201525485241', 'Pichon', 'Vincent', '2024/2025', '2015', '6851513', '14131351135', 'jvhhb', 'gfxgfxc', 'jhgkgk', 'jhvkvh', 'jgcvjhv', 'jgckhkjb', 'khjbkjb', 1, 1, '', '', '0'),
('2014584654685', 'Dupont', 'Antoine', '2024/2025', '2014', '68464', '684654', 'jgvjhgbkhku', 'kjbkkbghvbjh', 'jhvjhvjhvkh', 'jhvkhbk', 'hbkbkjkb', 'hfcdghchfc', 'chytfhtf', 1, 1, '', '', '0'),
('2013254895', 'Becker', 'Byron', '2024/2025', '2013', '068468', '0658435', 'iugkkh', 'jgcgcf', 'kyfjg', 'bkkj', 'hfxgd', 'tdxyfc', 'fchfc', 1, 1, '', '', '0'),
('201425256', 'Durand', 'Laurine', '2024/2025', '2014', '65468', '6546846', 'jhvjhv', 'jgvjgv', 'jvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhb', 1, 1, '', '', '0'),
('20155446545', 'Aldritt', 'Grégorie', '2024/2025', '2015', '6513515531', 'null', 'bhliblkhbhkb', 'null', 'jguvkjhkjhb', 'null', 'rgregerrgrgg', NULL, NULL, NULL, NULL, '', '', '0'),
('20176533', 'Penaud', 'Fabien', '2024/2025', '2017', '365135135', 'null', 'ibkkkkb', 'null', 'kbkjbbkjb', 'null', 'rgrtggg', NULL, NULL, NULL, NULL, '', '', '0'),
('2012654654654', 'Elissalde', 'Jean-Baptiste', '2024/2025', '2012', '535135135', '353513151', 'kbjkbkjkj', 'kjbkljj', 'jbljlnjnl', 'lnll', 'lnlnlkn', 'lnlnln', 'ljnlnlkn', 1, 1, '', '', '0'),
('201165165151', 'Ntamack', 'Dubois', '2024/2025', '2011', '35451', '5135135', 'kbkjn', 'jkkb', 'jkbkj', 'jnjnkj', 'ljnlnln', 'ljnln', 'ljbnljn', 1, 1, '', '', '0'),
('2012654654715', 'Lemarchand', 'Julien', '2024/2025', '2012', '65465451', '35035535', 'kjbkjkbkbj', 'jnhkbhvv', 'cftryctytc', 'trdycytyf', 'uvufuf', 'uhooiopihj', 'oihopop', 1, 1, '', '', '0'),
('20106513152', 'Flamand', 'Jules', '2024/2025', '2010', '6515615', '513153531', 'njjnjn', 'tdrdtrccrf', 'vyutgtu', 'utytty', 'ttyty', 'tfyttyv', 'yttt', 1, 1, '', '', '0'),
('2017654654655', 'Kelleher', 'Byron', '2024/2025', '2017', '6565151', '351351351', 'bkjkjb', 'kjhbkbk', 'kjbkb', 'kjbkjb', 'kjbkjb', 'kjbkjb', 'jukj', 1, 1, '', '', '0'),
('999', 'Picot', 'Simon', '3213', '1111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ysimon', '1234', 'modif'),
('123', 'Helias', 'Georges', '1234', '12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'a', 'a', 'admin'),
('957', 'Modif', 'Modif', '4321', '31', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'm', 'm', 'modif'),
('777', 'Consult', 'Consult', '563456236', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'c', 'c', 'consult'),
('8888', 'Montour', 'Jean-Paul', '333', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'JPMonour', '1234', 'modif'),
('2345324', 'Jean', 'Dufoin', 'oui', 'non', '1234', NULL, 'oui', NULL, 'zre', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('4312761', 'a', 'a', 'oui', 'oui', 'oui', NULL, 'oui', NULL, 'oui', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('786', 't', 't', 'oui', 'oui', 'oui', NULL, 'oui', NULL, 'oui', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('5376', 'i', 'i', 'i', 'i', 'i', NULL, 'i', NULL, 'i', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('43265', 'q', 'q', 'q', 'q', 'q', NULL, 'q', NULL, 'q', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('009090', 'p', 'p', 'p', 'p', 'p', NULL, 'p', NULL, 'p', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('21', 'x', 'x', 'x', 'x', 'x', NULL, 'x', NULL, 'x', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('v', 'v', 'v', 'v', 'v', 'v', NULL, 'v', NULL, 'v', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('g', 'f', 'f', 'g', 'g', 'g', NULL, 'g', NULL, 'g', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('df', 'zed', 'ef', 'hfch', 'hgch', 'fxgfc', 'gfx', 'fc', NULL, 'fchf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('eez', 'aqz', 'eare', 'zsd', 'resd', 'esd', NULL, 'resd', NULL, 'esd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('gf', 'trdtd', 'tdf', 'gfxc', 'gf', 'gfxc', NULL, 'gf', NULL, 'gf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('fc', 'rdf', 'yd', 'hfc', 'hfc', 'hfc', NULL, 'hfc', NULL, 'hfc', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('gcfd', 'ytf', 'fh', 'qeez', 'ze', 'eswsw', 'd', 'dsw', NULL, 'sw', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('kjhk', 'sjg', 'kkuk', 'kjkj', 'kjhkj', 'hgv', NULL, 'gv', NULL, 'hgv', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `anneecategorie`
--

CREATE TABLE `anneecategorie` (
  `categories` varchar(30) NOT NULL,
  `annee` varchar(5) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
  `numLic` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  `idcategorie` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
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
('123', '4'),
('123', '5'),
('123', '15'),
('123', '16'),
('957', '4'),
('957', '5'),
('777', '5'),
('8888', '4'),
('8888', '5'),
('8888', '17'),
('12', '4'),
('5762543', '5');

-- --------------------------------------------------------

--
-- Structure de la table `categoriesportive`
--

CREATE TABLE `categoriesportive` (
  `idcategorie` int UNSIGNED NOT NULL,
  `nomcategorie` varchar(50) COLLATE utf8mb4_general_ci NOT NULL
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `categorieutilisateur`
--

INSERT INTO `categorieutilisateur` (`idUser`, `categorieUser`) VALUES
(123, 'M12'),
(957, 'Baby'),
(777, 'Baby'),
(8888, 'Kozs'),
(999, 'M08');

-- --------------------------------------------------------

--
-- Structure de la table `critereadherent`
--

CREATE TABLE `critereadherent` (
  `numerolicence` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `idCritere` int UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='table de jointure entre critere et adherent';

--
-- Déchargement des données de la table `critereadherent`
--

INSERT INTO `critereadherent` (`numerolicence`, `idCritere`) VALUES
('20106513152', 1),
('201165165151', 2),
('2012654654654', 3),
('2012654654715', 4),
('2013254895', 5),
('201425256', 6),
('2014584654685', 7),
('201525485241', 8),
('20155446545', 9),
('20176533', 10),
('2017654654655', 11);

-- --------------------------------------------------------

--
-- Structure de la table `criteres`
--

CREATE TABLE `criteres` (
  `idCritere` int NOT NULL,
  `niveauphysique` int DEFAULT NULL,
  `critere1` int DEFAULT NULL,
  `critere2` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `criteres`
--

INSERT INTO `criteres` (`idCritere`, `niveauphysique`, `critere1`, `critere2`) VALUES
(1, NULL, NULL, NULL),
(2, NULL, NULL, NULL),
(3, NULL, NULL, NULL),
(4, NULL, NULL, NULL),
(5, NULL, NULL, NULL),
(6, NULL, NULL, NULL),
(7, NULL, NULL, NULL),
(8, NULL, NULL, NULL),
(9, NULL, NULL, NULL),
(10, NULL, NULL, NULL),
(11, NULL, NULL, NULL);

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
  ADD PRIMARY KEY (`idCritere`,`numerolicence`);

--
-- Index pour la table `criteres`
--
ALTER TABLE `criteres`
  ADD PRIMARY KEY (`idCritere`);

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
-- AUTO_INCREMENT pour la table `critereadherent`
--
ALTER TABLE `critereadherent`
  MODIFY `idCritere` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
