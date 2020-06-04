-- show databases;
-- Criando um banco de dados 
create database dbcad;
-- usando banco de dados criado dbcad
use dbcad;

-- criando tabela 
-- not null = o campo não pode ficar vazio
-- unique = não pode ter outro igual no banco dados
-- primary key = usado para pesquisar no banco da dados, não pode ser igual
create table tb_usuarios(
id_user int primary key,
usuario varchar(50) not null,
fone varchar(15),
login varchar(15) not null unique,
senha varchar(15) not null
);
-- mostra tabela
describe tb_usuarios;

-- Fazer um crud para inserir dados na tabela
insert into tb_usuarios(id_user,usuario,fone,login,senha)
values(1,'Débora Freire','98812-3451','deborafsm','123456');

-- exibir os dados

select * from tb_usuarios;

insert into tb_usuarios(id_user,usuario,fone,login,senha)
values(2,'Dimitri Tchaikovski','98888-3451','Dtchai','123456');

insert into tb_usuarios(id_user,usuario,fone,login,senha)
values(3,'Edgar Mozart','98888-32251','Emozart','123456');
insert into tb_usuarios(id_user,usuario,fone,login,senha)
values(4,'Lucio Debussy','98888-3151','LDebussy','123456');
insert into tb_usuarios(id_user,usuario,fone,login,senha)
values(6,'Flávio Liszt','91188-3151','FLiszt','123456');
insert into tb_usuarios(id_user,usuario,fone,login,senha)
values(5,'Júlio Bach','91188-2151','Jbach','123456');

-- Editar telefone
update tb_usuarios set fone = '1111-11111' where id_user=1;
-- Criar um cadastro para deletar
insert into tb_usuarios(id_user,usuario,fone,login,senha)
values(7,'Dimitri ','98888-3451','ddd','123456');

-- 
delete from tb_usuarios where id_user = 7;

-- Criar Bd de clientes(

create table tb_clientes(
id_cliente int primary key auto_increment,
nome_cliente varchar(30) not null,
end_cliente varchar(100) ,
fone_cliente varchar(50) not null,
email_cliente varchar(50),
cidade varchar(30),
estado varchar(20),
uf varchar(2),
cep varchar(10)
);

describe tb_clientes;


insert into tb_clientes(nome_cliente,end_cliente,fone_cliente,email_cliente,cidade,estado,UF,cep)
values('Débora Frere','Rue des Cascade','555-22','deborafreiresoares@outlook.com','Versalhes','Paris','PR','3030');
insert into tb_clientes(nome_cliente,end_cliente,fone_cliente,email_cliente,cidade,estado,UF,cep)
values('Rick Debois Ecre','Rue des Roses','555-21','rick_boybeau@outlook.com','Versalhes','Paris','PR','30');
select * from tb_clientes;



create database dbaluno;
use dbaluno;
create table aluno(
nome varchar(50),
idade int(3),
nome_mae varchar(50),
nome_pai varchar(50),
rm int(30) primary key,
rg varchar(30),
cpf varchar(30),
fone varchar(30),
sexo char(1),
nascimento date
);

describe aluno;

use dbcad;

create table tb_ordemservico(
os int primary key auto_increment,
data_os timestamp default current_timestamp, -- gera a data e hora automaticamente
equipamento varchar(150) not null,
defeito varchar(150) not null,
servico varchar(150),
tecnico varchar(30),
valor decimal(10,2), -- dez digitos 2 casas decimais
id_cliente int not null,
foreign key(id_cliente) references tb_clientes(id_cliente) -- chave estrangeira com a referencia aonde o id_cliente foi criado
);
describe tb_ordemservico;

insert into tb_ordemservico(equipamento,defeito,servico,tecnico,valor,id_cliente)
value('Smartphone','Tela Quebrada','Trocar a tela de vidro','Jones',100.00,1);

insert into tb_ordemservico(equipamento,defeito,servico,tecnico,valor,id_cliente)
value('Notebook','Teclado com defeito','Trocar teclado','Bily',98000.00,2);

select * from tb_ordemservico;


select 
Ordem.tb_ordemservico,equipamento,defeito,valor,
Cliente.nome_cliente,fone_cliente
from tb_ordemservico as Ordem
inner join tb_clientes as Cliente
on (Ordem.id_cliente = Cliente.id_cliente);

