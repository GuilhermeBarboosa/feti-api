--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2024-03-05 16:07:45

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
-- TOC entry 221 (class 1259 OID 26792)
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
-- TOC entry 234 (class 1259 OID 34928)
-- Name: arquivos_inscricao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.arquivos_inscricao (
    id bigint NOT NULL,
    caminho_arquivo character varying(255),
    inscricao bigint,
    created timestamp with time zone,
    updated timestamp with time zone,
    actived boolean
);


ALTER TABLE public.arquivos_inscricao OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 26798)
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
-- TOC entry 240 (class 1259 OID 35244)
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
-- TOC entry 220 (class 1259 OID 26782)
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
-- TOC entry 225 (class 1259 OID 26806)
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
-- TOC entry 3466 (class 0 OID 0)
-- Dependencies: 225
-- Name: id_seq_alternativa; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_alternativa OWNED BY public.alternativas.id;


--
-- TOC entry 235 (class 1259 OID 34938)
-- Name: id_seq_arquivos_inscricao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_arquivos_inscricao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_arquivos_inscricao OWNER TO postgres;

--
-- TOC entry 3467 (class 0 OID 0)
-- Dependencies: 235
-- Name: id_seq_arquivos_inscricao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_arquivos_inscricao OWNED BY public.arquivos_inscricao.id;


--
-- TOC entry 224 (class 1259 OID 26805)
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
-- TOC entry 3468 (class 0 OID 0)
-- Dependencies: 224
-- Name: id_seq_edital; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_edital OWNED BY public.editais.id;


--
-- TOC entry 222 (class 1259 OID 26797)
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
-- TOC entry 3469 (class 0 OID 0)
-- Dependencies: 222
-- Name: id_seq_funcao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_funcao OWNED BY public.funcoes.id;


--
-- TOC entry 228 (class 1259 OID 26850)
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
-- TOC entry 232 (class 1259 OID 26893)
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
-- TOC entry 233 (class 1259 OID 26913)
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
-- TOC entry 3470 (class 0 OID 0)
-- Dependencies: 233
-- Name: id_seq_inscricao_resposta; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_inscricao_resposta OWNED BY public.inscricao_respostas.id;


--
-- TOC entry 218 (class 1259 OID 26776)
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
-- TOC entry 219 (class 1259 OID 26781)
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
-- TOC entry 3471 (class 0 OID 0)
-- Dependencies: 219
-- Name: id_seq_pergunta; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_pergunta OWNED BY public.perguntas.id;


--
-- TOC entry 241 (class 1259 OID 35253)
-- Name: permissao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.permissao (
    id bigint NOT NULL,
    permissao character varying(50)
);


ALTER TABLE public.permissao OWNER TO postgres;

--
-- TOC entry 242 (class 1259 OID 35263)
-- Name: id_seq_permissao; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_permissao
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_permissao OWNER TO postgres;

--
-- TOC entry 3472 (class 0 OID 0)
-- Dependencies: 242
-- Name: id_seq_permissao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_permissao OWNED BY public.permissao.id;


--
-- TOC entry 230 (class 1259 OID 26882)
-- Name: pontuacao; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.pontuacao (
    id bigint NOT NULL,
    pontuacao bigint,
    inscricao bigint
);


ALTER TABLE public.pontuacao OWNER TO postgres;

--
-- TOC entry 231 (class 1259 OID 26892)
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
-- TOC entry 3473 (class 0 OID 0)
-- Dependencies: 231
-- Name: id_seq_pontuacao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_pontuacao OWNED BY public.pontuacao.id;


--
-- TOC entry 239 (class 1259 OID 34960)
-- Name: id_seq_role_tela; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_role_tela
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_role_tela OWNER TO postgres;

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
-- TOC entry 227 (class 1259 OID 26845)
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
-- TOC entry 229 (class 1259 OID 26851)
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
-- TOC entry 3474 (class 0 OID 0)
-- Dependencies: 229
-- Name: id_seq_situacao; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_situacao OWNED BY public.situacao.id;


--
-- TOC entry 236 (class 1259 OID 34939)
-- Name: telas; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.telas (
    id bigint NOT NULL,
    identificador character varying(255),
    descricao character varying(255),
    unica boolean
);


ALTER TABLE public.telas OWNER TO postgres;

--
-- TOC entry 238 (class 1259 OID 34959)
-- Name: id_seq_tela; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.id_seq_tela
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.id_seq_tela OWNER TO postgres;

--
-- TOC entry 3475 (class 0 OID 0)
-- Dependencies: 238
-- Name: id_seq_tela; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.id_seq_tela OWNED BY public.telas.id;


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
-- TOC entry 226 (class 1259 OID 26840)
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
-- TOC entry 237 (class 1259 OID 34944)
-- Name: role_tela; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_tela (
    id bigint NOT NULL,
    tela bigint,
    role bigint,
    permissao bigint
);


ALTER TABLE public.role_tela OWNER TO postgres;

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
-- TOC entry 3439 (class 0 OID 26792)
-- Dependencies: 221
-- Data for Name: alternativas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alternativas (id, alternativa, pergunta, actived, created, updated, pontuacao) FROM stdin;
3	Espelização	1	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	10
4	MBA	1	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	20
5	Faculdade	2	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	5
6	Online	2	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	3
8	Não	3	t	2024-01-01 00:00:00-03	2024-01-23 10:19:35.30831-03	7
9	Ensino fundamental incompleto	4	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	1
12	Curso técnico ou profissionalizante de panificação	6	t	2024-01-23 08:35:50.653797-03	2024-01-23 08:35:50.653797-03	4
13	Nenhum	6	t	2024-01-23 08:35:54.709771-03	2024-01-23 08:35:54.709771-03	0
10	Ensino médio incompleto	4	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	3
11	Ensino médio completo	4	t	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	6
14	teste	8	t	2024-02-09 08:11:51.98685-03	2024-02-09 08:11:51.98685-03	1
15	abc	9	t	2024-02-09 09:31:47.889964-03	2024-02-09 09:31:47.889964-03	12
1	Sim	3	t	2024-01-23 08:34:13.843693-03	2024-02-21 13:25:38.576879-03	3
\.


--
-- TOC entry 3452 (class 0 OID 34928)
-- Dependencies: 234
-- Data for Name: arquivos_inscricao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.arquivos_inscricao (id, caminho_arquivo, inscricao, created, updated, actived) FROM stdin;
150	aaa{id-1104}	1104	2024-02-23 10:19:47.280619-03	2024-02-23 10:19:47.280619-03	t
151	bbb{id-1104}	1104	2024-02-23 10:19:47.285602-03	2024-02-23 10:19:47.285602-03	t
152	aaa{id-1105}	1105	2024-02-23 10:20:57.3253-03	2024-02-23 10:20:57.3253-03	t
153	bbb{id-1105}	1105	2024-02-23 10:20:57.329295-03	2024-02-23 10:20:57.329295-03	t
154	{id-1106}	1106	2024-03-04 15:39:10.943773-03	2024-03-04 15:39:10.943773-03	t
155	vfdgfd{id-1112}	1112	2024-03-04 15:47:24.261383-03	2024-03-04 15:47:24.261383-03	t
156	fsdfds{id-1113}	1113	2024-03-04 15:48:44.26743-03	2024-03-04 15:48:44.26743-03	t
157	yjytjgh{id-1113}	1113	2024-03-04 15:48:44.268427-03	2024-03-04 15:48:44.268427-03	t
\.


--
-- TOC entry 3441 (class 0 OID 26798)
-- Dependencies: 223
-- Data for Name: editais; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.editais (id, edital, data_inicio, data_fim, qtd_vagas, arquivo, actived, created, updated) FROM stdin;
48	fdsgfdgfdgfdgfdgfd	2025-11-22	2025-11-22	23	fdsgfdgfdgfdgfdgfd	f	2024-02-08 11:09:54.583678-03	2024-02-09 08:10:56.816229-03
39	testeeeedfdsfsdesdasdase	2025-11-22	2025-11-22	23	testeeeedfdsfsdesdasdase	f	2024-02-08 09:09:06.672052-03	2024-02-09 08:10:58.705273-03
49	Edital secundario	2023-11-22	2025-11-22	23	Edital secundario	t	2024-02-09 08:11:05.520281-03	2024-02-09 08:11:36.636457-03
51	Edital ABC	2023-11-22	2025-11-22	23	Edital ABC	t	2024-02-09 09:30:55.943534-03	2024-02-09 09:30:55.943534-03
54	aaaaa aaa	2025-11-22	2025-11-22	23	aaaaa aaa	f	2024-02-23 11:35:42.512155-03	2024-02-26 11:00:13.316549-03
53	uuuuuuuuuu	2023-11-22	2025-11-22	23	aaaa	f	2024-02-23 11:35:34.035898-03	2024-02-26 11:00:14.987047-03
7	Edital Teste	2024-01-14	2025-01-31	25	editalteste	t	2024-01-16 12:39:59.88762-03	2024-01-29 08:10:13.861955-03
\.


--
-- TOC entry 3458 (class 0 OID 35244)
-- Dependencies: 240
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	<< Flyway Baseline >>	BASELINE	<< Flyway Baseline >>	\N	null	2024-03-01 10:50:21.707717	0	t
\.


--
-- TOC entry 3438 (class 0 OID 26782)
-- Dependencies: 220
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
-- TOC entry 3444 (class 0 OID 26840)
-- Dependencies: 226
-- Data for Name: inscricao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inscricao (id, usuario, situacao, actived, created, updated, edital, funcao, pontuacao) FROM stdin;
1105	9	1	t	2024-02-23 10:20:57.31033-03	2024-02-23 10:20:57.31033-03	51	52	12
1104	13	1	t	2024-02-23 10:19:47.230456-03	2024-02-26 11:04:14.643226-03	7	2	5
1106	1	1	f	2024-03-04 15:39:10.823761-03	2024-03-04 15:39:33.911426-03	51	52	12
1107	1	1	f	2024-03-04 15:39:42.635849-03	2024-03-04 15:40:47.105695-03	51	52	12
1108	1	1	f	2024-03-04 15:40:59.322823-03	2024-03-04 15:41:43.840909-03	51	52	12
1109	1	1	f	2024-03-04 15:41:55.821717-03	2024-03-04 15:42:03.249407-03	51	52	12
1110	1	1	f	2024-03-04 15:45:21.53654-03	2024-03-04 15:45:27.483752-03	51	52	12
1111	1	1	f	2024-03-04 15:46:10.503177-03	2024-03-04 15:46:15.956322-03	51	52	12
1112	1	1	f	2024-03-04 15:47:24.144774-03	2024-03-04 15:47:32.859986-03	51	52	12
1113	1	1	f	2024-03-04 15:48:44.173754-03	2024-03-04 15:48:53.348894-03	51	52	12
\.


--
-- TOC entry 3450 (class 0 OID 26893)
-- Dependencies: 232
-- Data for Name: inscricao_respostas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.inscricao_respostas (id, inscricao, created, updated, actived, pergunta, alternativa) FROM stdin;
187	1104	2024-02-23 10:19:47.264626-03	2024-02-23 10:19:47.264626-03	t	4	9
188	1104	2024-02-23 10:19:47.270652-03	2024-02-23 10:19:47.270652-03	t	3	8
189	1104	2024-02-23 10:19:47.274643-03	2024-02-23 10:19:47.274643-03	t	6	12
190	1105	2024-02-23 10:20:57.322309-03	2024-02-23 10:20:57.322309-03	t	9	15
191	1106	2024-03-04 15:39:10.917859-03	2024-03-04 15:39:10.917859-03	t	9	15
192	1107	2024-03-04 15:39:42.641828-03	2024-03-04 15:39:42.641828-03	t	9	15
193	1108	2024-03-04 15:40:59.328828-03	2024-03-04 15:40:59.328828-03	t	9	15
194	1109	2024-03-04 15:41:55.833717-03	2024-03-04 15:41:55.833717-03	t	9	15
195	1110	2024-03-04 15:45:21.64418-03	2024-03-04 15:45:21.64418-03	t	9	15
196	1111	2024-03-04 15:46:10.58889-03	2024-03-04 15:46:10.58889-03	t	9	15
197	1112	2024-03-04 15:47:24.166691-03	2024-03-04 15:47:24.166691-03	t	9	15
198	1113	2024-03-04 15:48:44.26145-03	2024-03-04 15:48:44.26145-03	t	9	15
\.


--
-- TOC entry 3436 (class 0 OID 26776)
-- Dependencies: 218
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
-- TOC entry 3459 (class 0 OID 35253)
-- Dependencies: 241
-- Data for Name: permissao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.permissao (id, permissao) FROM stdin;
1	Somente leitura
2	Leitura e escrita
3	Leitura, escrita e edição
4	Controle total
\.


--
-- TOC entry 3448 (class 0 OID 26882)
-- Dependencies: 230
-- Data for Name: pontuacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.pontuacao (id, pontuacao, inscricao) FROM stdin;
\.


--
-- TOC entry 3455 (class 0 OID 34944)
-- Dependencies: 237
-- Data for Name: role_tela; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role_tela (id, tela, role, permissao) FROM stdin;
233	5	3	1
234	8	3	2
235	1	3	2
236	7	3	4
237	16	3	2
\.


--
-- TOC entry 3433 (class 0 OID 26730)
-- Dependencies: 215
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, role) FROM stdin;
1	ADMIN
2	CANDIDATO
3	SECRETARIO
\.


--
-- TOC entry 3445 (class 0 OID 26845)
-- Dependencies: 227
-- Data for Name: situacao; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.situacao (id, situacao, created, updated, actived) FROM stdin;
1	Aguardando	2024-01-01 00:00:00-03	2024-01-01 00:00:00-03	t
2	Aprovado	2024-01-30 00:00:00-03	2024-01-30 00:00:00-03	t
\.


--
-- TOC entry 3454 (class 0 OID 34939)
-- Dependencies: 236
-- Data for Name: telas; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.telas (id, identificador, descricao, unica) FROM stdin;
1	user	Usuários	f
5	dashboard	Dashboard	t
8	edital	Edital	f
12	funcao	Função	f
16	pergunta	Pergunta	f
20	alternativa	Alternativa	f
24	role	Permissão	f
7	inscricao	Informação sobre inscrição	f
\.


--
-- TOC entry 3434 (class 0 OID 26754)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, email, password, role, actived, created, updated, data_de_nascimento, rua, bairro, telefone, cpf, cep, cidade) FROM stdin;
9	Guilherme Barbosa Rocha	guilherme.strg@gmail.com	$2a$10$M1ilkAXEFzbBGjdjIqNAUujr21HzRNMet6U82LrlBo/rpfcMiFgFi	2	t	2024-01-19 10:20:59.929589-03	2024-03-05 13:54:05.6043-03	\N	\N	\N	\N	\N	\N	\N
18	yyy	yyyy@gmail.com	$2a$10$vw0w5Xg0jii5p8/EScHnPOwNif98va0.rkZTD0wUzbWe1gzDvmpSO	1	f	2024-05-09 09:22:10.920723-03	2024-02-09 10:28:58.048069-03	\N	\N	\N	\N	\N	\N	\N
16	aaaa	aaa@gmail.com	$2a$10$Rv9GV3a2G4l7Q8EwDww5MeMFYntc8LAOqgldMpmgTk5ilX7DbrXo6	2	t	2024-02-09 09:21:15.962845-03	2024-02-09 09:21:15.962845-03	\N	\N	\N	\N	\N	\N	\N
17	bbbb	bbb@gmailc.om	$2a$10$MzF5jZVld4mWL1ajmVhuDuVSy4gYKsvEW0UQjaAkWBPuf./bkh/cW	2	t	2024-02-09 09:21:24.015355-03	2024-02-09 09:21:24.015355-03	\N	\N	\N	\N	\N	\N	\N
15	testeeee	testeeee@gmail.com	$2a$10$CSFIfQAFTAm1/M45iAenMebFudVTglT.xgv7SKkReLkEP0NpWWeba	2	t	2024-01-09 09:21:06.440072-03	2024-02-09 09:21:06.440072-03	\N	\N	\N	\N	\N	\N	\N
14	teste	teste@gmail.com	$2a$10$7nvBZsA4OQ5WOehJRVryPOKTv6FGwtKs8sKNTOIdHUu3daFVmEwTq	1	t	2024-03-09 09:20:59.949817-03	2024-02-09 09:20:59.949817-03	\N	\N	\N	\N	\N	\N	\N
19	gfhgf	hfghf@gmail.com	$2a$10$yUHPiIR9Rh/XPub.6HHhAuH5MAT997Y4IpyAdSbIgtFb7/cg5Hkyi	2	f	2024-05-09 09:22:18.683015-03	2024-02-22 10:15:26.012499-03	\N	\N	\N	\N	\N	\N	\N
1	Root	root@codiub.com.br	$2a$10$6FQr1U2zGloNMuYpE27BBuIQ60N7uJfwoNe5TPERuQqXE5swHIeou	1	t	2024-01-15 00:00:00-03	2024-03-01 13:46:08.758045-03	1990-11-29 00:00:00-02	0000000	00000000000	00 00000-0000	000.000.000-00	000.000.000-00	00000000
13	Guilherme Barbosa Rocha	guilherme@gmail.com	$2a$10$LRUOMN4sk9vLi.HaX9IpceN5LINYTcmzXaCoBw.SRCBS5hQ3YPMLq	2	t	2024-03-08 13:20:33.813486-03	2024-03-01 13:47:15.400159-03	2001-11-29 00:00:00-02	Rua Arnaldo Augusto dos Reis	Jardim	(34) 98403-9344	019.733.946-85	019.733.946-85	Uberaba
28	AAA	a@a.com	$2a$10$6FQr1U2zGloNMuYpE27BBuIQ60N7uJfwoNe5TPERuQqXE5swHIeou	3	t	2024-02-28 13:30:46.54283-03	2024-03-01 14:57:58.465839-03	\N	\N	\N	\N	\N	\N	\N
29	JJJJJJJJJJJ	jjj@gmail.com	$2a$10$CnyOTAErqKuLmZmbuHjiFuySv5ROvBl6FOHZpyqhWtffYoXBuXjRa	1	t	2024-03-04 11:05:43.220721-03	2024-03-04 11:05:43.220721-03	\N	\N	\N	\N	\N	\N	\N
30	BBB	b@b.com	$2a$10$4aSfr1BAGMl1phSOQrSTPO.3D0qDCZ4asEHVh8pOB/h17y/el6GaG	2	t	2024-03-05 09:34:06.072327-03	2024-03-05 09:34:06.072327-03	\N	\N	\N	\N	\N	\N	\N
\.


--
-- TOC entry 3476 (class 0 OID 0)
-- Dependencies: 225
-- Name: id_seq_alternativa; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_alternativa', 15, true);


--
-- TOC entry 3477 (class 0 OID 0)
-- Dependencies: 235
-- Name: id_seq_arquivos_inscricao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_arquivos_inscricao', 157, true);


--
-- TOC entry 3478 (class 0 OID 0)
-- Dependencies: 224
-- Name: id_seq_edital; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_edital', 1, false);


--
-- TOC entry 3479 (class 0 OID 0)
-- Dependencies: 222
-- Name: id_seq_funcao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_funcao', 54, true);


--
-- TOC entry 3480 (class 0 OID 0)
-- Dependencies: 228
-- Name: id_seq_inscricao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_inscricao', 1113, true);


--
-- TOC entry 3481 (class 0 OID 0)
-- Dependencies: 233
-- Name: id_seq_inscricao_resposta; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_inscricao_resposta', 198, true);


--
-- TOC entry 3482 (class 0 OID 0)
-- Dependencies: 219
-- Name: id_seq_pergunta; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_pergunta', 9, true);


--
-- TOC entry 3483 (class 0 OID 0)
-- Dependencies: 242
-- Name: id_seq_permissao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_permissao', 1, false);


--
-- TOC entry 3484 (class 0 OID 0)
-- Dependencies: 231
-- Name: id_seq_pontuacao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_pontuacao', 1, false);


--
-- TOC entry 3485 (class 0 OID 0)
-- Dependencies: 239
-- Name: id_seq_role_tela; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_role_tela', 237, true);


--
-- TOC entry 3486 (class 0 OID 0)
-- Dependencies: 214
-- Name: id_seq_roles; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_roles', 8, true);


--
-- TOC entry 3487 (class 0 OID 0)
-- Dependencies: 229
-- Name: id_seq_situacao; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_situacao', 1, false);


--
-- TOC entry 3488 (class 0 OID 0)
-- Dependencies: 238
-- Name: id_seq_tela; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_tela', 1, false);


--
-- TOC entry 3489 (class 0 OID 0)
-- Dependencies: 217
-- Name: id_seq_user; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_user', 30, true);


--
-- TOC entry 3264 (class 2606 OID 34932)
-- Name: arquivos_inscricao arquivos_inscricao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.arquivos_inscricao
    ADD CONSTRAINT arquivos_inscricao_pkey PRIMARY KEY (id);


--
-- TOC entry 3254 (class 2606 OID 26804)
-- Name: editais editais_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.editais
    ADD CONSTRAINT editais_pkey PRIMARY KEY (id);


--
-- TOC entry 3270 (class 2606 OID 35251)
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- TOC entry 3250 (class 2606 OID 26786)
-- Name: funcoes funcoes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcoes
    ADD CONSTRAINT funcoes_pkey PRIMARY KEY (id);


--
-- TOC entry 3246 (class 2606 OID 26758)
-- Name: users id_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT id_user PRIMARY KEY (id);


--
-- TOC entry 3256 (class 2606 OID 26844)
-- Name: inscricao inscricao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_pkey PRIMARY KEY (id);


--
-- TOC entry 3262 (class 2606 OID 26897)
-- Name: inscricao_respostas inscricao_respostas_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao_respostas
    ADD CONSTRAINT inscricao_respostas_pkey PRIMARY KEY (id);


--
-- TOC entry 3273 (class 2606 OID 35257)
-- Name: permissao permissao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.permissao
    ADD CONSTRAINT permissao_pkey PRIMARY KEY (id);


--
-- TOC entry 3252 (class 2606 OID 26834)
-- Name: alternativas pk_alternativa; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alternativas
    ADD CONSTRAINT pk_alternativa PRIMARY KEY (id);


--
-- TOC entry 3244 (class 2606 OID 26736)
-- Name: roles pk_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT pk_id PRIMARY KEY (id) INCLUDE (id);


--
-- TOC entry 3248 (class 2606 OID 26780)
-- Name: perguntas pk_id_pergunta; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perguntas
    ADD CONSTRAINT pk_id_pergunta PRIMARY KEY (id);


--
-- TOC entry 3260 (class 2606 OID 26886)
-- Name: pontuacao pontuacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pontuacao
    ADD CONSTRAINT pontuacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3268 (class 2606 OID 34948)
-- Name: role_tela role_tela_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_tela
    ADD CONSTRAINT role_tela_pkey PRIMARY KEY (id);


--
-- TOC entry 3258 (class 2606 OID 26849)
-- Name: situacao situacao_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.situacao
    ADD CONSTRAINT situacao_pkey PRIMARY KEY (id);


--
-- TOC entry 3266 (class 2606 OID 34943)
-- Name: telas tela_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.telas
    ADD CONSTRAINT tela_pkey PRIMARY KEY (id);


--
-- TOC entry 3271 (class 1259 OID 35252)
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- TOC entry 3286 (class 2606 OID 34933)
-- Name: arquivos_inscricao arquivos_inscricao_inscricao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.arquivos_inscricao
    ADD CONSTRAINT arquivos_inscricao_inscricao_fkey FOREIGN KEY (inscricao) REFERENCES public.inscricao(id);


--
-- TOC entry 3277 (class 2606 OID 26835)
-- Name: alternativas fk_alternativa_pergunta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alternativas
    ADD CONSTRAINT fk_alternativa_pergunta FOREIGN KEY (pergunta) REFERENCES public.perguntas(id) NOT VALID;


--
-- TOC entry 3276 (class 2606 OID 26807)
-- Name: funcoes fk_funcao_edital; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.funcoes
    ADD CONSTRAINT fk_funcao_edital FOREIGN KEY (edital) REFERENCES public.editais(id) NOT VALID;


--
-- TOC entry 3275 (class 2606 OID 26787)
-- Name: perguntas fk_pergunta_funcao; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.perguntas
    ADD CONSTRAINT fk_pergunta_funcao FOREIGN KEY (funcao) REFERENCES public.funcoes(id) NOT VALID;


--
-- TOC entry 3274 (class 2606 OID 26759)
-- Name: users fk_user_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_user_role FOREIGN KEY (role) REFERENCES public.roles(id);


--
-- TOC entry 3278 (class 2606 OID 26872)
-- Name: inscricao inscricao_edital_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_edital_fkey FOREIGN KEY (edital) REFERENCES public.editais(id) NOT VALID;


--
-- TOC entry 3279 (class 2606 OID 26877)
-- Name: inscricao inscricao_funcao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_funcao_fkey FOREIGN KEY (funcao) REFERENCES public.funcoes(id) NOT VALID;


--
-- TOC entry 3283 (class 2606 OID 26898)
-- Name: inscricao_respostas inscricao_respostas_inscricao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao_respostas
    ADD CONSTRAINT inscricao_respostas_inscricao_fkey FOREIGN KEY (inscricao) REFERENCES public.inscricao(id);


--
-- TOC entry 3284 (class 2606 OID 26903)
-- Name: inscricao_respostas inscricao_respostas_pergunta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao_respostas
    ADD CONSTRAINT inscricao_respostas_pergunta_fkey FOREIGN KEY (pergunta) REFERENCES public.perguntas(id);


--
-- TOC entry 3285 (class 2606 OID 26908)
-- Name: inscricao_respostas inscricao_respostas_resposta_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao_respostas
    ADD CONSTRAINT inscricao_respostas_resposta_fkey FOREIGN KEY (alternativa) REFERENCES public.alternativas(id);


--
-- TOC entry 3280 (class 2606 OID 26862)
-- Name: inscricao inscricao_situacao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_situacao_fkey FOREIGN KEY (situacao) REFERENCES public.situacao(id) NOT VALID;


--
-- TOC entry 3281 (class 2606 OID 26867)
-- Name: inscricao inscricao_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.inscricao
    ADD CONSTRAINT inscricao_user_fkey FOREIGN KEY (usuario) REFERENCES public.users(id) NOT VALID;


--
-- TOC entry 3282 (class 2606 OID 26887)
-- Name: pontuacao pontuacao_inscricao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.pontuacao
    ADD CONSTRAINT pontuacao_inscricao_fkey FOREIGN KEY (inscricao) REFERENCES public.inscricao(id) NOT VALID;


--
-- TOC entry 3287 (class 2606 OID 35258)
-- Name: role_tela role_tela_permissao_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_tela
    ADD CONSTRAINT role_tela_permissao_fkey FOREIGN KEY (permissao) REFERENCES public.permissao(id) NOT VALID;


--
-- TOC entry 3288 (class 2606 OID 34954)
-- Name: role_tela role_tela_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_tela
    ADD CONSTRAINT role_tela_role_fkey FOREIGN KEY (role) REFERENCES public.roles(id);


--
-- TOC entry 3289 (class 2606 OID 34949)
-- Name: role_tela role_tela_tela_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_tela
    ADD CONSTRAINT role_tela_tela_fkey FOREIGN KEY (tela) REFERENCES public.telas(id);


-- Completed on 2024-03-05 16:07:45

--
-- PostgreSQL database dump complete
--

