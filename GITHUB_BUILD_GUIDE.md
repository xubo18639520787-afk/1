# GitHub自动构建指南

## 概述

通过GitHub Actions，您可以在云端自动构建Android APK，无需本地配置复杂的开发环境。

## 设置步骤

### 1. 创建GitHub仓库

1. 访问 [GitHub](https://github.com)
2. 点击右上角的 "+" → "New repository"
3. 填写仓库信息：
   - Repository name: `simple-accounting-app`
   - Description: `简易记账应用 - Android个人财务管理工具`
   - 选择 Public 或 Private
   - 勾选 "Add a README file"
4. 点击 "Create repository"

### 2. 上传项目代码

#### 方法一：使用Git命令行
```bash
# 在SimpleAccountingApp目录下执行
git init
git add .
git commit -m "初始提交：简易记账应用"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/simple-accounting-app.git
git push -u origin main
```

#### 方法二：使用GitHub Desktop
1. 下载并安装 [GitHub Desktop](https://desktop.github.com/)
2. 登录您的GitHub账户
3. 点击 "Add an Existing Repository from your Hard Drive"
4. 选择 `SimpleAccountingApp` 文件夹
5. 点击 "Publish repository"

#### 方法三：直接上传文件
1. 在GitHub仓库页面点击 "uploading an existing file"
2. 将 `SimpleAccountingApp` 文件夹中的所有文件拖拽到页面
3. 填写提交信息
4. 点击 "Commit changes"

### 3. 自动构建触发

上传代码后，GitHub Actions会自动开始构建：

1. 访问您的仓库页面
2. 点击 "Actions" 标签
3. 您会看到 "Build Android APK" 工作流正在运行

### 4. 下载构建的APK

构建完成后：

1. 在 "Actions" 页面点击最新的构建
2. 在 "Artifacts" 部分下载 `app-debug`
3. 解压下载的文件，获得 `app-debug.apk`

### 5. 自动发布（可选）

如果推送到 `main` 分支，系统会自动创建Release：

1. 访问仓库的 "Releases" 页面
2. 下载最新版本的APK文件
3. APK文件名格式：`简易记账-v{版本号}.apk`

## 工作流特性

### 自动化功能
- ✅ 自动检测代码变更
- ✅ 自动配置Java 17环境
- ✅ 自动缓存Gradle依赖
- ✅ 自动构建Debug APK
- ✅ 自动上传构建产物
- ✅ 自动创建Release版本

### 构建环境
- **操作系统**: Ubuntu Latest
- **Java版本**: OpenJDK 17 (Temurin)
- **Gradle版本**: 8.0
- **Android SDK**: 自动配置最新版本

### 触发条件
- 推送到 `main` 或 `master` 分支
- 创建Pull Request
- 手动触发（在Actions页面点击"Run workflow"）

## 优势对比

### GitHub构建 vs 本地构建

| 特性 | GitHub构建 | 本地构建 |
|------|------------|----------|
| 环境配置 | ✅ 自动配置 | ❌ 手动配置 |
| 网络依赖 | ✅ 高速网络 | ❌ 可能受限 |
| 存储空间 | ✅ 无限制 | ❌ 占用本地空间 |
| 多平台支持 | ✅ 云端统一 | ❌ 依赖本地环境 |
| 版本管理 | ✅ 自动标记 | ❌ 手动管理 |
| 分享便利 | ✅ 直接链接 | ❌ 需要传输 |

## 故障排除

### 常见问题

#### 1. 构建失败
- 检查代码语法错误
- 查看Actions日志中的详细错误信息
- 确认所有必要文件都已上传

#### 2. 权限问题
- 确保仓库设置中启用了Actions
- 检查GITHUB_TOKEN权限设置

#### 3. 依赖下载失败
- GitHub Actions会自动重试
- 检查网络连接状态

### 查看构建日志
1. 进入Actions页面
2. 点击具体的构建任务
3. 展开各个步骤查看详细日志

## 自定义配置

### 修改构建配置
编辑 `.github/workflows/build.yml` 文件：

```yaml
# 修改触发分支
on:
  push:
    branches: [ main, develop ]

# 修改Java版本
- name: Set up JDK 11
  uses: actions/setup-java@v4
  with:
    java-version: '11'

# 添加签名配置（生产环境）
- name: Sign APK
  uses: r0adkll/sign-android-release@v1
  with:
    releaseDirectory: app/build/outputs/apk/release
    signingKeyBase64: ${{ secrets.SIGNING_KEY }}
    alias: ${{ secrets.ALIAS }}
    keyStorePassword: ${{ secrets.KEY_STORE_PASSWORD }}
```

## 安全注意事项

### 敏感信息保护
- 不要在代码中包含API密钥
- 使用GitHub Secrets存储敏感配置
- 定期更新依赖版本

### 访问控制
- 私有仓库限制访问权限
- 合理配置分支保护规则
- 审查Pull Request中的代码变更

## 总结

使用GitHub Actions构建Android应用的优势：

1. **零配置** - 无需本地Android开发环境
2. **高可靠** - 云端构建环境稳定一致
3. **自动化** - 代码提交即自动构建
4. **易分享** - 构建产物可直接下载分享
5. **版本管理** - 自动创建Release和版本标记

这种方式特别适合：
- 没有Android开发环境的用户
- 网络环境受限的情况
- 需要团队协作的项目
- 希望自动化构建流程的场景

现在您只需要将代码上传到GitHub，就能获得自动构建的APK文件！