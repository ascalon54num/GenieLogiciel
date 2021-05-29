-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 29 mai 2021 à 13:41
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.4.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `restogestio`
--

-- --------------------------------------------------------

--
-- Structure de la table `affectation`
--

DROP TABLE IF EXISTS `affectation`;
CREATE TABLE IF NOT EXISTS `affectation` (
  `idAffectation` int(3) NOT NULL AUTO_INCREMENT,
  `idServeur` int(3) NOT NULL,
  `idTable` int(3) NOT NULL,
  PRIMARY KEY (`idAffectation`),
  KEY `fkServeur` (`idServeur`),
  KEY `FkTable` (`idTable`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `board`
--

DROP TABLE IF EXISTS `board`;
CREATE TABLE IF NOT EXISTS `board` (
  `idTable` int(3) NOT NULL AUTO_INCREMENT,
  `statut` varchar(10) NOT NULL,
  `avancementRepas` varchar(50) DEFAULT NULL,
  `nbCouvert` int(2) NOT NULL,
  PRIMARY KEY (`idTable`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `board`
--

INSERT INTO `board` (`idTable`, `statut`, `avancementRepas`, `nbCouvert`) VALUES
(1, 'Libre', 'Rien', 5),
(2, 'Libre', 'Rien', 6),
(3, 'Libre', 'Rien', 5);

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
CREATE TABLE IF NOT EXISTS `categorie` (
  `idCategorie` int(3) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  PRIMARY KEY (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `idCommande` int(3) NOT NULL AUTO_INCREMENT,
  `idTable` int(3) NOT NULL,
  `idStatutCommande` int(3) NOT NULL,
  `idService` int(3) NOT NULL,
  PRIMARY KEY (`idCommande`),
  KEY `idTableFk` (`idTable`),
  KEY `idStatutFk` (`idStatutCommande`),
  KEY `idServiceFk` (`idService`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `commande_plat`
--

DROP TABLE IF EXISTS `commande_plat`;
CREATE TABLE IF NOT EXISTS `commande_plat` (
  `idCommande` int(3) NOT NULL,
  `idPlat` int(3) NOT NULL,
  `quantite` int(3) NOT NULL DEFAULT 0,
  `etat` varchar(20) NOT NULL,
  KEY `idCommandeFk` (`idCommande`),
  KEY `idPlatFk` (`idPlat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
CREATE TABLE IF NOT EXISTS `ingredient` (
  `idIngredient` int(3) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  `quantite` int(10) NOT NULL,
  PRIMARY KEY (`idIngredient`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `ingredient_plat`
--

DROP TABLE IF EXISTS `ingredient_plat`;
CREATE TABLE IF NOT EXISTS `ingredient_plat` (
  `idIngredient` int(3) NOT NULL,
  `idPlat` int(3) NOT NULL,
  `quantite` int(4) NOT NULL DEFAULT 0,
  KEY `idIngredientFk` (`idIngredient`),
  KEY `idPlatFk` (`idPlat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `plat`
--

DROP TABLE IF EXISTS `plat`;
CREATE TABLE IF NOT EXISTS `plat` (
  `idPlat` int(3) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  `prix` double NOT NULL,
  `isPlatDuJour` tinyint(1) NOT NULL DEFAULT 0,
  `idCategorie` int(3) NOT NULL,
  `isDisponible` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idPlat`),
  KEY `idCategorie` (`idCategorie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `idRole` int(3) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`idRole`, `libelle`, `isAdmin`) VALUES
(1, 'DIRECTOR', 1),
(2, 'HOTELMASTER', 0),
(3, 'SERVEUR', 0),
(4, 'COOK', 0),
(5, 'SERVICE_ASSISTANT', 0);

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `idService` int(3) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(20) NOT NULL,
  `dateDebut` date NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`idService`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `statutcommande`
--

DROP TABLE IF EXISTS `statutcommande`;
CREATE TABLE IF NOT EXISTS `statutcommande` (
  `idStatutCommande` int(3) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(10) NOT NULL,
  PRIMARY KEY (`idStatutCommande`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `statutcommande`
--

INSERT INTO `statutcommande` (`idStatutCommande`, `libelle`) VALUES
(1, 'EMISE'),
(2, 'EN COURS'),
(3, 'FACTUREE');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int(3) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `idRole` int(3) NOT NULL,
  PRIMARY KEY (`idUtilisateur`),
  KEY `idRole` (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `login`, `nom`, `prenom`, `idRole`) VALUES
(1, 'louisM', 'Martinez', 'Louis', 1),
(2, 'annaV', 'Vallence', 'Anna', 2),
(3, 'johnR', 'Reeves', 'John', 3),
(4, 'heleneP', 'Penwood', 'Helene', 4),
(5, 'amyW', 'Wynna', 'Amy', 5);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `affectation`
--
ALTER TABLE `affectation`
  ADD CONSTRAINT `fkServeur` FOREIGN KEY (`idServeur`) REFERENCES `utilisateur` (`idUtilisateur`),
  ADD CONSTRAINT `fkTableAffected` FOREIGN KEY (`idTable`) REFERENCES `board` (`idTable`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `fk_service` FOREIGN KEY (`idService`) REFERENCES `service` (`idService`),
  ADD CONSTRAINT `fk_statut` FOREIGN KEY (`idStatutCommande`) REFERENCES `statutcommande` (`idStatutCommande`),
  ADD CONSTRAINT `fk_table` FOREIGN KEY (`idTable`) REFERENCES `board` (`idTable`);

--
-- Contraintes pour la table `commande_plat`
--
ALTER TABLE `commande_plat`
  ADD CONSTRAINT `fk_commande` FOREIGN KEY (`idCommande`) REFERENCES `commande` (`idCommande`),
  ADD CONSTRAINT `fk_plat` FOREIGN KEY (`idPlat`) REFERENCES `plat` (`idPlat`);

--
-- Contraintes pour la table `ingredient_plat`
--
ALTER TABLE `ingredient_plat`
  ADD CONSTRAINT `fk_ingredient` FOREIGN KEY (`idIngredient`) REFERENCES `ingredient` (`idIngredient`),
  ADD CONSTRAINT `fk_plat2` FOREIGN KEY (`idPlat`) REFERENCES `plat` (`idPlat`);

--
-- Contraintes pour la table `plat`
--
ALTER TABLE `plat`
  ADD CONSTRAINT `fk_categorie` FOREIGN KEY (`idCategorie`) REFERENCES `categorie` (`idCategorie`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `fk_role` FOREIGN KEY (`idRole`) REFERENCES `role` (`idRole`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
