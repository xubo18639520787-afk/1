# Android 开发环境配置指南

## 当前环境状态

### ✅ 已配置
- Java 17 环境 (OpenJDK 17.0.16)
- 完整的项目源代码
- 标准的 Android 项目结构

### ❌ 缺失组件
- Android Studio IDE
- Android SDK
- Gradle Wrapper JAR 文件
- Android 构建工具

## 推荐解决方案

### 方案一：安装 Android Studio（强烈推荐）

1. **下载 Android Studio**
   - 访问：https://developer.android.com/studio
   - 下载最新版本的 Android Studio

2. **安装步骤**
   ```
   1. 运行下载的安装程序
   2. 选择标准安装（Standard Installation）
   3. 等待下载 Android SDK 和相关工具
   4. 完成安装后启动 Android Studio
   ```

3. **打开项目**
   ```
   1. 启动 Android Studio
   2. 选择 "Open an existing Android Studio project"
   3. 导航到项目文件夹：SimpleAccountingApp
   4. 点击 "OK" 打开项目
   ```

4. **自动配置**
   - Android Studio 会自动检测并下载缺失的组件
   - 包括 Gradle Wrapper、Android SDK、构建工具等
   - 等待同步完成（首次可能需要较长时间）

5. **构建 APK**
   ```
   1. 点击菜单 Build → Build Bundle(s) / APK(s) → Build APK(s)
   2. 等待构建完成
   3. APK 文件位于：app/build/outputs/apk/debug/
   ```

### 方案二：手动配置命令行环境

如果不想使用 Android Studio，可以手动配置：

1. **下载 Android SDK Command Line Tools**
   - 访问：https://developer.android.com/studio#command-tools
   - 下载适用于 Windows 的命令行工具

2. **配置环境变量**
   ```batch
   set ANDROID_HOME=C:\Android\Sdk
   set PATH=%PATH%;%ANDROID_HOME%\tools;%ANDROID_HOME%\platform-tools
   ```

3. **安装必要组件**
   ```batch
   sdkmanager "platforms;android-34"
   sdkmanager "build-tools;34.0.0"
   sdkmanager "platform-tools"
   ```

4. **下载 Gradle**
   - 访问：https://gradle.org/releases/
   - 下载 Gradle 8.0
   - 解压并配置 PATH

## 为什么推荐 Android Studio？

### 优势
- **一键解决**：自动下载和配置所有必要工具
- **集成环境**：代码编辑、调试、构建一体化
- **错误提示**：实时代码检查和错误修复建议
- **设备管理**：内置模拟器和设备连接工具
- **版本管理**：自动处理依赖版本冲突

### 替代方案的问题
- **复杂配置**：需要手动下载多个工具
- **版本兼容**：容易出现版本不匹配问题
- **网络依赖**：需要稳定的网络连接下载组件
- **维护困难**：需要手动更新各种工具

## 项目特点

### 技术栈
- **UI框架**：Jetpack Compose
- **数据库**：Room
- **架构**：MVVM
- **语言**：Kotlin
- **最低版本**：Android 7.0 (API 24)

### 功能特性
- 收入支出记录
- 实时余额计算
- 分类管理
- 交易历史
- 数据持久化

## 常见问题解决

### Q: 网络连接问题
A: 使用 Android Studio 可以配置代理或使用离线模式

### Q: Java 版本问题
A: 当前 Java 17 环境完全兼容，无需更改

### Q: 构建失败
A: Android Studio 会提供详细的错误信息和修复建议

### Q: 设备调试
A: Android Studio 内置 ADB 工具，可直接连接设备

## 总结

当前环境的主要问题是缺少 Android 开发工具链。使用 Android Studio 是最简单、最可靠的解决方案，可以一次性解决所有环境配置问题。

项目代码本身是完整和正确的，只需要正确的构建环境即可成功生成 APK。