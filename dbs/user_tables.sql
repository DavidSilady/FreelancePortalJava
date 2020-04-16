DROP TABLE IF EXISTS public.freelancer_languages;
DROP TABLE IF EXISTS public.languages;
DROP TABLE IF EXISTS public.reviews;
DROP TABLE IF EXISTS public.services;
DROP TABLE IF EXISTS public.gigs;
DROP TABLE IF EXISTS public.categories;
DROP TABLE IF EXISTS public.freelancers;
DROP TABLE IF EXISTS public.orders;
DROP TABLE IF EXISTS public.invoices;
DROP TABLE IF EXISTS public.users;
DROP TABLE IF EXISTS public.payment_methods;

CREATE TABLE public.users (
    user_id SERIAL UNIQUE NOT NULL ,
    name VARCHAR(50) NOT NULL ,
    surname VARCHAR(50) NOT NULL ,
    email VARCHAR(50) NOT NULL ,
    password TEXT NOT NULL ,
    registration_date date NOT NULL ,
    PRIMARY KEY (user_id)
);

CREATE TABLE public.freelancers (
    freelance_id SERIAL UNIQUE NOT NULL ,
    alias VARCHAR(50) NOT NULL ,
    description text
) INHERITS (public.users);

CREATE TABLE public.languages (
    language_id SERIAL NOT NULL ,
    language_name VARCHAR(50) NOT NULL ,
    PRIMARY KEY (language_id)
);

CREATE TABLE public.freelancer_languages (
    fl_id SERIAL NOT NULL ,
    freelancer_id INT NOT NULL ,
    language_id INT NOT NULL ,
    PRIMARY KEY (fl_id) ,
    FOREIGN KEY (freelancer_id) REFERENCES public.freelancers (freelance_id) ,
    FOREIGN KEY (language_id) REFERENCES public.languages (language_id)
);

CREATE TABLE public.categories (
    category_id SERIAL NOT NULL ,
    category_name VARCHAR(50) NOT NULL ,
    description text ,
    PRIMARY KEY  (category_id)
);

CREATE TABLE public.gigs (
    gig_id SERIAL NOT NULL ,
    freelancer_id INT NOT NULL,
    category_id INT NOT NULL,
    gig_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (gig_id) ,
    FOREIGN KEY (freelancer_id) REFERENCES public.freelancers (freelance_id) ,
    FOREIGN KEY (category_id) REFERENCES  public.categories (category_id)
);

CREATE TABLE public.reviews (
    review_id SERIAL NOT NULL ,
    customer_id INT NOT NULL ,
    gig_id INT NOT NULL ,
    content TEXT ,
    rating INT NOT NULL ,
    PRIMARY KEY (review_id) ,
    FOREIGN KEY (customer_id) REFERENCES public.users(user_id) ,
    FOREIGN KEY (gig_id) REFERENCES public.gigs(gig_id)
);

CREATE TABLE public.orders (
    order_id SERIAL NOT NULL ,
    customer_id INT NOT NULL ,
    order_date date NOT NULL,
    PRIMARY KEY (order_id) ,
    FOREIGN KEY (customer_id) REFERENCES public.users (user_id)
);

CREATE TABLE public.payment_methods(
    payment_method_id SERIAL NOT NULL ,
    method_name VARCHAR(50) NOT NULL ,
    PRIMARY KEY (payment_method_id)
);

CREATE TABLE public.invoices(
    invoice_id SERIAL NOT NULL ,
    payment_method_id INT NOT NULL ,
    payment_date date ,
    billing_address VARCHAR(250) ,
    PRIMARY KEY (invoice_id) ,
    FOREIGN KEY (payment_method_id) REFERENCES public.payment_methods (payment_method_id)
);

CREATE TABLE public.services(
    service_id SERIAL NOT NULL ,
    gig_id INT NOT NULL ,
    order_id INT NOT NULL ,
    invoice_id INT NOT NULL ,
    price INT NOT NULL ,
    description text ,
    PRIMARY KEY (service_id) ,
    FOREIGN KEY (gig_id) REFERENCES public.gigs (gig_id),
    FOREIGN KEY (order_id) REFERENCES public.orders (order_id),
    FOREIGN KEY (invoice_id) REFERENCES public.invoices (invoice_id)
 );