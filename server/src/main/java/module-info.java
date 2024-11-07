open module diode.server {

    requires spring.beans;
    requires spring.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.data.jpa;
    requires spring.web;
    requires jakarta.persistence;
    requires static lombok;
    requires org.hibernate.orm.core;
    requires org.mapstruct;
    requires org.slf4j;
    requires org.yaml.snakeyaml;

    requires common.classes;
    requires tcd.docdatadtos;

}