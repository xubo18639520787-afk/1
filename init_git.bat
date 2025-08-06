@echo off
chcp 65001 >nul
echo 初始化Git仓库并准备上传到GitHub...
echo.

REM 检查是否已安装Git
git --version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo [错误] 未安装Git，请先下载安装：
    echo https://git-scm.com/download/win
    pause
    exit /b 1
)

echo [✓] Git环境检查通过
echo.

REM 初始化Git仓库
echo 正在初始化Git仓库...
git init

REM 添加所有文件
echo 正在添加项目文件...
git add .

REM 创建初始提交
echo 正在创建初始提交...
git commit -m "初始提交：简易记账应用

- 使用Jetpack Compose + Room + MVVM架构
- 支持收入支出记录和分类管理
- 实时余额计算和交易历史
- Material Design 3界面设计
- 包含GitHub Actions自动构建配置"

REM 设置主分支名称
git branch -M main

echo.
echo [✓] Git仓库初始化完成！
echo.
echo 接下来的步骤：
echo.
echo 1. 在GitHub上创建新仓库：
echo    - 访问 https://github.com/new
echo    - 仓库名称：simple-accounting-app
echo    - 描述：简易记账应用 - Android个人财务管理工具
echo    - 选择Public或Private
echo    - 不要勾选任何初始化选项
echo.
echo 2. 复制以下命令并执行（替换YOUR_USERNAME为您的GitHub用户名）：
echo.
echo    git remote add origin https://github.com/YOUR_USERNAME/simple-accounting-app.git
echo    git push -u origin main
echo.
echo 3. 推送完成后，GitHub Actions会自动开始构建APK
echo.
echo 4. 在仓库的Actions页面可以查看构建进度
echo.
echo 5. 构建完成后可以下载APK文件
echo.
pause