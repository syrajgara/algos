SELECT sub.*
  FROM (
        SELECT *
          FROM incidents
         WHERE day_of_week = 'Friday'
       ) sub
 WHERE sub.resolution = 'NONE';


SELECT *
  FROM incidents
 WHERE my_date IN ( SELECT my_date
                      FROM incidents
                  ORDER BY my_date
                     LIMIT 5
                  );

SELECT incidents.*, sub.incidents_per_date AS incidents_that_day
  FROM incidents
  JOIN ( SELECT my_date, COUNT(incidnt_num) AS incidents_per_date
           FROM incidents
          GROUP BY 1
       ) sub
    ON incidents.my_date = sub.my_date
 ORDER BY sub.incidents DESC, time;


-- find start times of user sessions -- 30 mins of inactivity starts a session
select user_id, timestamp as session_start
from users ua
where not exists (select 1
                  from users ub
                  where ub.user_id = ua.user_id
                  and ub.timestamp between (ua.timestamp - 30) and ua.timestamp);

