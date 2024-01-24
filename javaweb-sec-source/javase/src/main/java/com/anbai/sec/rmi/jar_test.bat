set base_path=D:/Program_Files/JDK/oracle_jdk1.7.0_51/bin

%base_path%/javac   -encoding   UTF-8           jar_test/TestClientPayloadObject.java
:: 调试中
%base_path%/java    jar_test.TestClientPayloadObject

:: %base_path%/jar
:: %base_path%/jar     cvf        MainAbc.jar         --main-class   jar_test.TestClientPayloadObject                 jar_test/*.class
:: %base_path%/jar     cvfm         MainAbc.jar         jar_test/MANIFEST.MF                 jar_test/*.class
:: %base_path%/jar     cvfm        MainAbc.jar         --main-class   jar_test.TestClientPayloadObject                 jar_test/*.class
%base_path%/jar     cvfm         MainAbc.jar         jar_test/MANIFEST.MF                 jar_test/*.class


%base_path%/java    -jar        MainAbc.jar
%base_path%/java    -cp         MainAbc.jar      jar_test.TestClientPayloadObject

:: Main-Class: jar_test.TestClientPayloadObject