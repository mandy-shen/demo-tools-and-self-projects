CREATE TABLE IF NOT EXISTS eg1_table
( 
  Case_Type varchar(100) NOT NULL,
  Ban_No char(8) NOT NULL,
  Cmpy_Name varchar(100) NOT NULL,
  Cmpy_Address varchar(100),
  Invest_Amt varchar(100),
  Approved_Date char(8) NOT NULL,
  Dissolution_Date char(8),
);

CREATE INDEX index_eg1_table ON eg1_table (Case_Type);
