CREATE DATABASE Movie
GO
USE Movie
GO

CREATE TABLE Person
(
	IDPerson INT PRIMARY KEY IDENTITY,
	FirstName NVARCHAR(300),
	LastName NVARCHAR(300)
)
GO

CREATE PROCEDURE createActor
	@IDMovie int,
	@FirstName NVARCHAR(300),
	@LastName NVARCHAR(300)
AS
BEGIN
	DECLARE @Id int
	IF EXISTS (SELECT * FROM Person WHERE FirstName=@FirstName AND LastName=@LastName)
	BEGIN
		SELECT @Id=P.IDPerson
		FROM Person AS P
		WHERE FirstName=@FirstName AND LastName=@LastName
	END
	ELSE
	BEGIN
		INSERT INTO Person VALUES(@FirstName,@LastName)
		SET @Id=SCOPE_IDENTITY()	
	END
	IF NOT EXISTS (SELECT * FROM MovieActor AS MA WHERE MA.MovieID=@IDMovie AND MA.ActorID=@Id)
	BEGIN
	   INSERT INTO MovieActor VALUES(@IDMovie,@Id)
	END
END
GO






CREATE PROCEDURE updatePerson
	@FirstName NVARCHAR(300),
	@LastName NVARCHAR(300),
	@IDPerson INT
AS
BEGIN
	UPDATE Person SET LastName=@LastName,FirstName=@FirstName
	WHERE IDPerson=@IDPerson
END
GO

CREATE PROCEDURE selectPerson
	@IDPerson INT
AS
BEGIN
	SELECT * FROM Person
	WHERE IDPerson=@IDPerson
END
GO

CREATE PROCEDURE selectActors
	@IDMovie int
AS
BEGIN
	SELECT *
	FROM Person AS P JOIN MovieActor AS M ON P.IDPerson=M.ActorID
	WHERE M.MovieID=@IDMovie
END
GO

CREATE PROCEDURE selectAllActors
AS
BEGIN
	SELECT DISTINCT  P.IDPerson,P.FirstName,P.LastName
	FROM Person AS P JOIN MovieActor AS M ON P.IDPerson=M.ActorID
END
GO

CREATE PROCEDURE selectAllDirecotrs
AS
BEGIN
	SELECT DISTINCT  P.IDPerson,P.FirstName,P.LastName
	FROM Person AS P JOIN MovieDirector AS M ON P.IDPerson=M.DirectorID
END
GO

CREATE PROCEDURE deletePersonMovie
	@MovieID INT,
	@PersonID INT,
	@Mode INT
AS
BEGIN
	IF @Mode=1
	BEGIN
		DELETE MovieActor
		WHERE MovieID=@MovieID AND ActorID=@PersonID
	END
	ELSE IF @Mode=2
	BEGIN
		DELETE MovieDirector
		WHERE MovieID=@MovieID AND DirectorID=@PersonID
	END

END
GO


CREATE PROCEDURE selectPersonMovies
	@PersonID INT,
	@Mode INT
AS
BEGIN
	IF @Mode=1
	BEGIN
			SELECT *
			FROM Movie as M join  MovieActor AS MA ON M.IDMovie=MA.MovieID
			WHERE ActorID=@PersonID
	END
	ELSE IF @Mode=2
	BEGIN
			SELECT *
			FROM Movie as M join  MovieDirector AS MA ON M.IDMovie=MA.MovieID
			WHERE DirectorID=@PersonID
	END
END
GO


CREATE TABLE Movie
(
	IDMovie INT PRIMARY KEY IDENTITY,
	Title NVARCHAR(300),
	PublishedDate NVARCHAR(90),
	Description NVARCHAR(900),
	OriginalName NVARCHAR(300),
	Duration INT,
	Genre NVARCHAR(300),
	Poster NVARCHAR(90),
	Start NVARCHAR(300)
)
GO

CREATE PROCEDURE createMovie
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(90),
	@Description NVARCHAR(900),
	@OriginalName NVARCHAR(300),
	@Duration INT,
	@Genre NVARCHAR(300),
	@Poster NVARCHAR(90),
	@Start NVARCHAR(300),
	@Id INT OUTPUT
AS
BEGIN
		INSERT INTO Movie VALUES(@Title,@PublishedDate,@Description,@OriginalName,@Duration,@Genre,@Poster,@Start)
		SET @Id=SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE updateMovie
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(90),
	@Description NVARCHAR(900),
	@OriginalName NVARCHAR(300),
	@Duration INT,
	@Genre NVARCHAR(300),
	@Poster NVARCHAR(90),
	@Start NVARCHAR(300),
	@IDDirecotor INT,
	@IdMovie INT 
AS
BEGIN
	UPDATE Movie 
	SET 
		Title=@Title,
		PublishedDate=@PublishedDate,
		Description=@Description,
		OriginalName=@OriginalName,
		Duration=@Duration,
		Genre=@Genre,
		Poster=@Poster,
		Start=@Start
	WHERE IDMovie=@IdMovie

	UPDATE MovieDirector
	SET DirectorID=@IDDirecotor
	WHERE MovieID=@IdMovie
END
GO

CREATE PROCEDURE deleteMovie
	@IDMovie INT
AS
BEGIN
	DELETE FROM MovieActor
	WHERE MovieID=@IDMovie
	DELETE FROM MovieDirector
	WHERE MovieID=@IDMovie
	DELETE FROM Movie
	WHERE IDMovie=@IDMovie
END
GO

CREATE PROCEDURE selectMovie
	@IDMoive INT
AS
BEGIN
	SELECT *
	FROM Movie
	WHERE IDMovie=@IDMoive
END
GO

CREATE PROCEDURE selectMovies
AS
BEGIN
	SELECT * FROM Movie
END
GO


CREATE TABLE MovieActor
(
	IDMovieActoras INT PRIMARY KEY IDENTITY,
	MovieID INT CONSTRAINT FK_Movie FOREIGN KEY REFERENCES Movie(IDMovie),
	ActorID INT CONSTRAINT FK_Actor FOREIGN KEY REFERENCES Person(IDPerson)
)
GO

CREATE PROCEDURE addActorMovie
	@IDMovie INT,
	@IDActor INT
AS
BEGIN
	INSERT INTO MovieActor VALUES(@IDMovie,@IDActor)
END
GO

CREATE PROCEDURE deleteActorsMovie
	@MovieID INT
AS
BEGIN
	DELETE MovieActor
	WHERE MovieID=@MovieID 
END
GO
CREATE TABLE MovieDirector
(
	IDMovieDirector INT PRIMARY KEY IDENTITY,
	MovieID INT CONSTRAINT FK_Movie2 FOREIGN KEY REFERENCES Movie(IDMovie),
	DirectorID INT CONSTRAINT FK_Director FOREIGN KEY REFERENCES Person(IDPerson)
)
GO

CREATE PROCEDURE createDirector
	@IDMovie int,
	@FirstName NVARCHAR(300),
	@LastName NVARCHAR(300)
AS
BEGIN
	DECLARE @Id int
	IF EXISTS (SELECT * FROM Person WHERE FirstName=@FirstName AND LastName=@LastName)
	BEGIN
		SELECT @Id=P.IDPerson
		FROM Person AS P
		WHERE FirstName=@FirstName AND LastName=@LastName
	END
	ELSE
	BEGIN
		INSERT INTO Person VALUES(@FirstName,@LastName)
		SET @Id=SCOPE_IDENTITY()	
	END
	INSERT INTO MovieDirector VALUES(@IDMovie,@Id)
END
GO

CREATE PROCEDURE selectDirector
	@IDMovie int
AS
BEGIN
	SELECT *
	FROM Person AS P JOIN MovieDirector AS M ON P.IDPerson=M.DirectorID
	WHERE M.MovieID=@IDMovie
END
GO




CREATE TABLE AppUser
(
	IDAppUser INT PRIMARY KEY IDENTITY,
	Username NVARCHAR(300),
	Password NVARCHAR(300),
	Admin bit
)
GO

CREATE PROC addUser
	@Username NVARCHAR(300),
	@Password NVARCHAR(300)
AS
BEGIN
		IF NOT EXISTS (SELECT * FROM AppUser as A WHERE A.Password=@Password AND Username=@Username)
	BEGIN
		INSERT INTO AppUser VALUES(@Username,@Password,0)
		return 1
	END
	ELSE
		return 0

END
GO


CREATE PROC selectUser
	@Username NVARCHAR(300),
	@Password NVARCHAR(300),
	@Admin bit OUTPUT
AS
BEGIN
			IF EXISTS (SELECT * FROM AppUser AS A WHERE A.Password=@Password AND A.Username=@Username)
	BEGIN
		SELECT @Admin=A.Admin
		FROM AppUser as A
		WHERE Username=@Username AND Password=@Password
		RETURN 1
	END
	ELSE 
		RETURN 0
END
GO


CREATE PROC deleteData
AS
BEGIN
	DELETE FROM MovieActor
	DELETE FROM MovieDirector
	DELETE FROM Movie
	DELETE FROM Person
END
GO

CREATE PROC createAdmin
AS
BEGIN
	INSERT INTO AppUser VALUES('Admin','C7 AD 44 CB AD 76 2A 5D A0 A4 52 F9 E8 54 FD C1 E0 E7 A5 2A 38 01 5F 23 F3 EA B1 D8 0B 93 1D D4 72 63 4D FA C7 1C D3 4E BC 35 D1 6A B7 FB 8A 90 C8 1F 97 51 13 D6 C7 53 8D C6 9D D8 DE 90 77 EC',1)
END
GO


EXEC createAdmin
GO

