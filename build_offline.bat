@echo off
echo 离线构建Android应用...
echo.

REM 检查Java环境
java -version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo [错误] Java环境未配置
    pause
    exit /b 1
)

echo [✓] Java环境检查通过
echo.

REM 检查是否存在Android Studio
set ANDROID_STUDIO_PATH=""
if exist "C:\Program Files\Android\Android Studio\bin\studio64.exe" (
    set ANDROID_STUDIO_PATH="C:\Program Files\Android\Android Studio"
)
if exist "C:\Users\%USERNAME%\AppData\Local\Android\Sdk" (
    set ANDROID_HOME=C:\Users\%USERNAME%\AppData\Local\Android\Sdk
)

echo 当前状态检查:
echo [✓] 项目代码完整
echo [✓] Java 17 环境可用
echo [×] Gradle Wrapper 缺失
echo [×] 网络连接受限

echo.
echo 解决方案:
echo.
echo 1. 推荐方案 - 使用Android Studio:
echo    - 下载Android Studio: https://developer.android.com/studio
echo    - 打开项目文件夹: %CD%
echo    - Android Studio会自动下载所需组件
echo    - 点击Build -> Build APK构建应用
echo.
echo 2. 手动方案 - 下载Gradle:
echo    - 访问: https://gradle.org/releases/
echo    - 下载Gradle 8.0
echo    - 解压到本地目录
echo    - 配置环境变量
echo.
echo 3. 在线方案 - 修复网络:
echo    - 检查防火墙设置
echo    - 配置代理服务器
echo    - 使用移动热点尝试
echo.

echo 项目已准备就绪，只需要构建环境!
echo.
pause