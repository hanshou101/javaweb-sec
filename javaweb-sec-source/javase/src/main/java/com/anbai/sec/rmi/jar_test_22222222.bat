set base_path=D:/Program_Files/JDK/oracle_jdk1.7.0_51/bin

:: 进行java文件，到class文件的编译
%base_path%/javac   -encoding   UTF-8           jar_test/TestClientPayloadObject.java  jar_test/ReferenceObjectFactory.java
:: 调试中  （后面，其实是  完整包名）
%base_path%/java    jar_test.ReferenceObjectFactory

:: %base_path%/jar
:: %base_path%/jar     cvf        MainAbc.jar         --main-class   jar_test.TestClientPayloadObject                 jar_test/*.class
:: %base_path%/jar     cvfm         MainAbc.jar         jar_test/MANIFEST.MF                 jar_test/*.class
:: %base_path%/jar     cvfm        MainAbc.jar         --main-class   jar_test.TestClientPayloadObject                 jar_test/*.class

:: 将诸多的【class文件】打成一个【jar压缩包】
%base_path%/jar     cvfm         MainAbc_2222222222222.jar         jar_test/MANIFEST______22222.MF                 jar_test/*.class

:: 测试最后的jar包，是否成功
%base_path%/java    -jar        MainAbc_2222222222222.jar
:: %base_path%/java    -cp         MainAbc.jar      jar_test.TestClientPayloadObject
:: %base_path%/java    -cp         MainAbc.jar      jar_test.TestClientPayloadObject

:: Main-Class: jar_test.TestClientPayloadObject
