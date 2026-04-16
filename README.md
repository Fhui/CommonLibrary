# CommonLibrary

一个以 Android 为主的示例型仓库，用来集中演示常见网络请求、自定义 UI、页面交互、架构模式和 Kotlin 基础用法。

项目整体更接近“功能实验场”而不是单一业务应用：入口页通过 `MainActivity + DeskTopFragment` 将多个 Demo 串联起来，方便逐个查看和练习。

## Highlights

- 网络请求示例：`Retrofit`、`OkHttp`、`NoHttp`
- UI 与交互示例：数字加减器、仿陌陌登录、图片选择、共享元素动画、PopupWindow、Dialog、通知、指纹识别、自定义 Title
- 自定义控件：`AdderSubtractorView`、`CircleProgressView`、`HorizontalProgressView`、`ClearEditText`、`SectionBar`、`EasyPopupWindow`
- 架构与库练习：`RxJava`、`MVP`、`MVVM`
- Kotlin 练习：独立 `kotlin_lib` 模块 + `KotlinFragment` 示例页

## Demo List

应用首页的演示项主要包括：

- 网络：`Retrofit`、`OkHttp`、`NoHttp`
- UI：数字加减器、仿陌陌登录、知乎图片选择器、共享元素、联系人、进度条、`PopupWindow`、指纹、`Dialog`、`EasyRecycleView`、`MarginDesign`、通知、自定义 Title
- 第三方库 / 设计模式：`RxJava`、`MVP`、`MVVM`
- 新技术：`Kotlin`

其中部分页面已经具备可运行的交互逻辑，部分则更偏向结构示例或占位页面，适合继续扩展。

## Project Structure

```text
CommonLibrary
├── app/                          # 主示例应用
│   ├── src/main/java/com/example/huifeng/library
│   │   ├── activity/             # 页面入口
│   │   ├── fragment/             # 各类 Demo Fragment
│   │   ├── custom_widget/        # 自定义 View / PopupWindow / 过渡动画
│   │   ├── adapter/              # 列表与内容适配器
│   │   ├── net/retrofit/         # Retrofit 接口与管理类
│   │   ├── mvp/                  # MVP 示例代码
│   │   ├── utils/                # 通用工具类
│   │   └── bean/                 # 数据模型
│   └── src/main/res/             # 布局、动画、图片、字符串等资源
├── kotlin_lib/                   # Kotlin 基础示例模块
├── gradle/                       # Gradle Wrapper
├── build.gradle                  # 根构建脚本
└── settings.gradle               # 模块配置
```

## Tech Stack

- Android Gradle Plugin `3.0.1`
- Gradle Wrapper `4.1`
- Kotlin `1.1.3-2`
- Java 8 / Kotlin 混编
- Android Support Library `25/26`
- Retrofit 2
- OkHttp 3
- RxJava
- ButterKnife
- Glide
- Matisse
- Fastjson
- pinyin4j

## Key Modules

### `app`

主应用模块，负责承载所有演示页面。

比较值得关注的目录：

- `activity/MainActivity.java`：应用入口与 Fragment 栈管理
- `fragment/DeskTopFragment.java`：首页菜单，负责组织 Demo 列表
- `fragment/RetrofitFragment.java`：基于 `Retrofit` 的 GET / POST 示例
- `fragment/KotlinFragment.kt`：结合 `RxJava` 和 Gank 接口的 Kotlin 页面
- `custom_widget/`：仓库里最有复用价值的一批自定义控件
- `mvp/`：较完整的 MVP 结构示例

### `kotlin_lib`

一个轻量级 Kotlin Library 模块，包含基础语法、类继承、接口和函数示例，适合拿来做 Kotlin 入门练习或语法回顾。

## Getting Started

### 1. Clone

```bash
git clone <your-fork-or-repo-url>
cd CommonLibrary
```

### 2. Prepare environment

建议使用与项目年代相匹配的环境：

- Android Studio 3.x 或兼容旧版 Android 插件的 IDE
- JDK 8
- Android SDK 25/26

### 3. Make Gradle wrapper executable

当前仓库中的 `gradlew` 默认没有执行权限，首次使用前需要：

```bash
chmod +x gradlew
```

### 4. Build

```bash
./gradlew assembleDebug
```

如果你更习惯 IDE，也可以直接用 Android Studio 打开项目并运行 `app` 模块。

## Compatibility Notes

这是一个明显带有历史版本特征的 Android 工程，开始使用前建议先了解这些限制：

- 项目仍依赖 `jcenter()`
- 主模块使用 Android Support Library，而不是 AndroidX
- 根构建使用 `Gradle 4.1 + AGP 3.0.1`
- 使用较新的 JDK 时会构建失败，例如 JDK 21 下会报错：`Could not determine java version from '21.0.9'`
- `Constant.java` 中写死了示例接口地址和 API Key，这些第三方服务今天不一定仍然可用

如果你计划继续维护这个仓库，优先级通常会是：

1. 升级到 AndroidX
2. 升级 Gradle / AGP
3. 替换失效的第三方接口
4. 补充截图、测试和模块说明

## What This Repo Is Good For

- 快速浏览一个早期 Android Demo 集合的组织方式
- 学习 Fragment 导航 + 多示例页的搭建方式
- 拆解一些自定义 View / PopupWindow / 动画过渡实现
- 作为旧项目升级到现代 Android 技术栈前的参考底稿

## Current Status

仓库里已经包含不少可参考代码，但整体更适合作为学习样例和二次整理基础，而不是直接投入生产环境。

如果你打算继续完善它，推荐先从文档、依赖升级和 Demo 可运行性校验开始。
