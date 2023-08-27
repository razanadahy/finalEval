--create database clinique;
--drop database clinique;
create table adminUtilisateur
(
    idAdmin serial primary key ,
    nom varchar(30),
    mdp varchar(30)
);
insert into adminUtilisateur(nom, mdp) VALUES ('andrianiavo','1234');
--select * from adminUtilisateur;
create table utilisateur
(
    idUtilisateur serial primary key ,
    nom varchar(30),
    mdp varchar(30)
);
insert into utilisateur(nom, mdp) VALUES ('u1','u1');
create table genre
(
    idGenre int primary key ,
    genre varchar(30)
);
insert into genre(idGenre, genre) VALUES (1,'Homme');
insert into genre(idGenre, genre) VALUES (2,'Femme');
create table patient
(
    idPatient serial primary key ,
    nom varchar(100),
    naissance date,
    idGenre int,
    rembourche bool,
    foreign key (idGenre) references genre(idGenre)
);
create or replace view genrePatient as select idPatient , nom,naissance,rembourche,genre from patient join genre g on g.idGenre = patient.idGenre;
create table acte
(
    idActe serial primary key ,
    typeActe varchar(30),
    ref varchar(4),
    budget double precision
);

create table depense
(
    idDepense serial primary key ,
    typeDepense varchar(30),
    ref varchar(40),
    budget double precision
);

create table facture
(
    idFacture serial primary key ,
    reference varchar(100),
    idPatient int,
    date date,
    foreign key (idPatient) references patient(idPatient)
);
create or replace view facturePatient as select reference,date,p.idPatient,nom,idFacture from facture join patient p on p.idPatient = facture.idPatient;
--select reference,date,nom,idFacture from facturePatient;

create table actePatient
(
    idFacture int ,
    idActe int,
    montant double precision,
    foreign key (idFacture) references facture(idFacture),
    foreign key (idActe) references acte(idActe)
);
create or replace view acteFacture as select montant,idFacture,typeActe,actePatient.idActe from actePatient join acte a on a.idActe = actePatient.idActe;
--select montant,idActe,typeActe from acteFacture where idFacture=1;
create table mouvementDepense
(
    idMouvement serial primary key ,
    montant double precision,
    idDepense int,
    date date,
    foreign key (idDepense) references depense(idDepense)
);
create or replace view dept as select montant,d.idDepense,date,typeDepense from mouvementDepense join depense d on d.idDepense = mouvementDepense.idDepense;
create or replace view dateMovement as select date from mouvementDepense union all select date from facture;

create or replace view tActe as select case when sum(montant) is null then 0 else sum(montant) end as vola,typeActe, (budget/12) as budget,date from actePatient join facture f on f.idFacture = actePatient.idFacture right join acte a on a.idActe = actePatient.idActe group by typeActe, budget, date;
--where extract(year from date)=2023 and extract(month from date)=7
create or replace view tDepense as select case when sum(montant) is null then 0 else sum(montant) end as vola,typeDepense,(budget/12) as budget,date from mouvementDepense right join depense d on d.idDepense = mouvementDepense.idDepense group by typeDepense, budget, date;

select case when sum(montant) is null then 0 else sum(montant) end as vola,typeActe, (budget/12) as budget,date from acte full join  actePatient aP on acte.idActe = aP.idActe join facture f on f.idFacture = aP.idFacture
group by typeActe, budget, date;

select * from actePatient;

delete from actePatient where montant=0;

select * from acte left join actePatient aP on acte.idActe = aP.idActe;

select case when sum(montant) is null then 0 else sum(montant) end as vola,typeActe, (budget/12) as budget,
       case when date is null then TO_DATE('2023-01-19', 'YYYY-MM-DD') else date end as date
from actePatient
    join facture f on f.idFacture = actePatient.idFacture
    right join acte a on a.idActe = actePatient.idActe where (extract(year from date)=2023 and extract(month from date)=1) or date IS NULL group by typeActe, budget, date;

select case when sum(montant) is null then 0 else sum(montant) end as vola,typeActe, (budget/12) as budget,
    date
from acte left join actePatient aP on acte.idActe = aP.idActe join facture f on f.idFacture = aP.idFacture
group by date, typeActe, budget;
