--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2024-02-09 10:40:04

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 222 (class 1259 OID 26792)
-- Name: alternativas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alternativas (
    id bigint NOT NULL,
    alternativa character varying(255),
    pergunta bigint,
    actived boolean,
    created timestamp with time zone,
    updated timestamp with time zone,
    pontuacao bigint
);


ALTER TABLE public.alternativas OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 26798)
-- Name: editais; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.editais (
    id bigint NOT NULL,
    edital character varying(255),
    data_inicio date,
    data_fim date,
    qtd_vagas integer,
    arquivo character varying,
    actived boolean,
    created timestamp with time zone,
    updated timestamp with time zone
);


ALTER TABLE public.editais OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 26767)
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 26782)
-- Name: funcoes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.funcoes (
    id bigint NOT NULL,
    funcao character varying(255),
    actived boolean,
    created timestamp with time zone,
    updated timestamp with time zone,
    edital bigint
);


ALTER TABLE public.funcoes OWNER TO postgres;

--
-- TOC entry 226 (class 1259 OID 26806)
-- Name: id_seq_alternativa; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_alternativa
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_alternativa OWNER TO postgres;

--
-- TOC entry 3426 (class 0 OID 0)
-- Dependencies: 226
-- Name: id_seq_alternativa; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_alternativa OWNED BY public.alternativas.id;


--
-- TOC entry 225 (class 1259 OID 26805)
-- Name: id_seq_edital; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_edital
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_edital OWNER TO postgres;

--
-- TOC entry 3427 (class 0 OID 0)
-- Dependencies: 225
-- Name: id_seq_edital; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_edital OWNED BY public.editais.id;


--
-- TOC entry 223 (class 1259 OID 26797)
-- Name: id_seq_funcao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_funcao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_funcao OWNER TO postgres;

--
-- TOC entry 3428 (class 0 OID 0)
-- Dependencies: 223
-- Name: id_seq_funcao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_funcao OWNED BY public.funcoes.id;


--
-- TOC entry 229 (class 1259 OID 26850)
-- Name: id_seq_inscricao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_inscricao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_inscricao OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 26893)
-- Name: inscricao_respostas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inscricao_respostas (
    id bigint NOT NULL,
    inscricao bigint,
    created timestamp with time zone,
    updated timestamp with time zone,
    actived boolean,
    pergunta bigint,
    alternativa bigint
);


ALTER TABLE public.inscricao_respostas OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 26913)
-- Name: id_seq_inscricao_resposta; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_inscricao_resposta
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_inscricao_resposta OWNER TO postgres;

--
-- TOC entry 3429 (class 0 OID 0)
-- Dependencies: 234
-- Name: id_seq_inscricao_resposta; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_inscricao_resposta OWNED BY public.inscricao_respostas.id;


--
-- TOC entry 219 (class 1259 OID 26776)
-- Name: perguntas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.perguntas (
    id bigint NOT NULL,
    pergunta character varying(255),
    created timestamp with time zone,
    updated timestamp with time zone,
    actived boolean,
    funcao bigint
);


ALTER TABLE public.perguntas OWNER TO postgres;

--
-- TOC entry 220 (class 1259 OID 26781)
-- Name: id_seq_pergunta; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_pergunta
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_pergunta OWNER TO postgres;

--
-- TOC entry 3430 (class 0 OID 0)
-- Dependencies: 220
-- Name: id_seq_pergunta; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_pergunta OWNED BY public.perguntas.id;


--
-- TOC entry 231 (class 1259 OID 26882)
-- Name: pontuacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pontuacao (
    id bigint NOT NULL,
    pontuacao bigint,
    inscricao bigint
);


ALTER TABLE public.pontuacao OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 26892)
-- Name: id_seq_pontuacao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_pontuacao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_pontuacao OWNER TO postgres;

--
-- TOC entry 3431 (class 0 OID 0)
-- Dependencies: 232
-- Name: id_seq_pontuacao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_pontuacao OWNED BY public.pontuacao.id;


--
-- TOC entry 214 (class 1259 OID 26729)
-- Name: id_seq_roles; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_roles
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_roles OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 26845)
-- Name: situacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.situacao (
    id bigint NOT NULL,
    situacao character varying(20),
    created timestamp with time zone,
    updated timestamp with time zone,
    actived boolean
);


ALTER TABLE public.situacao OWNER TO postgres;

--
-- TOC entry 230 (class 1259 OID 26851)
-- Name: id_seq_situacao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_situacao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_situacao OWNER TO postgres;

--
-- TOC entry 3432 (class 0 OID 0)
-- Dependencies: 230
-- Name: id_seq_situacao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_situacao OWNED BY public.situacao.id;


--
-- TOC entry 217 (class 1259 OID 26766)
-- Name: id_seq_user; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_user
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_user OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 26840)
-- Name: inscricao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.inscricao (
    id bigint NOT NULL,
    usuario bigint,
    situacao bigint,
    actived boolean,
    created timestamp with time zone,
    updated timestamp with time zone,
    edital bigint,
    funcao bigint,
    pontuacao bigint
);


ALTER TABLE public.inscricao OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 26730)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    id bigint NOT NULL,
    role character varying(255)
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 26754)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    name character varying(255),
    email character varying(80),
    password character varying(255),
    role bigint,
    actived boolean,
    created timestamp with time zone,
    updated timestamp with time zone,
    data_de_nascimento timestamp with time zone,
    rua character varying(255),
    bairro character varying(255),
    telefone character varying(20),
    cpf character varying(30),
    cep character varying(30),
    cidade character varying(30)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3408 (class 0 OID 26792)
-- Dependencies: 222
-- Data for Name: alternativas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alternativas (id, alternativa, pergunta, actived, created, updated, pontuacao) FROM stdin;
3	Espelização	1	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	10
4	MBA	1	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	20
5	Faculdade	2	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	5
6	Online	2	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	3
7	Sim	3	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	10
1	sdasdsa	3	t	2024-01-23 08:34:13.843693-03	2024-01-23 10:19:30.17353-03	3
8	Não	3	t	2024-01-01 00:00:00-03	2024-01-23 10:19:35.30831-03	7
9	Ensino fundamental incompleto	4	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	1
12	Curso técnico ou profissionalizante de panificação	6	t	2024-01-23 08:35:50.653797-03	2024-01-23 08:35:50.653797-03	4
13	Nenhum	6	t	2024-01-23 08:35:54.709771-03	2024-01-23 08:35:54.709771-03	0
10	Ensino médio incompleto	4	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	3
11	Ensino médio completo	4	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	6
14	teste	8	t	2024-02-09 08:11:51.98685-03	2024-02-09 08:11:51.98685-03	1
15	abc	9	t	2024-02-09 09:31:47.889964-03	2024-02-09 09:31:47.889964-03	12
\.


--
-- TOC entry 3410 (class 0 OID 26798)
-- Dependencies: 224
-- Data for Name: editais; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.editais (id, edital, data_inicio, data_fim, qtd_vagas, arquivo, actived, created, updated) FROM stdin;
48	fdsgfdgfdgfdgfdgfd	2025-11-22	2025-11-22	23	fdsgfdgfdgfdgfdgfd	f	2024-02-08 11:09:54.583678-03	2024-02-09 08:10:56.816229-03
39	testeeeedfdsfsdesdasdase	2025-11-22	2025-11-22	23	testeeeedfdsfsdesdasdase	f	2024-02-08 09:09:06.672052-03	2024-02-09 08:10:58.705273-03
49	Edital secundario	2023-11-22	2025-11-22	23	Edital secundario	t	2024-02-09 08:11:05.520281-03	2024-02-09 08:11:36.636457-03
51	Edital ABC	2023-11-22	2025-11-22	23	Edital ABC	t	2024-02-09 09:30:55.943534-03	2024-02-09 09:30:55.943534-03
7	Edital Teste	2024-01-14	2025-01-31	25	editalteste	t	2024-01-16 12:39:59.88762-03	2024-01-29 08:10:13.861955-03
\.


--
-- TOC entry 3404 (class 0 OID 26767)
-- Dependencies: 218
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	<< Flyway Baseline >>	BASELINE	<< Flyway Baseline >>	\N	null	2024-01-15 10:58:58.194664	0	t
\.


--
-- TOC entry 3407 (class 0 OID 26782)
-- Dependencies: 221
-- Data for Name: funcoes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.funcoes (id, funcao, actived, created, updated, edital) FROM stdin;
2	Professor	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	7
1	Padeiro	t	2024-01-01 00:00:00-03	2024-01-22 10:36:48.095545-03	7
17	sdasdasfds	f	2024-01-22 10:15:54.056228-03	2024-01-22 10:37:30.426833-03	7
16	Carteiro	t	2024-01-22 10:15:14.748918-03	2024-02-05 10:27:30.083079-03	7
50	Teste	t	2024-02-09 08:11:43.184008-03	2024-02-09 08:11:43.184008-03	49
52	ABC	t	2024-02-09 09:31:38.088509-03	2024-02-09 09:31:38.088509-03	51
\.


--
-- TOC entry 3413 (class 0 OID 26840)
-- Dependencies: 227
-- Data for Name: inscricao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inscricao (id, usuario, situacao, actived, created, updated, edital, funcao, pontuacao) FROM stdin;
1009	9	1	t	2024-02-08 13:19:49.592555-03	2024-02-08 13:19:49.592555-03	7	2	4
1010	13	1	t	2024-02-08 13:20:42.864218-03	2024-02-08 13:20:42.864218-03	7	2	15
1011	9	1	t	2024-02-09 08:12:01.433436-03	2024-02-09 08:12:01.433436-03	49	50	1
1012	9	1	t	2024-02-09 09:31:57.173419-03	2024-02-09 09:31:57.173419-03	51	52	12
\.


--
-- TOC entry 3419 (class 0 OID 26893)
-- Dependencies: 233
-- Data for Name: inscricao_respostas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inscricao_respostas (id, inscricao, created, updated, actived, pergunta, alternativa) FROM stdin;
38	1009	2024-02-08 13:19:49.648554-03	2024-02-08 13:19:49.648554-03	t	4	9
39	1009	2024-02-08 13:19:49.662555-03	2024-02-08 13:19:49.662555-03	t	3	1
40	1009	2024-02-08 13:19:49.666554-03	2024-02-08 13:19:49.666554-03	t	6	13
41	1010	2024-02-08 13:20:42.869217-03	2024-02-08 13:20:42.869217-03	t	4	9
42	1010	2024-02-08 13:20:42.873217-03	2024-02-08 13:20:42.873217-03	t	3	7
43	1010	2024-02-08 13:20:42.88022-03	2024-02-08 13:20:42.88022-03	t	6	12
44	1011	2024-02-09 08:12:01.445481-03	2024-02-09 08:12:01.446473-03	t	8	14
45	1012	2024-02-09 09:31:57.18749-03	2024-02-09 09:31:57.18749-03	t	9	15
\.


--
-- TOC entry 3405 (class 0 OID 26776)
-- Dependencies: 219
-- Data for Name: perguntas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.perguntas (id, pergunta, created, updated, actived, funcao) FROM stdin;
1	Cursos Complementares	2024-01-15 12:57:53.214121-03	2024-01-16 08:41:17.130761-03	t	1
2	Palestras	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	t	1
7	teste	2024-01-22 11:07:03.481814-03	2024-01-22 11:10:32.793189-03	t	16
4	Nível de Escolaridade com respectivo comprovante (diploma, certificado, certidão, declaração, histório escolar)	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	t	2
3	É Servidor Público Municipal?	2024-01-01 00:00:00-03	2024-01-23 08:18:57.995564-03	t	2
6	Cursos complementares - a título de pontuação conforme item 6 do Edital.	2024-01-22 10:59:14.668437-03	2024-01-22 10:59:14.668437-03	t	2
8	teste	2024-02-09 08:11:46.544617-03	2024-02-09 08:11:46.544617-03	t	50
9	ABC	2024-02-09 09:31:43.816433-03	2024-02-09 09:31:43.816433-03	t	52
\.


--
-- TOC entry 3417 (class 0 OID 26882)
-- Dependencies: 231
-- Data for Name: pontuacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pontuacao (id, pontuacao, inscricao) FROM stdin;
\.


--
-- TOC entry 3401 (class 0 OID 26730)
-- Dependencies: 215
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, role) FROM stdin;
1	ADMIN
2	CANDIDATO
\.


--
-- TOC entry 3414 (class 0 OID 26845)
-- Dependencies: 228
-- Data for Name: situacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.situacao (id, situacao, created, updated, actived) FROM stdin;
1	Aguardando	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	t
2	Aprovado	2024-01-30 00:00:00-03	2024-01-30 00:00:00-03	t
\.


--
-- TOC entry 3402 (class 0 OID 26754)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, email, password, role, actived, created, updated, data_de_nascimento, rua, bairro, telefone, cpf, cep, cidade) FROM stdin;
1	Root	root@codiub.com.br	$2a$10$6FQr1U2zGloNMuYpE27BBuIQ60N7uJfwoNe5TPERuQqXE5swHIeou	1	t	2024-01-15 00:00:00-03	2024-01-15 10:18:51.850795-03	\N	\N	\N	\N	\N	\N	\N
19	gfhgf	hfghf@gmail.com	$2a$10$yUHPiIR9Rh/XPub.6HHhAuH5MAT997Y4IpyAdSbIgtFb7/cg5Hkyi	2	t	2024-05-09 09:22:18.683015-03	2024-02-09 09:22:18.683015-03	\N	\N	\N	\N	\N	\N	\N
20	sadfsa	fgdf@gmail.com	$2a$10$d8t/0kbYyONB0bgFYuOQ2e31i.z3RhQin5.j3eyRnXVFARHlAgwMO	1	t	2024-05-09 09:22:24.291179-03	2024-02-09 09:22:24.291179-03	\N	\N	\N	\N	\N	\N	\N
21	fdsfvsdf	sfsad@gmail.com	$2a$10$SM909F0/arayL.ohL62gRuxfTfGp9uluuqrbUEMLB/f50lkhT7My.	2	t	2024-05-09 09:22:30.63088-03	2024-02-09 09:22:30.63088-03	\N	\N	\N	\N	\N	\N	\N
22	fdsfsd	sdsa@gmail.com	$2a$10$dm9dsH2g9VHcnI3dcL8Mf.sgrmIndnlap7Knjrm9W5P6yxJ6hSnyi	2	t	2024-05-09 09:22:36.175755-03	2024-02-09 09:22:36.175755-03	\N	\N	\N	\N	\N	\N	\N
18	yyy	yyyy@gmail.com	$2a$10$vw0w5Xg0jii5p8/EScHnPOwNif98va0.rkZTD0wUzbWe1gzDvmpSO	1	f	2024-05-09 09:22:10.920723-03	2024-02-09 10:28:58.048069-03	\N	\N	\N	\N	\N	\N	\N
9	Guilherme Barbosa Rocha	guilherme.strg@gmail.com	$2a$10$7nvBZsA4OQ5WOehJRVryPOKTv6FGwtKs8sKNTOIdHUu3daFVmEwTq	2	t	2024-01-19 10:20:59.929589-03	2024-01-30 13:00:28.798145-03	2000-11-29 00:00:00-02	Rua Arnaldo	Jardins	34 98403-9344	019.733.946-85	01973394565	Uberaba
16	aaaa	aaa@gmail.com	$2a$10$Rv9GV3a2G4l7Q8EwDww5MeMFYntc8LAOqgldMpmgTk5ilX7DbrXo6	2	t	2024-02-09 09:21:15.962845-03	2024-02-09 09:21:15.962845-03	\N	\N	\N	\N	\N	\N	\N
17	bbbb	bbb@gmailc.om	$2a$10$MzF5jZVld4mWL1ajmVhuDuVSy4gYKsvEW0UQjaAkWBPuf./bkh/cW	2	t	2024-02-09 09:21:24.015355-03	2024-02-09 09:21:24.015355-03	\N	\N	\N	\N	\N	\N	\N
13	Guilherme Barbosa Rocha	guilherme@gmail.com	$2a$10$LRUOMN4sk9vLi.HaX9IpceN5LINYTcmzXaCoBw.SRCBS5hQ3YPMLq	2	t	2024-03-08 13:20:33.813486-03	2024-02-08 13:20:33.813486-03	2000-11-29 00:00:00-02	Rua Arnaldo Augusto dos Reis	Jardim	(34) 98403-9344	019.733.946-85	38035-205	Uberaba
14	teste	teste@gmail.com	$2a$10$bQIG3rcEBqXwNoMQkrMZ7ecKe40GMv0vbZeikpWGXptCRSKBfvtAu	1	t	2024-03-09 09:20:59.949817-03	2024-02-09 09:20:59.949817-03	\N	\N	\N	\N	\N	\N	\N
15	testeeee	testeeee@gmail.com	$2a$10$CSFIfQAFTAm1/M45iAenMebFudVTglT.xgv7SKkReLkEP0NpWWeba	2	t	2024-01-09 09:21:06.440072-03	2024-02-09 09:21:06.440072-03	\N	\N	\N	\N	\N	\N	\N
23	dsadsa	dasdas@gmail.com	$2a$10$AOW.Z3mnMXFRRFf0Zzsw0eUeimh6fBQ3mKAnfKS0/PIMDEzyeyw1q	1	t	2024-02-09 09:22:42.462168-03	2024-02-09 09:22:42.462168-03	\N	\N	\N	\N	\N	\N	\N
24	ghgf	ghf@gmail.com	$2a$10$q/VUwYgn7ke9aNd9Ta.11OBoc941XnEPpqOSrBM8bBG5AFm7KbQTq	1	t	2024-02-09 09:22:48.466826-03	2024-02-09 09:22:48.466826-03	\N	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 3433 (class 0 OID 0)
-- Dependencies: 226
-- Name: id_seq_alternativa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_alternativa', 15, true);


--
-- TOC entry 3434 (class 0 OID 0)
-- Dependencies: 225
-- Name: id_seq_edital; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_edital', 1, false);


--
-- TOC entry 3435 (class 0 OID 0)
-- Dependencies: 223
-- Name: id_seq_funcao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_funcao', 52, true);


--
-- TOC entry 3436 (class 0 OID 0)
-- Dependencies: 229
-- Name: id_seq_inscricao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_inscricao', 1012, true);


--
-- TOC entry 3437 (class 0 OID 0)
-- Dependencies: 234
-- Name: id_seq_inscricao_resposta; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_inscricao_resposta', 45, true);


--
-- TOC entry 3438 (class 0 OID 0)
-- Dependencies: 220
-- Name: id_seq_pergunta; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_pergunta', 9, true);


--
-- TOC entry 3439 (class 0 OID 0)
-- Dependencies: 232
-- Name: id_seq_pontuacao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_pontuacao', 1, false);


--
-- TOC entry 3440 (class 0 OID 0)
-- Dependencies: 214
-- Name: id_seq_roles; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_roles', 1, false);


--
-- TOC entry 3441 (class 0 OID 0)
-- Dependencies: 230
-- Name: id_seq_situacao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_situacao', 1, false);


--
-- TOC entry 3442 (class 0 OID 0)
-- Dependencies: 217
-- Name: id_seq_user; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_user', 24, true);


--
-- TOC entry 3237 (class 2606 OID 26804)
-- Name: editais editais_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editais
    ADD CONSTRAINT editais_pkey PRIMARY KEY (id);


--
-- TOC entry 3228 (class 2606 OID 26774)
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 3233 (class 2606 OID 26786)
-- Name: funcoes funcoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcoes
    ADD CONSTRAINT funcoes_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 26758)
-- Name: users id_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT id_user PRIMARY KEY (id);


--
-- TOC entry 3239 (class 2606 OID 26844)
-- Name: inscricao inscricao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_pkey PRIMARY KEY (id);


--
-- TOC entry 3245 (class 2606 OID 26897)
-- Name: inscricao_respostas inscricao_respostas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao_respostas
    ADD CONSTRAINT inscricao_respostas_pkey PRIMARY KEY (id);


--
-- TOC entry 3235 (class 2606 OID 26834)
-- Name: alternativas pk_alternativa; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alternativas
    ADD CONSTRAINT pk_alternativa PRIMARY KEY (id);


--
-- TOC entry 3224 (class 2606 OID 26736)
-- Name: roles pk_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT pk_id PRIMARY KEY (id) INCLUDE (id);


--
-- TOC entry 3231 (class 2606 OID 26780)
-- Name: perguntas pk_id_pergunta; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perguntas
    ADD CONSTRAINT pk_id_pergunta PRIMARY KEY (id);


--
-- TOC entry 3243 (class 2606 OID 26886)
-- Name: pontuacao pontuacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pontuacao
    ADD CONSTRAINT pontuacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3241 (class 2606 OID 26849)
-- Name: situacao situacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.situacao
    ADD CONSTRAINT situacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3229 (class 1259 OID 26775)
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- TOC entry 3249 (class 2606 OID 26835)
-- Name: alternativas fk_alternativa_pergunta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alternativas
    ADD CONSTRAINT fk_alternativa_pergunta FOREIGN KEY (pergunta) REFERENCES public.perguntas(id) NOT VALID;


--
-- TOC entry 3248 (class 2606 OID 26807)
-- Name: funcoes fk_funcao_edital; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcoes
    ADD CONSTRAINT fk_funcao_edital FOREIGN KEY (edital) REFERENCES public.editais(id) NOT VALID;


--
-- TOC entry 3247 (class 2606 OID 26787)
-- Name: perguntas fk_pergunta_funcao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perguntas
    ADD CONSTRAINT fk_pergunta_funcao FOREIGN KEY (funcao) REFERENCES public.funcoes(id) NOT VALID;


--
-- TOC entry 3246 (class 2606 OID 26759)
-- Name: users fk_user_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_user_role FOREIGN KEY (role) REFERENCES public.roles(id);


--
-- TOC entry 3250 (class 2606 OID 26872)
-- Name: inscricao inscricao_edital_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_edital_fkey FOREIGN KEY (edital) REFERENCES public.editais(id) NOT VALID;


--
-- TOC entry 3251 (class 2606 OID 26877)
-- Name: inscricao inscricao_funcao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_funcao_fkey FOREIGN KEY (funcao) REFERENCES public.funcoes(id) NOT VALID;


--
-- TOC entry 3255 (class 2606 OID 26898)
-- Name: inscricao_respostas inscricao_respostas_inscricao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao_respostas
    ADD CONSTRAINT inscricao_respostas_inscricao_fkey FOREIGN KEY (inscricao) REFERENCES public.inscricao(id);


--
-- TOC entry 3256 (class 2606 OID 26903)
-- Name: inscricao_respostas inscricao_respostas_pergunta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao_respostas
    ADD CONSTRAINT inscricao_respostas_pergunta_fkey FOREIGN KEY (pergunta) REFERENCES public.perguntas(id);


--
-- TOC entry 3257 (class 2606 OID 26908)
-- Name: inscricao_respostas inscricao_respostas_resposta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao_respostas
    ADD CONSTRAINT inscricao_respostas_resposta_fkey FOREIGN KEY (alternativa) REFERENCES public.alternativas(id);


--
-- TOC entry 3252 (class 2606 OID 26862)
-- Name: inscricao inscricao_situacao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_situacao_fkey FOREIGN KEY (situacao) REFERENCES public.situacao(id) NOT VALID;


--
-- TOC entry 3253 (class 2606 OID 26867)
-- Name: inscricao inscricao_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_user_fkey FOREIGN KEY (usuario) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3254 (class 2606 OID 26887)
-- Name: pontuacao pontuacao_inscricao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pontuacao
    ADD CONSTRAINT pontuacao_inscricao_fkey FOREIGN KEY (inscricao) REFERENCES public.inscricao(id) NOT VALID;


-- Completed on 2024-02-09 10:40:04

--
-- PostgreSQL database dump complete
--

