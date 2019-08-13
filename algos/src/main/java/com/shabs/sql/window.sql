/*
-- https://community.modeanalytics.com/sql/tutorial/sql-window-functions/

A window function performs a calculation across a set of table rows that are somehow related to the current row.
This is comparable to the type of calculation that can be done with an aggregate function. But unlike regular
aggregate functions, use of a window function does not cause rows to become grouped into a single output row
— the rows retain their separate identities. Behind the scenes, the window function is able to access more than
just the current row of the query result.
*/
-- these aggregates function (ex: SUM) still keep the individual rows and dont group everything into single row

-- without ORDER, considers all rows in the partition for SUM
SELECT duration_seconds,
       SUM(duration_seconds) OVER () AS total_seconds
  FROM tutorial.dc_bikeshare_q1_2012;

-- use ORDER, to consider only previous rows in the partition
SELECT duration_seconds,
       SUM(duration_seconds) OVER (ORDER BY start_time) AS running_total
  FROM tutorial.dc_bikeshare_q1_2012;

SELECT start_terminal,
       duration_seconds,
       SUM(duration_seconds) OVER (PARTITION BY start_terminal ORDER BY start_time) AS running_total
  FROM tutorial.dc_bikeshare_q1_2012
 WHERE start_time < '2012-01-08';
