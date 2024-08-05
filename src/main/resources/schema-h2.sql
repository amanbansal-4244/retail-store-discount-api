
CREATE TABLE Customer (
  id BIGINT PRIMARY KEY auto_increment,
  name VARCHAR(32) NOT NULL,
  customerType VARCHAR(32) NOT NULL,
  phoneNumber VARCHAR(64) NOT NULL,
  createdAt DATE NOT NULL,
  updatedAt DATE NOT NULL,
  version INTEGER NOT NULL
  
  CONSTRAINT UC_Customer UNIQUE (id,phoneNumber)
 );