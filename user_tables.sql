DROP TABLE IF EXISTS public.freelancer_languages;
DROP TABLE IF EXISTS public.languages;
DROP TABLE IF EXISTS public.reviews;
DROP TABLE IF EXISTS public.orders;
DROP TABLE IF EXISTS public.payment_methods;
DROP TABLE IF EXISTS public.invoices;
DROP TABLE IF EXISTS public.services;
DROP TABLE IF EXISTS public.gigs;
DROP TABLE IF EXISTS public.categories;
DROP TABLE IF EXISTS public.freelancers;
DROP TABLE IF EXISTS public.users;

CREATE TABLE public.users (
    id SERIAL UNIQUE NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    surname VARCHAR(50) NOT NULL ,
    mail VARCHAR(50) NOT NULL ,
    password TEXT NOT NULL ,
    registration_date date NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE public.freelancers (
    freelance_id SERIAL UNIQUE NOT NULL ,
    alias VARCHAR(50) NOT NULL ,
    description text
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
    FOREIGN KEY (freelancer_id) REFERENCES public.freelancers (freelance_id) ,
    FOREIGN KEY (language_id) REFERENCES public.languages (id)
);

CREATE TABLE public.categories (
    id SERIAL NOT NULL ,
    category_name VARCHAR(50) NOT NULL ,
    description text ,
    PRIMARY KEY  (id)
);

CREATE TABLE public.gigs (
    id SERIAL NOT NULL ,
    freelancer_id INT NOT NULL,
    category_id INT NOT NULL,
    gig_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id) ,
    FOREIGN KEY (freelancer_id) REFERENCES public.freelancers (freelance_id) ,
    FOREIGN KEY (category_id) REFERENCES  public.categories (id)
);

CREATE TABLE public.reviews (
    id SERIAL NOT NULL ,
    customer_id INT NOT NULL ,
    gig_id INT NOT NULL ,
    content TEXT ,
    rating INT NOT NULL ,
    PRIMARY KEY (id) ,
    FOREIGN KEY (customer_id) REFERENCES public.users(id) ,
    FOREIGN KEY (gig_id) REFERENCES public.gigs(id)
);

CREATE TABLE public.orders (
    id SERIAL NOT NULL ,
    customer_id INT NOT NULL ,
    order_date date NOT NULL,
    PRIMARY KEY (id) ,
    FOREIGN KEY (customer_id) REFERENCES public.users
);

CREATE TABLE public.payment_methods(
    id SERIAL NOT NULL ,
    method_name VARCHAR(50) NOT NULL ,
    PRIMARY KEY (id)
);

CREATE TABLE public.invoices(
    id SERIAL NOT NULL ,
    payment_method_id INT NOT NULL ,
    payment_date date ,
    billing_address VARCHAR(250) ,
    PRIMARY KEY (id) ,
    FOREIGN KEY (payment_method_id) REFERENCES public.payment_methods (id)
);

CREATE TABLE public.services(
    id SERIAL NOT NULL ,
    gig_id INT NOT NULL ,
    order_id INT NOT NULL ,
    invoice_id INT NOT NULL ,
    price INT NOT NULL ,
    description text ,
    PRIMARY KEY (id) ,
    FOREIGN KEY (gig_id) REFERENCES public.gigs (id),
    FOREIGN KEY (order_id) REFERENCES public.orders (id),
    FOREIGN KEY (invoice_id) REFERENCES public.invoices (id)
 );