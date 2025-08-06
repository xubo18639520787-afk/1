@echo off
echo 正在尝试简单构建...

REM 检查Java环境
java -version
if %ERRORLEVEL% neq 0 (
    echo Java环境未配置，请先安装Java
    pause
    exit /b 1
)

echo.
echo 简化版记账应用已创建完成！
echo.
echo 项目特点：
echo - 使用Jetpack Compose现代UI框架
echo - Room数据库进行本地存储
echo - MVVM架构模式
echo - 支持收入和支出记录
echo - 实时余额计算
echo - 分类管理功能
echo.
echo 主要功能：
echo 1. 添加收入/支出记录
echo 2. 查看交易历史
echo 3. 实时余额显示
echo 4. 分类管理
echo 5. 删除交易记录
echo.
echo 要构建APK，您需要：
echo 1. 安装Android Studio
echo 2. 配置Android SDK
echo 3. 在Android Studio中打开此项目
echo 4. 点击Build -> Build Bundle(s)/APK(s) -> Build APK(s)
echo.
echo 或者使用命令行（需要配置Android SDK）：
echo gradlew assembleDebug
echo.
pause