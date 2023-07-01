-- liquibase formatted sql

-- changeset liquibase:rseni-prepare-schema

create schema ni;

create role niapi with login password 'ChangeMeToARandomGUID';

create role nipub with login password 'ChangeMeToARandomGUID';

grant usage on schema ni to niapi;

grant usage on schema ni to nipub;
