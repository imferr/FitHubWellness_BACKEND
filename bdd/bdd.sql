-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-15 03:57:25.002

-- tables
-- Table: AUTHENTICATION

-- Table: CHATGPT_RESPONSE
CREATE TABLE CHATGPT_RESPONSE (
    chatId Serial  NOT NULL,
    question varchar(5000)  NOT NULL,
    response varchar(5000)  NOT NULL,
    EVALUATION_evaluationId Serial  NOT NULL,
    DAILY_TRAINING_dailyId Serial  NOT NULL,
    USERS_userId Serial  NOT NULL,
    CONSTRAINT CHATGPT_RESPONSE_pk PRIMARY KEY (chatId)
);

-- Table: DAILY_TRAINING
CREATE TABLE DAILY_TRAINING (
    dailyId Serial  NOT NULL,
    date date  NOT NULL,
    TYPE_TRAINING_typeTrainingId Serial  NOT NULL,
    USERS_userId Serial  NOT NULL,
    CONSTRAINT DAILY_TRAINING_pk PRIMARY KEY (dailyId)
);

-- Table: EVALUATION
CREATE TABLE EVALUATION (
    evaluationId Serial  NOT NULL,
    weight numeric(10,5)  NOT NULL,
    height int  NOT NULL,
    date date  NOT NULL,
    imc numeric(10,5)  NOT NULL,
    estado varchar(30)  NOT NULL,
    USERS_userId Serial  NOT NULL,
    CONSTRAINT EVALUATION_pk PRIMARY KEY (evaluationId)
);

-- Table: GOAL
CREATE TABLE GOAL (
    goalId Serial  NOT NULL,
    accomplished boolean  NOT NULL,
    quantity numeric(10,5)  NOT NULL,
    exerciseName varchar(500)  NOT NULL,
    date date  NOT NULL,
    accomplishedDate date  NULL,
    USERS_userId Serial  NOT NULL,
    TYPE_GOAL_typeGoalId Serial  NOT NULL,
    CONSTRAINT GOAL_pk PRIMARY KEY (goalId)
);

-- Table: PERSONAL_RECORD
CREATE TABLE PERSONAL_RECORD (
    personalRecordId Serial  NOT NULL,
    weight numeric(10,5)  NOT NULL,
    repetitions int  NOT NULL,
    date date  NOT NULL,
    exerciseName varchar(500)  NOT NULL,
    USERS_userId Serial  NOT NULL,
    CONSTRAINT PERSONAL_RECORD_pk PRIMARY KEY (personalRecordId)
);

-- Table: TYPE_GOAL
CREATE TABLE TYPE_GOAL (
    typeGoalId Serial  NOT NULL,
    type varchar(100)  NOT NULL,
    CONSTRAINT TYPE_GOAL_pk PRIMARY KEY (typeGoalId)
);

-- Table: TYPE_TRAINING
CREATE TABLE TYPE_TRAINING (
    typeTrainingId Serial  NOT NULL,
    type varchar(100)  NOT NULL,
    CONSTRAINT TYPE_TRAINING_pk PRIMARY KEY (typeTrainingId)
);

-- Table: USERS
CREATE TABLE USERS (
    userId Serial  NOT NULL,
    name varchar(100)  NOT NULL,
    email varchar(100)  NOT NULL,
    birthday date  NOT NULL,
    CONSTRAINT USERS_pk PRIMARY KEY (userId)
);

-- Table: LAST_EVALUATION
CREATE TABLE LAST_EVALUATION (
    lastEvaluationId Serial  NOT NULL,
    weight numeric(10,5)  NOT NULL,
    USERS_userId Serial  NOT NULL,
    CONSTRAINT LAST_EVALUATION_pk PRIMARY KEY (lastEvaluationId)
);

-- foreign keys

-- Reference: CHATGPT_RESPONSE_DAILY_TRAINING (table: CHATGPT_RESPONSE)
ALTER TABLE CHATGPT_RESPONSE ADD CONSTRAINT CHATGPT_RESPONSE_DAILY_TRAINING
    FOREIGN KEY (DAILY_TRAINING_dailyId)
    REFERENCES DAILY_TRAINING (dailyId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: CHATGPT_RESPONSE_EVALUATION (table: CHATGPT_RESPONSE)
ALTER TABLE CHATGPT_RESPONSE ADD CONSTRAINT CHATGPT_RESPONSE_EVALUATION
    FOREIGN KEY (EVALUATION_evaluationId)
    REFERENCES EVALUATION (evaluationId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: CHATGPT_RESPONSE_USERS (table: CHATGPT_RESPONSE)
ALTER TABLE CHATGPT_RESPONSE ADD CONSTRAINT CHATGPT_RESPONSE_USERS
    FOREIGN KEY (USERS_userId)
    REFERENCES USERS (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: DAILY_TRAINING_TYPE_TRAINING (table: DAILY_TRAINING)
ALTER TABLE DAILY_TRAINING ADD CONSTRAINT DAILY_TRAINING_TYPE_TRAINING
    FOREIGN KEY (TYPE_TRAINING_typeTrainingId)
    REFERENCES TYPE_TRAINING (typeTrainingId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: DAILY_TRAINING_USERS (table: DAILY_TRAINING)
ALTER TABLE DAILY_TRAINING ADD CONSTRAINT DAILY_TRAINING_USERS
    FOREIGN KEY (USERS_userId)
    REFERENCES USERS (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: EVALUATION_USERS (table: EVALUATION)
ALTER TABLE EVALUATION ADD CONSTRAINT EVALUATION_USERS
    FOREIGN KEY (USERS_userId)
    REFERENCES USERS (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: GOAL_TYPE_GOAL (table: GOAL)
ALTER TABLE GOAL ADD CONSTRAINT GOAL_TYPE_GOAL
    FOREIGN KEY (TYPE_GOAL_typeGoalId)
    REFERENCES TYPE_GOAL (typeGoalId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: GOAL_USERS (table: GOAL)
ALTER TABLE GOAL ADD CONSTRAINT GOAL_USERS
    FOREIGN KEY (USERS_userId)
    REFERENCES USERS (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: PERSONAL_RECORD_GOAL (table: PERSONAL_RECORD)
ALTER TABLE PERSONAL_RECORD ADD CONSTRAINT PERSONAL_RECORD_USERS
    FOREIGN KEY (USERS_userId)
    REFERENCES USERS (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: LAST_EVALUATION_USERS (table: LAST_EVALUATION)
ALTER TABLE LAST_EVALUATION ADD CONSTRAINT LAST_EVALUATION_USERS
    FOREIGN KEY (USERS_userId)
    REFERENCES USERS (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.









--TABLA HISTORICA EVALUATION

CREATE TABLE H_EVALUATION (
    evaluationId Serial  NOT NULL,
    weight numeric(10,5)  NOT NULL,
    height int  NOT NULL,
    date date  NOT NULL,
    imc numeric(10,5)  NOT NULL,
    estado varchar(30)  NOT NULL,
    aud_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    aud_user integer NOT NULL,
    CONSTRAINT H_EVALUATION_pk PRIMARY KEY (evaluationId, aud_date)
);

-- TABLA HISTORICA DAYLY_TRAINING

CREATE TABLE H_DAILY_TRAINING (
    dailyId Serial  NOT NULL,
    date date  NOT NULL,
    typeTrainingId integer NOT NULL,
    aud_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    aud_user integer NOT NULL,
    CONSTRAINT H_DAILY_TRAINING_pk PRIMARY KEY (dailyId, aud_date)
);



-- Trigger para la tabla EVALUATION
CREATE OR REPLACE FUNCTION evaluation_after_insert_or_update()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO H_EVALUATION (evaluationId, weight, height, date, imc, estado, aud_date, aud_user)
        VALUES (NEW.evaluationId, NEW.weight, NEW.height, NEW.date, NEW.imc, NEW.estado, CURRENT_TIMESTAMP, NEW.USERS_userId);
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO H_EVALUATION (evaluationId, weight, height, date, imc, estado, aud_date, aud_user)
        VALUES (NEW.evaluationId, NEW.weight, NEW.height, NEW.date, NEW.imc, NEW.estado, CURRENT_TIMESTAMP, NEW.USERS_userId);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER evaluation_after_insert_or_update
AFTER INSERT OR UPDATE ON EVALUATION
FOR EACH ROW
EXECUTE FUNCTION evaluation_after_insert_or_update();




-- Trigger para la tabla DAILY_TRAINING
CREATE OR REPLACE FUNCTION daily_training_after_insert_or_update()
RETURNS TRIGGER AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO H_DAILY_TRAINING (dailyId, date, typeTrainingId, aud_date, aud_user)
        VALUES (NEW.dailyId, NEW.date, NEW.TYPE_TRAINING_typeTrainingId, CURRENT_TIMESTAMP, NEW.USERS_userId);
    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO H_DAILY_TRAINING (dailyId, date, typeTrainingId, aud_date, aud_user)
        VALUES (NEW.dailyId, NEW.date, NEW.TYPE_TRAINING_typeTrainingId, CURRENT_TIMESTAMP, NEW.USERS_userId);
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER daily_training_after_insert_or_update
AFTER INSERT OR UPDATE ON DAILY_TRAINING
FOR EACH ROW
EXECUTE FUNCTION daily_training_after_insert_or_update();
