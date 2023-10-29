create schema investments;
use investments;

create table if not exists tbl_financial_assets
(
    id varchar(255) charset utf8 not null
    primary key,
    ticket varchar(45) not null,
    enterpriseName varchar(255) not null,
    unitAssetValue decimal(10,2) not null,
    numberAssets int not null
    );

create table if not exists tbl_portfolio
(
    id varchar(255) charset utf8 not null
    primary key,
    description varchar(255) not null
    );

create table if not exists tbl_portfolio_assets
(
    portfolioID varchar(255) charset utf8 not null,
    assetID varchar(255) charset utf8 not null,
    primary key (portfolioID, assetID),
    constraint tbl_portfolio_assets_tbl_financial_assets_id_fk
    foreign key (assetID) references tbl_financial_assets (id),
    constraint tbl_portfolio_assets_tbl_portfolio_id_fk
    foreign key (portfolioID) references tbl_portfolio (id)
    );

create table if not exists tbl_transaction
(
    id varchar(255) charset utf8 not null
    primary key,
    transactionType varchar(45) not null,
    assetID varchar(45) not null,
    numberAssets int not null,
    transactionValue double null
    );

create index tbl_transaction_tbl_financial_assets_id_fk
    on tbl_transaction (assetID);