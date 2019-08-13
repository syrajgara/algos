select distinct name from employees;

-- specify the number of records to return
select * from employees LIMIT 10;           // mysql, hive
select * from employees where rownum <= 11; // oracle

-- specify the number of records to return in an ordered list of records. (ie. after the order by clause is evaluated)
select TOP 10 * from employees;

-- 10th highest salary
SELECT salary FROM employee e1 where 9 = (select count(distinct salary) from employee e2 where e2.salary < e1.salary);
SELECT TOP (1) Salary
FROM (SELECT DISTINCT TOP (10) Salary FROM Employee ORDER BY Salary DESC)
ORDER BY Salary;


SELECT companies.permalink,
       companies.name,
       companies.status,
       COUNT(investments.investor_permalink) AS investors
  FROM companies
  LEFT JOIN investments
    ON companies.permalink = investments.company_permalink
 WHERE NVL(investments.funded_year, 0) > (companies.founded_year + 5)
 GROUP BY companies.permalink, companies.name, companies.status


SELECT companies.permalink,
       companies.founded_at_clean,
       companies.founded_at_clean::timestamp + INTERVAL '1 week' AS plus_one_week
  FROM companies
 WHERE founded_at_clean IS NOT NULL;


SELECT incidnt_num,
       location,
       my_date,
       LEFT(my_date, 10) AS cleaned_my_date,
       RIGHT(my_date, LENGTH(my_date) - 11) AS cleaned_time,
       SUBSTR(my_date, 4, 2) AS day,
       TRIM(both '()' FROM location),
       CONCAT(day_of_week, ', ', LEFT(my_date, 10)) AS day_and_my_date,
       COALESCE(descript, 'No Description')
  FROM tutorial.sf_crime_incidents_2014_01;