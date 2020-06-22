INSERT INTO USERS(name, email, phone, password) VALUES('André Tondin', 'andre.tondin@email.com', '1152526565', '123456');
INSERT INTO USERS(name, email, phone, password) VALUES('José Mario', 'jose.mario@email.com', '1198985252', '123456');
INSERT INTO USERS(name, email, phone, password) VALUES('Marcia da Silva', 'marcia.silva@email.com', '1114145252', '123456');

INSERT INTO EVENTS(dt_Event, title, description, user_owner_id) VALUES('2020-07-01', 'Aniversário José', 'Dia primeiro de Julho é aniversário do José', 1);
INSERT INTO EVENTS(dt_Event, title, description, user_owner_id) VALUES('2020-07-10', 'Encontro TCC', 'Discução do projeto', 3);

INSERT INTO EVENTS_USERS_PARTICIPANTS(events_id, users_participants_id) VALUES(1, 1); 
INSERT INTO EVENTS_USERS_PARTICIPANTS(events_id, users_participants_id) VALUES(1, 3);
INSERT INTO EVENTS_USERS_PARTICIPANTS(events_id, users_participants_id) VALUES(2, 1);
INSERT INTO EVENTS_USERS_PARTICIPANTS(events_id, users_participants_id) VALUES(2, 2);
INSERT INTO EVENTS_USERS_PARTICIPANTS(events_id, users_participants_id) VALUES(2, 3);
