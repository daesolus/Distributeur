-- phpMyAdmin SQL Dump
-- version 4.4.14.1
-- http://www.phpmyadmin.net
--
-- Client :  localhost
-- Généré le :  Jeu 12 Novembre 2015 à 14:14
-- Version du serveur :  10.0.21-MariaDB
-- Version de PHP :  5.6.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bar`
--

-- --------------------------------------------------------

--
-- Structure de la table `bouteille`
--

CREATE TABLE IF NOT EXISTS `bouteille` (
  `id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `marque` varchar(255) NOT NULL,
  `qte` float NOT NULL,
  `numeroPompe` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `bouteille`
--

INSERT INTO `bouteille` (`id`, `type`, `marque`, `qte`, `numeroPompe`) VALUES
(22, 'Vino', 'Toro Loco', 40, 0),
(23, 'Jus orange', 'McCain', 25, 1),
(24, 'Liqueur lime', 'Seven Up', 20, 2);

-- --------------------------------------------------------

--
-- Structure de la table `bouteilleRecette`
--

CREATE TABLE IF NOT EXISTS `bouteilleRecette` (
  `idBouteille` int(11) NOT NULL,
  `idRecette` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `bouteilleRecette`
--

INSERT INTO `bouteilleRecette` (`idBouteille`, `idRecette`) VALUES
(22, 7),
(23, 7),
(24, 7);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `traite` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `commande`
--

INSERT INTO `commande` (`id`, `date`, `traite`) VALUES
(23, '2015-11-11', 0),
(24, '2015-11-11', 0),
(25, '2015-11-11', 0),
(26, '2015-11-11', 0),
(27, '2015-11-11', 0),
(28, '2015-11-11', 0),
(29, '2015-11-11', 0),
(30, '2015-11-11', 0),
(31, '2015-11-11', 0),
(32, '2015-11-11', 0),
(33, '2015-11-11', 0),
(34, '2015-11-12', 0),
(35, '2015-11-12', 0),
(36, '2015-11-12', 0),
(37, '2015-11-12', 0),
(38, '2015-11-12', 0),
(39, '2015-11-12', 0),
(40, '2015-11-12', 0);

-- --------------------------------------------------------

--
-- Structure de la table `commandeRecette`
--

CREATE TABLE IF NOT EXISTS `commandeRecette` (
  `idCommande` int(11) NOT NULL,
  `idRecette` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `commandeRecette`
--

INSERT INTO `commandeRecette` (`idCommande`, `idRecette`) VALUES
(23, 7),
(24, 7),
(25, 7),
(26, 7),
(27, 7),
(28, 7),
(29, 7),
(30, 7),
(31, 7),
(32, 7),
(33, 7),
(34, 7),
(35, 7),
(36, 7),
(37, 7),
(38, 7),
(39, 7),
(40, 7);

-- --------------------------------------------------------

--
-- Structure de la table `recette`
--

CREATE TABLE IF NOT EXISTS `recette` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `recette`
--

INSERT INTO `recette` (`id`, `nom`) VALUES
(7, 'Rape Juice');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `bouteille`
--
ALTER TABLE `bouteille`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `recette`
--
ALTER TABLE `recette`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `bouteille`
--
ALTER TABLE `bouteille`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT pour la table `recette`
--
ALTER TABLE `recette`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
