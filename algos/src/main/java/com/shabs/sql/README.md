https://www.toptal.com/sql/interview-questions

CAP - NoSQL DBs
C - Consistency
A - Availability
P - Partition Tolerance

ACID - RDBMS
Atomicity - each transaction is all or nothing  
Consistency - db will move from one valid to another valid state
Isolation - each transaction is independent of other and will be executed serially
Durable - once committed, it will be permanent

* https://community.modeanalytics.com/sql/tutorial/introduction-to-sql/

(order of parsing query)

(8)  SELECT (9) [DISTINCT] (11) [TOP <number> [PERCENT]] <select_list>
(1)  FROM <left_table>
(3)  [LEFT|RIGHT|FULL] JOIN <right_table>
(2)       ON <join_condition>
(4)  WHERE <where_condition> [ [NOT] [IN|EXISTS|BETWEEN] ]
(5)  GROUP BY <group_by_list>
(6)  WITH {CUBE | ROLLUP}
(7)  HAVING <having_condition>
(10) ORDER BY <order_by_list> [ASC|DESC]


* INNER JOIN (or ***JOIN***)
    simple join between multiple tables

FROM t1 
JOIN t2 ON t2.c1 = t1.c1
JOIN t3 ON t3.c1 = t2.c1 AND t3.c2 = t1.c2
...


* LEFT OUTER JOIN (or ***LEFT JOIN***)
    get all records from LEFT table

FROM leftTable 
LEFT JOIN rightTable ON rightTable.c1 = leftTable.c1


* RIGHT OUTER JOIN (or ***RIGHT JOIN***)
    get all records from the RIGHT table

    
* FULL OUTER JOIN (or ***FULL JOIN***)
    get all records from both left and right table
    

* GROUP BY ... HAVING ...
    group by customer_id having count(customer_id) > 10
    
    
* WITH
    allows to name a subquery, so that it can be used in multiple places in the parent query

WITH <alias_name> AS (sql_subquery_statement)
SELECT column_list FROM <alias_name>, tablename
WHERE <join_condition>    


* UNION | UNION ALL


* NULL doesnt equal to anything .. not even another NULL
    equating to NULL will not give TRUE or FALSE, will give UNKNOWN
    so have to use IS NULL or IS NOT NULL
    NOT IN (1, 2 ... , NULL) => this always evaluates to FALSE, due to the NULL in the set, and looking for NOT IN
    
    
* ANY
    WHERE column_name operator ANY (SELECT column_name FROM table_name WHERE condition);
    WHERE column_name operator ALL (SELECT column_name FROM table_name WHERE condition);


SELECT - extracts data from a database
UPDATE - updates data in a database
DELETE - deletes data from a database
INSERT INTO - inserts new data into a database

CREATE DATABASE - creates a new database
ALTER DATABASE - modifies a database

CREATE TABLE - creates a new table
ALTER TABLE - modifies a table
DROP TABLE - deletes a table

CREATE INDEX - creates an index (search key)
DROP INDEX - deletes an index


* WINDOW FUNCTION
A window function performs a calculation across a set of table rows that are somehow related to the current row.
This is comparable to the type of calculation that can be done with an aggregate function. 
But unlike regular aggregate functions, use of a window function does not cause rows to become grouped 
into a single output row — the rows retain their separate identities. 
Behind the scenes, the window function is able to access more than just the current row of the query result.

Truncate vs Delete - no rollback vs rollback before commit, truncate frees the space

NVL(value, defaultValue)
