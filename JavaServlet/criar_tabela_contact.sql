CREATE TABLE contact
(
  id serial NOT NULL,
  name character varying(120),
  phone character varying(30),
  email character varying(50),
  CONSTRAINT contact_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE contact
  OWNER TO postgres;