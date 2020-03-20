
DROP TABLE IF EXISTS public.freelancer_languages;
DROP TABLE IF EXISTS public.freelancers;
DROP TABLE IF EXISTS public.languages;
DROP TABLE IF EXISTS public.users;
CREATE TABLE public.users (
    user_id SERIAL UNIQUE NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    surname VARCHAR(50) NOT NULL ,
    mail VARCHAR(50) NOT NULL ,
    password TEXT NOT NULL ,
    registration_date date NOT NULL ,
    PRIMARY KEY (user_id)
);

CREATE TABLE public.freelancers (
    freelancer_id SERIAL UNIQUE NOT NULL ,
    alias VARCHAR(50) NOT NULL ,
    description text NOT NULL
) INHERITS (public.users);

CREATE TABLE public.languages (
    id SERIAL NOT NULL ,
    language_name VARCHAR(50) NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE public.freelancer_languages (
    id SERIAL NOT NULL ,
    freelancer_id INT NOT NULL ,
    language_id INT NOT NULL ,
    PRIMARY KEY (id) ,
    FOREIGN KEY (freelancer_id) REFERENCES public.freelancers (freelancer_id) ,
    FOREIGN KEY (language_id) REFERENCES public.languages (id)
);