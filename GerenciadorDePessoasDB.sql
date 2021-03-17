create database gerenciador_de_pessoas;

use gerenciador_de_pessoas;

create table pessoas(
	id int auto_increment primary key,
    nome varchar(50) not null,
    sobreNome varchar(50) not null    
);

create table salasEventos(
	id int auto_increment primary key,
    nome varchar(50) not null,
    lotacao int not null
);

create table espacosCafe(
	id int auto_increment primary key,
    nome varchar(50) not null,
    lotacao int not null
);

create table etapas(
	id int auto_increment primary key,
    evento int not null,
	id_salasEventos int,
	id_espacosCafe int,
    id_pessoas int,
    constraint fk_id_salasEventos foreign key (id_salasEventos) references salasEventos(id),
    constraint fk_id_espacosCafe foreign key (id_espacosCafe) references espacosCafe(id),
	constraint fk_id_pessoas foreign key (id_pessoas) references pessoas(id)
);

insert into salasEventos(nome, lotacao) values("Sala 1", 2);
insert into salasEventos(nome, lotacao) values("Sala 2", 2);
insert into salasEventos(nome, lotacao) values("Sala 3", 2);

insert into espacosCafe(nome, lotacao) values("Coffe 1", 2);
insert into espacosCafe(nome, lotacao) values("Coffe 2", 2);
insert into espacosCafe(nome, lotacao) values("Coffe 3", 2);

insert into pessoas(nome, sobreNome) values("Bruno", "Bastos");
insert into pessoas(nome, sobreNome) values("Lucas", "Quinotto");
insert into pessoas(nome, sobreNome) values("Mariana", "M");
insert into pessoas(nome, sobreNome) values("Mateus", "B");
insert into pessoas(nome, sobreNome) values("Roblessa", "R");

insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(1, 1, 1, 1);
insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(1, 1, 1, 2);
insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(1, 1, 1, 3);
insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(1, 2, 2, 4);
insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(1, 2, 2, 5);


insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(2, 1, 1, 1);
insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(2, 2, 2, 2);
insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(2, 1, 1, 3);
insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(2, 2, 2, 4);
insert into etapas(evento, id_salasEventos, id_espacosCafe, id_pessoas) values(2, 1, 1, 5);


