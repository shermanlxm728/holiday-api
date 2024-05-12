INSERT INTO country VALUES (1, 'USA', 'United States');
INSERT INTO country VALUES (2,  'CAN', 'Canada');


-- -- Insert holidays
INSERT INTO holiday (name, date, country_id) VALUES ('New Year Day', '2023-01-01', (SELECT id FROM country WHERE code = 'USA'));
INSERT INTO holiday (name, date, country_id) VALUES ('Canada Day', '2023-07-01', (SELECT id FROM country WHERE code = 'CAN'));


