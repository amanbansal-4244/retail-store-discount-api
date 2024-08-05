
-- Always use ANSI default string literal format for date i.e. YYYY-MM-DD like below.

-- In Oracle, you need to convert it to date using function to_date([value],[format] prior to insertion as below.


INSERT INTO public.Customer(ID, NAME, CUSTOMER_TYPE, PHONE_NO, CREATED_AT, UPDATED_AT, VERSION)
VALUES (12345, 'Hardy', 'STORE', '1234567890', '2023-01-11', '2024-01-11', 1);

INSERT INTO public.Customer(ID, NAME, CUSTOMER_TYPE, PHONE_NO, CREATED_AT, UPDATED_AT, VERSION)
VALUES (23456, 'Sam', 'AFFLILIATE', '3123323323', '2023-01-11', '2024-01-11', 1);

INSERT INTO public.Customer(ID, NAME, CUSTOMER_TYPE, PHONE_NO, CREATED_AT, UPDATED_AT, VERSION)
VALUES (34567, 'Marvik', 'NORMAL', '313213131', '2021-01-11', '2023-05-11', 1);


INSERT INTO public.Customer(ID, NAME, CUSTOMER_TYPE, PHONE_NO, CREATED_AT, UPDATED_AT, VERSION)
VALUES (123452, 'Aman', 'STORE', '12345678901', '2023-01-11', '2024-03-11', 1);