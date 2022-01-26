DROP TABLE todo
;

CREATE TABLE todo(
  id         SERIAL,
  task       VARCHAR(60),
  delflg     INTEGER,
  createtime TIMESTAMP,
  PRIMARY KEY (id)
)
;

INSERT INTO todo (task, delflg, createtime) VALUES ('‚²‚Í‚ñ‚ğH‚×‚é', 0, '2022-01-22 09:10:56')
;
INSERT INTO todo (task, delflg, createtime) VALUES ('ƒSƒƒSƒ‚·‚é', 1, '2022-01-24 14:08:23')
;
INSERT INTO todo (task, delflg, createtime) VALUES ('•‚ğ–‚­', 0, '2022-01-23 14:08:23')
;
