-- administrative structure

CREATE SCHEMA IF NOT EXISTS adm;

CREATE TABLE IF NOT EXISTS adm.central_directorates (
  central_directorate_abbreviation VARCHAR(10) PRIMARY KEY,
  central_directorate_name VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS adm.central_departments (
  central_department_abbreviation VARCHAR(10) PRIMARY KEY,
  central_department_name VARCHAR(200) NOT NULL,
  central_directorate VARCHAR(10) REFERENCES adm.central_directorates (central_directorate_abbreviation)
);

CREATE TABLE IF NOT EXISTS adm.railways (
  railway_abbreviation VARCHAR(10) PRIMARY KEY,
  railway_name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS adm.railway_territorial_departments (
  railway_territorial_department_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  railway_territorial_department_abbreviation VARCHAR(10) NOT NULL,
  railway_territorial_department_name VARCHAR(100) NOT NULL,
  railway VARCHAR(10) REFERENCES adm.railways (railway_abbreviation)
);

CREATE TABLE IF NOT EXISTS adm.regional_directorates (
  regional_directorate_abbreviation VARCHAR(10) PRIMARY KEY,
  regional_directorate_name VARCHAR(100) NOT NULL,
  central_directorate VARCHAR(10) REFERENCES adm.central_directorates (central_directorate_abbreviation)
);

CREATE TABLE IF NOT EXISTS adm.regional_departments (
  regional_department_abbreviation VARCHAR(10) PRIMARY KEY,
  regional_directorate VARCHAR(10) REFERENCES adm.regional_directorates (regional_directorate_abbreviation),
  central_department VARCHAR(10) REFERENCES adm.central_departments (central_department_abbreviation)
);

CREATE TABLE IF NOT EXISTS adm.directorate_territorial_departments (
  directorate_territorial_department_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  directorate_territorial_department_abbreviation VARCHAR(10) NOT NULL,
  directorate_territorial_department_name VARCHAR(100) NOT NULL,
  regional_directorate VARCHAR(10) REFERENCES adm.regional_directorates (regional_directorate_abbreviation),
  railway_territorial_department SMALLINT
    REFERENCES adm.railway_territorial_departments (railway_territorial_department_id)
);

CREATE TABLE IF NOT EXISTS adm.structural_enterprises (
  structural_enterprise_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  regional_directorate_abbreviation VARCHAR(10) REFERENCES adm.regional_directorates (regional_directorate_abbreviation),
  structural_enterprise_name VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS adm.dir_ter_departments_enterprises (
  directorate_territorial_department_id SMALLINT
    REFERENCES adm.directorate_territorial_departments (directorate_territorial_department_id),
  structural_enterprise_id SMALLINT REFERENCES adm.structural_enterprises (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS adm.p_distances (
  structural_enterprise_id SMALLINT REFERENCES adm.structural_enterprises (structural_enterprise_id) PRIMARY KEY,
  p_distance_number SMALLINT NOT NULL,
  is_ich BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS adm.p_distance_departments (
  p_distance_department_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  p_distance_department_type VARCHAR(75),
  p_distance_department_note VARCHAR(50),
  p_distance_id SMALLINT REFERENCES adm.p_distances (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS adm.p_exploitative_sections (
  p_exploitative_section_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  p_exploitative_section_number SMALLINT NOT NULL,
  p_distance_id SMALLINT REFERENCES adm.p_distances (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS adm.p_linear_sections (
  p_linear_section_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  p_linear_section_number SMALLINT NOT NULL,
  p_exploitative_section_id SMALLINT REFERENCES adm.p_exploitative_sections (p_exploitative_section_id)
);

CREATE TABLE IF NOT EXISTS adm.sh_distances (
  structural_enterprise_id SMALLINT REFERENCES adm.structural_enterprises (structural_enterprise_id) PRIMARY KEY,
  sh_distance_number SMALLINT NOT NULL,
  is_ich BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS adm.sh_distance_departments (
  sh_distance_department_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  sh_distance_department_type VARCHAR(75),
  sh_distance_department_note VARCHAR(50),
  sh_distance_id SMALLINT REFERENCES adm.sh_distances (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS adm.sh_exploitative_sections (
  sh_exploitative_section_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  sh_exploitative_section_number SMALLINT NOT NULL,
  sh_distance_id SMALLINT REFERENCES adm.sh_distances (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS adm.sh_service_sections (
  sh_service_section_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  sh_service_section_number SMALLINT NOT NULL,
  sh_exploitative_section_id SMALLINT REFERENCES adm.sh_exploitative_sections (sh_exploitative_section_id)
);

CREATE TABLE IF NOT EXISTS adm.d_stations (
  structural_enterprise_id SMALLINT REFERENCES adm.structural_enterprises (structural_enterprise_id) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS adm.d_stations_p_distances (
  d_station_id SMALLINT REFERENCES adm.d_stations (structural_enterprise_id),
  p_distance_id SMALLINT REFERENCES adm.p_distances (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS adm.d_stations_sh_distances (
  d_station_id SMALLINT REFERENCES adm.d_stations (structural_enterprise_id),
  sh_distance_id SMALLINT REFERENCES adm.sh_distances (structural_enterprise_id)
);

-- employees

CREATE SCHEMA IF NOT EXISTS empl;

CREATE TABLE IF NOT EXISTS empl.employees (
  employee_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  asutr_personnel_number BIGINT NOT NULL,
  employee_surname VARCHAR(100) NOT NULL,
  employee_name VARCHAR(50) NOT NULL,
  employee_patronymic VARCHAR(50),
  employee_type VARCHAR(20) NOT NULL,
  job_title_abbreviation VARCHAR(10),
  job_title VARCHAR(150) NOT NULL
);

CREATE TABLE IF NOT EXISTS empl.central_directorate_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  central_directorate VARCHAR(10) REFERENCES adm.central_directorates (central_directorate_abbreviation)
);

CREATE TABLE IF NOT EXISTS empl.central_department_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  central_department VARCHAR(10) REFERENCES adm.central_departments (central_department_abbreviation)
);

CREATE TABLE IF NOT EXISTS empl.railway_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  railway VARCHAR(10) REFERENCES adm.railways (railway_abbreviation)
);

CREATE TABLE IF NOT EXISTS empl.railway_territorial_department_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  railway_territorial_department_id INTEGER
    REFERENCES adm.railway_territorial_departments (railway_territorial_department_id)
);

CREATE TABLE IF NOT EXISTS empl.regional_directorate_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  regional_directorate VARCHAR(10) REFERENCES adm.regional_directorates (regional_directorate_abbreviation)
);

CREATE TABLE IF NOT EXISTS empl.regional_department_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  regional_department VARCHAR(10) REFERENCES adm.regional_departments (regional_department_abbreviation)
);

CREATE TABLE IF NOT EXISTS empl.directorate_territorial_department_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  directorate_territorial_department_id SMALLINT
    REFERENCES adm.directorate_territorial_departments (directorate_territorial_department_id)
);

CREATE TABLE IF NOT EXISTS empl.p_distance_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  p_distance_id SMALLINT REFERENCES adm.p_distances (structural_enterprise_id),
  job_rank SMALLINT
);

CREATE TABLE IF NOT EXISTS empl.p_distance_adm_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  p_distance_id SMALLINT REFERENCES adm.p_distances (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS empl.p_distance_department_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  p_distance_department_id SMALLINT REFERENCES adm.p_distance_departments (p_distance_department_id)
);

CREATE TABLE IF NOT EXISTS empl.p_exploitative_section_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  p_exploitative_section_id SMALLINT REFERENCES adm.p_exploitative_sections (p_exploitative_section_id)
);

CREATE TABLE IF NOT EXISTS empl.p_linear_section_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  p_linear_section_id SMALLINT REFERENCES adm.p_linear_sections (p_linear_section_id)
);

CREATE TABLE IF NOT EXISTS empl.sh_distance_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  sh_distance_id SMALLINT REFERENCES adm.sh_distances (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS empl.sh_distance_department_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  sh_distance_department_id SMALLINT REFERENCES adm.sh_distance_departments (sh_distance_department_id),
  job_rank SMALLINT
);

CREATE TABLE IF NOT EXISTS empl.sh_exploitative_section_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  sh_exploitative_section_id SMALLINT REFERENCES adm.sh_exploitative_sections (sh_exploitative_section_id)
);

CREATE TABLE IF NOT EXISTS empl.sh_service_section_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  sh_service_section_id SMALLINT REFERENCES adm.sh_service_sections (sh_service_section_id),
  job_rank SMALLINT
);

CREATE TABLE IF NOT EXISTS empl.d_station_employees (
  employee_id INTEGER REFERENCES empl.employees (employee_id),
  d_station_id SMALLINT REFERENCES adm.d_stations (structural_enterprise_id)
);

-- infrastructure

CREATE SCHEMA IF NOT EXISTS infr;

CREATE TABLE IF NOT EXISTS infr.transport_directions (  -- направления (глобальные и локальные линии сети дорог)
  transport_direction_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  transport_direction_name VARCHAR(100),
  transport_direction_level VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS infr.main_ways (
  main_way_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  transport_direction_id SMALLINT REFERENCES infr.transport_directions (transport_direction_id),
  main_way_number SMALLINT CHECK (main_way_number > 0)
);

CREATE TABLE IF NOT EXISTS infr.main_ways_p_distances (
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id),
  p_distance_id SMALLINT REFERENCES adm.p_distances (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS infr.kilometers (
  transport_direction_id SMALLINT REFERENCES infr.transport_directions (transport_direction_id),
  km SMALLINT CHECK (km >= 0),
  non_standard BOOLEAN DEFAULT false,
  km_length SMALLINT DEFAULT 1000,
  p_linear_section_id SMALLINT REFERENCES adm.p_linear_sections (p_linear_section_id),
  sh_service_section_id SMALLINT REFERENCES adm.sh_service_sections (sh_service_section_id),
  PRIMARY KEY (transport_direction_id, km)
);

CREATE TABLE IF NOT EXISTS infr.transport_direction_sections (
  -- мелкие участки (направлений), разделенные по определенному признаку
  section_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  transport_direction_id SMALLINT REFERENCES infr.transport_directions (transport_direction_id),
  start_km SMALLINT,
  start_meter_of_km SMALLINT CHECK (start_meter_of_km BETWEEN 0 AND 1100),
  end_km SMALLINT,
  end_meter_of_km SMALLINT CHECK (end_meter_of_km BETWEEN 0 AND 1100),
  section_length INTEGER CHECK (section_length > 0),
  CONSTRAINT fk_start FOREIGN KEY (transport_direction_id, start_km)
    REFERENCES infr.kilometers (transport_direction_id, km),
  CONSTRAINT fk_end FOREIGN KEY (transport_direction_id, end_km)
    REFERENCES infr.kilometers (transport_direction_id, km)
);

CREATE TABLE IF NOT EXISTS infr.transport_direction_places (
  -- конкретные места (направлений), на которых находится ось или важный элемент объекта инфраструктуры
  place_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  transport_direction_id SMALLINT REFERENCES infr.transport_directions (transport_direction_id),
  place_km SMALLINT,
  place_meter_of_km SMALLINT CHECK (place_meter_of_km BETWEEN 0 AND 1100),
  CONSTRAINT fk_place FOREIGN KEY (transport_direction_id, place_km)
    REFERENCES infr.kilometers (transport_direction_id, km)
);

CREATE TABLE IF NOT EXISTS infr.stations (
  structural_enterprise_id SMALLINT REFERENCES adm.d_stations (structural_enterprise_id) PRIMARY KEY,
  section_id BIGINT REFERENCES infr.transport_direction_sections (section_id),
  station_class VARCHAR(3) NOT NULL,
  appointment VARCHAR(20) NOT NULL,
  technological_use VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS infr.interstation_tracks (  -- перегоны
  interstation_track_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  start_station_id INTEGER REFERENCES infr.stations (structural_enterprise_id),
  end_station_id INTEGER CHECK (end_station_id <> start_station_id) REFERENCES infr.stations (structural_enterprise_id),
  electrified BOOLEAN NOT NULL,
  auto_block_system BOOLEAN NOT NULL,
  amount_of_ways SMALLINT CHECK (amount_of_ways > 0)
);

CREATE TABLE IF NOT EXISTS infr.switches (  -- стрелочные переводы
  switch_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  switch_name VARCHAR(7) NOT NULL,
  switch_full_name VARCHAR(50),
  switch_project VARCHAR(20) NOT NULL,
  rail_type VARCHAR(3) DEFAULT 'Р65',
  beams_material VARCHAR(15) NOT NULL,
  ballast_type VARCHAR(20) NOT NULL,
  cross_marking VARCHAR(7) NOT NULL,
  control_type VARCHAR(5) NOT NULL,
  line_side VARCHAR(6) NOT NULL,
  gauge SMALLINT DEFAULT 1520,
  installation_date DATE NOT NULL,
  passed_tonnage_before_install FLOAT(1) CHECK (passed_tonnage_before_install >= 0),
  passed_tonnage_after_install FLOAT(1) CHECK (passed_tonnage_after_install >= 0),
  outcross_curve_length SMALLINT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS infr.travels (  -- съезды
  travel_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  travel_first_switch_id SMALLINT REFERENCES infr.switches (switch_id),
  travel_last_switch_id SMALLINT CHECK (travel_last_switch_id <> travel_first_switch_id)
    REFERENCES infr.switches (switch_id),
  travel_length SMALLINT CHECK (travel_length BETWEEN 0 AND 200),
  rail_type VARCHAR(3) DEFAULT 'Р65',
  sleepers_material VARCHAR(15) NOT NULL,
  ballast_type VARCHAR(20) NOT NULL,
  installation_date DATE NOT NULL,
  passed_tonnage_before_install FLOAT(1) CHECK (passed_tonnage_before_install >= 0),
  passed_tonnage_after_install FLOAT(1) CHECK (passed_tonnage_after_install >= 0),
  is_interstation_track_travel BOOLEAN DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS infr.interstation_track_switches (
  switch_id INTEGER REFERENCES infr.switches (switch_id) PRIMARY KEY,
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id),
  frame_rail_joint_place_id BIGINT REFERENCES infr.transport_direction_places (place_id),
  control_station_id SMALLINT REFERENCES infr.stations (structural_enterprise_id)
);

CREATE TABLE IF NOT EXISTS infr.station_parks (
  station_park_id SMALLINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  station_id SMALLINT REFERENCES infr.stations (structural_enterprise_id),
  park_name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS infr.station_ways (
  station_way_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  station_id SMALLINT REFERENCES infr.stations (structural_enterprise_id),
  station_park_id SMALLINT REFERENCES infr.station_parks (station_park_id),
  station_way_name VARCHAR(7) NOT NULL,
  station_way_full_name VARCHAR(50),
  appointment VARCHAR(25) NOT NULL,
  full_length SMALLINT CHECK (full_length > 0)
);

CREATE TABLE IF NOT EXISTS infr.station_main_ways (
  station_way_id INTEGER REFERENCES infr.station_ways (station_way_id) PRIMARY KEY,
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id),
  section_id BIGINT REFERENCES infr.transport_direction_sections (section_id)
);

CREATE TABLE IF NOT EXISTS infr.station_not_main_ways (
  station_way_id INTEGER REFERENCES infr.station_ways (station_way_id) PRIMARY KEY,
  starts_from_switch_with_name VARCHAR(7) NOT NULL,
  ends_on_switch_with_name VARCHAR(7),                                        -- null-значение обозначает упор
  useful_length SMALLINT CHECK (useful_length > 0),
  passenger_train_speed SMALLINT CHECK (passenger_train_speed > 0),
  freight_train_speed SMALLINT CHECK (freight_train_speed > 0),
  p_linear_section_id SMALLINT REFERENCES adm.p_linear_sections (p_linear_section_id),
  sh_service_section_id SMALLINT REFERENCES adm.sh_service_sections (sh_service_section_id)
);

CREATE TABLE IF NOT EXISTS infr.station_main_way_switches (
  switch_id INTEGER REFERENCES infr.switches (switch_id) PRIMARY KEY,
  station_park_id SMALLINT REFERENCES infr.station_parks (station_park_id),
  frame_rail_joint_station_main_way_id INTEGER REFERENCES infr.station_main_ways (station_way_id),
  frame_rail_joint_place_id BIGINT REFERENCES infr.transport_direction_places (place_id),
  way_name_switch_goes_on VARCHAR(7)
);

CREATE TABLE IF NOT EXISTS infr.station_way_sections (
  sw_section_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  station_way_id INTEGER REFERENCES infr.station_ways (station_way_id),
  sw_section_start_meter SMALLINT CHECK (sw_section_start_meter >= 0),
  sw_section_end_meter SMALLINT CHECK (sw_section_end_meter > 0),
  sw_section_length SMALLINT CHECK (sw_section_length > 0)
);

CREATE TABLE IF NOT EXISTS infr.station_way_places (
  sw_place_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  station_way_id INTEGER REFERENCES infr.station_ways (station_way_id),
  sw_place_meter SMALLINT CHECK (sw_place_meter > 0)
);

CREATE TABLE IF NOT EXISTS infr.station_way_switches (
  switch_id INTEGER REFERENCES infr.switches (switch_id) PRIMARY KEY,
  station_park_id SMALLINT REFERENCES infr.station_parks (station_park_id),
  frame_rail_joint_place_id BIGINT REFERENCES infr.station_way_places (sw_place_id),
  way_name_switch_goes_on VARCHAR(7)
);

CREATE TABLE IF NOT EXISTS infr.artificial_constructions ( -- искусственные сооружения
  isso_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  material_type VARCHAR(15) NOT NULL,
  p_isso_linear_section_id SMALLINT REFERENCES adm.p_linear_sections (p_linear_section_id)
);

CREATE TABLE IF NOT EXISTS infr.bridges (
  isso_id BIGINT REFERENCES infr.artificial_constructions (isso_id) PRIMARY KEY,
  bridge_name VARCHAR(50) NOT NULL,
  size_type VARCHAR(10) NOT NULL,
  appointment VARCHAR(10) NOT NULL,
  static_schema_type VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS infr.main_bridges (
  isso_id BIGINT REFERENCES infr.bridges (isso_id) PRIMARY KEY,
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id),
  section_id BIGINT REFERENCES infr.transport_direction_sections (section_id)
);

CREATE TABLE IF NOT EXISTS infr.station_bridges (
  isso_id BIGINT REFERENCES infr.bridges (isso_id) PRIMARY KEY,
  station_way_section_id BIGINT REFERENCES infr.station_way_sections (sw_section_id)
);

CREATE TABLE IF NOT EXISTS infr.tunnels (
  isso_id BIGINT REFERENCES infr.artificial_constructions (isso_id) PRIMARY KEY,
  tunnel_name VARCHAR(50) NOT NULL,
  size_type VARCHAR(10) NOT NULL,
  appointment VARCHAR(10) NOT NULL
);

CREATE TABLE IF NOT EXISTS infr.main_tunnels (
  isso_id BIGINT REFERENCES infr.tunnels (isso_id) PRIMARY KEY,
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id),
  section_id BIGINT REFERENCES infr.transport_direction_sections (section_id)
);

CREATE TABLE IF NOT EXISTS infr.station_tunnels (
  isso_id BIGINT REFERENCES infr.tunnels (isso_id) PRIMARY KEY,
  station_way_section_id BIGINT REFERENCES infr.station_way_sections (sw_section_id)
);

CREATE TABLE IF NOT EXISTS infr.pipes (
  isso_id BIGINT REFERENCES infr.artificial_constructions (isso_id) PRIMARY KEY,
  pipe_length INTEGER CHECK (pipe_length > 0),
  amount_of_holes SMALLINT CHECK (amount_of_holes > 0),
  hole_shape VARCHAR(13) NOT NULL,
  hole_max_dimension SMALLINT CHECK (hole_max_dimension BETWEEN 20 AND 4000),
  hole_min_dimension SMALLINT CHECK (hole_min_dimension BETWEEN 20 AND 4000)
);

CREATE TABLE IF NOT EXISTS infr.main_pipes (
  isso_id BIGINT REFERENCES infr.pipes (isso_id) PRIMARY KEY,
  place_id BIGINT REFERENCES infr.transport_direction_places (place_id)
);

CREATE TABLE IF NOT EXISTS infr.station_pipes (
  isso_id BIGINT REFERENCES infr.pipes (isso_id) PRIMARY KEY,
  station_way_place_id BIGINT REFERENCES infr.station_way_places (sw_place_id)
);

CREATE TABLE IF NOT EXISTS infr.road_crossings (
  road_crossing_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  road_crossing_category VARCHAR(3) NOT NULL,
  common_use BOOLEAN DEFAULT true,
  traffic_control BOOLEAN DEFAULT true,
  with_attendant BOOLEAN DEFAULT false
);

CREATE TABLE IF NOT EXISTS infr.main_road_crossings (
  road_crossing_id INTEGER REFERENCES infr.road_crossings (road_crossing_id) PRIMARY KEY,
  place_id BIGINT REFERENCES infr.transport_direction_places (place_id)
);

CREATE TABLE IF NOT EXISTS infr.station_road_crossings (
  road_crossing_id INTEGER REFERENCES infr.road_crossings (road_crossing_id) PRIMARY KEY,
  station_way_place_id BIGINT REFERENCES infr.station_way_places (sw_place_id)
);

-- way characteristics

CREATE SCHEMA IF NOT EXISTS wayc;

CREATE TABLE IF NOT EXISTS wayc.main_way_characteristic_sections (
  main_way_section_id BIGINT REFERENCES infr.transport_direction_sections (section_id) PRIMARY KEY,
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id)
);

CREATE TABLE IF NOT EXISTS wayc.station_way_characteristic_sections (
  sw_section_id BIGINT REFERENCES infr.station_way_sections (sw_section_id) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS wayc.main_longitudinal_profile_sections (
  main_way_section_id BIGINT REFERENCES wayc.main_way_characteristic_sections (main_way_section_id) PRIMARY KEY,
  slope FLOAT(1) CHECK (slope BETWEEN -30.0 AND 30.0)
);

CREATE TABLE IF NOT EXISTS wayc.station_longitudinal_profile_sections (
  sw_section_id BIGINT REFERENCES wayc.station_way_characteristic_sections (sw_section_id),
  slope FLOAT(1) CHECK (slope BETWEEN -30.0 AND 30.0)
);

CREATE TABLE IF NOT EXISTS wayc.main_plan_sections (
  main_way_section_id BIGINT REFERENCES wayc.main_way_characteristic_sections (main_way_section_id) PRIMARY KEY,
  straight BOOLEAN NOT NULL,
  line_side VARCHAR(6),
  radius SMALLINT CHECK (radius BETWEEN 100 AND 4000),
  rail_elevation SMALLINT CHECK (rail_elevation BETWEEN 0 AND 150),
  end_of_first_transition_curve_meter SMALLINT CHECK (end_of_first_transition_curve_meter > 0),
  end_of_second_transition_curve_meter SMALLINT CHECK (end_of_second_transition_curve_meter > 0)
);

CREATE TABLE IF NOT EXISTS wayc.station_plan_sections (
  sw_section_id BIGINT REFERENCES wayc.station_way_characteristic_sections (sw_section_id),
  straight BOOLEAN NOT NULL,
  line_side VARCHAR(6),
  radius SMALLINT CHECK (radius BETWEEN 100 AND 4000)
);

CREATE TABLE IF NOT EXISTS wayc.main_speed_sections (
  main_way_section_id BIGINT REFERENCES wayc.main_way_characteristic_sections (main_way_section_id) PRIMARY KEY,
  passenger_train_speed SMALLINT CHECK (passenger_train_speed BETWEEN 5 AND 250),
  freight_train_speed SMALLINT CHECK (freight_train_speed BETWEEN 5 AND 150)
);

CREATE TABLE IF NOT EXISTS wayc.main_classification_sections (
  main_way_section_id BIGINT REFERENCES wayc.main_way_characteristic_sections (main_way_section_id) PRIMARY KEY,
  freight_intensity FLOAT(1) CHECK (freight_intensity BETWEEN 0.1 AND 200.0),
  line_class_specialization VARCHAR(3) NOT NULL,
  group_class_code VARCHAR(3) NOT NULL
);

CREATE TABLE IF NOT EXISTS wayc.main_tonnage_sections (
  main_way_section_id BIGINT REFERENCES wayc.main_way_characteristic_sections (main_way_section_id) PRIMARY KEY,
  passed_tonnage_before_install FLOAT(1) CHECK (passed_tonnage_before_install >= 0),
  passed_tonnage_after_install FLOAT(1) CHECK (passed_tonnage_after_install >= 0)
);

CREATE TABLE IF NOT EXISTS wayc.station_tonnage_sections (
  sw_section_id BIGINT REFERENCES wayc.station_way_characteristic_sections (sw_section_id),
  passed_tonnage_before_install FLOAT(1) CHECK (passed_tonnage_before_install >= 0),
  passed_tonnage_after_install FLOAT(1) CHECK (passed_tonnage_after_install >= 0)
);

CREATE TABLE IF NOT EXISTS wayc.main_rail_sections (
  main_way_section_id BIGINT REFERENCES wayc.main_way_characteristic_sections (main_way_section_id) PRIMARY KEY,
  rail_type VARCHAR(3) DEFAULT 'Р65',
  rail_category VARCHAR(2) NOT NULL,
  thermal_hardening VARCHAR(50) NOT NULL,
  way_type VARCHAR(15) NOT NULL,
  factory VARCHAR(2) NOT NULL,
  factory_year VARCHAR(4) NOT NULL,
  installation_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS wayc.station_rail_sections (
  sw_section_id BIGINT REFERENCES wayc.station_way_characteristic_sections (sw_section_id),
  rail_type VARCHAR(3) DEFAULT 'Р65',
  rail_category VARCHAR(2) NOT NULL,
  thermal_hardening VARCHAR(50) NOT NULL,
  way_type VARCHAR(15) NOT NULL,
  factory VARCHAR(2) NOT NULL,
  factory_year VARCHAR(4) NOT NULL,
  installation_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS wayc.main_underrail_base_sections (
  main_way_section_id BIGINT REFERENCES wayc.main_way_characteristic_sections (main_way_section_id) PRIMARY KEY,
  sleeper_material VARCHAR(20) NOT NULL,
  sleepers_per_km SMALLINT CHECK (sleepers_per_km BETWEEN 0 AND 2000),
  fastening_type VARCHAR(10) NOT NULL,
  ballast_type VARCHAR(20) NOT NULL,
  ballast_height SMALLINT CHECK (ballast_height BETWEEN 10 AND 70)
);

CREATE TABLE IF NOT EXISTS wayc.station_underrail_base_sections (
  sw_section_id BIGINT REFERENCES wayc.station_way_characteristic_sections (sw_section_id),
  sleeper_material VARCHAR(20) NOT NULL,
  sleepers_per_km SMALLINT CHECK (sleepers_per_km BETWEEN 1200 AND 2300),
  fastening_type VARCHAR(10) NOT NULL,
  ballast_type VARCHAR(20) NOT NULL,
  ballast_height SMALLINT CHECK (ballast_height BETWEEN 10 AND 70)
);

CREATE TABLE IF NOT EXISTS wayc.main_repair_sections (
  main_way_section_id BIGINT REFERENCES wayc.main_way_characteristic_sections (main_way_section_id) PRIMARY KEY,
  capital_repair_year DATE NOT NULL,
  capital_repair_type VARCHAR(20) NOT NULL,
  intermediate_repair_year DATE,
  intermediate_repair_type VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS wayc.station_repair_sections (
  sw_section_id BIGINT REFERENCES wayc.station_way_characteristic_sections (sw_section_id),
  capital_repair_year DATE NOT NULL,
  capital_repair_type VARCHAR(20) NOT NULL,
  intermediate_repair_year DATE,
  intermediate_repair_type VARCHAR(20)
);

-- rail_book

CREATE SCHEMA IF NOT EXISTS rail;

CREATE TABLE IF NOT EXISTS rail.rails (
  rail_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  rail_length FLOAT(2) CHECK (rail_length BETWEEN 1.00 AND 100.00),
  rail_line_side VARCHAR(6) NOT NULL,
  rail_kind VARCHAR(25) NOT NULL,
  rail_type VARCHAR(3) DEFAULT 'Р65',
  rail_category VARCHAR(2) NOT NULL,
  thermal_hardening VARCHAR(50) NOT NULL,
  factory VARCHAR(2) NOT NULL,
  rolling_date VARCHAR(9) NOT NULL,
  fuse_number VARCHAR(30) NOT NULL,
  installation_date DATE NOT NULL,
  installation_type VARCHAR(17) NOT NULL,
  gap_before SMALLINT CHECK (gap_before BETWEEN 0 AND 50),
  gap_after SMALLINT CHECK (gap_after BETWEEN 0 AND 50),
  passed_tonnage_before_install FLOAT(1) CHECK (passed_tonnage_before_install >= 0),
  passed_tonnage_after_install FLOAT(1) CHECK (passed_tonnage_after_install >= 0),
  vertical_wear SMALLINT CHECK (vertical_wear BETWEEN 0 AND 15),
  horizontal_wear SMALLINT CHECK (horizontal_wear BETWEEN 0 AND 25),
  last_measure_date DATE,
  last_measure_rail_temp SMALLINT CHECK (last_measure_rail_temp BETWEEN -70 AND 80)
);

CREATE TABLE IF NOT EXISTS rail.rails_in_main_ways (
  rail_id BIGINT REFERENCES rail.rails (rail_id) PRIMARY KEY,
  rail_main_section_id BIGINT REFERENCES infr.transport_direction_sections (section_id),
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id)
);

CREATE TABLE IF NOT EXISTS rail.rails_in_station_ways (
  rail_id BIGINT REFERENCES rail.rails (rail_id) PRIMARY KEY,
  rail_sw_section_id BIGINT REFERENCES infr.station_way_sections (sw_section_id)
);

CREATE TABLE IF NOT EXISTS rail.joints (
  joint_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  rail_line_side VARCHAR(6) NOT NULL,
  rail_id_before BIGINT REFERENCES rail.rails (rail_id),
  rail_id_after BIGINT REFERENCES rail.rails (rail_id),
  joint_status VARCHAR(16) DEFAULT 'АКТИВЕН',
  joint_type VARCHAR(15) NOT NULL,
  pads_type VARCHAR(25) NOT NUll,
  pad_amount_of_holes SMALLINT CHECK (pad_amount_of_holes = 4 OR pad_amount_of_holes = 6),
  joint_vertical_step FLOAT(1) CHECK (joint_vertical_step BETWEEN 0 AND 10),
  joint_horizontal_step FLOAT(1) CHECK (joint_horizontal_step BETWEEN 0 AND 10),
  last_measure_date DATE,
  last_measure_rail_temp SMALLINT CHECK (last_measure_rail_temp BETWEEN -70 AND 80)
);

CREATE TABLE IF NOT EXISTS rail.conducting_joint_info (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  joint_contact_resistance SMALLINT CHECK (joint_contact_resistance BETWEEN 0 AND 10000),
  last_resistance_measure_date DATE,
  last_pads_removal_date DATE,
  connector_types VARCHAR(25),
  connector_installation_date DATE
);

CREATE TABLE IF NOT EXISTS rail.isolating_joint_info (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  isolating_joint_object_type VARCHAR(20) NOT NULL,
  last_pads_removal_date DATE,
  last_demagnetization_date DATE,
  last_demagnetization_method VARCHAR(20),
  isolating_joint_magnetization SMALLINT CHECK (isolating_joint_magnetization BETWEEN 0 AND 1000),
  last_magnetization_measure_date DATE
);

CREATE TABLE IF NOT EXISTS rail.switch_isolating_joints (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  switch_id INTEGER REFERENCES infr.switches (switch_id),
  joint_number SMALLINT CHECK (joint_number BETWEEN 1 AND 8)
);

CREATE TABLE IF NOT EXISTS rail.signal_isolating_joints (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  signal_name VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS rail.conducting_joints_in_main_ways (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  joint_main_place_id BIGINT REFERENCES infr.transport_direction_places (place_id),
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id)
);

CREATE TABLE IF NOT EXISTS rail.signal_isolating_joints_in_main_ways (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  joint_main_place_id BIGINT REFERENCES infr.transport_direction_places (place_id),
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id)
);

CREATE TABLE IF NOT EXISTS rail.switch_isolating_joints_in_main_ways (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  joint_main_place_id BIGINT REFERENCES infr.transport_direction_places (place_id),
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id)
);

CREATE TABLE IF NOT EXISTS rail.conducting_joints_in_station_ways (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  joint_sw_place_id BIGINT REFERENCES infr.station_way_places (sw_place_id)
);

CREATE TABLE IF NOT EXISTS rail.signal_isolating_joints_in_station_ways (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  joint_sw_place_id BIGINT REFERENCES infr.station_way_places (sw_place_id)
);

CREATE TABLE IF NOT EXISTS rail.switch_isolating_joints_in_station_ways (
  joint_id BIGINT REFERENCES rail.joints (joint_id) PRIMARY KEY,
  joint_sw_place_id BIGINT REFERENCES infr.station_way_places (sw_place_id)
);

CREATE TABLE IF NOT EXISTS rail.lashes (
  lash_id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  lash_name VARCHAR(7) NOT NULL,
  rail_line_side VARCHAR(6) NOT NULL,
  lash_length FLOAT(2) CHECK (lash_length > 0),
  lash_start_centimeter_on_meter SMALLINT CHECK (lash_start_centimeter_on_meter BETWEEN 0 AND 99),
  lash_end_centimeter_on_meter SMALLINT CHECK (lash_start_centimeter_on_meter BETWEEN 0 AND 99),
  lash_welding_company VARCHAR(10),
  installation_date DATE NOT NULL,
  installation_type VARCHAR(17) NOT NULL,
  installed_by VARCHAR(10),
  passed_tonnage_before_install FLOAT(1) CHECK (passed_tonnage_before_install >= 0),
  passed_tonnage_after_install FLOAT(1) CHECK (passed_tonnage_after_install >= 0),
  last_temperature_tension_discharging_date DATE NOT NULL,
  last_temperature_tension_discharging_temperature SMALLINT
    CHECK (last_temperature_tension_discharging_temperature BETWEEN -30 AND 40),
  last_temperature_tension_discharging_reason VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS rail.lash_gaps (
  lash_gap_id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
  lash_id_before BIGINT REFERENCES rail.lashes (lash_id),
  lash_id_after BIGINT REFERENCES rail.lashes (lash_id),
  amount_of_gap_rails SMALLINT CHECK (amount_of_gap_rails BETWEEN 2 AND 5)
);

CREATE TABLE IF NOT EXISTS rail.lashes_in_main_ways (
  lash_id BIGINT REFERENCES rail.lashes (lash_id) PRIMARY KEY,
  lash_main_section_id BIGINT REFERENCES infr.transport_direction_sections (section_id),
  main_way_id SMALLINT REFERENCES infr.main_ways (main_way_id)
);

CREATE TABLE IF NOT EXISTS rail.lashes_in_station_ways (
  lash_id BIGINT REFERENCES rail.lashes (lash_id) PRIMARY KEY,
  lash_sw_section_id BIGINT REFERENCES infr.station_way_sections (sw_section_id)
);