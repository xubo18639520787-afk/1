# Hosts文件安全修改指南

## ⚠️ 重要提醒
**不要删除hosts文件的全部内容！** 这可能会影响系统正常运行。

## 🔍 当前问题
Steam++软件在hosts文件中添加了GitHub重定向，阻止了Git推送。

## 🛡️ 安全解决方案

### 方案1：只删除GitHub相关条目（推荐）

1. **备份hosts文件**：
   ```powershell
   copy C:\Windows\System32\drivers\etc\hosts C:\Windows\System32\drivers\etc\hosts.backup
   ```

2. **以管理员身份编辑hosts文件**：
   - 右键点击记事本 → "以管理员身份运行"
   - 打开文件：`C:\Windows\System32\drivers\etc\hosts`

3. **只删除GitHub相关行**：
   删除或注释掉这些行：
   ```
   # 127.0.0.1 github.dev
   # 127.0.0.1 api.github.com
   # 127.0.0.1 gist.github.com
   # 127.0.0.1 github.githubassets.com
   # 127.0.0.1 support-assets.githubassets.com
   # 127.0.0.1 education.github.com
   # 127.0.0.1 raw.github.com
   # 127.0.0.1 githubusercontent.com
   # 127.0.0.1 raw.githubusercontent.com
   # 127.0.0.1 camo.githubusercontent.com
   # 127.0.0.1 cloud.githubusercontent.com
   # 127.0.0.1 avatars.githubusercontent.com
   # 127.0.0.1 avatars0.githubusercontent.com
   # 127.0.0.1 avatars1.githubusercontent.com
   # 127.0.0.1 avatars2.githubusercontent.com
   # 127.0.0.1 avatars3.githubusercontent.com
   # 127.0.0.1 user-images.githubusercontent.com
   # 127.0.0.1 objects.githubusercontent.com
   # 127.0.0.1 private-user-images.githubusercontent.com
   # 127.0.0.1 github.com
   # 127.0.0.1 pages.github.com
   # 127.0.0.1 githubapp.com
   # 127.0.0.1 github.io
   # 127.0.0.1 www.github.io
   ```

4. **保留其他内容**：
   - 保留Steam相关配置（如果需要Steam加速）
   - 保留Google等其他配置
   - 保留系统默认配置

### 方案2：临时禁用Steam++（更简单）

1. **关闭Steam++软件**
2. **在Steam++中禁用网络加速功能**
3. **或者在Steam++设置中排除GitHub域名**

### 方案3：使用手动上传（最安全）

如果不想修改hosts文件：
1. 访问 https://github.com/new
2. 创建仓库 `SimpleAccountingApp`
3. 手动上传项目zip文件
4. 自动构建APK

## 🔄 修改后的操作

修改hosts文件后：
1. **刷新DNS缓存**：
   ```powershell
   ipconfig /flushdns
   ```

2. **重新测试GitHub连接**：
   ```powershell
   ping github.com
   Test-NetConnection github.com -Port 443
   ```

3. **推送代码**：
   ```powershell
   cd SimpleAccountingApp
   git push -u origin main
   ```

## 📋 标准hosts文件内容

正常的hosts文件应该只包含：
```
# Copyright (c) 1993-2009 Microsoft Corp.
#
# This is a sample HOSTS file used by Microsoft TCP/IP for Windows.
#
# This file contains the mappings of IP addresses to host names. Each
# entry should be kept on an individual line. The IP address should
# be placed in the first column followed by the corresponding host name.
# The IP address and the host name should be separated by at least one
# space.
#
# Additionally, comments (such as these) may be inserted on individual
# lines or following the machine name denoted by a '#' symbol.
#
# For example:
#
#      102.54.94.97     rhino.acme.com          # source server
#       38.25.63.10     x.acme.com              # x client host

# localhost name resolution is handled within DNS itself.
#	127.0.0.1       localhost
#	::1             localhost
```

## 🎯 推荐操作步骤

1. **备份hosts文件**
2. **只删除GitHub相关行**
3. **保留Steam和其他配置**
4. **刷新DNS缓存**
5. **测试GitHub连接**
6. **推送代码到GitHub**

这样既解决了GitHub访问问题，又不会影响其他软件的正常运行。