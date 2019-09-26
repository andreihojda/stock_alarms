--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: alarm; Type: TABLE; Schema: public; Owner: alertsadmin
--

CREATE TABLE public.alarm (
    id bigint NOT NULL,
    stockid bigint,
    alarmname character varying,
    isactive boolean,
    price numeric,
    pricevariance numeric,
    usersid bigint
);


ALTER TABLE public.alarm OWNER TO alertsadmin;

--
-- Name: alarm_id_seq; Type: SEQUENCE; Schema: public; Owner: alertsadmin
--

CREATE SEQUENCE public.alarm_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alarm_id_seq OWNER TO alertsadmin;

--
-- Name: alarm_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alertsadmin
--

ALTER SEQUENCE public.alarm_id_seq OWNED BY public.alarm.id;


--
-- Name: role; Type: TABLE; Schema: public; Owner: alertsadmin
--

CREATE TABLE public.role (
    id bigint NOT NULL,
    rolename character varying
);


ALTER TABLE public.role OWNER TO alertsadmin;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: alertsadmin
--

CREATE SEQUENCE public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_seq OWNER TO alertsadmin;

--
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alertsadmin
--

ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;


--
-- Name: stock; Type: TABLE; Schema: public; Owner: alertsadmin
--

CREATE TABLE public.stock (
    id bigint NOT NULL,
    ticker character varying
);


ALTER TABLE public.stock OWNER TO alertsadmin;

--
-- Name: stock_id_seq; Type: SEQUENCE; Schema: public; Owner: alertsadmin
--

CREATE SEQUENCE public.stock_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.stock_id_seq OWNER TO alertsadmin;

--
-- Name: stock_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alertsadmin
--

ALTER SEQUENCE public.stock_id_seq OWNED BY public.stock.id;


--
-- Name: userroles; Type: TABLE; Schema: public; Owner: alertsadmin
--

CREATE TABLE public.userroles (
    id bigint NOT NULL,
    roleid bigint,
    userid bigint
);


ALTER TABLE public.userroles OWNER TO alertsadmin;

--
-- Name: userroles_id_seq; Type: SEQUENCE; Schema: public; Owner: alertsadmin
--

CREATE SEQUENCE public.userroles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.userroles_id_seq OWNER TO alertsadmin;

--
-- Name: userroles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alertsadmin
--

ALTER SEQUENCE public.userroles_id_seq OWNED BY public.userroles.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: alertsadmin
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    username character varying,
    email character varying,
    password character varying
);


ALTER TABLE public.users OWNER TO alertsadmin;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: alertsadmin
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO alertsadmin;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: alertsadmin
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: alarm id; Type: DEFAULT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.alarm ALTER COLUMN id SET DEFAULT nextval('public.alarm_id_seq'::regclass);


--
-- Name: role id; Type: DEFAULT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.role ALTER COLUMN id SET DEFAULT nextval('public.role_id_seq'::regclass);


--
-- Name: stock id; Type: DEFAULT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.stock ALTER COLUMN id SET DEFAULT nextval('public.stock_id_seq'::regclass);


--
-- Name: userroles id; Type: DEFAULT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.userroles ALTER COLUMN id SET DEFAULT nextval('public.userroles_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: alarm; Type: TABLE DATA; Schema: public; Owner: alertsadmin
--

COPY public.alarm (id, stockid, alarmname, isactive, price, pricevariance, usersid) FROM stdin;
16	3	alarmTest2	f	139.3600	3	7
17	2	alarmTest3	f	139.3600	3	7
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: alertsadmin
--

COPY public.role (id, rolename) FROM stdin;
\.


--
-- Data for Name: stock; Type: TABLE DATA; Schema: public; Owner: alertsadmin
--

COPY public.stock (id, ticker) FROM stdin;
1	MSFT
2	GOOG
3	AAPL
4	{\n    "stockTicker" : "6758"\n}
\.


--
-- Data for Name: userroles; Type: TABLE DATA; Schema: public; Owner: alertsadmin
--

COPY public.userroles (id, roleid, userid) FROM stdin;
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: alertsadmin
--

COPY public.users (id, username, email, password) FROM stdin;
7	admin	hojda.andrei@gmail.com	$2a$10$wxI4GbVRcRWEBKKn53N3.OPoeMjLk19uSgd6yFEatkX6vEjukbGHi
\.


--
-- Name: alarm_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alertsadmin
--

SELECT pg_catalog.setval('public.alarm_id_seq', 17, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alertsadmin
--

SELECT pg_catalog.setval('public.role_id_seq', 1, false);


--
-- Name: stock_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alertsadmin
--

SELECT pg_catalog.setval('public.stock_id_seq', 4, true);


--
-- Name: userroles_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alertsadmin
--

SELECT pg_catalog.setval('public.userroles_id_seq', 1, false);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: alertsadmin
--

SELECT pg_catalog.setval('public.users_id_seq', 8, true);


--
-- Name: alarm alarm_pkey; Type: CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.alarm
    ADD CONSTRAINT alarm_pkey PRIMARY KEY (id);


--
-- Name: users email_unique; Type: CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT email_unique UNIQUE (email);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: stock stock_pkey; Type: CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.stock
    ADD CONSTRAINT stock_pkey PRIMARY KEY (id);


--
-- Name: alarm unique_stock_user; Type: CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.alarm
    ADD CONSTRAINT unique_stock_user UNIQUE (stockid, usersid);


--
-- Name: users username_unique; Type: CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT username_unique UNIQUE (username);


--
-- Name: userroles userroles_pkey; Type: CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.userroles
    ADD CONSTRAINT userroles_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: alarm alarm_stockid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.alarm
    ADD CONSTRAINT alarm_stockid_fkey FOREIGN KEY (stockid) REFERENCES public.stock(id);


--
-- Name: alarm alarm_usersid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.alarm
    ADD CONSTRAINT alarm_usersid_fkey FOREIGN KEY (usersid) REFERENCES public.users(id);


--
-- Name: userroles userroles_roleid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.userroles
    ADD CONSTRAINT userroles_roleid_fkey FOREIGN KEY (roleid) REFERENCES public.role(id);


--
-- Name: userroles userroles_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: alertsadmin
--

ALTER TABLE ONLY public.userroles
    ADD CONSTRAINT userroles_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

