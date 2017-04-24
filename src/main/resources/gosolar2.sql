INSERT INTO `user` VALUES (1, 'pvenigandla2@student.gsu.edu', 'Pranathi', 'Venigandla', '1234', '1234567890', 0);
INSERT INTO `user` VALUES (2, 'jbhola_professor@gsu.edu', 'Jaman', 'Bhola', '1234', '1234567890', 1);
INSERT INTO `user` VALUES (3, 'jking82@student.gsu.edu', 'Joshua', 'King', '1234', '1234567890', 2);
INSERT INTO `user` VALUES (4, 'sarnett4@student.gsu.edu', 'Solomon', 'Arnett', '1234', '1234567890', 2);
INSERT INTO `transcript` VALUES (1, X'aced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770703000007e1041478', 1, 1);
INSERT INTO `transcript` VALUES (2, X'aced00057372000d6a6176612e74696d652e536572955d84ba1b2248b20c00007870770703000007e1041478', 0, 1);
INSERT INTO `student_courses` VALUES (1, 1);
INSERT INTO `student_courses` VALUES (2, 1);
INSERT INTO `student_courses` VALUES (3, 1);
INSERT INTO `student` VALUES (0, 'Computer Science', 1);
INSERT INTO `professor` VALUES (54000, 46800, '25 Park Place, Atlanta, GA
Room 648', 2);
INSERT INTO `emergency_contact` VALUES (1, '6330 Lake Oak Landing\nCumming, GA 30040', 'Joshua King', '6784352887', '', 1);
INSERT INTO `course` VALUES (1, 'Aderhold', '4350', 4, 72646, 'TR', 'Undergraduate',
                                'Techniques used in large scale scientific or technical software development, including requirements analysis, specification, systems design, implementation, testing, validation, verification, and maintenance. Serves as the Critical Thinking Through Writing (CTW) course required of all computer science majors.',
                                69300, 125, 'Software Engineering', '012', 63000, 'CSC', 'Spring 2017', 2);
INSERT INTO `course` VALUES (2, 'Aderhold', '2212K', 4, 36472, 'MW', 'Undergraduate',
                                'Three lecture and two laboratory hours a week. This introductory course will include material from electromagnetism, light, and modern physics. Elementary algebra and trigonometry will be used. PHYS 1111K and PHYS 1112K meet the science requirement for the B.A., the B.B.A., and the B.S. in Education degrees, and the physics requirement for students in the biological and life sciences. Most natural science majors should enroll in PHYS 2211K and PHYS 2212K.',
                                53100, 125, 'Physics', '223', 48600, 'CSC', 'Spring 2017', NULL);
INSERT INTO `course` VALUES (3, NULL, '4350', 4, 72646, NULL, 'Undergraduate',
                                'Techniques used in large scale scientific or technical software development, including requirements analysis, specification, systems design, implementation, testing, validation, verification, and maintenance. Serves as the Critical Thinking Through Writing (CTW) course required of all computer science majors.',
                                NULL, 125, 'Software Engineering', NULL, NULL, 'CSC', 'Spring 2017', NULL);
INSERT INTO `admin` VALUES (3);
INSERT INTO `admin` VALUES (4);