# 清理Hosts文件步骤

## ✅ 确认情况
- Steam++应用已删除
- hosts文件中的Steam++配置可以安全删除
- 已备份原始hosts文件

## 🔧 清理步骤

### 方法1：手动清理（推荐）
1. **以管理员身份运行记事本**：
   - 右键点击记事本
   - 选择"以管理员身份运行"

2. **打开hosts文件**：
   - 在记事本中按Ctrl+O
   - 导航到：`C:\Windows\System32\drivers\etc\`
   - 文件类型选择"所有文件(*.*)"
   - 选择"hosts"文件

3. **删除Steam++内容**：
   - 删除从`# Steam++ Start`到`# Steam++ End`之间的所有内容
   - 保留系统默认的注释内容

4. **保存文件**：
   - 按Ctrl+S保存
   - 如果提示权限问题，确保以管理员身份运行

### 方法2：使用PowerShell（快速）
```powershell
# 以管理员身份运行PowerShell，然后执行：
$cleanContent = @"
# Copyright (c) 1993-2009 Microsoft Corp.
#
# This is a sample HOSTS file used by Microsoft TCP/IP for Windows.
#
# localhost name resolution is handled within DNS itself.
#	127.0.0.1       localhost
#	::1             localhost
"@

$cleanContent | Out-File -FilePath "C:\Windows\System32\drivers\etc\hosts" -Encoding ASCII
```

## 🔄 清理后的操作

1. **刷新DNS缓存**：
   ```powershell
   ipconfig /flushdns
   ```

2. **测试GitHub连接**：
   ```powershell
   ping github.com
   Test-NetConnection github.com -Port 443
   ```

3. **推送代码到GitHub**：
   ```powershell
   cd SimpleAccountingApp
   git push -u origin main
   ```

## 📋 清理后的hosts文件内容

清理后的hosts文件应该只包含系统默认内容：
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

## ✅ 预期结果

清理hosts文件后：
- ✅ GitHub访问恢复正常
- ✅ 可以正常推送代码
- ✅ 不影响系统其他功能
- ✅ Steam++的配置完全清除

## 🚀 完成后的操作

1. **创建GitHub仓库**：https://github.com/new
2. **推送代码**：`git push -u origin main`
3. **等待自动构建**：5-10分钟
4. **下载APK文件**

您的SimpleAccountingApp项目已经完全准备就绪！