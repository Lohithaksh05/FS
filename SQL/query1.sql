/*
Write a SQL query using CTEs and window functions to find the top 3 highest-rated 
movies in each decade. Display the following columns:
    decade
    movie_name
    avg_rating

The output should show the top 3 movies per decade based on average ratings, 
ordered by decade and descending rating.

================================================================================
Artists (id, name, email, phone_number)
Movies (id, name, release_year, budget)
Directions (id, Artist_id, Movie_id)
Reviews (id, date_of_review, Artist_id, Movie_id, rating)
================================================================================

Output:
-------
+--------+--------------------+------------+                                                                                                          
| decade | movie_name         | avg_rating |                                                                                                          
+--------+--------------------+------------+                                                                                                          
|   1970 | Movie A            |       9.00 |                                                                                                          
|   1980 | Retro Future       |       8.50 |                                                                                                          
|   1980 | Movie B            |       7.67 |                                                                                                          
|   1990 | The Silent Network |       9.00 |                                                                                                          
|   1990 | Movie C            |       8.00 |                                                                                                          
|   2000 | The Quantum Code   |       9.00 |                                                                                                          
|   2010 | Digital Mirage     |       8.50 |                                                                                                          
|   2020 | Codebreakers       |       8.00 |                                                                                                          
+--------+--------------------+------------+ 


*/

WITH MovieRatings AS (
    SELECT 
        m.name AS movie_name,
        (m.release_year / 10) * 10 AS decade,
        AVG(r.rating) AS avg_rating
    FROM 
        Movies m
    JOIN 
        Reviews r ON m.id = r.Movie_id
    GROUP BY 
        m.id, m.name, decade
), RankedMovies AS (
    SELECT 
        movie_name,
        decade,
        avg_rating,
        ROW_NUMBER() OVER (PARTITION BY decade ORDER BY avg_rating DESC) AS rank
    FROM 
        MovieRatings
)
SELECT 
    decade,
    movie_name,
    avg_rating
FROM
    RankedMovies
WHERE 
    rank <= 3
ORDER BY 
    decade, avg_rating DESC;