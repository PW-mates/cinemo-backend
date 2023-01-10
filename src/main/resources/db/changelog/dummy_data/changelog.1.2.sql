--liquibase formatted sql

--comment: Add dummy data to tables

--changeset Dasha:4


INSERT INTO movie_category VALUES (1, 'action', 'action-category'),
                                  (2, 'adventure', 'adventure-category'),
                                  (3, 'comedy', 'comedy-category'),
                                  (4, 'drama', 'drama-category'),
                                  (5, 'fantasy', 'fantasy-category'),
                                  (6, 'horror', 'horror-category'),
                                  (7, 'musicals', 'musicals-category'),
                                  (8, 'mystery', 'mystery-category'),
                                  (9, 'romance', 'romance-category'),
                                  (10, 'science fiction', 'science-fiction-category'),
                                  (11, 'sports', 'sports-category'),
                                  (12, 'thriller', 'thriller-category'),
                                  (13, 'western', 'western-category');
