!/usr/bin/env sh

echo "- - - - - - - - - -  start build apk - - - - - - - - - - "
cp device_id_password.json IoTDeviceSDKDemo/app/src/main/res/raw/deviceinfo
cd IoTDeviceSDKDemo

checkJava () {
    java -version
    echo $?
    if [ $? -eq 0 ]; then
        echo "Java installed."
        checkGradle
    else
        echo "Environment Error. Java not installed."
    fi
}

checkGradle (){
    gradle -v
    if [ $? -eq 0 ]; then
        echo "Gradle installed."
        buildApk
    else
        echo "Environment Error. Gradle not installed."
    fi
}

buildApk () {
    ./gradlew build

    if [ $? -eq 0 ]; then
        echo "Gradlew build success."
		echo "apk build success path=IoTDeviceSDKDemo/app/build/outputs/apk/release"
        echo "- - - - - - - - - -  end build apk - - - - - - - - - - "
    else
		echo "Environment ERROR. Check if ANDROID_HOME variable or sdk.dir in the local.properties file has been set."
		echo "For example, ANDROID_HOME=/Users/xxx/Library/Android/sdk/ or sdk.dir=/Users/xxx/Library/Android/sdk/"
        echo "Use Android Studio to open this project and install missed packages."
    fi
}

checkJava
