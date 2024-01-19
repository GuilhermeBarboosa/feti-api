--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

-- Started on 2024-01-15 10:36:10

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

SET default_tablespace = '';

SET default_table_access_method = heap;

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
    updated timestamp with time zone
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 3326 (class 0 OID 26730)
-- Dependencies: 215
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (id, role) FROM stdin;
1	ADMIN
\.


--
-- TOC entry 3327 (class 0 OID 26754)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, email, password, role, actived, created, updated) FROM stdin;
1	Root	root@codiub.com.br	$2a$10$6FQr1U2zGloNMuYpE27BBuIQ60N7uJfwoNe5TPERuQqXE5swHIeou	1	t	2024-01-15 00:00:00-03	2024-01-15 10:18:51.850795-03
\.


--
-- TOC entry 3334 (class 0 OID 0)
-- Dependencies: 214
-- Name: id_seq_roles; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_roles', 1, false);


--
-- TOC entry 3335 (class 0 OID 0)
-- Dependencies: 217
-- Name: id_seq_user; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.id_seq_user', 2, true);


--
-- TOC entry 3181 (class 2606 OID 26758)
-- Name: users id_user; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT id_user PRIMARY KEY (id);


--
-- TOC entry 3179 (class 2606 OID 26736)
-- Name: roles pk_id; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT pk_id PRIMARY KEY (id) INCLUDE (id);


--
-- TOC entry 3182 (class 2606 OID 26759)
-- Name: users fk_user_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_user_role FOREIGN KEY (role) REFERENCES public.roles(id);


-- Completed on 2024-01-15 10:36:10

--
-- PostgreSQL database dump complete
--

