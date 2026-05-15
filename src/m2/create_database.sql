
DROP SCHEMA IF EXISTS dominion;
CREATE SCHEMA dominion COLLATE = utf8_general_ci;
USE dominion;

-- =========================================================
-- TABLA: CIVILIZATION MODULARIZADA
-- =========================================================
CREATE TABLE CIVILIZATION (
    civilization_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    
    wood_amount INT UNSIGNED NOT NULL DEFAULT 0,
    iron_amount INT UNSIGNED NOT NULL DEFAULT 0,
    food_amount INT UNSIGNED NOT NULL DEFAULT 0,
    mana_amount INT UNSIGNED NOT NULL DEFAULT 0,

    magic_tower_amount INT UNSIGNED NOT NULL DEFAULT 0,
    church_amount INT UNSIGNED NOT NULL DEFAULT 0,
    farm_amount INT UNSIGNED NOT NULL DEFAULT 0,
    smithy_amount INT UNSIGNED NOT NULL DEFAULT 0,
    carpentry_amount INT UNSIGNED NOT NULL DEFAULT 0,

    technology_defense_level INT UNSIGNED NOT NULL DEFAULT 0,
    technology_attack_level INT UNSIGNED NOT NULL DEFAULT 0,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NULL,

    PRIMARY KEY (civilization_id)
);

-- =========================================================
-- TABLA: BATTLE
-- =========================================================
CREATE TABLE BATTLE (
    battle_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    civilization_id BIGINT UNSIGNED NOT NULL,
    num_battle BIGINT UNSIGNED NOT NULL,
    winner ENUM('civilization', 'tie', 'enemy') NOT NULL,
    wood_waste INT UNSIGNED NOT NULL DEFAULT 0,
    iron_waste INT UNSIGNED NOT NULL DEFAULT 0,
    log_text LONGTEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50) NOT NULL,

    PRIMARY KEY (battle_id),
    CONSTRAINT fk_battle_civilization FOREIGN KEY (civilization_id) REFERENCES CIVILIZATION(civilization_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- =========================================================
-- TABLA: UNIT
-- =========================================================

CREATE TABLE UNIT_TYPE (
    unit_type_id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE,

    PRIMARY KEY (unit_type_id)
);

CREATE TABLE UNIT (
    unit_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    civilization_id BIGINT UNSIGNED NOT NULL,
    unit_type_id INT UNSIGNED NOT NULL,
    initialArmor INT UNSIGNED NOT NULL,
    armor INT UNSIGNED NOT NULL,
    baseDamage INT UNSIGNED NOT NULL,
    experience INT UNSIGNED NOT NULL DEFAULT 0,
    sanctified ENUM('NOT_APPLICABLE', 'TRUE', 'FALSE') NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP,
    updated_by VARCHAR(50) NULL,

    PRIMARY KEY (unit_id),
    CONSTRAINT fk_unit_civilization FOREIGN KEY (civilization_id) REFERENCES CIVILIZATION(civilization_id),
    CONSTRAINT fk_unit_type FOREIGN KEY (unit_type_id) REFERENCES UNIT_TYPE(unit_type_id)
);

-- =========================================================
-- TABLA: UNIT_GROUP
-- =========================================================
CREATE TABLE UNIT_GROUP (
    battle_id BIGINT UNSIGNED NOT NULL,
    side ENUM('civilization', 'enemy') NOT NULL,
    unit_type_id INT UNSIGNED NOT NULL,
    initial_amount INT UNSIGNED NOT NULL DEFAULT 0,
    drops INT UNSIGNED NOT NULL DEFAULT 0,

    PRIMARY KEY (battle_id, side, unit_type_id),
    CONSTRAINT fk_unit_group_battle FOREIGN KEY (battle_id) REFERENCES BATTLE(battle_id),
    CONSTRAINT fk_unit_group_type FOREIGN KEY (unit_type_id) REFERENCES UNIT_TYPE(unit_type_id)
);

-- =========================================================
-- TABLA: LOSSES
-- =========================================================
CREATE TABLE BATTLE_RESOURCE_LOSSES (
    battle_id BIGINT UNSIGNED NOT NULL,
    side ENUM('civilization','enemy') NOT NULL,
    initial_iron_amount INT UNSIGNED NOT NULL DEFAULT 0,
    initial_wood_amount INT UNSIGNED NOT NULL DEFAULT 0,
    initial_food_amount INT UNSIGNED NOT NULL DEFAULT 0,
    iron_losses INT UNSIGNED NOT NULL DEFAULT 0,
    wood_losses INT UNSIGNED NOT NULL DEFAULT 0,
    food_losses INT UNSIGNED NOT NULL DEFAULT 0,

    PRIMARY KEY (battle_id, side),
    CONSTRAINT fk_resource_losses_battle FOREIGN KEY (battle_id) REFERENCES BATTLE(battle_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

INSERT INTO UNIT_TYPE (name)
VALUES 
    ('swordsman'),
    ('spearman'),
    ('crossbow'),
    ('cannon'),
    ('arrow_tower'),
    ('catapult'),
    ('rocket_launcher'),
    ('magician'),
    ('priest');