CREATE KEYSPACE IF NOT EXISTS booksdb WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE IF NOT EXISTS booksdb.books (id TimeUUID PRIMARY KEY, title text, author text, cover text);
CREATE INDEX IF NOT EXISTS booktitleindex ON booksdb.books(title);
CREATE INDEX IF NOT EXISTS bookauthorindex ON booksdb.books(author);