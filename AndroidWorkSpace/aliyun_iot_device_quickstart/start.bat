TITLE Aliyun IoT Android Start Quick Build
COLOR 07
@echo - - - - - - - - START BUILD APK - - - - - - - -
@echo=

@echo current path %cd%
CP device_id_password.json IoTDeviceSDKDemo/app/src/main/res/raw/deviceinfo
@echo current path %cd%
CD IoTDeviceSDKDemo
@echo current path %cd%
DIR 

@rem check java environment
set JAVA_EXE=java.exe
call %JAVA_EXE% -version > NUL 2>&1
if "%ERRORLEVEL%" == "0" goto checkGradle
goto failJava

:checkGradle
@rem check gradle environment
set GRADLE_EXE=gradle.bat
call %GRADLE_EXE% -v >NUL 2>&1
echo %ERRORLEVEL%
if "%ERRORLEVEL%" == "0" goto init
goto failGradle

:init
@rem gradle run gradle cmd
call gradle -v
call gradlew build
@rem check gradle build result
if "%ERRORLEVEL%" == "0" (
  @echo apk build success path=IoTDeviceSDKDemo/app/build/outputs/apk/release
) else (
  @echo Environment ERROR. Check if ANDROID_HOME variable or sdk.dir in the local.properties file has been set.
  @echo For example, ANDROID_HOME=C:\Dev\tools\android-sdk-windows or sdk.dir=C\:\\Dev\\tools\\android-sdk-windows
)
goto end

:failJava
echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.
goto end

:failGradle
echo.
echo ERROR: GRADLE_HOME is not set and no 'gradle' command could be found in your PATH.
echo.
echo Please set the GRADLE_HOME variable in your environment to match the
echo location of your Gradle installation.
goto end

:fail
@echo=
@echo Environment ERROR. Please set the JAVA_HOME variable and the GRADLE_HOME variable
@echo in your environment to match the location of your Java and Gradle installation. And
@echo check whether Android API level 26 exists.
goto end


:end
@echo=
@echo - - - - - - - - END BUILD APK - - - - - - - -
pause>NUL


