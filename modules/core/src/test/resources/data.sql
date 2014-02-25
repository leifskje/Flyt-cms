INSERT INTO content (ContentId, ContentType, ContentTemplateId, MetaDataTemplateId, DisplayTemplateId, DocumentTypeId, GroupId, Owner, Location, Alias, PublishDate, ExpireDate, ExpireAction, VisibilityStatus, NumberOfNotes, OwnerPerson, RevisionDate, ForumId, OpenInNewWindow, DocumentTypeIdForChildren, IsLocked, RatingScore, NumberOfRatings, IsSearchable, NumberOfComments) VALUES (1, 0, 2, -1, 2, -1, 1, '', null, '/alias/', '2012-01-04 13:21:35', null, 'HIDE', 10, 0, '', null, -1, 0, -1, 0, 0, 0, 1, 0);
INSERT INTO associations (UniqueId, AssociationId, ContentId, ParentAssociationId, Category, SiteId, SecurityId, Type, Priority, Path, Depth, IsDeleted, DeletedItemsId, NumberOfViews) VALUES (1, 1, 1, 0, 0, 1, 1, 1, 1225741972, '/', 0, 0, 0, 298);
INSERT INTO contentversion (ContentVersionId, ContentId, Version, Status, IsActive, Language, Title, AltTitle, Description, Image, Keywords, Publisher, LastModified, LastModifiedBy, ChangeDescription, ApprovedBy, ChangeFrom, IsMinorChange, LastMajorChange, LastMajorChangeBy) VALUES (1, 1, 1, 10, 0, 0, 'Nyhetsarkiv first', '', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Pellentesque quis dui non ligula ultrices convallis. Fusce aliquam, velit sed varius adipiscing, eros orci cursus diam, vel malesuada sem urna nec tortor.', '', '', 'Espen Høe', '2008-11-10 11:11:08', '', null, 'esphoe', null, 0, '2008-11-10 11:11:08', 'esphoe');
INSERT INTO contentversion (ContentVersionId, ContentId, Version, Status, IsActive, Language, Title, AltTitle, Description, Image, Keywords, Publisher, LastModified, LastModifiedBy, ChangeDescription, ApprovedBy, ChangeFrom, IsMinorChange, LastMajorChange, LastMajorChangeBy) VALUES (2, 1, 2, 30, 1, 0, 'Nyhetsarkiv', '', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Pellentesque quis dui non ligula ultrices convallis. Fusce aliquam, velit sed varius adipiscing, eros orci cursus diam, vel malesuada sem urna nec tortor.', '', '', 'Espen Høe', '2008-11-10 11:11:08', '', null, 'esphoe', null, 0, '2008-11-10 11:11:08', 'esphoe');
INSERT INTO contentversion (ContentVersionId, ContentId, Version, Status, IsActive, Language, Title, AltTitle, Description, Image, Keywords, Publisher, LastModified, LastModifiedBy, ChangeDescription, ApprovedBy, ChangeFrom, IsMinorChange, LastMajorChange, LastMajorChangeBy) VALUES (3, 1, 3, 25, 0, 0, 'Nyhetsarkiv v2', '', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Pellentesque quis dui non ligula ultrices convallis. Fusce aliquam, velit sed varius adipiscing, eros orci cursus diam, vel malesuada sem urna nec tortor.', '', '', 'Espen Høe', '2008-11-10 11:11:08', '', null, 'esphoe', null, 0, '2008-11-10 11:11:08', 'esphoe');
INSERT INTO contentattributes (AttributeId, ContentVersionId, AttributeType, DataType, Name, Value) VALUES (63, 1, 'list', 0, 'vis arkiverte', ',true,');
INSERT INTO contentattributes (AttributeId, ContentVersionId, AttributeType, DataType, Name, Value) VALUES (62, 1, 'number', 0, 'max', '20');
INSERT INTO contentattributes (AttributeId, ContentVersionId, AttributeType, DataType, Name, Value) VALUES (60, 1, 'text', 0, 'tittel', 'Nyhetsarkiv');
INSERT INTO contentattributes (AttributeId, ContentVersionId, AttributeType, DataType, Name, Value) VALUES (61, 1, 'text', 0, 'ingress', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Pellentesque quis dui non ligula ultrices convallis. Fusce aliquam, velit sed varius adipiscing, eros orci cursus diam, vel malesuada sem urna nec tortor.');
INSERT INTO contentattributes (AttributeId, ContentVersionId, AttributeType, DataType, Name, Value) VALUES (64, 2, 'list', 0, 'vis arkiverte', ',false,');
INSERT INTO contentattributes (AttributeId, ContentVersionId, AttributeType, DataType, Name, Value) VALUES (65, 2, 'number', 0, 'max', '10');
INSERT INTO contentattributes (AttributeId, ContentVersionId, AttributeType, DataType, Name, Value) VALUES (66, 2, 'text', 0, 'tittel', 'Nyhetsarkivet');
INSERT INTO contentattributes (AttributeId, ContentVersionId, AttributeType, DataType, Name, Value) VALUES (67, 2, 'text', 0, 'ingress', 'Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Pellentesque quis dui non ligula ultrices convallis. Fusce aliquam, velit sed varius adipiscing, eros orci cursus diam, vel malesuada sem urna nec tortor.');
INSERT INTO associationcategory (AssociationId, AssociationName, Description, PublicId, LastModified) VALUES (1, 'Hovedmeny', 'Venstremeny', null, '2009-12-18 13:12:23');
INSERT INTO mailsubscription (Id, Channel, DocumentType, Language, Email, MailInterval) VALUES (10, 1, -1, 0, 'mailz@gmail.com', 'daily');
INSERT INTO mailsubscription (Id, Channel, DocumentType, Language, Email, MailInterval) VALUES (20, 2, -1, 0, 'mailz2@gmail.com', 'daily');
INSERT INTO mailsubscription (Id, Channel, DocumentType, Language, Email, MailInterval) VALUES (30, 1, -1, 0, 'mailz3@broadpark.no', 'immediate');
INSERT INTO mailsubscription (Id, Channel, DocumentType, Language, Email, MailInterval) VALUES (40, 2, -1, 0, 'mailz4@stinessen.com', 'immediate');
INSERT INTO mailsubscription (Id, Channel, DocumentType, Language, Email, MailInterval) VALUES (50, 1, -1, 0, 'mailz5@mail.com', 'weekly');
INSERT INTO mailsubscription (Id, Channel, DocumentType, Language, Email, MailInterval) VALUES (60, 2, -1, 0, 'mailz6@email.com', 'weekly');

INSERT INTO content (ContentId, ContentType, ContentTemplateId, MetaDataTemplateId, DisplayTemplateId, DocumentTypeId, GroupId, Owner, Location, Alias, PublishDate, ExpireDate, ExpireAction, VisibilityStatus, NumberOfNotes, OwnerPerson, RevisionDate, ForumId, OpenInNewWindow, DocumentTypeIdForChildren, IsLocked, RatingScore, NumberOfRatings, IsSearchable, NumberOfComments) VALUES (2, 0, 2, -1, 2, -1, 1, '', null, '/deletedalias/', '2012-01-04 13:21:35', null, 'HIDE', 10, 0, '', null, -1, 0, -1, 0, 0, 0, 1, 0);
INSERT INTO associations (UniqueId, AssociationId, ContentId, ParentAssociationId, Category, SiteId, SecurityId, Type, Priority, Path, Depth, IsDeleted, DeletedItemsId, NumberOfViews) VALUES (2, 2, 2, 0, 0, 1, 1, 1, 1225741972, '/', 0, 1, 12, 298);

ALTER TABLE Content ALTER ContentId restart with 11;
ALTER TABLE ContentVersion ALTER ContentVersionId restart with 11;
ALTER TABLE Associations ALTER UniqueId restart with 11;
INSERT INTO objectpermissions (ObjectSecurityId, ObjectType, Privilege, RoleType, Role, NotificationPriority) VALUES (123,3,1,'Role','innholdsprodusent',1);
INSERT INTO objectpermissions (ObjectSecurityId, ObjectType, Privilege, RoleType, Role, NotificationPriority) VALUES (123,1,3,'User','ZFG',1);