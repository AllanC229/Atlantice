-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mer. 18 mars 2026 à 12:26
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
  `sexe` tinyint(1) DEFAULT NULL,
  `droitimage` tinyint(1) DEFAULT NULL,
  `login` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `motdepasse` varchar(50) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `role` varchar(25) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `adherents`
--

INSERT INTO `adherents` (`numerolicence`, `nom`, `prenom`, `dernierelicenceactive`, `annee`, `tel1`, `tel2`, `adresse1`, `adresse2`, `mail1`, `mail2`, `commentaire`, `contact1`, `contact2`, `sexe`, `droitimage`, `login`, `motdepasse`, `role`) VALUES
('009090', 'p', 'p', 'p', 'p', 'p', NULL, 'p', NULL, 'p', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('123', 'Helias', 'Georges', '1234', '12', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'a', 'a', 'admin'),
('12340000000000', 'Poitevin', 'Christiane', '2012', '1977', '0298731221', NULL, 'Quimper', NULL, 'poitevinchrichri@test.fr', NULL, 'test', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('20106513152', 'Flamand', 'Jules', '2024/2025', '2010', '6515615', '513153531', 'njjnjn', 'tdrdtrccrf', 'vyutgtu', 'utytty', 'ttyty', 'tfyttyv', 'yttt', 1, 1, '', '', '0'),
('201165165151', 'Ntamack', 'Dubois', '2024/2025', '2011', '35451', '5135135', 'kbkjn', 'jkkb', 'jkbkj', 'jnjnkj', 'ljnlnln', 'ljnln', 'ljbnljn', 1, 1, '', '', '0'),
('2012654654654', 'Elissalde', 'Jean-Baptiste', '2024/2025', '2012', '535135135', '353513151', 'kbjkbkjkj', 'kjbkljj', 'jbljlnjnl', 'lnll', 'lnlnlkn', 'lnlnln', 'ljnlnlkn', 1, 1, '', '', '0'),
('2012654654715', 'Lemarchand', 'Julien', '2024/2025', '2012', '65465451', '35035535', 'kjbkjkbkbj', 'jnhkbhvv', 'cftryctytc', 'trdycytyf', 'uvufuf', 'uhooiopihj', 'oihopop', 1, 1, '', '', '0'),
('2013254895', 'Becker', 'Byron', '2024/2025', '2013', '068468', '0658435', 'iugkkh', 'jgcgcf', 'kyfjg', 'bkkj', 'hfxgd', 'tdxyfc', 'fchfc', 1, 1, '', '', '0'),
('201425256', 'Durand', 'Laurine', '2024/2025', '2014', '65468', '6546846', 'jhvjhv', 'jgvjgv', 'jvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhv', 'jhvjhb', 1, 1, '', '', '0'),
('2014584654685', 'Dupont', 'Antoine', '2024/2025', '2014', '68464', '684654', 'jgvjhgbkhku', 'kjbkkbghvbjh', 'jhvjhvjhvkh', 'jhvkhbk', 'hbkbkjkb', 'hfcdghchfc', 'chytfhtf', 1, 1, '', '', '0'),
('201525485241', 'Pichon', 'Vincent', '2024/2025', '2015', '6851513', '14131351135', 'jvhhb', 'gfxgfxc', 'jhgkgk', 'jhvkvh', 'jgcvjhv', 'jgckhkjb', 'khjbkjb', 1, 1, '', '', '0'),
('20155446545', 'Aldritt', 'Grégorie', '2024/2025', '2015', '6513515531', 'null', 'bhliblkhbhkb', 'null', 'jguvkjhkjhb', 'null', 'rgregerrgrgg', NULL, NULL, NULL, NULL, '', '', '0'),
('20176533', 'Penaud', 'Fabien', '2024/2025', '2017', '365135135', 'null', 'ibkkkkb', 'null', 'kbkjbbkjb', 'null', 'rgrtggg', NULL, NULL, NULL, NULL, '', '', '0'),
('2017654654655', 'Kelleher', 'Byron', '2024/2025', '2017', '6565151', '351351351', 'bkjkjb', 'kjhbkbk', 'kjbkb', 'kjbkjb', 'kjbkjb', 'kjbkjb', 'jukj', 1, 1, '', '', '0'),
('21', 'x', 'x', 'x', 'x', 'x', NULL, 'x', NULL, 'x', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('2345324', 'Jean', 'Dufoin', 'oui', 'non', '1234', NULL, 'oui', NULL, 'zre', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('4312761', 'a', 'a', 'oui', 'oui', 'oui', NULL, 'oui', NULL, 'oui', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('43265', 'q', 'q', 'q', 'q', 'q', NULL, 'q', NULL, 'q', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('5376', 'i', 'i', 'i', 'i', 'i', NULL, 'i', NULL, 'i', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('777', 'Consult', 'Consult', '563456236', '1', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'c', 'c', 'consult'),
('786', 't', 't', 'oui', 'oui', 'oui', NULL, 'oui', NULL, 'oui', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('8888', 'Montour', 'Jean-Paul', '333', '11', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'JPMonour', '1234', 'modif'),
('957', 'Modif', 'Modif', '4321', '31', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'm', 'm', 'modif'),
('999', 'Picot', 'Simon', '3213', '1111', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'ysimon', '1234', 'modif'),
('df', 'zed', 'ef', 'hfch', 'hgch', 'fxgfc', 'gfx', 'fc', NULL, 'fchf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('eez', 'aqz', 'eare', 'zsd', 'resd', 'esd', NULL, 'resd', NULL, 'esd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('fc', 'rdf', 'yd', 'hfc', 'hfc', 'hfc', NULL, 'hfc', NULL, 'hfc', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('g', 'f', 'f', 'g', 'g', 'g', NULL, 'g', NULL, 'g', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('gcfd', 'ytf', 'fh', 'qeez', 'ze', 'eswsw', 'd', 'dsw', NULL, 'sw', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('gf', 'trdtd', 'tdf', 'gfxc', 'gf', 'gfxc', NULL, 'gf', NULL, 'gf', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('kjhk', 'sjg', 'kkuk', 'kjkj', 'kjhkj', 'hgv', NULL, 'gv', NULL, 'hgv', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', 'o', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
('v', 'v', 'v', 'v', 'v', 'v', NULL, 'v', NULL, 'v', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
('5762543', '5'),
('12340000000000', '11');

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
  `numerolicence` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `valcritere` int UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `critereadherent`
--

INSERT INTO `critereadherent` (`idcritere`, `numerolicence`, `valcritere`) VALUES
(1, '009090', NULL),
(2, '009090', NULL),
(3, '009090', NULL),
(4, '009090', NULL),
(5, '009090', NULL),
(6, '009090', NULL),
(1, '123', 5),
(2, '123', 5),
(3, '123', 5),
(4, '123', 5),
(5, '123', 5),
(6, '123', 5),
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
(1, '21', NULL),
(2, '21', NULL),
(3, '21', NULL),
(4, '21', NULL),
(5, '21', NULL),
(6, '21', NULL),
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
(1, '777', NULL),
(2, '777', NULL),
(3, '777', NULL),
(4, '777', NULL),
(5, '777', NULL),
(6, '777', NULL),
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
(6, 'critere6');

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
  MODIFY `idcritere` int UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `critereadherent`
--
ALTER TABLE `critereadherent`
  ADD CONSTRAINT `critereadherent_ibfk_1` FOREIGN KEY (`idcritere`) REFERENCES `criteres` (`idcritere`),
  ADD CONSTRAINT `critereadherent_ibfk_2` FOREIGN KEY (`numerolicence`) REFERENCES `adherents` (`numerolicence`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
