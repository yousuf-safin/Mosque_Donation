USE master;

-- Create the database
CREATE DATABASE MosjidDonation;
GO

-- Use the newly created database
USE MosjidDonation;
GO

-- Create Users table
CREATE TABLE Users (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    Password NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(20) NOT NULL
);

-- Create Admin table
CREATE TABLE Admin (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Username NVARCHAR(50) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    Password NVARCHAR(100) NOT NULL,
    Phone NVARCHAR(20) NOT NULL
);

-- Create Mosque table
CREATE TABLE Mosque (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(100) NOT NULL,
    Location NVARCHAR(200) NOT NULL
);

-- Create Zakat table
CREATE TABLE Zakat (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Amount DECIMAL(18, 2) NOT NULL,
    Date DATE NOT NULL,
    MosqueId INT NOT NULL,
    UserId INT NOT NULL,
    DistributionId INT
);

-- Create Donation table
CREATE TABLE Donation (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Category NVARCHAR(50) NOT NULL,
    Amount DECIMAL(18, 2) NOT NULL,
    Date DATE NOT NULL,
    MosqueId INT NOT NULL,
    UserId INT NOT NULL,
    DistributionId INT
);

-- Create Distribution table
CREATE TABLE Distribution (
    Id INT PRIMARY KEY IDENTITY(1,1),
    Date DATE NOT NULL,
    DistributedBy INT NOT NULL
);

-- Insert data into Users table
INSERT INTO Users (Username, Email, Password, Phone)
VALUES
    ('John Doe', 'john@example.com', 'password123', '123-456-7890'),
    ('Jane Smith', 'jane@example.com', 'password456', '987-654-3210'),
    ('Ahmed Ali', 'ahmed@example.com', 'password789', '555-555-5555'),
    ('Sarah Jones', 'sarah@example.com', 'passwordabc', '111-222-3333'),
    ('Michael Brown', 'michael@example.com', 'passworddef', '444-444-4444');

-- Insert data into Admin table
INSERT INTO Admin (Username, Email, Password, Phone)
VALUES
    ('admin1', 'admin1@example.com', 'admin_password1', '111-111-1111'),
    ('admin2', 'admin2@example.com', 'admin_password2', '222-222-2222');

-- Insert data into Mosque table
INSERT INTO Mosque (Name, Location)
VALUES
    ('Al-Farooq Mosque', '123 Main Street, City'),
    ('Noor Islamic Center', '456 Elm Avenue, Town'),
    ('Masjid Al-Huda', '789 Oak Lane, Village'),
    ('Masjid An-Nur', '101 Maple Road, Suburb'),
    ('Islamic Center of Peace', '555 Pine Street, Town'),
    ('Al-Iman Mosque', '666 Elm Street, City'),
    ('Masjid Ar-Rahman', '777 Willow Avenue, Village'),
    ('Masjid Al-Amin', '888 Oak Road, Suburb'),
    ('Masjid As-Salam', '999 Maple Avenue, Town'),
    ('Islamic Center of Unity', '111 Cedar Lane, City');

-- Add foreign key constraints

-- Zakat.UserId references Users.Id
ALTER TABLE Zakat
ADD CONSTRAINT FK_Zakat_UserId FOREIGN KEY (UserId) REFERENCES Users(Id)
ON DELETE CASCADE;

-- Zakat.MosqueId references Mosque.Id
ALTER TABLE Zakat
ADD CONSTRAINT FK_Zakat_MosqueId FOREIGN KEY (MosqueId) REFERENCES Mosque(Id);

-- Zakat.DistributionId references Distribution.Id
ALTER TABLE Zakat
ADD CONSTRAINT FK_Zakat_DistributionId FOREIGN KEY (DistributionId) REFERENCES Distribution(Id);

-- Donation.UserId references Users.Id
ALTER TABLE Donation
ADD CONSTRAINT FK_Donation_UserId FOREIGN KEY (UserId) REFERENCES Users(Id)
ON DELETE CASCADE;

-- Donation.MosqueId references Mosque.Id
ALTER TABLE Donation
ADD CONSTRAINT FK_Donation_MosqueId FOREIGN KEY (MosqueId) REFERENCES Mosque(Id);

-- Donation.DistributionId references Distribution.Id
ALTER TABLE Donation
ADD CONSTRAINT FK_Donation_DistributionId FOREIGN KEY (DistributionId) REFERENCES Distribution(Id);

-- Distribution.DistributedBy references Admin.Id
ALTER TABLE Distribution
ADD CONSTRAINT FK_Distribution_DistributedBy FOREIGN KEY (DistributedBy) REFERENCES Admin(Id)
ON DELETE CASCADE;
