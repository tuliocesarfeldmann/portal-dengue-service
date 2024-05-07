CREATE SCHEMA IF NOT EXISTS `portal_dengue` DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `portal_dengue`.`informativos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(1024) NOT NULL,
  `caminho_imagem` VARCHAR(512) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `portal_dengue`.`usuarios` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(11) NOT NULL,
  `hash` VARCHAR(512) NOT NULL,
  `salt` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(128) NOT NULL,
  `email` VARCHAR(320) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `portal_dengue`.`situacao_pontos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `portal_dengue`.`pontos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `descricao` VARCHAR(512) NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  `id_situacao` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pontos_situacao_pontos1_idx` (`id_situacao` ASC) VISIBLE,
  CONSTRAINT `fk_pontos_situacao_pontos1`
    FOREIGN KEY (`id_situacao`)
    REFERENCES `portal_dengue`.`situacao_pontos` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `portal_dengue`.`detalhes_pontos` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_ponto` BIGINT(20) NOT NULL,
  `rua` VARCHAR(512) NULL DEFAULT NULL,
  `numero` INT(11) NULL DEFAULT NULL,
  `bairro` VARCHAR(512) NOT NULL,
  `cidade` VARCHAR(512) NOT NULL,
  `estado` VARCHAR(512) NOT NULL,
  `cep` VARCHAR(9) NOT NULL,
  `pais` VARCHAR(512) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_detalhes_pontos_pontos1_idx` (`id_ponto` ASC) VISIBLE,
  CONSTRAINT `fk_detalhes_pontos_pontos1`
    FOREIGN KEY (`id_ponto`)
    REFERENCES `portal_dengue`.`pontos` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `portal_dengue`.`acoes_corretivas` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `pontos_id` BIGINT(20) NOT NULL,
  `data_correcao` DATE NOT NULL,
  `descricao` VARCHAR(512) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_acoes_corretivas_pontos1_idx` (`pontos_id` ASC) VISIBLE,
  CONSTRAINT `fk_acoes_corretivas_pontos1`
    FOREIGN KEY (`pontos_id`)
    REFERENCES `portal_dengue`.`pontos` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;