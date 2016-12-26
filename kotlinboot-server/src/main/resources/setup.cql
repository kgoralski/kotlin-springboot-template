CREATE KEYSPACE IF NOT EXISTS booksdb WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
CREATE TABLE IF NOT EXISTS books (id TimeUUID PRIMARY KEY, title text, author text, cover text);
CREATE INDEX IF NOT EXISTS booktitleindex ON books(title);
CREATE INDEX IF NOT EXISTS bookauthorindex ON books(author);