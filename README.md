# 简易记账应用

这是一个使用现代Android开发技术栈构建的简化版个人记账应用。

## 项目特点

- **现代UI框架**: 使用Jetpack Compose构建响应式用户界面
- **本地数据存储**: 使用Room数据库进行可靠的本地数据持久化
- **架构模式**: 采用MVVM架构模式，确保代码的可维护性和可测试性
- **响应式编程**: 使用Kotlin Flow进行数据流管理
- **Material Design 3**: 遵循最新的Material Design设计规范

## 主要功能

### 核心功能
1. **添加交易记录**: 支持收入和支出两种类型的记录
2. **实时余额显示**: 自动计算并显示当前账户余额
3. **交易历史**: 按时间倒序显示所有交易记录
4. **分类管理**: 支持自定义分类，便于财务分析
5. **删除功能**: 长按交易记录可删除错误的条目

### 用户界面
- **主屏幕**: 显示余额卡片、收支统计和交易列表
- **添加对话框**: 简洁的表单界面，支持快速分类选择
- **交易项**: 清晰显示交易信息，包括金额、描述、分类和时间

## 技术架构

### 数据层
- **Entity**: `Transaction` - 交易记录实体
- **DAO**: `TransactionDao` - 数据访问对象
- **Database**: `AppDatabase` - Room数据库配置
- **Repository**: `TransactionRepository` - 数据仓库模式

### 业务层
- **ViewModel**: `MainViewModel` - 管理UI状态和业务逻辑
- **ViewModelFactory**: 依赖注入工厂

### 表现层
- **Screen**: `MainScreen` - 主界面组合函数
- **Component**: 
  - `TransactionItem` - 交易项组件
  - `AddTransactionDialog` - 添加交易对话框
- **Theme**: Material Design 3主题配置

## 项目结构

```
SimpleAccountingApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/simpleaccounting/
│   │   │   ├── data/                    # 数据层
│   │   │   │   ├── Transaction.kt       # 交易实体
│   │   │   │   ├── TransactionDao.kt    # 数据访问对象
│   │   │   │   ├── Converters.kt        # 类型转换器
│   │   │   │   └── AppDatabase.kt       # 数据库配置
│   │   │   ├── repository/              # 仓库层
│   │   │   │   └── TransactionRepository.kt
│   │   │   ├── viewmodel/               # 视图模型层
│   │   │   │   └── MainViewModel.kt
│   │   │   ├── ui/                      # UI层
│   │   │   │   ├── screen/
│   │   │   │   │   └── MainScreen.kt
│   │   │   │   ├── component/
│   │   │   │   │   ├── TransactionItem.kt
│   │   │   │   │   └── AddTransactionDialog.kt
│   │   │   │   └── theme/               # 主题配置
│   │   │   └── MainActivity.kt          # 主活动
│   │   ├── res/                         # 资源文件
│   │   └── AndroidManifest.xml          # 应用清单
│   └── build.gradle                     # 应用构建配置
├── gradle/wrapper/                      # Gradle Wrapper
├── build.gradle                         # 项目构建配置
├── settings.gradle                      # 项目设置
└── gradle.properties                    # Gradle属性
```

## 构建说明

### 环境要求
- Android Studio Arctic Fox 或更高版本
- JDK 8 或更高版本
- Android SDK API 24 (Android 7.0) 或更高版本

### 构建步骤

#### 方法一：使用Android Studio（推荐）
1. 打开Android Studio
2. 选择 "Open an existing Android Studio project"
3. 导航到 `SimpleAccountingApp` 文件夹并选择
4. 等待项目同步完成
5. 点击 "Build" -> "Build Bundle(s) / APK(s)" -> "Build APK(s)"
6. 构建完成后，APK文件位于 `app/build/outputs/apk/debug/`

#### 方法二：使用命令行
```bash
# 在项目根目录执行
./gradlew assembleDebug

# Windows系统使用
gradlew.bat assembleDebug
```

### 安装和运行
1. 将生成的APK文件传输到Android设备
2. 在设备上启用"未知来源"安装权限
3. 点击APK文件进行安装
4. 安装完成后即可使用

## 使用说明

### 添加交易
1. 点击右下角的"+"按钮
2. 选择交易类型（收入/支出）
3. 输入金额、描述和分类
4. 点击"确定"保存

### 查看统计
- 主屏幕顶部显示当前余额
- 收入和支出统计卡片显示总计金额
- 交易列表按时间倒序显示所有记录

### 删除交易
1. 点击要删除的交易记录
2. 在弹出的确认对话框中点击"删除"

## 数据存储

应用使用Room数据库在本地存储所有数据，包括：
- 交易记录（金额、描述、分类、类型、时间）
- 自动计算的统计数据

数据库文件存储在应用的私有目录中，卸载应用时会自动清除。

## 扩展功能建议

未来可以考虑添加以下功能：
1. 数据导出/导入功能
2. 图表统计显示
3. 预算管理
4. 多账户支持
5. 数据备份到云端
6. 支付方式记录
7. 定期交易模板

## 技术说明

### 依赖库
- Jetpack Compose: 现代UI工具包
- Room: 数据库抽象层
- ViewModel: 生命周期感知的数据持有者
- Kotlin Coroutines: 异步编程
- Material Design 3: UI设计系统

### 最低系统要求
- Android 7.0 (API level 24)
- 20MB 存储空间
- 1GB RAM

---

这个简化版记账应用提供了个人财务管理的核心功能，代码结构清晰，易于理解和扩展。适合作为学习Android开发的示例项目，也可以作为个人使用的实用工具。