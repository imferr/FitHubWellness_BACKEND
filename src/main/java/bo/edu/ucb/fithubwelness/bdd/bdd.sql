-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-15 03:00:20.61

-- tables
-- Table: AUTHENTICATION
CREATE TABLE AUTHENTICATION (
    authId Serial  NOT NULL,
    token varchar(100)  NOT NULL,
    expirationDate timestamp  NOT NULL,
    USERS_userId Serial  NOT NULL,
    CONSTRAINT AUTHENTICATION_pk PRIMARY KEY (authId)
);

-- Table: CHATGPT_RESPONSE
CREATE TABLE CHATGPT_RESPONSE (
    chatId Serial  NOT NULL,
    response varchar(5000)  NOT NULL,
    EVALUATION_evaluationId Serial  NOT NULL,
    DAILY_TRAINING_dailyId Serial  NOT NULL,
    CONSTRAINT CHATGPT_RESPONSE_pk PRIMARY KEY (chatId)
);

-- Table: DAILY_TRAINING
CREATE TABLE DAILY_TRAINING (
    dailyId Serial  NOT NULL,
    date timestamp  NOT NULL,
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
    USERS_userId serial  NOT NULL,
    CONSTRAINT EVALUATION_pk PRIMARY KEY (evaluationId)
);

-- Table: EXERCISE
CREATE TABLE EXERCISE (
    exerciseId Serial  NOT NULL,
    name varchar(100)  NOT NULL,
    description varchar(500)  NOT NULL,
    CONSTRAINT EXERCISE_pk PRIMARY KEY (exerciseId)
);

-- Table: GOAL
CREATE TABLE GOAL (
    goalId Serial  NOT NULL,
    description varchar(500)  NOT NULL,
    accomplished boolean  NOT NULL,
    quantity numeric(10,5)  NOT NULL,
    USERS_userId Serial  NOT NULL,
    TYPE_GOAL_typeGoalId Serial  NOT NULL,
    EXERCISE_exerciseId Serial  NOT NULL,
    CONSTRAINT GOAL_pk PRIMARY KEY (goalId)
);

-- Table: PERSONAL_RECORD
CREATE TABLE PERSONAL_RECORD (
    personalRecordId Serial  NOT NULL,
    weight numeric(10,5)  NOT NULL,
    repetitions int  NOT NULL,
    date date  NOT NULL,
    GOAL_goalId Serial  NOT NULL,
    CONSTRAINT PERSONAL_RECORD_pk PRIMARY KEY (personalRecordId)
);

-- Table: TYPE_GOAL
CREATE TABLE TYPE_GOAL (
    typeGoalId Serial  NOT NULL,
    type varchar(30)  NOT NULL,
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

-- foreign keys
-- Reference: AUTHENTICATION_USERS (table: AUTHENTICATION)
ALTER TABLE AUTHENTICATION ADD CONSTRAINT AUTHENTICATION_USERS
    FOREIGN KEY (USERS_userId)
    REFERENCES USERS (userId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

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

-- Reference: GOAL_EXERCISE (table: GOAL)
ALTER TABLE GOAL ADD CONSTRAINT GOAL_EXERCISE
    FOREIGN KEY (EXERCISE_exerciseId)
    REFERENCES EXERCISE (exerciseId)  
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
ALTER TABLE PERSONAL_RECORD ADD CONSTRAINT PERSONAL_RECORD_GOAL
    FOREIGN KEY (GOAL_goalId)
    REFERENCES GOAL (goalId)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

