# AdapterKit

## YdKit通用组件库
YdKit 是一组功能丰富的 Android 通用组件。

* [LogKit](https://github.com/ydstar/LogKit) — 轻量级的 Android 日志系统。
* [RestfulKit](https://github.com/ydstar/RestfulKit) — 简洁但不简单的 Android 网络组件库。
* [CrashKit](https://github.com/ydstar/CrashKit) — 简洁易用的 Android Crash日志捕捉组件。
* [PermissionKit](https://github.com/ydstar/PermissionKit) — 简洁易用的 Android 权限请求组件。
* [RefreshKit](https://github.com/ydstar/RefreshKit) — 简洁易用的 Android 下拉刷新和上拉加载组件。
* [AdapterKit](https://github.com/ydstar/AdapterKit) — 简洁易用的 Android 列表组件。
* [BannerKit](https://github.com/ydstar/BannerKit) — 简洁易用的 Android 无限轮播图组件。
* [TabBottomKit](https://github.com/ydstar/TabBottomKit) — 简洁易用的 Android 底部导航组件。


## 效果预览
<img src="https://github.com/ydstar/AdapterKit/blob/main/preview/show.gif" alt="动图演示效果" width="250px">

轻量级高扩展adapter

## 导入方式

仅支持`AndroidX`
```
dependencies {
     implementation 'com.android.ydkit:adapter-kit:1.0.0'
}
```

## 使用方法

```java
val adapter = IAdapter(this)

recycler_view.layoutManager = GridLayoutManager(this,2)
recycler_view.adapter=adapter

//数据
val list:ArrayList<IDataItem<*, out RecyclerView.ViewHolder>> = ArrayList()
list.add(TopTabDataItem(NewModel()))//顶部导航
list.add(TopBanner(NewModel()))//顶部轮播图
list.add(GridDataItem(NewModel()))//金刚区
list.add(ActivityDataItem(NewModel()))//活动区域
list.add(ItemTabDataItem(NewModel()))//商品tab栏

for (i in 0..9) {
    if (i % 2 == 0) {
       //feeds流的视频类型
       list.add(VideoDataItem(1, NewModel()))
    } else {
       //feeds流的图片类型
       list.add(ImageDataItem(1, NewModel()))
    }
}

//为adapter添加数据
adapter.addItems(list, false)
```


## License
```text
Copyright [2021] [ydStar]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
