USE [TattooLoverPlatformDB]
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 9/28/2023 4:32:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin](
	[admin_email] [varchar](50) NOT NULL,
	[username] [nvarchar](20) NOT NULL,
	[password] [nvarchar](20) NOT NULL,
	[full_name] [nvarchar](100) NOT NULL,
	[phone_number] [nvarchar](20) NOT NULL,
	[address] [nvarchar](100) NOT NULL,
	[roleID] [nvarchar](20) NOT NULL,
 CONSTRAINT [PK_Admin] PRIMARY KEY CLUSTERED 
(
	[admin_email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Post]    Script Date: 9/28/2023 4:32:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Post](
	[postID] [nvarchar](20) NOT NULL,
	[post_tittle] [nvarchar](100) NOT NULL,
	[author_name] [nvarchar](100) NOT NULL,
	[update_date] [date] NOT NULL,
	[thumbnail_link] [nvarchar](max) NOT NULL,
	[description] [text] NOT NULL,
	[brief_info] [nvarchar](500) NOT NULL,
	[status] [int] NOT NULL,
	[system_Staff_email] [varchar](50) NOT NULL,
	[admin_email] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Post] PRIMARY KEY CLUSTERED 
(
	[postID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SystemStaff]    Script Date: 9/28/2023 4:32:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SystemStaff](
	[system_Staff_email] [varchar](50) NOT NULL,
	[username] [nvarchar](20) NOT NULL,
	[password] [nvarchar](20) NOT NULL,
	[full_name] [nvarchar](100) NOT NULL,
	[phone_number] [nvarchar](20) NOT NULL,
	[address] [nvarchar](100) NOT NULL,
	[roleID] [nvarchar](20) NOT NULL,
	[admin_email] [varchar](50) NOT NULL,
 CONSTRAINT [PK_SystemStaff] PRIMARY KEY CLUSTERED 
(
	[system_Staff_email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TattooLovers]    Script Date: 9/28/2023 4:32:57 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TattooLovers](
	[tattoo_Lover_email] [varchar](100) NOT NULL,
	[username] [nvarchar](20) NOT NULL,
	[password] [nvarchar](20) NOT NULL,
	[full_name] [nvarchar](100) NOT NULL,
	[phone_number] [nvarchar](20) NOT NULL,
	[address] [nvarchar](100) NOT NULL,
	[roleID] [nvarchar](20) NOT NULL,
	[system_Staff_email] [varchar](50) NOT NULL,
 CONSTRAINT [PK_TattooLovers] PRIMARY KEY CLUSTERED 
(
	[tattoo_Lover_email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE Post
ADD CONSTRAINT FK_Post_SystemStaff
FOREIGN KEY (system_Staff_email)
REFERENCES SystemStaff(system_Staff_email)

ALTER TABLE Post
ADD CONSTRAINT FK_Post_Admin
FOREIGN KEY (admin_email)
REFERENCES Admin(admin_email)

ALTER TABLE Post
ADD CONSTRAINT FK_Post_Admin
FOREIGN KEY (admin_email)
REFERENCES Admin(admin_email)

ALTER TABLE SystemStaff
ADD CONSTRAINT FK_SystemStaff_Admin
FOREIGN KEY (admin_email)
REFERENCES Admin(admin_email)

ALTER TABLE TattooLovers
ADD CONSTRAINT FK_TattooLovers_SystemStaff
FOREIGN KEY (system_Staff_email)
REFERENCES SystemStaff(system_Staff_email)
