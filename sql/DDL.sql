DROP TABLE search CASCADE CONSTRAINT;

CREATE TABLE search(
	customer varchar2(20) not null,
	movie number(6), 
	cafe number(6), 
	telecom number(6),
	transportation number(6),
	onshop number(6),
	offshop number(6),
	food number(6),
	others number(6)
);

INSERT INTO search VALUES('20M',0,0,0,0,0,0,0,0);
INSERT INTO search VALUES('30M',0,0,0,0,0,0,0,0);
INSERT INTO search VALUES('40M',0,0,0,0,0,0,0,0);
INSERT INTO search VALUES('20F',0,0,0,0,0,0,0,0);
INSERT INTO search VALUES('30F',0,0,0,0,0,0,0,0);
INSERT INTO search VALUES('40F',0,0,0,0,0,0,0,0);

commit;