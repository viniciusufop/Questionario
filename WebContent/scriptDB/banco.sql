CREATE database questionario;

use questionario;

create table usuario (
	idUsuario int not null auto_increment,
	nome varchar(100) null,
    email varchar(100) not null,
    senha varchar(32) null,
    permissao varchar(100) null,
	ativo tinyint(1) not null,
    primary key (idUsuario)
);

create table questionario (
	idQuestionario int not null auto_increment,
    idUsuario int not null,
	nome varchar(100) null,
    primary key (idQuestionario),
	constraint fk_questionario_usuario 
		foreign key (idUsuario)
        references usuario (idUsuario)
        on delete cascade
        on update cascade
);

create table pergunta (
		idPergunta int not null auto_increment,
        idQuestionario int not null,
        codPergunta int not null,
        descricao VARCHAR(500) not null,
        objetiva TINYINT(1) not null default 0,
        primary key (idPergunta),
		constraint fk_pergunta_questionario 
			foreign key (idQuestionario)
			references questionario (idQuestionario)
            on delete cascade
        	on update cascade
);

create table opcaoRespostaObjetiva (
		idOpcaoRespostaObjetiva int not null auto_increment,
        idPergunta int not null,
        codResposta int not null,
        descricao varchar(100) null,
        primary key (idOpcaoRespostaObjetiva),
        constraint fk_opcaoRespostaObjectiva_pergunta
			foreign key (idPergunta)
			references pergunta (idPergunta)
           	on delete cascade
        	on update cascade
);

create table usuarioResposta (
		idUsuario int not null,
		idQuestionario int not null,
        idPergunta int not null,
        respostaDiscursiva varchar(100) null,
        respostaObjetiva int null,
        primary key (idUsuario,idQuestionario,idPergunta),
        constraint fk_usuarioResposta_usuario
			foreign key (idUsuario)
			references usuario (idUsuario)
            on delete cascade
       		on update cascade,
		constraint fk_usuarioResposta_questionario 
			foreign key (idQuestionario)
			references questionario (idQuestionario)
            on delete cascade
        	on update cascade,
		constraint fk_usuarioResposta_pergunta
			foreign key (idPergunta)
			references pergunta (idPergunta)
            on delete cascade
        	on update cascade
);

select * from usuario;
update usuario set ativo = 1 where idusuario = 2;

insert into usuario(nome,email,senha,permissao,ativo) values ("Vinicius", "vinicius", "teste", "ROLE_MEMBRO", 1);