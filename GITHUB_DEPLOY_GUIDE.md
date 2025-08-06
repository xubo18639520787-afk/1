# GitHub在线构建APK指南

## 步骤1：创建GitHub仓库

1. 访问 https://github.com/new
2. 仓库名称：`SimpleAccountingApp`
3. 设置为公开仓库（Private也可以）
4. 不要初始化README（我们已经有了）
5. 点击"Create repository"

## 步骤2：推送代码到GitHub

在命令行中执行以下命令：

```bash
cd SimpleAccountingApp
git remote add origin https://github.com/你的用户名/SimpleAccountingApp.git
git branch -M main
git push -u origin main
```

## 步骤3：触发自动构建

代码推送后，GitHub Actions会自动开始构建：

1. 访问你的仓库页面
2. 点击"Actions"标签
3. 查看构建进度

## 步骤4：下载APK

构建完成后：

1. 在Actions页面找到最新的构建
2. 点击构建任务
3. 在"Artifacts"部分下载`app-debug`
4. 解压得到APK文件

## 自动发布

如果推送到main分支，系统会自动创建Release并上传APK文件。

## 项目特点

✅ 已包含统计分析功能
✅ 支持收入支出记录  
✅ 实时余额计算
✅ 分类管理功能
✅ 交易历史查看
✅ Material Design 3界面
✅ 分类饼图统计
✅ 月度收支趋势图

## 注意事项

- 确保GitHub仓库是公开的（或者有GitHub Pro账户）
- 构建大约需要5-10分钟
- APK文件会自动签名（debug签名）