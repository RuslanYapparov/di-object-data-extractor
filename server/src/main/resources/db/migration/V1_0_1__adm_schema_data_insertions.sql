INSERT INTO adm.central_directorates (central_directorate_abbreviation, central_directorate_name)
  VALUES ('ЦДИ', 'Центральная дирекция инфраструктуры'),
  ('ЦД', 'Центральная дирекция управления движением');


INSERT INTO adm.central_departments (central_department_abbreviation, central_department_name, central_directorate)
  VALUES ('ЦП', 'Управление пути и сооружений', 'ЦДИ'),
  ('ЦШ', 'Управление автоматики и телемеханики', 'ЦДИ');


INSERT INTO adm.railways (railway_abbreviation, railway_name)
  VALUES ('ВЗЖД', 'Восточно-западная железная дорога');


INSERT INTO adm.railway_territorial_departments (railway_territorial_department_abbreviation,
                                                 railway_territorial_department_name,
                                                 railway)
  VALUES ('НЗтер-1', 'Где-нибудьское территориальное управление', 'ВЗЖД');


INSERT INTO adm.regional_directorates (regional_directorate_abbreviation,
                                       regional_directorate_name,
                                       central_directorate)
  VALUES ('ВЗДИ', 'Восточно-западная дирекция инфраструктуры', 'ЦДИ'),
  ('ВЗД', 'Восточно-западная дирекция управления движением', 'ЦД');


INSERT INTO adm.regional_departments (regional_department_abbreviation,
                                      regional_directorate,
                                      central_department)
  VALUES ('ВЗП', 'ВЗДИ', 'ЦП'),
  ('ВЗШ', 'ВЗДИ', 'ЦШ');


INSERT INTO adm.directorate_territorial_departments (directorate_territorial_department_abbreviation,
                                                    directorate_territorial_department_name,
                                                    regional_directorate,
                                                    railway_territorial_department)
  VALUES ('ДИЗтер-1', 'Где-нибудьский отдел инфраструктуры', 'ВЗДИ', 1),
  ('ДЦС-1', 'Где-нибудьский центр организации работы железнодорожных станций', 'ВЗД', 1);


INSERT INTO adm.structural_enterprises (regional_directorate_abbreviation, structural_enterprise_name)
  VALUES ('ВЗДИ', 'Кое-гденьская'),
  ('ВЗДИ', 'Где-нибудьская'),
  ('ВЗДИ', 'ИССО'),
  ('ВЗДИ', 'Кое-гденьская'),
  ('ВЗДИ', 'Где-нибудьская'),
  ('ВЗД', 'КОЕ-ГДЕНЬ'),
  ('ВЗД', 'ГДЕ-ЛИБО'),
  ('ВЗД', 'ГДЕ-НИБУДЬСК'),
  ('ВЗД', 'КОЕ-ГДЕНЬ ГРУЗОВАЯ'),
  ('ВЗД', 'ЗДЕСЯ');


INSERT INTO adm.dir_ter_departments_enterprises (directorate_territorial_department_id, structural_enterprise_id)
  VALUES (1, 1),
  (1, 2),
  (1, 3),
  (1, 4),
  (1, 5),
  (2, 6),
  (2, 7),
  (2, 8),
  (2, 9),
  (2, 10);


INSERT INTO adm.p_distances (structural_enterprise_id, p_distance_number, is_ich)
  VALUES (1, 1, false),
  (2, 1, true),
  (3, 0, false);


INSERT INTO adm.p_distance_departments (p_distance_department_type, p_distance_id)
  VALUES ('Производственно-технический отдел', 1),
  ('Производства', 1),
  ('Диагностики', 1),
  ('Финансово-экономический сектор', 1),
  ('Производственно-технический отдел', 2),
  ('Производства', 2),
  ('Диагностики', 2),
  ('Финансово-экономический сектор', 2),
  ('Производственно-технический отдел', 3),
  ('Финансово-экономический сектор', 3);


INSERT INTO adm.p_exploitative_sections (p_exploitative_section_number, p_distance_id)
  VALUES (1, 1),
  (1, 2),
  (1, 3);


INSERT INTO adm.p_linear_sections (p_linear_section_number, p_exploitative_section_id)
  VALUES (1, 1),
  (2, 1),
  (1, 2),
  (2, 2),
  (1, 3);


INSERT INTO adm.sh_distances (structural_enterprise_id, sh_distance_number, is_ich)
  VALUES (4, 1, false),
  (5, 1, true);


INSERT INTO adm.sh_distance_departments (sh_distance_department_type, sh_distance_id)
  VALUES ('Производственно-технический отдел', 4),
  ('Производства', 4),
  ('Финансово-экономический сектор', 4);


INSERT INTO adm.sh_exploitative_sections (sh_exploitative_section_number, sh_distance_id)
  VALUES (1, 4),
  (1, 5);


INSERT INTO adm.sh_service_sections (sh_service_section_number, sh_exploitative_section_id)
  VALUES (1, 1),
  (1, 2);


INSERT INTO adm.d_stations (structural_enterprise_id)
  VALUES (6), (7), (8), (9), (10);


INSERT INTO adm.d_stations_p_distances (d_station_id, p_distance_id)
  VALUES (6, 1),
  (6, 3),
  (7, 2),
  (7, 3),
  (8, 2),
  (8, 3),
  (9, 1),
  (9, 3),
  (10, 2),
  (10, 3);


INSERT INTO adm.d_stations_sh_distances (d_station_id, sh_distance_id)
  VALUES (6, 4),
  (7, 5),
  (8, 5),
  (9, 4),
  (10, 5);