# GitHub仓库创建脚本
# 由于API需要认证，这里提供手动创建的详细步骤

Write-Host "=== GitHub仓库创建向导 ===" -ForegroundColor Green
Write-Host ""

Write-Host "步骤1: 打开GitHub创建页面" -ForegroundColor Yellow
Write-Host "请在浏览器中访问: https://github.com/new" -ForegroundColor Cyan
Write-Host ""

Write-Host "步骤2: 填写仓库信息" -ForegroundColor Yellow
Write-Host "Repository name: SimpleAccountingApp" -ForegroundColor White
Write-Host "Description: 简易记账应用 - 包含统计分析功能的Android个人财务管理工具" -ForegroundColor White
Write-Host "选择: Public (公开)" -ForegroundColor White
Write-Host "不要勾选任何初始化选项 (README, .gitignore, License)" -ForegroundColor Red
Write-Host ""

Write-Host "步骤3: 创建仓库" -ForegroundColor Yellow
Write-Host "点击 'Create repository' 按钮" -ForegroundColor White
Write-Host ""

Write-Host "步骤4: 推送代码" -ForegroundColor Yellow
Write-Host "创建完成后，在此目录执行:" -ForegroundColor White
Write-Host "git push -u origin main" -ForegroundColor Cyan
Write-Host ""

Write-Host "步骤5: 等待自动构建" -ForegroundColor Yellow
Write-Host "推送完成后，GitHub Actions会自动开始构建APK" -ForegroundColor White
Write-Host "构建时间约5-10分钟" -ForegroundColor White
Write-Host ""

Write-Host "步骤6: 下载APK" -ForegroundColor Yellow
Write-Host "访问: https://github.com/xubo18639520787-afk/SimpleAccountingApp/actions" -ForegroundColor Cyan
Write-Host "在最新构建的Artifacts中下载app-debug" -ForegroundColor White
Write-Host ""

Write-Host "=== 项目特点 ===" -ForegroundColor Green
Write-Host "✅ 收入支出记录" -ForegroundColor Green
Write-Host "✅ 实时余额计算" -ForegroundColor Green  
Write-Host "✅ 分类管理功能" -ForegroundColor Green
Write-Host "✅ 交易历史查看" -ForegroundColor Green
Write-Host "✅ 统计分析功能 (饼图、柱状图)" -ForegroundColor Green
Write-Host "✅ Material Design 3界面" -ForegroundColor Green
Write-Host "✅ 自动构建APK" -ForegroundColor Green
Write-Host ""

Write-Host "按任意键打开GitHub创建页面..." -ForegroundColor Yellow
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")

# 打开GitHub创建页面
Start-Process "https://github.com/new"

Write-Host ""
Write-Host "页面已打开，请按照上述步骤创建仓库" -ForegroundColor Green
Write-Host "创建完成后回到此窗口，按任意键继续..." -ForegroundColor Yellow
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")

Write-Host ""
Write-Host "正在推送代码到GitHub..." -ForegroundColor Yellow

# 推送代码
git push -u origin main

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "🎉 代码推送成功！" -ForegroundColor Green
    Write-Host "GitHub Actions正在自动构建APK..." -ForegroundColor Yellow
    Write-Host ""
    Write-Host "请访问以下链接查看构建进度:" -ForegroundColor Cyan
    Write-Host "https://github.com/xubo18639520787-afk/SimpleAccountingApp/actions" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "构建完成后，在Artifacts中下载app-debug获得APK文件" -ForegroundColor White
    
    # 打开Actions页面
    Start-Process "https://github.com/xubo18639520787-afk/SimpleAccountingApp/actions"
} else {
    Write-Host ""
    Write-Host "❌ 推送失败，请检查仓库是否已创建" -ForegroundColor Red
    Write-Host "确保仓库名称为: SimpleAccountingApp" -ForegroundColor Yellow
    Write-Host "确保仓库设置为Public" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "按任意键退出..." -ForegroundColor Gray
$null = $Host.UI.RawUI.ReadKey("NoEcho,IncludeKeyDown")